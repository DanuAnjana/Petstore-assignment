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

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {
	//List<Pet> pets = new ArrayList<Pet>();

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {
		List<Pet> pets = new ArrayList<Pet>();
		pets = PetSingleton.getInstance().getPetList();
		if(pets == null)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
		else
		{
			return Response.ok(pets).build();
		}

	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet pet = new Pet();
		pet = PetSingleton.getInstance().getPetById(petId);
		if(pet == null)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
		else
		{
			return Response.ok(pet).build();
		}
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "create new pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@POST
	@Path("create")
	@Produces("application/json")
	public Response createPets(Pet pet){
		List<Pet> pets = new ArrayList<Pet>();

		pets = PetSingleton.getInstance().createNewPet(pet);
		return Response.ok(pets).build();

	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "update pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@PUT
	@Path("update")
	@Produces("application/json")
	public Response updatePets(Pet pet) {
		List<Pet> pets = new ArrayList<Pet>();


		pets = PetSingleton.getInstance().updatePet(pet);
		if(pets == null)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
		else
		{
			return Response.ok(pets).build();
		}

	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "delete pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@DELETE
	@Path("delete/{petId}")
	public Response deletePets(@PathParam("petId") int petId) {
		List<Pet> pets = new ArrayList<Pet>();
		pets = PetSingleton.getInstance().deletePet(petId);
		if(pets == null)
		{
			return Response.status(Status.BAD_REQUEST).build();
		}
		else
		{
			return Response.ok(pets).build();
		}

	}
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "delete pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("/search")
	public Response searchPet(@DefaultValue("-1") @QueryParam("id") int petId,
							  @DefaultValue("null") @QueryParam("name") String petName,
							  @DefaultValue("0") @QueryParam("age") int petAge)
	{
		boolean isPetFound = false;
		int id = 0;

		if(petId != -1 && petName.equals("null") && petAge == 0){
			if (petId < 0) {
				return Response.status(Status.NOT_FOUND).build();
			}

			for (int i=0;i<PetSingleton.getInstance().getPetList().size();i++){
				if(petId == PetSingleton.getInstance().getPetList().get(i).getPetId()){
					isPetFound = true;
					id = i;
				}
			}
			if(isPetFound){
				return Response.ok(PetSingleton.getInstance().getPetList().get(id)).build();
			}else{
				return Response.ok("There is no pet with id = "+petId).build();
			}
		}
		else if(petId == -1 && !petName.equals("null") && petAge == 0){
			for (int i=0;i<PetSingleton.getInstance().getPetList().size();i++){
				if(petName.equals(PetSingleton.getInstance().getPetList().get(i).getPetName())){
					isPetFound = true;
					id = i;
				}
			}
			if(isPetFound){
				return Response.ok(PetSingleton.getInstance().getPetList().get(id)).build();
			}else{
				return Response.ok("There is no pet with name = "+petName).build();
			}
		}
		else if(petId == -1 && petName.equals("null") && petAge != 0){
			List<Pet> temp = new ArrayList<Pet>();
			for (int i=0;i<PetSingleton.getInstance().getPetList().size();i++){
				if(petAge == PetSingleton.getInstance().getPetList().get(i).getPetAge()){
					isPetFound = true;
					id = i;
					temp.add(PetSingleton.getInstance().getPetList().get(id));
				}
			}
			if(isPetFound){
				return Response.ok(temp).build();
			}else{
				return Response.ok("There is no pet with age = "+petAge).build();
			}
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
