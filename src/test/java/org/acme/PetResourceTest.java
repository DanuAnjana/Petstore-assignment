package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.allOf;
import io.quarkus.test.junit.QuarkusTest;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class PetResourceTest {

	@Test
    public void testPetEndpoint() {
        given()
          .when().get("/v1/pets")
          .then()
             .statusCode(200);
//             .body(hasItem(
// 		            allOf(
//    		                hasEntry("pet_id", "1"),
//    		                hasEntry("pet_type", "Dog"),
//    		                hasEntry("pet_name", "Boola"),
//    		                hasEntry("pet_age", "3")
//    		            )
//    		      )
//    		 );
    }

    @Test
    public void testPetAddEndpoint(){
        given()
                .header("Content-Type","application/json")
                .body("{\r\n    \"petId\":\"4,\r\n    \"petName\":\"rocky\",\r\n  \"petAge\":8,\r\n   \"petType\":\"Dog\"\r\n}")
                .when().post("/v1/pets/create")
                .then()
                .statusCode(200)
                .body("petId",equalTo(4))
                .body("petName",equalTo("rocky"))
                .body("petAge",equalTo(8))
                .body("petType",equalTo("Dog"));
    }

    @Test
    void testDeletePet()
    {
        given()
                .header( "Content-Type", "application/json" )
                .pathParam( "id", 1 )
                .when().delete( "/v1/pets/delete/{id}" )
                .then()
                .assertThat()
                .statusCode( 200 );

    }

}