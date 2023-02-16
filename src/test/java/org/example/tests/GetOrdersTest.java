package org.example.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.models.Order;
import org.example.models.OrderList;
import org.example.steps.OrderSteps;
import org.junit.Assert;
import org.junit.Test;

import static io.qameta.allure.Allure.step;

public class GetOrdersTest extends BaseTest {

    @Test
    @DisplayName("Получение списка заказов.")
    public void getOrders() {
        Response response = OrderSteps.sendGetOrdersRequest();
        step("Проверка списка заказов", () -> {
            Order[] orderList = response.as(OrderList.class).getOrders();
            Assert.assertTrue("Список заказов пустой", orderList.length > 0);
        });

    }
}
