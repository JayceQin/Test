package codesafe.automation.drivers.selenium.views;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginView extends BaseView {
      public LoginView(WebDriver acDriver) throws Exception {
            super(acDriver);
      }
      
      private WebElement getUsernameField() {
            return findElementThatMayNotBePresent(By.cssSelector("input[ng-model='loginObj.user.username']"));
      }
      
      private WebElement getPasswordField() {
            return findElementThatMayNotBePresent(By.cssSelector("input[ng-model='loginObj.user.password"));
      }
      
      private WebElement getSignInButton() {
            return findElementThatMayNotBePresent(By.cssSelector("button[class='btn btn-success btn-lg loginBtn btn-success-spe'][ng-click='loginIn()']"));
      }
      
      private WebElement getErrorMsgInLoginPage() {
            return findElementThatMayNotBePresent(By.cssSelector("div[class='errMsg ng-binding ng-scope']"));
      }
      
      private WebElement getAgreementCheckBox(){
            return findElementThatMayNotBePresent(By.id("ageement-checkbox"));
      }
      
      private WebElement getUpdatePasswordTitle(){
            return findElementThatMayNotBePresent(By.cssSelector("div[class='passwd-wrapper cf-content-item ng-scope']")).findElement(By.cssSelector("div[class='cf-content-title'"));
      }
      
      public void enterUsername(String name) {
            waitForPageToLoad();
            inputValueToInputField(getUsernameField(),name);
      }
      
      public void enterPassword(String pass) {
            waitForPageToLoad();
            inputValueToInputField(getPasswordField(),pass);
      }
      
      public void clickSignInButton() {
            waitForPageToLoad();
            retryClick(getSignInButton());
            waitForPageToLoad();
      }
      
      public String getAccountLoginFailedReason() {
            return getErrorMsgInLoginPage().getText();
      }
      
      public boolean verifyAccountBlocked() {
            boolean result = false;
            WebElement element = getErrorMsgInLoginPage();
            if (element.getText().equals("您的帐号已冻结，请联系管理员")) {
                  result = true;
            }
            return result;
      }
      
      public boolean verifyAgreementCheckboxIsClicked(){
            return getAgreementCheckBox().isSelected();
      }
      
      public boolean verifyPasswordPageTitle(){
            return getUpdatePasswordTitle().getText().equals("初始化密码");
      }
}
