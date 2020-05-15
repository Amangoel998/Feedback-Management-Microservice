package com.cg.feedback.student_user.feignServices;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.feedback.student_user.feignServices.BatchCourseServiceFeign;
import com.cg.feedback.student_user.feignServices.FeedbackServiceFeign;
import com.cg.feedback.student_user.feignServices.ProgramCourseServiceFeign;
import com.cg.feedback.student_user.feignServices.StudentServiceFeign;
import com.cg.feedback.student_user.feignServices.TrainerProgramServiceFeign;
import com.cg.feedback.student_user.dto.AvailableProgramsDTO;
import com.cg.feedback.student_user.dto.BatchCourseDTO;
import com.cg.feedback.student_user.dto.ProgramCourseDTO;
import com.cg.feedback.student_user.dto.ProgramDTO;
import com.cg.feedback.student_user.dto.StudentDTO;
import com.cg.feedback.student_user.exceptions.CustomException;

@Service
public class StudentUserServiceImpl implements StudentUserService {

	private BatchCourseServiceFeign batchCourseService;
    private FeedbackServiceFeign feedbackService;
    private ProgramCourseServiceFeign programCourseService;
    private StudentServiceFeign studentService;
    private TrainerProgramServiceFeign trainerProgramService;
    private ProgramServiceFeign programService;
    private TrainerServiceFeign trainerService;

    @Autowired
    public StudentUserServiceImpl(	BatchCourseServiceFeign batchCourseService,
						    FeedbackServiceFeign feedbackService,
						    ProgramCourseServiceFeign programCourseService,
						    StudentServiceFeign studentService,
						    TrainerProgramServiceFeign trainerProgramService,
						    ProgramServiceFeign programService,
						    TrainerServiceFeign trainerService) throws CustomException {
        this.batchCourseService = batchCourseService;
        this.feedbackService = feedbackService;
        this.programCourseService = programCourseService;
        this.studentService = studentService;
        this.trainerProgramService = trainerProgramService;
        this.programService = programService;
        this.trainerService = trainerService;
    }
	
	@Override
	public List<AvailableProgramsDTO> getAvailablePrograms(String studentId) {
		StudentDTO student = studentService.getStudentById(studentId);
		BatchCourseDTO res1 = batchCourseService.getBatch(student.getBatch());
		String tempBatch = student.getBatch();
		String tempCourse = res1.getCourseId();
		List<ProgramCourseDTO> res2 = programCourseService.getCoursePrograms().stream().filter(temp -> temp.getprogramCourseId().getCourseId().equals(tempCourse)).filter(temp -> {
			long days = ChronoUnit.DAYS.between(LocalDate.now(), temp.getEnddate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			if(days<=0 && days>=-30)
				return true;
			return false;
		}).collect(Collectors.toList());
		System.out.println("LIST______________________------------>"+res2);
		List<String> programs = res2.stream().map(temp -> temp.getprogramCourseId().getProgramId()).collect(Collectors.toList());
		
		programs.stream().forEach(temp->System.out.println(temp));
		if(programs.size()==0) throw new CustomException("Student Exception : Student with Id: "+studentId+" has not completed a program since last 30 days!");
		List<AvailableProgramsDTO> result = new ArrayList<AvailableProgramsDTO>();
		List<String> feedbackGiven = new ArrayList<String>();
		feedbackService.getAllFeedbacks().stream().filter(temp -> programs.contains(temp.getProgramId()) && temp.getStudentId().equals(studentId)).
		map(temp->temp.getProgramId()).forEach(temp->feedbackGiven.add(temp));
		res2.stream().filter(temp -> !feedbackGiven.contains(temp.getprogramCourseId().getProgramId())).forEach(temp -> {
			ProgramDTO prgTrn = programService.getprogramById(temp.getprogramCourseId().getProgramId());
			String trainer = trainerProgramService.getProgramTrainer(temp.getprogramCourseId().getProgramId()+"-"+tempBatch).getTrainerProgramId().getTrainerId();
			System.out.println("TRAINER --------------->"+trainer);
			if(trainer!=null){
				result.add(new AvailableProgramsDTO() {
					
					@Override
					public String getTrainerName() {
						return trainerService.gettrainerById(trainer).getTrainerName();
					}
					
					@Override
					public String getTrainerId() {
						return trainer;
					}
					
					@Override
					public Date getStartDate() {
						return temp.getStartdate();
					}
					
					@Override
					public String getProgramName() {
						return prgTrn.getProgramName();
					}
					
					@Override
					public String getProgramId() {
						return prgTrn.getProgramId();
					}
					
					@Override
					public Date getEndDate() {
						return temp.getEnddate();
					}
				}); 
			}
		});
		return result;
	}

}
