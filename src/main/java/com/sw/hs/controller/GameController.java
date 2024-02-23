package com.sw.hs.controller;

import com.sw.hs.data.dto.GameDto;
import com.sw.hs.data.dto.GameReviewDto;
import com.sw.hs.data.entity.Game;
import com.sw.hs.data.repository.GameRepository;
import com.sw.hs.data.repository.GameReviewRepository;
import com.sw.hs.service.GameService;
import com.sw.hs.service.impl.GameServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/html/game")
@Slf4j
@MultipartConfig(maxFileSize = 1024*1024*2, maxRequestSize = 1024 * 1024 * 10)
public class GameController {
    @Autowired
    GameService gameService;

    @Autowired
    GameRepository gameRepository;
    @Autowired
    GameReviewRepository gameReviewRepository;

    @GetMapping("/gameForm")
    public String gameForm(){ return "gameForm"; }

    @PostMapping("/gameRegister")
    public String gameRegister(GameDto dto, @RequestParam("fileOne") MultipartFile fileOne, Model model) throws ServletException, IOException{
        gameService.joinOneImage(dto,fileOne);

        List<Game> games = gameService.getAll();
        Random random = new Random();
        long id = random.nextLong(games.size())+1;
        Game game = gameService.getOneDetail(id);
        model.addAttribute("game", game);

        return "index";
    }

    @GetMapping("/getAll")
    public String gameList(Model model){
        List<Game> games = gameService.getAll();
        model.addAttribute("games", games);

        return "gameList";
    }

    @GetMapping("/getOneDetail")
    public String getOneDetail(String id, Model model){
        Game game = gameService.getOneDetail(Long.parseLong(id));
        model.addAttribute("game", game);
        model.addAttribute("reviews", game.getGameReview());

        return "gameDetail";
    }

    @GetMapping("/addReview")
    public String addReview(String id, Model model){
        Game game = gameService.getOneDetail(Long.parseLong(id));
        model.addAttribute("game", game);

        return "gameAddReview";
    }

    @PostMapping("/gameReviewRegister")
    public String gameReviewRegister(GameReviewDto dto, @RequestParam("game_id") String game_id, Model model){

        Game game = gameService.getOneDetail(Long.parseLong(game_id));
        gameService.addReview(dto,game);

        List<Game> games = gameService.getAll();
        Random random = new Random();
        long id = random.nextLong(games.size())+1;
        game = gameService.getOneDetail(id);
        model.addAttribute("game", game);

        return "index";
    }

}
