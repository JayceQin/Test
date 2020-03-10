package codesafe.automation.testLogic.steps.UI.statisticalAnalysis;

import codesafe.automation.testLogic.TeamSystem;
import codesafe.automation.testLogic.steps.StepsBase;
import net.serenitybdd.core.Serenity;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.io.IOException;
import java.sql.SQLException;

public class StatisticalAnalysisSteps extends StepsBase {
      
      TeamSystem testingBox;
      
      public StatisticalAnalysisSteps() throws SQLException, IOException {
            super();
            testingBox = (TeamSystem) Serenity.getCurrentSession().get("TeamSystem");
      }
      
      @Given("在统计分析页面,点击更新统计按钮")
      @When("在统计分析页面,点击更新统计按钮")
      @Then("在统计分析页面,点击更新统计按钮")
      public void clickUpdateStatisticalButton() throws Exception {
            testingBox.useViews().statisticalAnalysisView.clickUpdateStatisticalButton();
      }
      
      @Given("在统计分析页面,点击统计条件\"$title\"下拉框")
      @When("在统计分析页面,点击统计条件\"$title\"下拉框")
      @Then("在统计分析页面,点击统计条件\"$title\"下拉框")
      public void clickAllAlternativeConditionBars(@Named("title") String title) throws Exception {
            testingBox.useViews().statisticalAnalysisView.clickAllAlternativeConditionBars(title);
      }
      
      @Given("在统计分析页面,选择统计条件\"$title\"下拉框中的\"$value\"")
      @When("在统计分析页面,选择统计条件\"$title\"下拉框中的\"$value\"")
      @Then("在统计分析页面,选择统计条件\"$title\"下拉框中的\"$value\"")
      public void clickAlternativeConditionDropDownList(@Named("title") String title,
                                                        @Named("value") String value) throws Exception {
            testingBox.useViews().statisticalAnalysisView.clickAlternativeConditionDropDownList(title, value);
      }
      
      @Given("在统计分析页面,点击\"$title\"下拉框后,点击\"$button\"")
      @When("在统计分析页面,点击\"$title\"下拉框后,点击\"$button\"")
      @Then("在统计分析页面,点击\"$title\"下拉框后,点击\"$button\"")
      public void clickTaskOrApartmentCondition(@Named("title") String title,
                                                @Named("button") String button) throws Exception {
            testingBox.useViews().statisticalAnalysisView.clickTaskOrApartmentCondition(title, button);
      }
      
      @Given("在统计分析页面,点击\"$title\"下拉框中,\"$task\"的全选按钮")
      @When("在统计分析页面,点击\"$title\"下拉框中,\"$task\"的全选按钮")
      @Then("在统计分析页面,点击\"$title\"下拉框中,\"$task\"的全选按钮")
      public void clickSelectAllCheckBoxOnChooseTaskDropDownList(@Named("title") String title,
                                                                 @Named("task") String task) throws Exception {
            if (task.equals("任务") || task.equals("项目")) {
                  testingBox.useViews().statisticalAnalysisView.clickSelectAllCondition(title);
            } else {
                  testingBox.useViews().statisticalAnalysisView.clickApartmentSelectAllCondition(title);
            }
      }
      
      @Given("在统计分析页面,在时间选择器中点击\"$ranges\"")
      @When("在统计分析页面,在时间选择器中点击\"$ranges\"")
      @Then("在统计分析页面,在时间选择器中点击\"$ranges\"")
      public void clickDateSelectRanges(@Named("ranges") String ranges) throws Exception {
            testingBox.useViews().statisticalAnalysisView.clickDateSelectRanges(ranges);
      }
      
      @Given("在统计分析页面,点击\"$area\"区域中的\"$language\"语言按钮")
      @When("在统计分析页面,点击\"$area\"区域中的\"$language\"语言按钮")
      @Then("在统计分析页面,点击\"$area\"区域中的\"$language\"语言按钮")
      public void clickAllLanguageButtonOnShowDataArea(@Named("area") String area,
                                                       @Named("language") String language) throws Exception {
            String langs[] = language.split(";");
            if (langs.length == 1) {
                  testingBox.useViews().statisticalAnalysisView.clickAllLanguageButtonOnShowDataArea(area, language);
                  boolean result = testingBox.useViews().commonPopWindowView.verifyErrorMsgExist();
                  Assert.assertTrue("统计分析页面中,点击" + area + "区域的" + language + "语言按钮后,弹出异常", !result);
            } else {
                  for (String s : langs) {
                        testingBox.useViews().statisticalAnalysisView.clickAllLanguageButtonOnShowDataArea(area, s);
                        boolean result = testingBox.useViews().commonPopWindowView.verifyErrorMsgExist();
                        Assert.assertTrue("统计分析页面中,点击" + area + "区域的" + s + "语言按钮后,弹出异常", !result);
                  }
            }
      }
      
      @Given("在统计分析页面,点击导出Word按钮")
      @When("在统计分析页面,点击导出Word按钮")
      @Then("在统计分析页面,点击导出Word按钮")
      public void clickExportWordReportButtonOnAnalysisPage() throws Exception {
            testingBox.useViews().statisticalAnalysisView.clickExportWordReportButtonOnAnalysisPage();
      }
      
      @Given("在统计分析页面,点击导出Excel按钮")
      @When("在统计分析页面,点击导出Excel按钮")
      @Then("在统计分析页面,点击导出Excel按钮")
      public void clickExportExcelReportButtonOnAnalysisPage() throws Exception {
            testingBox.useViews().statisticalAnalysisView.clickExportExcelReportButtonOnAnalysisPage();
      }
      
      @Given("在统计分析页面,随机点击\"$title\"区域的组件影响项目数超链接")
      @When("在统计分析页面,随机点击\"$title\"区域的组件影响项目数超链接")
      @Then("在统计分析页面,随机点击\"$title\"区域的组件影响项目数超链接")
      public void clickInfluencingTaskNumberFromArea(@Named("title") String title) throws Exception {
            testingBox.useViews().statisticalAnalysisView.clickInfluencingTaskNumberFromArea(title);
            boolean result = testingBox.useViews().commonPopWindowView.verifyErrorMsgExist();
            Assert.assertTrue("统计分析页面中,点击溯源统计的" + title + "区域中,组件影响项目数后,弹出异常", !result);
            testingBox.useViews().commonPopWindowView.clickCloseBtnOnPopWindow();
      }
}
