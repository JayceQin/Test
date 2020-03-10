package codesafe.automation.drivers.selenium.views.systemAdminPage;

import codesafe.automation.drivers.selenium.views.BaseView;
import org.openqa.selenium.*;

import java.util.List;

public class SysAdminView extends BaseView {
      
      /**
       * Intentionally empty constructor method to support instantiation.
       *
       * @param acDriver
       */
      public SysAdminView(WebDriver acDriver) throws Exception {
            super(acDriver);
      }
      
      
      /**
       * 获得系统管理页面的一级导航栏
       *
       * @param title 如用户管理,检测模板管理,日志管理,引擎管理,系统配置,集成管理
       */
      private WebElement getSystemPageTopLevelNavigateTitle(String title) {
            WebElement element = null;
            List<WebElement> list = findElements(By.cssSelector("li[class='angular-ui-tree-node ng-scope']"));
            for (WebElement e : list) {
                  if (e.getText().contains(title)) {
                        element = e;
                        break;
                  }
            }
            return element;
      }
      
      /**
       * 获得系统管理页面的导航栏链接
       *
       * @param title 一级导航栏,如用户管理,检测模板管理,日志管理,引擎管理,系统配置,集成管理
       * @param nav   二级导航栏 e.g. 用户,部门,角色
       */
      private WebElement getSystemPageNavigateTitle(String title, String nav) {
            WebElement element = null;
            List<WebElement> list = getSystemPageTopLevelNavigateTitle(title).findElements(By.tagName("a"));
            for (WebElement e : list) {
                  if (e.getText().equals(nav)) {
                        element = e;
                        break;
                  }
            }
            return element;
      }
      
      
      /**
       * 获取统计模板配置列表
       */
      private WebElement getStatisticsModelConfigForm() {
            return findElementThatMayNotBePresent(By.name("anaTplFrom"));
      }
      
      
      /**
       * 获取统计模板配置列表中的option项
       *
       * @param option E.g 名称，检测方式
       */
      private WebElement getStatisticsModelFormOptions(String option) {
            WebElement element = null;
            List<WebElement> lists = getStatisticsModelConfigForm().findElements(By.className("form-inline"));
            for (WebElement e : lists) {
                  if (e.findElement(By.tagName("label")).getText().contains(option)) {
                        element = e;
                  }
            }
            
            return element;
      }
      
      
      /**
       * 获取统计模板配置列表中的输入框
       *
       * @param option E.g 名称，检测方式
       */
      private WebElement getInputFieldFromStatisticsModelForm(String option) {
            return getStatisticsModelFormOptions(option).findElement(By.cssSelector("input[ng-model='anaConfigObj.formObj.templateName']"));
      }
      
      
      /**
       * 获取统计模板配置列表中的单选项
       *
       * @param option E.g 检测方式,访问权限
       */
      private WebElement getRadioButtonFromStatisticsModelForm(String option, String value) {
            WebElement element = null;
            List<WebElement> lists = getStatisticsModelFormOptions(option).findElements(By.cssSelector("label[class *= 'radio-inline']"));
            for (WebElement e : lists) {
                  if (e.getText().contains(value)) {
                        element = e.findElement(By.tagName("input"));
                  }
            }
            return element;
      }
      
      /**
       * 获取统计模板配置列表中的描述输入域
       *
       * @param option 描述
       */
      private WebElement getInputAreaFromStatisticsModelForm(String option) {
            return getStatisticsModelFormOptions(option).findElement(By.tagName("textarea"));
      }
      
      /**
       * 获取统计模板配置列表中的设置缺陷分类按钮
       */
      private WebElement getAddStatisticsModelForm(String option) {
            return getStatisticsModelFormOptions(option).findElement(By.cssSelector("i[class='glyphicon glyphicon-plus']"));
      }
      
      
      /**
       * 获取统计模板引擎选项
       */
      private WebElement getEngineListOnStatisticsModelForm(String engine) {
            return findElementThatMayNotBePresent(By.cssSelector("ul[class='dropdown-menu ng-scope'][ng-if='changeTabBool']")).findElement(By.partialLinkText(engine));
      }
      
      
      /**
       * 获取统计模板语言选项
       */
      private WebElement getDropDownListOnStatisticsModelForm(String language) {
            WebElement element = null;
            List<WebElement> lists = findElementThatMayNotBePresent(By.cssSelector("ul[class='ana-tpl-config-tool-wrap ng-scope'][ng-if='changeTabInObj[toolIn]']")).findElements(By.tagName("li"));
            for (WebElement e : lists) {
                  if (e.getText().equals(language)) {
                        element = e;
                  }
            }
            return element;
      }
      
      
      /**
       * 获取统计模板已选择语言标签
       */
      private WebElement getSeletedLanguageTab(String option, String language) {
            WebElement element = null;
            List<WebElement> lists = getStatisticsModelFormOptions(option).findElements(By.cssSelector("li[class *='ng-isolate-scope']"));
            for (WebElement e : lists) {
                  if (e.getText().contains(language)) {
                        element = e.findElement(By.cssSelector("a[ng-click='select()']"));
                  }
            }
            
            return element;
      }
      
      
      /**
       * 获取统计模板已选择语言的删除按钮
       */
      private WebElement getSeletedLanguageDeleteButton(String option, String language) {
            WebElement element = null;
            List<WebElement> lists = getStatisticsModelFormOptions(option).findElements(By.cssSelector("li[class *='ng-isolate-scope']"));
            for (WebElement e : lists) {
                  if (e.getText().contains(language)) {
                        element = e.findElement(By.cssSelector("a[ng-click='closeLanguageTab(tab)']"));
                  }
            }
            
            return element;
      }
      
      /**
       * 获取已选择语言的编辑按钮
       */
      private WebElement getSelectedLanguageEditButton(String option) {
            WebElement element = null;
            List<WebElement> lists = getStatisticsModelFormOptions(option).findElements(By.cssSelector("div[class *= 'add-type-body ng-scope']"));
            for (WebElement e : lists) {
                  if (e.findElement(By.tagName("button")).isDisplayed()) {
                        element = e.findElement(By.tagName("button"));
                  }
            }
            return element;
      }
      
      /**
       * 获取配置统计模板页面的保存按钮
       */
      private WebElement getSaveButtonOnCreateStatisticsModelPage() {
            return findElementThatMayNotBePresent(By.className("cf-ana-tpl-form-btn")).findElement(By.tagName("button"));
      }
      
      
      /**
       * 获取引擎列表
       */
      private WebElement getEngineListOnSysAdminPage() {
            return findElementThatMayNotBePresent(By.cssSelector("table[class ='table']"));
      }
      
      /**
       * 获取引擎列表中引擎的并发数
       */
      private WebElement getEngineConcurrency(String ip) {
            WebElement element = null;
            List<WebElement> list = getEngineListOnSysAdminPage().findElements(By.tagName("td"));
            for (WebElement e : list) {
                  if (e.getText().contains(ip)) {
                        element = e.findElement(By.name("editFrom"));
                        break;
                  }
            }
            return element;
      }
      
      
      /**
       * 获取配置邮箱页面中的form
       */
      private WebElement getEmailFormInSysAdminPage() {
            return findElementThatMayNotBePresent(By.name("emailFrom"));
      }
      
      /**
       * 获取配置邮箱页面中的form的输入框或按钮
       *
       * @param option 如 邮箱服务器地址
       */
      private WebElement getOptionInEmailForm(String option) {
            WebElement element = null;
            if (!option.equals("保存")) {
                  List<WebElement> list = getEmailFormInSysAdminPage().findElements(By.cssSelector("div[class='form-group cf-email-form']"));
                  for (WebElement e : list) {
                        if (e.getText().contains(option)) {
                              element = e.findElement(By.tagName("input"));
                              break;
                        }
                  }
            } else {
                  element = getEmailFormInSysAdminPage().findElement(By.className("cf-email-form-btn")).findElement(By.tagName("button"));
            }
            return element;
      }
      
      /**
       * 获取配置邮箱页面中的form的单选按钮
       *
       * @param option 如 是，否
       */
      private WebElement getOptionRadioBtnInEmailForm(String option) {
            WebElement element = null;
            List<WebElement> list = getEmailFormInSysAdminPage().findElements(By.className("radio-inline"));
            for (WebElement e : list) {
                  if (e.getText().equals(option)) {
                        element = e;
                        break;
                  }
            }
            
            return element;
      }
      
      /**
       * 获取系统清理界面中的清理起始时间输入框
       */
      private WebElement getStartTimeInCleanSystemPage() {
            return findElementThatMayNotBePresent(By.cssSelector("input[type='datetime'][ng-model='cleanObj.formData.startTime']"));
      }
      
      /**
       * 获取系统清理界面中的清理结束时间输入框
       */
      private WebElement getEndTimeInCleanSystemPage() {
            return findElementThatMayNotBePresent(By.cssSelector("input[type='datetime'][ng-model='cleanObj.formData.endTime']"));
      }
      
      /**
       * 获取系统清理界面中的多选项
       *
       * @param option 快速检测,项目管理,结果整合
       */
      private WebElement getCheckBoxButtonInCleanSystemPage(String option) {
            WebElement element = null;
            List<WebElement> elements = findElements(By.cssSelector("label[class='checkbox-inline ng-binding ng-scope']"));
            for (WebElement e : elements) {
                  if (e.getText().contains(option)) {
                        element = e.findElement(By.tagName("input"));
                  }
            }
            return element;
      }
      
      /**
       * 获取系统清理界面中的单选项
       *
       * @param option 快速检测,项目管理,结果整合
       */
      private WebElement getRadioButtonInCleanSystemPage(String option) {
            WebElement element = null;
            List<WebElement> elements = findElements(By.cssSelector("label[class='radio-inline']"));
            for (WebElement e : elements) {
                  if (e.getText().contains(option)) {
                        element = e.findElement(By.tagName("input"));
                  }
            }
            return element;
      }
      
      /**
       * 获取系统清理界面中的登录密码输入框
       */
      private WebElement getLoginPasswordInputField() {
            return findElementThatMayNotBePresent(By.cssSelector("input[ng-model='cleanObj.formData.password']"));
      }
      
      /**
       * 获取系统清理界面中的开始清理按钮
       */
      private WebElement getStartCleanButton() {
            return findElementThatMayNotBePresent(By.cssSelector("button[ng-click='cleanSys()']"));
      }
      
      /**
       * 获取系统清理界面中的继续清理按钮
       */
      private WebElement getContinueCleanButton() {
            return findElementThatMayNotBePresent(By.cssSelector("button[ng-click='continueClean()']"));
      }
      
      /**
       * 获取清理成功页面
       */
      private WebElement getCleanSuccessPage() {
            return findElementThatMayNotBePresent(By.cssSelector("section[class='system-clean ng-scope']")).findElement(By.cssSelector("p[class='clean-tips-title ng-binding']"));
      }
      
      /**
       * 获取系统清理指定日期输入框
       */
      private WebElement getCleanScheduleTimeInputField() {
            return findElementThatMayNotBePresent(By.cssSelector("input[type='datetime'][ng-model='cleanObj.formData.cleanDate']"));
      }
      
      /**
       * 获取系统清理指定日期的小时输入框
       */
      private WebElement getHourInputFieldOnCleanSystemPage() {
            return findElementThatMayNotBePresent(By.cssSelector("input[type='number'][ng-model='cleanObj.formData.hour']"));
      }
      
      /**
       * 获取系统清理指定日期的分钟输入框
       */
      private WebElement getMinInputFieldOnCleanSystemPage() {
            return findElementThatMayNotBePresent(By.cssSelector("input[type='number'][ng-model='cleanObj.formData.hour']"));
      }
      
      /**
       * 获取资源下载超链接按钮
       */
      private WebElement getSourceDownloadHyperLink() {
            return findElementThatMayNotBePresent(By.className("file-download-block")).findElement(By.tagName("a"));
      }
      
      /**
       * 获取用户初次登录修改密码表单
       */
      private WebElement getUserFirstLoginUpdatePasswordPage(){
            return findElementThatMayNotBePresent(By.name("passWdForm"));
      }
      
      
      /**
       * 获取用户初次登录修改密码表单中的旧密码输入框
       */
      private WebElement getOnePasswordInputFild(){
            return getUserFirstLoginUpdatePasswordPage().findElement(By.cssSelector("input[type='password'][name='oldPassword']"));
      }
      
      /**
       * 获取用户初次登录修改密码表单中的第一次新密码输入框
       */
      private WebElement getNewOnePasswordInputFild(){
            return getUserFirstLoginUpdatePasswordPage().findElement(By.cssSelector("input[type='password'][name='newPassword']"));
      }
      
      /**
       * 获取用户初次登录修改密码表单中的确认密码输入框
       */
      private WebElement getNewTwoPasswordInputFild(){
            return getUserFirstLoginUpdatePasswordPage().findElement(By.cssSelector("input[type='password'][name='confirmPwd']"));
      }
      
      /**
       * 获取用户初次登录修改密码表单中的提交按钮
       */
      private WebElement getSubmitButtonOnUpdatePasswordPage(){
            return getUserFirstLoginUpdatePasswordPage().findElement(By.cssSelector("button[ng-click='updatePwd()'][class='passwd-content-submit']"));
      }
      
      /**
       * 点击系统管理页面的二级导航栏链接
       *
       * @param title 一级导航栏,如用户管理,检测模板管理,日志管理,引擎管理,系统配置,集成管理
       */
      public void clickSystemPageTopLevelNavigateTitle(String title) {
            retryClick(getSystemPageTopLevelNavigateTitle(title));
      }
      
      
      /**
       * 点击系统管理页面的二级导航栏链接
       *
       * @param title 一级导航栏,如用户管理,检测模板管理,日志管理,引擎管理,系统配置,集成管理
       * @param nav   二级导航栏 e.g. 用户,部门,角色
       */
      public void clickSystemPageNavigateTitle(String title, String nav) {
            retryClick(getSystemPageNavigateTitle(title, nav));
      }
      
      
      /**
       * 在配置统计模板页面中option栏的输入框中输入value
       *
       * @param option E.g. 名称
       * @param value
       */
      public void inputFieldFromStatisticsModelForm(String option, String value) {
            inputValueToInputField(getInputFieldFromStatisticsModelForm(option), value);
      }
      
      /**
       * 在配置统计模板页面中option栏的选择值为value的单选按钮
       *
       * @param option E.g. 检测方式，访问权限
       * @param value
       */
      public void clickRadioButtonFromStatisticsModelForm(String option, String value) {
            retryClick(getRadioButtonFromStatisticsModelForm(option, value));
      }
      
      
      /**
       * 在配置统计模板页面中option栏的输入框中输入value
       *
       * @param option E.g. 描述
       * @param value
       */
      public void InputAreaFromStatisticsModelForm(String option, String value) {
            inputValueToInputField(getInputAreaFromStatisticsModelForm(option), value);
      }
      
      
      /**
       * 在配置统计模板页面中option栏点击添加语言按钮
       */
      public void clickAddStatisticsModelForm(String option) {
            retryClick(getAddStatisticsModelForm(option));
      }
      
      /**
       * 在配置统计模板页面中的下拉框中选择引擎
       */
      public void clickEngineListOnStatisticsModelForm(String engine) {
            retryClick(getEngineListOnStatisticsModelForm(engine));
      }
      
      
      /**
       * 在配置统计模板页面中的下拉框中选择语言
       */
      public void clickDropDownListOnStatisticsModelForm(String language) {
            retryClick(getDropDownListOnStatisticsModelForm(language));
      }
      
      
      /**
       * 在配置统计模板页面中的被选择语言的tab
       */
      public void clickSeletedLanguageTab(String option, String language) {
            retryClick(getSeletedLanguageTab(option, language));
      }
      
      /**
       * 在配置统计模板页面中的被选择语言tab的删除按钮
       */
      public void clickSeletedLanguageDeleteButton(String option, String language) {
            retryClick(getSeletedLanguageDeleteButton(option, language));
      }
      
      
      /**
       * 在配置统计模板页面中的被选择语言编辑按钮
       */
      public void clickSelectedLanguageEditButton(String option) {
            retryClick(getSelectedLanguageEditButton(option));
      }
      
      /**
       * 在配置统计模板页面点击保存按钮
       */
      public void clickSaveButtonOnCreateStatisticsModelPage() {
            retryClick(getSaveButtonOnCreateStatisticsModelPage());
      }
      
      /**
       * 编辑引擎并发数
       *
       * @param ip    引擎ip
       * @param value 并发数1-4
       */
      public void editEngineConcurrency(String ip, String value) {
            WebElement hyperLink = getEngineConcurrency(ip).findElement(By.tagName("a"));
            WebElement element = getEngineConcurrency(ip).findElement(By.tagName("input"));
            WebElement ipEle = findElement(By.partialLinkText(ip));
            if (verifyWebElementExists(hyperLink) && verifyWebElementExists(element) && verifyWebElementExists(ipEle)) {
                  retryClick(hyperLink);
                  inputValueToInputField(element, value);
                  retryClick(ipEle);
            }
      }
      
      /**
       * 输入邮箱配置信息
       *
       * @param option 如 邮箱服务器地址
       * @param value  输入值
       */
      public void inputEmailConfigInfoInSysAdmin(String option, String value) {
            inputValueToInputField(getOptionInEmailForm(option), value);
      }
      
      
      /**
       * 点击配置邮箱页面中的form的单选按钮
       *
       * @param value 如 是，否
       */
      public void clickRadioBtnInSysAdmin(String value) {
            retryClick(getOptionRadioBtnInEmailForm(value));
      }
      
      /**
       * 点击配置邮箱页面中的提交按钮
       */
      public void clickSubmitBtnInSysAdmin() {
            retryClick(getOptionInEmailForm("保存"));
      }
      
      
      /**
       * 输入系统清理配置页面中的开始时间
       *
       * @param startTime
       */
      public void inputStartTimeInCleanSystemPage(String startTime) {
            WebElement element = getStartTimeInCleanSystemPage();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].readOnly=false;", element);
            waitForPageToLoad();
            inputValueToInputField(element, startTime);
      }
      
      /**
       * 输入系统清理配置页面中的结束时间
       *
       * @param endTime
       */
      public void inputEndTimeInCleanSystemPage(String endTime) {
            WebElement element = getEndTimeInCleanSystemPage();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].readOnly=false;", element);
            waitForPageToLoad();
            inputValueToInputField(element, endTime);
      }
      
      /**
       * 在系统清理配置页面中勾选多选按钮
       *
       * @param option 如快速检测,项目管理,结果整合
       */
      public void clickCheckBoxButtonInCleanSystemPage(String option) {
            retryClick(getCheckBoxButtonInCleanSystemPage(option));
      }
      
      /**
       * 在系统清理配置页面中取消勾选多选按钮
       *
       * @param option 如快速检测,项目管理,结果整合
       */
      public void unclickCheckBoxButtonInCleanSystemPage(String option) {
            WebElement element = getCheckBoxButtonInCleanSystemPage(option);
            if (element.isSelected()) {
                  retryClick(getCheckBoxButtonInCleanSystemPage(option));
            }
      }
      
      /**
       * 在系统清理配置页面中点击单选按钮
       *
       * @param option 如立即执行或指定时间
       */
      public void clickRadioButtonInCleanSystemPage(String option) {
            retryClick(getRadioButtonInCleanSystemPage(option));
      }
      
      /**
       * 在系统清理配置页面中输入当前用户的登录密码
       *
       * @param password 登录密码
       */
      public void inputLoginPasswordInputField(String password) {
            inputValueToInputField(getLoginPasswordInputField(), password);
      }
      
      /**
       * 点击开始清理按钮
       */
      public void clickStartCleanButton() {
            retryClick(getStartCleanButton());
      }
      
      /**
       * 点击继续清理按钮
       */
      public void clickContinueCleanButton() {
            retryClick(getContinueCleanButton());
      }
      
      
      /**
       * 判断是否清理成功
       */
      public boolean verifyCleanSystemSucc() {
            boolean result = false;
            int wait = 500000;
            while (wait > 0) {
                  try {
                        WebElement element = getCleanSuccessPage();
                        if (element != null && element.getText().contains("系统数据清理完成")) {
                              wait = 0;
                              result = true;
                        }
                  } catch (NullPointerException | NoSuchElementException e) {
                        wait--;
                  }
            }
            
            return result;
      }
      
      /**
       * 输入开始清理日期
       */
      public void inputCleanScheduleTime(String dateTime) {
            WebElement element = getCleanScheduleTimeInputField();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].readOnly=false;", element);
            inputValueToInputField(element, dateTime);
      }
      
      /**
       * 输入开始清理日期的小时
       */
      public void inputHourInputFieldOnCleanSystemPage(String hourTime) {
            inputValueToInputField(getHourInputFieldOnCleanSystemPage(), hourTime);
      }
      
      /**
       * 输入开始清理日期的分钟
       */
      public void inputMinInputFieldOnCleanSystemPage(String minTime) {
            inputValueToInputField(getMinInputFieldOnCleanSystemPage(), minTime);
      }
      
      /**
       * 点击资源下载超链接按钮
       */
      public void clickSourceDownloadHyperLink() {
            retryClick(getSourceDownloadHyperLink());
      }
      
      /**
       * 输入旧密码
       */
      public void inputOnePasswordInputFild(String pass){
            inputValueToInputField(getOnePasswordInputFild(),pass);
      }
      
      /**
       * 第一次输入新密码
       */
      public void inputNewOnePasswordInputFild(String pass){
            inputValueToInputField(getNewOnePasswordInputFild(),pass);
      }
      
      /**
       * 确认新密码
       */
      public void inputNewTwoPasswordInputFild(String pass){
            inputValueToInputField(getNewTwoPasswordInputFild(),pass);
      }
      
      /**
       * 点击提交按钮
       */
      public void clickSubmitButtonOnUpdatePasswordPage(){
            retryClick(getSubmitButtonOnUpdatePasswordPage());
      }
}
