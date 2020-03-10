package codesafe.automation.testLogic.steps.UI.fastCheck;

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

public class FastCheckPageSteps extends StepsBase {
      TeamSystem testingBox;
      
      public FastCheckPageSteps() throws SQLException, IOException {
            super();
            testingBox = (TeamSystem) Serenity.getCurrentSession().get("TeamSystem");
      }
      
      @Given("在快速检测功能页面,点击 \"$title\" 标题 ，跳转到对应的子页面")
      @When("在快速检测功能页面,点击 \"$title\" 标题 ，跳转到对应的子页面")
      @Then("在快速检测功能页面,点击 \"$title\" 标题 ，跳转到对应的子页面")
      public void clickAndCheckFastCheckPage(@Named("title") String title) throws Exception {
            testingBox.useViews().fastCheckTaskListView.clickQuickCheckTypeHyperLink(title);
      }
      
      
      @Given("在快速检测功能页面,确认当前子页面是\"$title\"页面")
      @When("在快速检测功能页面,确认当前子页面是\"$title\"页面")
      @Then("在快速检测功能页面,确认当前子页面是\"$title\"页面")
      public void verifyDefaultFastCheckPage(@Named("title") String title) throws Exception {
            boolean result;
            result = testingBox.useViews().fastCheckTaskListView.verifySkipFastCheckPageCorrectly(title);
            Assert.assertTrue("没有跳转到 " + title + " 页面", result);
      }
      
      @Then("在\"$type\"的任务列表中, 确认 \"$projectName\" 任务的任务名称，检测语言 \"$language\"，创建者\"$user\"等信息")
      public void verifyTaskLanguage(@Named("projectName") String projectName,
                                     @Named("language") String language,
                                     @Named("user") String user) throws Exception {
            boolean result1, result2, result3;
            if (projectName.length() > 20) {
                  projectName = projectName.substring(0, 20);
            }
            result2 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(projectName, "检测语言", language);
            Assert.assertTrue(projectName + "任务的开发语言显示不正确", result2);
            result3 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(projectName, "创建者", user);
            Assert.assertTrue(projectName + "任务的创建者显示不正确", result3);
      }
      
      @Given("在\"$type\"的任务列表中, 确认 \"$projectName\"任务检测失败，失败原因为\"$reason\"")
      @When("在\"$type\"的任务列表中, 确认 \"$projectName\"任务检测失败，失败原因为\"$reason\"")
      @Then("在\"$type\"的任务列表中, 确认 \"$projectName\"任务检测失败，失败原因为\"$reason\"")
      public void verifyTaskCheckResult(@Named("type") String type,
                                        @Named("projectName") String projectName,
                                        @Named("reason") String reason) throws Exception {
            String result = testingBox.useViews().commonView.getTaskStatusDetailReason(projectName);
            Assert.assertTrue(projectName + "任务失败原因不正确", reason.equals(result));
      }
      
      @When("点击查看结果按钮后跳转成功，当前页面为查看结果页面")
      public void verifyGeneralResultPage() throws Exception {
            boolean result;
            result = testingBox.useViews().fastCheckTaskListView.verifyGeneralResultPage();
            Assert.assertTrue("点击查看结果按钮后跳转失败，当前页面不是查看结果页面", result);
      }
      
      @When("在查看结果页面中, 确认maven代码库\"$value\"信息")
      @Then("在查看结果页面中, 确认maven代码库\"$value\"信息")
      public void verifyMavenLibraryFromViewResultPage(@Named("value") String value) throws Exception {
            boolean result;
            result = testingBox.useViews().fastCheckTaskListView.verifyMavenLibraryFromViewResultPage(value);
            Assert.assertTrue("在查看结果页面中,Maven代码库" + value + "不存在", result);
      }
      
      @When("在查看结果页面中,点击\"$types\"总数超链接跳转按钮")
      public void clickCodeInfomationABtn() throws Exception {
            testingBox.useViews().fastCheckTaskListView.clickCodeInformationBtn();
      }
      
      
      @Given("检测类型为\"$type\"的快速检测任务\"$projectName\",检测\"$res\"")
      @When("检测类型为\"$type\"的快速检测任务\"$projectName\",检测\"$res\"")
      @Then("检测类型为\"$type\"的快速检测任务\"$projectName\",检测\"$res\"")
      public void verifyTaskProjectCheckStatus(@Named("type") String type,
                                               @Named("projectName") String projectName,
                                               @Named("res") String res) throws Exception {
            boolean result;
            boolean temp;
            String check_type = testingBox.useViews().commonView.getCheckTypeCode(type);
            String checkSql = "SELECT * FROM chk_task where task_name='<name>' and check_type = '<check_type>'";
            String resSql = "SELECT task_status FROM chk_task where task_name = '<name>' and check_type = '<check_type>'";
            String finalResult = "";
            String sqlCheck = checkSql.replaceAll("<name>", projectName).replaceAll("<check_type>", check_type);
            resSql = resSql.replaceAll("<name>", projectName).replaceAll("<check_type>", check_type);
            System.out.println(sqlCheck);
            ResultSet rs = testingBox.queryDatabase(sqlCheck);
            rs.last();
            int rows = rs.getRow();
            if (rows > 0) {
                  temp = false;
                  while (!temp) {
                        ResultSet rs2 = testingBox.queryDatabase(resSql);
                        while (rs2.next()) {
                              int checkResult = rs2.getInt(1);
                              if (checkResult == 2) {
                                    finalResult = "快速检测任务 " + projectName + " 中的 " + type + "任务检测成功!" + "\n";
                                    temp = true;
                              }
                              if (checkResult == 3) {
                                    finalResult = "快速检测任务 " + projectName + " 中的 " + type + "任务检测失败!" + "\n";
                                    temp = true;
                              }
                        }
                  }
            } else {
                  finalResult = "快速检测任务 " + projectName + "没有创建" + type + "类型的检测任务" + "\n";
            }
            
            if (res.equals("成功")) {
                  result = !finalResult.contains("检测失败") && !finalResult.contains("没有创建");
                  Assert.assertTrue(finalResult, result);
            } else if (res.equals("失败")) {
                  result = !finalResult.contains("检测成功") && !finalResult.contains("没有创建");
                  Assert.assertTrue(finalResult, result);
            }
      }
      
      
      @Given("检测类型为\"$type\"的\"$projectName\"项目拥有\"$engine\"引擎的检测结果并检测\"$checkResult\"")
      @When("检测类型为\"$type\"的\"$projectName\"项目拥有\"$engine\"引擎的检测结果并检测\"$checkResult\"")
      @Then("检测类型为\"$type\"的\"$projectName\"项目拥有\"$engine\"引擎的检测结果并检测\"$checkResult\"")
      public void verifyEngineRecordFormdatabase(@Named("type") String type,
                                                 @Named("projectName") String projectName,
                                                 @Named("engine") String engine,
                                                 @Named("checkResult") String checkResult) throws Exception {
            String sql = "SELECT task_name , task_tool_status ,task_result_desc FROM chk_task_tool WHERE task_name='<taskName>' and tool='<tool>' and check_type ='<checkType>'";
            boolean result;
            String tool = testingBox.useViews().commonView.getEngineCode(engine);
            String checkType = testingBox.useViews().commonView.getCheckTypeCode(type);
            String finalResult = "";
            sql = sql.replaceAll("<tool>", tool).replaceAll("<checkType>", checkType).replaceAll("<taskName>", projectName);
            System.out.println(sql);
            ResultSet rs = testingBox.queryDatabase(sql);
            while (rs.next()) {
                  String res1 = rs.getString("task_name");
                  int res2 = rs.getInt("task_tool_status");
                  String res3 = rs.getString("task_result_desc");
                  if (res2 == 2) {
                        finalResult = finalResult + " " + res1 + "拥有" + engine + "引擎的" + type + "检测结果并检测成功" + "\n";
                  }
                  if (res2 == 3) {
                        finalResult = finalResult + " " + res1 + "拥有" + engine + "引擎的" + type + "检测结果但是检测失败,失败原因为 " + res3 + "\n";
                  }
            }
            rs.last();
            int rows = rs.getRow();
            if (rows <= 0) {
                  finalResult = finalResult + projectName + "没有创建 " + engine + "引擎的" + type + "任务" + "\n";
            }
            if (checkResult.equals("成功")) {
                  result = !finalResult.contains("失败") && !finalResult.contains("没有创建");
                  Assert.assertTrue(finalResult, result);
            } else if (checkResult.equals("失败")) {
                  result = !finalResult.contains("成功") && !finalResult.contains("没有创建");
                  Assert.assertTrue(finalResult, result);
            }
      }
      
      
      @Given("检测类型为\"$type\"的\"$projectName\"项目没有\"$engine\"引擎的检测结果")
      @When("检测类型为\"$type\"的\"$projectName\"项目没有\"$engine\"引擎的检测结果")
      @Then("检测类型为\"$type\"的\"$projectName\"项目没有\"$engine\"引擎的检测结果")
      public void verifyNotEngineRecordFormdatabase(@Named("type") String type,
                                                    @Named("projectName") String projectName,
                                                    @Named("engine") String engine) throws Exception {
            boolean result;
            String sql = "SELECT * FROM chk_task_tool WHERE task_name='<taskName>' and tool='<tool>' and check_type ='<checkType>'";
            String tool = testingBox.useViews().commonView.getEngineCode(engine);
            String checkType = testingBox.useViews().commonView.getCheckTypeCode(type);
            sql = sql.replaceAll("<tool>", tool).replaceAll("<checkType>", checkType).replaceAll("<taskName>", projectName);
            ResultSet rs = testingBox.queryDatabase(sql);
            rs.last();
            int rows = rs.getRow();
            if (rows > 0) {
                  result = false;
            } else {
                  result = true;
            }
            Assert.assertTrue("在" + type + "的任务列表中," + projectName + "项目拥有" + engine + "引擎的检测结果", result);
      }
      
}
