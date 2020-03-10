package codesafe.automation.testLogic;

import net.serenitybdd.jbehave.SerenityStories;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(SerenityRunner.class)
public class TestExecution extends SerenityStories {
      TestConfiguration runConfig;
      protected Logger logger;
      
      public TestExecution() {
            logger = LogManager.getLogger(TestExecution.class.getName());
            runConfig = new TestConfiguration();
            
            configuredEmbedder().useMetaFilters(runConfig.getFilters());
            configuredEmbedder().embedderControls().useStoryTimeouts("720m");
            configuredEmbedder().embedderControls().ignoreFailureInStories();
            // configuredEmbedder().embedderControls().useThreads(2);
            String pathType = runConfig.getStoryPathType();
            if (pathType != null) {
                  if (pathType.contentEquals("stories_called") && runConfig.getStoriesCalledPath() != null) {
                        findStoriesCalled(runConfig.getStoriesCalledPath());
                  }
                  if (pathType.contentEquals("stories_in") && runConfig.getStoriesInPath() != null) {
                        findStoriesIn(runConfig.getStoriesInPath());
                  }
            }
      }
      
      @Override
      public List<String> storyPaths() {
            if (runConfig.getStoryPathType().contentEquals("stories_called") || runConfig.getStoryPathType().contentEquals("stories_in")) {
                  return super.storyPaths();
            } else {
                  return runConfig.getStoryOrderPaths();
            }
      }
}