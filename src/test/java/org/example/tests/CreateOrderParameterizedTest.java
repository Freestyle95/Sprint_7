package org.example.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.models.Order;
import org.example.steps.OrderSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.example.utils.CheckUtils.verifyResponseCode;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest extends BaseTest {
    private final Order order = OrderSteps.generateRandomOrder();

    public CreateOrderParameterizedTest(String[] colors, String name) {
        this.order.setColor(colors);
    }

    @Parameterized.Parameters(name = "Цвета: {1}")
    public static Object[][] getColorsData() {
        return new Object[][]{
                {new String[]{}, "без цветов"},
                {new String[]{"BLACK"}, "один цвет - BLACK"},
                {new String[]{"GREY"}, "один цвет - GREY"},
                {new String[]{"BLACK", "GREY"}, "два цвета - BLACK, GREY"}
        };
    }

    @Test
    @DisplayName("Создание заказа. Цвета.")
    public void createOrderWithColors() {
        Response response = OrderSteps.sendCreateOrderRequest(order);
        verifyResponseCode(response, HttpStatus.SC_CREATED);
    }
}
