package codesafe.automation.testLogic.steps.UI.resultIntegration;

import codesafe.automation.testLogic.TeamSystem;
import codesafe.automation.testLogic.steps.StepsBase;
import net.serenitybdd.core.Serenity;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.junit.Assert;

import java.io.IOException;
import java.sql.SQLException;

public class ResultIntegrationSteps extends StepsBase {
      
      TeamSystem testingBox;
      
      public ResultIntegrationSteps() throws SQLException, IOException {
            super();
            testingBox = (TeamSystem) Serenity.getCurrentSession().get("TeamSystem");
      }
      
      @Given("在结果整合任务列表中, \"$taskName\" 任务检测\"$res\"")
      @Then("在结果整合任务列表中, \"$taskName\" 任务检测\"$res\"")
      public void verifyTaskProjectCheckStatus(@Named("taskName") String taskName,
                                               @Named("res") String res) throws Exception {
            boolean result;
            if (taskName.length() > 20) {
                  taskName = taskName.substring(0, 20);
            }
            result = testingBox.useViews().resultIntegrationView.verifyTaskStatus(taskName);
            if (res.equals("成功")) {
                  Assert.assertTrue(taskName + "任务检测失败", result);
            } else if (res.equals("失败")) {
                  Assert.assertTrue(taskName + "任务检测成功", !result);
            }
      }
      
      @Then("在结果整合任务列表中, 确认 \"$taskName\" 任务的任务名称,开发语言 \"$language\",检测引擎\"$engine\",创建者\"$user\"等信息")
      public void verifyTaskLanguage(@Named("taskName") String taskName,
                                     @Named("engine") String engine,
                                     @Named("language") String language,
                                     @Named("user") String user) throws Exception {
            boolean result1, result2, result3, result4;
            if (taskName.length() > 20) {
                  taskName = taskName.substring(0, 20);
            }
            result1 = testingBox.useViews().resultIntegrationView.verifyColsValueCorrectlyInTaskListTable(taskName, "任务名称", taskName);
            Assert.assertTrue(taskName + "任务的任务名称不正确", result1);
            result2 = testingBox.useViews().resultIntegrationView.verifyColsValueCorrectlyInTaskListTable(taskName, "检测语言", language);
            Assert.assertTrue(taskName + "任务的开发语言显示不正确", result2);
            result3 = testingBox.useViews().resultIntegrationView.verifyColsValueCorrectlyInTaskListTable(taskName, "创建者", user);
            Assert.assertTrue(taskName + "任务的创建者显示不正确", result3);
            result4 = testingBox.useViews().resultIntegrationView.verifyTaskEngine(taskName, engine);
            Assert.assertTrue(taskName + "任务的引擎显示不正确", result4);
      }
}
