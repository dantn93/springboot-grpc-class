package com.vinsguru.protobuf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Int32Value;
import com.google.protobuf.InvalidProtocolBufferException;
import com.vinsguru.json.JPerson;
import com.vinsguru.models.Person;

import java.io.IOException;

public class PerformanceTest {
    public static void main(String[] args) {
        // json
        Runnable json = () -> {
            JPerson person = new JPerson();
            person.setName("sam");
            person.setAge(10);
            ObjectMapper mapper = new ObjectMapper();
            try {
                byte[] bytes = mapper.writeValueAsBytes(person);
                JPerson person1 = mapper.readValue(bytes, JPerson.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        // protobuf
        Person sam = Person.newBuilder()
                .setName("sam")
                .setAge(Int32Value.newBuilder().setValue(25).build())
                .build();

        Runnable proto = () -> {
            try {
                byte[] bytes = sam.toByteArray();
                Person sam1 = Person.parseFrom(bytes);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5; i++) {
            runPerformanceTest(json, "JSON");
            runPerformanceTest(proto, "PROTO");
        }



    }

    private static void runPerformanceTest(Runnable runnable, String method) {
        long time1 = System.currentTimeMillis();
        for(int i=0;i<1_000_000; i++) {
            runnable.run();
        }

        long time2 = System.currentTimeMillis();
        System.out.println(method + " : " + (time2 - time1) + " ms");
    }
}
