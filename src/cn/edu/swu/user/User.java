package cn.edu.swu.user;

public class User {
    private String name;
    private String pass;

    public User(String name, String pass) {
        this.setName(name);
        this.setPass(pass);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
