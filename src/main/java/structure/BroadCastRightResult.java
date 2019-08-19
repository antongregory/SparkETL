package structure;


import java.io.Serializable;

public class BroadCastRightResult  implements Serializable{

    private String dt;

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


    public BroadCastRightResult(StartedStream streamData, WhatsOn whatsOnData) {
        this.dt = whatsOnData.getDtInStringFormat();
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
