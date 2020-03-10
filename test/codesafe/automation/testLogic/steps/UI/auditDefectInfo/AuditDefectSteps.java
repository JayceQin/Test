package codesafe.automation.testLogic.steps.UI.auditDefectInfo;

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

public class AuditDefectSteps extends StepsBase {
      
      TeamSystem testingBox;
      
      public AuditDefectSteps() throws SQLException, IOException {
            super();
            testingBox = (TeamSystem) Serenity.getCurrentSession().get("TeamSystem");
      }
      
      @When("点击\"$type\"按钮后跳转成功，当前页面为审计页面")
      @Then("点击\"$type\"按钮后跳转成功，当前页面为审计页面")
      public void verifyAuditDefectPage(@Named("type") String type) throws Exception {
            boolean result;
            result = testingBox.useViews().auditInfoView.verifyAuditDefectPage();
            Assert.assertTrue("点击" + type + "按钮后跳转失败，当前页面不是" + type + "页面", result);
      }
      
      @When("在审计页面中,点击\"$title\"子标题")
      public void clickAuditContentTabOnAuditPage(@Named("title") String title) throws Exception {
            testingBox.useViews().auditInfoView.clickAuditContentTabOnAuditPage(title);
      }
      
      @Then("在审计页面中,源码来源\"$type\" 和源码路径\"$site\"显示正确")
      public void verifyCodeSourceInDefectOfAuditPage(@Named("type") String type,
                                                      @Named("site") String site) throws Exception {
            boolean result;
            result = testingBox.useViews().auditInfoView.verifyDefectsOfAuditCodeSourceOption(type, site);
            Assert.assertTrue("在结果审计页面,源码来源" + type + " 和源码路径" + site + "显示不正确", result);
      }
      
      @Then("在审计页面中,JAVA项目JDK版本\"$version\"显示正确")
      public void verifyJDKVersionInAuditPage(@Named("version") String version) throws Exception {
            boolean result;
            result = testingBox.useViews().auditInfoView.verifyJDKVersionOnAuditPage(version);
            Assert.assertTrue("在结果审计页面,JAVA项目JDK版本" + version + "显示不正确", result);
      }
      
      @Then("在审计页面中,JAVA项目J2EE工程\"$value\"显示正确")
      public void verifyJ2EEProjectInAuditPage(@Named("value") String value) throws Exception {
            boolean result;
            result = testingBox.useViews().auditInfoView.verifyJ2EEProjectOnAuditPage(value);
            Assert.assertTrue("在结果审计页面,JAVA项目J2EE工程" + value + "显示不正确", result);
      }
      
      @When("在审计页面中,Maven库依赖包\"$value\"显示正确")
      @Then("在审计页面中,Maven库依赖包\"$value\"显示正确")
      public void verifyMavenDependencyLibrariesInAuditPage(@Named("value") String value) throws Exception {
            boolean result;
            result = testingBox.useViews().auditInfoView.verifyMavenDependencyLibraryFromAuditPage(value);
            Assert.assertTrue("在结果审计页面,Maven库依赖包" + value + "显示不正确", result);
      }
      
      
      @When("在审计页面中,Maven库依赖包\"$value\"不存在")
      @Then("在审计页面中,Maven库依赖包\"$value\"不存在")
      public void verifyNotMavenDependencyLibrariesInAuditPage(@Named("value") String value) throws Exception {
            boolean result;
            result = testingBox.useViews().auditInfoView.verifyMavenDependencyLibraryFromAuditPage(value);
            Assert.assertTrue("在结果审计页面,Maven库依赖包" + value + "存在", !result);
      }
      
      
      @When("在审计页面的函数白名单子页面中, 确认\"$FuncName\" 函数存在")
      @Then("在审计页面的函数白名单子页面中, 确认\"$FuncName\" 函数存在")
      public void verifyWhiteFunctionRecordOnAuditPage(@Named("FuncName") String FuncName) throws Exception {
            boolean result;
            result = testingBox.useViews().auditInfoView.verifyWhiteFunctionNameOnAuditPage(FuncName);
            Assert.assertTrue(FuncName + "函数不存在", result);
      }
      
      
      @When("在溯源审计页面,点击组件\"$bugName\"")
      public void clickSyDefectHyperLink(@Named("bugName") String bugName) throws Exception {
            testingBox.useViews().auditInfoView.clickSyDefectHyperLink(bugName);
      }
      
      @When("在溯源审计页面,审计组件\"$bugName\"为\"$choice\"")
      public void auditSyDefectOnSyAuditPage(@Named("bugName") String bugName,
                                             @Named("choice") String choice) throws Exception {
            testingBox.useViews().auditInfoView.auditSyDefect(bugName, choice);
      }
      
      @Then("在溯源审计页面,验证组件\"$bugName\"审计为\"$choice\"成功")
      public void verifySyDefectAuditRecord(@Named("bugName") String bugName,
                                            @Named("choice") String choice) throws Exception {
            boolean result;
            result = testingBox.useViews().auditInfoView.verifySyDefectAuditRecord(bugName, choice);
            Assert.assertTrue("组件" + bugName + "审计失败", result);
      }
      
      
      @When("在审计页面中,{展开|点击}\"$level\"等级分类")
      @Then("在审计页面中,{展开|点击}\"$level\"等级分类")
      public void clickAuditHeaderDivOnAuditPage(@Named("level") String level) throws Exception {
            testingBox.useViews().auditInfoView.clickAuditLevelDivOnAuditPage(level);
      }
      
      @When("在审计页面,点击\"$level\"等级\"$bugType\"分类缺陷\"$defect\"")
      @Then("在审计页面,点击\"$level\"等级\"$bugType\"分类缺陷\"$defect\"")
      public void clickDefectsWithBugLevelAndBugType(@Named("level") String level,
                                                     @Named("bugType") String bugType,
                                                     @Named("defect") String defect) throws Exception {
            testingBox.useViews().auditInfoView.clickDefectsWithBugLevelAndBugType(level, bugType, defect);
      }
      
      
      @When("在审计页面中,展开\"$level\"等级{缺陷|违规|文件}分类\"$bugName\"")
      @Then("在审计页面中,展开\"$level\"等级{缺陷|违规|文件}分类\"$bugName\"")
      public void expandDefectTypeOnAuditLeft(@Named("level") String level,
                                              @Named("bugName") String bugName) throws Exception {
            testingBox.useViews().auditInfoView.expandAuditBugTypes(level, bugName);
      }
      
      @When("在审计页面,将\"$level\"等级\"$bugType\"分类的缺陷\"$bugs\"审计为\"$auditLevel\",备注为\"$mark\"")
      @Then("在审计页面,将\"$level\"等级\"$bugType\"分类的缺陷\"$bugs\"审计为\"$auditLevel\",备注为\"$mark\"")
      public void auditBugsOnAuditPage(@Named("level") String level,
                                       @Named("bugType") String bugType,
                                       @Named("bugs") String bugs,
                                       @Named("auditLevel") String auditLevel,
                                       @Named("mark") String mark) throws Exception {
            testingBox.useViews().auditInfoView.rightClickAndAuditBug(level, bugType, bugs, auditLevel, mark);
      }
      
      
      @When("在审计Tab上,验证缺陷\"$defect\"没有被审计")
      @Then("在审计Tab上,验证缺陷\"$defect\"没有被审计")
      public void verifyNotAuditInfo(@Named("defect") String defect) throws Exception {
            boolean result = testingBox.useViews().auditInfoView.verifyDefectNotAudited();
            Assert.assertTrue("缺陷" + defect + "已经被审计", !result);
      }
      
      @When("在审计页面中, 函数白名单\"$FuncType1\"-\"$FuncType2\"设置成功")
      @Then("在审计页面中, 函数白名单\"$FuncType1\"-\"$FuncType2\"设置成功")
      public void verifyWhiteFunctionSetSuccessfully(@Named("FuncType1") String FuncType1,
                                                     @Named("FuncType2") String FuncType2) throws Exception {
            boolean result;
            result = testingBox.useViews().auditInfoView.verifyWhiteFunctionOnAuditLeft(FuncType1, FuncType2);
            Assert.assertTrue("函数白名单" + FuncType1 + "-" + FuncType2 + "设置失败", result);
      }
      
      @When("在审计Tab上,验证\"$defect\"审计信息中审计人员为\"$user\",审计缺陷等级为\"$level\",审计备注为\"$remark\"")
      @Then("在审计Tab上,验证\"$defect\"审计信息中审计人员为\"$user\",审计缺陷等级为\"$level\",审计备注为\"$remark\"")
      public void verifyAuditSuccessfully(@Named("user") String user,
                                          @Named("defect") String defect,
                                          @Named("level") String level,
                                          @Named("remark") String remark) throws Exception {
            boolean result;
            result = testingBox.useViews().auditInfoView.verifyAuditSuccessfully(user, level, remark);
            Assert.assertTrue("缺陷" + defect + "审计失败", result);
      }
      
      @When("在审计Tab上,选择\"$value\"选项")
      @Then("在审计Tab上,选择\"$value\"选项")
      public void selectRadioBtnOnAuditForm(@Named("value") String value) throws Exception {
            testingBox.useViews().auditInfoView.selectRadioBtnOnAuditForm(value);
      }
      
      @When("在审计Tab上,输入备注\"$value\"")
      @Then("在审计Tab上,输入备注\"$value\"")
      public void inputRemarkOnAuditForm(@Named("value") String value) throws Exception {
            testingBox.useViews().auditInfoView.inputRemarkOnAuditForm(value);
      }
      
      @When("在审计Tab上,点击提交按钮")
      @Then("在审计Tab上,点击提交按钮")
      public void clickSubmitBtnOnAuditForm() throws Exception {
            testingBox.useViews().auditInfoView.clickSubmitBtnOnAuditForm();
      }
      
      @When("在审计页面,选择缺陷显示方式为\"$style\"")
      public void selectDefectShowStyle(@Named("style") String style) throws Exception {
            testingBox.useViews().auditInfoView.selectDefectShowStyle(style);
      }
      
      @When("在审计页面,点击高级搜索")
      @Then("在审计页面,点击高级搜索")
      public void clickAdvancedSearchButton() throws Exception {
            testingBox.useViews().auditInfoView.clickAdvancedSearchButton();
      }
      
      @When("在审计页面,使用高级搜索后，只显示\"$bugName\"")
      @Then("在审计页面,使用高级搜索后，只显示\"$bugName\"")
      public void verifyAdvancedSearch(@Named("bugName") String bugName) throws Exception {
            boolean result;
            result = testingBox.useViews().auditInfoView.verifyAdvancedSearch(bugName);
            Assert.assertTrue("高级搜索失效", result);
      }
      
      @Given("在审计页面,等级\"$level\"下的缺陷数为\"$num\"个")
      @When("在审计页面,等级\"$level\"下的缺陷数为\"$num\"个")
      @Then("在审计页面,等级\"$level\"下的缺陷数为\"$num\"个")
      public void verifyProjectBugNumbersCorrectly(@Named("level") String level,
                                                   @Named("num") String num) throws Exception {
            boolean result = testingBox.useViews().auditInfoView.verifyProjectBugNumbersCorrectly(level, num);
            Assert.assertTrue("在审计页面,等级" + level + "下的缺陷数不正确", result);
      }
      
      @Given("在审计页面,等级\"$level\"下的缺陷分类只有\"$defects\"")
      @When("在审计页面,等级\"$level\"下的缺陷分类只有\"$defects\"")
      @Then("在审计页面,等级\"$level\"下的缺陷分类只有\"$defects\"")
      public void verfiyBugsTypeNumberCorrectly(@Named("level") String level,
                                                @Named("defects") String defects) throws Exception {
            boolean result = testingBox.useViews().auditInfoView.verifyBugsTypeNumberCorrectly(level, defects);
            Assert.assertTrue("在审计页面,等级" + level + "下的缺陷分类不正确", result);
      }
}
