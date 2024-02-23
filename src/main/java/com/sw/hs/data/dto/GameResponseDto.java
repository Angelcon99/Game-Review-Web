package com.sw.hs.data.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameResponseDto {
    private Long id;
    private String title;
    private String developer;
    private String genre;
    private String releasedate;
    private Float score;
    private Integer scorecount;
    private Float sum;
    private String summary;
    private String image;
}