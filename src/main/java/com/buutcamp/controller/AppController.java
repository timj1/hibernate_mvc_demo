package com.buutcamp.controller;

import com.buutcamp.dao.StudentDAO;
import com.buutcamp.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

    //need a reference to the DAO
    @Autowired
    private StudentDAO studentDAO;

    //use getStudents method, and log out the result

    //create method to serve homepage when called.
    //@RequestMapping("/", method= RequestMethod.GET)
    @GetMapping("/")
    public String frontPageGET(Model model) {
        List<Student> students = studentDAO.getStudents();

        for (Student s : students) {

            System.out.println(s);

        }
        model.addAttribute("students", students);
        model.addAttribute("student", new Student());

        return "homepage";
    }

    //@RequestMapping("/saveStudent", method= RequestMethod.POST)
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student, Model model){

        studentDAO.saveStudent(student);

        return "redirect:/";
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId") int id) {

        studentDAO.deleteStudent(id);
        return "redirect:/";
    }

}
