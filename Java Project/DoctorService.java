
import java.util.ArrayList;
import java.util.List;

public class DoctorService implements IDoctorService {
    private List<Doctor> doctors = new ArrayList<>();

    @Override
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
       
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }
}
