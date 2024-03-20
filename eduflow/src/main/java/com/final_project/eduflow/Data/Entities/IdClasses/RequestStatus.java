package com.final_project.eduflow.Data.Entities.IdClasses;

public enum RequestStatus {
    ACCEPTED("accepted"),
    NEED_AFFIRMATION("need_affirmation"),
    WAITING("waiting"),
    REJECTED("rejected");

    private String value;

    RequestStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RequestStatus fromValue(String value) {
        for (RequestStatus status : RequestStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid RequestStatus value: " + value);
    }
}