package com.azimov.mygameapp.repositories;

import com.azimov.mygameapp.models.GameUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameUserRepository extends JpaRepository<GameUser, Integer> {
    Optional<GameUser> findByUsername(String username);
    Optional<GameUser> findByName(String name);

}
