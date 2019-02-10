package com.numsource.artproject.service;

import com.numsource.artproject.DTO.PictureForm;
import com.numsource.artproject.DTO.PictureInfo;
import com.numsource.artproject.entity.User;

import java.util.List;

public interface ProductService {
    public Object uploadProduct(PictureForm pictureForm, User user);//根据提交的表单上传画作返回pictureInfo
    public PictureInfo getProductInfo();
    public List<PictureInfo> allProductsInfo();
    public Object transferProduct(String productId,User buyerUser,Integer priceNow,User ownerUser);
    public Object closeSellStatus(String productId,User user,String password);
    public Object openSellStatus(String productId,User user,String password);
    public PictureInfo bidProduct(String productId,User user,String password,Integer bidPrice);//参与竞价拍卖
}
