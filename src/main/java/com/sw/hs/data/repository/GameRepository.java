package com.sw.hs.data.repository;

import com.sw.hs.data.entity.Game;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GameRepository extends JpaRepository<Game, Long> {
}
