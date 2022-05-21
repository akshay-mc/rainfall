package com.dell.sg.rainfall.model;

import java.util.ArrayList;
import java.util.Date;

public class Item{
    public Date timestamp;
    public ArrayList<Reading> readings;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<Reading> getReadings() {
        return readings;
    }

    public void setReadings(ArrayList<Reading> readings) {
        this.readings = readings;
    }

}
