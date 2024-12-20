package com.yuchen.dao;

import com.yuchen.bean.AdminUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserDao {
    List<AdminUser> getAdminUserList();
    AdminUser login_info(@Param("username") String username, @Param("password") String password);

}
