package codesafe.automation.drivers.selenium;

import codesafe.automation.drivers.utils.TestSystem;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages the creation of the browser session, including browser type and locale.
 */
public class BrowserDriver {
      public WebDriver webDriver;
      protected Browser selectedBrowser;
      protected final TestSystem testMachine;
      
      public enum Browser {
            Chrome("chrome"),
            Firefox("firefox"),
            Explorer("ie");
            
            private final String browserName;
            private static final Map<String, Browser> ENUM_MAP;
            
            Browser(String tokenName) {
                  this.browserName = tokenName;
            }
            
            public String getName() {
                  return this.browserName;
            }
            
            static {
                  Map<String, Browser> map = new ConcurrentHashMap<>();
                  for (Browser instance : Browser.values()) {
                        map.put(instance.getName(), instance);
                  }
                  ENUM_MAP = Collections.unmodifiableMap(map);
            }
            
            public static Browser get(String name) {
                  return ENUM_MAP.get(name);
            }
      }
      
      public BrowserDriver(TestSystem testMachine) {
            this.testMachine = testMachine;
      }
      
      /**
       * Acts as one of the overloaded instances of the getWebDriver method so that a user isn't always required to
       * give both a browser and a locale.
       *
       * @return through calling the full version of getWebDriver it returns the instance of WebDriver to be used
       */
      public synchronized WebDriver getWebDriver() throws Exception {
            return getWebDriver(null, null);
      }
      
      /**
       * Acts as one of the overloaded instances of the getWebDriver method so that a user isn't always required to
       * give a locale but can still specify a browser.
       *
       * @param browser Specific browser of choice, either chrome, firefox, safari, or ie
       * @return Through calling the full version of getWebDriver it returns the instance of WebDriver to be used
       */
      public synchronized WebDriver getWebDriver(String browser) throws Exception {
            return getWebDriver(browser, null);
      }
      
      /**
       * Creates the WebDriver instance of what will later be called driver in other classes.  It calls on
       * BrowserFactory to accomplish this.  It also adds a shutdown hook at the end to clean up the browser session
       * using the custom class BrowserCleanup so that it's not left up and running after code completion.
       *
       * @param browser Specific browser of choice, either chrome, firefox, safari, or ie
       * @param locale  Two letter locale for the browser to use
       * @return the instance of WebDriver to be used
       * @see BrowserFactory
       */
      public synchronized WebDriver getWebDriver(String browser, String locale) throws Exception {
            if (browser != null && !Browser.get(browser).equals(selectedBrowser)) {
                  quit();
            }
            if (webDriver == null) {
                  BrowserFactory factory = new BrowserFactory(testMachine);
                  webDriver = factory.getBrowser(browser, locale);
                  selectedBrowser = Browser.get(browser);
                  Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }
            return webDriver;
      }
      
      public synchronized void setWebDriver(String browser, String locale) throws Exception {
            if (browser != null && selectedBrowser != null && !Browser.get(browser).equals(selectedBrowser)) {
                  quit();
            }
            BrowserFactory factory = new BrowserFactory(testMachine);
            webDriver = factory.getBrowser(browser, locale);
            selectedBrowser = Browser.get(browser);
            Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
      }
      
      /**
       * Sets the dimensions of the current WebDriver session.
       *
       * @param height Desired height of the browser window in pixels
       * @param width  Desired width of the browser window in pixels
       */
      public void setDimensions(int height, int width) {
            Dimension dim = new Dimension(width, height);
            webDriver.manage().window().setSize(dim);
      }
      
      /**
       * There are times when the shutdownHook fails and so this method can be implemented to force the browser
       * session to close.
       */
      public void quit() {
            if (webDriver != null) {
                  webDriver.quit();
                  webDriver = null;
            }
      }
      
      /**
       * This gets called when the JVM exits to clean up.
       */
      private class BrowserCleanup implements Runnable {
            public void run() {
                  quit();
            }
      }
      
      /**
       * This is a minimal method to load a specific page by url.  It is also used as the primary means of starting a
       * WebDriver session.  It does so as an overload on the primary loadPage method and just calls that method
       * passing in voids for unspecified elements in order to execute.
       *
       * @param urlString The full url desired, like 'http://www.someurlhere.com/
       */
      public void loadPage(String urlString) throws Exception {
            loadPage(null, null, urlString);
      }
      
      /**
       * This is a reduced parameter method to load a specific page by url.  It is also used as the primary means of
       * starting a WebDriver session.  It does so as an overload on the primary loadPage method and just calls that
       * method, passing in voids for unspecified elements in order to execute.
       *
       * @param browser   Specific browser of choice, either chrome, firefox, safari, or ie
       * @param urlString The full url desired, like 'http://www.someurlhere.com/
       */
      public void loadPage(String browser, String urlString) throws Exception {
            loadPage(browser, null, urlString);
      }
      
      /**
       * The full version of the loadPage method.  This takes a browser, a locale, and an initial url and accesses
       * the current WebDriver session or creates a new one.  This is used as the primary means of starting a
       * WebDriver session.
       *
       * @param browser   Specific browser of choice, either chrome, firefox, safari, or ie
       * @param locale    Two letter locale for the browser to use
       * @param urlString The full url desired, like 'http://www.someurlhere.com/
       */
      public void loadPage(String browser, String locale, String urlString) throws Exception {
            getWebDriver(browser, locale).get(urlString);
      }
      
      public String getSelectedBrowser() {
            return selectedBrowser.getName();
      }
}