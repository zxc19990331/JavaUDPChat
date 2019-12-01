package presenters;

import models.ChatMessage;
import views.ChatView;

public class ChatPresenter {
    private String chatname;
    private ChatView chatView;

    public String getChatName(){
        return chatname;
    }

    public void init(String chatname){
        chatView = new ChatView();
        chatView.setChatName(chatname);
    }

    public void setVisible(boolean b){
        chatView.setVisible(b);
    }

    public void addNewMsg(ChatMessage chatMessage){

    }


}
