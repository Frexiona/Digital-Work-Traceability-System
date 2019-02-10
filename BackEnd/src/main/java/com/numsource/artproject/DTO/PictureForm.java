package com.numsource.artproject.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class PictureForm {
    @NotNull(message = "文件上传不能为空")
    private MultipartFile file;
    @NotNull(message = "作品名称不能为空")
    private String name;
    @NotNull(message = "作者名不为空")
    private String author;
    @NotNull(message = "作品价格不能为空")
    private Integer price;
    @NotNull(message = "作品信息不能为空")
    private String info;
    @NotNull
    private String password;

}
