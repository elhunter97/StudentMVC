package com.hbn.studentmvcservlet.services;

import com.hbn.studentmvcservlet.models.Classroom;

import java.util.List;

public interface IClassroomService {
    List<Classroom> findAll();
}
