package test;

import domain.Manager;

import java.util.UUID;

public class CreateTest extends Thread{
    public void run(){
        Manager manager = new Manager();
        UUID name = UUID.randomUUID();
        manager.createProfile(name.toString(), "standart_password");
    }
}
