package com.nye.progkorny.library_system.repository;

import com.nye.progkorny.library_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
