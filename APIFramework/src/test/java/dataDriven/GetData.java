package dataDriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetData {

	public static void main(String[] args) {

	}

	public ArrayList<String> getData(String testCaseName) throws IOException {
		

			ArrayList<String> a = new ArrayList<String>();

			FileInputStream input = new FileInputStream(
					"C:\\Local\\APIFramework\\APIFramework\\APIFramework\\resource\\Book1.xlsx");
			XSSFWorkbook workBook = new XSSFWorkbook(input);
			int sheets = workBook.getNumberOfSheets();
			for (int i = 0; i < sheets; i++) {
				if (workBook.getSheetName(i).equalsIgnoreCase("testdata")) {

					XSSFSheet getSheet = workBook.getSheetAt(i);

					Iterator<Row> rows = getSheet.iterator();

					Row firstrow = rows.next();

					Iterator<Cell> cell = firstrow.cellIterator();
					int k = 0;
					int coloum = 0;
					while (cell.hasNext()) {
						Cell value = cell.next();
						if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {

							coloum = k;

						}

						k++;

					}

					//System.out.println(coloum);

					while (rows.hasNext()) {
						Row r = rows.next();
						if (r.getCell(coloum).getStringCellValue().equalsIgnoreCase(testCaseName)) {
							Iterator<Cell> cv = r.cellIterator();

							while (cv.hasNext()) {
								Cell c = cv.next();
								if(c.getCellTypeEnum()==CellType.STRING) {
								
								String getCellValue = c.getStringCellValue();
								a.add(getCellValue);
								//System.out.println(getCellValue);
								
								}else {								
									a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
								}
							}

						}
					}
				}

			}
	
		return a;

		
	}
}
