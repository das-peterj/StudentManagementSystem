package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @POST
    public Response createStudent(Student student) {
        studentService.createStudent(student);
        String errMsgRegistrationFailed = "{\"Error\": \"Registration Failed\"}";

        if(student == null) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(errMsgRegistrationFailed)
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
        return Response.ok(student, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateStudentFirstName(@PathParam("id") Long id, @QueryParam("firstname") String firstName) {
        Student findStudent = studentService.findStudentById(id);
        String errMsgStudentNotFound = "{\"Error\": \"No student found with id " + id + "\"}";

        if (findStudent == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(errMsgStudentNotFound)
                    .type(MediaType.APPLICATION_JSON).build());
        }
        Student updatedStudent = studentService.updateStudentFirstName(id, firstName);
        return Response.ok(updatedStudent, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("{id}")
    @PUT
    public Response updateStudent(@PathParam("id") Long id, Student student) {
        Student changeStudent = studentService.findStudentById(id);
        String errMsgStudentNotFound = "{\"Error\": \"No student found with id " + id + "\"}";

        if (changeStudent == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(errMsgStudentNotFound)
                    .type(MediaType.APPLICATION_JSON).build());
        }
        changeStudent = studentService.updateStudent(id, student);
        return Response.ok(changeStudent, MediaType.APPLICATION_JSON_TYPE).build();
    }


    @GET
    public Response getAllStudents() {
        List<Student> foundStudents = studentService.getAllStudents();
        String errMsgStudentsNotFound = "{\"Error\": \"No students found\"}";

        if (foundStudents == null || foundStudents.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(errMsgStudentsNotFound)
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
        return Response.ok(foundStudents).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        if (id == null || id == 0) {
            return Response.serverError().entity("ID cannot be blank").build();
        }
        Student foundStudent = studentService.findStudentById(id);
        String errMsgStudentNotFound = "{\"Error\": \"No student found with id " + id + "\"}";

        if(foundStudent == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(errMsgStudentNotFound)
                    .type(MediaType.APPLICATION_JSON).build());

        }
        return Response.ok(foundStudent, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("/lastname")
    @GET
    public Response getStudentByLastName(@QueryParam("lastname") String lastName) {
        List<Student> foundStudent = studentService.findStudentByLastName(lastName);
        String errMsgLastNameNotFound = "{\"Error\": \"No student found with lastname " + lastName + "\"}";

        if(foundStudent == null || foundStudent.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(errMsgLastNameNotFound)
                    .type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundStudent).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id){
                Student foundStudent = studentService.doesStudentExist(id);
                String errMsgStudentNotFound = "{\"Error\": \"No student found with id " + id + "\"}";

                if(foundStudent == null) {
                    throw new WebApplicationException(Response.status(Response.Status.GONE)
                            .entity(errMsgStudentNotFound)
                            .type(MediaType.APPLICATION_JSON_TYPE).build());
                }
                studentService.deleteStudentById(id);
                return Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON_TYPE).build();
            }

    }

