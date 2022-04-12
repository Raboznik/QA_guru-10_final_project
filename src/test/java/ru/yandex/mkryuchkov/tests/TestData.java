package ru.yandex.mkryuchkov.tests;


import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import ru.yandex.mkryuchkov.config.Credentials;
import ru.yandex.mkryuchkov.config.CredentialsConfig;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class TestData extends TestBase {

    SteamTest steamTest = new SteamTest();

    @Step("Smoke test of Steam main page")
    @Test
    void steamMainPageTest() {

        steamTest
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
                .inspectUpTo300RubTab()
                .inspectLowerLogin()
                .inspectFooter();

    }

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
            $("#input_username").setValue(Credentials.cfgs.login());
        });

        step("Type password", () -> {
            $("#input_password").click();
            $("#input_password").setValue(Credentials.cfgs.password());
        });

        step("Click login", () -> {
            $("#login_btn_signin").$(".login_btn").click();
        });

        step("Verify login", () -> {
            $("#account_pulldown").shouldHave(text(Credentials.cfgs.login()));
        });
    }

    @Test
    void steamChatTest() {

        step("Login in Steam", this::loginTest);

        step("Press chat button", () -> {
                    $("#global_header").$(".supernav_container").$(".menuitem", 3).click();
                });

        step("Create chat with user name", () -> {
                    $(".createChatRoomButton").click();
                    $(".nicknameInput").click();
                    $(".nicknameInput").setValue(Credentials.cfgs.login());
                    $(".Primary").click();
                });

        step("Type а phrase into the chat window", () -> {
                    $(".chatentry_chatTextarea_3e__5").click();
                    $(".chatentry_chatTextarea_3e__5").setValue("Hello World!").pressEnter();
                });

        step("check the phrase in the chat history", () -> {
                    $(".chatHistoryScroll").$(".LastMessageBlock").$(".messages_MsgWithAddons_lFLbk").$(".msgText").shouldHave(text("Hello World!"));
                });

        step("Delete chat", () -> {
            $(".SVGIcon_ChatSettings").click();
            $(".chatroomgroupsettings_LeaveButton_3OGY7").click();
            $(".Primary").click();
        });
    }
}
