package domain;

import test.*;

public class Main {
    public static void main(String args[]) {
        CreateTest create = new CreateTest();
        AuthorizationTest authorization = new AuthorizationTest();
        ChangeTest change = new ChangeTest();

        create.start();
        authorization.start();
        change.start();
    }
}
