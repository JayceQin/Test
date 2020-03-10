package codesafe.automation.drivers.selenium.views;

import net.serenitybdd.core.Serenity;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class BaseView {
      protected WebDriver driver;
      
      /**
       * Intentionally empty constructor method to support instantiation.
       */
      public BaseView(WebDriver acDriver) throws Exception {
            driver = acDriver;
      }
      
      public void waitForPageToLoad() {
            ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                  public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
                                .equals("complete");
                  }
            };
            try {
                  Thread.sleep(1000);
                  WebDriverWait wait = new WebDriverWait(driver, 30);
                  wait.until(expectation);
            } catch (Throwable error) {
                  Assert.fail("Timeout waiting for Page Load Request to complete.");
            }
      }
      
      protected WebElement findElement(By by) {
            // waitForPageToLoad();
            return findElement(driver, by);
      }
      
      protected List<WebElement> findElements(By by) {
            // waitForPageToLoad();
            return findElements(driver, by);
      }
      
      protected WebElement findElement(SearchContext element, By by) {
            // waitForPageToLoad();
            WebElement found = element.findElement(by);
            return found;
      }
      
      protected List<WebElement> findElements(SearchContext element, By by) {
            // waitForPageToLoad();
            List<WebElement> found = element.findElements(by);
            return found;
      }
      
      public WebElement findElementThatMayNotBePresent(By by) {
            return findElementThatMayNotBePresent(driver, by);
      }
      
      protected WebElement findElementThatMayNotBePresent(SearchContext element, By by) {
            try {
                  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                  WebElement found = element.findElement(by);
                  return found;
            } catch (NoSuchElementException e) {
                  return null;
            } finally {
                  driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
            }
      }
      
      public WebElement waitForElementToBeClickable(WebElement elementToWaitFor) {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));
            return wait.until(ExpectedConditions.elementToBeClickable(elementToWaitFor));
      }
      
      public WebElement waitForVisibilityOfElementLocated(By waitElementLocator, long waitTime) {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(waitElementLocator));
      }
      
      public void retryClick(WebElement element) {
            waitForPageToLoad();
            int retries = 30;
            long sleepInterval = 100;
            boolean success = false;
            for (int i = 0; i < retries; i++) {
                  try {
                        element.click();
                        success = true;
                  } catch (Exception e) {
                  }
                  
                  if (success) {
                        break;
                  } else {
                        simpleSleep(sleepInterval);
                  }
            }
            if (!success) {
                  // Try one last time, but don't catch the exception this time
                  // because if it fails we want to see that stack trace.
                  element.click();
            }
            waitForPageToLoad();
      }
      
      private String getFrameId(String frameName) {
            String frameId;
            switch (frameName) {
                  default:
                        frameId = null;
                        break;
            }
            return frameId;
      }
      
      public void changeIFrame(String frameName) {
            String frameId = getFrameId(frameName);
            WebElement iFrameElement = findElement(By.id(frameId));
            driver.switchTo().frame(iFrameElement);
      }
      
      public void simpleSleep(long millis) {
            try {
                  Thread.sleep(millis);
                  
            } catch (InterruptedException e) {
                  throw new RuntimeException(e);
            }
      }
      
      /**
       * 将页面右侧的下拉框拉倒最下面
       */
      public void scrollPageOnBottom() {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
      }
      
      public void closeAllExtraTabs() {
            String originalHandle = (String) Serenity.getCurrentSession().get("windowHandle");
            for (String handle : driver.getWindowHandles()) {
                  if (!handle.equals(originalHandle)) {
                        driver.switchTo().window(handle);
                        driver.close();
                  }
            }
            
            driver.switchTo().window(originalHandle);
      }
      
      public void switchToNewWindowOrTab() {
            List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
            for (String window : windowHandles) {
                  if (window != (String) Serenity.getCurrentSession().get("windowsHandle")) {
                        driver.switchTo().window(window);
                  }
            }
      }
      
      protected boolean verifyWebElementExists(WebElement element) {
            boolean result = false;
            try {
                  if (element != null) {
                        result = true;
                  }
            } catch (NullPointerException | NoSuchElementException e) {
                  result = false;
            }
            
            return result;
      }
      
      protected void inputValueToInputField(WebElement element, String value) {
            element.clear();
            element.sendKeys(value);
      }
      
      protected String getColsNumbersInTable(WebElement element, String rolsName) {
            int result = 0;
            List<WebElement> lists = element.findElement(By.tagName("thead")).findElements(By.tagName("th"));
            for (WebElement e : lists) {
                  if (e.getText().equals(rolsName)) {
                        result = lists.indexOf(e) + 1;
                  }
            }
            return result + "";
      }
      
      protected WebElement getThirdColsInTable(WebElement element, String cols) {
            return element.findElement(By.cssSelector(":nth-child(" + cols + ")"));
      }
}