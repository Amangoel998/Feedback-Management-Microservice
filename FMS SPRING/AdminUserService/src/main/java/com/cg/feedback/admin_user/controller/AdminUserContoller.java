package com.cg.feedback.admin_user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.feedback.admin_user.dto.*;
import com.cg.feedback.admin_user.exceptions.CustomException;
import com.cg.feedback.admin_user.feignServices.AdminUserService;
import com.cg.feedback.admin_user.feignServices.BatchCourseServiceFeign;
import com.cg.feedback.admin_user.feignServices.CourseServiceFeign;
import com.cg.feedback.admin_user.feignServices.FeedbackServiceFeign;
import com.cg.feedback.admin_user.feignServices.LoginServiceFeign;
import com.cg.feedback.admin_user.feignServices.ProgramCourseServiceFeign;
import com.cg.feedback.admin_user.feignServices.ProgramServiceFeign;
import com.cg.feedback.admin_user.feignServices.StudentServiceFeign;
import com.cg.feedback.admin_user.feignServices.TrainerProgramServiceFeign;
import com.cg.feedback.admin_user.feignServices.TrainerServiceFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/api/admin")
@Api("admin-controller")
public class AdminUserContoller {

    private AdminUserService admService;
    private BatchCourseServiceFeign batchCourseService;
    private CourseServiceFeign courseService;
    private FeedbackServiceFeign feedbackService;
    private LoginServiceFeign loginService;
    private ProgramCourseServiceFeign programCourseService;
    private ProgramServiceFeign programService;
    private StudentServiceFeign studentService;
    private TrainerProgramServiceFeign trainerProgramService;
    private TrainerServiceFeign trainerService;

    @Autowired
    public AdminUserContoller(BatchCourseServiceFeign batchCourseService, CourseServiceFeign courseService,
            FeedbackServiceFeign feedbackService, LoginServiceFeign loginService,
            ProgramCourseServiceFeign programCourseService, ProgramServiceFeign programService,
            StudentServiceFeign studentService, TrainerProgramServiceFeign trainerProgramService,
            TrainerServiceFeign trainerService, AdminUserService admService) throws CustomException {
        this.batchCourseService = batchCourseService;
        this.courseService = courseService;
        this.feedbackService = feedbackService;
        this.loginService = loginService;
        this.programCourseService = programCourseService;
        this.programService = programService;
        this.studentService = studentService;
        this.trainerProgramService = trainerProgramService;
        this.trainerService = trainerService;
        this.admService = admService;
    }

    // ------------------ Admin Requests---------------------
    // ----------View Feedback Reports-------------
    @GetMapping(value = "/programReport", produces = "application/json")
    public ResponseEntity<ReportDTO> getProgramFeedback(@RequestParam("programId") String programId)
            throws CustomException {
        if (programId == null || programId.isEmpty())
            throw new CustomException("Invalid Request");
        ReportDTO result = new ReportDTO(feedbackService.getAllFeedbacksForProgram(programId));
        return new ResponseEntity<ReportDTO>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/trainerReport", produces = "application/json")
    public ResponseEntity<ReportDTO> getTrainerFeedback(@RequestParam("trainerId") String trainerId)
            throws CustomException {
        if (trainerId == null || trainerId.isEmpty())
            throw new CustomException("Invalid Request");
        ReportDTO result = new ReportDTO(feedbackService.getAllFeedbacksForTrainer(trainerId));
        return new ResponseEntity<ReportDTO>(result, HttpStatus.OK);
    }

    // ---------------View Feedback Defaulters----------------
    @GetMapping(value = "/programFeedbackDefaulters", produces = "application/json")
    public ResponseEntity<List<ProgramDefaulters>> getDefaultersByProgram(@RequestParam("programId") String programId)
            throws CustomException {
        if (programId == null || programId.isEmpty())
            throw new CustomException("Invalid Request");
        List<ProgramDefaulters> result = admService.viewDefaultersByProgram(programId);
        return new ResponseEntity<List<ProgramDefaulters>>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/trainerFeedbackDefaulters", produces = "application/json")
    public ResponseEntity<List<TrainerDefaulters>> getDefaultersByTrainer(@RequestParam("trainerId") String trainerId)
            throws CustomException {
        if (trainerId == null || trainerId.isEmpty())
            throw new CustomException("Invalid Request");
        List<TrainerDefaulters> result = admService.viewDefaultersByTrainer(trainerId);
        return new ResponseEntity<List<TrainerDefaulters>>(result, HttpStatus.OK);
    }

    // ---------------Get list of Items----------------
    @GetMapping(value = "/batches", produces = "application/json")
    public ResponseEntity<List<BatchCourseDTO>> getBatches() throws CustomException {
        return new ResponseEntity<List<BatchCourseDTO>>(batchCourseService.getBatches(), HttpStatus.OK);
    }

    @GetMapping(value = "/courses", produces = "application/json")
    public ResponseEntity<List<CourseDTO>> getCourses() throws CustomException {
        return new ResponseEntity<List<CourseDTO>>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping(value = "/programs", produces = "application/json")
    public ResponseEntity<List<ProgramDTO>> getPrograms() throws CustomException {
        return new ResponseEntity<List<ProgramDTO>>(programService.getAllprograms(), HttpStatus.OK);
    }

    @GetMapping(value = "/trainers", produces = "application/json")
    public ResponseEntity<List<TrainerDTO>> getTrainers() throws CustomException {
        return new ResponseEntity<List<TrainerDTO>>(trainerService.getAlltrainers(), HttpStatus.OK);
    }

    @GetMapping(value = "/students", produces = "application/json")
    public ResponseEntity<List<StudentDTO>> getStudents() throws CustomException {
        return new ResponseEntity<List<StudentDTO>>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping(value = "/coursePrograms", produces = "application/json")
    public ResponseEntity<List<ProgramCourseDTO>> getCoursePrograms() throws CustomException {
        return new ResponseEntity<List<ProgramCourseDTO>>(programCourseService.getCoursePrograms(), HttpStatus.OK);
    }

    @GetMapping(value = "/programTrainers", produces = "application/json")
    public ResponseEntity<List<TrainerProgramDTO>> getProgramTrainers() throws CustomException {
        return new ResponseEntity<List<TrainerProgramDTO>>(trainerProgramService.getProgramTrainers(), HttpStatus.OK);
    }
    // ----------------Admin POST methods-------------------

    @HystrixCommand(fallbackMethod = "addBatchFail", commandKey = "addCourse", groupKey = "AdminUser", ignoreExceptions = CustomException.class)
    @PostMapping(value = "/addBatch", produces = "application/json", consumes = "application/json")
    public ResponseEntity<BatchCourseDTO> addBatch(@RequestBody BatchCourseDTO batch) throws CustomException {
        if (batch == null)
            throw new CustomException("Invalid Request");
        BatchCourseDTO bat;
        try {
            bat = batchCourseService.addBatch(batch);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

        return new ResponseEntity<BatchCourseDTO>(bat, HttpStatus.CREATED);
    }

    public ResponseEntity<BatchCourseDTO> addBatchFail(BatchCourseDTO batch) throws CustomException {
        return new ResponseEntity<BatchCourseDTO>(batch, HttpStatus.BAD_GATEWAY);
    }

    @HystrixCommand(fallbackMethod = "addProgramFail", commandKey = "addCourse", groupKey = "AdminUser", ignoreExceptions = CustomException.class)
    @PostMapping(value = "/addProgram", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ProgramDTO> addProgram(@RequestBody ProgramDTO program) throws CustomException {
        if (program == null)
            throw new CustomException("Invalid Request");
        ProgramDTO tempProgram;
        try {
            tempProgram = programService.addprogram(program);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return new ResponseEntity<ProgramDTO>(tempProgram, HttpStatus.CREATED);
    }

    public ResponseEntity<ProgramDTO> addProgramFail(@RequestBody ProgramDTO program) throws CustomException {
        return new ResponseEntity<ProgramDTO>(program, HttpStatus.BAD_GATEWAY);
    }

    @HystrixCommand(fallbackMethod = "addCourseFail", commandKey = "addCourse", groupKey = "AdminUser", ignoreExceptions = CustomException.class)
    @PostMapping(value = "/addCourse", produces = "application/json", consumes = "application/json")
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO course) throws CustomException {
        if (course == null)
            throw new CustomException("Invalid Request");
        CourseDTO tempCourse;
        try {
            tempCourse = courseService.addCourse(course);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return new ResponseEntity<CourseDTO>(tempCourse, HttpStatus.CREATED);
    }

    public ResponseEntity<CourseDTO> addCourseFail(CourseDTO course) throws CustomException {
        return new ResponseEntity<CourseDTO>(course, HttpStatus.BAD_GATEWAY);
    }

    @HystrixCommand(fallbackMethod = "addTrainerFail", commandKey = "addTrainer", groupKey = "AdminUser", ignoreExceptions = CustomException.class)
    @PostMapping(value = "/addTrainer", produces = "application/json", consumes = "application/json")
    public ResponseEntity<TrainerDTO> addTrainer(@RequestBody NewTrainerDTO trainer) throws CustomException {
        if (trainer == null)
            throw new CustomException("Invalid Request");
        TrainerDTO trn;
        try {
            trn = new TrainerDTO(trainer);
            trn = trainerService.addtrainer(trn);
            LoginCredentials credentials = new LoginCredentials(trainer.getTrainerId(), trainer.getTrainerPass(),
                    "trainer");
            LoginCredentials log = loginService.newCredentials(credentials);
            if(log == null)
                throw new CustomException("Couldn't Create Login Credentials");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CustomException(e.getMessage());
        }
        return new ResponseEntity<TrainerDTO>(trn, HttpStatus.CREATED);
    }

    public ResponseEntity<TrainerDTO> addTrainerFail(NewTrainerDTO trainer) throws CustomException {
        TrainerDTO trn = new TrainerDTO(trainer);
        return new ResponseEntity<TrainerDTO>(trn, HttpStatus.BAD_GATEWAY);
    }

    @HystrixCommand(fallbackMethod = "addStudentFail", commandKey = "addStudent", groupKey = "AdminUser", ignoreExceptions = CustomException.class)
    @PostMapping(value = "/addStudent", produces = "application/json", consumes = "application/json")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody NewStudentDTO student) throws CustomException {
        if (student == null)
            throw new CustomException("Invalid Request");
        StudentDTO std;
        try {
            std = new StudentDTO(student);
            std = studentService.addStudent(std);
            LoginCredentials newLogin = new LoginCredentials(student.getStudentId(), student.getStudentPass(),
                    "student");
            LoginCredentials login = loginService.newCredentials(newLogin);
            if( login == null)
                throw new CustomException("Couldn't create Credentials");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return new ResponseEntity<StudentDTO>(std, HttpStatus.CREATED);

    }

    public ResponseEntity<StudentDTO> addStudentFail(NewStudentDTO student) throws CustomException {
        return new ResponseEntity<StudentDTO>(new StudentDTO(student), HttpStatus.BAD_GATEWAY);
    }

    // ----------Add Program to Course----------------
    @PostMapping(value = "/addProgramToCourse", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ProgramCourseDTO> assignCoursePrograms(@RequestBody ProgramCourseDTO courseprogram)
            throws CustomException {
        if (courseprogram == null)
            throw new CustomException("Invalid Request");
        return new ResponseEntity<ProgramCourseDTO>(programCourseService.assignCoursePrograms(courseprogram),
                HttpStatus.OK);
    }

    // -------------Add Trainer to Program
    @PostMapping(value = "/addTrainerToProgram", produces = "application/json", consumes = "application/json")
    public ResponseEntity<TrainerProgramDTO> assignProgramTrainers(@RequestBody TrainerProgramDTO programtrainer)
            throws CustomException {
        if (programtrainer == null)
            throw new CustomException("Invalid Request");
        return new ResponseEntity<TrainerProgramDTO>(trainerProgramService.assignProgramTrainers(programtrainer),
                HttpStatus.OK);
    }

    // -------------PUT Trainer Skill
    @PutMapping(value = "/maintainTrainerSkills", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> updateTrainerSkill(@RequestBody TrainerSkillDTO trainerSkills)
            throws CustomException {
        if (trainerSkills == null)
            throw new CustomException("Invalid Request");
        return new ResponseEntity<String>(trainerService.updateTrainerSkill(trainerSkills), HttpStatus.OK);
    }

    // ---------------Admin Delete Methods--------------------
    @DeleteMapping(value = "/removeStudent", produces = "application/json")
    public ResponseEntity<String> removeStudent(@RequestParam("studentId") String studentId) throws CustomException {
        if (studentId == null || studentId.isEmpty())
            throw new CustomException("Invalid Request");
        studentService.delteStudentById(studentId);
        loginService.deleteCredentials(studentId);
        return new ResponseEntity<String>("Student Deleted", HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeBatch", produces = "application/json")
    public ResponseEntity<String> removeBatch(@RequestParam("batch") String batch) throws CustomException {
        if (batch == null || batch.isEmpty())
            throw new CustomException("Invalid Request");
        batchCourseService.removeBatch(batch);
        return new ResponseEntity<String>("Batch Deleted", HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeProgram", produces = "application/json")
    public ResponseEntity<String> removeProgram(@RequestParam("programId") String programId) throws CustomException {
        if (programId == null || programId.isEmpty())
            throw new CustomException("Invalid Request");
        programService.delteprogramById(programId);
        programCourseService.removeByProgram(programId);
        trainerProgramService.removeByProgram(programId);
        return new ResponseEntity<String>("Program Deleted", HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeTrainer", produces = "application/json")
    public ResponseEntity<String> removeTrainer(@RequestParam("trainerId") String trainerId) throws CustomException {
        if (trainerId == null || trainerId.isEmpty())
            throw new CustomException("Invalid Request");
        trainerService.deltetrainerById(trainerId);
        trainerProgramService.removeByTrainer(trainerId);
        loginService.deleteCredentials(trainerId);
        return new ResponseEntity<String>("Trainer Deleted", HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeCourse", produces = "application/json")
    public ResponseEntity<String> removeCourse(@RequestParam("courseId") String courseId) throws CustomException {
        if (courseId == null || courseId.isEmpty())
            throw new CustomException("Invalid Request");
        courseService.delteCourseById(courseId);
        programCourseService.removeByCourse(courseId);
        batchCourseService.removeByCourse(courseId);
        return new ResponseEntity<String>("Course Deleted", HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeProgramFromCourse", produces = "application/json")
    public ResponseEntity<String> removeProgramInCourse(@RequestParam("programcourseId") String programcourseId)
            throws CustomException {
        if (programcourseId == null || programcourseId.isEmpty())
            throw new CustomException("Invalid Request");
        programCourseService.removeProgramInCourse(programcourseId);
        return new ResponseEntity<String>("Program removed from Course", HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeTrainerFromProgram", produces = "application/json")
    public ResponseEntity<String> removeTrainerInProgram(@RequestParam("trainerprogramId") String trainerprogramId)
            throws CustomException {
        if (trainerprogramId == null || trainerprogramId.isEmpty())
            throw new CustomException("Invalid Request");
        trainerProgramService.removeTrainerInProgram(trainerprogramId);
        return new ResponseEntity<String>("Trainer removed from Program", HttpStatus.OK);
    }
}