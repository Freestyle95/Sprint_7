package org.example.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.models.Courier;
import org.example.steps.CourierSteps;
import org.junit.Test;

import static io.qameta.allure.Allure.step;
import static org.example.utils.CheckUtils.verifyResponseBodyFieldHasValue;
import static org.example.utils.CheckUtils.verifyResponseCode;

public class CreateCourierTest extends BaseTest {

    @Test
    @DisplayName("Создание курьера. Успешное создание курьера.")
    public void createCourier() {
        Courier courierToCreate = CourierSteps.generateRandomCourier();
        Response response = CourierSteps.sendCreateCourierRequest(courierToCreate);
        createdCouriers.add(courierToCreate);
        verifyResponseCode(response, HttpStatus.SC_CREATED);
        verifyResponseBodyFieldHasValue(response, "ok", true);
    }

    @Test
    @DisplayName("Создание курьера. Создание курьеров с одинаковым логином невозможно.")
    public void createDuplicatedCouriersNotAllowed() {
        Courier courierToCreate = CourierSteps.generateRandomCourier();
        step("Create preconditions", () -> {
            CourierSteps.sendCreateCourierRequest(courierToCreate);

        });
        createdCouriers.add(courierToCreate);
        Response secondCreationResponse = CourierSteps.sendCreateCourierRequest(courierToCreate);
        verifyResponseCode(secondCreationResponse, HttpStatus.SC_CONFLICT);
        verifyResponseBodyFieldHasValue(secondCreationResponse, "message", "Этот логин уже используется");
    }
}
