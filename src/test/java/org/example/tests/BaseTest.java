package org.example.tests;

import io.restassured.RestAssured;
import org.example.ApiConfig;
import org.example.models.Courier;
import org.example.steps.CourierSteps;
import org.junit.After;
import org.junit.Before;

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
        CourierSteps.cleanCouriersPostAction(createdCouriers);
    }
}
