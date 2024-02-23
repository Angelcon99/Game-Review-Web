package com.sw.hs.service.impl;

import com.sw.hs.data.dto.GameDto;
import com.sw.hs.data.dto.GameReviewDto;
import com.sw.hs.data.entity.Game;
import com.sw.hs.data.entity.GameReview;
import com.sw.hs.data.repository.GameRepository;
import com.sw.hs.data.repository.GameReviewRepository;
import com.sw.hs.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class GameServiceImpl implements GameService {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameReviewRepository gameReviewRepository;

    @Value("${uploadPath}")
    private String UPLOAD_LOCATION; // C:/Images

    @Override
    public String joinOneImage(GameDto dto, MultipartFile fileOne){
        Game game = new Game();
        game.setTitle(dto.getTitle());
        game.setDeveloper(dto.getDeveloper());
        game.setGenre(dto.getGenre());
        game.setReleasedate(dto.getReleasedate());
        game.setScore(0.0F);   // 게임 평점
        game.setScorecount(0);   // 게임 평점 수
        game.setSum(0.0F);   // 게임 평점 총합
        game.setSummary(dto.getSummary());


        String uuidFile = fileOneSave(fileOne);
        game.setImage("/images/"+ uuidFile);
        gameRepository.save(game); // game 객체 저장
//        System.out.println("============ 게임 저장 성공(GameService.java)===========");

        return "OK";
    }

    @Override
    public String fileOneSave(MultipartFile file) {
        String uuidFile = null;
        String fileName = file.getOriginalFilename();
        String fileExt = fileName.substring(fileName.lastIndexOf("."));
//        log.info("UPLOAD_LOCATION : {}", UPLOAD_LOCATION); // C:/Images
//        log.info("파일 이름 : {}", fileName); log.info("파일 확장자 : {}", fileExt); // .jpg 등
//        log.info("파일 크기 : {}", file.getSize());
        uuidFile = UUID.randomUUID().toString().replaceAll("-", "") + fileExt; // UUID => 중복 없는 새로운 이름 생성 ex)dcacbbcad6d84168a145b5caac334766
//        log.info("UUID 파일명 : {}", uuidFile);
        String uploadFile = UPLOAD_LOCATION + "/" + uuidFile; // C:/1912053/bookMgr_img/dcacbbcad6d84168a145b5caac334766.jpg
//        log.info("업로드 파일 : {}", uploadFile);
        try {
            if (file.isEmpty()) {
                throw new IOException("common.file.empty"); // 빈 파일입니다.
            }
            // 저장 작업
            InputStream src = file.getInputStream();
            Path dest = Paths.get(uploadFile);
//            log.info("src : {}", src);
//            log.info("dest : {}", dest);
            Files.copy(src,dest, StandardCopyOption.REPLACE_EXISTING ); // java.nio.file.* 필요
        } catch (IOException e) {
            throw new RuntimeException("fileOne Save Error"+e.getMessage());
        }

        return uuidFile;
    }

    @Override
    public List<Game> getAll(){
        return gameRepository.findAll();
    }

    @Override
    public Game getOneDetail(long id){
        Optional<Game> ob = gameRepository.findById(id);
        if(ob.isPresent())
            return ob.get();
        else
            return null;
    }

    @Override
    public String addReview(GameReviewDto dto, Game game){
        GameReview gr = new GameReview();
        gr.setUser_name(dto.getUser_name());
        gr.setScore(Float.valueOf(dto.getScore()));
        gr.setComment(dto.getComment());
        gr.setGame(game);   // 연관관계 설정

        game.setScorecount(game.getScorecount()+1);   // 해당 게임에 등록된 리뷰 수 추가
        game.setSum(Float.valueOf(game.getSum()+gr.getScore()));
        game.setScore((float) (game.getSum()/game.getScorecount()));

        gameReviewRepository.save(gr);

        return "OK";
    }

}
