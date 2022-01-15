package tests;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import objects.CreditCardInformation;
import objects.UserDeatils;
import pages.SodaStreamFooter;
import pages.SodaStreamTopNav;

public class BaseClass {
	
	protected WebDriver driver = null;
	protected ExtentHtmlReporter htmlReporter = null;
	protected ExtentReports extent = null;
	protected SodaStreamTopNav topNav = null;
	protected SodaStreamFooter footer = null;
	protected UserDeatils user = null;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		this.topNav = new SodaStreamTopNav(driver);
		this.footer = new SodaStreamFooter(driver);
		CreditCardInformation creditCard = new CreditCardInformation("4539421852928308", "Claudio J. Cade", "032026", 149);
		this.user = new UserDeatils("Julia W."
				, "Cook", 
				"JuliaWCook@dayrep.com", 
				"Creekside Lane", "Goleta", "dayrep", "OH", "United States", "Goleta", "44131", "4403209703", "Ss123456", creditCard);
		driver.get("https://sodastream.com/");
	}
	
	@BeforeClass
	public void setupHtml() {
		htmlReporter = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	
	@AfterClass
	public void teardownHtml() {
		extent.flush();
	}
	
	@AfterClass
	public void teardown() {
		driver.close();
		driver.quit();
	}

}
