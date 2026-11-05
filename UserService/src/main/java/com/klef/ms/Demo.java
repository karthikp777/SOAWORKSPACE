package com.klef.ms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Demo 
{
    private int id;
    private String name;
    private String email;

    public void test() 
    {
        Demo demo = new Demo();

        // Lombok-generated setter methods
        demo.setId(101);
        demo.setName("KLU");
        demo.setEmail("demo@gmail.com");

        // Lombok-generated getter methods
        System.out.println("ID    : " + demo.getId());
        System.out.println("Name  : " + demo.getName());
        System.out.println("Email : " + demo.getEmail());
    }
}