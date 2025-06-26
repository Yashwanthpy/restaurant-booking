package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class BookingStore {
    private final List<Booking> bookings = new ArrayList<>();

    public synchronized void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public synchronized List<Booking> getBookingsByDate(String date) {
        return bookings.stream()
                .filter(b -> b.date.equals(date))
                .collect(Collectors.toList());
    }
}
