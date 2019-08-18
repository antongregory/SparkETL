package structure;

import java.io.Serializable;

public class StartedStream implements Serializable {


    private String dt;

    private String time;

    private String deviceName;

    private String houseNumber;

    private String userId;

    private String countryCode;

    private String programTitle;

    private String season;

    private String seasonEpisode;

    private String genre;

    private String productType;

    public StartedStream (String dt, String time, String deviceName, String houseNumber, String userId, String countryCode, String programTitle, String season, String seasonEpisode, String genre, String productType) {

        this.deviceName = deviceName;
        this.userId = userId;
        this.houseNumber = houseNumber;
        this.season = season;
        this.countryCode = countryCode;
        this.programTitle = programTitle;
        this.seasonEpisode = seasonEpisode;
        this.genre = genre;
        this.productType = productType;
        this.dt = dt;
        this.time =time;
    }

    public StartedStream (String[] input) {
        this(input[0], input[1], input[2], input[3], input[4], input[5], input[6], input[7], input[8], input[9], input[10]);
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSeasonEpisode() {
        return seasonEpisode;
    }

    public void setSeasonEpisode(String seasonEpisode) {
        this.seasonEpisode = seasonEpisode;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }


    @Override
    public String toString() {
        return "StartedStream{" +
                "dt=" + dt +
                ", time=" + time +
                ", deviceName='" + deviceName + '\'' +
                ", houseNumber=" + houseNumber +
                ", userId='" + userId + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", programTitle='" + programTitle + '\'' +
                ", season=" + season +
                ", seasonEpisode='" + seasonEpisode + '\'' +
                ", genre='" + genre + '\'' +
                ", productType='" + productType + '\'' +
                '}';
    }

}