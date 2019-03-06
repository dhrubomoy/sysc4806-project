package com.sysc4806.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import  java.util.*;

@Entity
public class Submitter extends User {

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Article> submittedArticles;

    public Submitter() {}

    public Submitter(String name, String username, String password) {
        super(name, username, password);
        this.submittedArticles = new ArrayList<>();
        this.setRole(Role.ROLE_SUBMITTER);
    }

    public List<Article> getSubmittedArticles() {
        return submittedArticles;
    }

    public void setSubmittedArticles(List<Article> submittedArticles) {
        this.submittedArticles = submittedArticles;
    }

    public void addSubmittedArticles(Article article) {
        this.submittedArticles.add(article);
    }
}
