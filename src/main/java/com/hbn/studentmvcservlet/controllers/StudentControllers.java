package com.hbn.studentmvcservlet.controllers;

import com.hbn.studentmvcservlet.dto.StudentDTO;
import com.hbn.studentmvcservlet.models.Classroom;
import com.hbn.studentmvcservlet.models.Student;
import com.hbn.studentmvcservlet.services.IClassroomService;
import com.hbn.studentmvcservlet.services.IStudentService;
import com.hbn.studentmvcservlet.services.impl.ClassroomService;
import com.hbn.studentmvcservlet.services.impl.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="StudentControllers",value="/student")
public class StudentControllers extends HttpServlet {
    private static IStudentService studentService = new StudentService();
    private static IClassroomService classroomService = new ClassroomService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action==null){
            action="";
        }
        switch(action){
            case "create":
                List<Classroom> classrooms = classroomService.findAll();
                req.setAttribute("classrooms", classrooms);
                req.getRequestDispatcher("/student/create.jsp").forward(req, resp);
                break;
            case "edit":
                Long id = Long.parseLong(req.getParameter("id"));
                Student student = studentService.findById(id);
                if(student!=null){
                    req.setAttribute("student", student);
                    req.getRequestDispatcher("/student/edit.jsp").forward(req, resp);
                }else{
                    req.getRequestDispatcher("/student/error-404.jsp").forward(req, resp);
                }
                break;
            default:
                List<StudentDTO> students = studentService.findAll();
                req.setAttribute("students", students);
                req.getRequestDispatcher("/student/list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                String name = req.getParameter("name");
                String address = req.getParameter("address");
                Float point = Float.parseFloat( req.getParameter("point"));
                Long id_class = Long.parseLong(req.getParameter("classroom"));
                Student student = new Student(name,address,point,id_class);
                studentService.save(student);
                resp.sendRedirect("/student");
                break;
            case "delete":
                Long id = Long.parseLong(req.getParameter("id"));
                Boolean isDelete = studentService.deleteById(id);
                if(isDelete){
                    resp.sendRedirect("/student");
                }else{
                    req.setAttribute("message","Xóa không thành công");
                    List<StudentDTO> students = studentService.findAll();
                    req.setAttribute("students", students);
                    req.getRequestDispatcher("/student/list.jsp").forward(req, resp);
                }
                break;
            case "edit":
                Long idEdit =Long.parseLong(req.getParameter("id"));
                String nameEdit = req.getParameter("name");
                String addressEdit = req.getParameter("address");
                Float pointEdit = Float.parseFloat(req.getParameter("point"));
                Student studentEdit = new Student(nameEdit,addressEdit,pointEdit);
                studentService.updateById(idEdit,studentEdit);
                resp.sendRedirect("/student");
                break;
            case "search":
                String searchName = req.getParameter("searchName");
                List<Student> students = studentService.searchByName(searchName);
                req.setAttribute("students", students);
                req.getRequestDispatcher("/student/list.jsp").forward(req, resp);
                break;
        }
    }
}
