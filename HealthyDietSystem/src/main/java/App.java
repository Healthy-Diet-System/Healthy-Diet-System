import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class App extends JFrame {
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;


    public static void main(String[] args) {
        try {
            Javaconnect dbConnection = Javaconnect.getInstance();
            connection = dbConnection.getConnection();

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }

        App app = new App();

    }

    public App(){
        loginScreen();
    }
    public static void loginScreen(){

        JFrame loginFrame = new JFrame();

        JLabel loginLabel = new JLabel("Healthy Diet System");
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");

        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");


        Font font = new Font("Arial",Font.BOLD,24);
        loginLabel.setFont(font);

        Font f2=new Font("Arial", Font.PLAIN,10);

        JLabel signature = new JLabel("<html><body>Done by:"+
                "<br>- Tareq Bajaber " +
                "<br>- Sami Bardisi " +
                "<br>- Ahmad Bakadam "+
                "</body></html>");
        signature.setFont(f2);
        JLabel signatureID = new JLabel("<html><body>"+
                "<br>| 2035796" +
                "<br>| 2035176" +
                "<br>| 2035445"+
                "</body></html>");
        signatureID.setFont(f2);

        loginLabel.setBounds(250,50,1000,30);
        emailLabel.setBounds(200,150,300,30);
        emailField.setBounds(300,150,300,30);
        passwordLabel.setBounds(200,200,300,30);
        passwordField.setBounds(300,200,300,30);
        loginButton.setBounds(300,260,80,35);
        registerButton.setBounds(400,260,150,35);
        signature.setBounds(20,400,200,200);
        signatureID.setBounds(130,400,200,200);



        loginFrame.add(emailField);
        loginFrame.add(passwordField);
        loginFrame.add(loginLabel);
        loginFrame.add(emailLabel);
        loginFrame.add(passwordLabel);
        loginFrame.add(loginButton);
        loginFrame.add(registerButton);
        loginFrame.add(signature);
        loginFrame.add(signatureID);

        loginFrame.setLayout(null);
        loginFrame.setVisible(true);
        loginFrame.setSize(750,600);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //------------ BUTTON ACTION ----------//
        loginButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){

                String emailInput = emailField.getText();
                String passwordInput = passwordField.getText();


                try {
                    String query = "select * from customer where email = '" + emailInput + "'";
                    preparedStatement = connection.prepareStatement(query);
                    ResultSet rezult = preparedStatement.executeQuery();
                    rezult.next();  // selects the customer with the userIDInput.. if there isnt a userID with that ID, then error => go to Catch


                    if(rezult.getString("password").equals(passwordInput)){   // Compare passwordInput with the password saved in database
                        Customer cc = new Customer(rezult.getInt("customer_id"), rezult.getString("fname"), rezult.getString("lname"), rezult.getString("phone_number"), rezult.getString("email"));
                        servicesScreen(cc.getId() + "");

                        loginFrame.setVisible(false);
                    }
                    else{
                        throw new SQLException();
                    }


                } catch (SQLException ae) {
                    JOptionPane.showMessageDialog(loginFrame, "Wrong email or Password, Please Try Again",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        //------------ BUTTON ACTION ----------//
        registerButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                registerScreen();
            }
        });

    }

    public static void registerScreen(){

        JFrame registerFrame = new JFrame("Register");


        JLabel password_Label = new JLabel("Password");
        JLabel Fname_Label = new JLabel("First Name");
        JLabel Lname_Label = new JLabel("Last Name");
        JLabel email_Label = new JLabel("Email Address");
        JLabel phoneNumber_Label = new JLabel("Phone Number");
        JLabel loginInfo_Label = new JLabel("Login Information");
        JLabel personalInfo_Label = new JLabel("Personal Information");

        JPasswordField password_Field = new JPasswordField();
        JTextField Fname_Field = new JTextField();
        JTextField Lname_Field = new JTextField();
        JTextField email_Field = new JTextField();
        JTextField phoneNumber_Field = new JTextField();

        JButton confirmButton = new JButton("Confirm");

        personalInfo_Label.setBounds(200,50,300,30);    // (<Left - >Right , <Up - >Down, length??, Width??)     < Low number goes..  > Higher number goes..
        Fname_Label.setBounds(100,100,300,30);
        Fname_Field.setBounds(250,100,200,30);
        Lname_Label.setBounds(100,150,300,30);
        Lname_Field.setBounds(250,150,200,30);
        phoneNumber_Label.setBounds(100,200,300,30);
        phoneNumber_Field.setBounds(250,200,200,30);
        loginInfo_Label.setBounds(200,250,300,30);
        email_Label.setBounds(100,300,300,30);
        email_Field.setBounds(250,300,200,30);
        password_Label.setBounds(100,350,300,30);
        password_Field.setBounds(250,350,200,30);
        confirmButton.setBounds(240,400,100,30);

        registerFrame.add(password_Label);
        registerFrame.add(password_Field);
        registerFrame.add(Fname_Label);
        registerFrame.add(Fname_Field);
        registerFrame.add(Lname_Label);
        registerFrame.add(Lname_Field);
        registerFrame.add(email_Label);
        registerFrame.add(email_Field);
        registerFrame.add(phoneNumber_Label);
        registerFrame.add(phoneNumber_Field);
        registerFrame.add(loginInfo_Label);
        registerFrame.add(personalInfo_Label);
        registerFrame.add(confirmButton);

        registerFrame.setLayout(null);
        registerFrame.setVisible(true);
        registerFrame.setSize(550,600);

        //------------ BUTTON ACTION ----------//
        confirmButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){  // When confirming.. check if the userID is already in the Database or not

                String fnameInput = Fname_Field.getText();
                String lnameInput = Lname_Field.getText();
                String emailInput = email_Field.getText();
                String phoneInput = phoneNumber_Field.getText();
                String passwordInput = password_Field.getText();

                if(fnameInput.isEmpty() || lnameInput.isEmpty() || emailInput.isEmpty() || phoneInput.isEmpty() || phoneInput.isEmpty() || passwordInput.isEmpty()){
                    JOptionPane.showMessageDialog(registerFrame, "Please fill all the information!",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    try {
                        String select = "select * from customer where email = '" + emailInput + "'";
                        preparedStatement = connection.prepareStatement(select);
                        ResultSet rezult = preparedStatement.executeQuery();
                        boolean re = rezult.next();              // true when moved to next... false when there isnt a row with that email


                        if(re){ // if there is an acc with that email address
                            JOptionPane.showMessageDialog(registerFrame, "Email Address already exists, Please try another Email Address",
                                    "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                        else{ // else if the email is NEW
                            JOptionPane.showMessageDialog(registerFrame, "Registered Successfully!",
                                    "Register Status", JOptionPane.PLAIN_MESSAGE);
                            registerFrame.setVisible(false);


                            int customer_id = 1;

                            while(true){ // loop to determine the next unused ID
                                try {
                                    String insert = "insert into customer values('" + customer_id + "', '" + fnameInput + "', '" + lnameInput
                                            + "', '" + emailInput + "', '" + passwordInput + "', null, null, null, null, '" + phoneInput + "', null)";
                                    preparedStatement = connection.prepareStatement(insert);
                                    ResultSet rezult2 = preparedStatement.executeQuery();
                                    rezult2.next();
                                    break;

                                } catch (SQLException aa) {
                                    customer_id++;
                                }
                            }
                        }

                    } catch (SQLException ae) {
                        JOptionPane.showMessageDialog(registerFrame, ae,
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }


    public static void servicesScreen(String custID)
    {
        JFrame servicesFrame = new JFrame();

        JLabel servicesLabel = new JLabel("Choose Type of Service");
        JButton dietPlanButton = new JButton("Request Diet Plan");
        JButton newButton = new JButton("");
        JButton caloriesButton = new JButton("Calculate Calories");
        JButton IBWButton = new JButton("Calculate Ideal Body Weight");
        JButton logoutButton = new JButton("Logout");

        Font font = new Font("Arial",Font.BOLD,18);
        servicesLabel.setFont(font);


        servicesLabel.setBounds(100,50,1000,100);
        dietPlanButton.setBounds(100,150,200,40);
        newButton.setBounds(400,250,200,40);
        caloriesButton.setBounds(100,250,200,40);
        IBWButton.setBounds(400,150,200,40);
        logoutButton.setBounds(650,5,100,40);

        servicesFrame.add(servicesLabel);
        servicesFrame.add(dietPlanButton);
        servicesFrame.add(newButton);
        servicesFrame.add(IBWButton);
        servicesFrame.add(caloriesButton);
        servicesFrame.add(logoutButton);


        servicesFrame.setLayout(null);
        servicesFrame.setVisible(true);
        servicesFrame.setSize(770,600);
        servicesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        /*
        ---------- BUTTON ACTIONS --------
         */
        dietPlanButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                //First, let use enter more info about him/herself -> then request plan*
                // Maybe Factory for Diet plan??? Check later
            }
        });

        //-------------------------------------------
        newButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                // Function????
            }
        });

        //-------------------------------------------
        caloriesButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                /**/servicesFrame.setVisible(false);
                informationScreen(custID, "calories");
            }
        });

        //-------------------------------------------
        IBWButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                // Sami

            }
        });


        //-------------------------------------------
        logoutButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                servicesFrame.setVisible(false);
                loginScreen();
            }
        });

    }


    public static void informationScreen(String userIDLog, String nextScreen) {
        // user enter more info about him to continue to other screens
    }


    public static void caloriesScreen(double weight, double height, int age, String gender, String userIDLog)
    {
        JFrame caloriesFrame = new JFrame();

    }


}

// How to release & generate JAR file from source code? update later


