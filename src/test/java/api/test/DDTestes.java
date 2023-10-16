package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTestes { // Data Driven Testes

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testePostUser(String userID, String userName, String firstName, String lastName,
			String userEmail, String pwd, String phone) {
		
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		Response response = UserEndPoints.criarUsuario(userPayload);		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testeDeleteUserPorNome(String userName) {
		
		Response response = UserEndPoints.apagarUsuario(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
