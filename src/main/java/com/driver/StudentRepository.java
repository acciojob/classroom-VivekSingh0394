package com.driver;
import java.util.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class StudentRepository {

    List<Student> studentList=new ArrayList<>();
    List<Teacher> teacherList=new ArrayList<>();
     HashMap<String,List<String>> teacherStudentPair = new HashMap<>();
    public void addStudentDb(Student student)
    {
       String name = student.getName();
       int age = student.getAge();
       double score=student.getAverageScore();
       studentList.add(new Student(name,age,score));
    }
    public void addTeacherDb(Teacher teacher)
    {
        String name=teacher.getName();
        int numberOfStudents = teacher.getNumberOfStudents();
        int age=teacher.getAge();
        teacherList.add(new Teacher(name,numberOfStudents,age));
    }
    public Student getStudentByNameFromDb(String name)
    {
        for(int i =0 ; i < studentList.size();i++)
        {
            String nameofStudent = studentList.get(i).getName();
            if(nameofStudent.equals(name))
            {
                return studentList.get(i);
            }
        }
        return null;
    }
    public Teacher getTeacherByNameDb(String name)
    {
        for(int i =0 ; i < teacherList.size();i++)
        {
            String nameOfteacher = teacherList.get(i).getName();
            if(nameOfteacher.equals(name))
            {
                return teacherList.get(i);
            }
        }
        return null;
    }

    public List<String> getAllStudentsFromDb()
    {
        List<String> allStudents = new ArrayList<>();
        for(int i =0 ; i < studentList.size();i++)
        {
            String name = studentList.get(i).getName();
            allStudents.add(name);
        }
        return allStudents;
    }

public void teacherStudentPairDb(String studentName,String teacherName)
{
    String studentForteacher = "";
    String teacherForStudent="";
    for(int i =0 ; i < studentList.size();i++)
    {
        String nameOfStudent = studentList.get(i).getName();
        if(nameOfStudent.equals(studentName))
        {
            studentForteacher=nameOfStudent;
            break;
        }
    }
    for(int i =0 ; i < teacherList.size();i++)
    {
        String nameOfteacher = teacherList.get(i).getName();
        if(nameOfteacher.equals(teacherName))
        {
            teacherForStudent=nameOfteacher;
            break;

        }
    }

    if(teacherStudentPair.containsKey(teacherForStudent))
    {
        teacherStudentPair.get(teacherForStudent).add(studentForteacher);
        return;
    }
    else
    {
        List<String> studentsofTeacher = new ArrayList<>();
        studentsofTeacher.add(studentForteacher);
        teacherStudentPair.put(teacherForStudent,studentsofTeacher);
        return;
    }
}

public List<String> getStudentListForteacher(String teacherName)
{
     List<String> studentlistOfteacher = new ArrayList<>();
    for(String t : teacherStudentPair.keySet())
    {
        if(t.equals(teacherName))
        {
            return teacherStudentPair.get(t);
        }
    }

    return studentlistOfteacher;
}

public void deleteTeacherDb(String nameOfteacher)
{
    String teacherToBeRemoved ="";
    List<String> studentsToBeRemoved=new ArrayList<>();
    for(String t : teacherStudentPair.keySet())
    {
        if(t.equals(nameOfteacher))
        {
          teacherToBeRemoved=t; // this teacher needs to be removed from tecaherDb

          studentsToBeRemoved = teacherStudentPair.get(t); // these list of students need to be removed form studentdb
            break;

        }
    }

    // removing students from studentDb
    List<Student> removetheseStudents = new ArrayList<>();

    for(int i = 0 ; i < studentsToBeRemoved.size();i++)
    {
        for(int j =0 ; j < studentList.size();j++)
        {
            Student st= studentList.get(j);
            if(studentList.get(j).getName().equals(studentsToBeRemoved.get(i)))
            {
                studentList.remove(st);
            }
        }

    }
    // removing teacher

    for(int i =0 ; i < teacherList.size();i++)
    {
        Teacher te = teacherList.get(i);
        if(teacherToBeRemoved.equals(teacherList.get(i).getName()))
        {
            teacherList.remove(te);

        }
    }

    // removed from pair ,also needs to be removed from teacher list and its students from student list
    teacherStudentPair.remove(nameOfteacher);

}

public void deleteAllteacherFromDb()
{
    //now students of all teachers are to be removed

      //  List<String> studentsToBeRemoved=new ArrayList<>();
        for(String t : teacherStudentPair.keySet())
        {
            List<String> listStudent = teacherStudentPair.get(t); // these list of students need to be removed form studentdb
            for(int i = 0 ; i < listStudent.size();i++)
            {
                for(int j =0 ; j < studentList.size();j++)
                {
                    Student st= studentList.get(j);
                    if(studentList.get(j).getName().equals(listStudent.get(i)))
                    {
                        studentList.remove(st);
                    }
                }

            }

            }


    // all teachers removed
    teacherList=new ArrayList<>();


    // delete pair
    teacherStudentPair=new HashMap<>();


}
}
