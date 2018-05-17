package com.edu.dao;

import com.edu.model.Card;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ICardDao {
    
    public abstract int create(Card card) throws Exception; // 增加一条记录
    public abstract int remove(Card card) throws Exception; // 删除一条记录
    public abstract Card find(Card card) throws Exception; // 查找一条记录
    public abstract int update(Card card) throws Exception; // 更新一条记录
    public abstract List<Card> findAll(HttpSession session) throws Exception; // 列出全部记录
}
