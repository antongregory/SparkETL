# SparkEtl
ETL project using Apache spark - Java

Spark version: 2.4.3
Java version: 1.8

To run the solution, import the project as a Maven project and import the maven dependencies. Make sure to have Java -8
in the system already configured. 

Input files must be placed within a folder called testdata in the project root directory:(whatson.csv and started_streams.csv)


The solution to the problem statements is spread/divided among three classes with its own main method.
The following gives an overview of the approach handled for solving the three problem statements.


### 1.	Sales and rentals broadcast rights:
    Problem: We need to find the broadcast rights for a title to be able to expose it to the analytics team 
    for further analysis. This should be for Product Types: TVOD and EST.
    Matching on most recent date for whatson data and joining based on the house_number and country. 
    
    Solution: SalesRightProblem :: main()
    
    - In the SalesRightProblem, the two input files whatson.csv and started_streams.csv files are parsed and
      read in to different JavaRDD objects
    - Key value pair is created for the data in whatson.csv. However, as the recent date is required 
      reduceByKey is performed on the key value pair which is stored in whatsOnPair.
    - As the solution needs a join based on house and country code, a JavaRddPair is created with 
      house_number + country_code for both the input files separately. The country code is obtained from 
      the broadcast_region column of the WhatsOn file as there is no direct column of country code,
    - Then 'join' operation is applied on the both JavaRddPair -streamDataPair and whatsOnPair
    - As the last step, based on the output format needed the data in JavaRddPair is extracted into a new map
      and displayed as output.
    
 
### 2.	Product and user count:
      Problem: We need to know how many watches a product is getting and how many unique users 
      are watching the content, in what device, country and what product_type 
      
      Solution: ProductUserCount :: main()
    
    - In the ProductUserCount::main(), the input files started_streams.csv files is
      parsed into startedStreamRDD.
    - A javaPairRdd is created from startedStreamRDD in a way that a string  key is used
      in one side and a value of 1 is used on the other side.   JavaPairRDD<String, Long>
    - The string key for the above JavaPair created is with date,device, country,title, and type
    - This is to facilitate the calculation of count per product using mapReduce (productCount)
    - Similarly, another JavaPairRdd is created but with a key including the userId as well.
    - A mapReduce is performed on this pair to find how many users watch a title.
    - countApproxDistinctByKey is applied on the above pair to get unique users watching a product 
      stored in uniqueUsersPerProduct.
    - As the last step, we perform a join on uniqueUsersPerProduct and productCount to
      get the summary required.
      

### 3.	Genre and time of day: 

      Problem: We need a list with the most popular Genre and what hours people watch?
      
      Solution: GenreAndTimeProblem :: main()
      
      - The GenreAndTimeProblem :: main has the solution achived in two critical steps.
      - As the first step the JavaPairRdd is created with a time field, genre and user field
      - The problem demands hour in the solution for which we have calculated the hour
        from the input field time
      - The count of the key field_hour,genre and user is calculated.
      - From the above key - value pair the number of unique users watching a genre per hour
       is calcualted by a mapReduce function
      - The above key- value pair is transformed to a GenrePopularity from which a sort operation
       is performed based on the number of unique users watching a genre on hour.
       
       
  
