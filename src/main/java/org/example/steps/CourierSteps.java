package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.models.Courier;

import static io.restassured.RestAssured.given;

public class CourierSteps {
    @Step("Отправка запроса на логин")
    public static Response sendCourierLoginRequest(Courier courier) {
        Courier json = Courier.builder()
                .login(courier.getLogin())
                .password(courier.getPassword())
                .build();
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .post("/courier/login");
    }

    @Step("Генерация случайного курьера")
    public static Courier generateRandomCourier() {
        return Courier.builder()
                .login(RandomStringUtils.randomAlphabetic(8))
                .password(RandomStringUtils.randomAlphabetic(8))
                .firstName(RandomStringUtils.randomAlphabetic(8))
                .build();

    }

    @Step("Отправка запроса на создание курьера")
    public static Response sendCreateCourierRequest(Courier courier) {
        return given()
                .header("Content-Type", "application/json")
                .body(courier)
                .when()
                .post("/courier");
    }

    @Step("Отправка запроса на удаление курьера")
    public static Response sendDeleteCourierRequest(Courier courier) {
        return given()
                .header("Content-Type", "application/json")
                .pathParam("id", courier.getId())
                .body(Courier.builder().id(courier.getId()).build())
                .delete("/courier/{id}");
    }

}
