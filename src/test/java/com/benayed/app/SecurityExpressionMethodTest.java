package com.benayed.app;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Getter;
@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityExpressionMethodTest {

	@LocalServerPort
	protected int port;
	
	@Test
	public void givenUserWithROOTRight_whenROOTprotectedEndpointIsCalled_ThenOK() {
		//Arrange
		User userWithRootRights = new User("Haytam", "123456");
		String endpointRequiringROOTright = "/api/v1/users";
		
		//Act
	    Response response = givenAuth(userWithRootRights).port(port).get(endpointRequiringROOTright);

		//Assert
	    Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
	}

	@Test
	public void givenUserWithUSERRight_whenROOTprotectedEndpointIsCalled_Then403() {
		//Arrange
		User userWitgUSERRights = new User("user1", "123456");
		String endpointRequiringROOTright = "/api/v1/users";
		
		//Act
	    Response response = givenAuth(userWitgUSERRights).port(port).get(endpointRequiringROOTright);

		//Assert
	    Assertions.assertThat(response.getStatusCode()).isEqualTo(403);
	}
	
	// Testing custom method security expressions, isTrue is an expression always true ie accessed by all and isFalse is an expression always false ie Unauthorized to all
	@Test
	public void givenAnyUser_WhenTrueEndpointIsCalled_ThenOk() {
		//Arrange
		User anoymousUser = new User("Anyone", "123456");
		String AuthorizedEndpoint = "/api/v1/true";

		//Act
	    Response response = givenAuth(anoymousUser).port(port).get(AuthorizedEndpoint);
	    
		//Assert
	    Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
	}
	
	@Test
	public void givenUserWithROOTRight_WhenFalseEndpointIsCalled_Then403() {
		//Arrange
		User userWithRootRights = new User("Haytam", "123456");
		String UnauthorizedEndpoint = "/api/v1/false";

		//Act
	    Response response = givenAuth(userWithRootRights).port(port).get(UnauthorizedEndpoint);
	    
		//Assert
	    Assertions.assertThat(response.getStatusCode()).isEqualTo(403);
	}
	 
	private RequestSpecification givenAuth(User user) {
	    FormAuthConfig formAuthConfig = 
	      new FormAuthConfig("http://localhost:8087/login", "username", "password");
	     
	    return RestAssured.given().auth().basic(user.getUsername(), user.getPassword());
	}
	
	@Getter @AllArgsConstructor
	private class User{
		private String username;
		private String password;
	}
}
