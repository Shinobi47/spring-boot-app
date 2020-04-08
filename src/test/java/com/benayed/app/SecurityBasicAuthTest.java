package com.benayed.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityBasicAuthTest {

	//https://stackoverflow.com/questions/47596283/withmockuser-doesnt-pick-spring-security-auth-credentials
	
	@Autowired
	private MockMvc mvc;

	@Test
	@WithMockUser(username = "petitL", roles = {"USER"}) // @WithMockUser provides an authentified user with roles
	public void given_user_with_role_USER_when_calling_protected_resource_then_403_is_returned() throws Exception {
		//Arrange
		String resourceAvailableForROOTroleOnly = "/api/v1/products";
		
		//Act
		mvc.perform(get(resourceAvailableForROOTroleOnly).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isForbidden());
		
		//Assert
		//=> 403 is returned
	}
	
	@Test
	@WithMockUser(username = "petitL", roles = { "ROOT" })
	public void given_user_with_role_USER_when_calling_available_resource_then_200_is_returned() throws Exception {
		//Arrange
		String resourceAvailableForROOTroleOnly = "/api/v1/products";
		
		//Act
		mvc.perform(get(resourceAvailableForROOTroleOnly).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
		
		//Assert
		//=> 200 is returned
	}
}
