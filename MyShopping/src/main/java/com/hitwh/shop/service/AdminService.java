package com.hitwh.shop.service;

import com.hitwh.shop.model.entity.User;
import com.hitwh.shop.model.pojo.BasePageResult;
import com.hitwh.shop.model.pojo.BaseResult;
import com.hitwh.shop.model.pojo.CitySales;
import com.hitwh.shop.model.pojo.LoginResult;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AdminService {
//    public void getCommodityItemList(BaseResult<Object> result);

    public void login(BaseResult<LoginResult> result, String userName, String password, HttpSession session);

    public void getCollection(BaseResult result, Integer userId, HttpSession session);
    public void getUserList(BasePageResult<User> result);
    public void showUser(BaseResult<User> result,Integer userId);
    public void deleteCollection(BaseResult result, Integer SPUID,Integer userId, HttpSession session);
    public void deleteUser(BaseResult result, Integer deleteUserId,Integer userId, HttpSession session);

    public void getCitySales(BaseResult<List<CitySales>> result);
    public void deleteSPU(BaseResult<Object> result,Integer spuId, HttpSession session);
}
