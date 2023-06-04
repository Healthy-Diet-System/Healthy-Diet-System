import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.Observable;
import java.util.Observer;
public class AppFacade extends JFrame implements Observer {
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static Statement statement;
    private Observable observable;

    public void startApp() {
        try {
            Javaconnect dbConnection = Javaconnect.getInstance();
            connection = dbConnection.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        observable = new Observable();
        observable.addObserver(this);

        loginScreen();
    }


    @Override
    public void update(Observable o, Object arg) {
        // Handle the update here
        System.out.println("Received update from observable");
    }
    public void loginScreen(){

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
                        int id = rezult.getInt("Customer_ID");
                        String fname = rezult.getString("fname");
                        String lname = rezult.getString("lname");
                        int weight = rezult.getInt("weight");
                        int height = rezult.getInt("height");
                        int age = rezult.getInt("age");
                        String gender = rezult.getString("gender");
                        String phoneNum = rezult.getString("phone_number");

                        Customer cust;
                        if(gender == null){
                            cust = new Customer(id, fname, lname, emailInput, passwordInput, phoneNum);
                        }
                        else{
                            cust = new Customer(id, fname, lname, emailInput, passwordInput, weight, height, age, gender, phoneNum);
                        }

                        servicesScreen(cust);
                        loginFrame.setVisible(false);

                        observable.notifyObservers();
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

    public void registerScreen(){

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

                            String query = "select max(customer_id) from customer";
                            preparedStatement = connection.prepareStatement(query);
                            ResultSet rezult3 = preparedStatement.executeQuery();
                            rezult3.next();
                            if(rezult3.getInt(customer_id) >= 1){
                                customer_id += rezult3.getInt("MAX(CUSTOMER_ID)");
                            }

                            String insert = "insert into customer values('" + customer_id + "', '" + fnameInput + "', '" + lnameInput
                                    + "', '" + emailInput + "', '" + passwordInput + "', null, null, null, null, '" + phoneInput + "')";
                            preparedStatement = connection.prepareStatement(insert);
                            ResultSet rezult2 = preparedStatement.executeQuery();
                            rezult2.next();



                        }

                    } catch (SQLException ae) {
                        JOptionPane.showMessageDialog(registerFrame, ae,
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }


    public void servicesScreen(Customer cust)
    {
        JFrame servicesFrame = new JFrame();

        JLabel servicesLabel = new JLabel("Choose Type of Service");
        JButton dietPlanButton = new JButton("Request Diet Plan");
        JButton settingButton = new JButton("Change Body Info");
        JButton caloriesButton = new JButton("Calculate Calories");
        JButton IBWButton = new JButton("Calculate Ideal Body Weight");
        JButton logoutButton = new JButton("Logout");

        Font font = new Font("Arial",Font.BOLD,18);
        servicesLabel.setFont(font);


        servicesLabel.setBounds(100,50,1000,100);
        dietPlanButton.setBounds(100,150,200,40);
        settingButton.setBounds(400,250,200,40);
        caloriesButton.setBounds(100,250,200,40);
        IBWButton.setBounds(400,150,200,40);
        logoutButton.setBounds(650,5,100,40);

        servicesFrame.add(servicesLabel);
        servicesFrame.add(dietPlanButton);
        servicesFrame.add(settingButton);
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

                /**/servicesFrame.setVisible(false);
                if(cust.getGender() == null)
                    informationScreen(cust, "dietPlan");
                else
                    dietPlanScreen(cust);

            }
        });

        //-------------------------------------------
        settingButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                /**/servicesFrame.setVisible(false);
                informationScreen(cust, "setting");
            }
        });

        //-------------------------------------------
        caloriesButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){

                /**/servicesFrame.setVisible(false);
                if(cust.getGender() == null)
                    informationScreen(cust, "calories");
                else
                    caloriesScreen(cust);
            }
        });

        //-------------------------------------------
        IBWButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                if (cust.getGender() == null) {
                    /**/servicesFrame.setVisible(false);
                    informationScreen(cust, "IBW");
                }
                else{
                    double ideal = calculateIBW(cust.getHeight(), cust.getGender());
                    JOptionPane.showMessageDialog(servicesFrame, "Your Ideal Weight is ( " + ideal + " ) kg.",
                            "Ideal Weight", JOptionPane.PLAIN_MESSAGE);
                }



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


    public  void informationScreen(Customer cust, String nextScreen)
    {
        JFrame informationFrame = new JFrame();

        JLabel informationLabel = new JLabel(
                "<html><body>  \n" +
                        "<br>Please Enter The Following Information to Continue" +
                        "</body></html>");
        JLabel heightLabel = new JLabel("Height (cm)");
        JLabel weightLabel = new JLabel("Weight (kg)");
        JLabel ageLabel = new JLabel("Age");
        JTextField heightField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField ageField = new JTextField();
        JButton nextButton = new JButton("Next");
        JButton backButton = new JButton("Back");

        JLabel genderLabel = new JLabel("Gender");
        JCheckBox maleBox = new JCheckBox("Male", true);
        JCheckBox femaleBox = new JCheckBox("Female");

        Font font = new Font("Arial",Font.BOLD,18);
        informationLabel.setFont(font);


        informationLabel.setBounds(100,50,1000,100);
        heightLabel.setBounds(150,150,300,30);
        heightField.setBounds(250,150,300,30);
        weightLabel.setBounds(150,200,300,30);
        weightField.setBounds(250,200,300,30);
        ageLabel.setBounds(150,250,300,30);
        ageField.setBounds(250,250,300,30);

        genderLabel.setBounds(150,300,100,30);
        maleBox.setBounds(250,300, 100,30);
        femaleBox.setBounds(350,300, 100,30);

        nextButton.setBounds(300,400,80,35);
        backButton.setBounds(630,5,100,40);

        //----------------- To Only Choose One Gender ----------------
        ButtonGroup checkBoxGroup = new ButtonGroup();
        checkBoxGroup.add(maleBox);
        checkBoxGroup.add(femaleBox);
        //------------------------------------------------------------

        informationFrame.add(informationLabel);
        informationFrame.add(heightLabel);
        informationFrame.add(heightField);
        informationFrame.add(weightLabel);
        informationFrame.add(weightField);
        informationFrame.add(ageLabel);
        informationFrame.add(ageField);

        informationFrame.add(genderLabel);
        informationFrame.add(maleBox);
        informationFrame.add(femaleBox);

        informationFrame.add(nextButton);
        informationFrame.add(backButton);



        informationFrame.setLayout(null);
        informationFrame.setVisible(true);
        informationFrame.setSize(750,600);
        informationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //------------ BUTTON ACTION ----------//
        nextButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {

                cust.setWeight(Double.parseDouble( weightField.getText()));
                cust.setHeight(Double.parseDouble( heightField.getText()));
                cust.setAge(Integer.parseInt(ageField.getText()));

                if(maleBox.isSelected())
                    cust.setGender("male");
                else
                    cust.setGender("female");


                try {


                    if(nextScreen.equalsIgnoreCase("dietPlan")){
                        dietPlanScreen(cust);
                        informationFrame.setVisible(false);
                    }
                    else if(nextScreen.equalsIgnoreCase("calories")){
                        caloriesScreen(cust);
                        informationFrame.setVisible(false);
                    }
                    else if(nextScreen.equalsIgnoreCase("IBW")){
                        double ideal = calculateIBW(cust.getHeight(), cust.getGender());
                        JOptionPane.showMessageDialog(informationFrame, "Your Ideal Weight is ( " + ideal + " ) kg.",
                                "Ideal Weight", JOptionPane.PLAIN_MESSAGE);
                        servicesScreen(cust);
                        informationFrame.setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(informationFrame, "Your body info has been updated!!",
                                "Body Information", JOptionPane.PLAIN_MESSAGE);
                        servicesScreen(cust);
                        informationFrame.setVisible(false);
                    }


                } catch(Exception exception)
                {
                    JOptionPane.showMessageDialog(informationFrame, "Wrong Information",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        //-------------------------------------------
        backButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                informationFrame.setVisible(false);
                servicesScreen(cust);
            }
        });
    }

    public double calculateIBW(double height, String gender){


        double ideal = 0;
        if(gender.equalsIgnoreCase("male"))
            ideal= 52 + (1.9 * ((height - 152.4) / 2.54));
        else
            ideal= 49 + (1.7 * ((height - 152.4) / 2.54));

        return Math.round(ideal * 100.0) / 100.0;   // Round up to 2 decimal places
    }



    public void caloriesScreen(Customer cust)
    {
        JFrame caloriesFrame = new JFrame();

        double calories = (10 * cust.getWeight()) + (6.25 * cust.getHeight()) + (5 * cust.getAge()) + 5; // daily calories


        double mildLoss = calories * 0.88;
        double weightLoss = calories * 0.76;
        double extremeLoss = calories * 0.53;

        // Adding results to labels to represent it in the Interface
        JLabel caloriesTitleLabel = new JLabel("Your Calories Per Day:");
        JLabel maintainLabel = new JLabel("- To maintain weight you should consume " + Double.parseDouble(String.format("%.1f", calories)) + " calories per day.");
        JLabel mildLossLabel = new JLabel("- To mildly lose weight you should consume " + Double.parseDouble(String.format("%.1f", mildLoss)) + " calories per day.");
        JLabel weightLossLabel = new JLabel("- To lose weight you should consume " + Double.parseDouble(String.format("%.1f", weightLoss)) + " calories per day.");
        JLabel extremeLossLabel = new JLabel("- To extremely lose weight you should consume " + Double.parseDouble(String.format("%.1f", extremeLoss)) + " calories per day.");
        JButton gotItButton = new JButton("Got it!");

        Font font = new Font("Arial",Font.BOLD,18);
        caloriesTitleLabel.setFont(font);

        // Labels & Button dimenstions
        caloriesTitleLabel.setBounds(20,50,600,50);
        maintainLabel.setBounds(50,100,600,30);
        mildLossLabel.setBounds(50,150,600,30);
        weightLossLabel.setBounds(50,200,600,30);
        extremeLossLabel.setBounds(50,250,600,30);
        gotItButton.setBounds(250,300,150,30);

        // Adding the buttons and labels to interface
        caloriesFrame.add(caloriesTitleLabel);
        caloriesFrame.add(maintainLabel);
        caloriesFrame.add(mildLossLabel);
        caloriesFrame.add(weightLossLabel);
        caloriesFrame.add(extremeLossLabel);
        caloriesFrame.add(gotItButton);

        caloriesFrame.setLayout(null);
        caloriesFrame.setVisible(true);
        caloriesFrame.setSize(700,400);

        //------------ BUTTON ACTION ----------//
        gotItButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                caloriesFrame.setVisible(false); // To hide the current screen
                servicesScreen(cust);

            }
        });
    }



    public void dietPlanScreen(Customer cust)
    {
        JFrame dietPlanFrame = new JFrame("Request Diet Plan");

        //------------- creating ------------
        //------------ Plan Duration:
        JLabel planTitle = new JLabel("Please select a plan:");
        JCheckBox twoWeeks = new JCheckBox("2 Weeks");
        JCheckBox oneMonth = new JCheckBox("1 Month");
        JCheckBox threeMonths = new JCheckBox("3 Months");
        JCheckBox sixMonths = new JCheckBox("6 Months");
        twoWeeks.setActionCommand("2 Weeks");
        oneMonth.setActionCommand("1 Month");
        threeMonths.setActionCommand("3 Months");
        sixMonths.setActionCommand("6 Months");
        JLabel priceLabel = new JLabel("Total: ");
        Font font = new Font("Arial",Font.BOLD,16);
        priceLabel.setFont(font);
        planTitle.setFont(font);

        //------------ Plan Type:
        JCheckBox extremeBox = new JCheckBox("Extreme Diet");
        JCheckBox ketoBox = new JCheckBox("Keto Diet");
        JCheckBox carbBox = new JCheckBox("Low Carb Diet");
        JCheckBox otherBox = new JCheckBox("Other Diet");
        extremeBox.setActionCommand("Extreme Diet");
        ketoBox.setActionCommand("Keto Diet");
        carbBox.setActionCommand("Low Carb Diet");
        JLabel typeLabel = new JLabel("Type of Diet Plan:");
        typeLabel.setFont(font);
        JTextField otherTextField = new JTextField();

        //------------ Credit Card Payment:
        JLabel ccLabel = new JLabel("Credit Card Information:");
        JLabel cardNumberLabel = new JLabel("Card Number");
        JLabel ccvLabel = new JLabel("CCV");
        JLabel monthLabel = new JLabel("Month");
        JLabel yearLabel = new JLabel("Year");
        ccLabel.setFont(font);
        JTextField cardNumberTextField = new JTextField();
        JTextField ccvTextField = new JTextField();
        JTextField monthTextField = new JTextField();
        JTextField yearTextField = new JTextField();
        JButton confirmButton = new JButton("Confirm");
        JButton backButton = new JButton("Back");
        //------------------------------------------------------------

        //------------ Dimenstions of the Elements of the interface ------------
        //------------ Plan Duration:
        planTitle.setBounds(20,20,400,50);
        twoWeeks.setBounds(20,50,400,50);
        oneMonth.setBounds(20,100,400,50);
        threeMonths.setBounds(20,150,400,50);
        sixMonths.setBounds(20,200,400,50);
        priceLabel.setBounds(20,250,400,50);

        //------------ Plan Type:
        typeLabel.setBounds(20,300,400,50);
        extremeBox.setBounds(20,350,350,50);
        ketoBox.setBounds(20,400,350,50);
        carbBox.setBounds(20,450,350,50);
        otherBox.setBounds(20,500,100,50);
        otherTextField.setBounds(150,505,250,35);

        //------------ Credit Card Payment:
        ccLabel.setBounds(500,20,400,50);
        cardNumberLabel.setBounds(500,70,400,50);
        cardNumberTextField.setBounds(600,75,350,35);
        monthLabel.setBounds(500,130,400,50);
        monthTextField.setBounds(600,135,70,35);
        yearLabel.setBounds(750,130,400,50);
        yearTextField.setBounds(800,135,70,35);
        ccvLabel.setBounds(500,180,50,35);
        ccvTextField.setBounds(600,185,70,35);

        confirmButton.setBounds(650,250,170,30);
        backButton.setBounds(850,5,100,40);

        //----------------- To Only Choose One ----------------
        ButtonGroup checkBoxGroup = new ButtonGroup();
        checkBoxGroup.add(extremeBox);
        checkBoxGroup.add(ketoBox);
        checkBoxGroup.add(carbBox);
        checkBoxGroup.add(otherBox);

        ButtonGroup checkBoxGroup2 = new ButtonGroup();
        checkBoxGroup2.add(twoWeeks);
        checkBoxGroup2.add(oneMonth);
        checkBoxGroup2.add(threeMonths);
        checkBoxGroup2.add(sixMonths);
        //------------------------------------------------------------

        //------------ Adding to Interface
        dietPlanFrame.add(planTitle);
        dietPlanFrame.add(twoWeeks);
        dietPlanFrame.add(oneMonth);
        dietPlanFrame.add(threeMonths);
        dietPlanFrame.add(sixMonths);
        dietPlanFrame.add(priceLabel);

        dietPlanFrame.add(typeLabel);
        dietPlanFrame.add(extremeBox);
        dietPlanFrame.add(ketoBox);
        dietPlanFrame.add(carbBox);
        dietPlanFrame.add(otherBox);
        dietPlanFrame.add(otherTextField);

        dietPlanFrame.add(ccLabel);
        dietPlanFrame.add(cardNumberLabel);
        dietPlanFrame.add(cardNumberTextField);
        dietPlanFrame.add(monthLabel);
        dietPlanFrame.add(monthTextField);
        dietPlanFrame.add(yearLabel);
        dietPlanFrame.add(yearTextField);
        dietPlanFrame.add(ccvLabel);
        dietPlanFrame.add(ccvTextField);

        dietPlanFrame.add(confirmButton);
        dietPlanFrame.add(backButton);

        //------------ Prices of Plan ------------//
        twoWeeks.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                priceLabel.setText("Total: "
                        + (e.getStateChange()==1?"SAR 49":""));
            }
        });
        oneMonth.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                priceLabel.setText("Total: "
                        + (e.getStateChange()==1?"SAR 99":""));
            }
        });
        threeMonths.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                priceLabel.setText("Total: "
                        + (e.getStateChange()==1?"SAR 249":""));
            }
        });
        sixMonths.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                priceLabel.setText("Total: "
                        + (e.getStateChange()==1?"SAR 449":""));
            }
        });
        //-----------------------------------------------------------------//

        dietPlanFrame.setLayout(null);
        dietPlanFrame.setVisible(true);
        dietPlanFrame.setSize(1000,600);


        //---------------- BUTTON ACTIONS --------------
        confirmButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    int plan_id = 1;

                    // Find the next id that hasnt been used
                    String query = "select max(plan_id) from plan";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet rezult3 = preparedStatement.executeQuery();
                    rezult3.next();
                    if(rezult3.getInt(plan_id) >= 1){
                        plan_id += rezult3.getInt("MAX(PLAN_ID)");
                    }


                    String type = checkBoxGroup.getSelection().getActionCommand();
                    String duration = checkBoxGroup2.getSelection().getActionCommand();
                    double price = Double.parseDouble(priceLabel.getText().substring(11));

                    System.out.println(type);
                    System.out.println(duration);
                    System.out.println(price);

                    DietPlan dietPlan;
                    if(extremeBox.isSelected())
                        dietPlan = new DietPlan(plan_id, type, duration, price, 1, cust);

                    else if(ketoBox.isSelected())
                        dietPlan = new DietPlan(plan_id, type, duration, price, 2, cust);

                    else if(carbBox.isSelected())
                        dietPlan = new DietPlan(plan_id, type, duration, price, 3, cust);

                    else if(otherBox.isSelected())
                        dietPlan = new DietPlan(plan_id, otherTextField.getText(), duration, price, 4, cust);

                    else
                        throw new SQLException();  // If no check box of plan type was selected, throw exception

                    if(!(twoWeeks.isSelected() || oneMonth.isSelected() || threeMonths.isSelected() || sixMonths.isSelected()))
                        throw new SQLException(); // If no check box of plan duration was selected, throw exception



                    JOptionPane.showMessageDialog(confirmButton, "Thank you for requesting!"+"\n"+"We will notify you once your plan is ready via Email or Phone Number :D.",
                            "Done!", JOptionPane.PLAIN_MESSAGE);
                    dietPlanFrame.setVisible(false);
                    servicesScreen(cust);

                } catch (SQLException e2) {
                    JOptionPane.showMessageDialog(dietPlanFrame, "Please select a duration and the type of the wanted plan",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }



            }
        });


        backButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dietPlanFrame.setVisible(false);
                servicesScreen(cust);
            }
        });
    }

}


