package com.browserstack.test.suites;

import com.browserstack.local.Local;
import com.browserstack.test.utils.MobileHelper;
import com.browserstack.test.utils.ScreenshotListener;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Listeners({ScreenshotListener.class})
public class TestBase {
    private static final String PATH_TO_TEST_CAPS_JSON = "src/test/resources/conf/capabilities/test_caps.json";
    private static final ThreadLocal<AppiumDriver<?>> driver = new ThreadLocal();
    private static final String BROWSERSTACK_HUB_URL = "hub-cloud.browserstack.com";
    private static final long TIMESTAMP = (new Date()).getTime();
    private static final String appLocation = FileSystems.getDefault().getPath(System.getProperty("user.dir"), "/src/test/resources/app").toString();
    private Local local;
    public MobileHelper mobileHelper;

    public AppiumDriver<?> getDriver() {
        return driver.get();
    }

    @BeforeMethod
    @Parameters({"environment", "testType", "env_cap_id"})
    public void setUp(@Optional("remote") String environment, @Optional("single") String testType, @Optional("0") int env_cap_id, Method m) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject testCapsConfig = (JSONObject)parser.parse(new FileReader(PATH_TO_TEST_CAPS_JSON));
        if (environment.equalsIgnoreCase("on-prem")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", testCapsConfig.get("platformName").toString());
            capabilities.setCapability("deviceName", testCapsConfig.get("deviceName").toString());
            capabilities.setCapability("platformVersion", testCapsConfig.get("platformVersion").toString());
            capabilities.setCapability("app", appLocation.concat(File.separator).concat(testCapsConfig.get("app.local").toString()));
            capabilities.setCapability("automationName", testCapsConfig.get("automationName").toString());
            driver.set(new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities));
        } else if (environment.equalsIgnoreCase("remote")) {
            JSONObject profilesJson = (JSONObject)testCapsConfig.get("tests");
            JSONObject envs = (JSONObject)profilesJson.get(testType);

            Map<String, String> envCapabilities = (Map)((JSONArray)envs.get("env_caps")).get(env_cap_id);
            Map<String, String> localCapabilities = (Map<String, String>) envs.get("local_binding_caps");
            Map<String, String> commonCapabilities = (Map<String, String>) envs.get("common_caps");

            String app = envCapabilities.get("platformName").equalsIgnoreCase("android") ? testCapsConfig.get("app.android").toString() : testCapsConfig.get("app.ios").toString();
            commonCapabilities.put("app", app);
            commonCapabilities.put("name", m.getName());
            commonCapabilities.put("build", (String)commonCapabilities.get("build") + " - " + TIMESTAMP);
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.merge(new DesiredCapabilities(commonCapabilities));
            caps.merge(new DesiredCapabilities(envCapabilities));
            if (testType.equals("local")) {
                caps.merge(new DesiredCapabilities(localCapabilities));
            }

            String username = getUsername(testCapsConfig);
            String accessKey = getAccessKey(testCapsConfig);

            createSecureTunnelIfNeeded(caps, testCapsConfig);
            driver.set(new AppiumDriver(new URL("https://" + username + ":" + accessKey + "@" + BROWSERSTACK_HUB_URL + "/wd/hub"), caps));
        } else if (environment.equalsIgnoreCase("docker")) {
            System.out.println("TO BE DECIDED");
        }

        mobileHelper = new MobileHelper(getDriver());
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    private String getUsername(JSONObject testCapsConfig) {
        String username = System.getenv("BROWSERSTACK_USERNAME");
        if (username == null) {
            username = testCapsConfig.get("user").toString();
        }

        return username;
    }

    private String getAccessKey(JSONObject testCapsConfig) {
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = testCapsConfig.get("key").toString();
        }

        return accessKey;
    }

    private void createSecureTunnelIfNeeded(DesiredCapabilities caps, JSONObject testCapsConfig) throws Exception {
        if (caps.getCapability("browserstack.local") != null
                && caps.getCapability("browserstack.local").equals("true")) {
            local = new Local();
            UUID uuid = UUID.randomUUID();
            caps.setCapability("browserstack.localIdentifier", uuid.toString());
            Map<String, String> options = new HashMap<>();
            options.put("key", getAccessKey(testCapsConfig));
            options.put("localIdentifier", uuid.toString());
            local.start(options);
        }
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
    }
}
