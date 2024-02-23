package com.sw.hs.controller;

import com.sw.hs.data.dto.GameResponseDto;
import com.sw.hs.data.dto.GameReviewDto;
import com.sw.hs.data.entity.Game;
import com.sw.hs.data.entity.GameReview;
import com.sw.hs.data.repository.GameRepository;
import com.sw.hs.data.repository.GameReviewRepository;
import com.sw.hs.service.GameApiService;
import com.sw.hs.service.impl.GameApiServiceImpl;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/game")
//@MultipartConfig(maxFileSize = 1024*1024*2, maxRequestSize = 1024 * 1024 * 10)
public class GameApiController {
    @Autowired
    GameApiService gameApiService;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    GameReviewRepository gameReviewRepository;


    @GetMapping()
    public ResponseEntity<GameResponseDto> getGame(Long id) {
        GameResponseDto gameResponseDto = gameApiService.getGame(id);

        return ResponseEntity.status(HttpStatus.OK).body(gameResponseDto);
    }


    @GetMapping("/getAll")
    public List<Game> getAll(){
        return gameApiService.getAll();   // 버그 해결 못함
    }

    @GetMapping("/getReview")
    public List<GameReview> getReview(Long id){   // 버그 해결 못함
        return getReview(id);
    }
}
