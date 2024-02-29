package datareader;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TestDataHandler {
    private static Sheet sheet;
    static {
        try(FileInputStream fis = new FileInputStream("actitime_testdata"+"\\" +"testscript_data.xlsx")){
            Workbook wb = WorkbookFactory.create(fis); // creating object of excel sheet
            sheet = wb.getSheet("testdata");
        }catch (IOException e){

        }
    }
    //first array will take all the test cases and second will take all the data for particular test case
    public static String[][] dataReader(String tcName){
        int dataRowCount = sheet.getPhysicalNumberOfRows();
        ArrayList<String[]> similar_testcase_data = new ArrayList<>();
        for(int rowIndex=0;rowIndex<dataRowCount;rowIndex++){
            Row r = sheet.getRow(rowIndex);// picked first row when loop runs with 0 index
            String testCaseName = r.getCell(1).getStringCellValue();
            String testRunStatus = r.getCell(2).getStringCellValue();
            if(testCaseName.equalsIgnoreCase(tcName) && testRunStatus.equalsIgnoreCase("Y")){
                ArrayList<String> each_testcase_data = new ArrayList<>();
                int cellCount = r.getPhysicalNumberOfCells();
                for(int dataCellIndex =3; dataCellIndex<cellCount;dataCellIndex++){
                    each_testcase_data.add(r.getCell(dataCellIndex).getStringCellValue()); // its gets datafield 1,2 and title name
                }
                each_testcase_data.add(rowIndex+"");// not required I believe
                similar_testcase_data.add(each_testcase_data.toArray(new String[]{}));

            }
        }

        return similar_testcase_data.toArray(new String[][]{});
    }
}
