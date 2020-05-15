package com.cg.feedback.admin_user.dto;

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