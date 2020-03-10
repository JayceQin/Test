package codesafe.automation.drivers.selenium;


import codesafe.automation.drivers.selenium.views.LoginView;
import codesafe.automation.drivers.selenium.views.auditDefectPage.AuditInfoView;
import codesafe.automation.drivers.selenium.views.configTaskPage.ConfigTaskInformationView;
import codesafe.automation.drivers.selenium.views.fastCheckPage.FastCheckTaskListView;
import codesafe.automation.drivers.selenium.views.popWindowView.CommonPopWindowView;
import codesafe.automation.drivers.selenium.views.commonView.CommonView;
import codesafe.automation.drivers.selenium.views.projectManagerPage.ProjectManagerView;
import codesafe.automation.drivers.selenium.views.reportManagePage.ReportManageView;
import codesafe.automation.drivers.selenium.views.resultIntegrationPage.ResultIntegrationView;
import codesafe.automation.drivers.selenium.views.statisticalAnalysisPage.StatisticalAnalysisView;
import codesafe.automation.drivers.selenium.views.systemAdminPage.SysAdminView;
import codesafe.automation.drivers.utils.TestSystem;
import org.openqa.selenium.WebDriver;

public class CodeSafeDriver extends BrowserDriver {
      
      /**
       * These are the views (also called page objects) that the CodeSafeDriver instance keeps track of in order to allow
       * the Steps classes to call the methods in the views.
       */
      public LoginView loginView;
      public CommonView commonView;
      public CommonPopWindowView commonPopWindowView;
      public SysAdminView sysAdminView;
      public ConfigTaskInformationView configTaskInformationView;
      public StatisticalAnalysisView statisticalAnalysisView;
      public FastCheckTaskListView fastCheckTaskListView;
      public AuditInfoView auditInfoView;
      public ProjectManagerView projectManagerView;
      public ReportManageView reportManageView;
      public ResultIntegrationView resultIntegrationView;
      
      public CodeSafeDriver(TestSystem testMachine) throws Exception {
            super(testMachine);
      }
      
      public LoginView getLoginView() {
            return loginView;
      }
      
      public CommonView getCommonView() {
            return commonView;
      }
      
      public CommonPopWindowView getCommonPopWindowView() {
            return commonPopWindowView;
      }
      
      public SysAdminView getSysAdminView() {
            return sysAdminView;
      }
      
      public ConfigTaskInformationView getConfigTaskInformationView() {
            return configTaskInformationView;
      }
      
      public StatisticalAnalysisView getStatisticalAnalysisView() {
            return statisticalAnalysisView;
      }
      
      public FastCheckTaskListView getFastCheckTaskListView() {
            return fastCheckTaskListView;
      }
      
      public AuditInfoView getAuditInfoView() {
            return auditInfoView;
      }
      
      public ProjectManagerView getProjectManagerView() {
            return projectManagerView;
      }
      
      public ReportManageView getReportManageView() {
            return reportManageView;
      }
      
      public ResultIntegrationView getResultIntegrationView() {
            return resultIntegrationView;
      }
      
      public void addViews(WebDriver driver) throws Exception {
            if (loginView == null) loginView = new LoginView(driver);
            if (commonView == null) commonView = new CommonView(driver);
            if (commonPopWindowView == null) commonPopWindowView = new CommonPopWindowView(driver);
            if (sysAdminView == null) sysAdminView = new SysAdminView(driver);
            if (configTaskInformationView == null) configTaskInformationView = new ConfigTaskInformationView(driver);
            if (statisticalAnalysisView == null) statisticalAnalysisView = new StatisticalAnalysisView(driver);
            if (fastCheckTaskListView == null) fastCheckTaskListView = new FastCheckTaskListView(driver);
            if (auditInfoView == null) auditInfoView = new AuditInfoView(driver);
            if (projectManagerView == null) projectManagerView = new ProjectManagerView(driver);
            if (reportManageView == null) reportManageView = new ReportManageView(driver);
            if (resultIntegrationView == null) resultIntegrationView = new ResultIntegrationView(driver);
      }
      
      @Override
      public synchronized WebDriver getWebDriver(String browser, String locale) throws Exception {
            WebDriver driver = super.getWebDriver(browser, locale);
            addViews(driver);
            return driver;
      }
      
      public void loadPage(String browser, String locale, String urlString, int height, int width) throws Exception {
            WebDriver driver = super.getWebDriver(browser, locale);
            driver.get(urlString);
            setDimensions(height, width);
            addViews(driver);
      }
}