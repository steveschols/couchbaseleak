package be.cegeka.domain;

import org.joda.time.LocalDate;

public class SummaryPatient {
    private String firstName;
    private String lastName;
    private String vioNumber;
    private String patientReference;
    private LocalDate dateOfBirth;
    private String nursingUnit;
    private String room;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getVioNumber() {
        return vioNumber;
    }

    public String getPatientReference() {
        return patientReference;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setPatientReference(String patientReference) {
        this.patientReference = patientReference;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNursingUnit() {
        return nursingUnit;
    }

    public String getRoom() {
        return room;
    }

    private SummaryPatient() {

    }

    public static Builder newSummaryPatient() {
        return new Builder();
    }

    public static final class Builder {
        private final SummaryPatient patient;

        private Builder() {
            patient = new SummaryPatient();
        }

        public Builder withFirstName(String firstName) {
            patient.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            patient.lastName = lastName;
            return this;
        }

        public Builder withVioNumber(String vioNumber) {
            patient.vioNumber = vioNumber;
            return this;
        }

        public Builder withPatientReference(String patientReference) {
            patient.patientReference = patientReference;
            return this;
        }

        public Builder withDateOfBirth(LocalDate dateOfBirth) {
            patient.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder withNursingUnit(String nursingUnit) {
            patient.nursingUnit = nursingUnit;
            return this;
        }

        public Builder withRoom(String room) {
            patient.room = room;
            return this;
        }

        public Builder withDefaults() {
            patient.vioNumber = "VIO987";
            patient.firstName = "Garfield";
            patient.lastName = "TestPatient";
            patient.patientReference = "reference";
            patient.dateOfBirth = LocalDate.now();

            return this;
        }

        public SummaryPatient build() {
            return patient;
        }
    }
}
