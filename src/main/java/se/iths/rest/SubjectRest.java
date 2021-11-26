package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.exception.BadRequestException;
import se.iths.exception.NotFoundException;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @POST
    public Response createSubject(Subject subject) {
        String errSubjectRegistrationFailed = "{\"Error\": \"Failed to create Subject\"}";
        if (subject.getSubject() == null || subject.getSubject().isEmpty()) {
            throw new BadRequestException(errSubjectRegistrationFailed);
        }
        subjectService.createSubject(subject);
        return Response.ok(subject, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateSubjectName(
            @PathParam("id") Long id, @QueryParam("subjectName") String subjectName) {
        Subject foundSubject = subjectService.findSubjectById(id);
        String errSubjectNotFound = "{\"Error\": \"No subject found with id " + id + "\"}";

        if (foundSubject == null) {
            throw new NotFoundException(errSubjectNotFound);
        }
        Subject updatedSubject = subjectService.updateSubject(id, subjectName);
        return Response.ok(updatedSubject, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @GET
    public Response getAllSubjects() {
        List<Subject> foundSubjects = subjectService.getAllSubjects();
        String errSubjectsNotFound = "{\"Error\": \"No subjects found\"}";

        if (foundSubjects.isEmpty()) {
            throw new NotFoundException(errSubjectsNotFound);
        }
        return Response.ok(foundSubjects).build();
    }

    @Path("{id}")
    @GET
    public Response getSubjectById(@PathParam("id") Long id) {
        Subject foundSubject = subjectService.findSubjectById(id);
        String errSubjectNotFound = "{\"Error\": \"No subject found with id " + id + "\"}";

        if(foundSubject == null) {
            throw new NotFoundException(errSubjectNotFound);
        }
        return Response.ok(foundSubject, MediaType.APPLICATION_JSON_TYPE).build();
    }


    @Path("getAllByName")
    @GET
    public Response getAllSubjectsByName(@QueryParam("subjectName") String subjectName) {
        Subject foundSubject = (Subject) subjectService.findAllSubjectsByName(subjectName);
        String errSubjectsNotFound = "{\"Error\": \"No subject found with name " + subjectName + "\"}";

        if(foundSubject == null) {
            throw new NotFoundException(errSubjectsNotFound);
        }
        return Response.ok(foundSubject).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id) {
        Subject foundSubject = subjectService.findSubjectById(id);
        String errSubjectNotFound = "{\"Error\": \"No subject found with id " + id + "\"}";

        if (foundSubject == null) {
            throw new NotFoundException(errSubjectNotFound);
        }
        return Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON_TYPE).build();
    }



}
