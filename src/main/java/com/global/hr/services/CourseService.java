package com.global.hr.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.global.hr.models.Courses;
import com.global.hr.repositories.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Courses> getAllCourses() {
        return courseRepository.findAll();
    }

    public void saveCourse(Courses course) {
        courseRepository.save(course);
    }

    public Courses getCourseById(Long id) {
        Optional<Courses> optional = courseRepository.findById(id);
        Courses course = null;
        if (optional.isPresent()) {
            course = optional.get();
        } else {
            throw new RuntimeException("Course not found for id: " + id);
        }
        return course;
    }

    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }
}
