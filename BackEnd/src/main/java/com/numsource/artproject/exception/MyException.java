package com.numsource.artproject.exception;

import com.numsource.artproject.enums.ResultEnum;
import lombok.Data;

@Data
public class MyException extends RuntimeException {
    private Integer code;
    public MyException(Integer code,String msg){
        super(msg);
        this.code = code;
    }
    public MyException(ResultEnum resultEnum) {

        super(resultEnum.getMsg());

        this.code = resultEnum.getCode();

    }

}
