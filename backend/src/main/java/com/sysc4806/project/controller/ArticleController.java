package com.sysc4806.project.controller;

import com.sysc4806.project.message.request.ReviewerForArticle;
import com.sysc4806.project.model.Article;
import com.sysc4806.project.model.ReviewStatus;
import com.sysc4806.project.model.Reviewer;
import com.sysc4806.project.model.Submitter;
import com.sysc4806.project.repository.ArticleRepository;
import com.sysc4806.project.repository.ReviewerRepository;
import com.sysc4806.project.repository.SubmitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private SubmitterRepository submitterRepository;

    @Autowired
    private ReviewerRepository reviewerRepository;

    @GetMapping("/api/articles")
    @PreAuthorize("hasRole('EDITOR')")
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @PostMapping("/api/submitter/articles/create")
    @PreAuthorize("hasRole('SUBMITTER')")
    public Article createArticle(@Valid @RequestBody Article article, Principal principal) {
        String username = principal.getName();
        Submitter submitter = submitterRepository.findByUsername(username).get();
        submitter.addSubmittedArticles(article);
        article.setSubmitter(submitter);
        return articleRepository.save(article);
    }

    @PutMapping("/api/articles/{id}/setReviewInfo")
    @PreAuthorize("hasRole('EDITOR')")
    public Article updateArticleWithReviewer(@PathVariable(value = "id") Long articleId,
                                          @Valid @RequestBody ReviewerForArticle reviewerInfo) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Could not find Article with id="+articleId));
        Reviewer reviewer = reviewerRepository.findById(reviewerInfo.getReviewerId())
                .orElseThrow(() -> new RuntimeException("Could not find Reviewer with id="+reviewerInfo.getReviewerId()));
        article.setReviewer(reviewer);
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = new Date();
        try {
            date = formatter.parse(reviewerInfo.getReviewDeadline());
        } catch (Exception e) {
//            "Error parsing date: " + e.getMessage();
        }
        article.setReviewDeadline(date);
        article.setReviewStatus(ReviewStatus.IN_REVIEW);
        reviewer.addAssginedArticles(article);
        return articleRepository.save(article);
    }

    @PutMapping("/api/articles/{id}/setReview")
    @PreAuthorize("hasRole('REVIEWER')")
    public Article reviewArticle(@PathVariable(value = "id") Long articleId,
                                             @Valid @RequestBody String review) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Could not find Article with id="+articleId));
        article.setReview(review);
        return articleRepository.save(article);
    }

    @GetMapping("/api/submitter/articles")
    @PreAuthorize("hasRole('SUBMITTER')")
    public List<Article> getSubmitterArticles(Principal principal) {
        String username = principal.getName();
        Optional<Submitter> submitter = submitterRepository.findByUsername(username);
        return submitter.get().getSubmittedArticles();
    }

    @GetMapping("/api/reviewer/articles")
    @PreAuthorize("hasRole('REVIEWER')")
    public List<Article> getArticlesForReviewer(Principal principal) {
        String username = principal.getName();
        Optional<Reviewer> reviewer = reviewerRepository.findByUsername(username);
        return reviewer.get().getAssignedArticles();
    }

}
