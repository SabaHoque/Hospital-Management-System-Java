package Classes;

public class Doctor extends Person {
    private String department;
    private String joiningDate;
    private String bmdcReg;
    private String password;

    // Original constructor
    public Doctor(String id, String name, String mobileNo, String gender, String department,
                  String joiningDate, String bmdcReg, String password) {
        super(id, name, mobileNo, gender);
        this.department = department;
        this.joiningDate = joiningDate;
        this.bmdcReg = bmdcReg;
        this.password = password;
    }

    // ================== BUILDER PATTERN (Creational) ==================
    public static class Builder {
        private String id, name, mobileNo, gender, department, joiningDate, bmdcReg, password;

        public Builder id(String id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder mobileNo(String mobileNo) { this.mobileNo = mobileNo; return this; }
        public Builder gender(String gender) { this.gender = gender; return this; }
        public Builder department(String department) { this.department = department; return this; }
        public Builder joiningDate(String joiningDate) { this.joiningDate = joiningDate; return this; }
        public Builder bmdcReg(String bmdcReg) { this.bmdcReg = bmdcReg; return this; }
        public Builder password(String password) { this.password = password; return this; }

        public Doctor build() {
            return new Doctor(id, name, mobileNo, gender, department, joiningDate, bmdcReg, password);
        }
    }

    // Getters and Setters
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getJoiningDate() { return joiningDate; }
    public void setJoiningDate(String joiningDate) { this.joiningDate = joiningDate; }

    public String getBmdcReg() { return bmdcReg; }
    public void setBmdcReg(String bmdcReg) { this.bmdcReg = bmdcReg; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}