package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
		

public class UserEndPoints {

	public static Response criarUsuario(User payload){
		Response response = given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when().post(Rotas.post_url);
		
		return response;
	}
	
	public static Response lerUsuario(String userName){
		Response response = given().pathParam("username", userName)
				.when().get(Rotas.get_url);
		
		return response;
	}
	
	public static Response atualizarUsuario(String userName, User payload){
		Response response = given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				.when().put(Rotas.update_url);
		
		return response;
	}
	
	public static Response apagarUsuario(String userName){
		Response response = given().pathParam("username", userName)
				.when().delete(Rotas.delete_url);
		
		return response;
	}
}
