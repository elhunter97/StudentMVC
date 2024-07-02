package com.hbn.studentmvcservlet.repositories.impl;

import com.hbn.studentmvcservlet.models.Student;
import com.hbn.studentmvcservlet.repositories.IStudentRepository;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    private static List<Student> students;

    static {
        students = new ArrayList<>();
        students.add(new Student(1L,"haiTT", "QN", 10.0f));
        students.add(new Student(2L,"Bảo Ngọc", "HN", 8.0f));
        students.add(new Student(3L,"Bảo Kỳ", "DN", 6.0f));
        students.add(new Student(5L,"Cook", "Bàn ăn", 2f));
    }
    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public void save(Student student) {
        student.setId(students.get(students.size()-1).getId()+1);
        students.add(student);
    }

    @Override
    public Boolean deleteById(Long id) {
        for(Student student : students){
            if(student.getId().equals(id)){
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateById(Long idEdit, Student studentEdit) {
        Student student = findById(idEdit);
        student.setName(studentEdit.getName());
        student.setAddress(studentEdit.getAddress());
        student.setPoint(studentEdit.getPoint());
    }

    public Student findById(Long idEdit) {
        for(Student student : students){
            if(student.getId().equals(idEdit)){
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> searchByName(String searchName) {
        List<Student> studentSearch = new ArrayList<>();
        for(Student student : students){
            if(student.getName().contains(searchName)){
                studentSearch.add(student);
            }
        }
        return studentSearch;
    }
}
