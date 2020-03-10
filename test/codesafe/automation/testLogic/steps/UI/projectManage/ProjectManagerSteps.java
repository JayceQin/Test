package codesafe.automation.testLogic.steps.UI.projectManage;

import codesafe.automation.drivers.utils.SSHExecutor;
import codesafe.automation.drivers.utils.Time;
import codesafe.automation.testLogic.TeamSystem;
import codesafe.automation.testLogic.steps.StepsBase;
import net.serenitybdd.core.Serenity;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProjectManagerSteps extends StepsBase {
      
      TeamSystem testingBox;
      
      public ProjectManagerSteps() throws SQLException, IOException {
            super();
            testingBox = (TeamSystem) Serenity.getCurrentSession().get("TeamSystem");
      }
      
      @Given("在项目信息页面,点击\"$linkText\"导航按钮,返回{项目|源代码}列表")
      @When("在项目信息页面,点击\"$linkText\"导航按钮,返回{项目|源代码}列表")
      @Then("在项目信息页面,点击\"$linkText\"导航按钮,返回{项目|源代码}列表")
      public void clickPMNavigationOnProjectInfoPage(@Named("linkText") String linkText) throws Exception {
            testingBox.useViews().projectManagerView.clickPMNavigationOnProjectInfoPage(linkText);
      }
      
      
      @Given("在项目信息页面,点击导航按钮\"$navi\"")
      @When("在项目信息页面,点击导航按钮\"$navi\"")
      @Then("在项目信息页面,点击导航按钮\"$navi\"")
      public void clickNavigationTabOnProjectInfoPage(@Named("navi") String navi) throws Exception {
            testingBox.useViews().projectManagerView.clickNavigationTabOnProjectInfoPage(navi);
      }
      
      @Given("在项目信息页面,点击按钮\"$navi\"")
      @When("在项目信息页面,点击按钮\"$navi\"")
      @Then("在项目信息页面,点击按钮\"$navi\"")
      public void clickCheckImmediatelyButton() throws Exception {
            testingBox.useViews().commonView.clickOperationButtonOnTablePage("立即检测");
      }
      
      
      @Given("在项目列表中,项目\"$projectName\"的检测语言\"$language\",检测模式\"$mode\",创建者\"$creator\"显示正确")
      @When("在项目列表中,项目\"$projectName\"的检测语言\"$language\",检测模式\"$mode\",创建者\"$creator\"显示正确")
      @Then("在项目列表中,项目\"$projectName\"的检测语言\"$language\",检测模式\"$mode\",创建者\"$creator\"显示正确")
      public void verifyProjectInfoCorrect(@Named("projectName") String projectName,
                                           @Named("creator") String creator,
                                           @Named("mode") String mode,
                                           @Named("language") String language) throws Exception {
            boolean result1, result2, result3, result4;
            if (projectName.length() > 20) {
                  projectName = projectName.substring(0, 20);
            }
            result2 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(projectName, "检测语言", language);
            Assert.assertTrue(projectName + "项目的检测语言不正确", result2);
            result3 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(projectName, "创建者", creator);
            Assert.assertTrue(projectName + "项目的创建者不正确", result3);
            result4 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(projectName, "检测模式", mode);
            Assert.assertTrue(projectName + "项目的检测模式不正确", result4);
      }
      
      @Given("在检测记录列表中,检测批次\"$batchName\"执行")
      @When("在检测记录列表中,检测批次\"$batchName\"执行")
      @Then("在检测记录列表中,检测批次\"$batchName\"执行")
      public void verifyCodeBatchNotExectute(@Named("batchName") String batchName) throws Exception {
            boolean result = testingBox.useViews().projectManagerView.verifyCodeBatchExectuteSucc(batchName);
            Assert.assertTrue("批次" + batchName + "没有执行", result);
      }
      
      
      @Given("在检测记录列表中,检测批次\"$batchName\"没有执行")
      @When("在检测记录列表中,检测批次\"$batchName\"没有执行")
      @Then("在检测记录列表中,检测批次\"$batchName\"没有执行")
      public void verifyCodeBatchExectuteSucc(@Named("batchName") String batchName) throws Exception {
            boolean result = testingBox.useViews().projectManagerView.verifyCodeBatchExectuteSucc(batchName);
            Assert.assertTrue("批次" + batchName + "已执行", !result);
      }
      
      @Given("在检测记录列表中,检测批次\"$batchName\"的检测模式为\"$mode\"")
      @When("在检测记录列表中,检测批次\"$batchName\"的检测模式为\"$mode\"")
      @Then("在检测记录列表中,检测批次\"$batchName\"的检测模式为\"$mode\"")
      public void verifyBatchTypeCorrect(@Named("batchName") String batchName,
                                         @Named("mode") String mode) throws Exception {
            boolean result = testingBox.useViews().projectManagerView.verifyColsValueCorrectlyInBatchListTable(batchName, "检测模式", mode);
            Assert.assertTrue("检测批次" + batchName + "的检测模式不正确", result);
      }
      
      @Given("在下拉菜单中点击项目\"$projectName\"的按钮\"$linkText\"")
      @When("在下拉菜单中点击项目\"$projectName\"的按钮\"$linkText\"")
      @Then("在下拉菜单中点击项目\"$projectName\"的按钮\"$linkText\"")
      public void clickModifyProjectInfoOrStrategy(@Named("projectName") String projectName,
                                                   @Named("linkText") String linkText) throws Exception {
            testingBox.useViews().projectManagerView.clickModifyProjectInfoOrStrategy(projectName, linkText);
      }
      
      @Given("禁用项目后,项目名称带有禁用提示")
      @When("禁用项目后,项目名称带有禁用提示")
      @Then("禁用项目后,项目名称带有禁用提示")
      public void verifyProjectNameContainsWordTips() throws Exception {
            boolean result = false;
            result = testingBox.useViews().projectManagerView.verifyProjectNameContainsWordTips();
            Assert.assertTrue("禁用项目后,项目名称未包含禁用提示", result);
      }
      
      
      @Given("禁用项目后,\"$linkText\"按钮不可点击")
      @When("禁用项目后,\"$linkText\"按钮不可点击")
      @Then("禁用项目后,\"$linkText\"按钮不可点击")
      public void verifyLinkButtonIsDisabled(@Named("linkText") String linkText) throws Exception {
            boolean result = testingBox.useViews().projectManagerView.verifyLinkButtonIsDisabled(linkText);
            Assert.assertTrue("禁用项目后," + linkText + "可点击", result);
      }
      
      @Given("等待{最后一个|}项目\"$projectName\"开始执行")
      @Then("等待{最后一个|}项目\"$projectName\"开始执行")
      public void waitForAllProjectStartCheck(@Named("projectName") String projectName) throws Exception {
            String sql = "SELECT exe_begin_time FROM chk_project WHERE project_name = '<PjName>'";
            sql = sql.replaceAll("<PjName>", projectName);
            String exeDay = "";
            String exeTime = "";
            ResultSet set = testingBox.queryDatabase(sql);
            while (set.next()) {
                  String rs = set.getString("exe_begin_time");
                  exeDay = rs.split(" ")[0];
                  exeTime = rs.split(" ")[1];
            }
            String phour = exeTime.split(":")[0];
            String pmin = exeTime.split(":")[1];
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
            Time timer = new Time();
            if (pt.before(ct)) {
                  timer.waitForSeconds(0, "project executing or finished");
            } else {
                  long days = (pt.getTime() - ct.getTime()) / (1000 * 3600 * 24);
                  if (days == 0) {
                        int h = Integer.parseInt(hour);
                        int m = Integer.parseInt(min);
                        int h2 = Integer.parseInt(phour);
                        int m2 = Integer.parseInt(pmin);
                        if (h < h2) {
                              int sec = (h2 - h) * 3600 + (m2 - m) * 60;
                              timer.waitForSeconds(sec, "wait for project executed");
                        } else if (h == h2 && m < m2) {
                              int sec = (m2 - m) * 60;
                              timer.waitForSeconds(sec, "wait for project executed");
                        } else {
                              timer.waitForSeconds(0, "project executing or finished");
                        }
                  } else {
                        int h = Integer.parseInt(hour);
                        int m = Integer.parseInt(min);
                        int h2 = (int) (Integer.parseInt(phour) + 24 * days);
                        int m2 = Integer.parseInt(pmin);
                        int sec = (h2 - h) * 3600 + (m2 - m) * 60;
                        timer.waitForSeconds(sec, "wait for project executed");
                  }
            }
            
      }
      
      @Given("验证\"$projectName\"(项目名-代码名)检测类型为\"$batchType\"的第\"$batch\"批次检测的\"$type\"结果\"$res\"")
      public void verifyProjectCodeNameExeResult(@Named("type") String type,
                                                 @Named("projectName") String projectName,
                                                 @Named("batchType") String batchType,
                                                 @Named("batch") String batch,
                                                 @Named("res") String res) throws Exception {
            boolean result;
            String taskType = "";
            if (batchType.equals("持续检测")) {
                  taskType = "1";
            } else if ((batchType.equals("手动检测"))) {
                  taskType = "2";
            }
            String check_type = testingBox.useViews().commonView.getCheckTypeCode(type);
            String finalResult = "";
            String checkSql = "SELECT * FROM chk_task where task_name = '<name>' and check_type = '<check_type>' and task_batch = '<batch>' and task_type = '<tasktype>'";
            String resSql = "SELECT task_status FROM chk_task where task_name = '<name>' and check_type = '<check_type>' and task_batch = '<batch>' and task_type = '<tasktype>'";
            checkSql = checkSql.replaceAll("<check_type>",check_type).replaceAll("<tasktype>",taskType).replaceAll("<batch>",batch).replaceAll("<name>",projectName);
            resSql = resSql.replaceAll("<tasktype>",taskType).replaceAll("<name>",projectName).replaceAll("<batch>",batch).replaceAll("<check_type>",check_type);
            System.out.println(checkSql);
            System.out.println(resSql);
            ResultSet rs = testingBox.queryDatabase(checkSql);
            rs.last();
            int rows = rs.getRow();
            if (rows > 0) {
                  boolean temp = false;
                  while (!temp) {
                        ResultSet rs2 = testingBox.queryDatabase(resSql);
                        while (rs2.next()) {
                              int checkResult = rs2.getInt(1);
                              if (checkResult == 2) {
                                    finalResult = finalResult + "代码名(项目名):" + projectName + "的" + batchType + "批次" + batch + "中的" + type + "检测成功" + "\n";
                                    temp = true;
                              }
                              if (checkResult == 3) {
                                    finalResult = finalResult + "代码名(项目名):" + projectName + "的" + batchType + "批次" + batch + "中的" + type + "检测失败" + "\n";
                                    temp = true;
                              }
                        }
                  }
            } else {
                  finalResult = finalResult + "代码名(项目名):" + projectName + "的" + batchType + "批次" + batch + "中的" + type + "检测没有创建" + "\n";
            }
            if (res.equals("成功")) {
                  result = !finalResult.contains("失败") && !finalResult.contains("创建");
                  Assert.assertTrue(finalResult, result);
            } else if (res.equals("失败")) {
                  result = !finalResult.contains("成功") && !finalResult.contains("创建");
                  Assert.assertTrue(finalResult, result);
            }
      }
      
      
      @Given("验证\"$projectName\"(项目名-代码名)检测类型为\"$batchType\"的第\"$batch\"批次的检测结果,拥有\"$engine\"的\"$type\"检测结果并检测\"$res\"")
      public void verifyEngineRecordFormdatabase(@Named("type") String type,
                                                 @Named("projectName") String projectName,
                                                 @Named("batchType") String batchType,
                                                 @Named("batch") String batch,
                                                 @Named("engine") String engine,
                                                 @Named("res") String res) throws Exception {
            boolean result;
            String finalResult = "";
            String check_type = testingBox.useViews().commonView.getCheckTypeCode(type);
            String tool = testingBox.useViews().commonView.getEngineCode(engine);
            String sql = "SELECT task_name , code_name ,task_tool_status ,task_result_desc FROM chk_task_tool WHERE pk_task = '<pktask>' and tool='<tool>' and check_type ='<checkType>'";
            sql = sql.replaceAll("<tool>", tool).replaceAll("<checkType>", check_type);
            String taskType = "";
            if (batchType.equals("持续检测")) {
                  taskType = "1";
            } else if ((batchType.equals("手动检测"))) {
                  taskType = "2";
            }
            String pkSql = "SELECT pk_task FROM chk_task Where task_name = '<name>' and check_type = '<check_type>' and task_batch = '<batch>' and task_type = '<tasktype>'";
            pkSql = pkSql.replaceAll("<check_type>", check_type).replaceAll("<tasktype>", taskType).replaceAll("<name>", projectName).replaceAll("<batch>", batch);
            ResultSet rs1 = testingBox.queryDatabase(pkSql);
            while (rs1.next()) {
                  String res1 = rs1.getString("pk_task");
                  sql = sql.replaceAll("<pktask>", res1);
            }
            System.out.println(sql);
            ResultSet rs = testingBox.queryDatabase(sql);
            while (rs.next()) {
                  int res3 = rs.getInt("task_tool_status");
                  String res4 = rs.getString("task_result_desc");
                  if (res3 == 2) {
                        finalResult = finalResult + "代码名(项目名):" + projectName + "的" + batchType + "批次" + batch + "拥有" + engine + "引擎的" + type + "检测结果并检测成功" + "\n";
                  }
                  if (res3 == 3) {
                        finalResult = finalResult + "代码名(项目名):" + projectName + "的" + batchType + "批次" + batch + "拥有" + engine + "引擎的" + type + "检测结果但是检测失败,失败原因为 " + res4 + "\n";
                  }
            }
            rs.last();
            int rows = rs.getRow();
            if (rows <= 0) {
                  finalResult = finalResult + "代码名(项目名):" + projectName + "的" + batchType + "批次" + batch + "没有创建 " + engine + "的" + type + "任务" + "\n";
            }
            if (res.equals("成功")) {
                  result = !finalResult.contains("失败") && !finalResult.contains("没有创建");
                  Assert.assertTrue("project " + finalResult, result);
            } else if (res.equals("失败")) {
                  result = !finalResult.contains("成功") && !finalResult.contains("没有创建");
                  Assert.assertTrue("project " + finalResult, result);
            }
      }
      
      
      @Given("验证\"$projectName\"(项目名-代码名)检测类型为\"$batchType\"的第\"$batch\"批次的检测结果,没有\"$engine\"的\"$type\"检测结果")
      public void verifyNotEngineRecordFormdatabase(@Named("type") String type,
                                                    @Named("projectName") String projectName,
                                                    @Named("batchType") String batchType,
                                                    @Named("batch") String batch,
                                                    @Named("engine") String engine) throws Exception {
            boolean result;
            String check_type = testingBox.useViews().commonView.getCheckTypeCode(type);
            String tool = testingBox.useViews().commonView.getEngineCode(engine);
            String sql = "SELECT * FROM chk_task_tool WHERE pk_task = '<pktask>' and tool='<tool>' and check_type ='<checkType>'";
            sql = sql.replaceAll("<tool>", tool).replaceAll("<checkType>", check_type);
            String taskType = "";
            if (batchType.equals("持续检测")) {
                  taskType = "1";
            } else if ((batchType.equals("手动检测"))) {
                  taskType = "2";
            }
            String pkSql = "SELECT pk_task FROM chk_task Where task_name = '<name>' and check_type = '<check_type>' and task_batch = '<batch>' and task_type = '<tasktype>'";
            pkSql = pkSql.replaceAll("<check_type>", check_type).replaceAll("<tasktype>", taskType).replaceAll("<name>", projectName).replaceAll("<batch>", batch);
            ResultSet rs = testingBox.queryDatabase(pkSql);
            while (rs.next()) {
                  String res1 = rs.getString("pk_task");
                  sql = sql.replaceAll("<pktask>", res1);
            }
            System.out.println(sql);
            ResultSet rs1 = testingBox.queryDatabase(sql);
            rs1.last();
            int rows = rs1.getRow();
            if (rows > 0) {
                  result = false;
            } else {
                  result = true;
            }
            Assert.assertTrue(projectName + "(项目名-代码名)检测类型为" + batchType + "的第" + batch + "批次的检测结果,拥有" + engine + "的检测结果", result);
      }
      
      @Given("在项目\"$pjName\"的项目信息页面中,项目经理\"$PM\",检测方式\"$checkType\",检测目标\"$target\",检测模式\"$mode\",携带审计\"$carryAudit\",我的Bug\"$myBug\"信息显示正确")
      @When("在项目\"$pjName\"的项目信息页面中,项目经理\"$PM\",检测方式\"$checkType\",检测目标\"$target\",检测模式\"$mode\",携带审计\"$carryAudit\",我的Bug\"$myBug\"信息显示正确")
      @Then("在项目\"$pjName\"的项目信息页面中,项目经理\"$PM\",检测方式\"$checkType\",检测目标\"$target\",检测模式\"$mode\",携带审计\"$carryAudit\",我的Bug\"$myBug\"信息显示正确")
      public void verifyProjectInfoCorrectly(@Named("pjName") String pjName,
                                             @Named("PM") String PM,
                                             @Named("checkType") String checkType,
                                             @Named("target") String target,
                                             @Named("mode") String mode,
                                             @Named("carryAudit") String carryAudit,
                                             @Named("myBug") String myBug) throws Exception {
            boolean result1 = testingBox.useViews().projectManagerView.verifyProjectInfoCorrectly("项目经理", PM);
            Assert.assertTrue("项目" + pjName + "的项目经理显示不正确", result1);
            boolean result2 = testingBox.useViews().projectManagerView.verifyProjectInfoCorrectly("检测方式", checkType);
            Assert.assertTrue("项目" + pjName + "的检测方式显示不正确", result2);
            boolean result3 = testingBox.useViews().projectManagerView.verifyProjectInfoCorrectly("检测目标", target);
            Assert.assertTrue("项目" + pjName + "的检测目标显示不正确", result3);
            boolean result4 = testingBox.useViews().projectManagerView.verifyProjectInfoCorrectly("检测模式", mode);
            Assert.assertTrue("项目" + pjName + "的检测模式显示不正确", result4);
            boolean result5 = testingBox.useViews().projectManagerView.verifyProjectInfoCorrectly("携带审计", carryAudit);
            Assert.assertTrue("项目" + pjName + "的携带审计显示不正确", result5);
            boolean result6 = testingBox.useViews().projectManagerView.verifyProjectInfoCorrectly("我的Bug", myBug);
            Assert.assertTrue("项目" + pjName + "的我的Bug显示不正确", result6);
      }
      
      @Given("在最近检测页面,点击\"$checkType\"检测结果总{缺陷|违规|漏洞}数链接")
      @When("在最近检测页面,点击\"$checkType\"检测结果总{缺陷|违规|漏洞}数链接")
      @Then("在最近检测页面,点击\"$checkType\"检测结果总{缺陷|违规|漏洞}数链接")
      public void clickCheckTypeDefectCountsLink(@Named("checkType") String checkType) throws Exception {
            testingBox.useViews().projectManagerView.clickCheckTypeDefectCountsLink(checkType);
      }
      
      
      @Given("在检测记录列表中,点击检测批次\"$batchName\"")
      @When("在检测记录列表中,点击检测批次\"$batchName\"")
      @Then("在检测记录列表中,点击检测批次\"$batchName\"")
      public void clickBatchNameInCodeBatchList(@Named("batchName") String batchName) throws Exception {
            testingBox.useViews().projectManagerView.clickBatchNameInCodeBatchList(batchName);
      }
      
      @Given("在检测记录列表中,勾选检测批次\"$batchName\"")
      @When("在检测记录列表中,勾选检测批次\"$batchName\"")
      @Then("在检测记录列表中,勾选检测批次\"$batchName\"")
      public void selectBatchInCodeBatchList(@Named("batchName") String batchName) throws Exception {
            testingBox.useViews().projectManagerView.selectBatchInCodeBatchList(batchName);
      }
      
      
      @Given("在检测记录列表中,点击检测批次\"$batchName\"中,检测方式为\"$type\"结果的\"$icon\" 图标")
      @When("在检测记录列表中,点击检测批次\"$batchName\"中,检测方式为\"$type\"结果的\"$icon\" 图标")
      @Then("在检测记录列表中,点击检测批次\"$batchName\"中,检测方式为\"$type\"结果的\"$icon\" 图标")
      public void clickOperationIconOnBatchList(@Named("batchName") String batchName,
                                                @Named("type") String type,
                                                @Named("icon") String icon) throws Exception {
            testingBox.useViews().projectManagerView.clickOperationIconOnBatchList(batchName, type, icon);
      }
      
      
      @Given("在检测记录列表中,检测批次\"$batchName\"中,检测方式为\"$type\"结果\"$button\"按钮的提示信息为\"$icon\"")
      @When("在检测记录列表中,检测批次\"$batchName\"中,检测方式为\"$type\"结果\"$button\"按钮的提示信息为\"$icon\"")
      @Then("在检测记录列表中,检测批次\"$batchName\"中,检测方式为\"$type\"结果\"$button\"按钮的提示信息为\"$icon\"")
      public void verifyOperationIconOnBatchList(@Named("batchName") String batchName,
                                                 @Named("type") String type,
                                                 @Named("icon") String icon) throws Exception {
            boolean result = testingBox.useViews().projectManagerView.verifyOperationIconOnBatchList(batchName, type, icon);
            Assert.assertTrue("检测批次" + batchName + "中,我的bug提示信息显示不正确", result);
      }
      
      @Given("在\"$type\"列表中,验证{项目|源代码}\"$pjName\"的检测执行次数为\"$checkTimes\"正确")
      @When("在\"$type\"列表中,验证{项目|源代码}\"$pjName\"的检测执行次数为\"$checkTimes\"正确")
      @Then("在\"$type\"列表中,验证{项目|源代码}\"$pjName\"的检测执行次数为\"$checkTimes\"正确")
      public void verifyProjectTaskCheckTimeCorrect(@Named("pjName") String projectName,
                                                    @Named("type") String type,
                                                    @Named("checkTimes") String checkTimes) throws Exception {
            boolean result;
            if (projectName.length() > 20) {
                  projectName = projectName.substring(0, 20);
            }
            if (type.equals("项目")){
                  result = testingBox.useViews().projectManagerView.verifyColsValueCorrectlyInTaskListTable(projectName, "检测次数", checkTimes);
            }else{
                  result = testingBox.useViews().projectManagerView.verifyColsValueCorrectlyInTaskListTable(projectName, "执行检测次数", checkTimes);
            }
            Assert.assertTrue(projectName + "项目的检测次数不正确", result);
      }
}
