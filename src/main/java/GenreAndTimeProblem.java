package problem3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import structure.GenrePopularity;
import scala.Tuple2;

public class GenreAndTimeProblem {


    public static void main(String[] args) {


        SparkConf conf = new SparkConf().setMaster("local").setAppName("PopularGenre");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> textFile = sc.textFile("testdata/started_streams.csv");

        String header1 = textFile.first();


        JavaRDD<String[]> stringStartedStream = textFile
                .filter(s -> !s.equalsIgnoreCase(header1))
                .map(s -> s.split(";"));

        /**
         * Create a key - count pair
         */
        JavaPairRDD<String, Long> watchCountAndUser = stringStartedStream
                .mapToPair(input -> new Tuple2<>(generateKeyWithUserId(input), 1L))
                .reduceByKey((a, b) -> a + b)
                .mapToPair(row -> new Tuple2<>(removeUserIdFromKey(row._1), 1L))
                .reduceByKey((a, b) -> a + b);


        JavaRDD<GenrePopularity> genrePopularityJavaRDD = watchCountAndUser.map(row -> new GenrePopularity(row._1.split(","),row._2)).
                sortBy(genrePopularity -> genrePopularity.getUniqueCount(), false, 1);

        genrePopularityJavaRDD.foreach(d->System.out.println(d));

        sc.stop();

    }


    /**
     *
     * @param key with userId appended
     * @return key without userId watchedHour,Title
     */
    private static String removeUserIdFromKey (String key){

        int index = key.lastIndexOf(",");
        return key.substring(0,index);
    }

    /**
     *
     * @param input has watchedHour in format HH:mm:ss which will be parsed into HH

     * @return returns a String in appended format watchedHour,genre,userId
     */
    private static String generateKeyWithUserId (String input[]){
        int index = input[StreamDataIndex.HOUR_INDEX] .indexOf(":");

        return input[StreamDataIndex.HOUR_INDEX].substring(0,index) + ","
                +  input[StreamDataIndex.PROGRAM_INDEX] +","
                + input[StreamDataIndex.USER_INDEX];
    }

    interface StreamDataIndex {
        public final static int HOUR_INDEX = 1;
        public final static int USER_INDEX = 4;
        public final static int PROGRAM_INDEX = 9;
    }


}
