package com.xbiztechventures.lapp.Model;

/**
 * Created by User on 16-01-2017.
 */
public class Track {

    public String order_no;
    public String date;
    public String cloth;
    public String amount;
    public String status;

    public Track(String order_no, String date, String cloth , String amount, String status) {
        this.order_no = order_no;
        this.date = date;
        this.status = status;
        this.cloth=cloth;
        this.amount=amount;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCloth() {
        return cloth;
    }

    public void setCloth(String cloth) {
        this.cloth = cloth;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
