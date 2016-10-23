package com.sergii.Dao;

import com.sergii.Entity.Student;

import java.util.Collection;

/**
 * Created by sergii on 23.10.16.
 */
public interface IStudentDao {
    Collection< Student > getAllStudents();

    Student getStudentById(int id);

    Student updateStudent(Student student);
}
