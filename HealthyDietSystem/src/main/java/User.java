public class User {
    private int id;
    private String fname;
    private String lname;
    private String phoneNum;
    private String email;


    public User(int id, String fname, String lname, String phoneNum, String email) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
