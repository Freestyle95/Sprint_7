package org.example.utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CheckUtils {
    @Step("Проверка кода ответа. Ожидаемое значение: {1}")
    public static void verifyResponseCode(Response response, int httpStatus) {
        response.then().statusCode(httpStatus);
    }

    @Step("Проверка тела ответа. Поле: {1}; ожидаемое значение: {2}")
    public static void verifyResponseBodyFieldHasValue(Response response, String fieldName, boolean value) {
        response.then().body(fieldName, equalTo(value));
    }

    @Step("Проверка тела ответа. Поле: {1}; ожидаемое значение: {2}")
    public static void verifyResponseBodyFieldHasValue(Response response, String fieldName, String value) {
        response.then().body(fieldName, equalTo(value));
    }

    @Step("Проверка тела ответа. Поле: {1} не пустое")
    public static void verifyResponseBodyFieldIsNotNull(Response response, String fieldName) {
        response.then().body(fieldName, notNullValue());
    }
}
