package ru.yandex.mkryuchkov.tests;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SteamMainPageTest extends TestBase {

    private PageObjects steamMainPage = new PageObjects();
    List<SelenideElement> comparisonList = new ArrayList<>();
    private String chatPhrase = "Hello World!";

    static Stream<Arguments> argumentsForParametrisedTests() {
        return Stream.of(
                Arguments.of($("#home_maincap_v7"), "featuredAndRecommended", 1, 2),
                Arguments.of($("#spotlight_carousel"), "spotlightCarousel", 0, 4),
                Arguments.of($("#module_community_recommendations"), "communityRecommendation", 1, 2),
                Arguments.of($(".specials_under10_content"), "cheapGames", 0, 4)
        );
    }

    @DisplayName("A parameterized test for tabs, that have switch arrows")
    @Description("Check special offers tabs that have arrows." +
            " First of all add visible element to list, then press right arrow button." +
            " After it add another visible element to list, then compare it. They should not be equal." +
            "Then press left arrow, add third visible element to list. First and third element should be equal")
    @MethodSource("argumentsForParametrisedTests")
    @ParameterizedTest(name = "Test elements : {index}: {1}")
    void checkGroupOfElementsThatHaveSwitchArrows(SelenideElement element, String name, int firstElementForCompare, int secondElementForCompare) {
        step("Sign in", this::loginTest);
        step("Add first element to compare list", () -> {
            element.scrollTo().shouldBe(visible);
            comparisonList.add(element.$(".app_impression_tracked", firstElementForCompare).shouldBe(visible));
        });
        step("Click right arrow", () ->
                element.$(".home_page_content .arrow", 1).click());
        step("Add second element to compare list", () ->
                comparisonList.add(element.$(".app_impression_tracked", secondElementForCompare).shouldBe(visible)));
        step("Compare first and second elements", () ->
                assertNotEquals(comparisonList.get(0), comparisonList.get(1)));
        step("Click left arrow", () ->
                element.$(".home_page_content .arrow").click());
        step("Add third element to compare list", () ->
                comparisonList.add(element.$(".app_impression_tracked", firstElementForCompare)));
        step("Compare first and third elements", () ->
                assertEquals(comparisonList.get(0), comparisonList.get(2)));
    }

    @DisplayName("Smoke test of Steam main page")
    @Test
    void steamMainPageSmokeTestWithPageObjects() {
        steamMainPage
                .openMainPage()
                .searchHrefsOfHeaderMenu()
                .searchFieldShouldBeVisible()
                .checkGutterBlockOnTheLeftSide()
                .checkPopularContentTabHrefs()
                .popularVrGamesTabShouldBeVisible()
                .contentInTabNewReleasesShouldBeVisible()
                .contentInTabTopSellersShouldBeVisible()
                .contentInTabUpcomingContentShouldBeVisible()
                .contentInTabSpecialsContentShouldBeVisible()
                .liveStreamTabShouldBeVisible()
                .checkBigButtonLoginHref()
                .checkFootersHrefs();
    }

    @DisplayName("Search game test")
    @Test
    void searchGameTest() {
        step("Open main page", () ->
                open(baseUrl));
        step("Click on search bar", () ->
                $("#store_nav_search_term").click());
        step("Type searched game", () ->
                $("#store_nav_search_term").setValue("Dota 2").pressEnter());
        step("Inspect search result list", () ->
                $("#search_resultsRows").shouldBe(visible).$("a")
                        .shouldHave(href(baseUrl + "app/570/Dota_2/?snr=1_7_7_151_150_1")));
    }

    @DisplayName("Login test")
    @Test
    void loginTest() {
        step("Open main page", () ->
                open(baseUrl));
        step("Open login page", () ->
                $("#global_action_menu .global_action_link").click());
        step("Type login", () -> {
            $("#input_username").click();
            $("#input_username").setValue(cfgs.login());
        });
        step("Type password", () -> {
            $("#input_password").click();
            $("#input_password").setValue(cfgs.password());
        });
        step("Click login", () ->
                $("#login_btn_signin .login_btn").click());
        step("Verify login", () ->
                $("#account_pulldown").shouldHave(text(cfgs.login())));
    }

    @DisplayName("Steam chat test")
    @Test
    void steamChatTest() {
        step("Login in Steam", this::loginTest);
        step("Press chat button", () ->
                $("#global_header .supernav_container .menuitem", 3).click());
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
        step("Check the phrase in the chat history", () ->
                $(".chatHistoryScroll .LastMessageBlock .messages_MsgWithAddons_lFLbk .msgText")
                        .shouldHave(text(chatPhrase)));
        step("Delete chat", () -> {
            $(".SVGIcon_ChatSettings").click();
            $(".chatroomgroupsettings_LeaveButton_3OGY7").click();
            $(".Primary").click();
        });
    }
}
