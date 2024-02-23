package com.sw.hs.data.dto;

import com.sw.hs.data.entity.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameReviewDto {
    private Long id;
    private String user_name;
    private String score;
    private String comment;
//    private Game game;
}
