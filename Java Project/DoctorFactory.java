public class DoctorFactory {

    public Doctor createDoctor(
            String name,
            String mobile,
            String gender,
            String department,
            String joiningDate,
            double salary,
            String roomNo,
            String bmdcReg
    ) {
        return new Doctor(
                name,
                mobile,
                gender,
                department,
                joiningDate,
                salary,
                roomNo,
                bmdcReg
        );
    }
}