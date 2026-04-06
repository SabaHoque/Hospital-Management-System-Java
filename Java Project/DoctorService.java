import java.util.*;

public class DoctorService implements IDoctorService {

    private List<Doctor> doctors = new ArrayList<>();
    private List<DoctorObserver> observers = new ArrayList<>();

    public void addObserver(DoctorObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message) {
        for (DoctorObserver observer : observers) {
            observer.update(message);
        }
    }

    @Override
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        notifyObservers("Doctor added successfully");
    }
}
