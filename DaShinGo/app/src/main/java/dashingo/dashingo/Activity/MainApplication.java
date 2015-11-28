package dashingo.dashingo.Activity;

import android.app.Application;

//所有程序的开始，设置所有的全局变量，所有的初始化在这里完成

public class MainApplication extends Application {

    private String userid;
    private String userpassword;

    public static String getGetAllList() {
        return getAllList;
    }

    private String username;
    private static String HostName = "http://139.129.131.78:8080/DaShinGo/";
    private static String signupUrl = HostName + "signUp";
    private static String signinUrl = HostName +"signIn";
    private static String getAllList = HostName + "route/getAll";
    private static String uploadRouteInfoUrl = HostName + "-uploadRouteInfo";
    private static String upLoadPic =  HostName + "uploadPics";
    private static String sendMessage = "http://222.73.117.158:80/msg/HttpBatchSendSM";
    public static String getSendMessage() {
        return sendMessage;
    }


    private static MainApplication instance;

    public static String getUpLoadPic() {
        return upLoadPic;
    }

    public static String getUploadRouteInfoUrl() {
        return uploadRouteInfoUrl;
    }

    public static String getSigninUrl() {

        return signinUrl;
    }

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
