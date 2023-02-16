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

import static org.example.utils.CheckUtils.verifyResponseBodyFieldHasValue;
import static org.example.utils.CheckUtils.verifyResponseCode;

@RunWith(Parameterized.class)
public class CourierParameterizedTest extends BaseTest {

    Courier courier;

    public CourierParameterizedTest(Courier courier, String name) {
        this.courier = courier;
    }

    @Parameterized.Parameters(name = "Курьер {1}")
    public static Object[][] getCourierData() {
        return new Object[][]{
                {
                        Courier.builder()
                                .firstName(null)
                                .login(RandomStringUtils.randomAlphabetic(8))
                                .password(RandomStringUtils.randomAlphabetic(8)).build(),
                        "без имени"
                },
                {
                        Courier.builder()
                                .firstName(RandomStringUtils.randomAlphabetic(8))
                                .login(null)
                                .password(RandomStringUtils.randomAlphabetic(8)).build(),
                        "без логина"
                },
                {
                        Courier.builder()
                                .firstName(RandomStringUtils.randomAlphabetic(8))
                                .login(RandomStringUtils.randomAlphabetic(8))
                                .password(null).build(),
                        "без пароля"
                }
        };
    }

    @Test
    @DisplayName("Создание курьера. Создание курьера с неполными полями невозможно.")
    public void createCourierRequestValidation() {
        Courier courierToCreate = courier;
        Response response = CourierSteps.sendCreateCourierRequest(courierToCreate);
        verifyResponseCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyResponseBodyFieldHasValue(response, "message", "Недостаточно данных для создания учетной записи");
    }
}
