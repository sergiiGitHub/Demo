package com.sergii.Service;

import com.sergii.Dao.IStudentDao;
import com.sergii.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by sergii on 23.10.16.
 */

@Service
public class StudentService {

    @Autowired
    @Qualifier("fakeData")
    private IStudentDao mStudentDao;

    public Collection< Student > getAllStudents(){
        return mStudentDao.getAllStudents();
    }

    public Student getStudentById(int id) {
        return mStudentDao.getStudentById(id);
    }

    public Student updateStudent(Student student) {
        return mStudentDao.updateStudent( student );
    }
}
