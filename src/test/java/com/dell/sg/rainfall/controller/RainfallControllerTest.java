package com.dell.sg.rainfall.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RainfallControllerTest {
    @Autowired
    private RainfallController rainfallController;

    @Test
    public void testRetrieveRainfallStats(){
        String result;
        System.out.println("Executing Test");
        result = rainfallController.retrieveRainfallStats();
        assertThat(result).isNotNull();
        assertThat(result).contains("Marina Gardens Drive");
        assertThat(result).contains("Raining");
        System.out.println("Testing completed");
    }
}
