package com.sysc4806.project.controller;

import com.sysc4806.project.model.Reviewer;
import com.sysc4806.project.repository.ReviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private ReviewerRepository reviewerRepository;

    @GetMapping("/api/reviewerList")
    @PreAuthorize("hasRole('EDITOR')")
    public List<Reviewer> getAllReviewers() {
        return reviewerRepository.findAll();
    }
}
