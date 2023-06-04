import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DietPlan implements CustomObserver {
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
        cust.attach(this);
        insert();
    }

    public void insert() {
        try {
            Connection connection = Javaconnect.getInstance().getConnection();

            String insert = "insert into plan values('" + planId + "', '" + type + "', '" +
                    duration + "', '" + price + "', '" + nutID + "', '" + cust.getId() + "')";

            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(Customer customer) {
        System.out.println("DietPlan updated for Customer ID: " + customer.getId());
        System.out.println("New data: Age - " + customer.getAge() + ", Height - " + customer.getHeight());
    }
}
