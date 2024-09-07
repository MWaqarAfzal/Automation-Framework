package excelPractice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestSample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		DataDrivenExcel testData = new DataDrivenExcel();
		
		ArrayList<String> sampleTestData = testData.getData("Login");
		
		for(int i=0; i<sampleTestData.size(); i++)
		{
		System.out.println(sampleTestData.get(i));
		}
		
	}

}
