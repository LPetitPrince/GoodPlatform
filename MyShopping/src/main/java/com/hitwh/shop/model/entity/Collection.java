package com.hitwh.shop.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName Collection
 * @Description TODO
 * @Author 孙一恒
 * @Date 2020/6/1 18:16
 * @Version 1.0
 **/
@Entity
public class Collection {
    private Integer id;
    private Integer userId;
    private Integer spuId;
    private Timestamp date;
    private Integer state;
    private Integer deleted;

    public Collection() {
    }

    public Collection(Integer id, Integer userId, Integer spuId, Timestamp date, Integer state, Integer deleted) {
        this.id = id;
        this.userId = userId;
        this.spuId = spuId;
        this.date = date;
        this.state = state;
        this.deleted = deleted;
    }

    public Collection(Integer userId, Integer spuId, Timestamp date, Integer state, Integer deleted) {
        this.userId = userId;
        this.spuId = spuId;
        this.date = date;
        this.state = state;
        this.deleted = deleted;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "spu_id")
    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "deleted")
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
        Collection that = (Collection) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(spuId, that.spuId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(state, that.state) &&
                Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, spuId, date, state, deleted);
    }
}
