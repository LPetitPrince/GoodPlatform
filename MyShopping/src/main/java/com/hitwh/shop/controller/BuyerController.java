package com.hitwh.shop.controller;

import com.hitwh.shop.model.pojo.BaseResult;
import com.hitwh.shop.model.pojo.MaxOrderResult;
import com.hitwh.shop.model.pojo.SpuTypeExpenseResult;
import com.hitwh.shop.service.BuyerService;
import com.hitwh.shop.util.ConstUtils;
import com.hitwh.shop.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @ClassName BuyerController
 * @Description 买家的功能接口
 * @Author 孙一恒
 * @Date 2020/6/1 17:12
 * @Version 1.0
 **/
@RestController
@RequestMapping(ConstUtils.URL_BUYER)
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    //获取收藏夹的内容
    @RequestMapping(value = ConstUtils.URL_GET_COLLECTION, method = RequestMethod.GET)
    public BaseResult<Object> getCollection(@RequestParam("userId") Integer userId, HttpSession session) {
        BaseResult<Object> result = new BaseResult<>();
        buyerService.getCollection(result, userId, session);
        return result;
    }

    //添加收藏
    @RequestMapping(value = ConstUtils.URL_ADD_COLLECTION, method = RequestMethod.POST)
    public BaseResult<Object> addCollection(@RequestParam("userId") Integer userId, @RequestParam("spuId") Integer spuId, HttpSession session) {
        BaseResult<Object> result = new BaseResult<>();
        buyerService.addCollection(result, userId, spuId, session);
        return result;
    }

    //删除收藏
    @RequestMapping(value = ConstUtils.URL_DELETE_COLLECTION, method = RequestMethod.GET)
    public BaseResult<Object> deleteCollection(@RequestParam("userId") Integer userId, @RequestParam("spuId") Integer spuId, HttpSession session) {
        BaseResult<Object> result = new BaseResult<>();
        buyerService.deleteCollection(result, userId, spuId, session);
        return result;
    }
    
    @RequestMapping(value = "/getOrderByUserId",method = RequestMethod.GET)
    public BaseResult<Object> getOrderByUserId(HttpSession session){
        BaseResult<Object> result = new BaseResult<>();
        buyerService.getOrderByUserId(session, result);
        return result;
    }

    //添加购物车
    @RequestMapping(value = ConstUtils.URL_ADD_SHOPPING_CART, method = RequestMethod.POST)
    public BaseResult<Object> addShoppingCart(@RequestParam("userId") Integer userId, @RequestParam("skuId") Integer skuId, @RequestParam("number") Integer number, HttpSession session) {
        BaseResult<Object> result = new BaseResult<>();
        buyerService.addShoppingCart(result, userId, skuId, number, session);
        return result;
    }

    //添加订单
    @RequestMapping(value = "/addOrder",method = RequestMethod.GET)
    public BaseResult<Object> addOrder(@RequestParam("spuId") Integer spuId, @RequestParam("skuId") Integer skuId,
                                       @RequestParam("amount") Integer amount, @RequestParam("spuName") String spuName,
                                       @RequestParam("price") BigDecimal price, HttpSession session){
        BaseResult<Object> result = new BaseResult<>();
        buyerService.addOrder(result,spuId,skuId,amount,spuName,price,session);
        return result;
    }

    @RequestMapping(value = "/deleteOrder",method = RequestMethod.GET)
    public BaseResult<Object> deleteOrder(@RequestParam("orderId") Integer orderId,HttpSession session){
        BaseResult<Object> result = new BaseResult<>();
        buyerService.deleteOrder(result,orderId,session);
        return result;
    }

    //查询购物车
    @RequestMapping(value = "/getShoppingCart",method = RequestMethod.GET)
    public BaseResult<Object> getShoppingCart(HttpSession session)
    {
        BaseResult<Object> result = new BaseResult<>();
        buyerService.getShoppingCart(session, result);
        return result;
    }

    //删除购物车
    @RequestMapping(value = "/deleteShoppingCart",method = RequestMethod.GET)
    public BaseResult<Object> deleteShoppingCart(@RequestParam("skuId") Integer skuId, HttpSession session)
    {
        BaseResult<Object> result = new BaseResult<>();
        buyerService.deleteShoppingCart(skuId,session, result);
        return result;
    }

    /**
     * 获取当前用户当月的各类商品消费情况
     */
    @RequestMapping(value = ConstUtils.URL_GET_CURRENT_MONTH_EXPENSE, method = RequestMethod.GET)
    public BaseResult<List<SpuTypeExpenseResult>> getCurrentMonthExpense(HttpSession session) {
        BaseResult<List<SpuTypeExpenseResult>> result = new BaseResult<>();
        buyerService.getExpenseByTime(session, result, new Timestamp(TimeUtils.getCurrentMonthStart()), new Timestamp(TimeUtils.getCurrentMonthEnd()));
        return result;
    }

    /**
     * 获取当前用户当年的各类商品消费情况
     */
    @RequestMapping(value = ConstUtils.URL_GET_CURRENT_YEAR_EXPENSE, method = RequestMethod.GET)
    public BaseResult<List<SpuTypeExpenseResult>> getCurrentYearExpense(HttpSession session) {
        BaseResult<List<SpuTypeExpenseResult>> result = new BaseResult<>();
        buyerService.getExpenseByTime(session, result, new Timestamp(TimeUtils.getCurrentYearStart()), new Timestamp(TimeUtils.getCurrentYearEnd()));
        return result;
    }

    /**
     * 获取当前用户当月的最高支出订单
     */
    @RequestMapping(value = ConstUtils.URL_GET_CURRENT_MONTH_MAX_ORDER, method = RequestMethod.GET)
    public BaseResult<MaxOrderResult> getCurrentMonthMaxOrder(HttpSession session) {
        BaseResult<MaxOrderResult> result = new BaseResult<>();
        buyerService.getMaxOrderByTime(session, result, new Timestamp(TimeUtils.getCurrentMonthStart()), new Timestamp(TimeUtils.getCurrentMonthEnd()));
        return result;
    }

    /**
     * 获取当前用户当年的最高支出订单
     */
    @RequestMapping(value = ConstUtils.URL_GET_CURRENT_YEAR_MAX_ORDER, method = RequestMethod.GET)
    public BaseResult<MaxOrderResult> getCurrentYearMaxOrder(HttpSession session) {
        BaseResult<MaxOrderResult> result = new BaseResult<>();
        buyerService.getMaxOrderByTime(session, result, new Timestamp(TimeUtils.getCurrentYearStart()), new Timestamp(TimeUtils.getCurrentYearEnd()));
        return result;
    }

    //获取用户信息
    @RequestMapping(value = ConstUtils.URL_GETUSERINFOBYID, method = RequestMethod.GET)
    public BaseResult getUserInfoById(HttpSession session){
        BaseResult<Object> result = new BaseResult<>();
        buyerService.getUserInfoById(result,session);
        return result;
    }

    //获取用户所在省
    @RequestMapping(value = ConstUtils.URL_GETPROVINCE, method = RequestMethod.GET)
    public BaseResult getProvince(HttpSession session){
        BaseResult<Object> result = new BaseResult<>();
        buyerService.getProvince(result,session);
        return result;
    }

    //获取用户所在市
    @RequestMapping(value = ConstUtils.URL_GETCITY, method = RequestMethod.GET)
    public BaseResult getCity(@RequestParam("name") String name, HttpSession session){
        BaseResult<Object> result = new BaseResult<>();
        buyerService.getCity(result,name,session);
        return result;
    }

    //获取用户所在县
    @RequestMapping(value = ConstUtils.URL_GETCOUNTY, method = RequestMethod.GET)
    public BaseResult getCounty(@RequestParam("name") String name, HttpSession session){
        BaseResult<Object> result = new BaseResult<>();
        buyerService.getCounty(result,name,session);
        return result;
    }

    //保存用户个人信息
    @RequestMapping(value = ConstUtils.URL_SAVEUSERINFO, method = RequestMethod.GET)
    public BaseResult saveUserInfo(@RequestParam("headImg") String headImg, @RequestParam("province") String provinceName,
                                   @RequestParam("city") String cityName, @RequestParam("county") String countyName,
                                   @RequestParam("detail") String detail, @RequestParam("phone") String phone,
                                   @RequestParam("password") String password,HttpSession session){
        BaseResult<Object> result = new BaseResult<>();
        buyerService.saveUserInfo(result, headImg, provinceName, cityName, countyName, detail, phone, password, session);
        return result;
    }

    //查询用户权限
    @RequestMapping(value = ConstUtils.URL_GET_USERROLE, method = RequestMethod.GET)
    public BaseResult getUserRole(HttpSession session){
        BaseResult<Object> result = new BaseResult<>();
        buyerService.getUserRole(result, session);
        return result;
    }

    //创建店铺
    @RequestMapping(value = ConstUtils.URL_CREATE_STORE, method = RequestMethod.GET)
    public BaseResult createStore(@RequestParam("description") String description, HttpSession session){
        BaseResult<Object> result = new BaseResult<>();
        buyerService.createStore(description, session, result);
        return result;
    }

    //添加商品评论
    @RequestMapping(value = ConstUtils.URL_ADD_SPU_COMMENT, method = RequestMethod.POST)
    public BaseResult<Object> showSpuComment(@RequestParam("spuId") Integer spuId, @RequestParam("userId") Integer userId, @RequestParam("grade") Integer grade, @RequestParam("comment") String comment, HttpSession session){
        BaseResult <Object> result= new BaseResult<>();
        buyerService.addSpuComment(result,spuId,userId,grade,comment,session);
        return result;
    }
}
