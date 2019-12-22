package presenters;

import constants.Constants;
import constants.Global;
import models.ChatMessage;
import utils.MessageHelper;
import utils.PortHelper;
import views.LoginView;
import Thread.ClientCaptureThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class LoginPresenter {
    private LoginView loginView;

    public void init(){
        loginView = new LoginView();
        //按下登录按钮的事件
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //获得一个随机端口号
                int port = PortHelper.getRandomPort(10000,65535);
                PortHelper.setClientPort(port);
                String username = loginView.getUserName();
                //用户名不得为空
                if(username.equals("")){
                    loginView.showInputInvalid();
                    return;
                }
                DatagramSocket sendSocket = MessageHelper.sendNewUserMsg(username);
                System.out.println("已发送消息");
                //同步阻塞等待回复
                while(true){
                    byte[] bytes = new byte[1024];
                    DatagramPacket recPacket = new DatagramPacket(bytes,bytes.length);
                    try {
                        sendSocket.receive(recPacket);
                        ChatMessage chatMessage = ChatMessage.getInstance(new String(recPacket.getData()));
                        System.out.println("收到消息：" + chatMessage.toJsonString());
                        switch (chatMessage.getMsgType()){
                            case Constants.MSG_TYPE_LOGIN_FAIL:
                                loginView.clearInput();
                                loginView.showLoginFail();
                                return;
                            case Constants.MSG_TYPE_LOGIN_SUCCESS:
                                //全局变量设置个人信息

                                System.out.println("run");
                                PortHelper.getMyInfo().setUserName(username);
                                System.out.println("run");
                                PortHelper.getMyInfo().setAddress(PortHelper.getLocalHost());
                                System.out.println("run");
                                PortHelper.getMyInfo().setPort(PortHelper.getClientPort());
                                System.out.println("run");
                                //全局变量设置套接字
                                Global.clientSocket = sendSocket;
                                loginView.showLoginSuccess();
                                loginView.closeLogin();

                                HomePresenter homePresenter = new HomePresenter();
                                //存放主页对象
                                Global.homePresenter = homePresenter;
                                homePresenter.init();
                                Global.groupChatPresenter=HomePresenter.createGroupChat();
                                Global.groupChatPresenter.setVisible(false);
                                ClientCaptureThread clientCaptureThread=new ClientCaptureThread();
                                clientCaptureThread.start();
                                return;
                        }
                    }catch (Exception ee){
                        ee.printStackTrace();
                    }
                }
            }
        };
        //窗口事件
        WindowListener windowListener = new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        };
        loginView.setWindowListener(windowListener);
        loginView.setButtonListener(actionListener);
    }
}
