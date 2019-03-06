package com.sysc4806.project.repository;

import com.sysc4806.project.model.Submitter;

import javax.transaction.Transactional;


@Transactional
public interface SubmitterRepository extends UserBaseRepository<Submitter> { }
