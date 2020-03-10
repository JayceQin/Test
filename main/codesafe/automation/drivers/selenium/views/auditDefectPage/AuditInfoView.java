package codesafe.automation.drivers.selenium.views.auditDefectPage;

import codesafe.automation.drivers.selenium.views.BaseView;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedList;
import java.util.List;

public class AuditInfoView extends BaseView {
      
      /**
       * Intentionally empty constructor method to support instantiation.
       *
       * @param acDriver
       */
      public AuditInfoView(WebDriver acDriver) throws Exception {
            super(acDriver);
      }
      
      /**
       * 获取审计页面中的审计概况div
       */
      private WebElement getAuditGeneralSituationOnAuditPage() {
            return findElementThatMayNotBePresent(By.cssSelector("div[class *='audit-wrapper']"));
      }
      
      /**
       * 获取审计页面中各个信息的div
       */
      private WebElement getDivOfAuditCodeSourceOption(String option) {
            WebElement element = null;
            List<WebElement> list = getAuditGeneralSituationOnAuditPage().findElements(By.className("form-inline"));
            for (WebElement e : list) {
                  if (e.getText().contains(option)) {
                        element = e;
                  }
            }
            
            return element;
      }
      
      
      /**
       * 获取审计页面中依赖库,编译日志,函数白名单三个子页面的div
       */
      private WebElement getAuditContentOnAuditPage() {
            return getAuditGeneralSituationOnAuditPage().findElement(By.cssSelector("div[class='audit-content cut-content']"));
      }
      
      /**
       * 获取结果审计中依赖库,编译日志,函数白名单的标题,点击显示不同审计内容
       *
       * @param tab 依赖库,编译日志,函数白名单
       */
      private WebElement getAuditContentTabOnAuditPage(String tab) {
            return getAuditGeneralSituationOnAuditPage().findElement(By.partialLinkText(tab));
      }
      
      /**
       * 获取结果审计中依赖库,编译日志,函数白名单的详细内容
       */
      private WebElement getTabContentFromAuditPage() {
            return getAuditContentOnAuditPage().findElement(By.className("tab-content"));
      }
      
      /**
       * 获取结果审计中函数白名单列表
       */
      private WebElement getWhiteListOnAuditPage() {
            return getAuditContentOnAuditPage().findElement(By.tagName("table"));
      }
      
      /**
       * 根据函数白名单函数名称获取结果审计中函数白名单列表中的一列
       */
      private WebElement getAllWhiteFuncOnWhiteListTableFromAuditPage(String FuncName) {
            WebElement element = null;
            List<WebElement> list = getWhiteListOnAuditPage().findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
            for (WebElement e : list) {
                  if (e.getText().contains(FuncName)) {
                        element = e;
                        break;
                  }
            }
            
            return element;
      }
      
      
      /**
       * 获取溯源审计页面中左侧的ul
       */
      private WebElement getAuditSyLeftDiv() {
            return findElementThatMayNotBePresent(By.cssSelector("ul[class = 'audit-sy-left']"));
      }
      
      
      /**
       * 获取溯源审计页面中左侧的ul下的li
       *
       * @param bugName 溯源组件名称
       */
      private WebElement getAuditDefectLi(String bugName) {
            WebElement element = null;
            List<WebElement> elements = getAuditSyLeftDiv().findElements(By.tagName("li"));
            for (WebElement e : elements) {
                  if (e.getText().contains(bugName)) {
                        element = e;
                  }
            }
            return element;
      }
      
      /**
       * 获取溯源审计页面中左侧的ul下的li下的审计标签
       * * @param bugName 溯源组件名称
       */
      private WebElement getSyDefectAuditPic(String bugName) {
            return getAuditDefectLi(bugName).findElement(By.cssSelector(":nth-child(1)"));
      }
      
      
      /**
       * 获取溯源审计页面中左侧的ul下的li下的组件HyperLink
       *
       * @param bugName 溯源组件名称
       */
      private WebElement getSyDefectAuditHyperLink(String bugName) {
            return getAuditDefectLi(bugName).findElement(By.cssSelector(":nth-child(2)"));
      }
      
      
      /**
       * 获取溯源审计页面中左侧的ul下的li下的审计选项
       *
       * @param bugName 溯源组件名称
       * @param choice  是问题或不是问题
       */
      private WebElement getSyOptionOnSyAuditPage(String bugName, String choice) {
            WebElement element = null;
            WebElement spanElement = getAuditDefectLi(bugName).findElement(By.cssSelector(":nth-child(3)"));
            if (choice.equals("是问题")) {
                  element = spanElement.findElement(By.cssSelector("span[class='red_point cursor_pointer']"));
            } else if (choice.equals("不是问题")) {
                  element = spanElement.findElement(By.cssSelector("span[class='green_point cursor_pointer']"));
            }
            
            return element;
      }
      
      /**
       * 获取审计页面中左侧缺陷列表的div
       */
      private WebElement getLeftAuditDivOnAuditPage() {
            return findElementThatMayNotBePresent(By.cssSelector("div[class='audit-left']")).findElement(By.cssSelector("div[class='cut-content2 ulblcok']"));
      }
      
      /**
       * 获取审计页面中左侧缺陷列表上方的区域
       */
      private WebElement getAuditPageSearchArea() {
            return findElementThatMayNotBePresent(By.cssSelector("div[class='audit-left']")).findElement(By.cssSelector("div[class='audit-left-header']"));
      }
      
      /**
       * 获取审计页面中左侧的Level栏中的超链接
       *
       * @param text 如高，中，低或者所有
       */
      private WebElement getAuditLevelDivOnAuditPage(String text) {
            WebElement element = null;
            List<WebElement> elements = getLeftAuditDivOnAuditPage().findElement(By.cssSelector("ul[class='nav nav-tabs']")).findElements(By.cssSelector("li[class *='ng-isolate-scope']"));
            for (WebElement e : elements) {
                  switch (text){
                        case "高":
                              if (e.getAttribute("active").equals("controlLevel.showH")) {
                                    element = e;
                              }
                              break;
                        case "中":
                              if (e.getAttribute("active").equals("controlLevel.showM")) {
                                    element = e;
                              }
                              break;
                        case "低":
                              if (e.getAttribute("active").equals("controlLevel.showL")) {
                                    element = e;
                              }
                              break;
                        case "所有":
                              if (e.getAttribute("active").equals("controlLevel.showA")) {
                                    element = e;
                              }
                              break;
                  }
                 
            }
            return element;
      }
      
      /**
       * 获取缺陷总数
       *
       * @param text 如高，中，低或者所有
       */
      private WebElement getAllBugsNumber(String text) {
            return getAuditLevelDivOnAuditPage(text).findElement(By.cssSelector("span[tooltip='总数']"));
      }
      
      private WebElement getBugsTableOnAuditPage() {
            return getLeftAuditDivOnAuditPage().findElement(By.cssSelector("div[class='tab-content']"));
      }
      
      /**
       * 根据缺陷等级获取该等级下所有缺陷的顶级元素
       *
       * @param bugLevel 缺陷等级
       */
      private WebElement getBugsLevelDiv(String bugLevel) {
            String levels = "";
            switch (bugLevel) {
                  case "高":
                        levels = "1";
                        break;
                  case "中":
                        levels = "2";
                        break;
                  case "低":
                        levels = "3";
                        break;
                  case "所有":
                        levels = "4";
                        break;
            }
            
            return getBugsTableOnAuditPage().findElement(By.cssSelector("div:nth-child(" + levels + ")"));
      }
      
      /**
       * 根据缺陷等级获取该等级下,对应一级缺陷分类li
       *
       * @param level   缺陷等级
       * @param bugType 缺陷分类
       */
      private WebElement getDefectLevelOneTypeLi(String level, String bugType) {
            String bugLevel = getBugLevelIds(level);
            WebElement element = null;
            String bugOneType = bugType.split(";")[0];
            List<WebElement> list = getBugsLevelDiv(level).findElement(By.id(bugLevel)).findElements(By.cssSelector("li[class='level0']"));
            for (WebElement e : list) {
                  if (e.findElement(By.cssSelector(":nth-child(2)")).getText().contains(bugOneType)) {
                        element = e;
                  }
            }
            return element;
      }
      
      /**
       * 根据缺陷等级获取该等级下,对应一级缺陷分类
       *
       * @param level   缺陷等级
       * @param bugType 缺陷分类
       */
      private WebElement getDefectLevelOneTypeHyperLink(String level, String bugType) {
            return getDefectLevelOneTypeLi(level, bugType).findElement(By.cssSelector(":nth-child(2)"));
      }
      
      /**
       * 根据缺陷等级获取该等级下,对应二级缺陷分类li
       *
       * @param level   缺陷等级
       * @param bugType 缺陷分类
       */
      private WebElement getDefectLevelTwoTypeLi(String level, String bugType) {
            WebElement element = null;
            String bugTwoType = bugType.split(";")[1];
            List<WebElement> list = getDefectLevelOneTypeLi(level, bugType).findElement(By.cssSelector("ul[class *='level0']")).findElements(By.cssSelector("li[class='level1']"));
            for (WebElement e : list) {
                  if (e.findElement(By.cssSelector(":nth-child(2)")).getText().contains(bugTwoType)) {
                        element = e;
                  }
            }
            return element;
      }
      
      /**
       * 根据缺陷等级获取该等级下,对应二级缺陷分类
       *
       * @param level   缺陷等级
       * @param bugType 缺陷分类
       */
      private WebElement getDefectLevelTwoTypeHyperLink(String level, String bugType) {
            if (verifyWebElementExists(getDefectLevelTwoTypeLi(level, bugType))){
                  return getDefectLevelTwoTypeLi(level, bugType).findElement(By.cssSelector(":nth-child(2)"));
            }else{
                  return null;
            }
      }
      
      /**
       * 根据缺陷等级获取该等级下,对应缺陷
       *
       * @param level   缺陷等级
       * @param bugType 缺陷分类
       * @param bugs    缺陷
       */
      private WebElement getDefectLevelThreeHyperLink(String level, String bugType, String bugs) {
            WebElement element = null;
            List<WebElement> list = getDefectLevelTwoTypeLi(level, bugType).findElement(By.cssSelector("ul[class *='level1']")).findElements(By.cssSelector("li[class='level2']"));
            for (WebElement e : list) {
                  if (e.findElement(By.cssSelector(":nth-child(2)")).getText().split("\\.")[0].equals(bugs)) {
                        element = e.findElement(By.cssSelector(":nth-child(2)"));
                  }
            }
            return element;
      }
      
      /**
       * 获取右键审计弹出窗
       */
      private WebElement getAuditSectionOnAuditPage() {
            return findElementThatMayNotBePresent(By.cssSelector("div[class ='bug-tree-rmenu bug-tree-quick-menu']"));
      }
      
      /**
       * 获取审计弹出窗中的级别单选按钮
       *
       * @param level 高，中，低，不是问题，遗留问题，是问题
       */
      private WebElement getAuditLevelRadioButton(String level) {
            WebElement element = null;
            List<WebElement> elements = getAuditSectionOnAuditPage().findElements(By.cssSelector("label[class *= 'radio-inline']"));
            for (WebElement e : elements) {
                  if (e.getText().equals(level)) {
                        element = e.findElement(By.tagName("input"));
                  }
            }
            
            return element;
      }
      
      /**
       * 获取审计页面中右下缺陷审计Tab中form中的div
       *
       * @param option 如等级，备注
       */
      private WebElement getAuditFormDiv(String option) {
            WebElement element = null;
            List<WebElement> elements = findElementThatMayNotBePresent(By.name("auditForm")).findElements(By.cssSelector("div[class = 'form-inline']"));
            for (WebElement e : elements) {
                  if (e.getText().contains(option)) {
                        element = e;
                  }
            }
            return element;
      }
      
      /**
       * 获取审计页面中右下缺陷审计Tab中的单选项
       *
       * @param value 高,中,低,是问题,不是问题
       */
      private WebElement getRadioBtnOnFormDiv(String value) {
            WebElement element = null;
            List<WebElement> elements = getAuditFormDiv("状态").findElements(By.cssSelector("label[class *='radio-inline']"));
            for (WebElement e : elements) {
                  if (e.getText().equals(value)) {
                        element = e;
                  }
            }
            return element;
      }
      
      /**
       * 获取审计页面中右下缺陷审计Tab中的textarea
       */
      private WebElement getInputAreaOnFormDiv() {
            return getAuditFormDiv("备注").findElement(By.tagName("textarea"));
      }
      
      /**
       * 获取审计页面中右下缺陷审计中的提交按钮
       */
      private WebElement getSubmitBtnOnAuditForm() {
            return findElementThatMayNotBePresent(By.name("auditForm")).findElement(By.tagName("button"));
      }
      
      /**
       * 获取审计历史的div
       */
      private WebElement getAuditHistoryFromAuditForm() {
           try {
                 return findElementThatMayNotBePresent(By.name("auditForm")).findElement(By.cssSelector("div[class *='audit-info-box']"));
           }catch (NoSuchElementException e){
                 return null;
           }
      }
      
      /**
       * 获取审计弹出窗中的备注输入框
       */
      private WebElement getAuditMarkTextArea() {
            return getAuditSectionOnAuditPage().findElement(By.tagName("textarea"));
      }
      
      
      /**
       * 获取审计弹出窗中的弹出按钮
       */
      private WebElement getSubmitButtonOnAuditPopWindow() {
            return getAuditSectionOnAuditPage().findElement(By.tagName("button"));
      }
      
      
      /**
       * 获取审计页面中显示方式下拉框
       */
      private WebElement getDefectShowStyleOnAuditLeft() {
            return getAuditPageSearchArea().findElement(By.cssSelector("select[class *='form-control']"));
      }
      
      /**
       * 获取审计页面中高级搜索按钮
       */
      private WebElement getAdvancedSearchHyperLink() {
            return getAuditPageSearchArea().findElement(By.cssSelector("a[tooltip='高级搜索'][data-ng-click='advancdfilterBug()']"));
      }
      
      
      /**
       * 判断当前页面是否为审计页面
       */
      public boolean verifyAuditDefectPage() {
            return verifyWebElementExists(getAuditGeneralSituationOnAuditPage());
      }
      
      
      /**
       * 点击审计页面中的子标题
       *
       * @param tab 函数白名单，依赖库，编译日志
       */
      public void clickAuditContentTabOnAuditPage(String tab) {
            retryClick(getAuditContentTabOnAuditPage(tab));
      }
      
      
      /**
       * 验证审计页面中maven依赖库是否存在
       *
       * @param library maven依赖库中的包,每个包以逗号隔开
       */
      public boolean verifyMavenDependencyLibraryFromAuditPage(String library) {
            boolean result = true;
            boolean temp;
            String[] lib = library.split(";");
            for (String str : lib) {
                  temp = getTabContentFromAuditPage().getText().contains(str);
                  if (!temp) {
                        result = false;
                  }
            }
            
            return result;
      }
      
      
      /**
       * 判断审计页面中函数白名单的名称是否正确
       *
       * @param FuncName 函数白名单名称
       */
      public boolean verifyWhiteFunctionNameOnAuditPage(String FuncName) {
            boolean result = false;
            WebElement element = getAllWhiteFuncOnWhiteListTableFromAuditPage(FuncName).findElement(By.cssSelector(":nth-child(1)"));
            if (element != null && element.getText().contains(FuncName)) {
                  result = true;
            }
            return result;
      }
      
      
      /**
       * 在审计页面源码来源栏判断Git或Svn地址显示正确
       *
       * @param type 为Git或Svn
       * @param site Git地址或Svn地址
       */
      public boolean verifyDefectsOfAuditCodeSourceOption(String type, String site) {
            WebElement element = getDivOfAuditCodeSourceOption("源码来源");
            if (element != null && element.getText().contains(type) && element.getText().contains(site)) {
                  return true;
            } else {
                  return false;
            }
      }
      
      
      /**
       * 在审计页面源码来源栏判断JDK版本显示正确
       */
      public boolean verifyJDKVersionOnAuditPage(String version) {
            WebElement element = getDivOfAuditCodeSourceOption("JDK版本");
            if (element != null && element.getText().contains(version)) {
                  return true;
            } else {
                  return false;
            }
      }
      
      
      /**
       * 在审计页面源码来源栏判断是J2EE工程显示正确
       */
      public boolean verifyJ2EEProjectOnAuditPage(String result) {
            WebElement element = getDivOfAuditCodeSourceOption("是J2EE工程");
            if (element != null && element.getText().contains(result)) {
                  return true;
            } else {
                  return false;
            }
      }
      
      
      /**
       * 点击溯源组件
       *
       * @param bugName 溯源组件名称
       */
      public void clickSyDefectHyperLink(String bugName) {
            retryClick(getSyDefectAuditHyperLink(bugName));
      }
      
      /**
       * 审计溯源组件
       *
       * @param bugName 溯源组件名称
       * @param choice  是问题或不是问题
       */
      public void auditSyDefect(String bugName, String choice) {
            retryClick(getSyOptionOnSyAuditPage(bugName, choice));
      }
      
      
      public String getBugLevelIds(String level) {
            String target = "";
            switch (level) {
                  case "高":
                        target = "h_bugs";
                        break;
                  case "中":
                        target = "m_bugs";
                        break;
                  case "低":
                        target = "l_bugs";
                        break;
                  case "所有":
                        target = "a_bugs";
                        break;
            }
            return target;
      }
      
      
      /**
       * 判断审计溯源组件是否成功
       *
       * @param bugName 溯源组件名称
       * @param choice  是问题或不是问题
       */
      public boolean verifySyDefectAuditRecord(String bugName, String choice) {
            boolean result = false;
            WebElement element = getSyDefectAuditPic(bugName);
            if (choice.equals("是问题")) {
                  if (element.getAttribute("class").equals("red_point")) {
                        result = true;
                  }
            } else if (choice.equals("不是问题")) {
                  if (element.getAttribute("class").equals("green_point")) {
                        result = true;
                  }
            }
            
            return result;
      }
      
      /**
       * 点击审计页面中左侧的header栏中的超链接
       *
       * @param text 如高，中，低或者所有
       */
      public void clickAuditLevelDivOnAuditPage(String text) {
            retryClick(getAuditLevelDivOnAuditPage(text));
      }
      
      /**
       * 判断检测任务的缺陷总数正确
       *
       * @param text    如高，中，低或者所有
       * @param numbers 缺陷总数
       */
      public boolean verifyProjectBugNumbersCorrectly(String text, String numbers) {
            return getAllBugsNumber(text).getText().equals(numbers);
      }
      
      
      /**
       * 点击buglevel等级下bugType缺陷分类缺陷bugName
       *
       * @param bugLevel 缺陷等级
       * @param bugName  缺陷名称
       * @param bugType  缺陷分类
       */
      public void clickDefectsWithBugLevelAndBugType(String bugLevel, String bugType, String bugName) {
            retryClick(getDefectLevelThreeHyperLink(bugLevel, bugType, bugName));
      }
      
      
      /**
       * 在审计页面,展开缺陷分类
       *
       * @param bugLevel 缺陷等级
       * @param bugType  缺陷名称
       */
      public void expandAuditBugTypes(String bugLevel, String bugType) {
            String str[] = bugType.split(";");
            if (str.length == 1) {
                  WebElement level1 = getDefectLevelOneTypeHyperLink(bugLevel, bugType);
                  Actions actions = new Actions(driver);
                  actions.doubleClick(level1);
                  actions.perform();
            } else if (str.length == 2) {
                  WebElement level1 = getDefectLevelOneTypeHyperLink(bugLevel, bugType);
                  Actions actions = new Actions(driver);
                  actions.doubleClick(level1).perform();
                  simpleSleep(1000);
                  WebElement level2 = getDefectLevelTwoTypeHyperLink(bugLevel, bugType);
                  actions.doubleClick(level2).perform();
            }
      }
      
      
      /**
       * 右键点击并审计缺陷
       *
       * @param level      缺陷等级
       * @param bugType    缺陷分类
       * @param bugName    缺陷名称
       * @param auditLevel 审计等级 如高，中，低，不是问题，遗留问题
       * @param mark       备注
       */
      public void rightClickAndAuditBug(String level, String bugType, String bugName, String auditLevel, String mark) {
            String str[] = bugName.split(";");
            Actions actions = new Actions(driver);
            if (str.length == 1) {
                  WebElement element = getDefectLevelThreeHyperLink(level, bugType, bugName);
                  actions.contextClick(element).perform();
                  WebElement audit = getAuditLevelRadioButton(auditLevel);
                  retryClick(audit);
                  WebElement auditMark = getAuditMarkTextArea();
                  auditMark.sendKeys(mark);
                  retryClick(getSubmitButtonOnAuditPopWindow());
            } else {
                  actions.keyDown(Keys.SHIFT).perform();
                  for (String s : str) {
                        WebElement element = getDefectLevelThreeHyperLink(level, bugType, s);
                        retryClick(element);
                        actions.moveToElement(element);
                  }
                  actions.keyUp(Keys.SHIFT).perform();
                  actions.contextClick().perform();
                  WebElement audit = getAuditLevelRadioButton(auditLevel);
                  retryClick(audit);
                  WebElement auditMark = getAuditMarkTextArea();
                  auditMark.sendKeys(mark);
                  retryClick(getSubmitButtonOnAuditPopWindow());
            }
      }
      
      
      /**
       * 判断审计页面中函数白名单是否被应用
       *
       * @param FuncName1 函数白名单名称-1级缺陷分类
       * @param FuncName2 函数白名单名称-2级级缺陷分类
       */
      public boolean verifyWhiteFunctionOnAuditLeft(String FuncName1, String FuncName2) {
            String str = FuncName1 + ";" + FuncName2;
            return !verifyWebElementExists(getDefectLevelTwoTypeHyperLink("所有", str));
      }
      
      
      /**
       * 判断审计是否成功
       *
       * @param user   审计人员
       * @param level  缺陷等级
       * @param remark 缺陷备注
       */
      public boolean verifyAuditSuccessfully(String user, String level, String remark) {
            boolean result = false;
            WebElement element = getAuditHistoryFromAuditForm();
            level = "审计信息:" + level;
            if (element != null) {
                  if (element.getText().contains(user) && element.getText().contains(level) && element.getText().contains(remark)) {
                        result = true;
                  }
            }
            return result;
      }
      
      
      /**
       * 选择审计页面中右下缺陷审计Tab中的单选项
       *
       * @param value 高,中,低,是问题,不是问题
       */
      public void selectRadioBtnOnAuditForm(String value) {
            retryClick(getRadioBtnOnFormDiv(value));
      }
      
      /**
       * 填写备注
       * 审计页面中右下缺陷审计Tab中的textarea
       */
      public void inputRemarkOnAuditForm(String value) {
            WebElement element = getInputAreaOnFormDiv();
            element.sendKeys(value);
      }
      
      /**
       * 点击审计页面中的提交按钮
       */
      public void clickSubmitBtnOnAuditForm() {
            retryClick(getSubmitBtnOnAuditForm());
      }
      
      /**
       * 判断缺陷没有被审计
       */
      public boolean verifyDefectNotAudited() {
            return verifyWebElementExists(getAuditHistoryFromAuditForm());
      }
      
      
      /**
       * 在审计页面选择缺陷选择方式
       *
       * @param value 文件或分类
       */
      public void selectDefectShowStyle(String value) {
            Select select = new Select(getDefectShowStyleOnAuditLeft());
            select.selectByVisibleText(value);
      }
      
      /**
       * 点击高级搜索
       */
      public void clickAdvancedSearchButton() {
            retryClick(getAdvancedSearchHyperLink());
      }
      
      
      /**
       * 判断高级搜索是否生效
       *
       * @param bugName
       */
      public boolean verifyAdvancedSearch(String bugName) {
            List<WebElement> elements = getLeftAuditDivOnAuditPage().findElements(By.tagName("a"));
            boolean result = false;
            String types[] = bugName.split(";");
            LinkedList<String> defects = new LinkedList<>();
            for (WebElement e : elements) {
                  for (String str : types) {
                        if (e.getText().contains(str)) {
                              defects.add(str);
                        }
                  }
            }
            if (defects.size() == types.length && defects.size() != 0) {
                  result = true;
            }
            
            return result;
      }
      
      
      /**
       * 判断审计页面只有指定的缺陷类型
       *
       * @param level
       * @param bugs
       */
      public boolean verifyBugsTypeNumberCorrectly(String level, String bugs) {
            boolean result = false;
            String[] abugs = bugs.split(";");
            String bugLevel = "";
            switch (level) {
                  case "中":
                        bugLevel = "m_bugs";
                        break;
                  case "高":
                        bugLevel = "h_bugs";
                        break;
                  case "低":
                        bugLevel = "l_bugs";
                        break;
                  case "所有":
                        bugLevel = "a_bugs";
                        break;
            }
            LinkedList<String> defects = new LinkedList<>();
            List<WebElement> element = getLeftAuditDivOnAuditPage().findElement(By.id(bugLevel)).findElements(By.tagName("a"));
            for (WebElement e : element) {
                  defects.add(e.getText());
            }
            if (defects.size() == abugs.length && defects.size() > 0) {
                  result = true;
            }
            return result;
      }
}
