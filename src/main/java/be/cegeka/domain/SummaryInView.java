package be.cegeka.domain;

import org.joda.time.LocalDateTime;

public class SummaryInView {
    private String requestNumber;
    private String orderId;
    private LocalDateTime sampleTakenOrSampleArrivalDate;
    private LocalDateTime orderDate;
    private SummaryDoctor doctor;
    private SummaryPatient patient;
    private boolean read;
    private Severity severity;
    private Validity validity;
    private String doctorNumber;
    private Status status;
    private String relabel;
    private boolean invoiced;

    private SummaryInView() {
    }

    public static Builder newSummaryInView() {
        return new Builder();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public LocalDateTime getSampleTakenOrSampleArrivalDate() {
        return sampleTakenOrSampleArrivalDate;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public SummaryDoctor getDoctor() {
        return doctor;
    }

    public SummaryPatient getPatient() {
        return patient;
    }

    public boolean isRead() {
        return read;
    }

    public Severity getSeverity() {
        return severity;
    }

    public Validity getValidity() {
        return validity;
    }

    public String getDoctorNumber() {
        return doctorNumber;
    }

    public Status getStatus() {
        return status;
    }

    public String getRelabel() {
        return relabel;
    }

    public boolean isInvoiced() {
        return invoiced;
    }

    public static class Builder {
        private SummaryInView summaryInView = new SummaryInView();

        private Builder() {
        }

        public SummaryInView build() {
            return this.summaryInView;
        }

        public Builder withOrderId(String orderId) {
            summaryInView.orderId = orderId;
            return this;
        }

        public Builder withRequestNumber(String requestNumber) {
            summaryInView.requestNumber = requestNumber;
            return this;
        }

        public Builder withSampleTakenOrSampleArrivalDate(LocalDateTime sampleTakenOrSampleArrivalDate) {
            summaryInView.sampleTakenOrSampleArrivalDate = sampleTakenOrSampleArrivalDate;
            return this;
        }

        public Builder withOrderDate(LocalDateTime orderDate) {
            summaryInView.orderDate = orderDate;
            return this;
        }

        public Builder withDoctor(SummaryDoctor doctor) {
            summaryInView.doctor = doctor;
            return this;
        }

        public Builder withPatient(SummaryPatient patient) {
            summaryInView.patient = patient;
            return this;
        }

        public Builder withRead(boolean read) {
            summaryInView.read = read;
            return this;
        }

        public Builder withSeverity(Severity severity) {
            summaryInView.severity = severity;
            return this;
        }

        public Builder withValidity(Validity validity) {
            summaryInView.validity = validity;
            return this;
        }

        public Builder withDoctorNumber(String doctorNumber) {
            summaryInView.doctorNumber = doctorNumber;
            return this;
        }

        public Builder withStatus(Status status) {
            summaryInView.status = status;
            return this;
        }

        public Builder withRelabel(String relabel) {
            summaryInView.relabel = relabel;
            return this;
        }

        public Builder withInvoiced(boolean invoiced) {
            summaryInView.invoiced = invoiced;
            return this;
        }
    }
}