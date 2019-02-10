package com.numsource.artproject.service;

public interface RpcService {
    public Object createUser(String password) throws Throwable;//创建账户
    public Object uploadProduct(String password,String productName,Integer price,String ownerAddress) throws Throwable;
    public Object changeProductStatus(String ownerAddress,String productId, String password, boolean bool) throws Throwable;
    public Object transferProduct(String productId,String buyerAddress,Integer priceNow,String ownerAddress) throws Throwable;
    public Object bidProduct(String productId,Integer bidPrice,String ownerAddress,String password) throws Throwable;
    public Object getAllProductCodeList() throws Throwable;
    public Object getAllProductInfo(String productId) throws Throwable;

}
