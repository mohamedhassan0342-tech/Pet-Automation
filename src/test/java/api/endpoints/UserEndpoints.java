package api.endpoints;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response; 
import io.restassured.http.ContentType;
import api.payloads.User;  
public class UserEndpoints {
    public static Response createUser(User payload){
        Response response = given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(payload)
         .when()
            .post(Routes.post_URL);
        return response;
    }
    public static Response listUser(String username){
            Response response =given()
                .pathParam("username", username)
         .when()
            .get(Routes.get_URL);
        return response;
    }
    public static Response updateUser(String username, User payload){
        Response response =given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("username", username)
            .body(payload)
         .when()
            .put(Routes.put_URL);
            return response;
    }
    public static Response deleteUser(String username){
         Response response =given()
                .pathParam("username", username)
         .when()
            .delete(Routes.delete_URL);
            return response;
    }
}
