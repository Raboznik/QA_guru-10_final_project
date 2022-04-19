package com.steampowered.mkryuchkov.pages.components;

import com.codeborne.selenide.SelenideElement;
import com.steampowered.mkryuchkov.pages.MainPage;
import io.qameta.allure.Step;
import com.steampowered.mkryuchkov.tests.TestBase;

import static com.codeborne.selenide.Condition.href;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;

public class NavigationMenuComponent extends TestBase {

    SelenideElement header = $("#global_header .supernav_container");

    @Step("Check headers links")
    public MainPage searchHrefsOfHeaderMenu(MainPage po) {
        header.shouldBe(visible);

        header.find(".menuitem", 0).shouldHave(href(baseUrl + "?snr=1_4_4__global-header"));
        header.find(".menuitem", 1).shouldHave(href("https://steamcommunity.com/"));
        header.find(".menuitem", 2).shouldHave(href(baseUrl + "about/?snr=1_4_4__global-header"));
        header.find(".menuitem", 3).shouldHave(href("https://help.steampowered.com/en/"));

        return new MainPage();
    }
}
