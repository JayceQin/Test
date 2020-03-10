package codesafe.automation.drivers.selenium.views.fastCheckPage;

import codesafe.automation.drivers.selenium.views.BaseView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FastCheckTaskListView extends BaseView {
      
      /**
       * Intentionally empty constructor method to support instantiation.
       *
       * @param acDriver
       */
      public FastCheckTaskListView(WebDriver acDriver) throws Exception {
            super(acDriver);
      }
      
      
      /**
       * 获取页面中顶部菜单栏
       */
      private WebElement getCfContentTabs() {
            return findElement(By.className("cf-content-tabs"));
      }
      
      
      /**
       * 获取快速检测页面中检测类型的页签
       *
       * @param quickType e.g. 如"缺陷检测" "溯源检测"等
       */
      private WebElement getCheckTypeTabOnFastCheckPage(String quickType) {
            WebElement element = null;
            List<WebElement> elements = getCfContentTabs().findElements(By.cssSelector("li[role='presentation'][ng-class='{active:tab.active}']"));
            for (WebElement e : elements) {
                  if (e.getText().equals(quickType)) {
                        element = e;
                  }
            }
            return element;
      }
      
      /**
       * 获取页面顶部菜单栏中的导航按钮
       *
       * @param quickType e.g. 缺陷检测,合规检测,溯源检测
       */
      private WebElement getQuickCheckTypeHyperLink(String quickType) {
            return getCheckTypeTabOnFastCheckPage(quickType).findElement(By.tagName("a"));
      }
      
      /**
       * 获取查看结果中的div
       */
      private WebElement getViewResultPage() {
            return findElementThatMayNotBePresent(By.cssSelector("div[class='wrapper center ng-scope']"));
      }
      
      /**
       * 获取查看结果中的maven库div
       */
      private WebElement getTaskBasicInfoFromViewResultPage() {
            WebElement element = null;
            List<WebElement> list = getViewResultPage().findElements(By.cssSelector("div[class *='row']"));
            for (WebElement e : list) {
                  if (e.getText().contains("Maven仓库")) {
                        element = e;
                        break;
                  }
            }
            return element;
      }
      
      /**
       * 获取查看结果中的缺陷总数超链接跳转按钮
       */
      private WebElement getCodeInformationBtn() {
            return findElementThatMayNotBePresent(By.cssSelector("a[ng-if = 'hasPowerByTaskType(checkResult.taskType)']"));
      }
      
      
      public void clickQuickCheckTypeHyperLink(String quickType) {
            retryClick(getQuickCheckTypeHyperLink(quickType));
      }
      
      
      /**
       * 验证是否跳转到该功能页面
       *
       * @param title 如"缺陷检测" "溯源检测"等
       */
      public boolean verifySkipFastCheckPageCorrectly(String title) {
            boolean result = false;
            waitForPageToLoad();
            boolean exist = verifyWebElementExists(getCheckTypeTabOnFastCheckPage(title));
            if (exist) {
                  WebElement fu = getCheckTypeTabOnFastCheckPage(title);
                  if (fu.getAttribute("class").equals("ng-scope active")) {
                        result = true;
                  }
            }
            
            return result;
      }
      
      
      /**
       * 判断当前页面是否为查看结果页面
       */
      public boolean verifyGeneralResultPage() {
            boolean result = false;
            WebElement element = getViewResultPage();
            if (element != null && element.getText().contains("任务基本信息") && element.getText().contains("源代码统计信息")) {
                  result = true;
            } else {
                  result = false;
            }
            
            return result;
      }
      
      /**
       * 验证查看结果页面中maven代码库显示
       *
       * @param value maven代码库地址
       */
      public boolean verifyMavenLibraryFromViewResultPage(String value) {
            boolean result = false;
            boolean exist = verifyWebElementExists(getTaskBasicInfoFromViewResultPage().findElement(By.tagName("li")));
            if (exist) {
                  WebElement element = getTaskBasicInfoFromViewResultPage().findElement(By.tagName("li"));
                  if (element.getText().contains(value)) {
                        result = true;
                  }
            }
            
            return result;
      }
      
      /**
       * 点击查看结果中的缺陷总数超链接跳转按钮
       */
      public void clickCodeInformationBtn() {
            retryClick(getCodeInformationBtn());
      }
}
