package codesafe.automation.testLogic;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestConfiguration {
      private Logger logger;
      private JSONObject jsonConfig;
      private String targetAddress;
      private String targetSqlClass;
      private String targetUrl;
      private String targetSqlUser;
      private String targetSqlPass;
      private String serverUser;
      private String serverPass;
      private File runConfigFile;
      public final String defaultConfigLocation = "src/test/resources/target-configuration.json";
      private final String defaultStoryLocation = "src/test/resources/stories";
      
      public TestConfiguration() {
            logger = LogManager.getLogger(TestConfiguration.class.getName());
            
            runConfigFile = null;
            if (System.getProperty("localconfig") != null) {
                  File passedConfigFile = new File(System.getProperty("localconfig"));
                  if (passedConfigFile.exists()) {
                        runConfigFile = passedConfigFile;
                        logger.info("- Using local configuration file at " + System.getProperty("runconfig"));
                  } else {
                        logger.warn("- Local configuration file passed as system property was not found at " + System.getProperty("runconfig"));
                  }
            }
            
            if (runConfigFile == null && System.getProperty("remoteconfig") != null) {
                  try {
                        URL url = new URL(System.getProperty("remoteconfig"));
                        String[] nameChunks = url.getPath().split("/");
                        Path localConfig = new File(nameChunks[nameChunks.length - 1]).toPath();
                        Files.copy(url.openStream(), localConfig, StandardCopyOption.REPLACE_EXISTING);
                        runConfigFile = localConfig.toFile();
                        logger.info("- Using remote configuration file at " + System.getProperty("remoteconfig"));
                  } catch (MalformedURLException mue) {
                        logger.warn("- Remote configuration file passed as system property has malformed URL: " + System.getProperty("remoteconfig"));
                  } catch (IOException ioe) {
                        logger.warn("- Remote configuration file passed as system property cannot be obtained at URL: " + System.getProperty("remoteconfig"));
                  }
            }
            
            if (runConfigFile == null) {
                  Path defaultConfigPath = Paths.get(System.getProperty("user.dir"), defaultConfigLocation);
                  File defaultConfigFile = defaultConfigPath.toFile();
                  if (defaultConfigFile.exists()) {
                        runConfigFile = defaultConfigFile;
                        logger.info("- Using default configuration file at " + runConfigFile.getAbsolutePath());
                  } else {
                        logger.warn("- Default configuration file was missing at " + defaultConfigFile.getAbsolutePath());
                  }
            }
            
            if (runConfigFile != null) {
                  JSONParser parser = new JSONParser();
                  try {
                        Object jsonObject = parser.parse(new FileReader(runConfigFile.getAbsolutePath()));
                        logger.trace("- Loaded configuration file " + runConfigFile.getAbsolutePath());
                        jsonConfig = (JSONObject) jsonObject;
                        if (jsonConfig.get("website") != null) {
                              targetAddress = jsonConfig.get("website") != null ? jsonConfig.get("website").toString() : null;
                        }
                        
                        if (jsonConfig.get("server") != null) {
                              JSONArray configSection = (JSONArray) jsonConfig.get("server");
                              for (Object eachTag : configSection) {
                                    JSONObject tagSet = (JSONObject) eachTag;
                                    serverUser = tagSet.get("username") != null ? tagSet.get("username").toString() : null;
                                    serverPass = tagSet.get("password") != null ? tagSet.get("password").toString() : null;
                              }
                        }
                        
                        if (jsonConfig.get("sql") != null) {
                              JSONArray configSection = (JSONArray) jsonConfig.get("sql");
                              for (Object eachTag : configSection) {
                                    JSONObject tagSet = (JSONObject) eachTag;
                                    targetSqlClass = tagSet.get("sqlClass") != null ? tagSet.get("sqlClass").toString() : null;
                                    targetUrl = tagSet.get("sqlUrl") != null ? tagSet.get("sqlUrl").toString() : null;
                                    targetSqlUser = tagSet.get("sqlUser") != null ? tagSet.get("sqlUser").toString() : null;
                                    targetSqlPass = tagSet.get("sqlPass") != null ? tagSet.get("sqlPass").toString() : null;
                              }
                        }
                        
                  } catch (FileNotFoundException notFoundException) {
                        logger.warn("- File " + runConfigFile.getAbsolutePath() + " could not be found");
                  } catch (IOException ioException) {
                        logger.warn("- File " + runConfigFile.getAbsolutePath() + " could not be opened: " + ioException.getMessage());
                  } catch (ParseException parseException) {
                        logger.warn("- File " + runConfigFile.getAbsolutePath() + " could not be parsed: " + parseException.getMessage());
                  }
            } else {
                  logger.warn("- Running without configuration!");
            }
      }
      
      public String getTargetAddress() {
            return targetAddress;
      }
      
      public String getTargetUrl() {
            return targetUrl;
      }
      
      public String getTargetSqlClass() {
            return targetSqlClass;
      }
      
      public String getTargetSqlUser() {
            return targetSqlUser;
      }
      
      public String getTargetSqlPass() {
            return targetSqlPass;
      }
      
      public String getServerUser() {
            return serverUser;
      }
      
      public String getServerPass() {
            return serverPass;
      }
      
      public File getRunConfigFile() {
            return runConfigFile;
      }
      
      public List<String> getFilters() {
            List<String> filters = new ArrayList<>();
            filters.add("-Ignore");
            logger.info("- Excluding scenarios with the 'Ignore' meta tag");
            
            if (jsonConfig != null && jsonConfig.get("meta_tags") != null) {
                  JSONArray configSection = (JSONArray) jsonConfig.get("meta_tags");
                  for (Object eachTag : configSection) {
                        JSONObject tagSet = (JSONObject) eachTag;
                        String newAction = tagSet.get("action").toString();
                        String newTag = tagSet.get("tag_name").toString();
                        if (newAction.contentEquals("include") && newTag.length() > 0) {
                              filters.add("+" + newTag);
                              logger.info("- Only including scenarios with meta tag of '" + newTag + "'");
                        } else if (newAction.contentEquals("exclude") && newTag.length() > 0) {
                              filters.add("-" + newTag);
                              logger.info("- Excluding scenarios with the '" + newTag + "' meta tag");
                        } else {
                              logger.warn("- Invalid meta tag combination ignored: action=" + newAction + "; tag=" + newTag);
                        }
                  }
            }
            return filters;
      }
      
      public List<String> getStoryPaths(String pathType) {
            List<String> stories = new ArrayList<>();
            if (jsonConfig != null) {
                  if (jsonConfig.get("story_paths") != null) {
                        JSONArray configSection = (JSONArray) jsonConfig.get("story_paths");
                        for (Object eachTag : configSection) {
                              JSONObject tagSet = (JSONObject) eachTag;
                              String newDetermination = tagSet.get("determination").toString();
                              String newPath = tagSet.get("path").toString();
                              if (newDetermination.contains(pathType)) {
                                    stories.add(newPath);
                              }
                        }
                  } else {
                        stories.add("**/*");
                  }
            } else {
                  stories.add("**/*");
            }
            return stories;
      }
      
      public String getStoryPathType() {
            String storyType = null;
            if (jsonConfig != null) {
                  if (jsonConfig.get("story_paths") != null) {
                        JSONObject pathSection = (JSONObject) jsonConfig.get("story_paths");
                        if (pathSection.containsKey("determination")) {
                              storyType = (String) pathSection.get("determination");
                        } else {
                              logger.error("- Invalid story_paths section: 'determination' key is missing");
                        }
                  }
            }
            return storyType;
      }
      
      public String getStoriesCalledPath() {
            String storyPath = null;
            if (jsonConfig != null) {
                  if (jsonConfig.get("story_paths") != null) {
                        JSONObject pathSection = (JSONObject) jsonConfig.get("story_paths");
                        if (pathSection.containsKey("determination")) {
                              String determination = (String) pathSection.get("determination");
                              if (determination != null && determination.contentEquals("stories_called")) {
                                    storyPath = (String) pathSection.get("paths");
                              }
                        }
                  }
            }
            return storyPath;
      }
      
      public String getStoriesInPath() {
            String storyPath = null;
            if (jsonConfig != null) {
                  if (jsonConfig.get("story_paths") != null) {
                        JSONObject pathSection = (JSONObject) jsonConfig.get("story_paths");
                        if (pathSection.containsKey("determination")) {
                              String determination = (String) pathSection.get("determination");
                              if (determination != null && determination.contentEquals("stories_in")) {
                                    storyPath = (String) pathSection.get("paths");
                              }
                        }
                  }
            }
            return storyPath;
      }
      
      public List<String> getStoryOrderPaths() {
            List<String> storyPaths = new ArrayList<>();
            if (jsonConfig != null) {
                  if (jsonConfig.get("story_paths") != null) {
                        JSONObject pathSection = (JSONObject) jsonConfig.get("story_paths");
                        if (pathSection.containsKey("determination") && pathSection.get("determination").equals("story_order")) {
                              JSONArray orderedPaths = (JSONArray) pathSection.get("paths");
                              for (int index = 0; index < orderedPaths.size(); index++) {
                                    storyPaths.add((String) orderedPaths.get(index));
                              }
                        } else {
                              logger.error("- Missing story_order key");
                        }
                  }
            }
            return storyPaths;
      }
      
      private boolean pathExists(String storyPath) {
            List<String> pathPieces = new ArrayList<>(Arrays.asList(storyPath.split("/")));
            if (pathPieces.get(0).equals("**")) pathPieces.remove(0);
            String targetPath = String.join("/", pathPieces);
            Path storyDirectoryPath = Paths.get(System.getProperty("user.dir"), defaultStoryLocation, targetPath);
            File storyDirectory = storyDirectoryPath.toFile();
            return storyDirectory.exists();
      }
}
