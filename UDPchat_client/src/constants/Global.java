package constants;

import models.UserInfo;
import presenters.ChatPresenter;
import presenters.HomePresenter;

import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class Global {
    public static int CLIENT_PORT;
    public static HomePresenter homePresenter;
    public static List<ChatPresenter> chatPresenters=new ArrayList<ChatPresenter>();
    public static UserInfo myUserInfo = new UserInfo();
    public static DatagramSocket clientSocket = null;
    public static ChatPresenter groupChatPresenter;
}
