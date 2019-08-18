package structure;


public class BroadCastRightResult {

    private String dt;

    public BroadCastRightResult(String dt, String time, String deviceName, String houseNumber, String userId, String countryCode, String programTitle, String season, String seasonEpisode, String genre, String productType, String broadcastStartDate, String broadcastEndDate) {
        this.dt = dt;
        this.time = time;
        this.deviceName = deviceName;
        this.houseNumber = houseNumber;
        this.userId = userId;
        this.countryCode = countryCode;
        this.programTitle = programTitle;
        this.season = season;
        this.seasonEpisode = seasonEpisode;
        this.genre = genre;
        this.productType = productType;
        this.broadcastStartDate = broadcastStartDate;
        this.broadcastEndDate = broadcastEndDate;
    }


    public BroadCastRightResult(StartedStream streamData, WhatsOn whatsOnData) {
        this.dt = whatsOnData.getDt();
        this.time = streamData.getTime();
        this.deviceName = streamData.getDeviceName();
        this.houseNumber = whatsOnData.getHouseNumber();
        this.userId = streamData.getUserId();
        this.countryCode = streamData.getCountryCode();
        this.programTitle = streamData.getProgramTitle();
        this.season = streamData.getSeason();
        this.seasonEpisode = streamData.getSeasonEpisode();
        this.genre = streamData.getGenre();
        this.productType = streamData.getProductType();
        this.broadcastStartDate = whatsOnData.getBroadcastStartDate();
        this.broadcastEndDate = whatsOnData.getBroadcastEndDate();
    }

    private String time;

    private String deviceName;

    private String houseNumber;

    private String userId;

    private String countryCode;

    private  String programTitle;

    private String season;

    private String seasonEpisode;

    private String genre;

    private String productType;

    private String broadcastStartDate;


    private String broadcastEndDate;



    @Override
    public String toString() {
        return
                dt + ',' +  time + ','+deviceName + ','+ houseNumber + ',' + userId + ',' + countryCode + ',' +
                 programTitle + ',' + season + ',' +
                seasonEpisode + ',' +
                genre + ','+
                productType + ','+
                broadcastStartDate + ',' +
                broadcastEndDate ;
    }


}
