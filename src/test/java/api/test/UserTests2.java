package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setupDados() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//Iniciar os logs
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority = 1)
	public void testePostUser() {
		logger.info("*************** Criando usuário ***************");
		
		Response response = UserEndPoints2.criarUsuario(userPayload);
		response.then().log().all(); // retorna todo o escopo da resposta da api
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*************** Usuário criado ***************");
	}
	
	@Test(priority = 2)
	public void testeGetUserPorNome() {
		logger.info("*************** Lendo infos do usuário ***************");
		
		Response response = UserEndPoints2.lerUsuario(userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*************** Infos do usuário lidas ***************");
	}
	
	@Test(priority = 3)
	public void testeUpdateUserPorNome() {
		logger.info("*************** Atualizando do usuário ***************");
		
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		
		Response response = UserEndPoints2.atualizarUsuario(this.userPayload.getUsername(), userPayload);
		response.then().log().all(); 
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseDepoisDoUpdate = UserEndPoints2.lerUsuario(userPayload.getUsername());
		responseDepoisDoUpdate.then().log().body(); // retorna apenas o corpo da resposta da api
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*************** Usuário atualizado ***************");
	}
	
	@Test(priority = 4)
	public void testeDeletePorNome() {
		logger.info("*************** Apagando usuário ***************");
		
		Response response = UserEndPoints2.apagarUsuario(this.userPayload.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*************** Usuário apagado ***************");
	}
	
}
