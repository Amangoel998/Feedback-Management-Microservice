package com.cg.feedback.user.feignServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.feedback.user.dao.AdminDAO;
import com.cg.feedback.user.dto.AdminDTO;
import com.cg.feedback.user.dto.LoginCredentials;
import com.cg.feedback.user.dto.LoginResponse;
import com.cg.feedback.user.dto.StudentDTO;
import com.cg.feedback.user.dto.TrainerDTO;
import com.cg.feedback.user.exceptions.CustomException;

@Service
public class UserServiceImpl implements UserService {
	
    private StudentServiceFeign studentService;
    private TrainerServiceFeign trainerService;
    private AdminDAO admDao;

    @Autowired
    public UserServiceImpl(	StudentServiceFeign studentService,
						    TrainerServiceFeign trainerService,
						    AdminDAO admDAO) throws CustomException {
        this.studentService = studentService;
        this.trainerService = trainerService;
        this.admDao = admDAO;
    }

	@Override
	public LoginResponse getUserInfo(LoginCredentials credentials) throws CustomException{
        switch (credentials.getRole().toLowerCase()) {
            case "student":
                StudentDTO student = studentService.getStudentById(credentials.getId());
                if (student == null)
                    throw new CustomException("Invalid Data Store");
                return new LoginResponse(student.getStudentId(), "student", student.getStudentName(),
                        student.getStudentEmail());
            case "trainer":
                TrainerDTO trainer = trainerService.gettrainerById(credentials.getId());
                if (trainer == null)
                    throw new CustomException("Invalid Data Store");
                return new LoginResponse(trainer.getTrainerId(), "trainer", trainer.getTrainerName(),
                        trainer.getTrainerEmail());
            case "admin":
            System.out.println(credentials);
                AdminDTO admin = admDao.findById(credentials.getId()).get();
                if (admin == null)
                    throw new CustomException("Invalid Data Store");
                return new LoginResponse(admin.getAdminId(), "admin", admin.getAdminName(), admin.getAdminEmail());
            default:
                throw new CustomException("Invalid Data");
        }
    }
}
