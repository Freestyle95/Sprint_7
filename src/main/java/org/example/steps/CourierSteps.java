package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.example.ApiConfig;
import org.example.models.Courier;

import java.text.MessageFormat;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CourierSteps {

    private final static String COURIER_PATH = ApiConfig.BASE_PATH + "/courier";

    @Step("Отправка запроса на логин")
    public static Response sendCourierLoginRequest(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .basePath(COURIER_PATH)
                .body(courier)
                .post("/login");
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
                .basePath(COURIER_PATH)
                .body(courier)
                .when()
                .post();
    }

    @Step("Отправка запроса на удаление курьера")
    public static Response sendDeleteCourierRequest(Courier courier) {
        return given()
                .header("Content-Type", "application/json")
                .basePath(COURIER_PATH)
                .pathParam("id", courier.getId())
                .body(Courier.builder().id(courier.getId()).build())
                .delete("/{id}");
    }

    @Step("Удаление курьера")
    public static void deleteCourier(Courier courier) {
        if (courier.getId() == null) {
            courier.setId(CourierSteps.sendCourierLoginRequest(courier)
                    .then().statusCode(HttpStatus.SC_OK)
                    .extract().as(Courier.class).getId());
        }
        CourierSteps.sendDeleteCourierRequest(courier)
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Step("Post-actions: удаление курьеров, если они были созданы")
    public static void cleanCouriersPostAction(List<Courier> createdCouriers) {
        for (Courier courier : createdCouriers) {
            try {
                deleteCourier(courier);
            } catch (Exception ex) {
                System.out.println(MessageFormat.format("Не удалось удалить курьера %s", courier.toString()));
            }
        }
    }

}
