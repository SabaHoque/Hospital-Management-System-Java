public class DoctorMenuAdapter {

    private IDoctorService doctorService;

    public DoctorMenuAdapter() {
        doctorService = new DoctorService();
    }

    public void openAddDoctorUI() {
        AddDoctorUI ui = new AddDoctorUI(doctorService);
        ui.setVisible(true);
    }
}