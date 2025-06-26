package com.example;

import io.muserver.Method;
import io.muserver.MuServer;
import io.muserver.MuServerBuilder;

public class Main {
    public static void main(String[] args) {
        BookingStore store = new BookingStore();
        BookingApi api = new BookingApi(store);

        MuServer server = MuServerBuilder.httpServer()
                .withHttpPort(8080)
                .addHandler(Method.POST, "/bookings", api.postBooking)
                .addHandler(Method.GET, "/bookings", api.getBookingsByDate)
                .start();

        System.out.println("Server started at " + server.uri());
    }
}
