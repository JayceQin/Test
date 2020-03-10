package codesafe.automation.testLogic.steps.UI.commonPopWindow;

import codesafe.automation.drivers.utils.Time;
import codesafe.automation.testLogic.TeamSystem;
import codesafe.automation.testLogic.steps.StepsBase;
import net.serenitybdd.core.Serenity;
import org.jbehave.core.annotations.*;
import org.junit.Assert;

import java.io.IOException;
import java.sql.SQLException;


public class CommonSeleniumSteps extends StepsBase {
      TeamSystem testingBox;
      
      public CommonSeleniumSteps() throws SQLException, IOException {
            super();
            testingBox = (TeamSystem) Serenity.getCurrentSession().get("TeamSystem");
      }
      
//      @BeforeScenario(uponType = ScenarioType.ANY)
//      public void initBaseBrowserforAnyScenario() throws Exception{
//            testingBox.useViews().getWebDriver("chrome", null);
//      }
      @AfterScenario(uponOutcome = AfterScenario.Outcome.FAILURE,uponType = ScenarioType.ANY)
      public void closePopWindowAfterFail() throws Exception{
            if (testingBox.useViews().commonPopWindowView.verifyPopWindowExist()){
                  testingBox.useViews().commonPopWindowView.getInfoCloseIcon();
            }
            if (testingBox.useViews().commonPopWindowView.verifyErrorMsgExist()){
                  testingBox.useViews().commonPopWindowView.getInfoCloseIcon();
            }
      }
      
//      @AfterScenario(uponType=ScenarioType.ANY)
//      public void closeBrowserforAnyScenario() throws Exception {
//            System.out.println("After Any Scenario Execute");
//            testingBox.useViews().close();
//      }
      
//      @AfterStory
//      public void closeBrowserFroStory() throws Exception{
//            testingBox.useViews().close();
//      }
      
      @AfterStories
      public void closeBrowserForStories() throws Exception{
            testingBox.useViews().quit();
      }
      
      @Given("初始化 \"$browser\" 浏览器")
      public void chooseBaseBrowser(@Named("browser") String browser) throws Exception {
            testingBox.useViews().getWebDriver(browser, null);
      }
      
      
      @Given("进入 \"$url\" 页面 通过 url")
      public void navigateByUrl(@Named("url") String url) throws Exception {
            switch (url) {
                  case "Home":
                        url = "https://" + testingBox.getSystemAddress() + "/#/login";
                        break;
            }
            
            testingBox.useViews().commonView.navigateByUrl(url);
      }
      
      @Given("在当前页面,等待 \"$seconds\" 秒")
      @When("在当前页面,等待 \"$seconds\" 秒")
      @Then("在当前页面,等待 \"$seconds\" 秒")
      public void waitFor(@Named("seconds") int seconds) {
            Time timer = new Time();
            timer.waitForSeconds(seconds, "by request");
      }
      
      @Given("输入用户名 \"$username\"和密码 \"$password\"")
      @When("输入用户名 \"$username\"和密码 \"$password\"")
      @Then("输入用户名 \"$username\"和密码 \"$password\"")
      public void enterUserAndPass(@Named("username") String username,
                                   @Named("password") String password) throws Exception {
            testingBox.useViews().loginView.enterUsername(username);
            testingBox.useViews().loginView.enterPassword(password);
      }
      
      @Given("在登录页面,点击登录按钮")
      @When("在登录页面,点击登录按钮")
      @Then("在登录页面,点击登录按钮")
      public void clickLoginButton() throws Exception {
            if (!testingBox.useViews().loginView.verifyAgreementCheckboxIsClicked()){
                  if (testingBox.useViews().commonPopWindowView.verifyPopWindowExist()){
                        testingBox.useViews().commonPopWindowView.clickCheckBoxOnPopWindowBottm();
                        testingBox.useViews().commonPopWindowView.clickSaveButtonOnPopWindowBottm();
                  }
            }
            if (!testingBox.useViews().loginView.verifyAgreementCheckboxIsClicked()){
                  if (testingBox.useViews().commonPopWindowView.verifyPopWindowExist()){
                        testingBox.useViews().commonPopWindowView.clickCheckBoxOnPopWindowBottm();
                        testingBox.useViews().commonPopWindowView.clickSaveButtonOnPopWindowBottm();
                  }
            }
            testingBox.useViews().loginView.clickSignInButton();
            testingBox.useViews().commonPopWindowView.simpleSleep(5000);
            String reason = "";
            boolean result = testingBox.useViews().commonView.verifyNavigatePageSuccessfully("首页") || testingBox.useViews().loginView.verifyPasswordPageTitle();
            if (!result) {
                  reason = testingBox.useViews().loginView.getAccountLoginFailedReason();
            }
            Assert.assertTrue("用户登录失败,失败原因是 : " + reason, result);
      }
      
      @Given("在当前页面,点击用户名\"$user\"")
      @When("在当前页面,点击用户名\"$user\"")
      @Then("在当前页面,点击用户名\"$user\"")
      public void clickUserName(@Named("user") String user) throws Exception {
            testingBox.useViews().commonView.clickUserHyperLinkWithText(user);
      }
      
      @Given("在用户的下拉菜单中,点击\"$button\"按钮")
      @Then("在用户的下拉菜单中,点击\"$button\"按钮")
      public void clickUserOperationButton(@Named("button") String button) throws Exception {
            testingBox.useViews().commonView.clickUserListPopoverListMenu(button);
      }
      
      
      @Given("点击 \"$title\" 标题 ,进入到对应的功能页面")
      @When("点击 \"$title\" 标题 ,进入到对应的功能页面")
      @Then("点击 \"$title\" 标题 ,进入到对应的功能页面")
      public void clickBannerTitle(@Named("title") String title) throws Exception {
            testingBox.useViews().commonView.clickNavigationWithText(title);
      }
      
      
      @Given("点击\"$title\"{标题|按钮}后 ,确认跳转页面正确")
      @When("点击\"$title\"{标题|按钮}后 ,确认跳转页面正确")
      @Then("点击\"$title\"{标题|按钮}后 ,确认跳转页面正确")
      public void verifyBannerTitleRedirectionStatus(@Named("title") String title) throws Exception {
            boolean result;
            if (title.equals("生成报告")) {
                  title = "报告管理";
            }
            result = testingBox.useViews().commonView.verifyNavigatePageSuccessfully(title);
            Assert.assertTrue("点击" + title + "标题或按钮后,页面跳转失败", result);
      }
      
      @Given("在\"$option\"列表中，点击\"$value\"按钮")
      @When("在\"$option\"列表中，点击\"$value\"按钮")
      public void clickAddHyperLinkWithText(@Named("value") String value) throws Exception {
            testingBox.useViews().commonView.clickOperationButtonOnTablePage(value);
      }
      
      
      @Given("在\"$banner\"列表中，{检测批次|}\"$task\"{创建|修改|执行}{成功|}")
      @When("在\"$banner\"列表中，{检测批次|}\"$task\"{创建|修改|执行}{成功|}")
      @Then("在\"$banner\"列表中，{检测批次|}\"$task\"{创建|修改|执行}{成功|}")
      public void verifyTaskInTable(@Named("banner") String banner,
                                    @Named("task") String task) throws Exception {
            if (banner.equals("用户")) {
                  if (task.length() > 10) {
                        task = task.substring(0, 10) + "...";
                  }
            } else if (banner.equals("函数白名单")) {
                  if (task.length() > 20) {
                        task = task.substring(0, 20) + "...";
                  }
            }
            boolean result;
            if (banner.equals("依赖库")) {
                  result = testingBox.useViews().commonView.verifyTaskInTable(task, "3");
            } else {
                  result = testingBox.useViews().commonView.verifyTaskInTable(task, "2");
            }
            Assert.assertTrue("在" + banner + "列表中,任务" + task + "创建失败", result);
      }
      
      @Given("在\"$banner\"列表中，{检测批次|}\"$task\"{删除成功|不存在|没有执行}")
      @When("在\"$banner\"列表中，{检测批次|}\"$task\"{删除成功|不存在|没有执行}")
      @Then("在\"$banner\"列表中，{检测批次|}\"$task\"{删除成功|不存在|没有执行}")
      public void verifyTaskNotInTable(@Named("banner") String banner,
                                       @Named("task") String task) throws Exception {
            if (banner.equals("用户")) {
                  if (task.length() > 10) {
                        task = task.substring(0, 10) + "...";
                  }
            } else if (banner.equals("函数白名单")) {
                  if (task.length() > 20) {
                        task = task.substring(0, 20) + "...";
                  }
            }
            boolean result;
            if (banner.equals("依赖库")) {
                  result = testingBox.useViews().commonView.verifyTaskInTable(task, "3");
            } else {
                  result = testingBox.useViews().commonView.verifyTaskInTable(task, "2");
            }
            Assert.assertTrue("任务" + task + "删除失败", !result);
      }
      
      
      @Given("在\"$option\"列表中,点击\"$name\"的\"$operation\"{图形按钮|按钮}")
      @When("在\"$option\"列表中,点击\"$name\"的\"$operation\"{图形按钮|按钮}")
      @Then("在\"$option\"列表中,点击\"$name\"的\"$operation\"{图形按钮|按钮}")
      public void clickOperationIconOnTaskList(@Named("name") String name,
                                               @Named("operation") String operation) throws Exception {
            if (name.length() > 20) {
                  name = name.substring(0, 20)+"...";
            }
            testingBox.useViews().commonView.clickRowsOperationButton(name, operation);
      }
      
      @Given("在\"$option\"列表中,勾选\"$name\"")
      @When("在\"$option\"列表中,勾选\"$name\"")
      @Then("在\"$option\"列表中,勾选\"$name\"")
      public void clickCheckBoxButtonInTaskListTable(@Named("name") String name) throws Exception {
            testingBox.useViews().commonView.clickCheckBoxButtonInTaskListTable(name);
      }
      
      @Given("在\"$option\"列表中,点击删除按钮")
      @When("在\"$option\"列表中,点击删除按钮")
      @Then("在\"$option\"列表中,点击删除按钮")
      public void clickDeleteChoiceOption() throws Exception {
            testingBox.useViews().commonView.clickDeleteButtonOnTablePage();
      }
      
      
      @Given("用户\"$account\"已被冻结")
      @When("用户\"$account\"已被冻结")
      @Then("用户\"$account\"已被冻结")
      public void verifyAccountBlocked(@Named("account") String account) throws Exception {
            testingBox.useViews().loginView.clickSignInButton();
            boolean result = testingBox.useViews().loginView.verifyAccountBlocked();
            Assert.assertTrue("用户" + account + "没有冻结", result);
      }
      
      @Given("在列表中,点击页码\"$pageNumber\"")
      @When("在列表中,点击页码\"$pageNumber\"")
      @Then("在列表中,点击页码\"$pageNumber\"")
      public void clickPageNumberHyperLink(@Named("pageNumber") String pageNumber) throws Exception {
            testingBox.useViews().commonView.clickPageNumberHyperLink(pageNumber);
      }
      
      @Given("刷新当前页面")
      @When("刷新当前页面")
      @Then("刷新当前页面")
      public void refreshPage() throws Exception {
            testingBox.useViews().commonView.refreshCurrentPage();
      }
      
      @Given("返回上一页")
      @When("返回上一页")
      @Then("返回上一页")
      public void backLastPage() throws Exception {
            testingBox.useViews().commonView.backLastPage();
      }
      
      @Given("在\"$option\"列表中,点击{任务|项目|源代码}名称\"$taskName\"")
      @When("在\"$option\"列表中,点击{任务|项目|源代码}名称\"$taskName\"")
      @Then("在\"$option\"列表中,点击{任务|项目|源代码}名称\"$taskName\"")
      public void clickTaskNameHyperLink(@Named("taskName") String taskName) throws Exception {
            testingBox.useViews().commonView.clickTaskNameHyperLink(taskName);
      }
      
      @Given("在\"$option\"列表中,在搜索框中输入\"$value\"")
      @When("在\"$option\"列表中,在搜索框中输入\"$value\"")
      @Then("在\"$option\"列表中,在搜索框中输入\"$value\"")
      public void inputSearchValueOnSearchField(@Named("value") String value) throws Exception{
            testingBox.useViews().commonView.inputSearchValueOnSearchField(value);
      }
      
      @Given("在\"$option\"列表中,点击搜索按钮")
      @When("在\"$option\"列表中,点击搜索按钮")
      @Then("在\"$option\"列表中,点击搜索按钮")
      public void clickSearchButtonOnTable() throws Exception{
            testingBox.useViews().commonView.clickSearchButtonOnTable();
      }
}
