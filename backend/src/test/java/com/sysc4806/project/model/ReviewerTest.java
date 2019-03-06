package com.sysc4806.project.model;

import org.junit.Test;

public class ReviewerTest {
    @Test
    public void testAddSubmittedArticles(){
        //create a reviewer
        String name = "John Doe";
        String userName = "John.Doe";
        String password = "password";
        Reviewer reviewer = new Reviewer(name, userName, password);

        //create a sample article
        String articleTitle = "Board Green lights One-Tier State Change";
        String articleText = "The Board of Public Accountants has been given the green light to move forward with legislation that\n" +
                "would change Montana from a two-tier licensing state to a one-tier state. As of this newsletter, HB44\n" +
                "has passed the House and is awaiting Senate action. If passed, the Board would no longer issue a\n" +
                "“certificate only” but would require all applicants to qualify for a permit to practice (license) by meeting\n" +
                "the 4-Es – Education, Examination, Experience, & Ethics.\n";
        Article sampleArticle = new Article(articleTitle, articleText);

        //Add the article to be reviewed by this reviewer
        reviewer.addSubmittedArticles(sampleArticle);
        assert(reviewer.getAssignedArticles().contains(sampleArticle));
    }
}
