package test;

import java.util.List;
import java.util.Random;

import domain.Manager;
import domain.entity.Profile;

public class AuthorizationTest extends Thread{

    private Random randomGenerator;

    public void run(){
        Manager manager = new Manager();
        List<Profile> list = manager.getAllProfiles();
        int index = randomGenerator.nextInt(list.size());
        manager.authorization(list.get(index).getName(), list.get(index).getPassword());
    }
}
