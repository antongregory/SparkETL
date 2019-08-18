package structure;

import java.io.Serializable;

public class GenrePopularity implements Serializable {

    private String watchedHour;

    private String genre;

    private long uniqueCount;


    public GenrePopularity(String genreInfo[], long count){

        this.watchedHour = genreInfo [0];
        this.genre = genreInfo[1];
        this.uniqueCount = count;
    }

    public long getUniqueCount(){
        return this.uniqueCount;
    }


    @Override
    public String toString() {
        return watchedHour+","+genre+","+uniqueCount;
    }

}
