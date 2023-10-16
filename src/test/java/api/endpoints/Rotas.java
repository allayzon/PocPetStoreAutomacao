package api.endpoints;

/*
Swagger petstore URI -> https://petstore.swagger.io

Criar usuário (Post):  https://petstore.swagger.io/v2/user
Get usuário (Get):  https://petstore.swagger.io/v2/user/{username}
Atualizar usuário (Put):  https://petstore.swagger.io/v2/user/{username}
Deletar usuário (Delete):  https://petstore.swagger.io/v2/user/{username}
*/

public class Rotas {

	public static String base_url = "https://petstore.swagger.io/v2";
	
	//user model
	
	public static String post_url = base_url + "/user";
	public static String get_url = base_url + "/user/{username}";
	public static String update_url = base_url + "/user/{username}";
	public static String delete_url = base_url + "/user/{username}";
	
	//store model
	
	//pet model
}
