package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/petTypes")
@Produces("application/json")
public class PetTypeResource {
    //List<Pet> pets = new ArrayList<Pet>();

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pet types", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
    @GET
    public Response getPetTypes() {
        List<PetType> types = new ArrayList<PetType>();
        types = PetTypeSingleton.getInstance().getPetTypeList();
        return Response.ok(types).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet types for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No Pet types found for the id.") })
    @GET
    @Path("{typeId}")
    public Response getPet(@PathParam("typeId") int petId) {
        if (petId < 0) {
            return Response.status(Status.NOT_FOUND).build();
        }
        PetType type = new PetType();
        type = PetTypeSingleton.getInstance().getPetTypeById(petId);
        if(type == null)
        {
            return Response.status(Status.NOT_FOUND).build();
        }
        else
        {
            return Response.ok(type).build();
        }
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "create new pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
    @POST
    @Path("create")
    @Produces("application/json")
    public Response createPetTypes(PetType petType){
        List<PetType> types = new ArrayList<PetType>();
        types = PetTypeSingleton.getInstance().createNewPetType(petType);
        return Response.ok(types).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "update pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
    @PUT
    @Path("update")
    @Produces("application/json")
    public Response updatePetTypes(PetType pettype) {
        List<PetType> types = new ArrayList<PetType>();

        types = PetTypeSingleton.getInstance().updatePetType(pettype);
        if(types == null)
        {
            return Response.status(Status.NOT_FOUND).build();
        }
        else
        {
            return Response.ok(types).build();
        }

    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "delete pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
    @DELETE
    @Path("delete/{typeId}")
    public Response deletePetTypes(@PathParam("typeId") int typeId) {
        List<PetType> types = new ArrayList<PetType>();


        types = PetTypeSingleton.getInstance().deletePetType(typeId);
        if(types == null)
        {
            return Response.status(Status.NOT_FOUND).build();
        }
        else
        {
            return Response.ok(types).build();
        }
    }


}
