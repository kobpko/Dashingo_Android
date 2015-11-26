package model;

import java.lang.String; /**
 * Created by MSI on 2015/11/25.
 */
public class User {
    private String userId;
    private String userName;
    private String password;

    public User( ) {

    }

    public User(String password, String userId, String userName) {
        this.password = password;
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
