package com.sw.hs.controller;

import com.sw.hs.data.entity.Game;
import com.sw.hs.data.entity.User;
import com.sw.hs.service.impl.GameServiceImpl;
import com.sw.hs.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/html/user")   // http://localhost:8080/html/user
public class Html_UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    GameServiceImpl gameService;

    @GetMapping("/userForm")
    public String userForm(){ return "userForm"; }   // userForm.html

    // 회원가입
    @PostMapping("/userSave")
    public String userSave(User user, Model model){
        userService.userSave(user);

        List<Game> games = gameService.getAll();
        Random random = new Random();
        long id = random.nextLong(games.size())+1;
        Game game = gameService.getOneDetail(id);
        model.addAttribute("game", game);

        return "index";
    }

    // 로그인 폼
    @GetMapping("/loginForm")
    public String loginForm(){ return "loginForm"; }

    // 로그인 체크
    @PostMapping("/loginCheck")
    public String loginCheck(User user, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();   // session 객체 얻기, 모든 사용자는 한개의 session 객체를 자동으로 갖고 있음
        if(userService.loginCheck(user)){
            session.setAttribute("loginTag", "YES");
            session.setAttribute("loginId", user.getEmail());
        } else{
            session.setAttribute("loginTag", "NO");
        }

        List<Game> games = gameService.getAll();
        Random random = new Random();
        long id = random.nextLong(games.size())+1;
        Game game = gameService.getOneDetail(id);
        model.addAttribute("game", game);

        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model){
        request.getSession().setAttribute("loginTag", "NO");

        List<Game> games = gameService.getAll();
        Random random = new Random();
        long id = random.nextLong(games.size())+1;
        Game game = gameService.getOneDetail(id);
        model.addAttribute("game", game);

        return "index";
    }

}
