package com.sw.hs.service;

import com.sw.hs.data.dto.GameResponseDto;
import com.sw.hs.data.dto.GameReviewDto;
import com.sw.hs.data.entity.Game;
import com.sw.hs.data.entity.GameReview;

import java.util.List;

public interface GameApiService {
    public Game getOneDetail(long id);

    public GameResponseDto getGame(Long id);

    public List<Game> getAll();


    public List<GameReview> getReview(Long id);

    }
