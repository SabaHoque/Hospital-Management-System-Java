
public class Doctor {
    private String name;
    private String mobile;
    private String gender;
    private String department;
    private String joiningDate;
    private double salary;
    private String roomNo;
    private String bmdcReg;

    public Doctor(String name, String mobile, String gender, String department,
                  String joiningDate, double salary, String roomNo, String bmdcReg) {
        this.name = name;
        this.mobile = mobile;
        this.gender = gender;
        this.department = department;
        this.joiningDate = joiningDate;
        this.salary = salary;
        this.roomNo = roomNo;
        this.bmdcReg = bmdcReg;
    }

    // Getters
    public String getName() { return name; }
    public String getMobile() { return mobile; }
    public String getGender() { return gender; }
    public String getDepartment() { return department; }
    public String getJoiningDate() { return joiningDate; }
    public double getSalary() { return salary; }
    public String getRoomNo() { return roomNo; }
    public String getBmdcReg() { return bmdcReg; }
}

