package com.cg.feedback.trainer_user.feignServices;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.feedback.trainer_user.dto.BatchCourseDTO;
import com.cg.feedback.trainer_user.dto.FeedbackDTO;
import com.cg.feedback.trainer_user.dto.ProgramCourseDTO;
import com.cg.feedback.trainer_user.dto.ProgramDefaulters;
import com.cg.feedback.trainer_user.dto.ReportDTO;
import com.cg.feedback.trainer_user.dto.StudentDTO;
import com.cg.feedback.trainer_user.dto.TrainerDefaulters;
import com.cg.feedback.trainer_user.dto.TrainerProgramDTO;
import com.cg.feedback.trainer_user.exceptions.CustomException;

@Service
public class TrainerUserServiceImpl implements TrainerUserService {
	
    private BatchCourseServiceFeign batchCourseService;
    private FeedbackServiceFeign feedbackService;
    private ProgramCourseServiceFeign programCourseService;
    private StudentServiceFeign studentService;
    private TrainerProgramServiceFeign trainerProgramService;
    private TrainerServiceFeign trainerService;

    @Autowired
    public TrainerUserServiceImpl(	BatchCourseServiceFeign batchCourseService,
						    FeedbackServiceFeign feedbackService,
						    ProgramCourseServiceFeign programCourseService,
						    StudentServiceFeign studentService,
						    TrainerProgramServiceFeign trainerProgramService,
						    TrainerServiceFeign trainerService) throws CustomException {
        this.batchCourseService = batchCourseService;
        this.feedbackService = feedbackService;
        this.programCourseService = programCourseService;
        this.studentService = studentService;
        this.trainerProgramService = trainerProgramService;
        this.trainerService = trainerService;
    }
	
    @Override
    public ReportDTO showFeedbackReport(String trainerId) throws CustomException {
        try {
            if (trainerService.existsById(trainerId)) {
                List<FeedbackDTO> feedbacks = feedbackService.getAllFeedbacksForTrainer(trainerId);
                if(feedbacks==null || feedbacks.size()<1)
                    throw new CustomException("No Feedbacks for the Trainer");
                ReportDTO result = new ReportDTO(feedbacks);
                return result;
            } else
                throw new CustomException("Trainer Doesn't Exists");
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        }
    }

    @Override
    public List<TrainerDefaulters> showDefaulters(String trainerId) throws CustomException {
        try {
            if (trainerService.existsById(trainerId)) {
                List<TrainerDefaulters> defaulters = viewDefaultersByTrainer(trainerId);
                if(defaulters==null || defaulters.size()<1)
                    throw new CustomException("No Feedbacks Defaulters for the Trainer");
                return defaulters;
            } else
                throw new CustomException("Trainer Doesn't Exists");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
    
    @Override
	public List<ProgramDefaulters> viewDefaultersByProgram(String program) throws CustomException {
		List<ProgramCourseDTO> programCourses = programCourseService.getCoursePrograms();
		List<String> courses = programCourses.stream().filter(temp -> {
			long days = ChronoUnit.DAYS.between(LocalDate.now(), temp.getEnddate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() );
			return temp.getprogramCourseId().getProgramId().equals(program) && days<=0 && days>=-30;
		}).map(temp -> temp.getprogramCourseId().getCourseId()).collect(Collectors.toList());
		
		if(courses.size()==0) throw new CustomException("Program not ended in any course in recent 30 days!");
		
		List<BatchCourseDTO> batchCourses = batchCourseService.getBatches();
		List<String> batches = batchCourses.stream().filter(temp -> {
			return courses.contains(temp.getCourseId());
		}).map(temp -> temp.getBatch()).collect(Collectors.toList());
		
		if(batches.size()==0) throw new CustomException("Program not ended in any course in recent 30 days!");
		
		List<StudentDTO> studentDTOs = studentService.getAllStudents().stream().filter(temp -> batches.contains(temp.getBatch())).collect(Collectors.toList());
		List<String> students = studentDTOs.stream().map(temp -> temp.getStudentId()).collect(Collectors.toList());
		
		if(students.size()==0) throw new CustomException("No Students have studied this program in recent 30 days!"); 
		
		List<String> studentsFeedbackGiven = feedbackService.getAllFeedbacksForProgram(program).stream().filter(temp -> students.contains(temp.getStudentId())).map(temp -> temp.getStudentId()).collect(Collectors.toList());
		
		List<ProgramDefaulters> programDefaulters = new ArrayList<ProgramDefaulters>();
		
		studentDTOs.stream().filter(temp -> !studentsFeedbackGiven.contains(temp.getStudentId())).forEach(temp -> {
			BatchCourseDTO course = batchCourses.stream().filter(temp2 -> temp2.getBatch().equals(temp.getBatch())).findFirst().get();
			ProgramCourseDTO prgCrs = programCourses.stream().filter(temp2 -> {
				return temp2.getprogramCourseId().getProgramId().equals(program) && temp2.getprogramCourseId().getCourseId().equals(course.getCourseId());
			}).findFirst().get();
			programDefaulters.add(new ProgramDefaulters() {
				
				@Override
				public String getStudentName() {
					return temp.getStudentName();
				}
				
				@Override
				public String getStudentId() {
					return temp.getStudentId();
				}
				
				@Override
				public String getStudentEmail() {
					return temp.getStudentEmail();
				}
				
				@Override
				public Date getStartDate() {
					return prgCrs.getStartdate();
				}
				
				@Override
				public Date getEndDate() {
					return prgCrs.getEnddate();
				}
				
				@Override
				public String getBatch() {
					return temp.getBatch();
				}
			});
		});
		
		return programDefaulters;
	}

	@Override
	public List<TrainerDefaulters> viewDefaultersByTrainer(String trainer) throws CustomException {
		List<TrainerProgramDTO> trainers = trainerProgramService.getProgramTrainers().stream().filter(temp -> temp.getTrainerProgramId().getTrainerId().equals(trainer)).collect(Collectors.toList());
		if(trainers.size()==0)throw new CustomException("Trainer not present!");
		
		List<TrainerDefaulters> result = new ArrayList<TrainerDefaulters>();
		trainers.stream().forEach(temp->{
			try {
				List<ProgramDefaulters> students = viewDefaultersByProgram(temp.getTrainerProgramId().getProgramId());
				if(students.size()==0) throw new CustomException("No Defaulters in this Program!");
				students.stream().forEach(stud -> result.add(new TrainerDefaulters() {
					
					@Override
					public String getStudentName() {
						return stud.getStudentName();
					}
					
					@Override
					public String getStudentId() {
						return stud.getStudentId();
					}
					
					@Override
					public String getStudentEmail() {
						return stud.getStudentEmail();
					}
					
					@Override
					public Date getStartDate() {
						return stud.getStartDate();
					}
					
					@Override
					public String getProgramId() {
						return temp.getTrainerProgramId().getProgramId();
					}
					
					@Override
					public Date getEndDate() {
						return stud.getEndDate();
					}
					
					@Override
					public String getBatch() {
						return stud.getBatch();
					}
				}));
			} catch (CustomException e) {
			}
		});
		
		return result;
	}
}
