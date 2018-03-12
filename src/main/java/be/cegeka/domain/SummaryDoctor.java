package be.cegeka.domain;

public class SummaryDoctor {
    private String doctorNumber;
    private String firstName;
    private String lastName;
    private String title;

    public String getDoctorNumber() {
        return doctorNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    private SummaryDoctor(){}

    public static Builder newSummaryDoctor(){
        return new Builder();
    }

    public final static class Builder{

        private SummaryDoctor summaryDoctor;

        private Builder(){
            summaryDoctor = new SummaryDoctor();
        }

        public Builder withDoctorNumber(String doctorNumber){
            summaryDoctor.doctorNumber = doctorNumber;
            return this;
        }

        public Builder withFirstName(String firstName){
            summaryDoctor.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName){
            summaryDoctor.lastName = lastName;
            return this;
        }

        public Builder withTitle(String title){
            summaryDoctor.title = title;
            return this;
        }

        public Builder withDefaults(){
            summaryDoctor.doctorNumber = "ABC123";
            summaryDoctor.firstName = "Dre";
            summaryDoctor.lastName = "Dr.";
            summaryDoctor.title = "Doctor";
            return this;
        }

        public SummaryDoctor build(){
            return summaryDoctor;
        }
    }
}
