package com.driver;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public void addStudent(Student student)
    {
        studentRepository.addStudentDb(student);
    }
    public void addTeacher(Teacher teacher)
    {
        studentRepository.addTeacherDb(teacher);
    }
    public Student getStudentByName(String name)
    {
        Student student = studentRepository.getStudentByNameFromDb(name);
        return student;
    }
    public Teacher getTeacherByName(String name)
    {
        Teacher teacher = studentRepository.getTeacherByNameDb(name);
        return teacher;
    }
    public List<String> getAllstudents()
    {
        List<String> allStudentlist = studentRepository.getAllStudentsFromDb();
        return allStudentlist;
    }
    public void studentTeacherPair(String studentname,String teachername)
    {
        studentRepository.teacherStudentPairDb(studentname,teachername);
        return;
    }
    public List<String> studentListOfTeacher(String name)
    {
        return studentRepository.getStudentListForteacher(name);
    }

    public void deleteTeacher(String name)
    {
        studentRepository.deleteTeacherDb(name);
    }
    public void deleteallTeacher()
    {
        studentRepository.deleteAllteacherFromDb();
    }
}
