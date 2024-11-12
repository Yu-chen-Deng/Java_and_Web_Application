package cn.edu.swu;

import cn.edu.swu.user.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LoginServlet extends HttpServlet {
    private Map<String, User> users = new HashMap<>();

    public void init(ServletConfig config) {
        String users = config.getInitParameter("users");
        String[] items = users.split(";");
        for (String item : items) {
            String[] userpass = item.split("@");
            User user = new User(userpass[0], userpass[1]);
            this.users.put(user.getName(), user);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        if (this.users.get(user) != null) {
            if (this.users.get(user).getPass().equals(pass)) {
                response.sendRedirect("./main.html");
                return;
            }
        }

        response.sendRedirect("./index.html");
    }
}
