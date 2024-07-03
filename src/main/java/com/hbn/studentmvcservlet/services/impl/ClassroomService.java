package com.hbn.studentmvcservlet.services.impl;

import com.hbn.studentmvcservlet.models.Classroom;
import com.hbn.studentmvcservlet.repositories.IClassroomRepository;
import com.hbn.studentmvcservlet.repositories.impl.ClassroomRepository;
import com.hbn.studentmvcservlet.services.IClassroomService;

import java.util.List;

public class ClassroomService implements IClassroomService {
    private IClassroomRepository classroomRepository = new ClassroomRepository();
    @Override
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }
}
