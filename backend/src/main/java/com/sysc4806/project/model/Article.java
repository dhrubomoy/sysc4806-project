package com.sysc4806.project.model;

import javax.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Submitter submitter;

    @JsonFormat
        (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date reviewDeadline;

    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reviewer reviewer;

    private String review;

    public Article() {
        this.reviewStatus = ReviewStatus.SUBMITTED;
    }

    public Article(String title, String text) {
        this.title = title;
        this.text = text;
        this.reviewStatus = ReviewStatus.SUBMITTED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Submitter getSubmitter() {
        return submitter;
    }

    public void setSubmitter(Submitter submitter) {
        this.submitter = submitter;
    }

    public Date getReviewDeadline() {
        return reviewDeadline;
    }

    public void setReviewDeadline(Date reviewDeadline) {
        this.reviewDeadline = reviewDeadline;
    }

    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(ReviewStatus reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
