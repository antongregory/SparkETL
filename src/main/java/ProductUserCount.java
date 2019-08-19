import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class ProductUserCount{


    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("ProductUserCount");
        JavaSparkContext sc = new JavaSparkContext(conf);


        JavaRDD<String> textFile = sc.textFile("testdata/started_streams.csv");

        String header1 = textFile.first();

        JavaRDD<String[]> startedStreamRDD = textFile
                .filter(s -> !s.equalsIgnoreCase(header1))
                .map(s -> s.split(";"));

        // 1. Calculate count per product
        JavaPairRDD<String, Long> productCount = startedStreamRDD.mapToPair(inputLine -> new Tuple2<>( generateKeyWithoutUserId(inputLine),1L))
                . reduceByKey((a, b) -> a + b);

        // 2. Calculate product count with user id
        JavaPairRDD<String, Long> productCountWithUserId = startedStreamRDD.mapToPair(inputLine -> new Tuple2<>( generateKeyWithUserId(inputLine),1L))
                . reduceByKey((a, b) -> a + b).mapToPair(x -> new Tuple2<>(removeUserIdFromKey(x._1()),x._2));

        // 3. Unique user count per product
        JavaPairRDD<String, Long> uniqueUsersPerProduct =productCountWithUserId.countApproxDistinctByKey(2);

        // 4. join the results from 1 and 3
        JavaRDD<String> uniqueUserAndProductCountSummary = productCount.join(uniqueUsersPerProduct).map(
                joined -> joined._1 +","+joined._2._1()+","+joined._2._2() );


        uniqueUserAndProductCountSummary.foreach(d -> System.out.println(d));



    }

    private  static String generateKeyWithoutUserId(String line[]) {
        return  line[StreamDataIndex.DATE]+","
                +line[StreamDataIndex.DEVICE_NAME]+","
                + line[ StreamDataIndex.COUNTRY_CODE ]+","
                +line[ StreamDataIndex.TITLE]+","
                +line[ StreamDataIndex.TYPE];
    }

    private static String generateKeyWithUserId(String line[]) {
        return  line[StreamDataIndex.DATE]+","
                +line[StreamDataIndex.DEVICE_NAME]+","
                + line[ StreamDataIndex.COUNTRY_CODE ]+","
                +line[ StreamDataIndex.TITLE]+","
                +line[ StreamDataIndex.TYPE]+","
                +line[ StreamDataIndex.USER_ID];

    }

    private static String removeUserIdFromKey(String key) {
        int index = key.lastIndexOf(",");
        return key.substring(0,index);
    }

    private interface StreamDataIndex {

        public final static int DATE = 0;
        public final static int DEVICE_NAME = 2;
        public final static int COUNTRY_CODE = 5;
        public final static int TITLE = 6;
        public final static int USER_ID = 4;
        public final static int TYPE = 10;

    }



}
