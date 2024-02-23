package com.sw.hs.data.repository;


import com.sw.hs.data.entity.GameReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameReviewRepository extends JpaRepository<GameReview, Long> {
}
