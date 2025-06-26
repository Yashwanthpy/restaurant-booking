package com.example;

import com.google.gson.Gson;
import io.muserver.*;

public class BookingApi {
    private BookingStore store = new BookingStore();
    private final Gson gson = new Gson();

    public BookingApi(BookingStore store) {
        this.store = store;
    }

    public RouteHandler postBooking = (request, response, pathParams) -> {
        String body = request.readBodyAsString();
        Booking booking = gson.fromJson(body, Booking.class);

        if (!isValidBooking(booking)) {
            response.status(400);
            response.write("Invalid booking data or time format.");
            return;
        }

        store.addBooking(booking);
        response.status(201);
        response.write("Booking created!");
    };

    public RouteHandler getBookingsByDate = (request, response, pathParams) -> {
        String date = request.query().get("date");
        if (date == null || date.isEmpty()) {
            response.status(400);
            response.write("Missing date.");
            return;
        }
        response.contentType("application/json");
        response.write(gson.toJson(store.getBookingsByDate(date)));
    };

    private boolean isValidBooking(Booking booking) {
        return booking != null &&
                booking.customerName != null &&
                booking.tableSize > 0 &&
                booking.date != null &&
                booking.time != null &&
                booking.time.matches("^(0[0-9]|1[0-9]|2[0-2]):00$");
    }
}
