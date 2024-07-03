package com.hbn.studentmvcservlet.repositories;

import com.hbn.studentmvcservlet.models.Classroom;

import java.util.List;

public interface IClassroomRepository {
    List<Classroom> findAll();
}
