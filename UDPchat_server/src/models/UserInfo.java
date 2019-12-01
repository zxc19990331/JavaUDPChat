package models;

import java.net.InetAddress;

public class UserInfo {

    private String userName;
    private InetAddress address;
    private int port;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public UserInfo(String name,InetAddress ip,int port){
        this.userName = name;
        this.address = ip;
        this.port = port;
    }
}
