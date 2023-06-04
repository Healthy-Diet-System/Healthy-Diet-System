import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private int age;
    private double height;
    private double weight;
    private String gender;
    private List<CustomObserver> customObservers;

    public Customer(int id, String fname, String lname, String email, String password, String phoneNum) {
        super(id, fname, lname, email, password, phoneNum);
        customObservers = new ArrayList<>();
    }

    public Customer(int id, String fname, String lname, String email, String password, int weight, int height, int age, String gender, String phoneNum) {
        super(id, fname, lname, email, password, phoneNum);
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        customObservers = new ArrayList<>();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        String update = "update customer set age ='" + age + "' where customer_ID ='" + getId() + "'";
        try {
            Connection connection = Javaconnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            System.out.println("In customer");
        }
        notifyObservers();
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        String update = "update customer set height ='" + height + "' where customer_ID ='" + getId() + "'";
        try {
            Connection connection = Javaconnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            System.out.println("In customer");
        }
        notifyObservers();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        String update = "update customer set weight ='" + weight + "' where customer_ID ='" + getId() + "'";
        try {
            Connection connection = Javaconnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            System.out.println("In customer");
        }
        notifyObservers();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        String update = "update customer set gender ='" + gender + "' where customer_ID ='" + getId() + "'";
        try {
            Connection connection = Javaconnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            System.out.println("In customer");
        }
        notifyObservers();
    }

    public void attach(CustomObserver customObserver) {
        customObservers.add(customObserver);
    }

    public void detach(CustomObserver customObserver) {
        customObservers.remove(customObserver);
    }

    private void notifyObservers() {
        for (CustomObserver customObserver : customObservers) {
            customObserver.update(this);
        }
    }
}
