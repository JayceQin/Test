package codesafe.automation.drivers.selenium.views.commonView;

import codesafe.automation.drivers.selenium.views.BaseView;
import org.openqa.selenium.*;

import java.util.List;

public class CommonView extends BaseView {
      
      public CommonView(WebDriver acDriver) throws Exception {
            super(acDriver);
      }
      
      /**
       * 跳转到url页面
       *
       * @param urlString 网页路径
       */
      public void navigateByUrl(String urlString) {
            driver.get(urlString);
      }
      
      /**
       * 根据导航名称获取导航栏
       *
       * @param text 超链接Text
       */
      private WebElement getNavigationWithText(String text) {
            return findElementThatMayNotBePresent(By.className("nav-section")).findElement(By.className("nav-block")).findElement(By.partialLinkText(text));
      }
      
      /**
       * 获取用户下拉菜单Div
       */
      private WebElement getUserAreaDiv() {
            return findElementThatMayNotBePresent(By.cssSelector("div[class='user-block']"));
      }
      
      /**
       * 获取资源下载超链接按钮
       */
      private WebElement getSourceDownloadHyperLink() {
            return findElementThatMayNotBePresent(By.className("file-download-block")).findElement(By.tagName("a"));
      }
      
      
      /**
       * 获取用户名超链接
       *
       * @param username 用户名
       */
      private WebElement getUserHyperLinkWithText(String username) {
            return getUserAreaDiv().findElement(By.partialLinkText(username));
      }
      
      /**
       * 点击用户名后,获取下菜单中的linkText
       *
       * @param linkText e.g. 修改密码
       */
      private WebElement getUserListPopoverListMenu(String linkText) {
            return getUserAreaDiv().findElement(By.className("popover-content")).findElement(By.linkText(linkText));
      }
      
      
      /**
       * 获取页面中的操作按钮栏
       */
      private WebElement getCfContentOperation() {
            return findElement(By.className("cf-content-operation"));
      }
      
      
      /**
       * 获取页面中的操作按钮
       */
      protected WebElement getOperationButtonOnTablePage() {
            return getCfContentOperation().findElement(By.cssSelector("div[class *='cf-content-operation-right']")).findElement(By.cssSelector("button[class='search-add-btn btn btn-green']"));
      }
      
      /**
       * 获取页面中的超链接操作按钮
       *
       * @param operation e.g. 发起快速检测任务,添加用户..
       */
      protected WebElement getOperationButtonOnTablePage(String operation) {
            return getCfContentOperation().findElement(By.cssSelector("div[class *='cf-content-operation-right']")).findElement(By.partialLinkText(operation));
      }
      
      
      private WebElement getSearchTextFieldOnTablePage(){
            return getCfContentOperation().findElement(By.cssSelector("div[class *='cf-content-operation-right']")).findElement(By.className("cf-input-group")).findElement(By.tagName("input"));
      }
      
      private WebElement getSearchButtonOnTablePage(){
            return getCfContentOperation().findElement(By.cssSelector("div[class *='cf-content-operation-right']")).findElement(By.className("cf-input-group")).findElement(By.tagName("button"));
      }
      
      /**
       * 获取页面中的删除按钮
       */
      private WebElement getDeleteButtonOnTablePage() {
            return getCfContentOperation().findElement(By.cssSelector("div[class *='cf-content-operation-left']")).findElement(By.tagName("button"));
      }
      
      
      /**
       * 获取页面中的列表区域
       */
      private WebElement getCfContentTable() {
            return findElement(By.className("cf-content-table"));
      }
      
      
      /**
       * 获取页面中的列表
       */
      protected WebElement getTableOnPage() {
            return getCfContentTable().findElement(By.cssSelector("table[class *='table table-hover'] "));
      }
      
      
      /**
       * 根据名称点击导航栏
       *
       * @param text 导航栏名称
       */
      public void clickNavigationWithText(String text) {
            retryClick(getNavigationWithText(text));
      }
      
      
      /**
       * 获取页面中列表的一列
       *
       * @param name 任务名称
       */
      protected WebElement getAllTaskInTaskListTable(String name) {
            WebElement element = null;
            List<WebElement> list = getTableOnPage().findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
            for (WebElement e : list) {
                  if (!e.getAttribute("class").equals("ng-hide")){
                        if (e.getText().contains("冻结")){
                              if (e.findElement(By.cssSelector(":nth-child(2)")).getText().contains(name.trim())) {
                                    element = e;
                                    break;
                              }
                        }else{
                              try {
                                    if (e.findElement(By.cssSelector(":nth-child(2)")).getText().equals(name.trim())) {
                                          element = e;
                                          break;
                                    }else if (e.findElement(By.cssSelector(":nth-child(3)")).getText().equals(name.trim())){
                                          element = e;
                                          break;
                                    }
                              }catch (NoSuchElementException Ex){
                                    break;
                              }
                             
                        }
                  }
            }
            
            return element;
      }
      
      
      
      /**
       * 根据多重属性获取页面中列表的一列
       *
       * @param name 任务名称
       * @param type 任务类型
       */
      private WebElement getAllTaskInTaskListTable(String type, String name) {
            WebElement element = null;
            List<WebElement> list = getTableOnPage().findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
            for (WebElement e : list) {
                  if (e.getText().contains(name.trim()) && e.getText().contains(type)) {
                        element = e;
                        break;
                  }
            }
            
            return element;
      }
      
      
      /**
       * 获取页面中的搜索区域
       */
      private WebElement getCfContentSearch() {
            return findElementThatMayNotBePresent(By.cssSelector("div[class *='cf-content-search']"));
      }
      
      /**
       * 获取某一列具体的的列数
       *
       * @param rolsName 列明 E.g. 任务名称,检测语言
       */
      protected String getColsNumbersInTaskListTable(String rolsName) {
            return getColsNumbersInTable(getTableOnPage(), rolsName);
      }
      
      
      /**
       * 获取列表中的第n列
       *
       * @param name 任务名称
       */
      protected WebElement getThirdColsInTaskTable(String name, String cols) {
            return getThirdColsInTable(getAllTaskInTaskListTable(name), cols);
      }
      
      
      /**
       * 获取列表中的各个任务前的勾选框
       *
       * @param name 任务名称
       */
      private WebElement getCheckBoxButtonInTaskListTable(String name) {
            return getThirdColsInTaskTable(name, "1").findElement(By.tagName("input"));
      }
      
      
      /**
       * 获取每个页面的页码超链接按钮
       */
      private WebElement getPageNumberHyperLink(String pageNumber) {
            return getCfContentTable().findElement(By.cssSelector("div[class='text-center']")).findElement(By.linkText(pageNumber));
      }
      
      
      /**
       * 获取每个列表中某一行的操作一栏中的所有操作按钮
       *
       * @param taskName 任务名称
       * @param text     操作按钮名称
       */
      private WebElement getRowsOperationButton(String taskName, String text) {
            String cols = getColsNumbersInTaskListTable("操作");
            WebElement element = null;
            List<WebElement> lists = getThirdColsInTaskTable(taskName, cols).findElements(By.tagName("a"));
            for (WebElement e : lists) {
                  if (text.equals("修改项目配置")){
                        if (e.getAttribute("id").equals("update-project-dropdown")){
                              element = e;
                        }
                  }else{
                        if (e.getAttribute("tooltip") == null || e.getAttribute("tooltip").equals("")) {
                              if (e.getText().equals(text)) {
                                    element = e;
                                    break;
                              }
                        } else {
                              if (e.getAttribute("tooltip").equals(text)) {
                                    element = e;
                                    break;
                              }
                        }
                  }
                  
            }
            
            return element;
      }
      
      
      /**
       * 获取队列中的任务
       *
       * @param taskName 任务名称
       * @param type     检测方式
       */
      public WebElement getDeleteQueueHypeLink(String taskName, String type) {
            WebElement element = null;
            List<WebElement> elements = getAllTaskInTaskListTable(type, taskName).findElements(By.tagName("a"));
            for (WebElement e : elements) {
                  if (e.getAttribute("tooltip").equals("删除")) {
                        element = e;
                  }
            }
            return element;
      }
      
      /**
       * 获取列表中的任务名链接
       *
       * @param taskName 任务名称
       */
      private WebElement getTaskNameHyperLink(String taskName) {
            return getThirdColsInTaskTable(taskName, "2").findElement(By.tagName("a"));
      }
      
      /**
       * 验证页面跳转成功
       *
       * @param navName e.g. 首页,快速检测,项目管理...
       */
      public boolean verifyNavigatePageSuccessfully(String navName) {
            boolean result = false;
            waitForPageToLoad();
            boolean exists = verifyWebElementExists(getNavigationWithText(navName));
            if (exists) {
                  WebElement navPage = getNavigationWithText(navName);
                  if (navName.equals("首页")) {
                        if (navPage.getAttribute("class").equals("i-1 home-nav current")) {
                              result = true;
                        }
                  } else {
                        if (navPage.getAttribute("class").equals("i-2 ng-scope current")) {
                              result = true;
                        }
                  }
            }
            return result;
      }
      
      /**
       * 点击用户名
       *
       * @param username 用户名
       */
      public void clickUserHyperLinkWithText(String username) {
            retryClick(getUserHyperLinkWithText(username));
      }
      
      /**
       * 点击用户名下拉菜单中的操作按钮
       *
       * @param linkText 修改密码
       */
      public void clickUserListPopoverListMenu(String linkText) {
            retryClick(getUserListPopoverListMenu(linkText));
      }
      
      /**
       * 返回缺陷，合规，溯源检测在数据库中的code
       *
       * @param type 如缺陷检测,合规检测,溯源检测
       */
      public String getCheckTypeCode(String type) {
            String code = "";
            if (type.equals("缺陷检测")) {
                  code = "0";
            } else if (type.equals("合规检测")) {
                  code = "1";
            } else if (type.equals("溯源检测")) {
                  code = "2";
            }
            
            return code;
      }
      
      /**
       * 返回引擎在数据库中的code
       *
       * @param engine 如CodeSafe,Fortify,CheckMarx
       */
      public String getEngineCode(String engine) {
            String code = "";
            if (engine.equals("CodeSafe")) {
                  code = "0";
            } else if (engine.equals("Fortify")) {
                  code = "1";
            } else if (engine.equals("CheckMarx")) {
                  code = "2";
            }
            
            return code;
      }
      
      /**
       * 返回检测语言在数据库中的code
       *
       * @param lang
       */
      public String getLanguageCode(String lang) {
            String code = "";
            switch (lang) {
                  case "Java":
                        code = "0";
                        break;
                  case "C/C++":
                        code = "1";
                        break;
                  case "C#":
                        code = "2";
                        break;
                  case "Objective-C":
                        code = "5";
                        break;
                  case "Python":
                        code = "3";
                        break;
                  case "PHP":
                        code = "4";
                        break;
                  case "Cobol":
                        code = "6";
                        break;
                  case "NodeJS":
                        code = "9";
                        break;
                  case "Go":
                        code = "12";
                        break;
                  case "Swift":
                        code = "15";
                        break;
            }
            return code;
      }
      
      
      /**
       * 点击列表中的操作按钮
       *
       * @param operation
       */
      public void clickOperationButtonOnTablePage(String operation) {
            switch (operation) {
                  case "添加代码仓库":
                        retryClick(getOperationButtonOnTablePage());
                        break;
                  case "添加私钥":
                        retryClick(getOperationButtonOnTablePage());
                        break;
                  default:
                        retryClick(getOperationButtonOnTablePage(operation));
            }
      }
      
      /**
       * 判断列表中的数据是否存在
       *
       * @param task     名称
       * @param location 第几列
       */
      public boolean verifyTaskInTable(String task, String location) {
            waitForPageToLoad();
            boolean result = verifyWebElementExists(getAllTaskInTaskListTable(task));
            System.out.println(result);
            if (result) {
                  WebElement element = getAllTaskInTaskListTable(task).findElement(By.cssSelector(":nth-child(" + location + ")"));
                  if (element.getText().equals(task)) {
                        return true;
                  }
            }
            
            return result;
      }
      
      
      /**
       * 点击每个列表中某一行的操作一栏中的某个操作按钮
       *
       * @param taskName      任务名称
       * @param operationText 操作按钮名称
       */
      public void clickRowsOperationButton(String taskName, String operationText) {
            retryClick(getRowsOperationButton(taskName, operationText));
      }
      
      /**
       * 判断列表中值的正确
       *
       * @param name    任务名称
       * @param colName 列名
       * @param value   值
       */
      public boolean verifyColsValueCorrectlyInTaskListTable(String name, String colName, String value) {
            boolean result = false;
            String cols = getColsNumbersInTaskListTable(colName);
            WebElement element = getThirdColsInTaskTable(name, cols);
            if (element.getText().contains(value)) {
                  result = true;
            }
            return result;
      }
      
      /**
       * 勾选任务
       *
       * @param name 任务名称
       */
      public void clickCheckBoxButtonInTaskListTable(String name) {
            retryClick(getCheckBoxButtonInTaskListTable(name));
      }
      
      
      /**
       * 点击列表的删除按钮
       */
      public void clickDeleteButtonOnTablePage() {
            retryClick(getDeleteButtonOnTablePage());
      }
      
      /**
       * 点击列表中的页码
       */
      public void clickPageNumberHyperLink(String pageNumber) {
            retryClick(getPageNumberHyperLink(pageNumber));
      }
      
      
      /**
       * 删除队列中的任务
       *
       * @param taskName 任务名称
       * @param type     检测方式
       */
      public void clickDeleteQueueHypeLink(String taskName, String type) {
            retryClick(getDeleteQueueHypeLink(taskName, type));
      }
      
      
      /**
       * 判断任务队列是否创建成功
       *
       * @param taskName 任务名称
       * @param type     检测方式
       */
      public boolean verifyTaskQueueCreatedSuccessd(String taskName, String type) {
            return verifyWebElementExists(getAllTaskInTaskListTable(type, taskName));
      }
      
      /**
       * 判断maven库地址正确
       *
       * @param name  仓库名称
       * @param value maven地址
       */
      public boolean verifyMavenSiteCorrectly(String name, String value) {
            boolean result = false;
            String cols = getColsNumbersInTaskListTable("依赖库地址");
            WebElement element = getThirdColsInTaskTable(name, cols).findElement(By.tagName("a"));
            if (element.getAttribute("popover").equals(value)) {
                  result = true;
            }
            return result;
      }
      
      /**
       * 得到快速检测任务检测结果具体原因
       *
       * @param projectName 项目名称
       */
      public String getTaskStatusDetailReason(String projectName) {
            String reason = "";
            String nth = getColsNumbersInTaskListTable("检测状态");
            WebElement element = getAllTaskInTaskListTable(projectName).findElement(By.cssSelector(":nth-child(" + nth + ")"));
            if (element != null) {
                  reason = element.findElement(By.tagName("span")).getAttribute("tooltip");
            }
            
            return reason;
      }
      
      /**
       * 刷新当前页面
       */
      public void refreshCurrentPage() {
            driver.navigate().refresh();
      }
      
      /**
       * 返回上一页
       */
      public void backLastPage() {
            driver.navigate().back();
      }
      
      /**
       * 点击列表中的任务名称
       *
       * @param taskName 任务名
       */
      public void clickTaskNameHyperLink(String taskName) {
            retryClick(getTaskNameHyperLink(taskName));
      }
      
      public void inputSearchValueOnSearchField(String value){
            inputValueToInputField(getSearchTextFieldOnTablePage(),value);
      }
      
      public void clickSearchButtonOnTable(){
            retryClick(getSearchButtonOnTablePage());
      }
}