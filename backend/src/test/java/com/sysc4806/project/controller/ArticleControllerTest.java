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
        String expected = "{\"id\":7,\"title\":\"Title of new article\",\"text\":\"Text of new article\",\"submitter\":{\"id\":1,\"name\":\"Vesta Nichols\",\"username\":\"submitter1\",\"role\":\"ROLE_SUBMITTER\"},\"reviewDeadline\":null,\"reviewStatus\":\"SUBMITTED\",\"reviewer\":null,\"review\":null}";
        mvc.perform(post("/api/submitter/articles/create")
                .content(jsonArticle)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    @WithMockUser(username="editor1", password="123456", roles={"EDITOR"})
    public void editorShouldSetReviewerAndAssignDeadline() throws Exception {
        String params = "{\"reviewerId\": \"3\", \"reviewDeadline\": \"22/11/2019\"}";
        String expected = "{\"id\":1,\"title\":\"Title Article 1\",\"text\":\"Text Article 1 by submitter1\",\"submitter\":{\"id\":1,\"name\":\"Vesta Nichols\",\"username\":\"submitter1\",\"role\":\"ROLE_SUBMITTER\",\"hibernateLazyInitializer\":{}},\"reviewDeadline\":\"22-11-2019 12:00:00\",\"reviewStatus\":\"SUBMITTED\",\"reviewer\":{\"id\":3,\"name\":\"Margie Parrott\",\"username\":\"reviewer1\",\"role\":\"ROLE_REVIEWER\"},\"review\":null}";
        mvc.perform(put("/api/articles/1/setReviewInfo")
                .content(params)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    @WithMockUser(username="editor1", password="123456", roles={"EDITOR"})
    public void getAllArticlesForEditor_shouldSucceedWith200() throws Exception {
        String content = "[{\"id\":1,\"title\":\"Title Article 1\",\"text\":\"Text Article 1 by submitter1\",\"submitter\":{\"id\":1,\"name\":\"Vesta Nichols\",\"username\":\"submitter1\",\"role\":\"ROLE_SUBMITTER\",\"hibernateLazyInitializer\":{}},\"reviewDeadline\":\"22-11-2019 12:00:00\",\"reviewStatus\":\"SUBMITTED\",\"reviewer\":{\"id\":3,\"name\":\"Margie Parrott\",\"username\":\"reviewer1\",\"role\":\"ROLE_REVIEWER\",\"hibernateLazyInitializer\":{}},\"review\":null},{\"id\":2,\"title\":\"Title Article 1\",\"text\":\"Text Article 1 by submitter1\",\"submitter\":{\"id\":1,\"name\":\"Vesta Nichols\",\"username\":\"submitter1\",\"role\":\"ROLE_SUBMITTER\",\"hibernateLazyInitializer\":{}},\"reviewDeadline\":null,\"reviewStatus\":\"SUBMITTED\",\"reviewer\":null,\"review\":null},{\"id\":3,\"title\":\"Title Article 2\",\"text\":\"Text Article 2 By submitter1\",\"submitter\":{\"id\":1,\"name\":\"Vesta Nichols\",\"username\":\"submitter1\",\"role\":\"ROLE_SUBMITTER\",\"hibernateLazyInitializer\":{}},\"reviewDeadline\":null,\"reviewStatus\":\"SUBMITTED\",\"reviewer\":null,\"review\":null},{\"id\":4,\"title\":\"Title Article 1\",\"text\":\"Text Article 1 by submitter2\",\"submitter\":{\"id\":2,\"name\":\"Thomas Mathews\",\"username\":\"submitter2\",\"role\":\"ROLE_SUBMITTER\",\"hibernateLazyInitializer\":{}},\"reviewDeadline\":null,\"reviewStatus\":\"SUBMITTED\",\"reviewer\":null,\"review\":null},{\"id\":5,\"title\":\"Title Article 1\",\"text\":\"Text Article 1 by submitter2\",\"submitter\":{\"id\":2,\"name\":\"Thomas Mathews\",\"username\":\"submitter2\",\"role\":\"ROLE_SUBMITTER\",\"hibernateLazyInitializer\":{}},\"reviewDeadline\":null,\"reviewStatus\":\"SUBMITTED\",\"reviewer\":null,\"review\":null},{\"id\":6,\"title\":\"Title Article 2\",\"text\":\"Text Article 2 By submitter2\",\"submitter\":{\"id\":2,\"name\":\"Thomas Mathews\",\"username\":\"submitter2\",\"role\":\"ROLE_SUBMITTER\",\"hibernateLazyInitializer\":{}},\"reviewDeadline\":null,\"reviewStatus\":\"SUBMITTED\",\"reviewer\":null,\"review\":null},{\"id\":7,\"title\":\"Title of new article\",\"text\":\"Text of new article\",\"submitter\":{\"id\":1,\"name\":\"Vesta Nichols\",\"username\":\"submitter1\",\"role\":\"ROLE_SUBMITTER\",\"hibernateLazyInitializer\":{}},\"reviewDeadline\":null,\"reviewStatus\":\"SUBMITTED\",\"reviewer\":null,\"review\":null}]";
        mvc.perform(get("/api/articles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(content));
    }
}
