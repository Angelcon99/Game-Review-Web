package com.sw.hs.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name="game_review")
public class GameReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String user_name;
    private Float score;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "game_id")   // 외래키 이름
    private Game game;
}
