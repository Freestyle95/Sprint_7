package org.example.tests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.example.ApiConfig;
import org.example.models.Courier;
import org.example.steps.CourierSteps;
import org.junit.After;
import org.junit.Before;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    protected List<Courier> createdCouriers;

    @Before
    public void setUp() {
        RestAssured.baseURI = ApiConfig.SERVER_URL;
        RestAssured.basePath = ApiConfig.BASE_PATH;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        createdCouriers = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (Courier courier : createdCouriers) {
            try {
                if (courier.getId() == null) {
                    courier = CourierSteps.sendCourierLoginRequest(courier)
                            .then().statusCode(HttpStatus.SC_OK)
                            .extract().as(Courier.class);
                }
                CourierSteps.sendDeleteCourierRequest(courier)
                        .then().statusCode(HttpStatus.SC_OK);
            } catch (Exception ex) {
                System.out.println(MessageFormat.format("Не удалось удалить курьера %s", courier.toString()));
            }
        }
    }
}
