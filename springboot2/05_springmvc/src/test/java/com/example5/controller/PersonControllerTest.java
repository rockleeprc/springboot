package com.example5.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


//@SpringBootTest // 加载所有的bean
@WebMvcTest(PersonController.class) // 只加载 PersonController bean
@AutoConfigureMockMvc // 自动装配MockMvc
@ExtendWith(SpringExtension.class) // 构建运行时Servlet环境
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    /*
    @BeforeAll
    static void beforeAll() {
        mockMvc = MockMvcBuilders.standaloneSetup(new PersonController()).build();
    }*/

    @Test
    public void testPerson() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .request(HttpMethod.GET, "/person"))
                .andExpect(MockMvcResultMatchers.status().isOk()) // 200
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("2020-06-27"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("aa"))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

}
