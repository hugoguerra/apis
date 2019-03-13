package com.generator.key.app.controller.test;

import com.generator.key.app.Application;
import com.generator.key.app.service.GeneratorService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class GeneratorServiceTest {

    @Autowired
    private GeneratorService service;

    private HttpServletRequest request;

    private HttpServletResponse response;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @After
    public void close(){

    }

    @Test
    public void test() {
        boolean generatorKey = service.generatorKey(request, response);
        Assert.assertTrue(generatorKey);
    }
}
