package com.alan.springbasic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args){
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("ABCD");
        helloLombok.setAge(1);

        System.out.println(helloLombok);
    }
}
