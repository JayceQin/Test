package codesafe.automation.drivers.selenium;

import codesafe.automation.drivers.utils.TestSystem;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {
      
      /**
       * Creates the browser driver specified in the system property "browser"
       * if no property is set then a chrome browser driver is created.
       * The allowed properties are chrome, firefox, and ie
       * e.g to run with firefox, pass in the option -Dbrowser=firefox at runtime
       * (assuming Maven is being used).
       *
       * @return WebDriver
       */
      
      private final String CHROME_DRIVER_EXE_PROPERTY = "webdriver.chrome.driver";
      private final TestSystem codeSafeSystem;
      
      public BrowserFactory(TestSystem testMachine) {
            this.codeSafeSystem = testMachine;
      }
      
      /**
       * Overloaded method that calls the full getBrowser method, but allows a user to leave parameters unfilled.
       *
       * @return a WebDriver instance with the default browser and locale
       */
      public WebDriver getBrowser() throws Exception {
            return getBrowser(null, null);
      }
      
      /**
       * Overloaded method that calls the full getBrowser method, but allows the user to leave the locale unspecified
       *
       * @param browser
       * @return a WebDriver instance with the defined browser and the default locale
       */
      public WebDriver getBrowser(String browser) throws Exception {
            return getBrowser(browser, null);
      }
      
      /**
       * The base method for creating a WebDriver instance with a chosen browser.  It calls on support methods to
       * create the browser specific drivers and us currently defaults to Chrome as the browser.
       *
       * @param browser
       * @param locale
       * @return a WebDriver instance with the defined browser and locale
       */
      public WebDriver getBrowser(String browser, String locale) throws Exception {
            if (browser == null) browser = "chrome";
            WebDriver selectedDriver;
            switch (BrowserDriver.Browser.get(browser)) {
                  case Chrome:
                        selectedDriver = createChromeDriver(locale);
                        break;
//            case Firefox:
//                selectedDriver = createFirefoxDriver(getFirefoxProfile(locale));
//                break;
                  case Explorer:
                        selectedDriver = createIEDriver();
                        break;
                  default:
                        selectedDriver = createChromeDriver(locale);
                        break;
            }
            
            addAllBrowserSetup(selectedDriver);
            return selectedDriver;
      }
      
      /**
       * First checks to make certain the OS is Windows as IE isn't a valid choice on any other OS.  It then finds
       * the IEDriverServer.exe file, allowing WebDriver to interact with IE.
       *
       * @return an IE specific instance of WebDriver with the default settings noting that locale cannot be set
       * on IE in the current version of this method
       */
      private WebDriver createIEDriver() throws Exception {
            Path ieDriverPath = Paths.get(System.getProperty("user.dir"), "src/main/resources", "IEDriverServer.exe");
            File ieDriverFile = ieDriverPath.toFile();
            if (ieDriverFile.exists()) {
                  System.setProperty("webdriver.ie.driver", ieDriverPath.toAbsolutePath().toString());
                  return new InternetExplorerDriver();
            } else {
                  throw new Exception("IE driver file could not be found at " + ieDriverPath.toString());
            }
      }
      
      /**
       * The system property CHROME_DRIVER_EXE_PROPERTY allows a user to specify a unique chrome driver.  If not,
       * it defaults to a value known to be valid.  It then checks to see if the system is Windows or Mac, assuming
       * Windows if it can't tell.  There isn't currently support for Linux (though there is a chromedriver for it).
       * It performs a few more actions to make sure that the driver file will work and then sets the locale if one
       * has been specified.
       *
       * @param locale
       * @return a Chrome specific instance of WebDriver for either Mac or PC and with a locale if specified
       */
      private WebDriver createChromeDriver(String locale) throws Exception {
            File executable;
            String chromeDriverProperty = System.getProperty(CHROME_DRIVER_EXE_PROPERTY);
            if (chromeDriverProperty == null) {
                  String chromeDriverName;
                  chromeDriverName = "chromedriver.exe";
                  Path chromeDriverPath = Paths.get(System.getProperty("user.dir"), "src/main/resources", chromeDriverName);
                  executable = new File(chromeDriverPath.toAbsolutePath().toString());
            } else {
                  executable = new File(chromeDriverProperty);
            }
            if (executable.exists()) {
                  System.setProperty(CHROME_DRIVER_EXE_PROPERTY, executable.getAbsolutePath());
                  executable.setExecutable(true);
                  if (!executable.canExecute()) executable.setExecutable(true);
                  if (locale != null) {
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--lang=" + locale);
                        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
                        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                        return new ChromeDriver(options);
                  } else {
                        ChromeOptions options = new ChromeOptions();
                        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
                        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                        return new ChromeDriver(options);
                  }
            } else {
                  throw new Exception("Chrome driver file could not be found at " + executable.getAbsolutePath());
            }
      }

//    /**
//     * Very simple creation method since Firefox is native to Selenium tools and doesn't require any additional
//     * driver files.
//     *
//     * @param firefoxProfile
//     * @return a Firefox specific instance of Webdriver with the options specified a provided FirefoxProfile
//     */
//    private WebDriver createFirefoxDriver(FirefoxProfile firefoxProfile) {
//        return new FirefoxDriver(firefoxProfile);
//    }

//    private FirefoxProfile getFirefoxProfile(String locale) {
//        FirefoxProfile firefoxProfile = new FirefoxProfile();
//        if (locale != null) {
//            firefoxProfile.setPreference("intl.accept_languages", locale);
//        }
//        return firefoxProfile;
//    }
      
      /**
       * Sets the default amount of time WebDriver will spend looking for something on a page before it times out.  Also
       * sets the default position and size of the browser.
       *
       * @param driver
       */
      private void addAllBrowserSetup(WebDriver driver) {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.manage().window().maximize();
//        driver.manage().window().setPosition(new Point(0, 0));
//        driver.manage().window().setSize(new Dimension(1920, 1080));
      }
      
}
