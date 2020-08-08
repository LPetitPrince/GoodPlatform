package com.hitwh.shop.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ClassName Address
 * @Description TODO
 * @Author 孙一恒
 * @Date 2020/6/8 16:51
 * @Version 1.0
 **/
@Entity
public class Address {
    private Integer id;
    private Integer userId;
    private String provinceId;
    private String cityId;
    private String countryId;
    private String detail;
    private String phone;
    private Integer state;
    private Integer deleted;

    public Address() {
    }

    public Address(Integer userId, String provinceId, String cityId, String countryId, String detail, String phone, Integer state, Integer deleted) {
        this.userId = userId;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.countryId = countryId;
        this.detail = detail;
        this.phone = phone;
        this.state = state;
        this.deleted = deleted;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "province_id", nullable = true, length = 255)
    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "city_id", nullable = true, length = 255)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "country_id", nullable = true, length = 255)
    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "detail", nullable = false, length = 512)
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 255)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "state", nullable = false)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "deleted", nullable = false)
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(userId, address.userId) &&
                Objects.equals(provinceId, address.provinceId) &&
                Objects.equals(cityId, address.cityId) &&
                Objects.equals(countryId, address.countryId) &&
                Objects.equals(detail, address.detail) &&
                Objects.equals(state, address.state) &&
                Objects.equals(deleted, address.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, provinceId, cityId, countryId, detail, state, deleted);
    }
}
