package ru.netology.dataGenerator;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankData {

    private String login;
    private String password;
    private Status status;

    public void createUser() {
        Faker faker = new Faker(new Locale("en"));
        this.setLogin(faker.name().lastName());
        this.setPassword(faker.internet().password(5, 8));
        int number = faker.number().numberBetween(0, 1);
        switch (number) {
            case (0):
                this.setStatus(Status.ACTIVE);
                break;
            case (1):
                this.setStatus(Status.BLOCKED);
                break;
        }
    }
}
