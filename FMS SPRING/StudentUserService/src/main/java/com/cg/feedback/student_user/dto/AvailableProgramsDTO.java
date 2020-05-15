package com.cg.feedback.student_user.dto;

import java.util.Date;

public interface AvailableProgramsDTO {
    public String getProgramId();
    public String getProgramName();
    public String getTrainerId();
    public String getTrainerName();
    public Date getStartDate();
    public Date getEndDate();
}