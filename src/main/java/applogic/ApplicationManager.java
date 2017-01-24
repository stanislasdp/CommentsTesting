package applogic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import util.PropertyLoader;

/**
 * Created by stas on 1/23/17.
 */
public class ApplicationManager implements ApplicationManage {
    private String startURL;
    private WebDriver driver;
    private NavigationManage navigationManage;


    public ApplicationManager() {
        startURL = PropertyLoader.getProperty("url");
        //System.setProperty("webdriver.gecko.driver","D:\\KiryanStanislav\\screnshots\\SoftBank\\PCC-22108\\PCC-22108\\PCC-22108\\selenium\\geckodriver.exe");
       // System.setProperty("webdriver.chrome.driver", "/media/MEDIA/install/linux/instALL/chromedriver");
        System.setProperty("webdriver.chrome.driver", "/mnt/JAVA/instALL/chromedriver");
        driver = new ChromeDriver();

        navigationManage = new NavigationManager(this);

    }

    public String getStartURL() {
        return startURL;
    }

    public NavigationManage getNavigationManage() {
        return navigationManage;
    }

    public void stopApp() {
        driver.close();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
