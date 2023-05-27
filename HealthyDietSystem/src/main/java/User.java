public class User {
    public int id;
    public String fname;
    public String lname;
    public String phoneNum;
    public String email;
    public String password;

    public User(int id, String fname, String lname,  String email, String password, String phoneNum) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.phoneNum = phoneNum;
        this.email = email;
        this.password = password;
    }
    public User(){}


}
