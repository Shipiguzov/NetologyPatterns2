package ru.netology.actions;

import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;
import ru.netology.data.LoginData;

import static io.restassured.RestAssured.given;

public class UserAction {

    public static LoginData addUser(LoginData data, RequestSpecification requestSpec) {
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(data) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("api/system/users")
                .then() // "тогда ожидаем"
                .statusCode(200);// код 200 OK
        return null;
    }
}
