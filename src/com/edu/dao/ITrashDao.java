package com.edu.dao;

import com.edu.model.Card;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ITrashDao {

    public abstract int delete(Card card); // 删除一条记录
    public abstract List<Card> findAll(HttpSession session);// 列出全部记录
    public abstract int recover(Card card);  // 回收站恢复一条记录
}
