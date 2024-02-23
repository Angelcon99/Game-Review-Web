package com.sw.hs.service;

import com.sw.hs.data.dto.GameDto;
import com.sw.hs.data.dto.GameReviewDto;
import com.sw.hs.data.entity.Game;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GameService {
    public String joinOneImage(GameDto dto, MultipartFile fileOne);

    public String fileOneSave(MultipartFile file);

    public List<Game> getAll();

    public String addReview(GameReviewDto dto, Game game);

    public Game getOneDetail(long id);
}
