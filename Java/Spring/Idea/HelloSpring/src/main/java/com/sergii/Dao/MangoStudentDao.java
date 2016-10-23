package com.sergii.Dao;

import com.sergii.Entity.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by sergii on 23.10.16.
 */
@Repository
@Qualifier( "mangoData" )
public class MangoStudentDao implements IStudentDao {
    @Override
    public Collection<Student> getAllStudents() {
        return null;
    }

    @Override
    public Student getStudentById(int id) {
        return null;
    }

    @Override
    public Student updateStudent(Student student) {
        return null;
    }
}
