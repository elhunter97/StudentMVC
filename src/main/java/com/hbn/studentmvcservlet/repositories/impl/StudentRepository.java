package com.hbn.studentmvcservlet.repositories.impl;

import com.hbn.studentmvcservlet.dto.StudentDTO;
import com.hbn.studentmvcservlet.models.Student;
import com.hbn.studentmvcservlet.repositories.IStudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public List<StudentDTO> findAll() {
        List<StudentDTO> students = new ArrayList<>();
        try {
            PreparedStatement  ps = BaseRepository.getConnection().
                    prepareStatement("select id,student.name,address,point,classroom.name_class from student join classroom on student.id_class=classroom.id_class");
            ResultSet resultSet = ps.executeQuery();
            Long id;
            String name;
            String address;
            Float point;
            String nameClass;
            while (resultSet.next()) {
                 id = resultSet.getLong("id");
                 name = resultSet.getString("name");
                 address = resultSet.getString("address");
                 point = resultSet.getFloat("point");
                 nameClass = resultSet.getString("name_class");
                students.add(new StudentDTO(id,name,address,point,nameClass));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public void save(Student student) {
        try {
            PreparedStatement  ps = BaseRepository.getConnection().prepareStatement("insert into student(name,address,point,id_class) values(?,?,?,?)");
            ps.setString(1, student.getName());
            ps.setString(2, student.getAddress());
            ps.setFloat(3, student.getPoint());
            ps.setLong(4, student.getId_class());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        student.setId(students.get(students.size()-1).getId()+1);
        students.add(student);
    }

    @Override
    public Boolean deleteById(Long id) {
        boolean isDelete;
        PreparedStatement  ps = null;
        try {
            ps = BaseRepository.getConnection().prepareStatement("delete from student where id=?;");
            ps.setLong(1,id);
            isDelete = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isDelete;
    }

    @Override
    public void updateById(Long idEdit, Student studentEdit) {
            PreparedStatement  ps = null;
            try{
                ps = BaseRepository.getConnection().prepareStatement("update student set name = ?, address=?,point=? where id=?");
                ps.setString(1,studentEdit.getName());
                ps.setString(2,studentEdit.getAddress());
                ps.setFloat(3,studentEdit.getPoint());
                ps.setLong(4,idEdit);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    public Student findById(Long idEdit) {
        Student student = null;
        PreparedStatement  ps = null;
        try {
            ps = BaseRepository.getConnection().prepareStatement("select * from student where id=?");
            ps.setLong(1,idEdit);
            ResultSet resultSet = ps.executeQuery();
            Long id;
            String name;
            String address;
            Float point;
            while (resultSet.next()) {
                id = resultSet.getLong("id");
                name = resultSet.getString("name");
                address = resultSet.getString("address");
                point = resultSet.getFloat("point");
                student = new Student(id,name,address,point);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public List<Student> searchByName(String searchName) {
        List<Student> studentSearch = new ArrayList<>();
        try {
            PreparedStatement  ps = BaseRepository.getConnection().prepareStatement("select * from student  where name like concat ('%',?,'%')");
            ps.setString(1,searchName);
            ResultSet resultSet = ps.executeQuery();
            Long id;
            String name;
            String address;
            Float point;
            while (resultSet.next()) {
                id = resultSet.getLong("id");
                name = resultSet.getString("name");
                address = resultSet.getString("address");
                point = resultSet.getFloat("point");
                studentSearch.add(new Student(id,name,address,point));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentSearch;
    }
}
