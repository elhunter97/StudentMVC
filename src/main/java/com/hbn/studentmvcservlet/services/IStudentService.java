package com.hbn.studentmvcservlet.services;

import com.hbn.studentmvcservlet.models.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();

    void save(Student student);

    Boolean deleteById(Long id);


    void updateById(Long idEdit, Student studentEdit);

    Student findById(Long id);

    List<Student> searchByName(String searchName);
}
