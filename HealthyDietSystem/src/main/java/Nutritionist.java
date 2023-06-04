public class Nutritionist extends User{
    private int yearsOfExperiance;
    public Nutritionist(int id, String fname, String lname, String email, String password, String phoneNum, int yearsOfExperiance) {
        super(id, fname, lname, email, password, phoneNum);
        this.yearsOfExperiance = yearsOfExperiance;
    }

    public int getYearsOfExperiance() {
        return yearsOfExperiance;
    }

    public void setYearsOfExperiance(int yearsOfExperiance) {
        this.yearsOfExperiance = yearsOfExperiance;
    }
}
