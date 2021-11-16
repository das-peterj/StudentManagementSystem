package se.iths.service;


import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {
    @PersistenceContext
    EntityManager entityManager;

    public Student createStudent(Student student) {
        entityManager.persist(student);
        return student;
    }

    public Student updateStudent(Student student) {
        entityManager.merge(student);
        return student;
    }

    public Student findStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT s from Student s", Student.class).getResultList();
    }

    public Student updateStudentFirstName(long id, String name) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setFirstName(name);
        return entityManager.merge(foundStudent);
    }

    public Student updateStudentLastName(long id, String name) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setLastName(name);
        return entityManager.merge(foundStudent);
    }

    public void deleteStudentById(Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        entityManager.remove(foundStudent);
    }



}
