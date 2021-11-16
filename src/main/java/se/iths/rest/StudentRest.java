package se.iths.rest;

import se.iths.entity.Student;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class StudentRest {
    @PersistenceContext
    EntityManager entityManager;

    public Student createStudent(Student student) {
        entityManager.persist(student);
        return student;
    }

}
