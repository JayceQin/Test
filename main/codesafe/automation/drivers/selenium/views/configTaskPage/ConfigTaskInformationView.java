package codesafe.automation.drivers.selenium.views.configTaskPage;

import codesafe.automation.drivers.selenium.views.BaseView;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.List;

public class ConfigTaskInformationView extends BaseView {
      
      /**
       * Intentionally empty constructor method to support instantiation.
       *
       * @param acDriver
       */
      public ConfigTaskInformationView(WebDriver acDriver) throws Exception {
            super(acDriver);
      }
      
      /**
       * 获取配置信息页面
       */
      private WebElement getConfigInformationPage() {
            return findElementThatMayNotBePresent(By.name("checkForm"));
      }
      
      /**
       * 获取新建项目配置信息页面
       */
      private WebElement getPjConfigInformationPage() {
            return findElementThatMayNotBePresent(By.cssSelector("div[class='cf-project-generate-section pro-wrap content ng-scope']"));
      }
      
      
      /**
       * 获取配置信息页面中的各个栏目的父元素
       */
      private WebElement getOptionsFromInfoConfig(String option) {
            WebElement element = null;
            List<WebElement> list = findElements(By.cssSelector("div[class *='form-inline']"));
            for (WebElement e : list) {
                  if (e.getText().contains(option)) {
                        element = e;
                        break;
                  }
            }
            return element;
      }
      
      
      /**
       * 获取配置信息页面多层form-inline
       */
      private WebElement getSubOptionsFromInfoConfig(String option, String subOption) {
            WebElement element = null;
            if (verifyWebElementExists(getOptionsFromInfoConfig(option))){
                  List<WebElement> list = getOptionsFromInfoConfig(option).findElements(By.cssSelector("div[class *='form-inline']"));
                  for (WebElement e : list) {
                        if (e.getText().contains(subOption)) {
                              element = e;
                              break;
                        }
                  }
            }
            return element;
      }
      
      
      /**
       * 获取配置信息页面中的检测类型栏
       *
       * @param option 选项类型，如缺陷检测
       * @param value  选项
       */
      private WebElement getSubOptionAreaFromInfoConfig(String option, String subOption, String value) {
            WebElement element = null;
            List<WebElement> list = getSubOptionsFromInfoConfig(option, subOption).findElements(By.cssSelector("div[class *='form-group']"));
            for (WebElement tem : list) {
                  if (tem.findElement(By.tagName("span")).getText().contains(value)) {
                        element = tem;
                        break;
                  }
            }
            return element;
      }
      
      /**
       * 获取配置信息页面中检测类型栏或检测目标栏中的勾选框
       *
       * @param option 选项类型，如缺陷检测
       * @param value  选项
       */
      private WebElement getSubOptionCheckBoxFromInfoConfig(String option, String subOption, String value) {
            return getSubOptionAreaFromInfoConfig(option, subOption, value).findElement(By.tagName("input"));
      }
      
      /**
       * 获取配置信息页面的检测类型栏或检测目标栏中的下拉框
       *
       * @param option    选项类型，如检测类型
       * @param subOption 选项类型，如缺陷检测
       */
      private WebElement getDropDownSelectFromInfoConfig(String option, String subOption) {
            return getSubOptionsFromInfoConfig(option, subOption).findElement(By.tagName("select"));
      }
      
      /**
       * 获取配置信息页面中的输入框
       *
       * @param option e.g.解决方案栏
       */
      private WebElement getInputFieldFromInfoConfigPage(String option) {
            return getOptionsFromInfoConfig(option).findElement(By.tagName("input"));
      }
      
      /**
       * 获取任务描述输入文本域
       */
      private WebElement getTaskDescriptionTextArea(String option) {
            return getOptionsFromInfoConfig(option).findElement(By.tagName("textarea"));
      }
      
      
      /**
       * 获取配置信息页option栏中的按钮
       *
       * @param option e.g.上传结果文件或上传源代码包
       */
      private WebElement getButtonOnInfoConfigPage(String option) {
            return getOptionsFromInfoConfig(option).findElement(By.tagName("button"));
      }
      
      /**
       * 获取配置信息页option栏中的超链接按钮
       *
       * @param option e.g.设置函数白名单
       */
      private WebElement getHyperLinkButtonOnInfoConfigPage(String option) {
            return getOptionsFromInfoConfig(option).findElement(By.tagName("a"));
      }
      
      
      /**
       * 获取配置信息页面中的单选项
       *
       * @param option 单选类型，如开发语言
       * @param radio  选项，如C/C++
       */
      private WebElement getRadioButtonFromInfoConfig(String option, String radio) {
            WebElement element = null;
            List<WebElement> list = getOptionsFromInfoConfig(option).findElements(By.tagName("label"));
            for (WebElement e : list) {
                  if (e.getText().equals(radio)) {
                        element = e.findElement(By.tagName("input"));
                        break;
                  }
            }
            return element;
      }
      
      
      /**
       * 获取上传本地文件栏的输入框
       */
      private WebElement getUploadFileInputField() {
            return findElementThatMayNotBePresent(By.id("filename"));
      }
      
      /**
       * 获取发起检测按钮
       */
      private WebElement getInitiateCheckButton() {
            return findElement(By.className("cf-quick-btn-group")).findElement(By.tagName("button"));
      }
      
      /**
       * 获取上传结果按钮
       */
      private WebElement getUploadResultButton() {
            return findElement(By.className("cf-result-btn-group")).findElement(By.tagName("button"));
      }
      
      
      /**
       * 获取配置信息页面中执行策略的下拉框
       *
       * @param option 如执行策略
       */
      private WebElement getDropDownListOnConfigPage(String option) {
            WebElement element;
            if (option.contains("缺陷检测") || option.contains("合规检测")) {
                  if (option.contains("目标")) {
                        element = getDropDownSelectFromInfoConfig("检测目标", option);
                  } else {
                        element = getDropDownSelectFromInfoConfig("检测方式", option);
                  }
            } else {
                  element = getOptionsFromInfoConfig(option).findElement(By.tagName("select"));
            }
            return element;
      }
      
      
      /**
       * 获取配置信息页面中执行策略的checkButton
       *
       * @param value 选项，如周一
       */
      private WebElement getExecuteFunctionCheckButtonFromInfoConfigPage(String value) {
            WebElement element = null;
            List<WebElement> list = getOptionsFromInfoConfig("执行策略").findElements(By.cssSelector("label[class *='checkbox-inline']"));
            for (WebElement tem : list) {
                  if (tem.getText().contains(value)) {
                        element = tem.findElement(By.tagName("input"));
                        break;
                  }
            }
            return element;
      }
      
      private List<WebElement> getUnSelectedBeginDateCheckCheckButton() {
            return getOptionsFromInfoConfig("执行策略").findElements(By.tagName("input"));
      }
      
      
      /**
       * 获取配置信息页面中的SvnOrGit地址输入框
       */
      private WebElement getSvnGitUriInputField() {
            return findElementThatMayNotBePresent(By.name("svnGitUri"));
      }
      
      
      /**
       * 获取配置信息页面中的SvnOrGit用户名输入框
       */
      private WebElement getSvnGitUserInputField() {
            return findElementThatMayNotBePresent(By.name("svnGitUserName"));
      }
      
      
      /**
       * 获取配置信息页面中的SvnOrGit密码输入框
       */
      private WebElement getSvnGitPassInputField() {
            return findElementThatMayNotBePresent(By.name("svnGitPwd"));
      }
      
      
      /**
       * 获取配置信息页面中的Git分支输入框
       */
      private WebElement getGitBranchInputField() {
            return findElementThatMayNotBePresent(By.name("gitBranchName"));
      }
      
      
      /**
       * 获取函数白名单列表
       */
      private WebElement getWhiteListArea() {
            return findElementThatMayNotBePresent(By.cssSelector("div[class='white-list-item']"));
      }
      
      
      /**
       * 获取配置信息页面中maven代码库栏的已添加的maven库
       *
       * @param site 已添加的maven库
       */
      private WebElement getMavenLibraryShowInputFieldFromInfoConfigPage(String site) {
            WebElement element = null;
            List<WebElement> list = getOptionsFromInfoConfig("Maven仓库").findElements(By.tagName("span"));
            for (WebElement e : list) {
                  if (e.getText().contains(site)) {
                        element = e;
                        break;
                  }
            }
            
            return element;
      }
      
      
      /**
       * 根据函数白名单中的函数名获取函数白名单列表
       */
      private WebElement getAllWhiteFuncInWhiteListTable(String FuncName) {
            WebElement element = null;
            List<WebElement> list = getWhiteListArea().findElements(By.cssSelector("tbody > tr"));
            for (WebElement e : list) {
                  if (e.getText().contains(FuncName)) {
                        element = e.findElement(By.cssSelector(":nth-child(1)"));
                        break;
                  }
            }
            return element;
      }
      
      
      /**
       * 根据LinkText获取option栏的超链接按钮
       *
       * @param option
       * @param linkText
       */
      private WebElement getHyperLinkFromConfigPage(String option, String linkText) {
            return getOptionsFromInfoConfig(option).findElement(By.partialLinkText(linkText));
      }
      
      
      /**
       * 获取配置信息页面中的是否携带下拉框
       *
       * @param ngShow 只有两个输入项"QX"代表选择一个缺陷检测任务，"HG"代表选择一个合规检测任务
       */
      private WebElement getBringDropDownListFromInfoConfig(String ngShow) {
            WebElement element = null;
            if (ngShow.equals("isAuditQX")) {
                  element = findElementThatMayNotBePresent(By.className("select2-block-group")).findElement(By.cssSelector("div[ng-show='isAuditQX']"));
            } else if (ngShow.equals("isAuditHG")) {
                  element = findElementThatMayNotBePresent(By.className("select2-block-group")).findElement(By.cssSelector("div[ng-show='isAuditHG']"));
            }
            
            return element;
      }
      
      
      /**
       * 获取配置信息页面中的是否携带下拉框中的超链接
       *
       * @param ngShow 只有两个输入项"QX"代表选择一个缺陷检测任务，"HG"代表选择一个合规检测任务
       */
      private WebElement getBringDropDownListHyperLink(String ngShow) {
            WebElement element = null;
            if (ngShow.equals("isAuditQX")) {
                  element = getBringDropDownListFromInfoConfig(ngShow).findElement(By.cssSelector("a[placeholder = '选择一个缺陷检测任务']"));
            } else if (ngShow.equals("isAuditHG")) {
                  element = getBringDropDownListFromInfoConfig(ngShow).findElement(By.cssSelector("a[placeholder = '选择一个合规检测任务']"));
            }
            
            return element;
      }
      
      
      /**
       * 获取配置信息页面中的是否携带下拉框中的值
       *
       * @param ngShow 只有两个输入项"QX"代表选择一个缺陷检测任务，"HG"代表选择一个合规检测任务
       * @param value  要选择的检测任务
       */
      private WebElement getBringDropDownListValue(String ngShow, String value) {
            WebElement element = null;
            List<WebElement> elements = getBringDropDownListFromInfoConfig(ngShow).findElements(By.tagName("span"));
            for (WebElement e : elements) {
                  if (ngShow.equals("isAuditQX")) {
                        if (e.getAttribute("title").equals(value)) {
                              element = e;
                        }
                  } else if (ngShow.equals("isAuditHG")) {
                        if (e.getText().equals(value)) {
                              element = e;
                        }
                  }
            }
            return element;
      }
      
      
      /**
       * 获取配置信息页面中执行策略中的输入框
       */
      private WebElement getExecuteFunctionInputFieldFromInfoConfigPage() {
            return getOptionsFromInfoConfig("执行策略").findElement(By.className("project-exe-rate")).findElement(By.tagName("input"));
      }
      
      /**
       * 获取配置信息页面中执行策略栏的时间选择器
       */
      private WebElement getExecuteTimeSelector() {
            return getOptionsFromInfoConfig("执行策略").findElement(By.id("exetime"));
      }
      
      /**
       * 获取配置信息页面中执行策略栏的时间显示控件
       */
      private WebElement getTimeShow() {
            return getExecuteTimeSelector().findElement(By.cssSelector("span[class='ng-binding'][ng-bind='condition.addStart']"));
      }
      
      /**
       * 获取时间选择器控件
       */
      private WebElement getCalendarSelector() {
            WebElement element = null;
            List<WebElement> lists = findElements(By.cssSelector("div[class='daterangepicker ltr show-ranges single show-calendar opensleft']"));
            if (lists.size() == 1) {
                  element = lists.get(0);
            } else {
                  element = lists.get(lists.size() - 1);
            }
            return element;
      }
      
      /**
       * 获取时间选择器控件中的小时下拉框
       */
      private WebElement getHourDropDownListOnCalendarSelector() {
            return getCalendarSelector().findElement(By.className("hourselect"));
      }
      
      /**
       * 获取时间选择器控件中的分钟下拉框
       */
      private WebElement getMinuateDropDownListOnCalendarSelector() {
            return getCalendarSelector().findElement(By.className("minuteselect"));
      }
      
      
      /**
       * 获取时间选择器控件中的应用按钮
       */
      private WebElement getApplyButtonOnCalendarSelector() {
            return getCalendarSelector().findElement(By.className("drp-buttons")).findElement(By.cssSelector("button[class='applyBtn btn btn-sm btn-primary']"));
      }
      
      
      /**
       * 获取当前时间三天后的日期
       *
       * @param days 天数
       */
      private WebElement getDataOnCalendarSelector(int days) {
            int todayTrNum = 0;
            List<WebElement> lists = getCalendarSelector().findElement(By.className("table-condensed")).findElement(By.tagName("tbody")).findElements(By.tagName("td"));
            for (WebElement e : lists) {
                  if (e.getAttribute("class").contains("today")) {
                        todayTrNum = lists.indexOf(e);
                  }
            }
            return lists.get(todayTrNum + days);
      }
      
      
      /**
       * 验证是否跳转到配置信息页面
       */
      public boolean verifyConfigInformationPage() {
            waitForPageToLoad();
            return verifyWebElementExists(getConfigInformationPage());
      }
      
      
      /**
       * 实现上传文件功能
       */
      public void uploadCodeFileFromKeyBoard(String exePath, String filePath) {
            Runtime runtime = Runtime.getRuntime();
            try {
                  runtime.exec(exePath + " " + "chrome" + " " + filePath);
            } catch (IOException e) {
                  e.printStackTrace();
            }
      }
      
      /**
       * 验证是否跳转到新建项目的配置信息页面
       */
      public boolean verifyPjConfigInformationPage() {
            waitForPageToLoad();
            return verifyWebElementExists(getPjConfigInformationPage());
      }
      
      /**
       * 在配置信息页面中的解决方案栏中输入C#项目的解决方案
       *
       * @param option 解决方案
       * @param value  解决方案
       */
      public void inputInputFieldFromInfoConfigPage(String option, String value) {
            waitForPageToLoad();
            inputValueToInputField(getInputFieldFromInfoConfigPage(option), value);
      }
      
      
      /**
       * 在任务描述文本域中输入任务描述
       *
       * @param option 如项目描述，任务描述
       * @param value  要输入的内容
       */
      public void inputTextAreaFromInfoConfigPage(String option, String value) {
            waitForPageToLoad();
            inputValueToInputField(getTaskDescriptionTextArea(option), value);
      }
      
      /**
       * 点击option中的浏览按钮
       *
       * @param option e.g. 上传文件栏
       */
      public void clickButtonWithOptionOnInfoConfigPage(String option) {
            if (option.equals("函数白名单")) {
                  retryClick(getHyperLinkButtonOnInfoConfigPage(option));
            } else {
                  retryClick(getButtonOnInfoConfigPage(option));
            }
      }
      
      /**
       * 选择配置信息页面中的单选项
       *
       * @param option 单选类型，如开发语言
       * @param radio  选项，如C/C++
       */
      public void selectFastCheckRadioButton(String option, String radio) {
            retryClick(getRadioButtonFromInfoConfig(option, radio));
      }
      
      /**
       * 验证上传文件栏输入框中的文字正确
       *
       * @param text
       */
      public boolean verifyUploadFieldInputFieldTextCorrect(String text) {
            boolean result = false;
            WebElement element = getUploadFileInputField();
            if (element.getAttribute("placeholder").equals(text)) {
                  result = true;
            }
            return result;
      }
      
      
      /**
       * 判断option栏中的结果文件或源代码包是否上传成功
       */
      public boolean verifyResultFileUploadSuccess(String option) {
            boolean result = false;
            int wait = 500000;
            WebElement element = null;
            while (wait > 0) {
                  try {
                        if (option.equals("上传结果文件")) {
                              element = getOptionsFromInfoConfig(option).findElement(By.cssSelector("div[ng-if='uploadObj.resultObj.action_complete']"));
                        } else if (option.equals("上传源代码包")) {
                              element = getOptionsFromInfoConfig(option).findElement(By.cssSelector("div[ng-show='uploadObj.codeObj.action_complete']"));
                        } else if (option.equals("上传文件")) {
                              element = getOptionsFromInfoConfig(option).findElement(By.cssSelector("div[ng-show='action_complete']"));
                        }
                        if (element != null && element.getText().equals("上传成功")) {
                              wait = 0;
                              result = true;
                        }
                  } catch (NoSuchElementException | NullPointerException e) {
                        wait = wait - 1;
                  }
                  
            }
            
            return result;
      }
      
      
      /**
       * 点击发起检测按钮或确认按钮
       */
      public void clickInitiateButton() {
            retryClick(getInitiateCheckButton());
      }
      
      /**
       * 点击上传结果按钮
       */
      public void clickUploadResultButton() {
            retryClick(getUploadResultButton());
      }
      
      /**
       * 勾选配置信息页面中的checkbutton,如codesafe引擎的勾选框
       *
       * @param option 选项类型，如缺陷检测
       * @param value  选项，如CodeSafe
       */
      public void selectedFastCheckCheckButton(String option, String subOption, String value) {
            retryClick(getSubOptionCheckBoxFromInfoConfig(option, subOption, value));
      }
      
      /**
       * 取消勾选配置信息页面中的checkbutton,如codesafe引擎的勾选框
       *
       * @param option 选项类型，如缺陷检测
       * @param value  选项，如CodeSafe
       */
      public void unSelectedFastCheckCheckButton(String option, String subOption, String value) {
            waitForPageToLoad();
            WebElement lan = getSubOptionCheckBoxFromInfoConfig(option, subOption, value);
            lan.sendKeys(Keys.SPACE);
      }
      
      /**
       * 选择配置信息页面中的option下拉列表中的选择(C/C++,C#)
       *
       * @param option 解决方案
       * @param value  下拉列表中的选项
       */
      public void selectDropDownListValue(String option, String value) {
            waitForPageToLoad();
            Select s1 = new Select(getDropDownListOnConfigPage(option));
            s1.selectByVisibleText(value);
      }
      
      /**
       * 在执行策略勾选checkbutton
       *
       * @param value 如周一
       */
      public void clickCheckButtonFromExecuteFunction(String value) {
            WebElement element = getExecuteFunctionCheckButtonFromInfoConfigPage(value);
            retryClick(element);
      }
      
      /**
       * 在执行策略栏取消勾选checkButton
       *
       * @param value 如周一
       */
      public void unClickCheckButtonFromExecuteFunction(String value) {
            WebElement element = getExecuteFunctionCheckButtonFromInfoConfigPage(value);
            element.sendKeys(Keys.SPACE);
      }
      
      /**
       * 取消勾选执行策略栏的所有已勾选的日期
       */
      public void unSelectedBeginDateCheckCheckButton() {
            waitForPageToLoad();
            List<WebElement> list = getUnSelectedBeginDateCheckCheckButton();
            for (WebElement tem : list) {
                  if (tem.getAttribute("type").equals("checkbox")) {
                        if (tem.isSelected()) {
                              tem.sendKeys(Keys.SPACE);
                        }
                  }
            }
      }
      
      
      /**
       * 在配置信息页面中，输入Svn信息
       *
       * @param site Svn地址
       * @param user Svn用户名
       * @param pass Svn密码
       */
      public void inputSvnInfoOnInfoConfigPage(String site, String user, String pass) {
            waitForPageToLoad();
            inputValueToInputField(getSvnGitUriInputField(), site);
            inputValueToInputField(getSvnGitUserInputField(), user);
            inputValueToInputField(getSvnGitPassInputField(), pass);
      }
      
      /**
       * 在配置信息页面中输入Git信息
       *
       * @param site   Git地址
       * @param branch Git分支名称
       * @param user   Git用户名
       * @param pass   Git密码
       */
      public void inputGitInfoOnInfoConfigPage(String site, String branch, String user, String pass) {
            waitForPageToLoad();
            inputValueToInputField(getSvnGitUriInputField(), site);
            if (!branch.equals("")) {
                  inputValueToInputField(getGitBranchInputField(), branch);
            }
            inputValueToInputField(getSvnGitUserInputField(), user);
            inputValueToInputField(getSvnGitPassInputField(), pass);
      }
      
      
      /**
       * 判断maven代码库是否添加成功
       *
       * @param value 已配置的maven代码库
       */
      public boolean verifyMavenLibraryShowCorrectly(String value) {
            return verifyWebElementExists(getMavenLibraryShowInputFieldFromInfoConfigPage(value));
      }
      
      /**
       * 点击根据LinkText获取option栏的超链接按钮
       *
       * @param option
       * @param linkText
       */
      public void clickHyperLinkFromConfigPage(String option, String linkText) {
            retryClick(getHyperLinkFromConfigPage(option, linkText));
      }
      
      /**
       * 判断函数白名单名称是否正确
       *
       * @param FuncName 函数白名单名称
       */
      public boolean verifyWhiteFunctionName(String FuncName) {
            boolean result = false;
            WebElement element = getAllWhiteFuncInWhiteListTable(FuncName);
            if (element.getText().contains(FuncName)) {
                  result = true;
            }
            return result;
      }
      
      /**
       * 点击配置信息页面中的是否携带下拉框中的超链接
       *
       * @param type 只有两个输入项,"QX"代表选择一个缺陷检测任务，"HG"代表选择一个合规检测任务
       */
      public void clickBringDropDownListHyperLink(String type) {
            retryClick(getBringDropDownListHyperLink(type));
      }
      
      /**
       * 配置信息页面中的是否携带下拉框
       *
       * @param type  只有两个输入项,"QX"代表选择一个缺陷检测任务，"HG"代表选择一个合规检测任务
       * @param value 下拉列表中的选项
       */
      public void selectFastCheckBringDropDownTaskListValue(String type, String value) {
            retryClick(getBringDropDownListValue(type, value));
      }
      
      /**
       * 判断选择携带的快速检测任务选择成功
       *
       * @param type  只有两个输入项,"QX"代表选择一个缺陷检测任务，"HG"代表选择一个合规检测任务
       * @param value 下拉列表中的选项
       */
      public boolean verifyChooseBringDownTaskSucc(String type, String value) {
            boolean result = false;
            WebElement element = getBringDropDownListHyperLink(type).findElement(By.cssSelector("span[ng-bind='$select.selected.taskVO.taskName']"));
            if (element != null && element.getText().equals(value)) {
                  result = true;
            }
            
            return result;
      }
      
      /**
       * 输入配置信息页面中执行策略中的执行频率
       */
      public void clickExecuteFunctionInputFieldFromInfoConfigPage(String value) {
            inputValueToInputField(getExecuteFunctionInputFieldFromInfoConfigPage(), value);
      }
      
      
      /**
       * 在开始执行时间中输入开始日期
       *
       * @param beginDate 开始日期
       */
      public void inputDateInBeginDateField(String beginDate) {
            WebElement element = getTimeShow();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].innerText=\"" + beginDate + "\"", element);
      }
      
      /**
       * 点击时间显示器
       */
      public void clickTimeShowWidget() {
            retryClick(getTimeShow());
      }
      
      /**
       * 获取项目开始时间
       */
      public String getProjectStartTime() {
            return getTimeShow().getText();
      }
      
      
      /**
       * 在时间选择器的小时下拉框中选取value
       *
       * @param value 0-24
       */
      public void selectHourDropDownListOnCalendarSelector(String value) {
            Select select = new Select(getHourDropDownListOnCalendarSelector());
            select.selectByVisibleText(value);
      }
      
      /**
       * 在时间选择器的分钟下拉框中选取value
       *
       * @param value 0-60
       */
      public void selectMinuateDropDownListOnCalendarSelector(String value) {
            Select select = new Select(getMinuateDropDownListOnCalendarSelector());
            select.selectByVisibleText(value);
      }
      
      /**
       * 点击当前时间days天后的日期
       *
       * @param days 天数
       */
      public void clickDataOnCalendarSelector(int days) {
            retryClick(getDataOnCalendarSelector(days));
      }
      
      /**
       * 点击时间选择器控件中的应用按钮
       */
      public void clickApplyButtonOnCalendarSelector() {
            retryClick(getApplyButtonOnCalendarSelector());
      }
      
      
      public boolean verifyAnotherEngineExists(String value) {
            if (verifyWebElementExists(getSubOptionsFromInfoConfig("检测方式", "缺陷检测"))){
                  WebElement element = getSubOptionsFromInfoConfig("检测方式", "缺陷检测");
                  if (element.getText().contains(value)) {
                        return true;
                  } else {
                        return false;
                  }
            }else{
                  return false;
            }
            
      }
}