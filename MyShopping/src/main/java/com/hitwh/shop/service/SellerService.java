package com.hitwh.shop.service;

import com.hitwh.shop.model.pojo.AddSpuResult;
import com.hitwh.shop.model.pojo.BasePageResult;
import com.hitwh.shop.model.pojo.BaseResult;
import com.hitwh.shop.model.pojo.SpuSalesResult;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface SellerService {
    public void getCommodityItemList(BaseResult<Object> result);

    public void getOrderItemList(BaseResult<Object> result);

    public void getStoreSPUSales(HttpSession session, BaseResult<List<SpuSalesResult>> result);

    public void getAllSpus(HttpSession session, String query, BasePageResult<Object> result, Pageable pageable);

    public void deleteSpuById(HttpSession session, Integer spuId, BaseResult<Object> result);

    public void getCategories(BaseResult<Object> result);

    public void addGoods(HttpSession session, BaseResult<Object> result, AddSpuResult addSpuResult);

    public void getAllOrders(HttpSession session, String query, BasePageResult<Object> result, Pageable pageable);

    public void getSpuBySpuId(HttpSession session, Integer spuId, BaseResult<Object> result);

    public void editSpuInfo(Integer spuId, String spuName, String spuImg, String spuIntroduce, BaseResult<Object> result);

    public void getOrderByOrderId(Integer orderId, BaseResult<Object> result);

    public void getOrderInfoByOrderId(Integer orderId, BaseResult<Object> result);

    public void sendGoodByOrderId(Integer orderId, BaseResult<Object> result);

    public void getAddressByOrderId(Integer orderId, BaseResult<Object> result);
}
