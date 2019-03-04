package com.sysc4806.project.repository;

import com.sysc4806.project.model.Editor;

import javax.transaction.Transactional;


@Transactional
public interface EditorRepository extends UserBaseRepository<Editor> { }

