package com.numsource.artproject.DTO;

import lombok.Data;

import java.io.File;

@Data
public class PictureInfo {
    private String  picture;
    private String name;
    private String author;
    private Integer price;
    private String info;
    private Integer hot;
    private String owner;
    private String status;
}
