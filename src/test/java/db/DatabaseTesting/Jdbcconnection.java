package db.DatabaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Jdbcconnection {

	ChromeDriver driver;

	// works on MYSQL DB
	String host = "localhost";
	String port = "3306";
	String url = "jdbc:mysql://" + host + ":" + port + "/databasename";

	// database = qadbt
	// Connection con = DriverManager.getConnection(url, "root", "root");
	@Test
	public void connection() throws SQLException {
		// create DB connection
		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/qadbt", "root", "root");

		// creating a way by "createStatement" so that you can hit statement
		Statement s = con.createStatement();

		// After above statement is created, we execute query (whatever the query we are
		// interested in)
		ResultSet rs = s.executeQuery("select * from credentials where scenario = 'zerobalancecard'");

		// by default result will come to 1 index, setup base index to 1st index by
		// using rs.next() which mean increment by 1;
		while (rs.next()) {
			// getting result from rs object using column name/label
			/*
			 * System.out.println(rs.getString("username"));
			 * System.out.println(rs.getString("password"));
			 */

			String baseURL = "https://login.salesforce.com";
			System.setProperty("webdriver.chrome.driver", "./driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(baseURL);

			driver.findElement(By.xpath(".//*[@id='username']")).sendKeys(rs.getString("username"));

			driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(rs.getString("password"));

		}

	}

}
