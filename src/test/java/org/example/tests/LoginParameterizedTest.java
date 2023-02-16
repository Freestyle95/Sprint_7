package org.example.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.example.models.Courier;
import org.example.steps.CourierSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.example.utils.CheckUtils.verifyResponseCode;

@RunWith(Parameterized.class)
public class LoginParameterizedTest extends BaseTest {
    private final Courier courier;

    public LoginParameterizedTest(Courier courier, String name) {
        this.courier = courier;
    }

    @Parameterized.Parameters(name = "Курьер {1}")
    public static Object[][] getCourierData() {
        return new Object[][]{
                {
                        Courier.builder()
                                .login(null)
                                .password(RandomStringUtils.randomAlphabetic(8)).build(),
                        "без логина"
                },
                {
                        Courier.builder()
                                .login(RandomStringUtils.randomAlphabetic(8))
                                .password(null).build(),
                        "без пароля"
                }
        };
    }

    @Test
    @DisplayName("Авторизация курьера. Авторизация с неполными данными невозможна.")
    public void authorizationRequestValidation() {
        Response response = CourierSteps.sendCourierLoginRequest(courier);
        verifyResponseCode(response, HttpStatus.SC_BAD_REQUEST);
    }
}
