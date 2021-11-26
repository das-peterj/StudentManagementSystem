package se.iths.util;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateSampleData() {
        Student student1 = new Student("Peter", "Jorgensen", "peterj@iths.se", "0722410429");
        Student student2 = new Student("Marcus", "Peters", "makkan@yahoo.com", "0738275912");
        Student student3 = new Student("Niklas", "Bern", "nikkan@gmail.com", "0738811002");

        Subject subject1 = new Subject("Matte");
        Subject subject2 = new Subject("Idrott");
        Subject subject3 = new Subject("Teknik");

        Teacher teacher1 = new Teacher("Pontus", "Redig", "pontan@iths.se", "0993152952");
        Teacher teacher2 = new Teacher("Elon", "Musk", "definitelynotelonmusk@outlook,com", "6666942000");

        student1.addSubject(subject1);
        student1.addSubject(subject3);
        student2.addSubject(subject2);
        student2.addSubject(subject1);
        student3.addSubject(subject2);
        student3.addSubject(subject3);

        teacher1.addSubject(subject1);
        teacher1.addSubject(subject2);
        teacher2.addSubject(subject3);

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.persist(teacher1);
        entityManager.persist(teacher2);
    }
}
