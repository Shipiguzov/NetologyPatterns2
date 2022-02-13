package ru.netology;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.actions.UserAction;
import ru.netology.dataGenerator.BankData;


public class BankTests {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static BankData data = new BankData();

    @BeforeAll
    static void setUpAll() {
    }

    @Test
    void isUserPresent(){
        data.createUser();
        System.out.println(data);
        UserAction.addUser(data, requestSpec);
        /*given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .when() // "когда"
                .get("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(500);*/
    }
}
