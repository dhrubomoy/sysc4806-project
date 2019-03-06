package com.sysc4806.project;


import com.sysc4806.project.model.*;
import com.sysc4806.project.repository.ArticleRepository;
import com.sysc4806.project.repository.EditorRepository;
import com.sysc4806.project.repository.ReviewerRepository;
import com.sysc4806.project.repository.SubmitterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    // mockPassword = '123456'
    private String mockPassword = "$2a$10$sshM0qVOF2qHJhZoPnw9k.8osFZp7EBVYwqhVNj4/iSCrxIITwX1a";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner loadData(
            SubmitterRepository submitterRepository,
            ReviewerRepository reviewerRepository,
            EditorRepository editorRepository,
            ArticleRepository articleRepository
    ) {
        return (args) -> {
            // Create Submitter

            Submitter submitter1 = submitterRepository.save(new Submitter("Vesta Nichols", "submitter1", mockPassword));
            Submitter submitter2 = submitterRepository.save(new Submitter("Thomas Mathews", "submitter2", mockPassword));

            // Create articles
            createArticle(articleRepository, submitterRepository, submitter1, new Article("Title Article 1", "Text Article 1 by submitter1"));
            createArticle(articleRepository, submitterRepository, submitter1, new Article("Title Article 2", "Text Article 2 By submitter1"));
//            createArticle(articleRepository, submitterRepository, submitter1, new Article("Title Article 3", "Text Article 3 By submitter1 "));
            createArticle(articleRepository, submitterRepository, submitter2, new Article("Title Article 1", "Text Article 1 by submitter2"));
            createArticle(articleRepository, submitterRepository, submitter2, new Article("Title Article 2", "Text Article 2 By submitter2"));

            // Create Reviewers
            Reviewer reviewer1 = reviewerRepository.save(new Reviewer("Margie Parrott", "reviewer1", mockPassword));
            Reviewer reviewer2 = reviewerRepository.save(new Reviewer("David Maurer", "reviewer2", mockPassword));
            Reviewer reviewer3 = reviewerRepository.save(new Reviewer("Roy Grant", "reviewer3", mockPassword));

            Editor editor1 = editorRepository.save(new Editor("Richard Sutton", "editor1", mockPassword));
        };
    }

    private void createArticle(ArticleRepository articleRepository, SubmitterRepository submitterRepository, Submitter submitter, Article article) {
        article.setSubmitter(submitter);
        submitter.addSubmittedArticles(article);
        submitterRepository.save(submitter);
//        articleRepository.save(article);
    }
}