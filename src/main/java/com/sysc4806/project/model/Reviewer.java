package com.sysc4806.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Reviewer extends User {

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Article> assignedArticles;

    public Reviewer() {}

    public Reviewer(String name, String username, String password) {
        super(name, username, password);
        this.assignedArticles = new ArrayList<>();
        this.setRole(Role.ROLE_REVIEWER);
    }

    public List<Article> getAssignedArticles() {
        return assignedArticles;
    }

    public void setAssignedArticles(List<Article> assignedArticles) {
        this.assignedArticles = assignedArticles;
    }

    public void addSubmittedArticles(Article article) {
        this.assignedArticles.add(article);
    }
}
