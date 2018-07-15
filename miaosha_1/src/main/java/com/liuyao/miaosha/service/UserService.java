package com.liuyao.miaosha.service;

import com.liuyao.miaosha.dao.UserDao;
import com.liuyao.miaosha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created By liuyao on 2018/07/15 9:33.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

    //    @Transactional
    public boolean tx() {
        User user1 = new User();
        user1.setId(2);
        user1.setName("bbb");
        userDao.insert(user1);

        User user2 = new User();
        user2.setId(1);
        user2.setName("ccc");
        userDao.insert(user2);
        return true;
    }
}
