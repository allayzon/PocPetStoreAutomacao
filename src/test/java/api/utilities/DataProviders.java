package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String[][] getTodosDados() throws IOException {
		String path = System.getProperty("user.dir")+"//dadosTeste//dadosUser.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int numLinha = xl.getNumeroLinhas("Sheet1");
		int numColuna = xl.getNumeroCelulas("Sheet1", 1);
		
		String dadosApi[][] = new String[numLinha][numColuna];
		
		for(int i = 1; i <= numLinha; i++) {
			for(int j = 0; j < numColuna; j++) {
				dadosApi[i - 1][j] = xl.getDadosCelula("Sheet1", i, j);
			}
		}
		
		return dadosApi;
	}
	
	@DataProvider(name="UserNames")
	public String[] getNomesUsuarios() throws IOException {
		String path = System.getProperty("user.dir")+"//dadosTeste//dadosUser.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int numLinha = xl.getNumeroLinhas("Sheet1");
		
		String dadosApi[] = new String[numLinha];
		
		for(int i = 1; i <= numLinha; i++) {
			dadosApi[i - 1] = xl.getDadosCelula("Sheet1", i, 1);
		}
		
		return dadosApi;
	}
}
