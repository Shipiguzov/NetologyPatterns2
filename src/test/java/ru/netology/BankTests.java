package ru.netology;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.actions.UserAction;
import ru.netology.data.DataGenerator;
import ru.netology.data.LoginData;
import ru.netology.pageObject.DashboardPage;
import ru.netology.pageObject.LoginPage;


import static com.codeborne.selenide.Selenide.open;


public class BankTests {

    private LoginData user;
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeEach
    void setUpBefore() {
        loginPage = open("http://localhost:9999/", LoginPage.class);
    }

    @Test
    void loginCorrect() {
        this.user = DataGenerator.createActiveUser();
        UserAction.addUser(user, requestSpec);
        dashboardPage = loginPage.loginActiveUser(user);
    }

    @Test
    void loginBlockedUser() {
        this.user = DataGenerator.createBlockedUser();
        UserAction.addUser(user, requestSpec);
        loginPage.loginBlockedUser(user);
    }

    @Test
    void loginUserWithChangedPassword() {
        user = DataGenerator.createActiveUser();
        UserAction.addUser(user, requestSpec);
        String oldPassword = user.getPassword();
        user.equals(DataGenerator.changeUserPassword(user));
        UserAction.addUser(user, requestSpec);
        loginPage.loginWithOldPassword(user, oldPassword);
        loginPage.loginActiveUser(user);
    }

    @Test
    void loginUserWithChangedStatus() {
        user = DataGenerator.createBlockedUser();
        UserAction.addUser(user, requestSpec);
        user.equals(DataGenerator.changeUserStatus(user));
        UserAction.addUser(user, requestSpec);
        loginPage.loginActiveUser(user);
    }

    @Test
    void loginIncorrectUser(){
        user = DataGenerator.createActiveUser();
        loginPage.incorrectCredentials(user);
    }
}
