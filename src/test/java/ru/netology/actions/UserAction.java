package ru.netology.actions;

import com.google.gson.Gson;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;
import ru.netology.dataGenerator.BankData;

import java.util.zip.GZIPInputStream;

import static io.restassured.RestAssured.given;

public class UserAction {

    public static BankData addUser(BankData data, RequestSpecification requestSpec){
        // сам запрос
        //TODO: Server return error 500
        ResponseBodyExtractionOptions test = given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(data) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("api/system/users")
                .then() // "тогда ожидаем"
                .statusCode(200)// код 200 OK
                .assertThat()
                .extract()
                .body();
        System.out.println(test.asString());
        return null;
    }
}
