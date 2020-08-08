package com.hitwh.shop.service.impl;

import com.hitwh.shop.dao.*;
import com.hitwh.shop.model.entity.*;
import com.hitwh.shop.model.pojo.*;
import com.hitwh.shop.service.SellerService;
import com.hitwh.shop.util.ConstUtils;
import com.hitwh.shop.util.SensitiveWordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SellerServiceImpl
 * @Description TODO
 * @Author 于劲鹏
 * @Date 2020/6/1 17:23
 * @Version 1.0
 **/
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SPURepository spuRepository;

    @Autowired
    private SKURepository skuRepository;

    @Autowired
    private SpuTypeRepository spuTypeRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private AttributeTypeRepository attributeTypeRepository;

    @Autowired
    private SKUAttributeRepository skuAttributeRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void getCommodityItemList(BaseResult<Object> result) {
        List<String> commodityItemList = new ArrayList<String>();
        commodityItemList.add("商品列表");
        commodityItemList.add("添加商品");
        result.construct(true, ConstUtils.MSG_QUERY_SUCCESS, commodityItemList);
    }

    @Override
    public void getOrderItemList(BaseResult<Object> result) {
        List<String> orderItemList = new ArrayList<String>();
        orderItemList.add("订单列表");
        orderItemList.add("退货申请处理");
        result.construct(true, ConstUtils.MSG_QUERY_SUCCESS, orderItemList);
    }

    @Override
    public void getStoreSPUSales(HttpSession session, BaseResult<List<SpuSalesResult>> result) {
        Integer userId = (Integer)session.getAttribute(ConstUtils.SESSION_USER_ID);
        List<SpuSalesResult> data = userRepository.getSpuSalesByUserId(userId);
        result.construct(true, ConstUtils.MSG_SUCCESS, data);
    }

    @Override
    public void getAllSpus(HttpSession session, String query, BasePageResult<Object> result, Pageable pageable) {
        Integer userId = (Integer)session.getAttribute(ConstUtils.SESSION_USER_ID);
        if (query == null || query.length() <= 0) {
            Page<Spu> spus = spuRepository.findSpusByUserId(pageable, userId);
            result.construct(true, ConstUtils.MSG_SUCCESS, null, spus);
        } else {
            Pageable newPageable = PageRequest.of(0, pageable.getPageSize());
            Page<Spu> spus = spuRepository.findSpusByNameByUserId(newPageable, query, userId);
            result.construct(true, ConstUtils.MSG_SUCCESS, null, spus);
        }
    }

    @Transactional
    @Override
    public void deleteSpuById(HttpSession session, Integer spuId, BaseResult<Object> result) {
        // Integer userId = (Integer)session.getAttribute(ConstUtils.SESSION_USER_ID);
        List<Sku> skuList = skuRepository.findSkusBySpuId(spuId);
        spuRepository.deleteSpuById(spuId);
        for( Sku s : skuList ) {
            skuRepository.deleteSkuById(s.getId());
        }
        result.construct(true, ConstUtils.MSG_SUCCESS, null);
    }

    @Override
    public void getCategories(BaseResult<Object> result) {
        List<SpuType> spuTypeList = spuTypeRepository.findSpuTypesByDeleted(0);
        result.construct(true, ConstUtils.MSG_SUCCESS, spuTypeList);
    }

    @Override
    @Transactional
    public void addGoods(HttpSession session, BaseResult<Object> result, AddSpuResult addSpuResult) {
        // 先检查敏感词
        String judgeMessage = addSpuResult.getGoodsName() + addSpuResult.getGoodsIntroduce();
        if (SensitiveWordUtil.contains(judgeMessage)) {
            result.construct(false, ConstUtils.MSG_CONTATINS_SENSITIVE + SensitiveWordUtil.getSensitiveWord(judgeMessage), null);
            return;
        }
        Integer userId = (Integer)session.getAttribute(ConstUtils.SESSION_USER_ID);
        Spu spu = new Spu(addSpuResult.getGoodsCat(),storeRepository.findStoreByUserId(userId).getId(),addSpuResult.getGoodsName(),addSpuResult.getPicture(),addSpuResult.getGoodsIntroduce());
        spu = spuRepository.saveAndFlush(spu);
        AttributeType attributeType = new AttributeType(spu.getId(),addSpuResult.getAttrName());
        attributeType = attributeTypeRepository.saveAndFlush(attributeType);
        for( String s : addSpuResult.getAttrValues()) {
            Attribute attribute = new Attribute(attributeType.getId(), s);
            attribute = attributeRepository.saveAndFlush(attribute);
            BigDecimal bigDecimal = new BigDecimal(addSpuResult.getGoodsPrice());
            Sku sku = new Sku(spu.getId(), bigDecimal, addSpuResult.getGoodsNumber());
            sku = skuRepository.saveAndFlush(sku);
            SkuAttribute skuAttribute = new SkuAttribute(sku.getId(), attribute.getId());
            skuAttribute = skuAttributeRepository.saveAndFlush(skuAttribute);
        }
        result.construct(true, ConstUtils.MSG_ADD_SUCCESS, null);
    }

    @Override
    public void getAllOrders(HttpSession session, String query, BasePageResult<Object> result, Pageable pageable) {
        Integer userId = (Integer)session.getAttribute(ConstUtils.SESSION_USER_ID);
        if (query == null || query.length() <= 0) {
            Page<SellerOrderResult> sellerOrderResults = orderRepository.findSellerOrderByUserId(pageable, userId);
            result.construct(true, ConstUtils.MSG_SUCCESS, null, sellerOrderResults);
        } else {
            Pageable newPageable = PageRequest.of(0, pageable.getPageSize());
            Integer state = 0;
            if (query.equals("未发货")) {
                state = 0;
            } else if (query.equals("已发货")) {
                state = 1;
            }
            Page<SellerOrderResult> sellerOrderResults = orderRepository.findSellerOrderByStateByUserId(newPageable, state, userId);
            result.construct(true, ConstUtils.MSG_SUCCESS, null, sellerOrderResults);
        }
    }

    @Override
    public void getSpuBySpuId(HttpSession session, Integer spuId, BaseResult<Object> result) {
        Integer userId = (Integer)session.getAttribute(ConstUtils.SESSION_USER_ID);
        Spu spu = spuRepository.findSpuById(spuId);
        result.construct(true, ConstUtils.MSG_GET_SUCCESS, spu);
    }

    @Transactional
    @Modifying
    @Override
    public void editSpuInfo(Integer spuId, String spuName, String spuImg, String spuIntroduce, BaseResult<Object> result) {
        spuRepository.updateSpuById(spuId, spuName, spuImg, spuIntroduce);
        result.construct(true, ConstUtils.MSG_UPDATE_SUCCESS, null);
    }

    @Override
    public void getOrderByOrderId(Integer orderId, BaseResult<Object> result) {
        Order order = orderRepository.findOrderById(orderId);
        result.construct(true, ConstUtils.MSG_GET_SUCCESS, order);
    }

    @Override
    public void getOrderInfoByOrderId(Integer orderId, BaseResult<Object> result) {
        OrderInfoResult orderInfoResult = orderRepository.findOrderInfoById(orderId);
        result.construct(true, ConstUtils.MSG_GET_SUCCESS, orderInfoResult);
    }

    @Transactional
    @Modifying
    @Override
    public void sendGoodByOrderId(Integer orderId, BaseResult<Object> result) {
        orderRepository.deleteOrderById(orderId);
        result.construct(true, ConstUtils.MSG_DELETE_SUCCESS, null);
    }

    @Override
    public void getAddressByOrderId(Integer orderId, BaseResult<Object> result) {
        Address address = addressRepository.findAddressByOrderId(orderId);
        result.construct(true, ConstUtils.MSG_GET_SUCCESS, address);
    }
}
