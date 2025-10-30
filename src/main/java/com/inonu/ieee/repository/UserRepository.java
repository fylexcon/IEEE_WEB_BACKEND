package com.inonu.ieee.repository;

import com.inonu.ieee.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<user, UUID> {
}