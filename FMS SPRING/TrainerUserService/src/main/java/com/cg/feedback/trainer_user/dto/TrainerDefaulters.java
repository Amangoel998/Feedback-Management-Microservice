package com.cg.feedback.trainer_user.dto;

import java.util.Date;

public interface TrainerDefaulters {
    String getBatch();
    String getProgramId();
    String getStudentName();
    String getStudentId();
    String getStudentEmail();
    Date getStartDate();
    Date getEndDate();
}