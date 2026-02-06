package api.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.payloads.User;
import api.utilities.AllureLogger;
import io.restassured.response.Response;
import api.endpoints.*;

public class UsersTests {
    Faker fake; 
    User userPayload; 
     @BeforeClass
     public void Setup(){
        fake  =new Faker();
        userPayload = new User();
        userPayload.setId(fake.idNumber().hashCode());
        userPayload.setUsername(fake.name().username());
        userPayload.setFirstname(fake.name().firstName());
        userPayload.setLastname(fake.name().lastName());
        userPayload.setEmail(fake.internet().emailAddress());
        userPayload.setPassword(fake.internet().password());
     }
     @Test(priority = 1)
    public void testCreateUser(){
        AllureLogger.step("Create User API call Started");
        Response response = UserEndpoints.createUser(userPayload);
        AllureLogger.step("Response Received");
         AllureLogger.attachJson("Create User Response", response.asPrettyString());
          AllureLogger.step("Validate status code");
        org.testng.Assert.assertEquals(response.statusCode(), 200);
    }
    @Test(priority = 2)
     public void testReadUser(){
        Response response = UserEndpoints.listUser(this.userPayload.getUsername());
        response.then().log().all();
        org.testng.Assert.assertEquals(response.statusCode(), 200);
        response.then().body("username",equalTo(this.userPayload.getUsername()));
    }
    @Test(priority = 3)
    public void testUpdateUser(){
        Response response = UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().all();
        
        org.testng.Assert.assertEquals(response.statusCode(), 200);
    }
    @Test(priority = 4)
    public void testDeleteUser(){
        Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        org.testng.Assert.assertEquals(response.statusCode(), 200);
    }
}
