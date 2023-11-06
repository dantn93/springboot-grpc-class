package com.vinsguru.protobuf;

import com.vinsguru.models.Person;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//import com.vinsguru.models.Person;
public class PersonDemo {
    public static void main(String[] args) throws IOException {
//        ==== compare 2 object ====
//        Person sam1 = Person.newBuilder()
//                .setName("sam")
//                .setAge(10)
//                .build();
//
//        Person sam2 = Person.newBuilder()
//                .setName("sam")
//                .setAge(10)
//                .build();
//
//        System.out.println(sam1.equals(sam2));


//        ==== Serialize: Write object to byte array ===
//        Person sam = Person.newBuilder()
//                .setName("sam")
//                .setAge(10)
//                .build();
        Path path = Paths.get("sam.ser");
//        Files.write(path, sam.toByteArray());

//        ==== Deserialize: Read object from byte array ===
        byte[] bytes = Files.readAllBytes(path);
        Person newSam = Person.parseFrom(bytes);
        System.out.println(newSam);
    }
}
