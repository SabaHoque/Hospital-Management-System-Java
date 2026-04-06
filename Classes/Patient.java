package Classes;

public class Patient extends Person {
    private String age;
    private String address;
    private String appointedDoctor;
    private String room;
    private String diagnosis;
    private String deposit;

    // Original constructor (kept for compatibility)
    public Patient(String id, String name, String mobileNo, String gender, String age, String address,
                   String appointedDoctor, String room, String diagnosis, String deposit) {
        super(id, name, mobileNo, gender);
        this.age = age;
        this.address = address;
        this.appointedDoctor = appointedDoctor;
        this.room = room;
        this.diagnosis = diagnosis;
        this.deposit = deposit;
    }

    // ================== BUILDER PATTERN (Creational) ==================
    public static class Builder {
        private String id, name, mobileNo, gender, age, address, appointedDoctor, room, diagnosis, deposit;

        public Builder id(String id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder mobileNo(String mobileNo) { this.mobileNo = mobileNo; return this; }
        public Builder gender(String gender) { this.gender = gender; return this; }
        public Builder age(String age) { this.age = age; return this; }
        public Builder address(String address) { this.address = address; return this; }
        public Builder appointedDoctor(String appointedDoctor) { this.appointedDoctor = appointedDoctor; return this; }
        public Builder room(String room) { this.room = room; return this; }
        public Builder diagnosis(String diagnosis) { this.diagnosis = diagnosis; return this; }
        public Builder deposit(String deposit) { this.deposit = deposit; return this; }

        public Patient build() {
            return new Patient(id, name, mobileNo, gender, age, address, appointedDoctor, room, diagnosis, deposit);
        }
    }

    // Getters and Setters
    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getAppointedDoctor() { return appointedDoctor; }
    public void setAppointedDoctor(String appointedDoctor) { this.appointedDoctor = appointedDoctor; }

    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getDeposit() { return deposit; }
    public void setDeposit(String deposit) { this.deposit = deposit; }
}