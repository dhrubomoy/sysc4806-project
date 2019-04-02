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
import org.hamcrest.core.StringContains;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllArticlesWithoutAuth_shouldFailWith403() throws Exception {
        mvc.perform(get("/api/articles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username="submitter1", password="123456", roles={"SUBMITTER"})
    public void getAllArticlesForSubmitter_shouldFailWith403() throws Exception {
        mvc.perform(get("/api/articles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username="submitter1", password="123456", roles={"SUBMITTER"})
    public void submitterShouldCreateArticle() throws Exception {
        String jsonArticle = "{\"title\": \"Title of new article\", \"text\": \"Text of new article\"}";
        mvc.perform(post("/api/submitter/articles/create")
                .content(jsonArticle)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(StringContains.containsString("\"title\":\"Title of new article\"")))
                .andExpect(content().string(StringContains.containsString("\"text\":\"Text of new article\"")));
    }

    @Test
    @WithMockUser(username="editor1", password="123456", roles={"EDITOR"})
    public void editorShouldSetReviewerAndAssignDeadline() throws Exception {
        String params = "{\"reviewerId\": \"3\", \"reviewDeadline\": \"22/11/2019\"}";
        mvc.perform(put("/api/articles/1/setReviewInfo")
                .content(params)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(StringContains.containsString("\"reviewDeadline\":\"22-11-2019 12:00:00\"")))
                .andExpect(content().string(StringContains.containsString("\"reviewer\":{\"id\":3")));
    }

    @Test
    @WithMockUser(username="editor1", password="123456", roles={"EDITOR"})
    public void getAllArticlesForEditor_shouldSucceedWith200() throws Exception {
        mvc.perform(get("/api/articles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(StringContains.containsString("\"title\":\"Title Article 1\"")))
                .andExpect(content().string(StringContains.containsString("\"title\":\"Title Article 2\"")));
    }

    @Test
    @WithMockUser(username="reviewer1", password="123456", roles={"REVIEWER"})
    public void getArticlesForReviewer_shouldSucceedWith200() throws Exception {
        mvc.perform(get("/api/reviewer/articles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(StringContains.containsString("\"id\":1,\"title\":\"Title Article 1\"")));
    }


    // Issue# 21
    @Test
    @WithMockUser(username="editor1", password="123456", roles={"EDITOR"})
    public void assignArticleToDifferentReviewerShouldWork() throws Exception {
        String params = "{\"reviewerId\": \"3\", \"reviewDeadline\": \"22/11/2019\"}";
        mvc.perform(put("/api/articles/2/setReviewInfo")
                .content(params)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(StringContains.containsString("\"reviewDeadline\":\"22-11-2019 12:00:00\"")))
                .andExpect(content().string(StringContains.containsString("\"reviewer\":{\"id\":3")));
        // Assign a different reviewer
        params = "{\"reviewerId\": \"4\", \"reviewDeadline\": \"29/11/2019\"}";
        mvc.perform(put("/api/articles/2/setReviewInfo")
                .content(params)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(StringContains.containsString("\"reviewDeadline\":\"29-11-2019 12:00:00\"")))
                .andExpect(content().string(StringContains.containsString("\"reviewer\":{\"id\":4")));
    }
}
