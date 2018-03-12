package be.cegeka.domain;

import java.util.Arrays;
import java.util.List;

public enum Status {
    DELETED_BY_DOCTOR,
    DELETED_BY_LAB,
    DELETED_BY_TIME,
    ORDERED,
    AUTO_VALIDATED,
    PARTIALLY_VALIDATED,
    APPROVED,
    SAMPLE_TAKEN,
    INTERNAL_ANALYSES_APPROVED,
    DRAFT;

    public static List<Status> getDeleteStatuses() {
        return Arrays.asList(DELETED_BY_DOCTOR, DELETED_BY_LAB, DELETED_BY_TIME);
    }
}
