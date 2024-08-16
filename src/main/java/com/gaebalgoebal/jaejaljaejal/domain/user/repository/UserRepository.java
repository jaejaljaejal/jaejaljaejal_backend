package com.gaebalgoebal.jaejaljaejal.domain.user.repository;

import com.gaebalgoebal.jaejaljaejal.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
