package com.sw.hs.service.impl;

import com.sw.hs.data.dto.GameResponseDto;
import com.sw.hs.data.dto.GameReviewDto;
import com.sw.hs.data.entity.Game;
import com.sw.hs.data.entity.GameReview;
import com.sw.hs.data.repository.GameRepository;
import com.sw.hs.data.repository.GameReviewRepository;
import com.sw.hs.service.GameApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameApiServiceImpl implements GameApiService {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameReviewRepository gameReviewRepository;

    @Value("${uploadPath}")
    private String UPLOAD_LOCATION; // C:/Images

    @Override
    public Game getOneDetail(long id){
        Optional<Game> ob = gameRepository.findById(id);
        if(ob.isPresent())
            return ob.get();
        else
            return null;
    }

    @Override
    public GameResponseDto getGame(Long id){
        Game game = getOneDetail(id);

        GameResponseDto dto = new GameResponseDto();
        dto.setId(game.getId());
        dto.setTitle(game.getTitle());
        dto.setDeveloper(game.getDeveloper());
        dto.setGenre(game.getGenre());
        dto.setReleasedate(game.getReleasedate());
        dto.setScore(game.getScore());
        dto.setScorecount(game.getScorecount());
        dto.setSum(game.getSum());
        dto.setSummary(game.getSummary());
        dto.setImage(game.getImage());

        return dto;
    }

    @Override
    public List<Game> getAll(){
        for(Game a : gameRepository.findAll()){
            System.out.println("---------------"+a+"---------------");
        }

        return gameRepository.findAll();
    }


    @Override
    public List<GameReview> getReview(Long id){
        Game game = getOneDetail(id);

        return game.getGameReview();
    }

}
