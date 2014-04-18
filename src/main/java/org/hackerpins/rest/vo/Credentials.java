package org.hackerpins.rest.vo;

/**
 * Created by shekhargulati on 18/04/14.
 */
public class Credentials {

    private String username;
    private String password;

    public Credentials() {
    }

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
