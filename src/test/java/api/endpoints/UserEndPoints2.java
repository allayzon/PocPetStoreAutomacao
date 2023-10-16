package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
		

public class UserEndPoints2 {
	
	// Método pra pegar as urls da api
	public static ResourceBundle getURL() {
		 ResourceBundle rotas = ResourceBundle.getBundle("rotas"); // Carregar o arquivo de rotas.properties
		 
		 return rotas;
	}
	
	public static Response criarUsuario(User payload){
		String post_url = getURL().getString("post_url"); // esse "post_url" é a propriedade que o método vai buscar no properties
		
		Response response = given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when().post(post_url);
		
		return response;
	}
	
	public static Response lerUsuario(String userName){
		String get_url = getURL().getString("get_url");
		
		Response response = given().pathParam("username", userName)
				.when().get(get_url);
		
		return response;
	}
	
	public static Response atualizarUsuario(String userName, User payload){
		String update_url = getURL().getString("update_url");
		
		Response response = given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				.when().put(update_url);
		
		return response;
	}
	
	public static Response apagarUsuario(String userName){
		String delete_url = getURL().getString("delete_url");
		
		Response response = given().pathParam("username", userName)
				.when().delete(delete_url);
		
		return response;
	}
}
