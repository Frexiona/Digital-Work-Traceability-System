package com.numsource.artproject.util.dataUtil;

import lombok.Data;

@Data
public class ServerInformation {


    private String ip;
    private String userName;
    private String userPwd;



    public ServerInformation() {
    }
}