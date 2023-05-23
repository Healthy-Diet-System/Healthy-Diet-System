public class Nutritionist extends User{
    private int yearsOfExp;
    private String SpecializedIn;
    public Nutritionist(int id, String fname, String lname, String phoneNum, String email) {
        super(id, fname, lname, phoneNum, email);
    }

    public int getYearsOfExp() {
        return yearsOfExp;
    }

    public void setYearsOfExp(int yearsOfExp) {
        this.yearsOfExp = yearsOfExp;
    }

    public String getSpecializedIn() {
        return SpecializedIn;
    }

    public void setSpecializedIn(String specializedIn) {
        SpecializedIn = specializedIn;
    }
}
