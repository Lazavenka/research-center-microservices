package com.roger.researchcenter.repository;

import com.roger.researchcenter.model.UserRegisterToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserRegisterToken, Long> {
    Optional<UserRegisterToken> findUserRegisterTokenByToken(String token);
}
