package com.numsource.artproject.service.impl;

import com.numsource.artproject.DTO.PictureForm;
import com.numsource.artproject.DTO.PictureInfo;
import com.numsource.artproject.dao.ProductRepository;
import com.numsource.artproject.entity.Product;
import com.numsource.artproject.entity.User;
import com.numsource.artproject.enums.ResultEnum;
import com.numsource.artproject.service.ProductService;
import com.numsource.artproject.service.RpcService;
import com.numsource.artproject.util.MyUtil;
import com.numsource.artproject.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RpcService rpcService;
    @Override
    public Object uploadProduct(PictureForm pictureForm, User user) {
        String picturePath = MyUtil.uploadPath + pictureForm.getFile().getOriginalFilename()+MyUtil.getRandomStr();//指定上传的路径
        try {
            byte[] bytes = pictureForm.getFile().getBytes();
            Path path = Paths.get(picturePath);
            Files.write(path, bytes);
        } catch (IOException e) {
            log.error("上传文件失败");
            e.printStackTrace();
        }
        log.info("获取当前请求用户的信息:{}",user.toString());
        Product product = new Product();
        try {
            //上传画作
            String productId = (String) rpcService.uploadProduct(pictureForm.getPassword(),pictureForm.getName(),pictureForm.getPrice(),user.getAddress());
            //得到区块链返回的商品的唯一的code标识后与图片存储的路径,用户的Id进行绑定,存入数据库
            product.setProductId(productId);
            product.setAuthor(pictureForm.getAuthor());
            product.setInfo(pictureForm.getInfo());
            product.setImagePath(picturePath);
            product.setHot(0);
            product.setUserId(user.getUserId());
            productRepository.save(product);
        } catch (Throwable throwable) {
            log.error(throwable.getMessage());
            return ResultVOUtil.error(ResultEnum.PASSWORD_WRONG);
        }
        return ResultVOUtil.success(product);
    }

    @Override
    public PictureInfo getProductInfo() {
        return null;
    }

    @Override
    public List<PictureInfo> allProductsInfo() {
        return null;
    }

    @Override
    public Object transferProduct(String productId, User buyerUser, Integer priceNow, User ownerUser) {
        try {
            rpcService.transferProduct(productId,buyerUser.getAddress(),priceNow,ownerUser.getAddress());
        } catch (Throwable throwable) {
            log.info("商品交易失败");
            throwable.printStackTrace();
            ResultVOUtil.error(ResultEnum.SYSTEM_ERROR);
        }
        Product product = productRepository.findByProductId(productId);
        product.setUserId(buyerUser.getUserId());
        return ResultVOUtil.success(product);
    }

    @Override
    public Object closeSellStatus(String productId,User user,String password) {
        try {
            rpcService.changeProductStatus(user.getAddress(),productId,password,true);
        } catch (Throwable throwable) {
            log.info("关闭售卖状态失败");
            throwable.printStackTrace();
            return ResultVOUtil.error(ResultEnum.PASSWORD_WRONG);
        }
        return ResultVOUtil.success();
    }

    @Override
    public Object openSellStatus(String productId, User user, String password) {
        try {
            rpcService.changeProductStatus(user.getAddress(),productId,password,false);
        } catch (Throwable throwable) {
            log.info("打开售卖状态失败");
            throwable.printStackTrace();
            return ResultVOUtil.error(ResultEnum.PASSWORD_WRONG);
        }
        return ResultVOUtil.success();
    }

    //竞价出售
    @Override
    public PictureInfo bidProduct(String productId,User user,String password,Integer bidPrice) {
        try {
            rpcService.bidProduct(productId,bidPrice,user.getAddress(),password);
        } catch (Throwable throwable) {
            log.error("商品参与竞价拍卖操作失败");
            throwable.printStackTrace();
        }
        return null;
    }
}
