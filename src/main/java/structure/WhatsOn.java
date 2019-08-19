package structure;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WhatsOn implements Serializable, Comparable<WhatsOn> {

    private Date dt;

    private String houseNumber;



    private  String title;

    private String productCategory;

    private String broadcastRegion;

    private String broadcastType;

    private String broadcastStartDate;

    private String broadcastEndDate;

    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public WhatsOn(String dt, String houseNumber, String title, String productCategory, String broadcastRegion, String broadcastType, String broadcastStartDate, String broadcastEndDate) {
        try{
            this.dt = sDateFormat.parse(dt);
        }catch (ParseException e){
            System.err.println("Parse exception while parsing date "+dt);
            this.dt = new Date();
        }
        this.houseNumber = houseNumber;
        this.title = title;
        this.productCategory = productCategory;
        this.broadcastRegion = broadcastRegion;
        this.broadcastType = broadcastType;
        this.broadcastStartDate = broadcastStartDate;
        this.broadcastEndDate = broadcastEndDate;
    }


    public WhatsOn (String[] input){
        this (input[0],input[1],input[2],input[3],input[4],input[5],input[6],input[7]);
    }


    public Date getDt() {
        return dt;
    }

    public String getDtInStringFormat() {
        return sDateFormat.format(dt);
    }

    public String getHouseNumber() {
        return houseNumber;
    }


    public String getBroadcastStartDate() {
        return broadcastStartDate;
    }


    public String getBroadcastEndDate() {
        return broadcastEndDate;
    }


    @Override
    public String toString() {
        return "WhatsOn{" +
                "dt='" + getDtInStringFormat() + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", title='" + title + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", broadcastRegion='" + broadcastRegion + '\'' +
                ", broadcastType='" + broadcastType + '\'' +
                ", broadcastStartDate='" + broadcastStartDate + '\'' +
                ", broadcastEndDate='" + broadcastEndDate + '\'' +
                '}';
    }

    @Override
    public int compareTo(WhatsOn o) {
        return getDt().compareTo(o.getDt());
    }
}
