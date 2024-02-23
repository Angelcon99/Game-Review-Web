package com.sw.hs.controller;


import com.sw.hs.data.entity.Game;
import com.sw.hs.service.impl.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/html")
public class MainController {
    @Autowired
    GameServiceImpl gameService;

    @GetMapping("")   // http://localhost:8080/html
    public String index(Model model){
        // 랜덤한 게임 1개를 메인 화면에 표시
        List<Game> games = gameService.getAll();
        Random random = new Random();
        long id = random.nextLong(games.size())+1;
        Game game = gameService.getOneDetail(id);
        model.addAttribute("game", game);

        return "index";
    }
}
