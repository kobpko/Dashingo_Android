package dashingo.dashingo.Activity;

import android.app.Application;

//所有程序的开始，设置所有的全局变量，所有的初始化在这里完成

public class MainApplication extends Application {

    private String userid;
    private String userpassword;
    private String username;
    private static String signupUrl ="http://10.0.1.22:8080/signUp";
    private static MainApplication instance;

    public static MainApplication getInstance() {
        return instance;
    }

    public static String getSignupUrl() {
        return signupUrl;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override

    public void onCreate(){
        super.onCreate();
        this.instance = this;

    }
}
