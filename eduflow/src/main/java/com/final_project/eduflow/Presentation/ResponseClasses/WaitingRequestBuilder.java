package com.final_project.eduflow.Presentation.ResponseClasses;

import java.time.LocalDateTime;

public class WaitingRequestBuilder {
    private WaitingRequestsForStaff waitingRequestsForStaff = new WaitingRequestsForStaff();

    public WaitingRequestBuilder setStudentId(long studentId) {
        waitingRequestsForStaff.setStudentId(studentId);
        return this;
    }

    public WaitingRequestBuilder setStudentName(String studentName) {
        waitingRequestsForStaff.setStudentName(studentName);
        return this;
    }

    public WaitingRequestBuilder setStudentMail(String studentMail) {
        waitingRequestsForStaff.setStudentMail(studentMail);
        return this;
    }

    public WaitingRequestBuilder setStudentDepartment(String studentDepartment) {
        waitingRequestsForStaff.setStudentDepartment(studentDepartment);
        return this;
    }

    public WaitingRequestBuilder setRequestTypeId(int requestTypeId) {
        waitingRequestsForStaff.setRequestTypeId(requestTypeId);
        return this;
    }

    public WaitingRequestBuilder setRequestTypeName(String requestTypeName) {
        waitingRequestsForStaff.setRequestTypeName(requestTypeName);
        return this;
    }

    public WaitingRequestBuilder setCurrent_index(int current_index) {
        waitingRequestsForStaff.setCurrent_index(current_index);
        return this;
    }

    public WaitingRequestBuilder setInformation(String information) {
        waitingRequestsForStaff.setInformation(information);
        return this;
    }

    public WaitingRequestBuilder setAddition(String addition) {
        waitingRequestsForStaff.setAddition(addition);
        return this;
    }

    public WaitingRequestBuilder setWhenCreated(LocalDateTime whenCreated) {
        waitingRequestsForStaff.setWhenCreated(whenCreated);
        return this;
    }

    public WaitingRequestBuilder setCurrentActorId(long currentActorId) {
        waitingRequestsForStaff.setCurrentActorId(currentActorId);
        return this;
    }

    public WaitingRequestBuilder setStatus(String status) {
        waitingRequestsForStaff.setStatus(status);
        return this;
    }

    public WaitingRequestBuilder setAdviserId(long adviserId) {
        waitingRequestsForStaff.setAdviserId(adviserId);
        return this;
    }

    public WaitingRequestBuilder setAdviserName(String adviserName) {
        waitingRequestsForStaff.setAdviserName(adviserName);
        return this;
    }
    

    public WaitingRequestsForStaff build() {
        return waitingRequestsForStaff;
    }

    public WaitingRequestBuilder() {
    }



}
