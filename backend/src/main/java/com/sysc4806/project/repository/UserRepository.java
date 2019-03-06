package com.sysc4806.project.repository;

import com.sysc4806.project.model.User;

import javax.transaction.Transactional;


@Transactional
public interface UserRepository extends UserBaseRepository<User> { }

