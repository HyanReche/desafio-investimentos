package br.com.hyan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hyan.model.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}

