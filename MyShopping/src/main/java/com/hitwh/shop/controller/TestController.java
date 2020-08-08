package com.hitwh.shop.controller;

import com.hitwh.shop.dao.SPURepository;
import com.hitwh.shop.dao.UserRepository;
import com.hitwh.shop.model.entity.Spu;
import com.hitwh.shop.model.entity.User;
import com.hitwh.shop.model.pojo.BasePageResult;
import com.hitwh.shop.model.pojo.BaseResult;
import com.hitwh.shop.model.pojo.TestObject;
import com.hitwh.shop.util.ConstUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestController
 * @Description 测试接口
 * @Author 孙一恒
 * @Date 2020/6/1 17:16
 * @Version 1.0
 **/
@RestController
@RequestMapping(ConstUtils.URL_TEST)
public class TestController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SPURepository spuRepository;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public BaseResult<List<User>> getAllUsers() {
        List<User> users = userRepository.findUsersByDeleted(0);
        BaseResult<List<User>> result = new BaseResult<>(true, "测试成功", users);
        return result;
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public BaseResult<User> saveUserTest() {
        User u = userRepository.save(new User("孙一恒", "12345", "12345@email.com", null, new Timestamp(System.currentTimeMillis()), 0, 0));
        BaseResult<User> result = new BaseResult<>(true, "测试成功", u);
        return result;
    }

    @RequestMapping(value = "/testMethod", method = RequestMethod.POST)
    public BaseResult<Object> test(@RequestParam("hi")String hi, @RequestBody TestObject testObject) {
        BaseResult<Object> result = new BaseResult<>(true, "测试成功", null);
        System.out.println(hi);
        System.out.println(testObject);
        return result;
    }

    @RequestMapping(value = "/pageTest", method = RequestMethod.POST)
    public BasePageResult<Object> pageQueryTest(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize")int pageSize) {
        BasePageResult<Object> result = new BasePageResult<>();
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Spu> queryResult = spuRepository.findAllSpuByPage(pageable);
        result.construct(true, "查询成功", null, queryResult);
        return result;
    }

    @RequestMapping(value = "/getCommodityItemList", method = RequestMethod.GET)
    public BaseResult<Object> getCommodityItemList() {
        BaseResult<Object> result = new BaseResult<>();
        List<String> commodityItemList = new ArrayList<String>();
        commodityItemList.add("商品列表");
        commodityItemList.add("添加商品");
        result.construct(true, "查询成功", commodityItemList);
        return result;
    }

    @RequestMapping(value = "/getOrderItemList", method = RequestMethod.GET)
    public BaseResult<Object> getOrderItemList() {
        BaseResult<Object> result = new BaseResult<>();
        List<String> commodityItemList = new ArrayList<String>();
        commodityItemList.add("订单列表");
        commodityItemList.add("退货申请处理");
        result.construct(true, "查询成功", commodityItemList);
        return result;
    }

    //查看用户列表
    @RequestMapping(value = "/getUserList",method = RequestMethod.GET)
    public BasePageResult<User> getUserList(){
        BasePageResult<User> result = new BasePageResult<>();
        List<User> UserList = userRepository.findUsersByDeleted(0);
        if (!UserList.isEmpty()) {
            result.setContent(UserList);
//            result.setPageSize(UserList.size());
//            System.out.println(UserList.get(0).getRegisterDate().getClass().toString());
            result.construct(true, ConstUtils.MSG_QUERY_SUCCESS, null);
        } else {
            result.construct(true, ConstUtils.MSG_SEARCH_SPU_NULL, null);
        }
        return result;
    }

    //删除用户
    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    public BaseResult<Object> deleteUser(@RequestParam("deleteUserID") Integer deleteUserID){
        BaseResult <Object> result= new BaseResult<>();
        userRepository.deleteById(deleteUserID);
        result.construct(true, ConstUtils.MSG_QUERY_SUCCESS, null);
        return result;
    }

    @RequestMapping(value = "/showUser",method = RequestMethod.GET)
    public BaseResult<User> showUser(@RequestParam("userId") Integer userId){
        BaseResult<User> result = new BasePageResult<>();
        User currentUser = userRepository.findUserById(userId);
        if(currentUser == null){
            result.construct(false, ConstUtils.MSG_SPU_NOT_EXIST, null);
        }else{
            result.construct(true,ConstUtils.MSG_QUERY_SUCCESS,currentUser);
        }
        return result;
    }

}
