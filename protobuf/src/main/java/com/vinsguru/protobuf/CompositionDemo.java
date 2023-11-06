package com.vinsguru.protobuf;

import com.vinsguru.models.Address;
import com.vinsguru.models.Car;
import com.vinsguru.models.Person;

import java.util.ArrayList;

public class CompositionDemo {
    public static void main(String[] args) {
        Address address = Address.newBuilder()
                .setPostbox(123)
                .setStreet("main street")
                .setCity("Atlanta")
                .build();

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

        ArrayList<Car> cars = new ArrayList<>();
        cars.add(accord);
        cars.add(civic);

        Person sam = Person.newBuilder()
                .setName("sam")
                .setAge(25)
//                .addCar(accord)
//                .addCar(civic)
                .addAllCar(cars)
                .setAddress(address)
                .build();

        System.out.println(sam);
    }
}
