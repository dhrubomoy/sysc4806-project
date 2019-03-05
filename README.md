# SYSC4806 Project 
## Article submission and review system

[![Build Status](https://travis-ci.org/dhrubomoy/sysc4806-project.svg?branch=master)](https://travis-ci.org/dhrubomoy/sysc4806-project)

### Project Description
Article Submitter submits Article (some attachment, any format is ok). The Editor assigns the article to a Reviewer, and provides a deadline for submission of the review. The Reviewer submits a Review of the article. The Editor accepts/rejects the Article based on the review. The status of the article ("submitted", "in review", "accepted", "rejected") and the review should be visible to the Editor and Submitter. The Editor can list all articles in the system, sort/filter them by status, and highlight/show articles whose reviews are overdue. The Reviewer can list the articles assigned to him/her, and sort them by deadline.

### Current state of the project
**Backend:**
- Models, controllers and repositories have been implemented.
- Sign-in/sign-up by implementing JWT authentication.

**Frontend:**
- User signin/signup.
- Submitter can submit articles, view articles submitted by him.
- Editor can view all articles, assign reviewers, set deadline.

#### Plan for next spring
**Backend:**
- Provide endpoints for
  - Setting review for reviewer
  - Accepting/rejecting articles for editor
- Add integration tests.

**Frontend:**
- Reviewer can set review
- Reviewer can list the articles assigned to him/her, and sort them by deadline
- Once reviewed, editor can accept/reject articles
- Editor can show articles whose reviews are overdue

### Diagrams

#### UML Class Diagram

**[TODO]**

#### Entity Relationship Diagram

![ER Diagram](https://raw.githubusercontent.com/dhrubomoy/sysc4806-project/master/diagrams/erd.jpg)
