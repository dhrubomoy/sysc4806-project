package com.sysc4806.project.message.request;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class ReviewerForArticle {

    private Long reviewerId;

    @NotBlank
    private String reviewDeadline;

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewDeadline() {
        return reviewDeadline;
    }

    public void setReviewDeadline(String reviewDeadline) {
        this.reviewDeadline = reviewDeadline;
    }
}
