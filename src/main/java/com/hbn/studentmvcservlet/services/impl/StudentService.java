package com.hbn.studentmvcservlet.services.impl;

import com.hbn.studentmvcservlet.dto.StudentDTO;
import com.hbn.studentmvcservlet.models.Student;
import com.hbn.studentmvcservlet.repositories.IStudentRepository;
import com.hbn.studentmvcservlet.repositories.impl.StudentRepository;
import com.hbn.studentmvcservlet.services.IStudentService;

import java.util.List;

public class StudentService implements IStudentService {
    private static IStudentRepository studentRepository = new StudentRepository();
    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);

    }

    @Override
    public Boolean deleteById(Long id) {
        return studentRepository.deleteById(id);
    }

    @Override
    public void updateById(Long idEdit, Student studentEdit) {
        studentRepository.updateById(idEdit,studentEdit);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> searchByName(String searchName) {
        return studentRepository.searchByName(searchName);
    }


}
