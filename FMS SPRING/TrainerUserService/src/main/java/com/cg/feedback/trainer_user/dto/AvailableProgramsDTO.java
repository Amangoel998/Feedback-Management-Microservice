package com.cg.feedback.trainer_user.dto;

import java.sql.Date;

public interface AvailableProgramsDTO {
    public String getProgramId();
    public String getProgramName();
    public String getTrainerId();
    public String getTrainerName();
    public Date getStartDate();
    public Date getEndDate();
}