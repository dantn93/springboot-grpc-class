package com.vinsguru.protobuf;

import com.vinsguru.models.Car;
import com.vinsguru.models.Dealer;

public class MapDemo {
    public static void main(String[] args) {
        Car accord = Car.newBuilder()
                .setMake("Honda")
                .setModel("Accord")
                .setYear(2020)
                .build();

        Car civic = Car.newBuilder()
                .setMake("Honda")
                .setModel("Civic")
                .setYear(2025)
                .build();

        Dealer dealer = Dealer.newBuilder()
                .putModel(2005, civic)
                .putModel(2020, accord)
                .build();

        System.out.println(dealer.getModelOrDefault(2004, accord));
        System.out.println(dealer.getModelMap());
    }
}
