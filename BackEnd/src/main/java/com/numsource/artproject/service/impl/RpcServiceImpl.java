package com.numsource.artproject.service.impl;

import com.numsource.artproject.service.RpcService;
import com.numsource.artproject.util.RpcRequestUtil;
import org.springframework.stereotype.Service;

@Service
public class RpcServiceImpl implements RpcService {


    @Override
    public Object createUser(String password) throws Throwable {
        String address = RpcRequestUtil.newAccount(password).toString();
        RpcRequestUtil.unlockAccount(RpcRequestUtil.mainAddress,RpcRequestUtil.mainPassword);
        RpcRequestUtil.sendTransaction(RpcRequestUtil.mainAddress,address,"0xff");//TODO 主账户的设置
        return address;
    }

    //返回商品唯一标识的code,后台自动自动将数据库的图片和code进行绑定
    @Override
    public Object uploadProduct(String password,String productName, Integer price, String ownerAddress) throws Throwable {
        RpcRequestUtil.unlockAccount(ownerAddress,password);
        RpcRequestUtil.setCommodityCode(productName,price,ownerAddress);
        String productId = RpcRequestUtil.getCommodityCode(productName).toString();
        RpcRequestUtil.setCommodityOwner_Holder_Name(productId,productName,ownerAddress);
        RpcRequestUtil.setCommodityDate_Price(productId,price,ownerAddress);
        RpcRequestUtil.setCommodityElsePara(productId,ownerAddress);
        RpcRequestUtil.setCommodityAgents(productId,ownerAddress);
        RpcRequestUtil.setCommodityTId(productId,ownerAddress);
        return productId;
    }

    //true（关闭拍卖）或false（打开拍卖）
    @Override
    public Object changeProductStatus(String ownerAddress,String productId, String password, boolean bool) throws Throwable {
        RpcRequestUtil.unlockAccount(ownerAddress,password);
        RpcRequestUtil.changeCommodityState(productId,bool,ownerAddress);
        return null;
    }

    //转售操作
    @Override
    public Object transferProduct(String productId, String buyerAddress, Integer priceNow,String ownerAddress) throws Throwable {
        RpcRequestUtil.transfer1(productId,buyerAddress,priceNow,ownerAddress);
        RpcRequestUtil.transfer2(productId,buyerAddress,priceNow,ownerAddress);
        RpcRequestUtil.transfer3(productId,buyerAddress,priceNow,ownerAddress);
        return null;
    }

    //参与竞标的操作
    @Override
    public Object bidProduct(String productId,Integer bidPrice, String ownerAddress,String password) throws Throwable {
        changeProductStatus(ownerAddress,productId,password,false);
        RpcRequestUtil.auction(productId,bidPrice,ownerAddress);
        changeProductStatus(ownerAddress,productId,password,true);
        RpcRequestUtil.auctionEnd1(productId,ownerAddress);
        RpcRequestUtil.auctionEnd2(productId,ownerAddress);
        return null;
    }

    @Override
    public Object getAllProductCodeList() throws Throwable {
        Object codeList = RpcRequestUtil.getCommodityCodeList();
        return codeList;
    }

    @Override
    public Object getAllProductInfo(String productId) throws Throwable {
        Object info = RpcRequestUtil.queryCommodity(productId);
        return info;
    }
}
