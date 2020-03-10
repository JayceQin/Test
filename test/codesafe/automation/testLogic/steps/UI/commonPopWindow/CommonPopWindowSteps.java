package codesafe.automation.testLogic.steps.UI.commonPopWindow;

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

public class CommonPopWindowSteps extends StepsBase {
      TeamSystem testingBox;
      
      public CommonPopWindowSteps() throws SQLException, IOException {
            super();
            testingBox = (TeamSystem) Serenity.getCurrentSession().get("TeamSystem");
      }
      
      @Given("在\"$window\"弹出窗中的\"$option\"栏,输入\"$value\"")
      @When("在\"$window\"弹出窗中的\"$option\"栏,输入\"$value\"")
      @Then("在\"$window\"弹出窗中的\"$option\"栏,输入\"$value\"")
      public void inputValueInTextFieldOnPopWindow(@Named("option") String option,
                                                   @Named("value") String value) throws Exception {
            testingBox.useViews().commonPopWindowView.inputValueInTextFiledOnPopWindows(option, value);
      }
      
      
      @When("在\"$page\"弹出窗中的\"$option\"栏,展开下拉框并选择\"$apartment\"下的{用户|子部门}\"$value\"")
      public void selectValueOnPopWindow(@Named("option") String option,
                                         @Named("apartment") String apartment,
                                         @Named("value") String value) throws Exception {
            if (!apartment.equals("")) {
                  testingBox.useViews().commonPopWindowView.expandDropDownListOnPopWindow(option);
                  if (value.equals("")) {
                        testingBox.useViews().commonPopWindowView.clickDropDownListValue(option, apartment);
                  } else {
                        testingBox.useViews().commonPopWindowView.expandSpecificFiledOnPopWindow(option, apartment);
                        testingBox.useViews().commonPopWindowView.clickDropDownListValue(option, value);
                  }
            }
      }
      
      @When("在\"$page\"弹出窗中的\"$option\"栏,选择\"$right\"下的\"$secondaryRight\"")
      @Then("在\"$page\"弹出窗中的\"$option\"栏,选择\"$right\"下的\"$secondaryRight\"")
      public void selectAccessOnPopWindow(@Named("option") String option,
                                          @Named("right") String right,
                                          @Named("secondaryRight") String secondaryRight) throws Exception {
            if (right.equals("") && secondaryRight.equals("全选")) {
                  testingBox.useViews().commonPopWindowView.clickOnSelectAllOnPopWindow(option);
            } else if (right.equals("") && !secondaryRight.equals("")) {
                  testingBox.useViews().commonPopWindowView.clickAccessRightOnRightList(option, secondaryRight);
            } else if (!right.equals("") && !secondaryRight.equals("")) {
                  String[] temp = right.split(";");
                  for (String s : temp) {
                        testingBox.useViews().commonPopWindowView.expandAccessRightOnAccessList(option, s);
                  }
                  testingBox.useViews().commonPopWindowView.clickAccessRightOnRightList(option, secondaryRight);
            }
      }
      
      @When("在\"$page\"弹出窗中的\"$option\"栏,勾选 \"$value\" 选项")
      public void clickCheckBoxBtnOnPopWindow(@Named("option") String option,
                                              @Named("value") String value) throws Exception {
            testingBox.useViews().commonPopWindowView.clickCheckBoxBtnOnPopWindow(option, value);
      }
      
      
      @When("在\"$page\"弹出窗中的\"$option\"栏,取消勾选 \"$value\" 选项")
      public void unSelectCheckBoxBtnOnPopWindow(@Named("option") String option,
                                                 @Named("value") String value) throws Exception {
            testingBox.useViews().commonPopWindowView.unClickCheckBoxBtnOnPopWindow(option, value);
      }
      
      
      @When("在\"$page\"弹出窗中的\"$option\"栏,单选 \"$value\" 项")
      public void clickRadioBtnOnPopWindow(@Named("option") String option,
                                           @Named("value") String value) throws Exception {
            testingBox.useViews().commonPopWindowView.clickRadioBtnOnPopWindow(option, value);
      }
      
      
      @Given("在\"$page\"弹出窗中的\"$option\"栏,选择下拉列表中的\"$value\"")
      @When("在\"$page\"弹出窗中的\"$option\"栏,选择下拉列表中的\"$value\"")
      @Then("在\"$page\"弹出窗中的\"$option\"栏,选择下拉列表中的\"$value\"")
      public void selectDropDownListValueOnPopWindow(@Named("option") String option,
                                                     @Named("value") String value) throws Exception {
            testingBox.useViews().commonPopWindowView.selectDropDownListValueOnPopWindow(option, value);
      }
      
      @Given("在上传资源文件弹出窗中,点击浏览按钮")
      @When("在上传资源文件弹出窗中,点击浏览按钮")
      @Then("在上传资源文件弹出窗中,点击浏览按钮")
      public void clickScanButtonOnPopWindows() throws Exception {
            testingBox.useViews().commonPopWindowView.clickSourceUploadScanButton();
      }
      
      @Given("在弹出窗中,点击\"$value\"超链接")
      @When("在弹出窗中,点击\"$value\"超链接")
      @Then("在弹出窗中,点击\"$value\"超链接")
      public void clicHyperLinkOnPopWindow(@Named("value") String value) throws Exception {
            testingBox.useViews().commonPopWindowView.clicHyperLinkOnPopWindow(value);
      }
      
      @Given("在函数白名单弹出窗中,选择函数白名单\"$name\"")
      @When("在函数白名单弹出窗中,选择函数白名单\"$name\"")
      @Then("在函数白名单弹出窗中,选择函数白名单\"$name\"")
      public void selectSafeFunctionOnPopWindow(@Named("name") String name) throws Exception {
            testingBox.useViews().commonPopWindowView.selectSafeFunctionOnPopWindow(name);
      }
      
      
      @When("在{指定人员|导出报告|访问权限|项目经理|设置权限}弹出窗中{的\"$option\"栏|},选择\"$v1\"下的\"$v2\"")
      @Then("在{指定人员|导出报告|访问权限|项目经理|设置权限}弹出窗中{的\"$option\"栏|},选择\"$v1\"下的\"$v2\"")
      public void selectAccessUserOnAccessUserWindow(@Named("option") String option,
                                                     @Named("v1") String v1,
                                                     @Named("v2") String v2) throws Exception {
            if (!v1.equals("")) {
                  String[] temp = v1.split(";");
                  for (String s : temp) {
                        testingBox.useViews().commonPopWindowView.expandAccessRightOnAccessList("", s);
                  }
                  testingBox.useViews().commonPopWindowView.clickAccessRightOnRightList(option, v2);
            } else {
                  testingBox.useViews().commonPopWindowView.clickAccessRightOnRightList(option, v2);
            }
      }
      
      @Given("点击按钮后,确认当前弹出框为\"$title\"弹出窗")
      @When("点击按钮后,确认当前弹出框为\"$title\"弹出窗")
      public void verifyPopWindow(@Named("title") String title) throws Exception {
            boolean result;
            result = testingBox.useViews().commonPopWindowView.verifyPopWindowTitleText(title);
            Assert.assertTrue("当前弹出窗不是" + title + "弹出窗", result);
      }
      
      @Given("在弹出窗中,点击{保存|确认|添加|生成|修改} 按钮")
      @When("在弹出窗中,点击{保存|确认|添加|生成|修改} 按钮")
      @Then("在弹出窗中,点击{保存|确认|添加|生成|修改} 按钮")
      public void clickSaveButtonOnPopWindow() throws Exception {
            testingBox.useViews().commonPopWindowView.clickSaveBtnOnPopWindow();
            testingBox.useViews().commonPopWindowView.clickInfoCloseIcon();
      }
      
      
      @Given("在弹出窗中,点击{取消|关闭} 按钮")
      @When("在弹出窗中,点击{取消|关闭} 按钮")
      @Then("在弹出窗中,点击{取消|关闭} 按钮")
      public void clickCloseButtonOnPopWindow() throws Exception {
            testingBox.useViews().commonPopWindowView.clickCloseBtnOnPopWindow();
      }
      
      
      @Given("在弹出窗中,生成按钮不可点击")
      @When("在弹出窗中,生成按钮不可点击")
      @Then("在弹出窗中,生成按钮不可点击")
      public void verfiySaveOrExportButtonDisable() throws Exception {
            boolean result = testingBox.useViews().commonPopWindowView.verifySaveOrExportButtonDisable();
            Assert.assertTrue("弹出窗中的保存/生成/确认按钮可点击", result);
      }
      
      
      @When("导出\"$type\"报告窗口的\"$option\"中有\"$value\"类型")
      @Then("导出\"$type\"报告窗口的\"$option\"中有\"$value\"类型")
      public void verifyDefectTypeOnExportReportWindow(@Named("type") String type,
                                                       @Named("option") String option,
                                                       @Named("value") String value) throws Exception {
            boolean result = testingBox.useViews().commonPopWindowView.verifyDefectTypeOnExportReportWindow(option, value);
            Assert.assertTrue("导出报告窗口没有检测模板中的" + type + "类型的缺陷", result);
      }
      
      @Given("在\"$page\"弹出窗中的\"$option\"栏,点击按钮")
      @When("在\"$page\"弹出窗中的\"$option\"栏,点击按钮")
      @Then("在\"$page\"弹出窗中的\"$option\"栏,点击按钮")
      public void clickScanButtonOnAddCodePopWindow(@Named("option") String option) throws Exception {
            testingBox.useViews().commonPopWindowView.clickScanButtonOnAddCodePopWindow(option);
      }
      
      
      @Given("在添加源代码弹出窗中的上传文件栏,文件上传成功")
      @When("在添加源代码弹出窗中的上传文件栏,文件上传成功")
      @Then("在添加源代码弹出窗中的上传文件栏,文件上传成功")
      public void verifyResultFileUploadSuccess() throws Exception {
            boolean result;
            result = testingBox.useViews().commonPopWindowView.verifyResultFileUploadSuccessOnPopWindows();
            Assert.assertTrue("在添加源代码弹出窗中的上传文件栏,文件上传失败", result);
      }
      
      @Given("在添加源代码弹出窗中,输入SvnGit地址\"$site\"")
      @When("在添加源代码弹出窗中,输入SvnGit地址\"$site\"")
      @Then("在添加源代码弹出窗中,输入SvnGit地址\"$site\"")
      public void inputPopWindowSvnGitUriInputField(@Named("site") String site) throws Exception {
            testingBox.useViews().commonPopWindowView.inputPopWindowSvnGitUriInputField(site);
      }
      
      
      @Given("在添加源代码弹出窗中,输入Git分支名\"$branch\"")
      @When("在添加源代码弹出窗中,输入Git分支名\"$branch\"")
      @Then("在添加源代码弹出窗中,输入Git分支名\"$branch\"")
      public void inputPopWindowGitBranchInputField(@Named("branch") String branch) throws Exception {
            testingBox.useViews().commonPopWindowView.inputPopWindowGitBranchInputField(branch);
      }
      
      @Given("在添加源代码弹出窗中,输入SvnGit用户名\"$username\"")
      @When("在添加源代码弹出窗中,输入SvnGit用户名\"$username\"")
      @Then("在添加源代码弹出窗中,输入SvnGit用户名\"$username\"")
      public void inputSvnGitUserNameOnPopWindow(@Named("username") String username) throws Exception {
            testingBox.useViews().commonPopWindowView.inputSvnGitUserNameOnPopWindow(username);
      }
      
      @Given("在添加源代码弹出窗中,输入SvnGit密码\"$pass\"")
      @When("在添加源代码弹出窗中,输入SvnGit密码\"$pass\"")
      @Then("在添加源代码弹出窗中,输入SvnGit密码\"$pass\"")
      public void inputSvnGitPassOnPopWindow(@Named("pass") String pass) throws Exception {
            testingBox.useViews().commonPopWindowView.inputSvnGitPassOnPopWindow(pass);
      }
      
      
      @Given("在设置权限弹出窗中,用户\"$users\"选择成功")
      @When("在设置权限弹出窗中,用户\"$users\"选择成功")
      @Then("在设置权限弹出窗中,用户\"$users\"选择成功")
      public void verifySetPermissionSucc(@Named("users") String users) throws Exception {
            boolean result;
            result = testingBox.useViews().commonPopWindowView.verifySetPermissionSucc(users);
            Assert.assertTrue("指定人员在右侧不显示", result);
      }
      
      @Given("在确认弹出窗中,点击{取消|关闭} 按钮")
      @When("在确认弹出窗中,点击{取消|关闭} 按钮")
      @Then("在确认弹出窗中,点击{取消|关闭} 按钮")
      public void clickCloseButtonOnConfirmWindow() throws Exception {
            testingBox.useViews().commonPopWindowView.clickCloseBtnOnConfirmWindow();
      }
      
      @Given("在确认弹出窗中,点击{保存|确认|删除|检测} 按钮")
      @When("在确认弹出窗中,点击{保存|确认|删除|检测} 按钮")
      @Then("在确认弹出窗中,点击{保存|确认|删除|检测} 按钮")
      public void clickSaveButtonOnConfirmWindow() throws Exception {
            testingBox.useViews().commonPopWindowView.clickSaveBtnOnConfirmWindow();
            testingBox.useViews().commonPopWindowView.clickInfoCloseIcon();
      }
      
      @Given("错误信息弹出窗弹出,错误信息为\"$msg\"")
      @When("错误信息弹出窗弹出,错误信息为\"$msg\"")
      @Then("错误信息弹出窗弹出,错误信息为\"$msg\"")
      public void verifyErrorMsgCorrected(@Named("msg") String msg) throws Exception {
            boolean result = testingBox.useViews().commonPopWindowView.verifyErrorMsgPopUp(msg);
            Assert.assertTrue("错误信息不正确", result);
      }
      
      @Given("在该页面进行操作后,没有弹出任何错误信息弹出窗")
      @When("在该页面进行操作后,没有弹出任何错误信息弹出窗")
      @Then("在该页面进行操作后,没有弹出任何错误信息弹出窗")
      public void verifyErrorMsgExist() throws Exception {
            boolean result = testingBox.useViews().commonPopWindowView.verifyErrorMsgExist();
            Assert.assertTrue("该页面弹出错误信息弹出窗", !result);
      }
}
