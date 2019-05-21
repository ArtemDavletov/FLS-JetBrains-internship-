package test;

import domain.Manager;
import domain.entity.Profile;

import java.util.List;
import java.util.Random;

public class ChangeTest extends Thread{

    private Random randomGenerator;

    public void run(){
        Manager manager = new Manager();
        List<Profile> list = manager.getAllProfiles();
        int index = randomGenerator.nextInt(list.size());
        manager.changePassword(list.get(index).getName(), "new_password");
    }
}
