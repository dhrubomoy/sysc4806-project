package com.sysc4806.project.controller;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldSignInProperly() throws Exception {
        String content = "{\"username\": \"submitter1\", \"password\": \"123456\"}";
        mvc.perform(post("/api/auth/signin")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSignUpAndSignInProperly() throws Exception {
        String content = "{\"name\": \"Some Guy\",\"username\": \"someguy\", \"password\": \"123456\", \"role\": \"editor\"}";
        mvc.perform(post("/api/auth/signup")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"message\":\"User 'someguy' registered successfully!\"}"));

        String signInContent = "{\"username\": \"someguy\", \"password\": \"123456\"}";
        mvc.perform(post("/api/auth/signin")
                .content(signInContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
