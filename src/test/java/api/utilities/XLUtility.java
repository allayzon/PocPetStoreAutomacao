package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet folha;
	public XSSFRow linha;
	public XSSFCell celula;
	public CellStyle style;
	String path;
	
	public XLUtility(String path) {
		this.path = path;
	}
	
	public int getNumeroLinhas(String nomeFolha) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		folha = workbook.getSheet(nomeFolha);
		int rowCount = folha.getLastRowNum();
		workbook.close();
		fi.close();
		
		return rowCount;
	}
	
	public int getNumeroCelulas(String nomeFolha, int numLinha) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		folha = workbook.getSheet(nomeFolha);
		linha = folha.getRow(numLinha);
		int numCelulas = linha.getLastCellNum();
		workbook.close();
		fi.close();
		
		return numCelulas;
	}
	
	public String getDadosCelula(String nomeFolha, int numLinha, int coluna) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		folha = workbook.getSheet(nomeFolha);
		linha = folha.getRow(numLinha);
		celula = linha.getCell(coluna);
		
		DataFormatter formata = new DataFormatter();
		String dado;
		try {
			dado = formata.formatCellValue(celula); // retorna o valor de uma celula em formato de string
		} catch (Exception e) {
			dado = "";
		}
		workbook.close();
		fi.close();
		
		return dado;
	}
	
	public void setDadoCelula(String nomeFolha, int numLinha, int numColuna, String dado) throws IOException {
		File arquivoXl = new File(path);
		if (!arquivoXl.exists()) { // se o arquivo não existir, cria ele 
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		
		if (workbook.getSheetIndex(nomeFolha) == -1) // se a folha não existir, cria ela
			workbook.createSheet(nomeFolha);
		folha = workbook.getSheet(nomeFolha);
		
		if (folha.getRow(numLinha) == null)
			folha.createRow(numLinha);
		linha = folha.getRow(numLinha);
		
		celula = linha.createCell(numColuna);
		celula.setCellValue(dado);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	public void preencheVermelho(String nomeFolha, int numLinha, int numColuna) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		folha = workbook.getSheet(nomeFolha);
		linha = folha.getRow(numLinha);
		celula = linha.getCell(numColuna);
		
		style = workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		celula.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
}
