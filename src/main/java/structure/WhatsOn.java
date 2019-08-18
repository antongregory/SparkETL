package structure;

import java.io.Serializable;

public class WhatsOn implements Serializable {

    private String dt;

    private String houseNumber;



    private  String title;

    private String productCategory;

    private String broadcastRegion;

    private String broadcastType;

    private String broadcastStartDate;

    private String broadcastEndDate;

    public WhatsOn(String dt, String houseNumber, String title, String productCategory, String broadcastRegion, String broadcastType, String broadcastStartDate, String broadcastEndDate) {
        this.dt = dt;
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


    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getBroadcastRegion() {
        return broadcastRegion;
    }

    public void setBroadcastRegion(String broadcastRegion) {
        this.broadcastRegion = broadcastRegion;
    }

    public String getBroadcastType() {
        return broadcastType;
    }

    public void setBroadcastType(String broadcastType) {
        this.broadcastType = broadcastType;
    }

    public String getBroadcastStartDate() {
        return broadcastStartDate;
    }

    public void setBroadcastStartDate(String broadcastStartDate) {
        this.broadcastStartDate = broadcastStartDate;
    }

    public String getBroadcastEndDate() {
        return broadcastEndDate;
    }

    public void setBroadcastEndDate(String broadcastEndDate) {
        this.broadcastEndDate = broadcastEndDate;
    }

    @Override
    public String toString() {
        return "WhatsOn{" +
                "dt='" + dt + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", title='" + title + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", broadcastRegion='" + broadcastRegion + '\'' +
                ", broadcastType='" + broadcastType + '\'' +
                ", broadcastStartDate='" + broadcastStartDate + '\'' +
                ", broadcastEndDate='" + broadcastEndDate + '\'' +
                '}';
    }


}
