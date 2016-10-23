package com.sergii.Controller;

import com.sergii.Entity.Student;
import com.sergii.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by sergii on 23.10.16.
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService mService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection< Student > getAllStudents(){
        return mService.getAllStudents();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStudent( @PathVariable("id") int id){
        return mService.getStudentById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent(@RequestBody Student student) {
        return mService.updateStudent(student);
    }

}
