package org.example.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.models.Order;
import org.example.steps.OrderSteps;
import org.junit.Test;

import static org.example.utils.CheckUtils.verifyResponseBodyFieldIsNotNull;
import static org.example.utils.CheckUtils.verifyResponseCode;

public class CreateOrderTest extends BaseTest {

    @Test
    @DisplayName("Создание заказа. Успешное создание заказа.")
    public void createOrder() {
        Order orderToCreate = OrderSteps.generateRandomOrder();
        Response response = OrderSteps.sendCreateOrderRequest(orderToCreate);
        verifyResponseCode(response, HttpStatus.SC_CREATED);
        verifyResponseBodyFieldIsNotNull(response, "track");
    }
}
