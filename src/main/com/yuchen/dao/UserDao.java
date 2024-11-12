package com.yuchen.dao;

import com.yuchen.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserDao {
    User login_info(@Param("username") String username, @Param("password") String password);
    User select_Username(String username);
    void add_User(User user);
    User findUserById(int userId);
    void update_nickname(@Param("nickname") String nickname, @Param("userId") int userId);
    void update_password(@Param("password") String password, @Param("userId") int userId);
    void update_birthday(@Param("birthday") Date birthday, @Param("userId") int userId);
    void update_sex(@Param("sex") String sex, @Param("userId") int userId);
    void update_phone(@Param("phone") String phone, @Param("userId") int userId);
    void update_email(@Param("email") String email, @Param("userId") int userId);
    void update_signature(@Param("signature") String signature, @Param("userId") int userId);
    List<User> getAllUser();
    void delete_user(int id);
}
