package com.cg.feedback.trainer_user.dto;

import java.util.Date;

public interface ProgramDefaulters {
    String getBatch();
    String getStudentName();
    String getStudentId();
    String getStudentEmail();
    Date getStartDate();
    Date getEndDate();
}