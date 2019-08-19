import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import structure.BroadCastRightResult;
import structure.StartedStream;
import structure.WhatsOn;
import scala.Tuple2;

import java.util.HashMap;

public class SalesRightProblem {

    private static HashMap<String,String> countryCodeMap = new HashMap<>();


    static {
        countryCodeMap.put("Sweden","se");
        countryCodeMap.put("Norway","no");
        countryCodeMap.put("Denmark","dk");
        countryCodeMap.put("Finland","fi");
    }

    public static void main(String[] args) {


        SparkConf conf = new SparkConf().setMaster("local").setAppName("SalesRight");
        JavaSparkContext sc = new JavaSparkContext(conf);


        // Load the text into a Spark RDD, which is a distributed representation of each line of text
        JavaRDD<String> streamData = sc.textFile("testdata/started_streams.csv");
        JavaRDD<String> whatSon = sc.textFile("testdata/whatson.csv");

        String streamFileHeader = streamData.first();
        String whatsOnFileHeader = whatSon.first();


        // create a tuple pair with housenumber and country code
        JavaPairRDD<Tuple2<String,String>, StartedStream> streamDataPair = createFilteredRDD(streamData, streamFileHeader, ';' , 10)
                .mapToPair(row -> {
                            return new Tuple2<>( new Tuple2<>(row[3],row[5]),new StartedStream(row));
                        }
                );;


        // create a tuple pair with housenumber and country code
        JavaPairRDD<Tuple2<String,String>, WhatsOn> whatsOnPair = createFilteredRDD(whatSon, whatsOnFileHeader, ',' , 5)
                .mapToPair(row -> {
                            return new Tuple2<>( new Tuple2<>(row[1],getCountryCode(row[4])),new WhatsOn(row));
                        }
                ).reduceByKey(( whatsOnValue1, whatsOnValue2) -> {
                    WhatsOn whatsOnValueWithRecentDate = (whatsOnValue1.compareTo(whatsOnValue2) > 1) ? whatsOnValue1 : whatsOnValue2;
                    return whatsOnValueWithRecentDate;
                } );

        // Join the data from two rdd pairs
        JavaPairRDD <Tuple2<String,String>,Tuple2<StartedStream,WhatsOn>> joinedData
                = streamDataPair.join(whatsOnPair);

        // compose the result from the joined data
        JavaRDD<BroadCastRightResult> broadCastRightResultJavaRDD = joinedData.map(tupleV -> new BroadCastRightResult(tupleV._2._1,tupleV._2._2));
        broadCastRightResultJavaRDD.foreach(d->System.out.println(d));

    }


    private static JavaRDD<String[]> createFilteredRDD (JavaRDD<String> data, String header, char regex, int indexToFilter){

        JavaRDD<String[]> filtereResult = data
                .filter(s -> !s.equalsIgnoreCase(header))
                .map(s -> s.split(""+regex))
                .filter(row ->{
                    return (row[indexToFilter].equalsIgnoreCase("TVOD") || row[indexToFilter].equalsIgnoreCase("EST") );
                });

        return filtereResult;
    }


    /**
     * Country code is not available in whatson .
     * Helper function to compose country code from broadcast region
     *
     * @param country
     * @return
     */
    private static String getCountryCode(String country){
        String countryCode = countryCodeMap.get(country);
        if(countryCode == null){
            return "na";
        } else {
            return countryCode;
        }
    }




}
