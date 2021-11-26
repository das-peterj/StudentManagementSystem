package se.iths.service;


import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    StudentService studentService;
    @Inject
    TeacherService teacherService;

    public Subject createSubject(Subject subject) {
        entityManager.persist(subject);
        return subject;
    }

    public Subject updateSubject(Long id, String subject) {
        Subject foundSubject = entityManager.find(Subject.class, id);

        foundSubject.setSubject(subject);
        return entityManager.merge(foundSubject);
    }

    public Subject findSubjectById(Long id) {
        return entityManager.find(Subject.class, id);
    }

    public List<Subject> getAllSubjects() {
        return entityManager.createQuery(
                "SELECT s FROM Subject s", Subject.class).getResultList();
    }

    public void deleteSubject(Long id) {
        Subject foundSubject = entityManager.find(Subject.class, id);
        entityManager.remove(foundSubject);
    }

    public Subject addTeacherToSubject(Long id, Long teacherId) {
        Subject foundSubject = findSubjectById(id);
        Teacher foundTeacher = teacherService.findTeacherById(teacherId);

        foundSubject.setTeacher(foundTeacher);
        foundTeacher.addSubject(foundSubject);

        return foundSubject;
    }

    public Subject deleteTeacherFromSubject(Long id, Long teacherId) {
        Subject foundSubject = findSubjectById(id);
        Teacher foundTeacher = teacherService.findTeacherById(teacherId);

        foundSubject.setTeacher(null);
        foundTeacher.deleteSubject(foundSubject);
        entityManager.persist(foundSubject);

        return foundSubject;
    }

    public Subject addStudentToSubject(Long id, Long studentId) {
        Subject foundSubject = findSubjectById(id);
        Student foundStudent = studentService.findStudentById(studentId);

        foundSubject.addStudent(foundStudent);
        entityManager.persist(foundSubject);

        return foundSubject;
    }

    public Subject deleteStudentFromSubject(Long id, Long studentId) {
        Subject foundSubject = findSubjectById(id);
        Student foundStudent = studentService.findStudentById(studentId);

        foundSubject.deleteStudent(foundStudent);
        entityManager.persist(foundSubject);

        return foundSubject;
    }
}
