package com.yuchen.service;

import com.yuchen.bean.AdminUser;
import com.yuchen.bean.User;

public class LoginService {
    public User login(String username, String password) {
        UserService userService = new UserService();
        User user = userService.login(username, password);

        return user;
    }

    public AdminUser adminLogin(String username, String password){
        AdminUserService adminUserService = new AdminUserService();
        AdminUser adminUser = adminUserService.login(username, password);

        return adminUser;
    }
}
