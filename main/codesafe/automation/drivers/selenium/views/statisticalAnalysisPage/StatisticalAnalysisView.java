package codesafe.automation.drivers.selenium.views.statisticalAnalysisPage;

import codesafe.automation.drivers.selenium.views.BaseView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StatisticalAnalysisView extends BaseView {
      
      /**
       * Intentionally empty constructor method to support instantiation.
       *
       * @param acDriver
       */
      public StatisticalAnalysisView(WebDriver acDriver) throws Exception {
            super(acDriver);
      }
      
      /**
       * 获取统计分析页面中的顶级元素
       */
      private WebElement getAnalysisTopElement() {
            return findElementThatMayNotBePresent(By.cssSelector("div[class='analysis-section ng-scope']"));
      }
      
      
      /**
       * 获取统计分析页面中选择条件的顶级元素
       */
      private WebElement getAnalysisAlternativeConditionBar() {
            return getAnalysisTopElement().findElement(By.className("analysis-operation-bar"));
      }
      
      
      /**
       * 获取统计分析页面中展示区域的顶级元素
       */
      private WebElement getAnalysisShowArea() {
            return getAnalysisTopElement().findElement(By.className("analysis-main"));
      }
      
      /**
       * 获取统计分析页面中的更新统计按钮
       */
      private WebElement getUpdateStatisticalButton() {
            return getAnalysisAlternativeConditionBar().findElement(By.className("analysis-operation-statistics-right")).findElement(By.tagName("button"));
      }
      
      /**
       * 获取统计分析页面中的导出word报告按钮
       */
      private WebElement getExportWordReportOnAnalysisPage() {
            return getAnalysisAlternativeConditionBar().findElement(By.cssSelector("div[class='analysis-operation-export ng-scope']")).findElement(By.cssSelector("button[ng-click='exportDocument(0)']"));
      }
      
      /**
       * 获取统计分析页面中的导出excel报告按钮
       */
      private WebElement getExportExcelReportOnAnalysisPage() {
            return getAnalysisAlternativeConditionBar().findElement(By.cssSelector("div[class='analysis-operation-export ng-scope']")).findElement(By.cssSelector("button[ng-click='exportDocument(1)']"));
      }
      
      /**
       * 获取统计分析页面中所有的选择条件
       *
       * @param title 选择条件
       */
      private WebElement getAllAlternativeConditionBars(String title) {
            WebElement element = null;
            List<WebElement> lists = getAnalysisAlternativeConditionBar().findElement(By.className("analysis-operation-statistics-left")).findElements(By.cssSelector("div[class *= 'ana-btn-item']"));
            for (WebElement e : lists) {
                  if (e.getText().contains(title)) {
                        element = e;
                  }
            }
            return element;
      }
      
      
      /**
       * 获取统计分析页面中选择条件的下拉框
       *
       * @param title 选择条件
       * @param value 选项
       */
      private WebElement getAlternativeConditionDropDownList(String title, String value) {
            WebElement element = null;
            List<WebElement> lists = getAllAlternativeConditionBars(title).findElement(By.tagName("ul")).findElements(By.tagName("li"));
            for (WebElement e : lists) {
                  if (e.getText().contains(value)) {
                        element = e;
                  }
            }
            return element;
      }
      
      /**
       * 获取统计分析页面中选择任务或选择项目下拉框
       *
       * @param title 选中任务或选择项目
       */
      private WebElement getChooseTaskDropDownWindow(String title) {
            return getAllAlternativeConditionBars(title).findElement(By.cssSelector("div[class='ng-isolate-scope'][type='project-multi']"));
      }
      
      
      /**
       * 获取统计分析页面中选择任务或选择项目下拉框中的,任务和部门统计按钮
       *
       * @param title 选中任务或选择项目
       * @param value 任务,项目或部门
       */
      private WebElement getTaskOrApartmentCondition(String title, String value) {
            WebElement element = null;
            List<WebElement> lists = getChooseTaskDropDownWindow(title).findElement(By.tagName("ul")).findElements(By.tagName("li"));
            for (WebElement e : lists) {
                  if (e.getAttribute("heading").equals(value)) {
                        element = e;
                  }
            }
            return element;
      }
      
      /**
       * 获取选择任务或选择项目中的全选按钮
       *
       * @param title 选中任务或选择项目
       */
      private WebElement getSelectAllCondition(String title) {
            return getChooseTaskDropDownWindow(title).findElement(By.cssSelector("div[class='tab-content']")).findElement(By.cssSelector("a[class='ng-scope'][ng-if='defaultOptions.isAllBtn'][ng-click='all(true)']"));
      }
      
      /**
       * 获取以部门中,选择全部部门选项
       *
       * @param title 选中任务或选择项目
       */
      private WebElement getApartmentSelectAllCondition(String title) {
            WebElement element = null;
            List<WebElement> list = getChooseTaskDropDownWindow(title).findElement(By.cssSelector("ul[class *='ztree']")).findElements(By.tagName("li"));
            for (WebElement e : list) {
                  if (e.getText().contains("全选")) {
                        element = e.findElement(By.id("anaOrgTree_1_check"));
                  }
            }
            return element;
      }
      
      
      /**
       * 获取时间选择器控件
       */
      private WebElement getCalendarSelector() {
            WebElement element;
            List<WebElement> lists = findElements(By.cssSelector("div[class='daterangepicker ltr show-ranges show-calendar opensright']"));
            if (lists.size() == 1) {
                  element = lists.get(0);
            } else {
                  element = lists.get(lists.size() - 1);
            }
            return element;
      }
      
      /**
       * 获取时间中的范围选项
       */
      private WebElement getDateSelectRanges(String range) {
            WebElement element = null;
            List<WebElement> lists = getCalendarSelector().findElements(By.tagName("li"));
            for (WebElement e : lists) {
                  if (e.getAttribute("data-range-key").equals(range)) {
                        element = e;
                  }
            }
            
            return element;
      }
      
      
      /**
       * 获取统计分析title 区域
       *
       * @param title 按项目,部门,经理统计
       */
      private WebElement getAnalysisImageAndDataArea(String title) {
            WebElement element = null;
            List<WebElement> list = getAnalysisShowArea().findElements(By.cssSelector("div[class='analysis-item ng-scope']"));
            for (WebElement e : list) {
                  if (e.findElement(By.cssSelector("div[class *='analysis-item-title']")).getText().contains(title)) {
                        element = e;
                  }
            }
            return element;
      }
      
      
      /**
       * 获取统计分析title 区域的所有语言分类按钮
       *
       * @param title    按项目,部门,经理统计
       * @param language 语言e.g. java,c#
       */
      private WebElement getAllLanguageButtonsFromArea(String title, String language) {
            WebElement element = null;
            WebElement target = getAnalysisImageAndDataArea(title).findElement(By.cssSelector("div[class='analysis-item-body']")).findElement(By.cssSelector("div[class='toggle-language-operation']")).findElement(By.cssSelector("div[class='btn-group']"));
            List<WebElement> list = target.findElements(By.tagName("label"));
            for (WebElement e : list) {
                  if (e.getText().equals(language)) {
                        element = e;
                  }
            }
            return element;
      }
      
      /**
       * 随机获取溯源组件影响项目数超链接
       *
       * @param title 漏洞统计
       */
      private WebElement getInfluencingTaskNumberFromArea(String title) {
            WebElement target = getAnalysisImageAndDataArea(title).findElement(By.cssSelector("div[class='analysis-item-body']")).findElement(By.cssSelector("div[class='analysis-item-body-main ng-scope']")).findElement(By.cssSelector("div[class='analysis-table']")).findElement(By.cssSelector("tbody[class='ant-table-tbody']"));
            List<WebElement> list = target.findElements(By.cssSelector("a[ng-click='reloadProjectDetail(0,item.projects)']"));
            int num = (int) (Math.random() * list.size());
            return list.get(num);
      }
      
      
      /**
       * 点击统计分析页面中的更新统计按钮
       */
      public void clickUpdateStatisticalButton() {
            retryClick(getUpdateStatisticalButton());
      }
      
      /**
       * 点击统计分析页面的筛选条件下拉框
       *
       * @param title e.g. 检测方式
       */
      public void clickAllAlternativeConditionBars(String title) {
            retryClick(getAllAlternativeConditionBars(title));
      }
      
      /**
       * 点击统计分析页面中选择条件的下拉框
       *
       * @param title 选择条件
       * @param value 选项
       */
      public void clickAlternativeConditionDropDownList(String title, String value) {
            retryClick(getAlternativeConditionDropDownList(title, value));
      }
      
      /**
       * 点击统计分析页面中选择任务或选择项目下拉框中的,任务和部门统计按钮
       *
       * @param title 选中任务或选择项目
       * @param value 任务,项目或部门
       */
      public void clickTaskOrApartmentCondition(String title, String value) {
            retryClick(getTaskOrApartmentCondition(title, value));
      }
      
      /**
       * 点击选择任务或选择项目中的全选按钮
       *
       * @param title 选中任务或选择项目
       */
      public void clickSelectAllCondition(String title) {
            retryClick(getSelectAllCondition(title));
      }
      
      /**
       * 点击部门列表中的全选按钮
       *
       * @param title 选中任务或选择项目
       */
      public void clickApartmentSelectAllCondition(String title) {
            retryClick(getApartmentSelectAllCondition(title));
      }
      
      
      /**
       * 点击时间选择器中的范围按钮
       *
       * @param ranges 最近3天,最近7天
       */
      public void clickDateSelectRanges(String ranges) {
            retryClick(getDateSelectRanges(ranges));
      }
      
      /**
       * 获取统计分析title 区域的所有语言分类按钮
       *
       * @param title    按项目,部门,经理统计
       * @param language 语言e.g. java,c#
       */
      public void clickAllLanguageButtonOnShowDataArea(String title, String language) {
            retryClick(getAllLanguageButtonsFromArea(title, language));
      }
      
      /**
       * 点击统计分析页面中的导出word报告按钮
       */
      public void clickExportWordReportButtonOnAnalysisPage() {
            System.out.println(getExportWordReportOnAnalysisPage().isEnabled());
            retryClick(getExportWordReportOnAnalysisPage());
      }
      
      /**
       * 点击统计分析页面中的导出excel报告按钮
       */
      public void clickExportExcelReportButtonOnAnalysisPage() {
            retryClick(getExportExcelReportOnAnalysisPage());
      }
      
      /**
       * 点击获取溯源组件影响项目数超链接
       *
       * @param title 漏洞统计
       */
      public void clickInfluencingTaskNumberFromArea(String title) {
            retryClick(getInfluencingTaskNumberFromArea(title));
      }
}