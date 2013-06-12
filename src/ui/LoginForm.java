package ui;

import minecraft.User;
import network.http.LoginHandler;
import network.util.Constants;
import ui.util.SpringUtilities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 12.06.13
 * Time: 14:09
 * To change this template use File | Settings | File Templates.
 */
public class LoginForm extends JFrame {
    JPanel rootPanel;

    JLabel usernameLabel;
    JLabel passwordLabel;

    JTextField username;
    JPasswordField password;

    JButton login;

    private LoginForm() {
        initUI();
    }

    private void initUI() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new SpringLayout());

        usernameLabel = new JLabel("Username");
        rootPanel.add(usernameLabel);

        username = new JTextField();
        rootPanel.add(username);

        passwordLabel = new JLabel("Password");
        rootPanel.add(passwordLabel);

        password = new JPasswordField();
        rootPanel.add(password);

        login = new JButton("Login");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginHandler.login(User.getUser(username.getText(), String.valueOf(password.getPassword())), Constants.LAUNCHER_VERSION);
            }
        });
        rootPanel.add(login);

        JButton dummy = new JButton();
        dummy.setVisible(false);
        rootPanel.add(dummy);

        setBounds(100, 100, 300, 200);

        SpringUtilities.makeCompactGrid(rootPanel, 3, 2, 10, 10, 10, 10);
    }

    private static LoginForm instance;
    public static LoginForm getLoginForm() {
        if(instance == null) {
            instance = new LoginForm();
        }
        instance.setVisible(true);
        return instance;
    }

}
