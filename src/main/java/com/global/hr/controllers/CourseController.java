package com.global.hr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.global.hr.models.Courses;
import com.global.hr.services.CourseService;
import org.springframework.ui.Model;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "index";
    }

    @GetMapping("/showNewCourseForm")
    public String showNewCourseForm(Model model) {
        Courses course = new Courses();
        model.addAttribute("course", course);
        return "course-form";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Courses course) {
        courseService.saveCourse(course);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        Courses course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "course-form";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable(value = "id") Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/";
    }
}
