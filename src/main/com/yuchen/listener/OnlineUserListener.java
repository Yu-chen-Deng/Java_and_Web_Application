package com.yuchen.listener;

import com.yuchen.bean.User;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class OnlineUserListener implements HttpSessionListener {
    // 存储在线用户的 Map（key: 用户id, value: User对象）
    private static final Map<Integer, User> onlineUsers = new ConcurrentHashMap<>();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 会话创建时，预留存储用户对象的属性
        se.getSession().setAttribute("user", null);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 会话销毁时，移除用户
        User user = (User) se.getSession().getAttribute("user");
        if (user != null) {
            onlineUsers.remove(user.getId());
        }
    }

    public static Map<Integer, User> getOnlineUsers() {
        return onlineUsers;
    }

    public static int getOnlineUserCount() {
        return onlineUsers.size();
    }

    // 添加用户到在线列表
    public static void addUser(User user) {
        if (user != null) {
            onlineUsers.put(user.getId(), user);
        }
    }
}
