package com.numsource.artproject.util;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.numsource.artproject.DTO.jsonData.JsonParam;
import com.numsource.artproject.DTO.jsonData.JsonParams;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;

/**
 * 对接区块链方法请求的工具类
 */
@Slf4j
public class RpcRequestUtil {
    public static String url = "http://10.20.0.62:8180";
    public static String contentType = "application/json";
    public static String mainAddress = "0x05a016fe7c89c55d35b372811f8c28aebad783e2";
    public static String mainPassword = "123456";

    public static String doRequest(String methodName,Object[] params) throws Throwable {

        JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(url));
        client.setContentType(contentType);
        Object address = client.invoke(methodName, params, Object.class);
        if(address==null){
            return null;
        }
        log.info("请求Rpc接口成功,返回address:{}", address.toString());
        return address.toString();
    }


//    public static void main(String[] args) {
//        JsonParams jsonParams = new JsonParams();
//        jsonParams.setFrom("0xbf3617cf0ebe38fad388ef102911e5cf2ad05aab");
//        jsonParams.setTo("0x2bec6b177e3052af78cfd0b314a6205495b28beb");
//        jsonParams.setValue("0xff");
//        Object[] objects = new Object[]{jsonParams};
////        JsonParams[] params = new JsonParams[]{jsonParams};
//        try {
//            JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(url));
//            client.setContentType(contentType);
//            Object address = client.invoke("eth_sendTransaction", objects, Object.class);
//            System.out.println(address);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//    }

    //5秒间断休眠的挖矿
    public static void miner_start() throws Throwable {
        Object[] params = new Object[]{1};
        doRequest("miner_start",params);
    }

    public static Object miner_stop() throws Throwable {
        Object[] params = new Object[]{1};
        return doRequest("miner_stop",params);
    }

    public static Object newAccount(String password) throws Throwable{
        Object[] params = new Object[]{password};
        String userAddress = RpcRequestUtil.doRequest("personal_newAccount",params);
        return userAddress;
    }

    public static Object unlockAccount(String address, String password) throws Throwable {
        Object[] params = new Object[]{address,password};
        return RpcRequestUtil.doRequest("personal_unlockAccount",params);
    }

    public static Object sendTransaction(String from, String to, String value) throws Throwable {
        JsonParams jsonParams = new JsonParams();
        jsonParams.setFrom(from);
        jsonParams.setTo(to);
        jsonParams.setValue(value);
        Object[] params = new Object[]{jsonParams};
        return doRequest("eth_sendTransaction",params);
    }

    public static Object setCommodityCode(String commodityName, Integer priceNow, String from) throws Throwable {
        JsonParam jsonParam = new JsonParam();
        jsonParam.setFrom(from);
        Object[] params = new Object[]{commodityName,priceNow,jsonParam};
        return doRequest("cinstraceability_setCommodityCode",params);
    }

    //上传画作后需要进行5秒的等待才能得到商品的ID
    public static Object getCommodityCode(String commodityName) throws Throwable {
        Object[] params = new Object[]{commodityName};
        return doRequest("cinstraceability_getCommodityCode",params);
    }

    public static Object setCommodityOwner_Holder_Name(String commodityCode, String commodityName, String from) throws Throwable {
        JsonParam jsonParam = new JsonParam();
        jsonParam.setFrom(from);
        Object[] params = new Object[]{commodityCode,commodityName,jsonParam};
        return doRequest("cinstraceability_setCommodityOwnerHolderName",params);
    }

    public static Object setCommodityDate_Price(String commodityCode, Integer priceNow, String from) throws Throwable {
        JsonParam jsonParam = new JsonParam();
        jsonParam.setFrom(from);
        Object[] params = new Object[]{commodityCode,priceNow,jsonParam};
        return doRequest("cinstraceability_setCommodityDatePrice",params);
    }

    public static Object setCommodityElsePara(String commodityCode, String from) throws Throwable {
        JsonParam jsonParam = new JsonParam();
        jsonParam.setFrom(from);
        Object[] params = new Object[]{commodityCode,jsonParam};
        return doRequest("cinstraceability_setCommodityElsePara",params);
    }

    public static Object setCommodityAgents(String commodityCode, String from) throws Throwable {
        JsonParam jsonParam = new JsonParam();
        jsonParam.setFrom(from);
        Object[] params = new Object[]{commodityCode,jsonParam};
        return doRequest("cinstraceability_setCommodityAgents",params);
    }

    public static Object setCommodityTId(String commodityCode, String from) throws Throwable {
        JsonParam jsonParam = new JsonParam();
        jsonParam.setFrom(from);
        Object[] params = new Object[]{commodityCode,jsonParam};
        return doRequest("cinstraceability_setCommodityTId",params);
    }

    //false表示可以售卖,true表示不可以售卖
    public static Object changeCommodityState(String commodityCode, boolean bool, String from) throws Throwable {
        JsonParam jsonParam = new JsonParam();
        jsonParam.setFrom(from);
        Object[] params = new Object[]{commodityCode,bool,jsonParam};
        return doRequest("cinstraceability_changeCommodityState",params);
    }

    public static Object transfer1(String commodityCode, String buyer, Integer priceNow, String from) throws Throwable {
        JsonParam jsonParam = new JsonParam();
        jsonParam.setFrom(from);
        Object[] params = new Object[]{commodityCode,buyer,priceNow,jsonParam};
        return doRequest("cinstraceability_transfer1",params);
    }

    public static Object transfer2(String commodityCode, String buyer, Integer priceNow, String from) throws Throwable {
        JsonParam jsonParam = new JsonParam();
        jsonParam.setFrom(from);
        Object[] params = new Object[]{commodityCode,buyer,priceNow,jsonParam};
        return doRequest("cinstraceability_transfer2",params);
    }

    public static Object transfer3(String commodityCode, String buyer, Integer priceNow, String from) throws Throwable {
        JsonParam jsonParam = new JsonParam();
        jsonParam.setFrom(from);
        Object[] params = new Object[]{commodityCode,buyer,priceNow,jsonParam};
        return doRequest("cinstraceability_transfer3",params);
    }

    public static Object auction(String commodityCode, Integer bidPrice, String from) throws Throwable {
        JsonParam jsonParam = new JsonParam();
        jsonParam.setFrom(from);
        Object[] params = new Object[]{commodityCode,bidPrice,jsonParam};
        return doRequest("cinstraceability_auction",params);
    }

    public static Object auctionEnd1(String commodityCode, String from) throws Throwable {
        JsonParam jsonParam = new JsonParam();
        jsonParam.setFrom(from);
        Object[] params = new Object[]{commodityCode,jsonParam};
        return doRequest("cinstraceability_auctionEnd1",params);
    }
    public static Object auctionEnd2(String commodityCode,String from) throws Throwable {
        JsonParam jsonParam = new JsonParam();
        jsonParam.setFrom(from);
        Object[] params = new Object[]{commodityCode,jsonParam};
        return doRequest("cinstraceability_auctionEnd2",params);
    }

    public static Object getCommodityCodeList() throws Throwable {
        return doRequest("cinstraceability_getCommodityCodeList",null);
    }

    public static Object queryCommodity(String commodityCode) throws Throwable {
        Object[] params = new Object[]{commodityCode};
        Object r1 = doRequest("cinstraceability_queryCommodity1",params);
        Object r2 = doRequest("cinstraceability_queryCommodity1",params);
        return null;
    }
}
