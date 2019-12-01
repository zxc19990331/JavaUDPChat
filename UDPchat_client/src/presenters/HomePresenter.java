package presenters;

import models.UserInfo;
import views.HomeView;

import java.util.List;

public class HomePresenter {
    private HomeView homeView;
    private List<UserInfo> onlineUsers;

    public void init(){
        homeView = new HomeView();
    }

    public void setMyInfo(UserInfo userInfo){

    }

    public void setOnlineUserList(){

    }

    public static ChatPresenter createChat(String username){
        ChatPresenter newchat = new ChatPresenter();
        newchat.init(username);
        return newchat;
    }
}
