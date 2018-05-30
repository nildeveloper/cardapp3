package com.edu.dao;

import com.edu.model.Card;
import com.edu.model.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ICardDao {
    
    int create(Card card) throws Exception; // 增加一条记录
    int remove(Card card) throws Exception; // 删除一条记录
    Card find(Card card) throws Exception; // 查找一条记录
    int update(Card card) throws Exception; // 更新一条记录
    List<Card> findAll(HttpSession session) throws Exception; // 列出全部记录
    List<Card> selectCard(Card card, HttpSession session);
}
