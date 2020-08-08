package com.hitwh.shop.model.pojo;

import java.math.BigDecimal;

/**
 * @ClassName OrderTempResult
 * @Description TODO
 * @Author 孙一恒
 * @Date 2020/6/10 23:42
 * @Version 1.0
 **/
public class OrderTempResult {
    private Integer orderId;
    private Integer number;
    private BigDecimal price;

    public OrderTempResult(Integer orderId, Integer number, BigDecimal price) {
        this.orderId = orderId;
        this.number = number;
        this.price = price;
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
