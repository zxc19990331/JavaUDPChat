package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public class LoginView {
    private JFrame jFrame = new JFrame("登录");
    private Container c = jFrame.getContentPane();
    private JLabel a1 = new JLabel("用户名");
    private JTextField usernamePanel = new JTextField();
    private JButton okbtn = new JButton("确定");
    private JButton cancelbtn = new JButton("取消");

    public LoginView() {
        //设置窗体的位置及大小
        jFrame.setBounds(600, 200, 300, 220);
        //设置一层相当于桌布的东西
        c.setLayout(new BorderLayout());//布局管理器
        //初始化--往窗体里放其他控件
        init();
        //设置窗体可见
        jFrame.setVisible(true);
    }
    public void init() {
        /*标题部分--North*/
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("UDP聊天程序登录系统"));
        c.add(titlePanel, "North");

        /*输入部分--Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        a1.setBounds(50, 20, 50, 20);
        fieldPanel.add(a1);

        usernamePanel.setBounds(110, 20, 120, 20);
        fieldPanel.add(usernamePanel);

        c.add(fieldPanel, "Center");

        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(okbtn);
        buttonPanel.add(cancelbtn);
        c.add(buttonPanel, "South");
    }

    public String getUserName(){
        return usernamePanel.getText();
    }

    public void setButtonListener(ActionListener actionListener){
        okbtn.addActionListener(actionListener);
    }

    public void setWindowListener(WindowListener windowListener){
        jFrame.addWindowListener(windowListener);
    }

    public void showLoginFail(){
        JOptionPane.showMessageDialog(null, "在线用户中有重名，请更换昵称！", "登录失败",JOptionPane.WARNING_MESSAGE);
    }

    public void showLoginSuccess(){
        JOptionPane.showMessageDialog(null, "欢迎你," + getUserName(), "登录成功",JOptionPane.WARNING_MESSAGE);
    }

    public void showInputInvalid(){
        JOptionPane.showMessageDialog(null, "用户名非法！", "登录失败",JOptionPane.WARNING_MESSAGE);
    }

    public void clearInput(){
        usernamePanel.setText("");
    }
    public void closeLogin(){
        jFrame.dispose();
    }

    //测试
    public static void main(String[] args) {
        new LoginView();
    }


}
