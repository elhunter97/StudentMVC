package com.hbn.studentmvcservlet.repositories.impl;

import com.hbn.studentmvcservlet.dto.StudentDTO;
import com.hbn.studentmvcservlet.models.Classroom;
import com.hbn.studentmvcservlet.repositories.IClassroomRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassroomRepository implements IClassroomRepository {
    @Override
    public List<Classroom> findAll() {
        List<Classroom> classrooms = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().
                    prepareStatement("select * from studentmanage.classroom");
            ResultSet resultSet = preparedStatement.executeQuery();
            Long id_class;
            String name_class;
            while (resultSet.next()) {
                id_class = resultSet.getLong("id_class");
                name_class = resultSet.getString("name_class");
                classrooms.add(new Classroom(id_class, name_class));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classrooms;
    }
}
