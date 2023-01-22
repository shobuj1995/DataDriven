package testNGdataDriven;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginTest extends BaseTest{
    @DataProvider(name = "getTestData")
    public Object[][] getTestData() {
        return new Object[][]{{"username1", "password"}, {"username2", "password2"}, {"username3", "password"}};
    }

    @Test(dataProvider = "getTestDataFromExcel")
    public void loginShouldSuccessed(String username, String password) throws InterruptedException {
        WebElement usernameEl = driver.findElement(By.name("username"));
        usernameEl.isDisplayed();
        usernameEl.clear();
        usernameEl.sendKeys(username);
        Thread.sleep(3000);

        WebElement passwordEl = driver.findElement(By.name("password"));
        passwordEl.isDisplayed();
        passwordEl.clear();
        passwordEl.sendKeys(password);
        Thread.sleep(3000);
    }
    @DataProvider
    public static Object[][] getTestDataFromExcel() {
        String path = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData.xls";
        Workbook book = null;
        Sheet sheet;
        FileInputStream file = null;
        try {
            file = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet("Sheet1");
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        // System.out.println(sheet.getLastRowNum() + "--------" +
        // sheet.getRow(0).getLastCellNum());
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
                System.out.println(data[i][k]);
            }
        }
        return data;

    }



//    public class ReadandWriteCSVFile {
//        public static void main(String[] args) throws FileNotFoundException, IOException {
//            String fName = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData.xls";
////            String fName = "c:\\csv\\myfile.csv";
//            Object obj[][] = null;
//            // Read data from CSV file.
//            String thisLine;
//
//            FileInputStream fis = new FileInputStream(fName);
//            DataInputStream myInput = new DataInputStream(fis);
//            List<String[]> lines = new ArrayList<String[]>();
//            while ((thisLine = myInput.readLine()) != null) {
//                lines.add(thisLine.split(";"));
//            }
//
//// convert our list to a String array.
//            String[][] array = new String[lines.size()][0];
//            lines.toArray(array);
//
//        }
//    }
}
