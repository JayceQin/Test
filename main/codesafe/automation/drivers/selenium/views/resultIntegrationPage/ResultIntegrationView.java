package codesafe.automation.drivers.selenium.views.resultIntegrationPage;
import codesafe.automation.drivers.selenium.views.commonView.CommonView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultIntegrationView extends CommonView {
      
      /**
       * Intentionally empty constructor method to support instantiation.
       *
       * @param acDriver
       */
      public ResultIntegrationView(WebDriver acDriver) throws Exception {
            super(acDriver);
      }
      
      /**
       * 判断任务的检测状态
       *
       * @param taskName 任务名称
       */
      public boolean verifyTaskStatus(String taskName) {
            boolean result = false;
            int wait = 500000;
            String cols = getColsNumbersInTaskListTable("整合状态");
            WebElement element = getThirdColsInTaskTable(taskName, cols);
            if (verifyWebElementExists(element)) {
                  while (wait > 0) {
                        if (element.getText().contains("正在整合")) {
                              wait = wait - 1;
                        } else if (element.getText().equals("整合完成")) {
                              result = true;
                              wait = 0;
                        } else if (element.getText().equals("整合失败")) {
                              result = false;
                              wait = 0;
                        }
                  }
            }
            return result;
      }
      
      
      /**
       * 判断任务的检测引擎是否正确
       *
       * @param taskName   任务名称
       * @param engineName 引擎名称
       */
      public boolean verifyTaskEngine(String taskName, String engineName) {
            boolean result = false;
            String cols = getColsNumbersInTaskListTable("检测引擎");
            WebElement element = getThirdColsInTaskTable(taskName, cols).findElement(By.tagName("span"));
            if (verifyWebElementExists(element)) {
                  if (element.getAttribute("tooltip").equals(engineName)) {
                        result = true;
                  }
            }
            
            return result;
      }
}
