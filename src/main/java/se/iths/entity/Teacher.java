package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min = 3)
    private String firstName;

    @NotEmpty
    @Size(min = 3)
    private String lastName;

    @NotEmpty
    @Size(min = 6)
    private String email;

    private String phoneNumber;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.PERSIST)
    private Set<Subject> subjects = new HashSet<>();

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        subjects.add(subject);
        subject.setTeacher(this);
    }

    public void deleteSubject(Subject subject) {
        this.subjects.remove(subject);
        subjects.remove(subject);
        subject.setTeacher(this);
    }


    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
