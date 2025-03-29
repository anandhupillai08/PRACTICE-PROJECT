import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class loginform extends JFrame {
    final private Font mainFont = new Font(" Times New Roman", Font.BOLD,20);
    JTextField tfEmail;
    JPasswordField pfPassword;

    public void initialize(){
        JLabel lbloginform = new JLabel("Login form", SwingConstants.CENTER);
        lbloginform.setFont(mainFont);

        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(mainFont);

        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);

        JLabel lbPassword = new JLabel("Password");
        lbPassword.setFont(mainFont);

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0,1,10,10));
        formPanel.add(lbloginform);
        formPanel.add(lbEmail);
        formPanel.add(tfEmail);
        formPanel.add(lbPassword);
        formPanel.add(lbPassword);

/*Button panel*/ 

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(mainFont);
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String email = tfEmail.getText();
                String Password = String.valueOf(pfPassword.getPassword());
                
                User user = getAuthenticatedUser (email, Password);

                if (user != null) {
                    MainFrame mainFrame =  new MainFrame();
                    mainFrame.initialize(user);
                    dispose();
                }    
                else {
                    JOptionPane.showMessageDialog(loginform.this, 
                            "Email or Password Invalid",
                            "Try again", 
                            JOptionPane.ERROR_MESSAGE);
            }
        }
    });

    JButton btnCancel = new JButton("Cancel");
    btnCancel.setFont(mainFont);
    btnCancel.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            dispose();
        }

    });

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new GridLayout(1, 2, 10, 0));
    buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
    buttonsPanel.add(btnLogin);
    buttonsPanel.add(btnCancel);



/*intiialize the frame*/

add(formPanel, BorderLayout.NORTH); 
add(buttonsPanel, BorderLayout.SOUTH);

        setTitle("Login form");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400,500);
        setMinimumSize(new Dimension(350,450));
        setLocationRelativeTo(null);
        setVisible(true);
    }



    private User getAuthenticatedUser (String email, String password) {
    User user = null;
    
    final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASSWORD = "";
    
    try{
        Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        // Connected to database successfully...
    
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
    
        ResultSet resultSet = preparedStatement.executeQuery();   
        
        if (resultSet.next()) {
            user = new User();
            user.name = resultSet.getString("name");
            user.email = resultSet.getString("email");
            user.phone = resultSet.getString("phone");
            user.address = resultSet.getString("address");
            user.Password = resultSet.getString("password");
        }


        preparedStatement.close();
        conn.close();
    
    }catch(Exception e){
        System.out.println("Database connection failed!");
    }
    
    return user;
    }


    public static void main(String[] args){
        loginform loginForm = new loginform();
        loginForm.initialize();
    }
}