package com.sysc4806.project.repository;

import com.sysc4806.project.model.Reviewer;

import javax.transaction.Transactional;


@Transactional
public interface ReviewerRepository extends UserBaseRepository<Reviewer> { }

