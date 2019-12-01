package constants;

import models.UserInfo;

import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class Global {
    public static List<UserInfo> userList = new ArrayList<>();
    public static DatagramSocket serverSocket = null;
}
