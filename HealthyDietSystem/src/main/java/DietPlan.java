import javax.swing.*;
import java.sql.*;

public class DietPlan {
    private int planId;
    private String type;
    private String duration;
    private double price;
    private int nutID;
    private Customer cust;

    public DietPlan(int planId, String type, String duration, double price, int nutID, Customer cust) {
        this.planId = planId;
        this.type = type;
        this.duration = duration;
        this.price = price;
        this.nutID = nutID;
        this.cust = cust;

        insert();


    }

    public void insert(){
        try {

            Connection connection = Javaconnect.getInstance().getConnection();

            String insert = "insert into plan values('" + planId + "', '" + type + "', '" +
                    duration + "', '" + price + "', '" + nutID + "', '" + cust.getId() + "')";



            //Insert Plan info
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            ResultSet rezult = preparedStatement.executeQuery();
            rezult.next();


        } catch(SQLException e){
            System.out.println(e);
        }

    }


}
