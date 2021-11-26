package se.iths.service;

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
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public Teacher createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
        return teacher;
    }

    public Teacher updateTeacher(Long id, Teacher teacher) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);

        foundTeacher.setFirstName(teacher.getFirstName());
        foundTeacher.setLastName(teacher.getLastName());
        foundTeacher.setEmail(teacher.getEmail());
        foundTeacher.setPhoneNumber(teacher.getPhoneNumber());

        return entityManager.merge(foundTeacher);
    }

    public Teacher findTeacherById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    public List<Teacher> getAllTeachers() {
        return entityManager.createQuery(
                "SELECT t from Teacher t", Teacher.class) .getResultList();

    }

    public void deleteTeacher(Long id) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        entityManager.remove(foundTeacher);
    }


}
