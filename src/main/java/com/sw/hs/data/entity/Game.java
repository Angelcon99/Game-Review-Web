package com.sw.hs.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String developer;
    private String genre;
    private String releasedate;
    private Float score;
    private Integer scorecount;
    private Float sum;
    private String summary;
    private String image;   // 이미지 파일 이름

    @OneToMany(mappedBy = "game")   // 양방향 관계
    private List<GameReview> gameReview = new ArrayList<>();

//    @OneToMany
//    @JoinColumn(name = "gameReview_id")   // 외래키 이름
//    private GameReview gameReview;
}
