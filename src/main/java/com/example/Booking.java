package com.example;

public class Booking {
    public String customerName;
    public int tableSize;
    public String date;
    public String time;

    public Booking() {}

    public Booking(String customerName, int tableSize, String date, String time) {
        this.customerName = customerName;
        this.tableSize = tableSize;
        this.date = date;
        this.time = time;
    }
}
