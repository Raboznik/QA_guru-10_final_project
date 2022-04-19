package com.steampowered.mkryuchkov.pages.components;

import com.codeborne.selenide.SelenideElement;
import com.steampowered.mkryuchkov.pages.MainPage;
import com.steampowered.mkryuchkov.tests.TestBase;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.href;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;

public class FooterMenuComponent extends TestBase {

    private SelenideElement footer = $("#footer_text");

    @Step("Check footer links")
    public MainPage checkFootersHrefs(MainPage po) {
        footer.scrollTo().shouldBe(visible);
        footer.find("div", 1).find("a", 0).shouldHave(href(baseUrl + "privacy_agreement/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 1).shouldHave(href(baseUrl + "legal/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 2).shouldHave(href(baseUrl + "subscriber_agreement/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 3).shouldHave(href(baseUrl + "steam_refunds/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 4).shouldHave(href(baseUrl + "account/cookiepreferences/?snr=1_44_44_"));

        return new MainPage();
    }
}

