package Classes;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentList extends AbstractFileRepository<Appointment> {

    private static final String APPOINTMENT_FILE_PATH = "Files/AppointmentList.txt";
    private static final Logger LOGGER = Logger.getLogger(AppointmentList.class.getName());

    private static volatile AppointmentList instance;

    private AppointmentList() {
        super(APPOINTMENT_FILE_PATH);
    }

    // ── Singleton ─────────────────────────────────────────────────────────────
    // volatile + double-checked locking: avoids lock acquisition cost on every
    // call once the instance is initialized, while remaining thread-safe.
    public static AppointmentList getInstance() {
        if (instance == null) {
            synchronized (AppointmentList.class) {
                if (instance == null) {
                    instance = new AppointmentList();
                }
            }
        }
        return instance;
    }

    // ── File Loading ──────────────────────────────────────────────────────────
    @Override
    protected void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                Appointment app = parseNextAppointment(sc);
                if (app != null) {
                    items.add(app);
                }
            }
        } catch (IOException e) {
            // Catch specific exception, not all of Exception.
            // Fail loudly — silent swallowing hides data corruption.
            LOGGER.log(Level.SEVERE, "Failed to load appointments from file: " + filePath, e);
        }
    }

    /**
     * Reads exactly 6 data lines + 1 blank separator from the scanner.
     * Returns null and logs a warning if the block is malformed,
     * so one bad record doesn't corrupt all subsequent records.
     */
    private Appointment parseNextAppointment(Scanner sc) {
        try {
            String appID       = readLine(sc, "appID");
            String patientName = readLine(sc, "patientName");
            String diagnosis   = readLine(sc, "diagnosis");
            String doctorName  = readLine(sc, "doctorName");
            String appDate     = readLine(sc, "appDate");
            String appTime     = readLine(sc, "appTime");

            if (sc.hasNextLine()) sc.nextLine(); // consume blank separator

            // Uses the Builder — validation is enforced at construction
            return new Appointment.Builder(appID, patientName)
                    .diagnosis(diagnosis)
                    .doctorName(doctorName)
                    .appDate(appDate)
                    .appTime(appTime)
                    .build();

        } catch (IllegalArgumentException | IllegalStateException e) {
            // One malformed record is skipped, not the entire file
            LOGGER.log(Level.WARNING, "Skipping malformed appointment record: " + e.getMessage());
            return null;
        }
    }

    /**
     * Reads the next line, throwing clearly if the file ends unexpectedly.
     * Replaces silent positional reads that corrupt on missing lines.
     */
    private String readLine(Scanner sc, String fieldName) {
        if (!sc.hasNextLine()) {
            throw new IllegalStateException(
                "Unexpected end of file while reading field: " + fieldName
            );
        }
        return sc.nextLine().trim();
    }

    // ── Serialization ─────────────────────────────────────────────────────────
    @Override
    protected String buildBlock(Appointment app) {
        return app.getAppId()       + "\n" +
               app.getPatientName() + "\n" +
               app.getDiagnosis()   + "\n" +
               app.getDoctorName()  + "\n" +
               app.getAppDate()     + "\n" +
               app.getAppTime()     + "\n\n";
    }

    // ── Match fix: ID-based instead of full block rebuild ─────────────────────
    // Old: rebuilt the entire block string just for equality — fragile and wasteful.
    // New: matches on the unique appID — correct by design, O(1) string compare.
    @Override
    protected boolean blocksMatch(Appointment app, String block) {
        return block.startsWith(app.getAppId());
    }

    // ── Public API ────────────────────────────────────────────────────────────
    public void addAppointment(Appointment app)                               { add(app); }
    public Appointment getAppointment(int index)                              { return get(index); }
    public void deleteAppointment(Appointment app)                            { delete(app); }
    public int searchAppointment(String appId)                                { return searchByKey(appId, Appointment::getAppId); }
    public void updateAppointment(String appId, Appointment updatedApp)       { update(appId, updatedApp); }
}