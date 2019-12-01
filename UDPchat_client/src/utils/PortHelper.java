package utils;

import constants.Constants;
import constants.Global;
import models.UserInfo;

import java.net.InetAddress;
import java.util.Random;

public class PortHelper{
    public static int getRandomPort(int begin ,int end ){
        Random random = new Random();
        return random.nextInt(end - begin + 1) + begin;
    }

    public static int getServerPort(){
        return Constants.SERVER_PORT;
    }

    public static int getClientPort(){
        return Global.CLIENT_PORT;
    }

    public static void setClientPort(int port){
        Global.CLIENT_PORT = port;
    }

    public static InetAddress getLocalHost(){
        try {
            return InetAddress.getLocalHost();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static UserInfo getMyInfo(){
        return Global.myUserInfo;
    }
}
