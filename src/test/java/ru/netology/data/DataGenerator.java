package ru.netology.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {

    private static Faker faker = new Faker(new Locale("en"));

    public static LoginData createActiveUser() {
        LoginData user = new LoginData();
        user.setLogin(faker.name().lastName());
        user.setPassword(faker.internet().password(5, 8));
        user.setStatus(Status.ACTIVE.name().toLowerCase());
        return user;
    }

    public static LoginData createBlockedUser() {
        LoginData user = createActiveUser();
        user.setStatus(Status.BLOCKED.name().toLowerCase());
        return user;
    }

    public static LoginData changeUserPassword(LoginData user){
        user.setPassword(faker.internet().password(5, 8));
        return user;
    }

    public static LoginData changeUserStatus(LoginData user){
        if (user.getStatus().equals(Status.ACTIVE.name().toLowerCase()))
            user.setStatus(Status.BLOCKED.name().toLowerCase());
        else
            user.setStatus(Status.ACTIVE.name().toLowerCase());
        return user;
    }
}
