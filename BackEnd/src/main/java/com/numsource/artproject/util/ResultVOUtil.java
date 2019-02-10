package com.numsource.artproject.util;

import com.numsource.artproject.ViewObject.ResultVO;
import com.numsource.artproject.enums.ResultEnum;

public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }


    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO error(Integer code,String message){

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(message);

        return resultVO;
    }
    public static ResultVO error(ResultEnum resultEnum){

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMsg());

        return resultVO;
    }
}
