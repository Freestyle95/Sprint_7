package org.example.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.models.Courier;
import org.example.steps.CourierSteps;
import org.junit.Test;

import static io.qameta.allure.Allure.step;
import static org.example.utils.CheckUtils.verifyResponseBodyFieldIsNotNull;
import static org.example.utils.CheckUtils.verifyResponseCode;

public class LoginTest extends BaseTest {
    @Test
    @DisplayName("Авторизация курьера. Успешная авторизация.")
    public void successfulAuthorization() {
        Courier courierToCreate = CourierSteps.generateRandomCourier();
        step("Create preconditions", () -> {
            CourierSteps.sendCreateCourierRequest(courierToCreate);
        });
        createdCouriers.add(courierToCreate);
        Response response = CourierSteps.sendCourierLoginRequest(courierToCreate);
        verifyResponseCode(response, HttpStatus.SC_OK);
        verifyResponseBodyFieldIsNotNull(response, "id");
    }

    @Test
    @DisplayName("Авторизация курьера. Несуществующий логин/пароль.")
    public void authorizationWithInvalidCredentials() {
        Courier courierToCreate = CourierSteps.generateRandomCourier();
        Response response = CourierSteps.sendCourierLoginRequest(courierToCreate);
        verifyResponseCode(response, HttpStatus.SC_NOT_FOUND);
    }

}
