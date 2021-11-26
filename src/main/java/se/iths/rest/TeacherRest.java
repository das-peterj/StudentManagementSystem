package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.exception.BeenRemovedException;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @POST
    public Response createTeacher(Teacher teacher) {
        String errTeacherRegistrationFailed = "{\"Error\": \"Failed to create Teacher\"}";
        if (teacher == null) {
            throw new BadRequestException(errTeacherRegistrationFailed);
        }
        teacherService.createTeacher(teacher);
        return Response.ok(teacher, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("{id}")
    @GET
    public Response getTeacherById(@PathParam("id") Long id) {
        Teacher foundTeacher = teacherService.findTeacherById(id);
        String errTeacherNotFound = "{\"Error\": \"No teacher found with id " + id + "\"}";

        if(foundTeacher == null) {
            throw new NotFoundException(errTeacherNotFound);
        }
        return Response.ok(foundTeacher, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("getAllByFirstName")
    @GET
    public Response getAllTeachersByFirstName(@QueryParam("firstName") String firstName) {
        Teacher foundTeacher = (Teacher) teacherService.findAllTeachersByName(firstName);
        String errTeachersNotFound = "{\"Error\": \"No teacher found with name " + firstName + "\"}";

        if (foundTeacher == null) {
            throw new NotFoundException(errTeachersNotFound);
        }
        return Response.ok(foundTeacher).build();
    }

    @GET
    public Response getAllTeachers() {
        List<Teacher> foundTeachers = teacherService.getAllTeachers();
        String errTeachersNotFound = "{\"Error\": \"No teachers found\"}";

        if (foundTeachers.isEmpty()) {
            throw new NotFoundException(errTeachersNotFound);
        }
        return Response.ok(foundTeachers).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id) {
        Teacher foundTeacher = teacherService.findTeacherById(id);
        String errTeacherNotFound = "{\"Error\": \"No teacher found with id " + id + "\"}";

        if (foundTeacher == null) {
            throw new BeenRemovedException(errTeacherNotFound);
        }
        teacherService.deleteTeacher(id);
        return Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON_TYPE).build();
    }


}
