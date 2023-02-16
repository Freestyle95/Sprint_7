package org.example.steps;


import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.example.models.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class OrderSteps {
    @Step("Отправка запроса на создание заказа")
    public static Response sendCreateOrderRequest(Order order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .post("/orders");
    }

    @Step("Генерация случайного заказа")
    public static Order generateRandomOrder() {
        return Order.builder()
                .firstName(RandomStringUtils.randomAlphabetic(8))
                .lastName(RandomStringUtils.randomAlphabetic(8))
                .address(RandomStringUtils.randomAlphabetic(8))
                .metroStation(RandomStringUtils.randomAlphabetic(8))
                .phone(RandomStringUtils.randomAlphabetic(8))
                .rentTime(RandomUtils.nextInt(1, 99999))
                .deliveryDate(LocalDateTime.now().plusMonths(1).format(DateTimeFormatter.ISO_DATE_TIME))
                .comment(RandomStringUtils.randomAlphabetic(8))
                .color(new String[]{"BLACK"})
                .build();
    }

    @Step("Отправка запроса на получение заказа по номеру: {0}")
    public static Response sendGetOrderByTrackRequest(Integer track) {
        return given()
                .queryParam("t", track)
                .get("/orders/track");
    }

    @Step("Отправка запроса на получение списка заказов")
    public static Response sendGetOrdersRequest(Map<String, ?> queryParams) {
        return given()
                .queryParams(queryParams)
                .get("/orders");
    }

    @Step("Отправка запроса на получение списка заказов")
    public static Response sendGetOrdersRequest() {
        return given()
                .get("/orders");
    }
}
