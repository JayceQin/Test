package codesafe.automation.testLogic.steps.UI.configTaskInfo;

import codesafe.automation.drivers.utils.SSHExecutor;
import codesafe.automation.testLogic.TeamSystem;
import codesafe.automation.testLogic.steps.StepsBase;
import net.serenitybdd.core.Serenity;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ConfigTaskInfomationSteps extends StepsBase {
      TeamSystem testingBox;
      
      public ConfigTaskInfomationSteps() throws SQLException, IOException {
            super();
            testingBox = (TeamSystem) Serenity.getCurrentSession().get("TeamSystem");
      }
      
      @When("在{配置信息页面|弹出窗}中的{上传文件}栏,上传源码文件\"$path\"")
      @Then("在{配置信息页面|弹出窗}中的{上传文件}栏,上传源码文件\"$path\"")
      public void uploadCodeFileFromKeyBoard(@Named("") String path) throws Exception {
            String workingDir = System.getProperty("user.dir");
            String filePackage = workingDir + "\\src\\test\\resources\\package\\" + path;
            String uploadExePath = workingDir + "\\src\\main\\resources\\upload.exe";
            testingBox.useViews().configTaskInformationView.uploadCodeFileFromKeyBoard(uploadExePath, filePackage);
      }
      
      
      @Given("点击\"$taskName\"的下载按钮后,确认{资源文件|报告}\"$fileName\"下载成功")
      @When("点击\"$taskName\"的下载按钮,确认{资源文件|报告}\"$fileName\"下载成功")
      @Then("点击\"$taskName\"的下载按钮,确认{资源文件|报告}\"$fileName\"下载成功")
      public void verifyReportDownloadSucc(@Named("taskName") String taskName,
                                           @Named("fileName") String fileName) throws Exception {
            String path = "C:\\Users\\lenovo\\Downloads\\" + fileName;
            File file = new File(path);
            Assert.assertTrue(taskName + "报告下载失败", file.exists());
      }
      
      @Given("点击{发起快速检测|上传检测结果}按钮后，页面跳转到了配置信息页面")
      @When("点击{发起快速检测|上传检测结果}按钮后，页面跳转到了配置信息页面")
      @Then("点击{发起快速检测|上传检测结果}按钮后，页面跳转到了配置信息页面")
      public void verifyInitiateFastCheckButtonFuction() throws Exception {
            boolean result;
            result = testingBox.useViews().configTaskInformationView.verifyConfigInformationPage();
            Assert.assertTrue("跳转失败，没有跳转到配置信息页面", result);
      }
      
      
      @Given("在配置信息页面中的\"$option\"栏,输入\"$name\"")
      @When("在配置信息页面中的\"$option\"栏,输入\"$name\"")
      @Then("在配置信息页面中的\"$option\"栏,输入\"$name\"")
      public void enterProjectNameInInfoConfigPage(@Named("option") String option,
                                                   @Named("name") String name) throws Exception {
            if (option.contains("描述")) {
                  testingBox.useViews().configTaskInformationView.inputTextAreaFromInfoConfigPage(option, name);
            } else {
                  testingBox.useViews().configTaskInformationView.inputInputFieldFromInfoConfigPage(option, name);
            }
      }
      
      @Given("在配置信息页面中的\"$option\"栏,单选\"$value\"选项")
      @When("在配置信息页面中的\"$option\"栏,单选\"$value\"选项")
      @Then("在配置信息页面中的\"$option\"栏,单选\"$value\"选项")
      public void selectLanguageOnInfoConfigPage(@Named("option") String option,
                                                 @Named("value") String value) throws Exception {
            testingBox.useViews().configTaskInformationView.selectFastCheckRadioButton(option, value);
            if (testingBox.useViews().configTaskInformationView.verifyAnotherEngineExists("Fortify")){
                  switch (value){
                        case "C/C++":
                              testingBox.useViews().configTaskInformationView.unSelectedFastCheckCheckButton("检测方式", "缺陷检测", "Fortify");
                              break;
                        case "C#":
                              testingBox.useViews().configTaskInformationView.unSelectedFastCheckCheckButton("检测方式", "缺陷检测", "Fortify");
                              break;
                        case "Java":
                              testingBox.useViews().configTaskInformationView.unSelectedFastCheckCheckButton("检测方式", "缺陷检测", "Fortify");
                              break;
                        case "Python":
                              testingBox.useViews().configTaskInformationView.unSelectedFastCheckCheckButton("检测方式", "缺陷检测", "Fortify");
                              break;
                        default:
                              break;
                  }
            }
            if (testingBox.useViews().configTaskInformationView.verifyAnotherEngineExists("CheckMarx")){
                  switch (value){
                        case "C/C++":
                              testingBox.useViews().configTaskInformationView.unSelectedFastCheckCheckButton("检测方式", "缺陷检测", "CheckMarx");
                              break;
                        case "C#":
                              testingBox.useViews().configTaskInformationView.unSelectedFastCheckCheckButton("检测方式", "缺陷检测", "CheckMarx");
                              break;
                        case "Java":
                              testingBox.useViews().configTaskInformationView.unSelectedFastCheckCheckButton("检测方式", "缺陷检测", "CheckMarx");
                              break;
                        default:
                              break;
                  }
            
            }
      }
      
      @When("在配置信息页面中的\"$option\"栏,点击按钮")
      @Then("在配置信息页面中的\"$option\"栏,点击按钮")
      public void clickScanButtonOnInfoConfigPage(@Named("option") String option) throws Exception {
            testingBox.useViews().configTaskInformationView.clickButtonWithOptionOnInfoConfigPage(option);
      }
      
      @Given("在配置信息页面中,上传文件输入框中的文字显示为\"$text\"")
      @When("在配置信息页面中,上传文件输入框中的文字显示为\"$text\"")
      @Then("在配置信息页面中,上传文件输入框中的文字显示为\"$text\"")
      public void verifyUploadFieldInputFieldTextCorrect(@Named("text") String text) throws Exception {
            boolean result = testingBox.useViews().configTaskInformationView.verifyUploadFieldInputFieldTextCorrect(text);
            Assert.assertTrue("在配置信息页面中,上传文件输入框中的文字显示不正确", result);
      }
      
      
      @Given("在配置信息页面中的\"$option\"栏,文件上传成功")
      @When("在配置信息页面中的\"$option\"栏,文件上传成功")
      @Then("在配置信息页面中的\"$option\"栏,文件上传成功")
      public void verifyResultFileUploadSuccess(@Named("option") String option) throws Exception {
            boolean result;
            result = testingBox.useViews().configTaskInformationView.verifyResultFileUploadSuccess(option);
            Assert.assertTrue("在配置信息页面中的" + option + "栏,文件上传失败", result);
      }
      
      @Given("在配置信息页面,点击{发起检测|确认}按钮")
      @Then("在配置信息页面,点击{发起检测|确认}按钮")
      public void clickInitiateCheckButton() throws Exception {
            testingBox.useViews().configTaskInformationView.clickInitiateButton();
      }
      
      @Given("在配置信息页面,点击上传结果按钮")
      @Then("在配置信息页面,点击上传结果按钮")
      public void clickUploadResultButton() throws Exception {
            testingBox.useViews().configTaskInformationView.clickUploadResultButton();
      }
      
      @Given("在配置信息页面中的\"$option\"栏,勾选\"$subOption\"中的\"$value\"选项")
      @When("在配置信息页面中的\"$option\"栏,勾选\"$subOption\"中的\"$value\"选项")
      @Then("在配置信息页面中的\"$option\"栏,勾选\"$subOption\"中的\"$value\"选项")
      public void selectDefectCheckValueOnInfoConfigPage(@Named("option") String option,
                                                         @Named("subOption") String subOption,
                                                         @Named("value") String value) throws Exception {
            testingBox.useViews().configTaskInformationView.selectedFastCheckCheckButton(option, subOption, value);
      }
      
      
      @Given("在配置信息页面中的\"$option\"栏,取消勾选\"$subOption\"中的\"$value\"选项")
      @When("在配置信息页面中的\"$option\"栏,取消勾选\"$subOption\"中的\"$value\"选项")
      @Then("在配置信息页面中的\"$option\"栏,取消勾选\"$subOption\"中的\"$value\"选项")
      public void unselectDefectCheckValueOnInfoConfigPage(@Named("option") String option,
                                                           @Named("subOption") String subOption,
                                                           @Named("value") String value) throws Exception {
            testingBox.useViews().configTaskInformationView.unSelectedFastCheckCheckButton(option, subOption, value);
      }
      
      
      @Given("在配置信息页面中的\"$option\"栏,在下拉列表中选择\"$value\"")
      @When("在配置信息页面中的\"$option\"栏,在下拉列表中选择\"$value\"")
      @Then("在配置信息页面中的\"$option\"栏,在下拉列表中选择\"$value\"")
      public void selectSolutionOnInfoConfigPage(@Named("option") String option,
                                                 @Named("subOption") String subOption,
                                                 @Named("value") String value) throws Exception {
            testingBox.useViews().configTaskInformationView.selectDropDownListValue(option, value);
      }
      
      @Given("在配置信息页面中的执行策略栏 , 勾选\"$value\"")
      @When("在配置信息页面中的执行策略栏 , 勾选\"$value\"")
      @Then("在配置信息页面中的执行策略栏 , 勾选\"$value\"")
      public void selectWeekDayFromConfigInfoPage(@Named("value") String value) throws Exception {
            testingBox.useViews().configTaskInformationView.clickCheckButtonFromExecuteFunction(value);
      }
      
      @Given("在配置信息页面中的执行策略栏,取消全部勾选的日期")
      @When("在配置信息页面中的执行策略栏,取消全部勾选的日期")
      @Then("在配置信息页面中的执行策略栏,取消全部勾选的日期")
      public void unselectAllWeekDaysFromConfigInfoPage() throws Exception {
            testingBox.useViews().configTaskInformationView.unSelectedBeginDateCheckCheckButton();
      }
      
      @Given("在配置信息页面中的执行策略栏 , 取消勾选\"$value\"")
      @When("在配置信息页面中的执行策略栏 , 取消勾选\"$value\"")
      @Then("在配置信息页面中的执行策略栏 , 取消勾选\"$value\"")
      public void unselectWeekDayFromConfigInfoPage(@Named("value") String value) throws Exception {
            testingBox.useViews().configTaskInformationView.unClickCheckButtonFromExecuteFunction(value);
      }
      
      
      @Then("在配置信息页面，分别输入Git地址\"$site\",Git分支\"$branch\",Git用户名\"$user\",Git密码\"$pass\"")
      public void inputGitInfoOnInfoConfigPage(@Named("site") String site,
                                               @Named("branch") String branch,
                                               @Named("user") String user,
                                               @Named("pass") String pass) throws Exception {
            testingBox.useViews().configTaskInformationView.inputGitInfoOnInfoConfigPage(site, branch, user, pass);
      }
      
      @Then("在配置信息页面，分别输入Svn地址\"$site\",Svn用户名\"$user\",Svn密码\"$pass\"")
      public void inputSvnInfoOnInfoConfigPage(@Named("site") String site,
                                               @Named("user") String user,
                                               @Named("pass") String pass) throws Exception {
            testingBox.useViews().configTaskInformationView.inputSvnInfoOnInfoConfigPage(site, user, pass);
      }
      
      @When("在配置信息页面中的{maven代码库|Maven仓库}栏, 验证maven库\"$value\"选择成功")
      @Then("在配置信息页面中的{maven代码库|Maven仓库}栏, 验证maven库\"$value\"选择成功")
      public void verifyMavenLibraryOnInfoConfigPage(@Named("value") String value) throws Exception {
            boolean result;
            result = testingBox.useViews().configTaskInformationView.verifyMavenLibraryShowCorrectly(value);
            Assert.assertTrue("maven库" + value + "选择失败", result);
      }
      
      
      @Given("在配置信息页面,点击\"$option\"栏的\"$linkText\"按钮")
      @When("在配置信息页面,点击\"$option\"栏的\"$linkText\"按钮")
      @Then("在配置信息页面,点击\"$option\"栏的\"$linkText\"按钮")
      public void clickHyperLinkFromConfigPage(@Named("option") String option,
                                               @Named("linkText") String linkText) throws Exception {
            testingBox.useViews().configTaskInformationView.clickHyperLinkFromConfigPage(option, linkText);
      }
      
      @Then("在函数白名单的任务列表中, 确认\"$funcName\" 函数创建成功")
      public void verifyWhiteFunctionRecord(@Named("funcName") String funcName) throws Exception {
            boolean result;
            if (funcName.length() > 20) {
                  funcName = funcName.substring(0, 20);
            }
            result = testingBox.useViews().configTaskInformationView.verifyWhiteFunctionName(funcName);
            Assert.assertTrue(funcName + "函数不存在", result);
      }
      
      
      @When("在配置信息页面是否携带栏的下拉列表,选择\"$type\"检测任务\"$value\"")
      @Then("在配置信息页面是否携带栏的下拉列表,选择\"$type\"检测任务\"$value\"")
      public void selectFastCheckBringDropDownTaskListValue(@Named("type") String type,
                                                            @Named("value") String value) throws Exception {
            if (type.equals("缺陷")) {
                  type = "isAuditQX";
            } else if (type.equals("合规")) {
                  type = "isAuditHG";
            }
            testingBox.useViews().configTaskInformationView.clickBringDropDownListHyperLink(type);
            testingBox.useViews().configTaskInformationView.selectFastCheckBringDropDownTaskListValue(type, value);
      }
      
      @When("选择携带\"$type\"检测任务\"$value\"成功")
      @Then("选择携带\"$type\"检测任务\"$value\"成功")
      public void verifyChooseBringDownTaskSucc(@Named("type") String type,
                                                @Named("value") String value) throws Exception {
            if (type.equals("缺陷")) {
                  type = "isAuditQX";
            } else if (type.equals("合规")) {
                  type = "isAuditHG";
            }
            boolean result = testingBox.useViews().configTaskInformationView.verifyChooseBringDownTaskSucc(type, value);
            Assert.assertTrue("选择携带" + type + "检测任务" + value + "失败", result);
      }
      
      
      @Given("在配置信息页面中,点击{下一步|更新项目}按钮")
      @When("在配置信息页面中,点击{下一步|更新项目}按钮")
      @Then("在配置信息页面中,点击{下一步|更新项目}按钮")
      public void clickCreatePjNextBtn() throws Exception {
            testingBox.useViews().projectManagerView.clickCreatePjLeftBtn();
      }
      
      @Given("在新建项目的配置信息页面中,点击创建项目按钮")
      @When("在新建项目的配置信息页面中,点击创建项目按钮")
      @Then("在新建项目的配置信息页面中,点击创建项目按钮")
      public void clickCreatePjInitCheckBtn() throws Exception {
            testingBox.useViews().projectManagerView.clickCreatePjRightBtn();
            testingBox.useViews().commonPopWindowView.clickInfoCloseIcon();
      }
      
      
      @Given("在配置信息页面中的执行策略栏,输入执行频率,每\"$value\"{天|周}执行")
      @When("在配置信息页面中的执行策略栏,输入执行频率,每\"$value\"{天|周}执行")
      @Then("在配置信息页面中的执行策略栏,输入执行频率,每\"$value\"{天|周}执行")
      public void selectProjectExecuteFunc(@Named("value") String value) throws Exception {
            testingBox.useViews().configTaskInformationView.clickExecuteFunctionInputFieldFromInfoConfigPage(value);
      }
      
      @Given("在配置信息页面中的执行策略栏 , 选择执行时间为当前日期(当前时间,星期X)")
      @When("在配置信息页面中的执行策略栏 , 选择执行时间为当前日期(当前时间,星期X)")
      @Then("在配置信息页面中的执行策略栏 , 选择执行时间为当前日期(当前时间,星期X)")
      public void modifyExecuteFunctionBaseNowTimeConfigOnConfigPage() throws Exception {
            SSHExecutor ssh = new SSHExecutor(testingBox.getSystemAddress(), testingBox.getServerUser(), testingBox.getServerPass());
            ssh.execute(" date +%u\n");
            ArrayList<String> stdout = ssh.getStandardOutput();
            String date = stdout.get(0);
            switch (date) {
                  case "1":
                        date = "周一";
                        break;
                  case "2":
                        date = "周二";
                        break;
                  case "3":
                        date = "周三";
                        break;
                  case "4":
                        date = "周四";
                        break;
                  case "5":
                        date = "周五";
                        break;
                  case "6":
                        date = "周六";
                        break;
                  case "7":
                        date = "周日";
                        break;
            }
            testingBox.useViews().configTaskInformationView.selectFastCheckRadioButton("执行策略", date);
      }
      
      
      @Given("在配置信息页中的执行策略栏,点击时间显示器")
      @When("在配置信息页中的执行策略栏,点击时间显示器")
      @Then("在配置信息页中的执行策略栏,点击时间显示器")
      public void clickTimeShowWidget() throws Exception {
            
            testingBox.useViews().configTaskInformationView.clickTimeShowWidget();
      }
      
      @Given("在配置信息页面中的执行策略栏 , 更改执行日期为服务器时间的\"$days\"天之后")
      @When("在配置信息页面中的执行策略栏 , 更改执行日期为服务器时间的\"$days\"天之后")
      @Then("在配置信息页面中的执行策略栏 , 更改执行日期为服务器时间的\"$days\"天之后")
      public void clickDataOnCanlendarSelector(@Named("days") int days) throws Exception {
            testingBox.useViews().configTaskInformationView.clickDataOnCalendarSelector(days);
      }
      
      
      @Given("在配置信息页面中的执行策略栏 , 更改执行时间为服务器最近的执行时间")
      @When("在配置信息页面中的执行策略栏 , 更改执行时间为服务器最近的执行时间")
      @Then("在配置信息页面中的执行策略栏 , 更改执行时间为服务器最近的执行时间")
      public void selectExecuteFunctionOnConfigPage() throws Exception {
            String projectTime = testingBox.useViews().configTaskInformationView.getProjectStartTime();
            String exeDay = projectTime.split(" ")[0];
            SSHExecutor ssh = new SSHExecutor(testingBox.getSystemAddress(), testingBox.getServerUser(), testingBox.getServerPass());
            ssh.execute("date +\"%Y-%m-%d %H:%M\"");
            ArrayList<String> stdout = ssh.getStandardOutput();
            String dateTime = stdout.get(0);
            String clientDay = dateTime.split(" ")[0];
            String clientTime = dateTime.split(" ")[1];
            String hour = clientTime.split(":")[0];
            String min = clientTime.split(":")[1];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date pt = sdf.parse(exeDay);
            Date ct = sdf.parse(clientDay);
            if (!pt.equals(ct)) {
                  testingBox.useViews().configTaskInformationView.clickDataOnCalendarSelector(0);
            }
            int h = Integer.parseInt(hour);
            int m = Integer.parseInt(min);
            int verify = m % 10;
            if (m % 5 != 0) {
                  if (verify < 5) {
                        m = m - m % 5 + 10;
                  } else {
                        m = m - m % 5 + 15;
                  }
            } else {
                  m = m + 10;
            }
            if (m >= 60) {
                  h = h + 1;
                  m = m - 60;
            }
            String serverMin = "";
            if (m < 10) {
                  serverMin = "0" + m;
            } else {
                  serverMin = m + "";
            }
            testingBox.useViews().configTaskInformationView.selectHourDropDownListOnCalendarSelector(h + "");
            testingBox.useViews().configTaskInformationView.selectMinuateDropDownListOnCalendarSelector(serverMin);
            testingBox.useViews().configTaskInformationView.clickApplyButtonOnCalendarSelector();
      }
}
