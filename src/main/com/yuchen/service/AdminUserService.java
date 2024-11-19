package com.yuchen.service;

import com.yuchen.bean.AdminUser;
import com.yuchen.bean.User;
import com.yuchen.dao.AdminUserDao;
import com.yuchen.dao.ForumDao;
import com.yuchen.dao.UserDao;
import com.yuchen.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AdminUserService {
    //获得sqlSession对象
    SqlSession sqlSession;
    public AdminUser login(String username, String password) {
        sqlSession = MybatisUtils.getSqlSession();

        AdminUserDao adminUserDao = sqlSession.getMapper(AdminUserDao.class);

        AdminUser adminUser = adminUserDao.login_info(username, password);

        sqlSession.close();

        return adminUser;
    }
    //获取所有用户
    public List<User> getAllUser() {
        sqlSession = MybatisUtils.getSqlSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        List<User> userList = userDao.getAllUser();

        sqlSession.close();

        return userList;
    }
    //删除用户帖子
    public void deletePost(int fid){
        sqlSession = MybatisUtils.getSqlSession();
        ForumDao forumDao = sqlSession.getMapper(ForumDao.class);

        forumDao.deletePost(fid);

        sqlSession.commit();
        sqlSession.close();
    }
    //删除用户
    public void delete_user(int id) {
        sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        ForumDao forumDao = sqlSession.getMapper(ForumDao.class);

        forumDao.deleteUserAllPost(id);
        userDao.delete_user(id);

        sqlSession.commit();
        sqlSession.close();
    }
    public void setSelectedPost(int fid) {
        sqlSession = MybatisUtils.getSqlSession();
        ForumDao forumDao = sqlSession.getMapper(ForumDao.class);

        forumDao.setSelectedPost(fid);

        sqlSession.commit();
        sqlSession.close();
    }
}
