package com.yuchen.service;

import com.yuchen.bean.User;
import com.yuchen.dao.UserDao;
import com.yuchen.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;

public class UserService {
    //获取sqlSession对象
    SqlSession sqlSession;
    //获取UserDao接口的对象
    UserDao userDao;
    User u;

    //登录
    public User login(String username, String password) {
        sqlSession= MybatisUtils.getSqlSession();
        userDao = sqlSession.getMapper(UserDao.class);
        u = userDao.login_info(username, password);

        sqlSession.close();

        return u;
    }

    //判断账号是否已存在
    public boolean checkUser(String username) {
        sqlSession= MybatisUtils.getSqlSession();
        userDao = sqlSession.getMapper(UserDao.class);
        u = userDao.select_Username(username);

        return u == null;
    }

    //注册
    public void register(User user) {
        sqlSession= MybatisUtils.getSqlSession();
        userDao = sqlSession.getMapper(UserDao.class);

        userDao.add_User(user);

        sqlSession.commit();

        sqlSession.close();
    }

    //获取用户个人信息
    public User findUserById(int userId) {
        sqlSession= MybatisUtils.getSqlSession();
        userDao = sqlSession.getMapper(UserDao.class);

        u = userDao.findUserById(userId);

        sqlSession.close();

        return u;
    }

    public void update_nickname (String nickname, int userId) {
        sqlSession= MybatisUtils.getSqlSession();
        userDao = sqlSession.getMapper(UserDao.class);

        userDao.update_nickname(nickname, userId);

        sqlSession.commit();
        sqlSession.close();
    }

    public void update_password(String password, int userId) {
        sqlSession= MybatisUtils.getSqlSession();
        userDao = sqlSession.getMapper(UserDao.class);

        userDao.update_password(password, userId);

        sqlSession.commit();
        sqlSession.close();
    }

    public void update_birthday(Date birthday, int userId) {
        sqlSession = MybatisUtils.getSqlSession();
        userDao = sqlSession.getMapper(UserDao.class);

        userDao.update_birthday(birthday, userId);
        sqlSession.commit();
        sqlSession.close();
    }


    public void update_sex(String sex, int userId) {
        sqlSession= MybatisUtils.getSqlSession();
        userDao = sqlSession.getMapper(UserDao.class);

        userDao.update_sex(sex, userId);

        sqlSession.commit();
        sqlSession.close();
    }
    public void update_phone(String phone, int userId) {
        sqlSession= MybatisUtils.getSqlSession();
        userDao = sqlSession.getMapper(UserDao.class);

        userDao.update_phone(phone, userId);

        sqlSession.commit();
        sqlSession.close();
    }
    public void update_email(String email, int userId) {
        sqlSession= MybatisUtils.getSqlSession();
        userDao = sqlSession.getMapper(UserDao.class);

        userDao.update_email(email, userId);

        sqlSession.commit();
        sqlSession.close();
    }

    public void update_signature(String signature, int userId) {
        sqlSession= MybatisUtils.getSqlSession();
        userDao = sqlSession.getMapper(UserDao.class);

        userDao.update_signature(signature, userId);

        sqlSession.commit();

        sqlSession.close();
    }

    public void update_avatar(String avatar, int userId) {
        sqlSession= MybatisUtils.getSqlSession();
        userDao = sqlSession.getMapper(UserDao.class);

        userDao.update_avatar(avatar, userId);

        sqlSession.commit();

        sqlSession.close();
    }

}
