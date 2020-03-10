package codesafe.automation.drivers.selenium.views.projectManagerPage;

import codesafe.automation.drivers.selenium.views.commonView.CommonView;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProjectManagerView extends CommonView {
      
      /**
       * Intentionally empty constructor method to support instantiation.
       *
       * @param acDriver
       */
      public ProjectManagerView(WebDriver acDriver) throws Exception {
            super(acDriver);
      }
      
      
      private WebElement getConfigPageBottomArea() {
            return findElementThatMayNotBePresent(By.cssSelector("div[class='cf-project-btn-group ng-scope']"));
      }
      
      /**
       * 新建项目配置信息页面中的左侧按钮
       */
      private WebElement getCreatePjLeftBtn() {
            return getConfigPageBottomArea().findElement(By.cssSelector(":nth-child(1)"));
      }
      
      
      /**
       * 新建项目配置信息页面中的右侧按钮
       */
      private WebElement getCreatePjRightBtn() {
            return getConfigPageBottomArea().findElement(By.cssSelector(":nth-child(2)"));
      }
      
      
      /**
       * 获取项目信息中的顶部导航链接
       */
      private WebElement getPMNavigationOnProjectInfoPage(String linkText) {
            return findElementThatMayNotBePresent(By.className("cf-breadcrumb")).findElement(By.linkText(linkText));
      }
      
      /**
       * 获取页面中顶部菜单栏
       */
      private WebElement getCfContentTabs() {
            return findElement(By.className("cf-content-tabs"));
      }
      
      /**
       * 获取项目名称
       */
      private WebElement getProjectNameOnProjectInfoPage() {
            return findElementThatMayNotBePresent(By.className("cf-breadcrumb")).findElement(By.cssSelector("span[ng-if='detailObj.projectInfo.exeStatus==1']"));
      }
      
      
      /**
       * 获取项目信息中的的导航栏
       *
       * @param navi 源代码列表,项目信息,差距分析
       */
      private WebElement getNavigationTabOnProjectInfoPage(String navi) {
            WebElement element = null;
            List<WebElement> elements = getCfContentTabs().findElements(By.cssSelector("li[class *='ng-isolate-scope']"));
            for (WebElement e : elements) {
                  if (e.getAttribute("heading").equals(navi)) {
                        element = e;
                  }
            }
            return element;
      }
      
      
      /**
       * 获取项目信息中的option栏
       *
       * @param option E.g. 创建者,创建时间,项目经理 ...
       */
      private WebElement getProjectDetailOnProjectInfoPage(String option) {
            WebElement element = null;
            List<WebElement> lists = findElementThatMayNotBePresent(By.className("project-detail-info-section")).findElements(By.cssSelector("div[class *='detail-info-item']"));
            for (WebElement e : lists) {
                  if (e.findElement(By.className("detail-info-title")).getText().equals(option)) {
                        element = e.findElement(By.cssSelector("div[class='detail-info-body ng-binding']"));
                  }
            }
            return element;
      }
      
      
      /**
       * 获取最近检测结果的区域
       */
      private WebElement getRecentDetectionPage() {
            return findElementThatMayNotBePresent(By.cssSelector("div[class='cf-content-result ng-scope']"));
      }
      
      /**
       * 获取最近检测结果的区域检测类型为checkType的总缺陷数
       *
       * @param checkType 缺陷检测,合规检测
       */
      private WebElement getCheckTypeDefectCountsLink(String checkType) {
            WebElement element = null;
            List<WebElement> lists = getRecentDetectionPage().findElements(By.cssSelector("div[class='code-task-info ng-scope']"));
            for (WebElement e : lists) {
                  if (e.findElement(By.className("info-title")).getText().contains(checkType)) {
                        element = e.findElement(By.cssSelector("a[ng-bind-html='item.problemNum|thousandsFilter']"));
                  }
            }
            return element;
      }
      
      /**
       * 根据批次名称获取批次一行的tbody
       *
       * @param batchName 检测批次名称
       */
      private WebElement getCodeExecuteRowsTbody(String batchName) {
            WebElement element = null;
            List<WebElement> batchs = getTableOnPage().findElements(By.cssSelector("tbody"));
            try {
                  if (batchs.size() > 0) {
                        for (WebElement e : batchs) {
                              if (e.findElement(By.tagName("a")).getText().equals(batchName)) {
                                    element = e;
                                    break;
                              }
                        }
                  }
                  return element;
            } catch (NullPointerException | NoSuchElementException e) {
                  return null;
            }
      }
      
      
      /**
       * 获取批次列表中的一行
       *
       * @param batchName 检测批次名称
       */
      private WebElement getCodeExecuteRowsData(String batchName) {
            return getCodeExecuteRowsTbody(batchName).findElement(By.cssSelector("tr:nth-child(1)"));
      }
      
      /**
       * 获取项目源码列表中batchName的批次的详细信息的tr
       *
       * @param batchName 检测批次名称
       */
      private WebElement getCodeExecuteBatchDetailsData(String batchName) {
            return getCodeExecuteRowsTbody(batchName).findElement(By.cssSelector("tr:nth-child(2)"));
      }
      
      
      /**
       * 获取项目源码列表中的勾选框
       *
       * @param batchName 检测批次名称
       */
      private WebElement getSelectCodeCheckBoxField(String batchName) {
            return getThirdColsInTable(getCodeExecuteRowsData(batchName), "1").findElement(By.tagName("input"));
      }
      
      
      /**
       * 获取列表中的任务名链接
       *
       * @param batchName 任务名称
       */
      private WebElement getTaskNameHyperLink(String batchName) {
            return getThirdColsInTable(getCodeExecuteRowsData(batchName), "2").findElement(By.tagName("a"));
      }
      
      /**
       * 获得修改项目配置下拉菜单的链接按钮
       *
       * @param projectName 项目名称
       * @param linkText    修改检测策略;修改检测信息
       */
      private WebElement getModifyProjectInfoOrStrategy(String projectName, String linkText) {
            return getAllTaskInTaskListTable(projectName).findElement(By.cssSelector("ul[class='dropdown-menu dropdown-menu-right']")).findElement(By.linkText(linkText));
      }
      
      /**
       * 获取检测记录列表中执行批次为batchName的检测详情table
       *
       * @param batchName 检测批次名称
       */
      private WebElement getCodeExecuteBatchDetailsTable(String batchName) {
            return getCodeExecuteBatchDetailsData(batchName).findElement(By.cssSelector("table[class='table table-hover']"));
      }
      
      
      /**
       * 获取项目源码列表中执行批次中检测类型为checktype的tr
       *
       * @param batchName 检测批次名称
       * @param checkType 检测方式:缺陷检测,合规检测,溯源检测
       */
      private WebElement getCodeExecuteBatchsDetailsTr(String batchName, String checkType) {
            WebElement element = null;
            List<WebElement> batchDetials = getCodeExecuteBatchDetailsTable(batchName).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
            if (batchDetials.size() > 0) {
                  for (WebElement e : batchDetials) {
                        if (e.getText().contains(checkType)) {
                              element = e;
                              break;
                        }
                  }
            }
            
            return element;
      }
      
      /**
       * 获取项目源码列表中执行批次中检测类型为checktype中列表为rolsName的一列
       *
       * @param batchName 检测批次名称
       * @param checkType 检测方式:缺陷检测,合规检测,溯源检测
       * @param colsName  列名
       */
      private WebElement getColsOnBatchCheckDetailsTable(String batchName, String checkType, String colsName) {
            String cols = getColsNumbersInTable(getCodeExecuteBatchDetailsTable(batchName), colsName);
            return getCodeExecuteBatchsDetailsTr(batchName, checkType).findElement(By.cssSelector(":nth-child(" + cols + ")"));
      }
      
      
      /**
       * 获取检测批次中检测方式为缺陷检测/合规检测/溯源检测的操作按钮
       *
       * @param batchName 检测批次名称
       * @param checkType 检测方式:缺陷检测,合规检测,溯源检测
       * @param operation 为查看结果，结果审计，导出报告
       */
      public WebElement getOperationIconOnBatchList(String batchName, String checkType, String operation) {
            WebElement element = null;
            List<WebElement> lists = getColsOnBatchCheckDetailsTable(batchName, checkType, "操作").findElements(By.tagName("a"));
            if (lists != null) {
                  for (WebElement e : lists) {
                        if (e.getAttribute("tooltip").equals(operation)) {
                              element = e;
                        }
                  }
            }
            return element;
      }
      
      /**
       * 点击新建项目配置信息页面中的创建项目按钮
       */
      public void clickCreatePjRightBtn() {
            retryClick(getCreatePjRightBtn());
      }
      
      
      /**
       * 点击项目信息中的顶部导航链接
       *
       * @param linkText 项目管理
       */
      public void clickPMNavigationOnProjectInfoPage(String linkText) {
            retryClick(getPMNavigationOnProjectInfoPage(linkText));
      }
      
      
      /**
       * 点击项目信息中的的导航栏
       *
       * @param navi 源代码列表,项目信息,差距分析
       */
      public void clickNavigationTabOnProjectInfoPage(String navi) {
            retryClick(getNavigationTabOnProjectInfoPage(navi));
      }
      
      
      /**
       * 点击新建项目配置信息页面中的下一步按钮
       */
      public void clickCreatePjLeftBtn() {
            retryClick(getCreatePjLeftBtn());
      }
      
      
      /**
       * 判断批次batchName执行
       *
       * @param batchName 检测批次名称
       */
      public boolean verifyCodeBatchExectuteSucc(String batchName) {
            return verifyWebElementExists(getCodeExecuteRowsTbody(batchName));
      }
      
      /**
       * 判断列表中值的正确
       *
       * @param name    任务名称
       * @param colName 列名
       * @param value   值
       */
      public boolean verifyColsValueCorrectlyInBatchListTable(String name, String colName, String value) {
            boolean result = false;
            String cols = getColsNumbersInTable(getTableOnPage(), colName);
            WebElement element = getThirdColsInTable(getCodeExecuteRowsData(name), cols);
            if (element.getText().contains(value)) {
                  result = true;
            }
            return result;
      }
      
      /**
       * 判断项目名称带有禁用提示
       */
      public boolean verifyProjectNameContainsWordTips() {
            boolean result = false;
            if (verifyWebElementExists(getProjectNameOnProjectInfoPage())) {
                  WebElement element = getProjectNameOnProjectInfoPage();
                  if (element.getText().equals("（已禁用）")) {
                        result = true;
                  }
            }
            
            return result;
      }
      
      /**
       * 判断项目禁用后,添加源代码按钮和立即检测按钮不可点击
       */
      public boolean verifyLinkButtonIsDisabled(String linkText) {
            boolean result = false;
            WebElement element = getOperationButtonOnTablePage(linkText);
            if (element.getAttribute("class").equals("btn btn-green disabled")) {
                  result = true;
            }
            return result;
      }
      
      
      /**
       * 点击修改项目配置下拉菜单的链接按钮
       *
       * @param projectName 项目名称
       * @param linkText    修改检测策略;修改检测信息
       */
      public void clickModifyProjectInfoOrStrategy(String projectName, String linkText) {
            retryClick(getModifyProjectInfoOrStrategy(projectName, linkText));
      }
      
      /**
       * 判断项目信息是否正确
       */
      public boolean verifyProjectInfoCorrectly(String option, String value) {
            boolean result = false;
            WebElement element = getProjectDetailOnProjectInfoPage(option);
            if (element.getText().equals(value)) {
                  result = true;
            }
            
            return result;
      }
      
      /**
       * 点击最近检测结果的区域检测类型为checkType的总缺陷数
       *
       * @param checkType 缺陷检测,合规检测
       */
      public void clickCheckTypeDefectCountsLink(String checkType) {
            retryClick(getCheckTypeDefectCountsLink(checkType));
      }
      
      /**
       * 展开/收起 类型为batchType的批次batchName
       *
       * @param batchName 检测批次名称
       */
      public void clickBatchNameInCodeBatchList(String batchName) {
            retryClick(getTaskNameHyperLink(batchName));
      }
      
      
      /**
       * 判断类型为batchTyep的批次batchName中,checktype检测任务是否成功
       *
       * @param batchName 检测批次名称
       * @param checkType 检测方式:缺陷检测,合规检测,溯源检测
       * @param colsName  列名
       */
      public boolean verifyBatchCheckTypeCheckStatus(String batchName, String checkType, String colsName) {
            boolean result = false;
            WebElement element = getColsOnBatchCheckDetailsTable(batchName, checkType, colsName);
            if (verifyWebElementExists(element)) {
                  if (element.getText().equals("检测完成")) {
                        result = true;
                  } else if (element.getText().equals("检测失败")) {
                        result = false;
                  }
            }
            return result;
      }
      
      /**
       * 勾选类型为batchType的批次batchName
       *
       * @param batchName 检测批次名称
       */
      public void selectBatchInCodeBatchList(String batchName) {
            retryClick(getSelectCodeCheckBoxField(batchName));
      }
      
      
      /**
       * 点击检测批次中检测方式为缺陷检测/合规检测/溯源检测的操作按钮
       *
       * @param batchName 检测批次名称
       * @param checkType 检测方式:缺陷检测,合规检测,溯源检测
       * @param operation 为查看结果，结果审计，导出报告
       */
      public void clickOperationIconOnBatchList(String batchName, String checkType, String operation) {
            retryClick(getOperationIconOnBatchList(batchName, checkType, operation));
      }
      
      /**
       * 判断悬浮信息是否正确
       *
       * @param batchName 检测批次名称
       * @param checkType 检测方式:缺陷检测,合规检测,溯源检测
       * @param operation 为查看结果，结果审计，导出报告
       */
      public boolean verifyOperationIconOnBatchList(String batchName, String checkType, String operation) {
            return verifyWebElementExists(getOperationIconOnBatchList(batchName, checkType, operation));
      }
}
