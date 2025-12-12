package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.Logger;  //Log4j
import org.apache.logging.log4j.LogManager;  //Log4j

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;


	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public	void setUp(String os,String br) throws InterruptedException, IOException {

		//Loading config files

		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);





		logger=	LogManager.getLogger(this.getClass()); //log4j2


		//step-10
		//it is for selenium grid of execution_env="remote"

		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities desiredCapabilities=new DesiredCapabilities();

			//os
			if(os.equalsIgnoreCase("windows")) {
				desiredCapabilities.setPlatform(Platform.WIN10);

			}
			else if(os.equalsIgnoreCase("linux")) {
				desiredCapabilities.setPlatform(Platform.LINUX);

			}
			else if(os.equalsIgnoreCase("edge")) {
				desiredCapabilities.setPlatform(Platform.MAC);

			}
			else if(os.equalsIgnoreCase("mac")) {
				desiredCapabilities.setPlatform(Platform.MAC);

			}
			else {
				System.out.println("no matching browser");
				return;
			}

			//browser
			switch (br.toLowerCase()) {
			case "chrome":desiredCapabilities.setBrowserName("chrome");break;
			case "edge"	:desiredCapabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox":desiredCapabilities.setBrowserName("firefox");break;
			default:System.out.println("No matching"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),desiredCapabilities);
		}


		
		
		
		//if selenium grid is local
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome":driver=new ChromeDriver();break;
			case "edge":driver=new EdgeDriver();break;
			case "firefox":driver=new FirefoxDriver();break;
			default:System.out.println("Invalid browser name");return;
			}
		}



		//		switch (br.toLowerCase()) {
		//		case "chrome":driver=new ChromeDriver();break;
		//		case "edge":driver=new EdgeDriver();break;
		//		case "firefox":driver=new FirefoxDriver();break;
		//		default:System.out.println("Invalid browser name");return;
		//		}



		//		driver=new ChromeDriver();
		//driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//		driver.get("https://tutorialsninja.com/demo/index.php?route=");
		driver.get(p.getProperty("appURL1")); //reading url from properties file   
		logger.info("URL opened: " + p.getProperty("appURL1"));

		driver.manage().window().maximize();
		Thread.sleep(3000);
	}

	@AfterClass(groups = {"Sanity","Regression","Master"})
	public	void tearDown() {
		driver.quit();
	}


	public String randomString() {
		String generatedString=	RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber() {
		String generatedNumber=	RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomAlphaNumber() {
		String generatedString=	RandomStringUtils.randomAlphabetic(5);
		String generatedNumber=	RandomStringUtils.randomNumeric(3);
		return (generatedString+"@"+generatedNumber);
	}


	public String captureScreen(String tname)throws IOException{
		String timeStamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
		File sourceFile= takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath=	System.getProperty("user.dir")+"\\screenshots\\" + tname + "_"+ timeStamp +".png";
		File targetFile= new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
