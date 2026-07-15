package com.heroldbloom.heroldbloom_backend.repository;

import com.heroldbloom.heroldbloom_backend.model.DonaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonaRepository extends JpaRepository<DonaModel, Long> {

    Optional<DonaModel> findByEmail(String email);
}