package ru.yandex.mkryuchkov.tests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SteamTest extends TestBase {

    private TestData testData = new TestData();
    private String chatPhrase = "Hello World!";

    @DisplayName("Smoke test of Steam main page")
    @Test
    void steamMainPageTest() {

        testData
                .openMainPage()
                .inspectMainContentBlock()
                .inspectMainPage()
                .inspectNavigationMenu()
                .inspectGutterBlock()
                .inspectSpecialOffers()
                .inspectCommunityRecommendation()
                .inspectPopularContent()
                .inspectPopularVrGames()
                .inspectHomeTab()
                .inspectLiveStreamTab()
                .inspectCheapGamesTab()
                .inspectLowerLogin()
                .inspectFooter();
    }


    @DisplayName("Search game test")
    @Test
    void searchGameTest() {

        step("Open main page", () -> {
            open(baseUrl);
        });

        step("Click on search bar", () -> {
            $("#store_nav_search_term").click();
        });

        step("Type searched game", () -> {
            $("#store_nav_search_term").setValue("Dota 2").pressEnter();
        });

        step("Inspect search result list", () -> {
            $("#search_resultsRows").shouldBe(visible).$("a")
                    .shouldHave(href("https://store.steampowered.com/app/570/Dota_2/?snr=1_7_7_151_150_1"));
        });
    }


    @DisplayName("Login test")
    @Test
    void loginTest() {

        step("Open main page", () -> {
            open(baseUrl);
        });

        step("Open login page", () -> {
            $("#global_action_menu").$(".global_action_link").click();
        });

        step("Type login", () -> {
            $("#input_username").click();
            $("#input_username").setValue(cfgs.login());
        });

        step("Type password", () -> {
            $("#input_password").click();
            $("#input_password").setValue(cfgs.password());
        });

        step("Click login", () -> {
            $("#login_btn_signin").$(".login_btn").click();
        });

        step("Verify login", () -> {
            $("#account_pulldown").shouldHave(text(cfgs.login()));
        });
    }


    @DisplayName("Steam chat test")
    @Test
    void steamChatTest() {

        step("Login in Steam", this::loginTest);

        step("Press chat button", () -> {
            $("#global_header").$(".supernav_container").$(".menuitem", 3).click();
        });

        step("Create chat with user name", () -> {
            $(".createChatRoomButton").click();
            $(".nicknameInput").click();
            $(".nicknameInput").setValue(cfgs.login());
            $(".Primary").click();
        });

        step("Type а phrase into the chat window", () -> {
            $(".chatentry_chatTextarea_3e__5").click();
            $(".chatentry_chatTextarea_3e__5").setValue(chatPhrase).pressEnter();
        });

        step("Check the phrase in the chat history", () -> {
            $(".chatHistoryScroll").$(".LastMessageBlock").$(".messages_MsgWithAddons_lFLbk").$(".msgText")
                    .shouldHave(text(chatPhrase));
        });

        step("Delete chat", () -> {
            $(".SVGIcon_ChatSettings").click();
            $(".chatroomgroupsettings_LeaveButton_3OGY7").click();
            $(".Primary").click();
        });
    }
}
