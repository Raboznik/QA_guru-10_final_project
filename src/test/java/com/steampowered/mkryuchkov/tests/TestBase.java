package com.steampowered.mkryuchkov.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.steampowered.mkryuchkov.config.CredentialsConfig;
import com.steampowered.mkryuchkov.helper.Attach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.sleep;


public class TestBase {

    public static CredentialsConfig cfgs = ConfigFactory.create(CredentialsConfig.class);

    @BeforeAll
    static void setUp() {
        String browser = System.getProperty("browser", "chrome");
        String size = System.getProperty("size", "1920x1080");

        String remoteUrl = System.getProperty("remoteUrl", cfgs.remoteUrl());
        String login = System.getProperty("login", cfgs.remoteLogin());
        String pass = System.getProperty("pass", cfgs.remotePassword());

        Configuration.baseUrl = "https://store.steampowered.com/";
        Configuration.remote = "https://" + login + ":" + pass + "@" + remoteUrl;
        Configuration.browser = browser;
        Configuration.browserSize = size;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("allure", new AllureSelenide());

        Attach.attachAsText("Browser: ", browser);
        Attach.attachAsText("Remote Url: ", cfgs.remoteUrl());
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
