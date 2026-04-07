package Classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Appointment {

    private final String appID;
    private final String patientName;
    private final String diagnosis;
    private final String doctorName;
    private final LocalDate appDate;
    private final LocalTime appTime;

    // Private constructor — only Builder can instantiate
    private Appointment(Builder builder) {
        this.appID       = builder.appID;
        this.patientName = builder.patientName;
        this.diagnosis   = builder.diagnosis;
        this.doctorName  = builder.doctorName;
        this.appDate     = builder.appDate;
        this.appTime     = builder.appTime;
    }

    // ── Getters ──────────────────────────────────────────────
    public String getAppId()        { return appID; }
    public String getPatientName()  { return patientName; }
    public String getDiagnosis()    { return diagnosis; }
    public String getDoctorName()   { return doctorName; }
    public LocalDate getAppDate()   { return appDate; }
    public LocalTime getAppTime()   { return appTime; }

    @Override
    public String toString() {
        return String.format(
            "Appointment{id='%s', patient='%s', doctor='%s', diagnosis='%s', date=%s, time=%s}",
            appID, patientName, doctorName, diagnosis, appDate, appTime
        );
    }

    // ── Builder ──────────────────────────────────────────────
    public static class Builder {

        // Required fields
        private final String appID;
        private final String patientName;

        // Optional / secondary fields
        private String diagnosis = "Not specified";
        private String doctorName;
        private LocalDate appDate;
        private LocalTime appTime;

        public Builder(String appID, String patientName) {
            this.appID       = requireNonBlank(appID,       "Appointment ID");
            this.patientName = requireNonBlank(patientName, "Patient name");
        }

        public Builder diagnosis(String diagnosis) {
            this.diagnosis = requireNonBlank(diagnosis, "Diagnosis");
            return this;
        }

        public Builder doctorName(String doctorName) {
            this.doctorName = requireNonBlank(doctorName, "Doctor name");
            return this;
        }

        /**
         * Accepts ISO-8601 format: yyyy-MM-dd (e.g. "2025-04-07")
         */
        public Builder appDate(String date) {
            try {
                this.appDate = LocalDate.parse(date);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException(
                    "Invalid date format. Expected yyyy-MM-dd, got: " + date
                );
            }
            return this;
        }

        /**
         * Accepts ISO-8601 format: HH:mm (e.g. "09:30")
         */
        public Builder appTime(String time) {
            try {
                this.appTime = LocalTime.parse(time);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException(
                    "Invalid time format. Expected HH:mm, got: " + time
                );
            }
            return this;
        }

        public Appointment build() {
            Objects.requireNonNull(doctorName, "Doctor name must be set before building.");
            Objects.requireNonNull(appDate,    "Appointment date must be set before building.");
            Objects.requireNonNull(appTime,    "Appointment time must be set before building.");
            return new Appointment(this);
        }

        // ── Validation helper ─────────────────────────────────
        private static String requireNonBlank(String value, String fieldName) {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException(fieldName + " must not be null or blank.");
            }
            return value;
        }
    }
}