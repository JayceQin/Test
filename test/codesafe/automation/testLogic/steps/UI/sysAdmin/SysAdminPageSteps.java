package codesafe.automation.testLogic.steps.UI.sysAdmin;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SysAdminPageSteps extends StepsBase {
      
      TeamSystem testingBox;
      
      public SysAdminPageSteps() throws SQLException, IOException {
            super();
            testingBox = (TeamSystem) Serenity.getCurrentSession().get("TeamSystem");
      }
      
      @Given("在系统管理页面,点击\"$title\"下的\"$value\"导航按钮")
      @When("在系统管理页面,点击\"$title\"下的\"$value\"导航按钮")
      @Then("在系统管理页面,点击\"$title\"下的\"$value\"导航按钮")
      public void clickHyperLinkWithText(@Named("title") String title,
                                         @Named("value") String value) throws Exception {
            if (value.equals("")) {
                  testingBox.useViews().sysAdminView.clickSystemPageTopLevelNavigateTitle(title);
            } else {
                  testingBox.useViews().sysAdminView.clickSystemPageNavigateTitle(title, value);
            }
      }
      
      @Given("在部门列表中,部门\"$apartment\"的上级部门\"$sa\",描述正确\"$des\"")
      @When("在部门列表中,部门\"$apartment\"的上级部门\"$sa\",描述正确\"$des\"")
      @Then("在部门列表中,部门\"$apartment\"的上级部门\"$sa\",描述正确\"$des\"")
      public void verifyApartmentInformationCorrectly(@Named("apartment") String apartment,
                                                      @Named("sa") String sa,
                                                      @Named("des") String des) throws Exception {
            boolean result1 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(apartment, "上级部门", sa);
            Assert.assertTrue("部门 " + apartment + " 的上级部门 " + sa + " 不正确", result1);
            if (des.length() > 10) {
                  des = des.substring(0, 10) + "...";
            }
            boolean result2 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(apartment, "描述", des);
            Assert.assertTrue("部门 " + apartment + " 的描述 " + des + " 不正确", result2);
      }
      
      
      @Given("在用户列表中,用户\"$user\"的部门\"$apartment\",角色\"$role\",联系电话\"$phone\",电子邮件\"$mail\",描述\"$des\"正确")
      @When("在用户列表中,用户\"$user\"的部门\"$apartment\",角色\"$role\",联系电话\"$phone\",电子邮件\"$mail\",描述\"$des\"正确")
      @Then("在用户列表中,用户\"$user\"的部门\"$apartment\",角色\"$role\",联系电话\"$phone\",电子邮件\"$mail\",描述\"$des\"正确")
      public void verifyUserInfoCorrectly(@Named("user") String user,
                                          @Named("apartment") String apartment,
                                          @Named("role") String role,
                                          @Named("phone") String phone,
                                          @Named("mail") String mail,
                                          @Named("des") String des) throws Exception {
            boolean result2 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(user, "所属部门", apartment);
            Assert.assertTrue("用户 " + user + " 的部门 " + apartment + " 不正确", result2);
            boolean result3 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(user, "角色", role);
            Assert.assertTrue("用户 " + user + " 的角色 " + role + " 不正确", result3);
            boolean result4 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(user, "联系电话", phone);
            Assert.assertTrue("用户 " + user + " 的联系电话 " + phone + " 不正确", result4);
            if (mail.length() > 10) {
                  mail = mail.substring(0, 10) + "...";
            }
            boolean result5 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(user, "电子邮件", mail);
            Assert.assertTrue("用户 " + user + " 的电子邮件 " + mail + " 不正确", result5);
            if (des.length() > 10) {
                  des = des.substring(0, 10) + "...";
            }
            boolean result6 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(user, "描述", des);
            Assert.assertTrue("用户 " + user + " 的描述 " + des + " 不正确", result6);
      }
      
      
      @Given("在角色列表中,角色\"$role\"的描述\"$des\"正确")
      @When("在角色列表中,角色\"$role\"的描述\"$des\"正确")
      @Then("在角色列表中,角色\"$role\"的描述\"$des\"正确")
      public void verifyRoleInfoCorrectly(@Named("role") String role,
                                          @Named("des") String des) throws Exception {
            if (des.length() > 10) {
                  des = des.substring(0, 10) + "...";
            }
            boolean result = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(role, "描述", des);
            Assert.assertTrue("角色 " + role + " 的描述 " + des + " 不正确", result);
      }
      
      @Given("在角色列表中,角色\"$roleName\"的权限为\"$roleRight\"")
      @When("在角色列表中,角色\"$roleName\"的权限为\"$roleRight\"")
      @Then("在角色列表中,角色\"$roleName\"的权限为\"$roleRight\"")
      public void verifyRolesRightsCorrectly(@Named("roleName") String roleName,
                                             @Named("roleRight") String roleRight) throws Exception {
            boolean result;
            String sql = "select resource_name  from sys_resource where pk_resource in (select pk_resource from sys_role_resource where pk_role in (select pk_role from sys_role where role_name='<roleName>'))";
            sql = sql.replaceAll("<roleName>", roleName);
            ResultSet rs = testingBox.queryDatabase(sql);
            String str[] = roleRight.split(";");
            List<String> origin = Arrays.asList(str);
            List<String> list = new ArrayList<String>();
            while (rs.next()) {
                  String rights = rs.getString("resource_name");
                  list.add(rights);
            }
            result = origin.equals(list);
            
            Assert.assertTrue("角色" + roleName + "的权限不正确", result);
      }
      
      @Given("在\"$type\"列表中,模板\"$model\"的状态\"$status\"正确")
      @When("在\"$type\"列表中,模板\"$model\"的状态\"$status\"正确")
      @Then("在\"$type\"列表中,模板\"$model\"的状态\"$status\"正确")
      public void verifyCheckModelSetDefaltInPage(@Named("type") String type,
                                                  @Named("model") String model,
                                                  @Named("status") String status) throws Exception {
            if (model.length() > 20) {
                  model = model.substring(0, 20) + "...";
            }
            boolean result1 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(model, "状态", status);
            Assert.assertTrue(type + "检测模板" + model + " 的状态 " + status + " 不正确", result1);
      }
      
      
      @Given("在{\"$type\"检测|\"$type\"}模板列表中,\"$lang\"语言的默认模板为\"$model\"")
      @When("在{\"$type\"检测|\"$type\"}模板列表中,\"$lang\"语言的默认模板为\"$model\"")
      @Then("在{\"$type\"检测|\"$type\"}模板列表中,\"$lang\"语言的默认模板为\"$model\"")
      public void verifyCheckModelSetDefault(@Named("type") String type,
                                             @Named("lang") String lang,
                                             @Named("model") String model) throws Exception {
            boolean result = false;
            String language = testingBox.useViews().commonView.getLanguageCode(lang);
            String checkType = testingBox.useViews().commonView.getCheckTypeCode(type);
            String sql = "select template_name from sys_template where template_lang = '<lang>' and is_default = 'Y' and check_type = '<check_type>'";
            sql = sql.replaceAll("<lang>", language).replaceAll("<check_type>", checkType);
            System.out.println(sql);
            ResultSet rs = testingBox.queryDatabase(sql);
            while (rs.next()) {
                  String modelName = rs.getString("template_name");
                  if (modelName.equals(model)) {
                        result = true;
                  }
            }
            Assert.assertTrue(lang + "语言的" + type + "检测默认模板不是" + model, result);
      }
      
      
      @Given("在\"$type\"列表中,模板\"$model\"的开发语言\"$language\",创建者\"$user\",描述\"$des\"正确")
      @When("在\"$type\"列表中,模板\"$model\"的开发语言\"$language\",创建者\"$user\",描述\"$des\"正确")
      @Then("在\"$type\"列表中,模板\"$model\"的开发语言\"$language\",创建者\"$user\",描述\"$des\"正确")
      public void verifyCheckModelInfoCorrectly(@Named("type") String type,
                                                @Named("model") String model,
                                                @Named("language") String language,
                                                @Named("user") String user,
                                                @Named("des") String des) throws Exception {
            boolean result1 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(model, "检测语言", language);
            Assert.assertTrue(type + "检测模板" + model + " 的检测语言 " + language + " 不正确", result1);
            boolean result2 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(model, "创建者", user);
            Assert.assertTrue(type + "检测模板" + model + " 的创建者 " + user + " 不正确", result2);
            if (des.length() > 10) {
                  des = des.substring(0, 10) + "...";
            }
            boolean result3 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(model, "描述", des);
            Assert.assertTrue(type + "检测模板" + model + " 的描述 " + des + " 不正确", result3);
      }
      
      
      @Given("在统计模板列表中,模板的名称\"$name\",检测方式\"$checkType\",创建者\"$creator\",描述\"$des\"正确")
      @When("在统计模板列表中,模板的名称\"$name\",检测方式\"$checkType\",创建者\"$creator\",描述\"$des\"正确")
      @Then("在统计模板列表中,模板的名称\"$name\",检测方式\"$checkType\",创建者\"$creator\",描述\"$des\"正确")
      public void verifyStatisticsModelInfoCorrectly(@Named("name") String name,
                                                     @Named("checkType") String checkType,
                                                     @Named("creator") String creator,
                                                     @Named("des") String des) throws Exception {
            boolean result1 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(name, "检测类型", checkType);
            Assert.assertTrue("模板 " + name + " 的检测类型 " + checkType + " 不正确", result1);
            boolean result2 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(name, "创建者", creator);
            Assert.assertTrue("模板 " + name + " 的创建者 " + creator + " 不正确", result2);
            if (des.length() > 10) {
                  des = des.substring(0, 10);
            }
            boolean result3 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(name, "描述", des);
            Assert.assertTrue("模板 " + name + " 的描述 " + des + " 不正确", result3);
      }
      
      
      @Given("在模板列表中,模板\"$temName\"拥有一级规则\"$oneLevelTem\"下的二级检测规则\"$twoLevelTem\"")
      @When("在模板列表中,模板\"$temName\"拥有一级规则\"$oneLevelTem\"下的二级检测规则\"$twoLevelTem\"")
      @Then("在模板列表中,模板\"$temName\"拥有一级规则\"$oneLevelTem\"下的二级检测规则\"$twoLevelTem\"")
      public void verifyTemplateRulesCorrectly(@Named("temName") String temName,
                                               @Named("oneLevelTem") String oneLevelTem,
                                               @Named("twoLevelTem") String twoLevelTem) throws Exception {
            boolean result1 = false, result2 = false;
            boolean temp1 = true, temp2 = true;
            String checkSql = "select  distinct rule_type ,rule_name from sys_rule where pk_rule in ( select pk_rule from sys_template_rule where pk_template in (select pk_template from sys_template where template_name = '<TemName>'))";
            checkSql = checkSql.replaceAll("<TemName>", temName);
            String oneLevel[] = oneLevelTem.split(";");
            String twoLevel[] = twoLevelTem.split(";");
            List<String> ruleType = new ArrayList<String>();
            List<String> ruleName = new ArrayList<String>();
            ResultSet rs = testingBox.queryDatabase(checkSql);
            while (rs.next()) {
                  String rn1 = rs.getString("rule_type");
                  String rn2 = rs.getString("rule_name");
                  if (!ruleType.contains(rn1)) {
                        ruleType.add(rn1);
                  }
                  if (!ruleName.contains(rn2)) {
                        ruleName.add(rn2);
                  }
            }
            for (String one : oneLevel) {
                  if (!ruleType.contains(one)) {
                        temp1 = false;
                  }
            }
            if (temp1) {
                  result1 = true;
            }
            for (String two : twoLevel) {
                  if (!ruleName.contains(two)) {
                        temp2 = false;
                  }
            }
            if (temp2) {
                  result2 = true;
            }
            Assert.assertTrue("模板" + temName + "的检测规则不正确", result1 && result2);
      }
      
      
      @Given("在{新建|修改}统计模板页面的\"$option\"栏,输入\"$value\"")
      @When("在{新建|修改}统计模板页面的\"$option\"栏,输入\"$value\"")
      @Then("在{新建|修改}统计模板页面的\"$option\"栏,输入\"$value\"")
      public void inputFieldFromStatisticsModelForm(@Named("option") String option,
                                                    @Named("value") String value) throws Exception {
            if (option.equals("描述")) {
                  testingBox.useViews().sysAdminView.InputAreaFromStatisticsModelForm(option, value);
            } else {
                  testingBox.useViews().sysAdminView.inputFieldFromStatisticsModelForm(option, value);
            }
      }
      
      @Given("在{新建|修改}统计模板页面的\"$option\"栏,选择单选项\"$value\"")
      @When("在{新建|修改}统计模板页面的\"$option\"栏,选择单选项\"$value\"")
      @Then("在{新建|修改}统计模板页面的\"$option\"栏,选择单选项\"$value\"")
      public void clickRadioButtonFromStatisticsModelForm(@Named("option") String option,
                                                          @Named("value") String value) throws Exception {
            testingBox.useViews().sysAdminView.clickRadioButtonFromStatisticsModelForm(option, value);
      }
      
      @Given("在{新建|修改}统计模板页面的\"$option\"栏,点击添加语言按钮")
      @When("在{新建|修改}统计模板页面的\"$option\"栏,点击添加语言按钮")
      @Then("在{新建|修改}统计模板页面的\"$option\"栏,点击添加语言按钮")
      public void clickAddStatisticsModelForm(@Named("option") String option) throws Exception {
            testingBox.useViews().sysAdminView.clickAddStatisticsModelForm(option);
      }
      
      @Given("在{新建|修改}统计模板页面的\"$option\"栏,选择下拉框中的\"$language\"")
      @When("在{新建|修改}统计模板页面的\"$option\"栏,选择下拉框中的\"$language\"")
      @Then("在{新建|修改}统计模板页面的\"$option\"栏,选择下拉框中的\"$language\"")
      public void clickDropDownListOnStatisticsModelForm(@Named("language") String language) throws Exception {
            if (language.equals("Sky") || language.equals("Ft") || language.equals("Cx")) {
                  testingBox.useViews().sysAdminView.clickEngineListOnStatisticsModelForm(language);
            } else {
                  testingBox.useViews().sysAdminView.clickDropDownListOnStatisticsModelForm(language);
            }
      }
      
      @Given("在{新建|修改}统计模板页面的\"$option\"栏,点击已选择语言\"$language\"的Tab")
      @When("在{新建|修改}统计模板页面的\"$option\"栏,点击已选择语言\"$language\"的Tab")
      @Then("在{新建|修改}统计模板页面的\"$option\"栏,点击已选择语言\"$language\"的Tab")
      public void clickSeletedLanguageTab(@Named("option") String option,
                                          @Named("language") String language) throws Exception {
            testingBox.useViews().sysAdminView.clickSeletedLanguageTab(option, language);
      }
      
      @Given("在{新建|修改}统计模板页面的\"$option\"栏,点击已选择语言的编辑按钮")
      @When("在{新建|修改}统计模板页面的\"$option\"栏,点击已选择语言的编辑按钮")
      @Then("在{新建|修改}统计模板页面的\"$option\"栏,点击已选择语言的编辑按钮")
      public void clickSelectedLanguageEditButton(@Named("option") String option) throws Exception {
            testingBox.useViews().sysAdminView.clickSelectedLanguageEditButton(option);
      }
      
      @Given("在{新建|修改}统计模板页面的\"$option\"栏,点击已选择语言\"$language\"的删除按钮")
      @When("在{新建|修改}统计模板页面的\"$option\"栏,点击已选择语言\"$language\"的删除按钮")
      @Then("在{新建|修改}统计模板页面的\"$option\"栏,点击已选择语言\"$language\"的删除按钮")
      public void clickSeletedLanguageDeleteButton(@Named("option") String option,
                                                   @Named("language") String language) throws Exception {
            testingBox.useViews().sysAdminView.clickSeletedLanguageDeleteButton(option, language);
      }
      
      
      @Given("在{新建|修改}统计模板页面,点击保存按钮")
      @When("在{新建|修改}统计模板页面,点击保存按钮")
      @Then("在{新建|修改}统计模板页面,点击保存按钮")
      public void clickSaveButtonOnCreateStatisticsModelPage() throws Exception {
            testingBox.useViews().sysAdminView.clickSaveButtonOnCreateStatisticsModelPage();
            testingBox.useViews().commonPopWindowView.clickInfoCloseIcon();
      }
      
      
      @Given("在统计模板列表中,\"$checkType\"模板\"$temName\"拥有\"$lang\"语言一级检测规则\"$ruleType\"下的所有规则")
      @When("在统计模板列表中,\"$checkType\"模板\"$temName\"拥有\"$lang\"语言一级检测规则\"$ruleType\"下的所有规则")
      @Then("在统计模板列表中,\"$checkType\"模板\"$temName\"拥有\"$lang\"语言一级检测规则\"$ruleType\"下的所有规则")
      public void verifyStatisticsModelRuleTypeCorrectly(@Named("checkType") String checkType,
                                                         @Named("temName") String temName,
                                                         @Named("lang") String lang,
                                                         @Named("ruleType") String ruleType) throws Exception {
            boolean result = true;
            String type = testingBox.useViews().commonView.getCheckTypeCode(checkType);
            String language = testingBox.useViews().commonView.getLanguageCode(lang);
            String checkSql = "select  distinct rule_type ,rule_name from sys_rule where pk_rule in ( select pk_rule from sys_template_rule where pk_template in (select pk_template from sys_template where template_name = '<TemName>')) And language = <lang>";
            checkSql = checkSql.replaceAll("<TemName>", temName).replaceAll("<lang>", language);
            String checkSql2 = "select distinct rule_type ,rule_name from sys_rule where rule_type in (<ruleType>) And  language = <lang> And standard_type = <type> And is_use = 'Y' And tool = 'Sky'";
            checkSql2 = checkSql2.replaceAll("<ruleType>", ruleType).replaceAll("<lang>", language).replaceAll("<type>", type);
            List<String> ruleTypes = new ArrayList<String>();
            List<String> ruleNames = new ArrayList<String>();
            ResultSet rs = testingBox.queryDatabase(checkSql);
            System.out.println(checkSql);
            while (rs.next()) {
                  String rn1 = rs.getString("rule_type");
                  String rn2 = rs.getString("rule_name");
                  if (!ruleNames.contains(rn2)) {
                        ruleNames.add(rn2);
                  }
                  if (!ruleTypes.contains(rn1)) {
                        ruleTypes.add(rn1);
                  }
            }
            ResultSet rs2 = testingBox.queryDatabase(checkSql2);
            System.out.println(checkSql2);
            while (rs2.next()) {
                  String rn1 = rs2.getString("rule_type");
                  String rn2 = rs2.getString("rule_name");
                  if (!ruleTypes.contains(rn1)) {
                        result = false;
                  }
                  if (!ruleNames.contains(rn2)) {
                        result = false;
                  }
            }
            Assert.assertTrue("统计模板" + temName + "规则不正确", result);
      }
      
      @Then("在函数白名单列表中,白名单\"$name\"的类型\"$type\",检测语言\"$name\",包名\"$packageName\",类名\"$className\",方法名\"$functionName\",返回值\"$returnValue\",参数\"$parameter\",创建者\"$creator\"正确")
      public void verifySafeFunctionInfoCorrectly(@Named("name") String name,
                                                  @Named("type") String type,
                                                  @Named("lang") String lang,
                                                  @Named("packageName") String packageName,
                                                  @Named("className") String className,
                                                  @Named("functionName") String functionName,
                                                  @Named("returnValue") String returnValue,
                                                  @Named("parameter") String parameter,
                                                  @Named("creator") String creator) throws Exception {
            if (name.length() > 20) {
                  name = name.substring(0, 20) + "...";
            }
            boolean result1 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(name, "类型", type);
            Assert.assertTrue("函数白名单 " + name + " 的类型 " + type + " 不正确", result1);
            boolean result2 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(name, "检测语言", lang);
            Assert.assertTrue("函数白名单 " + name + " 的检测语言 " + lang + " 不正确", result2);
            boolean result3 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(name, "包名/命名空间", packageName);
            Assert.assertTrue("函数白名单 " + name + " 的包名 " + packageName + " 不正确", result3);
            boolean result4 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(name, "类名", className);
            Assert.assertTrue("函数白名单 " + name + " 的类名 " + className + " 不正确", result4);
            boolean result5 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(name, "方法名", functionName);
            Assert.assertTrue("函数白名单 " + name + " 的方法名 " + functionName + " 不正确", result5);
            if (returnValue.length() > 20) {
                  returnValue = returnValue.substring(0, 20) + "...";
            }
            boolean result6 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(name, "返回值", returnValue);
            Assert.assertTrue("函数白名单 " + name + " 的返回值 " + returnValue + " 不正确", result6);
            if (parameter.length() > 20) {
                  parameter = parameter.substring(0, 20) + "...";
            }
            boolean result7 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(name, "参数列表", parameter);
            Assert.assertTrue("函数白名单 " + name + " 的参数列表 " + parameter + " 不正确", result7);
            boolean result8 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(name, "创建者", creator);
            Assert.assertTrue("函数白名单 " + name + " 的创建者 " + creator + " 不正确", result8);
      }
      
      
      @Given("在\"$option\"列表中,编辑引擎\"$ip\"并发数为\"$value\"")
      public void editEngineConcurrency(@Named("ip") String ip,
                                        @Named("value") String value) throws Exception {
            testingBox.useViews().sysAdminView.editEngineConcurrency(ip, value);
      }
      
      
      @Given("在\"$option\"列表中,验证引擎\"$ip\"并发数修改成功,为\"$value\"")
      public void verifyEngineConcurrency(@Named("ip") String ip,
                                          @Named("value") String value) throws Exception {
            boolean result = false;
            String sql = "SELECT DISTINCT concurrent_num FROM sys_engine where  engine_ip='<ip>'";
            sql = sql.replaceAll("<ip>", ip);
            ResultSet rs = testingBox.queryDatabase(sql);
            while (rs.next()) {
                  int concurrency = rs.getInt(1);
                  result = (concurrency == Integer.parseInt(value));
            }
            Assert.assertTrue("引擎并发数修改失败", result);
      }
      
      
      @Given("在邮箱配置页面中,在\"$option\"栏输入\"$value\"")
      public void inputEmailConfigInfoInSysAdmin(@Named("option") String option,
                                                 @Named("value") String value) throws Exception {
            testingBox.useViews().sysAdminView.inputEmailConfigInfoInSysAdmin(option, value);
      }
      
      @Given("在邮箱配置页面中,在身份验证栏点击\"$value\"")
      public void clickRadioBtnInSysAdmin(@Named("value") String value) throws Exception {
            testingBox.useViews().sysAdminView.clickRadioBtnInSysAdmin(value);
      }
      
      @Given("在邮箱配置页面中,点击保存按钮")
      public void clickSubmitBtnInSysAdmin() throws Exception {
            testingBox.useViews().sysAdminView.clickSubmitBtnInSysAdmin();
            testingBox.useViews().commonPopWindowView.clickInfoCloseIcon();
      }
      
      @Given("配置邮箱后，验证邮箱\"$email\"配置成功")
      public void verifyEmailConfigSuccessfully(@Named("email") String email) throws Exception {
            String sql = "SELECT * FROM sys_email where from_user='<user>'";
            sql = sql.replaceAll("<user>", email);
            ResultSet rs = testingBox.queryDatabase(sql);
            rs.last();
            int rows = rs.getRow();
            Assert.assertTrue("邮箱配置失败", rows > 0);
      }
      
      
      @When("在\"$option\"列表中,删除任务名称为\"$taskName\",检测方式为\"$checkType\"的队列")
      @Then("在\"$option\"列表中,删除任务名称为\"$taskName\",检测方式为\"$checkType\"的队列")
      public void clickDeleteQueueOnQueueTable(@Named("taskName") String taskName,
                                               @Named("checkType") String checkType) throws Exception {
            testingBox.useViews().commonView.clickDeleteQueueHypeLink(taskName, checkType);
      }
      
      @When("在\"$option\"列表中,任务名称为\"$taskName\",检测方式为\"$checkType\"的队列创建成功")
      @Then("在\"$option\"列表中,任务名称为\"$taskName\",检测方式为\"$checkType\"的队列创建成功")
      public void verifyTaskQueueCreatedSuccessd(@Named("taskName") String taskName,
                                                 @Named("checkType") String checkType) throws Exception {
            boolean result = testingBox.useViews().commonView.verifyTaskQueueCreatedSuccessd(taskName, checkType);
            Assert.assertTrue("任务名称为" + taskName + ",检测方式为" + checkType + "的队列创建失败", result);
      }
      
      
      @Given("在系统清理页面中,输入系统清理开始时间\"$startTime\"和结束时间\"$endTime\"")
      @When("在系统清理页面中,输入系统清理开始时间\"$startTime\"和结束时间\"$endTime\"")
      @Then("在系统清理页面中,输入系统清理开始时间\"$startTime\"和结束时间\"$endTime\"")
      public void inputStartTimeInCleanSystemPage(@Named("startTime") String startTime,
                                                  @Named("endTime") String endTime) throws Exception {
            testingBox.useViews().sysAdminView.inputStartTimeInCleanSystemPage(startTime);
            testingBox.useViews().sysAdminView.inputEndTimeInCleanSystemPage(endTime);
      }
      
      @Given("在系统清理页面中,输入系统清理时间为testAuto,TestAuto用户创建快速检测任务与项目管理任务的时间")
      @When("在系统清理页面中,输入系统清理时间为testAuto,TestAuto用户创建快速检测任务与项目管理任务的时间")
      @Then("在系统清理页面中,输入系统清理时间为testAuto,TestAuto用户创建快速检测任务与项目管理任务的时间")
      public void inputEndTimeInCleanSystemPage() throws Exception {
            String pk_user1 = "select pk_user from sys_user where user_name = 'testAuto'";
            String pk_user2 = "select pk_user from sys_user where user_name = 'AutoTest'";
            String quick = "select distinct date(ts) from chk_quick where pk_user = '<pk_user>'";
            String project = "select distinct date(ts) from chk_project where pk_user='<pk_user>'";
            String pk1 = "";
            String pk2 = "";
            String d1 = "", d2 = "";
            ResultSet rs1 = testingBox.queryDatabase(pk_user1);
            while (rs1.next()) {
                  pk1 = rs1.getString("pk_user");
            }
            ResultSet rs2 = testingBox.queryDatabase(pk_user2);
            while (rs2.next()) {
                  pk2 = rs2.getString("pk_user");
            }
            quick = quick.replaceAll("<pk_user>", pk1);
            project = project.replaceAll("<pk_user>", pk2);
            ResultSet q = testingBox.queryDatabase(quick);
            while (q.next()) {
                  d1 = q.getString("date(ts)");
            }
            ResultSet p = testingBox.queryDatabase(project);
            while (p.next()) {
                  d2 = p.getString("date(ts)");
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (d1 != "" && d2 != "") {
                  if (dateFormat.parse(d1).getTime() < dateFormat.parse(d2).getTime()) {
                        testingBox.useViews().sysAdminView.inputStartTimeInCleanSystemPage(d1);
                        testingBox.useViews().sysAdminView.inputEndTimeInCleanSystemPage(d2);
                  } else if (dateFormat.parse(d1).getTime() > dateFormat.parse(d2).getTime()) {
                        testingBox.useViews().sysAdminView.inputStartTimeInCleanSystemPage(d2);
                        testingBox.useViews().sysAdminView.inputEndTimeInCleanSystemPage(d1);
                  } else if (dateFormat.parse(d1).getTime() == dateFormat.parse(d2).getTime()) {
                        testingBox.useViews().sysAdminView.inputStartTimeInCleanSystemPage(d1);
                        testingBox.useViews().sysAdminView.inputEndTimeInCleanSystemPage(d1);
                  }
            } else if (d1 == "") {
                  testingBox.useViews().sysAdminView.inputStartTimeInCleanSystemPage(d2);
                  testingBox.useViews().sysAdminView.inputEndTimeInCleanSystemPage(d2);
            } else if (d2 == "") {
                  testingBox.useViews().sysAdminView.inputStartTimeInCleanSystemPage(d1);
                  testingBox.useViews().sysAdminView.inputEndTimeInCleanSystemPage(d1);
            }
      }
      
      @Given("在系统清理页面中,勾选多选按钮\"$option\"")
      @When("在系统清理页面中,勾选多选按钮\"$option\"")
      @Then("在系统清理页面中,勾选多选按钮\"$option\"")
      public void clickCheckBoxButtonInCleanSystemPage(@Named("option") String option) throws Exception {
            testingBox.useViews().sysAdminView.clickCheckBoxButtonInCleanSystemPage(option);
      }
      
      @Given("在系统清理页面中,取消勾选多选按钮\"$option\"")
      @When("在系统清理页面中,取消勾选多选按钮\"$option\"")
      @Then("在系统清理页面中,取消勾选多选按钮\"$option\"")
      public void unclickCheckBoxButtonInCleanSystemPage(@Named("option") String option) throws Exception {
            testingBox.useViews().sysAdminView.unclickCheckBoxButtonInCleanSystemPage(option);
      }
      
      @Given("在系统清理页面中,点击单选按钮\"$option\"")
      @When("在系统清理页面中,点击单选按钮\"$option\"")
      @Then("在系统清理页面中,点击单选按钮\"$option\"")
      public void clickRadioButtonInCleanSystemPage(@Named("option") String option) throws Exception {
            testingBox.useViews().sysAdminView.clickRadioButtonInCleanSystemPage(option);
      }
      
      @Given("在系统清理页面中,输入用户登录密码\"$password\"")
      @When("在系统清理页面中,输入用户登录密码\"$password\"")
      @Then("在系统清理页面中,输入用户登录密码\"$password\"")
      public void inputLoginPasswordInputField(@Named("password") String password) throws Exception {
            testingBox.useViews().sysAdminView.inputLoginPasswordInputField(password);
      }
      
      @Given("在系统清理页面中,点击开始清理按钮")
      @When("在系统清理页面中,点击开始清理按钮")
      @Then("在系统清理页面中,点击开始清理按钮")
      public void clickStartCleanButton() throws Exception {
            testingBox.useViews().sysAdminView.clickStartCleanButton();
      }
      
      @Given("在系统清理页面中,点击继续清理按钮")
      @When("在系统清理页面中,点击继续清理按钮")
      @Then("在系统清理页面中,点击继续清理按钮")
      public void clickContinueCleanButton() throws Exception {
            testingBox.useViews().sysAdminView.clickContinueCleanButton();
      }
      
      
      @Given("在系统清理页面中,输入开始清理具体时间\"$date\"-\"$hour\"-\"$min\"")
      @When("在系统清理页面中,输入开始清理具体时间\"$date\"-\"$hour\"-\"$min\"")
      @Then("在系统清理页面中,输入开始清理具体时间\"$date\"-\"$hour\"-\"$min\"")
      public void inputDetailTimeOnCleanSystemPage(@Named("date") String date,
                                                   @Named("hour") String hour,
                                                   @Named("min") String min) throws Exception {
            testingBox.useViews().sysAdminView.inputCleanScheduleTime(date);
            testingBox.useViews().sysAdminView.inputHourInputFieldOnCleanSystemPage(hour);
            testingBox.useViews().sysAdminView.inputMinInputFieldOnCleanSystemPage(min);
      }
      
      @Given("点击系统清理按钮后,系统清理成功")
      @When("点击系统清理按钮后,系统清理成功")
      @Then("点击系统清理按钮后,系统清理成功")
      public void verifyCleanSystemSuccessfully() throws Exception {
            boolean wait = testingBox.useViews().sysAdminView.verifyCleanSystemSucc();
            boolean result = false;
            String quick_code = "select * from chk_code where pk_code in (select pk_code from chk_quick where pk_user in (select pk_user from sys_user where user_name = 'testAuto'))";
            String project_code = "select * from chk_code where pk_code in (select pk_code from chk_project_config where pk_project in (select pk_project from chk_project where pk_user in (select pk_user from sys_user where user_name = 'AutoTest')))";
            String task = "select *  from chk_task where pk_user in (select pk_user from sys_user where user_name = 'testAuto' or user_name = 'AutoTest')";
            String task_tool = "seletc * from chk_task_tool where pk_user in (select pk_user from sys_user where user_name = 'testAuto' or user_name = 'AutoTest')";
            String bug_table = "SELECT * FROM information_schema.TABLES WHERE table_name like 'chk_res_bug%' and TABLE_SCHEMA = 'codesafe'";
            if (wait == true) {
                  ResultSet rs1 = testingBox.queryDatabase(quick_code);
                  ResultSet rs2 = testingBox.queryDatabase(project_code);
                  ResultSet rs3 = testingBox.queryDatabase(task);
                  ResultSet rs4 = testingBox.queryDatabase(task_tool);
                  ResultSet rs5 = testingBox.queryDatabase(bug_table);
                  rs5.last();
                  int rows = rs5.getRow();
                  if (!rs1.next() && !rs2.next() && !rs3.next() && !rs4.next() && rows == 5) {
                        result = true;
                  }
            }
            Assert.assertTrue("系统清理失败，数据库中存在残留数据", result);
      }
      
      
      @Given("在依赖库列表中,依赖库\"$libs\"的布局类型\"$layoutType\",依赖库地址\"$site\",创建者\"$user\"正确")
      @When("在依赖库列表中,依赖库\"$libs\"的布局类型\"$layoutType\",依赖库地址\"$site\",创建者\"$user\"正确")
      @Then("在依赖库列表中,依赖库\"$libs\"的布局类型\"$layoutType\",依赖库地址\"$site\",创建者\"$user\"正确")
      public void verifyDependentLibrariesInfoCorrectly(@Named("libs") String libs,
                                                        @Named("layoutType") String layoutType,
                                                        @Named("site") String site,
                                                        @Named("user") String user) throws Exception {
            boolean result1 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(libs, "布局类型", layoutType);
            Assert.assertTrue("依赖库" + libs + "的布局类型" + layoutType + "不正确", result1);
            boolean result2 = testingBox.useViews().commonView.verifyMavenSiteCorrectly(libs, site);
            Assert.assertTrue("依赖库" + libs + "的地址" + site + "不正确", result2);
            boolean result3 = testingBox.useViews().commonView.verifyColsValueCorrectlyInTaskListTable(libs, "创建者", user);
            Assert.assertTrue("依赖库" + libs + "的创建者" + user + "不正确", result3);
      }
      
      @Then("在当前页面,点击资源下载按钮")
      public void clickSourceDownloadHyperLink() throws Exception {
            testingBox.useViews().sysAdminView.clickSourceDownloadHyperLink();
      }
      
      @Given("创建用户成功后,修改用户\"$userName\"数据库中的is_first参数")
      @Then("创建用户成功后,修改用户\"$userName\"数据库中的is_first参数")
      public void setUserFirstLoginParam(@Named("userName") String userName) throws Exception {
            String updateSql = "Update sys_user set is_first = 0 where user_name = '<user_name>'";
            updateSql = updateSql.replaceAll("<user_name>",userName);
            testingBox.updateDatabase(updateSql);
      }
      
      @Given("在初次登录修改密码页面,输入旧密码\"$oldPass\"")
      public void inputOnePasswordInputFild(@Named("oldPass") String oldPass) throws Exception{
            testingBox.useViews().sysAdminView.inputOnePasswordInputFild(oldPass);
      }
      
      @Given("在初次登录修改密码页面,输入新密码\"$newPass\"")
      public void inputNewOnePasswordInputFild(@Named("newPass") String newPass) throws Exception{
            testingBox.useViews().sysAdminView.inputNewOnePasswordInputFild(newPass);
      }
      
      @Given("在初次登录修改密码页面,确认新密码\"$conPass\"")
      public void inputNewTwoPasswordInputFild(@Named("conPass") String conPass) throws Exception{
            testingBox.useViews().sysAdminView.inputNewTwoPasswordInputFild(conPass);
      }
      
      @Given("在初次登录修改密码页面,点击提交按钮")
      public void clickSubmitButtonOnUpdatePasswordPage() throws Exception{
            testingBox.useViews().sysAdminView.clickSubmitButtonOnUpdatePasswordPage();
      }
}
