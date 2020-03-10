package codesafe.automation.drivers.selenium.views.popWindowView;

import codesafe.automation.drivers.selenium.views.BaseView;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedList;
import java.util.List;

public class CommonPopWindowView extends BaseView {
      
      /**
       * Intentionally empty constructor method to support instantiation.
       *
       * @param acDriver
       */
      public CommonPopWindowView(WebDriver acDriver) throws Exception {
            super(acDriver);
      }
      
      /**
       * 获取操作弹出窗 如:修改密码弹出窗，访问权限弹出窗，导出报告弹出窗,状态监控弹出窗
       */
      private WebElement getPopWindow() {
            return findElementThatMayNotBePresent(By.className("modal-dialog")).findElement(By.className("modal-content"));
      }
      
      /**
       * 获取操作弹出窗标题
       */
      private WebElement getPopWindowTitle() {
            return getPopWindow().findElement(By.tagName("h3"));
      }
      
      
      /**
       * 根据option获取操作弹出窗中的区域
       *
       * @param option 弹出窗中的区域
       */
      private WebElement getFieldFromPopWindow(String option) {
            WebElement element = null;
            List<WebElement> list = getPopWindow().findElements(By.cssSelector("div[class *='form-group']"));
            for (WebElement e : list) {
                  String text = e.getText().replaceAll("\\*", "").split("：")[0].trim();
                  if (text.equals(option)) {
                        element = e;
                  }
            }
            return element;
      }
      
      
      /**
       * 根据option获取弹出窗中的下三角下拉图标
       */
      private WebElement getPopWindowDropDownList(String option) {
            return getFieldFromPopWindow(option).findElement(By.cssSelector("span[class='glyphicon glyphicon-triangle-bottom form-control-icon-right cursor_pointer']"));
      }
      
      
      /**
       * 根据option获取弹出窗中的下拉列表
       *
       * @param option
       */
      private WebElement getDropDownListOnPopWindow(String option) {
            return getFieldFromPopWindow(option).findElement(By.tagName("select"));
      }
      
      /**
       * 获取弹出窗中的ul列表
       */
      private WebElement getSpecificULOnPopWindow(String option) {
            WebElement element = null;
            List<WebElement> temp = getPopWindow().findElements(By.cssSelector("ul[class *='ztree']"));
            if (temp.size() > 1) {
                  for (WebElement e : temp) {
                        if (option.equals("访问权限")) {
                              if (e.getAttribute("datas").equals("users")) {
                                    element = e;
                              }
                        } else if (option.equals("缺陷分类") || option.equals("违规分类")) {
                              if (e.getAttribute("datas").equals("rules")) {
                                    element = e;
                              }
                        } else if (option.equals("上级领导")) {
                              if (e.getAttribute("id").equals("userTree")) {
                                    element = e;
                              }
                        } else if (option.equals("部门")) {
                              if (e.getAttribute("id").equals("orgTree") || e.getAttribute("id").equals("slTree")) {
                                    element = e;
                              }
                        }
                  }
            } else {
                  element = temp.get(0);
            }
            
            return element;
      }
      
      
      /**
       * 根据option获取操作弹出窗中的输入框
       */
      private WebElement getPopWindowInputField(String option) {
            WebElement element = null;
            WebElement e = getFieldFromPopWindow(option);
            if (option.equals("描述")) {
                  if (e.getText().contains(option)) {
                        element = e.findElement(By.tagName("textarea"));
                  }
            } else {
                  if (e.getText().contains(option)) {
                        element = e.findElement(By.tagName("input"));
                  }
            }
            return element;
      }
      
      
      /**
       * 获取操作弹出窗中底部左侧按钮
       */
      private WebElement getSaveBtnOnPopWindow() {
            return getPopWindow().findElement(By.cssSelector("div[class='modal-footer ng-scope']>button:first-child"));
      }
      
      /**
       * 获取操作弹出窗中底部右侧按钮
       */
      private WebElement getCloseBtnOnPopWindow() {
            return getPopWindow().findElement(By.cssSelector("div[class='modal-footer ng-scope']>button:last-child"));
      }
      
      /**
       * 获取操作弹出窗中的下一步按钮
       */
      private WebElement getNextStepsBtnOnPopWindow() {
            return getPopWindow().findElement(By.cssSelector("div[class='bug-pre-next-btn']>button:first-child"));
      }
      
      
      private WebElement getSaveButtonOnPopWindowBottm(){
            return getPopWindow().findElement(By.cssSelector("div[class='modal-footer ng-scope']")).findElement(By.tagName("button"));
      }
      
      private WebElement getCheckBoxOnPopWindowBottm(){
            return getPopWindow().findElement(By.cssSelector("div[class='modal-footer ng-scope disabled-footer']")).findElement(By.tagName("input"));
      }
      
      /**
       * 获取弹出窗中单选框
       *
       * @param option 如跟踪路径，修复建议等
       * @param value  是 ，否
       */
      private WebElement getRadioBtnOnPopWindow(String option, String value) {
            WebElement element = null;
            List<WebElement> list = getFieldFromPopWindow(option).findElements(By.cssSelector("label[class *='radio-inline']"));
            for (WebElement e : list) {
                  if (e.getText().contains(value)) {
                        element = e.findElement(By.tagName("input"));
                  }
            }
            return element;
      }
      
      /**
       * 获取上传资源文件浏览按钮
       */
      private WebElement getSourceUploadScanButton() {
            return getPopWindow().findElement(By.id("codeUpload"));
      }
      
      /**
       * 获取弹出窗中option栏的浏览按钮
       *
       * @param option e.g. 上传文件
       */
      private WebElement getScanButtonOnAddCodePopWindow(String option) {
            return getFieldFromPopWindow(option).findElement(By.tagName("button"));
      }
      
      
      /**
       * 获取弹出窗中的部门或用户所在的li元素
       *
       * @param option 参数为上级领导或部门
       * @param value  参数为部门或用户的名称
       */
      private WebElement getSpecificAreaOnPopWindow(String option, String value) {
            WebElement element = null;
            List<WebElement> list = getSpecificULOnPopWindow(option).findElements(By.tagName("li"));
            for (WebElement e : list) {
                  if (value.equals("全选")) {
                        if (e.getText().contains(value)) {
                              element = e;
                        }
                  } else {
                        if (e.getText().equals(value)) {
                              element = e;
                        }
                  }
            }
            
            return element;
      }
      
      /**
       * 获取弹出窗中下拉列表的全选勾选框的li
       */
      private WebElement getSelectAllOnPopWindow(String option) {
            return getSpecificAreaOnPopWindow(option, "全选").findElement(By.cssSelector("span:nth-child(2)"));
      }
      
      
      /**
       * 获取弹出窗中多选框
       *
       * @param option 如缺陷等级等
       * @param value  高，中，低
       */
      private WebElement getCheckBoxBtnOnPopWindow(String option, String value) {
            WebElement element = null;
            List<WebElement> list = getFieldFromPopWindow(option).findElements(By.cssSelector("label[class *='checkbox-inline']"));
            if (value.length() > 4) {
                  value = value.substring(0, 4);
            }
            for (WebElement e : list) {
                  if (e.getText().contains(value)) {
                        element = e.findElement(By.tagName("input"));
                  }
            }
            return element;
      }
      
      /**
       * 获取弹出窗中的超链接
       */
      private WebElement getHyperLinkOnPopWindow(String value) {
            return getPopWindow().findElement(By.partialLinkText(value));
      }
      
      
      /**
       * 获取弹出窗中的函数白名单列表，并根据函数名选择函数白名单s
       *
       * @param funcName 函数名
       */
      private WebElement getSafeFunctionUlOnPopWindow(String funcName) {
            WebElement element = null;
            List<WebElement> list = getFieldFromPopWindow("选择函数").findElement(By.tagName("ul")).findElements(By.tagName("li"));
            for (WebElement e : list) {
                  if (e.findElement(By.cssSelector("div[class='safe-fn-white-list-title ng-binding'")).getText().equals(funcName)) {
                        element = e.findElement(By.tagName("input"));
                  }
            }
            return element;
      }
      
      /**
       * 获取弹出窗中的SvnGit地址输入框
       */
      private WebElement getPopWindowSvnGitUriInputField() {
            return getPopWindow().findElement(By.name("svnGitUri"));
      }
      
      /**
       * 获取弹出窗中的Git分支输入框
       */
      private WebElement getPopWindowGitBranchInputField() {
            return getPopWindow().findElement(By.name("gitBranchName"));
      }
      
      
      /**
       * 获取弹出窗中的SvnGit用户名输入框
       */
      private WebElement getPopWindowSvnGitUserNameInputField() {
            return getPopWindow().findElement(By.name("svnGitUserName"));
      }
      
      
      /**
       * 获取弹出窗中的SvnGit密码输入框
       */
      private WebElement getPopWindowSvnGitPassInputField() {
            return getPopWindow().findElement(By.name("svnGitPwd"));
      }
      
      /**
       * 获取设置权限弹出窗中的右侧显示区域
       */
      private WebElement getShowAuthorityUserFieldOnPopWindow() {
            return getPopWindow().findElement(By.cssSelector("div[class='cf-user-transfer ng-scope']")).findElement(By.className("cf-user-transfer-right"));
      }
      
      
      /**
       * 获取确认弹出窗 如:点击删除选中项时确认删除的弹出窗
       */
      private WebElement getConfirmWindow() {
            return findElementThatMayNotBePresent(By.id("confirm-modal"));
      }
      
      /**
       * 获取确认弹出窗中状态信息的关闭图标X
       */
      public WebElement getInfoCloseIcon() {
            return findElementThatMayNotBePresent(By.className("close"));
      }
      
      
      /**
       * 获取确认弹出窗中的保存按钮
       */
      private WebElement getSaveBtnOnConfirmWindow() {
            return getConfirmWindow().findElement(By.cssSelector("div[class='confirm-footer ng-scope']>button:first-child"));
      }
      
      /**
       * 获取确认弹出窗中的关闭按钮
       */
      private WebElement getCloseBtnOnConfirmWindow() {
            return getConfirmWindow().findElement(By.cssSelector("div[class='confirm-footer ng-scope']>button:last-child"));
      }
      
      
      /**
       * 获取错误信息弹出Tab
       */
      private WebElement getErrorPopTabDiv() {
            return findElementThatMayNotBePresent(By.cssSelector("div[class='break-word alert ng-isolate-scope alert-error alert-dismissible'][role='alert'][type='error']"));
      }
      
      /**
       * 获取错误信息
       */
      private WebElement getErrorMessage() {
            return getErrorPopTabDiv().findElement(By.cssSelector("span[class='ng-binding ng-scope']"));
      }
      
      
      /**
       * 在弹出窗中展开部门
       *
       * @param option 上级部门
       * @param value  部门
       */
      public void expandSpecificFiledOnPopWindow(String option, String value) {
            String[] temp = value.split(";");
            for (String str : temp) {
                  WebElement element = getSpecificAreaOnPopWindow(option, str).findElement(By.tagName("span"));
                  retryClick(element);
            }
      }
      
      
      /**
       * 在弹出窗中对缺陷分类或权限进行勾选
       *
       * @param option 如缺陷分类，权限
       * @param value
       */
      public void clickAccessRightOnRightList(String option, String value) {
            String[] temp = value.split(";");
            for (String str : temp) {
                  WebElement element = getSpecificAreaOnPopWindow(option, str).findElement(By.cssSelector("span:nth-child(2)"));
                  retryClick(element);
            }
      }
      
      
      /**
       * 在弹出窗中对部门进行点击
       *
       * @param option 上级领导
       * @param value  部门
       */
      public void clickDropDownListValue(String option, String value) {
            WebElement element = getSpecificAreaOnPopWindow(option, value).findElement(By.tagName("a"));
            retryClick(element);
      }
      
      
      /**
       * 在弹出窗中对全选勾选框进行勾选
       */
      public void clickOnSelectAllOnPopWindow(String option) {
            WebElement element = getSelectAllOnPopWindow(option);
            retryClick(element);
      }
      
      
      /**
       * 对弹出窗中的权限列表中的权限进行展开
       *
       * @param option 如缺陷分类，权限
       * @param right  权限
       */
      public void expandAccessRightOnAccessList(String option, String right) {
            waitForPageToLoad();
            WebElement element = getSpecificAreaOnPopWindow(option, right).findElement(By.tagName("a"));
            Actions actions = new Actions(driver);
            actions.doubleClick(element).perform();
            waitForPageToLoad();
      }
      
      
      /**
       * 勾选弹出窗中多选框
       *
       * @param option 如角色
       * @param value  如系统管理员等
       */
      public void clickCheckBoxBtnOnPopWindow(String option, String value) {
            retryClick(getCheckBoxBtnOnPopWindow(option, value));
      }
      
      /**
       * 取消勾选弹出窗中多选框
       *
       * @param option 如角色
       * @param value  如系统管理员等
       */
      public void unClickCheckBoxBtnOnPopWindow(String option, String value) {
            WebElement element = getCheckBoxBtnOnPopWindow(option, value);
            element.sendKeys(Keys.SPACE);
      }
      
      /**
       * 勾选弹出窗中单选框
       *
       * @param option 如跟踪路径，修复建议等
       * @param value  是 ，否
       */
      public void clickRadioBtnOnPopWindow(String option, String value) {
            retryClick(getRadioBtnOnPopWindow(option, value));
      }
      
      /**
       * 点击弹出窗中的浏览按钮
       */
      public void clickSourceUploadScanButton() {
           // retryClick(getSourceUploadScanButton());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",getSourceUploadScanButton());
      }
      
      
      /**
       * 单击弹出窗中的超链接按钮
       */
      public void clicHyperLinkOnPopWindow(String value) {
            retryClick(getHyperLinkOnPopWindow(value));
      }
      
      
      /**
       * 获取操作弹出窗中的输入框，并输入
       *
       * @param option 弹出窗中对应的区域
       * @param value  将要输入的值
       */
      public void inputValueInTextFiledOnPopWindows(String option, String value) {
            inputValueToInputField(getPopWindowInputField(option), value);
      }
      
      /**
       * 点击操作弹出窗中的底部左侧按钮
       */
      public void clickSaveBtnOnPopWindow() {
            retryClick(getSaveBtnOnPopWindow());
      }
      
      
      /**
       * 展开弹出窗中的下拉列表
       *
       * @param option 如上级部门，上级领导，部门等
       */
      public void expandDropDownListOnPopWindow(String option) {
            retryClick(getPopWindowDropDownList(option));
      }
      
      /**
       * 选择下拉列表中的value
       *
       * @param option 如开发语言
       * @param value  如java等
       */
      public void selectDropDownListValueOnPopWindow(String option, String value) {
            Select select = new Select(getDropDownListOnPopWindow(option));
            select.selectByVisibleText(value);
      }
      
      /**
       * 选择函数白名单
       */
      public void selectSafeFunctionOnPopWindow(String funcName) {
            retryClick(getSafeFunctionUlOnPopWindow(funcName));
      }
      
      
      /**
       * 判断当前弹出窗为title
       *
       * @param title 弹出窗标题
       */
      public boolean verifyPopWindowTitleText(String title) {
            boolean result = false;
            waitForPageToLoad();
            boolean exist = verifyWebElementExists(getPopWindowTitle());
            if (exist) {
                  WebElement element = getPopWindowTitle();
                  if (element.getText().equals(title)) {
                        result = true;
                  }
            }
            return result;
      }
      
      
      /**
       * 判断弹出窗的生成/保存按钮不可点击
       */
      public boolean verifySaveOrExportButtonDisable() {
            boolean result = false;
            WebElement element = getSaveBtnOnPopWindow();
            if (element.getAttribute("disabled").equals("true")) {
                  result = true;
            }
            
            return result;
      }
      
      /**
       * 点击弹出窗中的关闭/取消按钮
       */
      public void clickCloseBtnOnPopWindow() {
            retryClick(getCloseBtnOnPopWindow());
      }
      
      
      /**
       * 点击弹出窗中的下一步按钮
       */
      public void clickNextStepsBtnOnPopWindow() {
            retryClick(getNextStepsBtnOnPopWindow());
      }
      
      
      public void clickCheckBoxOnPopWindowBottm(){
            retryClick(getCheckBoxOnPopWindowBottm());
      }
      
      public void clickSaveButtonOnPopWindowBottm(){
            retryClick(getSaveButtonOnPopWindowBottm());
      }
      
      /**
       * 判断缺陷导出报告窗口中违规类型是不是只有一种类型
       */
      public boolean verifyDefectTypeOnExportReportWindow(String option, String type) {
            boolean result = false;
            List<WebElement> list = getFieldFromPopWindow(option).findElements(By.tagName("a"));
            String types[] = type.split(";");
            LinkedList<String> defects = new LinkedList<>();
            for (WebElement e : list) {
                  if (!e.getText().equals("全选")) {
                        defects.add(e.getText());
                  }
            }
            if (defects.size() == types.length && defects.size() != 0) {
                  result = true;
            }
            
            return result;
      }
      
      /**
       * 点击弹出窗中option栏的浏览按钮
       *
       * @param option e.g. 上传文件
       */
      public void clickScanButtonOnAddCodePopWindow(String option) {
            retryClick(getScanButtonOnAddCodePopWindow(option));
      }
      
      
      /**
       * 判断弹出窗中上传文件栏中的结果文件或源代码包是否上传成功
       */
      public boolean verifyResultFileUploadSuccessOnPopWindows() {
            boolean result = false;
            int wait = 500000;
            while (wait > 0) {
                  try {
                        WebElement element = getFieldFromPopWindow("上传文件").findElement(By.cssSelector("div[class='detail-upload-code-tips ng-scope']")).findElement(By.tagName("span"));
                        if (element != null && element.getText().equals("上传成功")) {
                              wait = 0;
                              result = true;
                        }
                  } catch (NoSuchElementException | NullPointerException | StaleElementReferenceException e) {
                        wait = wait - 1;
                  }
                  
            }
            return result;
      }
      
      /**
       * 在弹出窗中输入SvnGit仓库地址
       *
       * @param uri 地址
       */
      public void inputPopWindowSvnGitUriInputField(String uri) {
            inputValueToInputField(getPopWindowSvnGitUriInputField(), uri);
      }
      
      /**
       * 在弹出窗中输入SvnGit密码
       *
       * @param branch Git分支名
       */
      public void inputPopWindowGitBranchInputField(String branch) {
            inputValueToInputField(getPopWindowGitBranchInputField(), branch);
      }
      
      /**
       * 在弹出窗中输入SvnGit用户名
       *
       * @param username 用户名
       */
      public void inputSvnGitUserNameOnPopWindow(String username) {
            inputValueToInputField(getPopWindowSvnGitUserNameInputField(), username);
      }
      
      /**
       * 在弹出窗中输入SvnGit密码
       *
       * @param pass 密码
       */
      public void inputSvnGitPassOnPopWindow(String pass) {
            inputValueToInputField(getPopWindowSvnGitPassInputField(), pass);
      }
      
      /**
       * 判断在弹出窗中选择人员选择成功
       *
       * @param value 设置权限 人员(部门)，多个人员以;隔开
       */
      public boolean verifySetPermissionSucc(String value) {
            boolean result = false;
            String temp[] = value.split(";");
            if (temp.length == 1) {
                  List<WebElement> elements = getShowAuthorityUserFieldOnPopWindow().findElements(By.className("cf-user-org-title"));
                  for (WebElement e : elements) {
                        if (e.getText().equals(value)) {
                              result = true;
                        }
                  }
            } else {
                  boolean tempRes = false;
                  List<WebElement> elements = getShowAuthorityUserFieldOnPopWindow().findElements(By.className("cf-user-org-title"));
                  for (WebElement e : elements) {
                        tempRes = false;
                        for (String s : temp) {
                              if (e.getText().equals(s)) {
                                    tempRes = true;
                              }
                        }
                  }
                  if (tempRes) {
                        result = true;
                  }
            }
            
            return result;
      }
      
      /**
       * 点击弹出窗中的状态信息的关闭图标X
       */
      public void clickInfoCloseIcon() {
            WebElement element = getConfirmWindow();
            if (!element.getAttribute("class").contains("ng-hide")) {
                  if (getInfoCloseIcon().isDisplayed()){
                        retryClick(getInfoCloseIcon());
                  }
            }
      }
      
      /**
       * 点击确认弹出窗中的保存/确认按钮
       */
      public void clickSaveBtnOnConfirmWindow() {
            retryClick(getSaveBtnOnConfirmWindow());
      }
      
      /**
       * 点击确认弹出窗中的关闭/取消按钮
       */
      public void clickCloseBtnOnConfirmWindow() {
            retryClick(getCloseBtnOnConfirmWindow());
      }
      
      
      /**
       * 判断错误信息正确
       *
       * @param errMsg 错误信息
       */
      public boolean verifyErrorMsgPopUp(String errMsg) {
            boolean result = false;
            if (verifyWebElementExists(getErrorMessage())) {
                  if (getErrorMessage().getText().equals(errMsg)) {
                        result = true;
                  }
                  retryClick(getInfoCloseIcon());
            }
            return result;
      }
      
      
      /**
       * 判断错误弹出窗存在
       */
      public boolean verifyErrorMsgExist() {
            return verifyWebElementExists(getErrorPopTabDiv());
      }
      
      public boolean verifyPopWindowExist() {
            try {
                  if (findElement(By.className("modal-dialog")) != null ) {
                        return true;
                  }else{
                        return false;
                  }
            } catch (NullPointerException | NoSuchElementException e) {
                  return false;
            }
      }
}
