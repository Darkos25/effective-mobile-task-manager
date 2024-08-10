package com.example.EffectiveMobile.Repository;

import com.example.EffectiveMobile.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    User deleteUserById(Long id);

    User findUserByEmail(String email);

}
