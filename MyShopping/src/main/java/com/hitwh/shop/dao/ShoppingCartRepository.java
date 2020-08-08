package com.hitwh.shop.dao;

import com.hitwh.shop.model.entity.Sku;
import com.hitwh.shop.model.entity.UserSku;
import com.hitwh.shop.model.pojo.ShoppingCartSkuResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<UserSku, Integer> {
    UserSku findUserSkuBySkuIdAndUserId(Integer skuId, Integer userId);

    @Query(value = "select new com.hitwh.shop.model.pojo.ShoppingCartSkuResult(sk.spuId, sp.name, " +
            "us.number,sk.price,sk.id)"+
        "from UserSku us,Sku sk ,Spu sp " +
            "where us.userId = :userId and us.skuId = sk.id and sk.spuId = sp.id and us.deleted = 0")
    List<ShoppingCartSkuResult> getShoppingCartByUserId(Integer userId);
}
