public class Customer extends User {
    private int age;
    private int height;
    private int weight;

    public Customer(int id, String fname, String lname, String phoneNum, String email) {
        super(id, fname, lname, phoneNum, email);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
