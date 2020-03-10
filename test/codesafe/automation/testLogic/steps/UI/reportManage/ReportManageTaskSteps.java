package codesafe.automation.testLogic.steps.UI.reportManage;

import codesafe.automation.testLogic.TeamSystem;
import codesafe.automation.testLogic.steps.StepsBase;
import net.serenitybdd.core.Serenity;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.junit.Assert;

import java.io.IOException;
import java.sql.SQLException;

public class ReportManageTaskSteps extends StepsBase {
      
      TeamSystem testingBox;
      
      public ReportManageTaskSteps() throws SQLException, IOException {
            super();
            testingBox = (TeamSystem) Serenity.getCurrentSession().get("TeamSystem");
      }
      
      @Then("在报告管理的任务列表中, 确认 \"$reportName\" 报告的任务名称\"$taskName\"，报告格式 \"$format\"，创建者\"$user\"等信息")
      public void verifyTaskLanguage(@Named("reportName") String reportName,
                                     @Named("taskName") String taskName,
                                     @Named("format") String format,
                                     @Named("user") String user) throws Exception {
            boolean result1, result2, result3;
            if (reportName.length() > 20) {
                  reportName = reportName.substring(0, 20);
            }
            if (taskName.length() > 20) {
                  taskName = taskName.substring(0, 20);
            }
            result1 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(reportName, "任务名称", taskName);
            Assert.assertTrue(reportName + "报告的任务名称不正确", result1);
            result2 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(reportName, "报告格式", format);
            Assert.assertTrue(reportName + "报告的格式不正确", result2);
            result3 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(reportName, "创建者", user);
            Assert.assertTrue(reportName + "报告的创建者不正确", result3);
      }
}
