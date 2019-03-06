package com.sysc4806.project.repository;

import com.sysc4806.project.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends JpaRepository<T, Long> {
    Optional<T> findByUsername(String username);
    Boolean existsByUsername(String username);
}
