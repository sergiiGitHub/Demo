package com.sergii.Dao;

import com.sergii.Entity.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sergii on 23.10.16.
 */
@Repository
@Qualifier("fakeData")
public class FakeStudentDao implements IStudentDao {

    private static Map<Integer, Student> mStudents;

    static {
        mStudents = new HashMap<Integer, Student>(){
            {
                put( 0, new Student( 0, "Name1", "Computer Sience" ) );
                put( 1, new Student( 1, "Name2", "Computer Sience" ) );
                put( 2, new Student( 2, "Name3", "Computer Sience" ) );
                put( 3, new Student( 3, "Name4", "Computer Sience" ) );
            }
        };
    }

    @Override
    public Collection< Student > getAllStudents(){
        return mStudents.values();
    }

    @Override
    public Student getStudentById(int id) {
        return mStudents.get(id);
    }

    @Override
    public Student updateStudent(Student student) {
        return mStudents.put( student.getId(),  student );
    }
}
