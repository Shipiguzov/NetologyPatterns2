package ru.netology.actions;

import io.restassured.specification.RequestSpecification;
import ru.netology.dataGenerator.BankData;

import static io.restassured.RestAssured.given;

public class UserAction {

    public static void addUser(BankData data, RequestSpecification requestSpec){
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(data) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(500); // код 200 OK
    }
}
