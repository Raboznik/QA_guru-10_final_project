package ru.yandex.mkryuchkov.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.yandex.mkryuchkov.config.CredentialsConfig;
import ru.yandex.mkryuchkov.helper.Attach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.sleep;


public class TestBase {

    protected String baseUrl = "https://github.com/Raboznik/QA_guru-10_final_project";


    @BeforeAll
    static void setUp() {

        CredentialsConfig cfgs = ConfigFactory.create(CredentialsConfig.class);

        String browser = System.getProperty("browser", "chrome");
//        String version = System.getProperty("version", "91");
        String size = System.getProperty("size", "1920x1080");

        String remoteUrl = System.getProperty("remoteUrl", "selenoid.autotests.cloud/wd/hub");
        String login = System.getProperty("login", cfgs.remoteLogin());
        String pass = System.getProperty("pass", cfgs.remotePassword());

//        Configuration.remote = "https://" + login + ":" + pass + "@" + remoteUrl;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        Configuration.browser = browser;
//        Configuration.browserVersion = version;
        Configuration.browserSize = size;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("allure", new AllureSelenide());

        Attach.attachAsText("Browser: ", browser);
//        Attach.attachAsText("Version: ", version);
        Attach.attachAsText("Remote Url: ", "selenoid.autotests.cloud/wd/hub");

    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        sleep(5000);
        closeWebDriver();
    }

}