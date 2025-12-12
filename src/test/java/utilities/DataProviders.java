package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException
	{
	    // Taking Excel file from testData folder
	    String path = ".\\testData\\loginData.xlsx";

	    // Creating an object for ExcelUtility
	    ExcelUtility xlutil = new ExcelUtility(path);

	    // Get total number of rows from Sheet1
	    int totalrows = xlutil.getRowCount("Sheet1");

	    // Get total number of columns from row 1
	    int totalcols = xlutil.getCellCount("Sheet1", 1);

	    // Create 2-dimensional array based on rows & cols
	    String logindata[][] = new String[totalrows][totalcols];

	    // Read the data from Excel and store into 2D array
	    for (int i = 1; i <= totalrows; i++)      // i = rows
	    {
	        for (int j = 0; j < totalcols; j++)   // j = columns
	        {
	            logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
	        }
	    }

	    // Returning the 2D data to DataProvider
	    return logindata;
	}


}
