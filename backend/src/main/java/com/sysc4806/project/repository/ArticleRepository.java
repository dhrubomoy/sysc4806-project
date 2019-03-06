package com.sysc4806.project.repository;

import java.util.Optional;

import com.sysc4806.project.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
