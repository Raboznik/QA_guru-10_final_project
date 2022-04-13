package ru.yandex.mkryuchkov.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TestData extends TestBase {

    private SelenideElement
            header = $("#global_header"),
            storeNavigation = $("#store_nav_area"),
            homePageGutter = $(".home_page_gutter"),
            popular = $("#home_maincap_v7"),
            spotlightCarousel = $("#spotlight_carousel"),
            communityRecomendation = $("#module_community_recommendations"),
            popularContent = $(".big_buttons").$(".button_container"),
            popularVrGames = $(".best_selling_vr_ctn").$(".home_page_content").$(".paging_capsules"),
            preview = $("#tab_preview_container"),
            liveStream = $("#live_streams_carousel"),
            upTo300rub = $(".specials_under10_content"),
            lowerLogin = $("#content_login"),
            footer = $("#footer_text");

    @Step("Open main page")
    public TestData openMainPage() {
        open(baseUrl);
        return this;
    }

    @Step("Inspect Header")
    public TestData inspectMainPage() {

        header.shouldBe(visible);

        header.$(".supernav_container").find(".menuitem", 0).shouldHave(href(baseUrl + "?snr=1_4_4__global-header"));
        header.$(".supernav_container").find(".menuitem", 1).shouldHave(href("https://steamcommunity.com/"));
        header.$(".supernav_container").find(".menuitem", 2).shouldHave(href(baseUrl + "about/?snr=1_4_4__global-header"));
        header.$(".supernav_container").find(".menuitem", 3).shouldHave(href("https://help.steampowered.com/en/"));

        return this;
    }

    @Step("Inspect search bar")
    public TestData inspectNavigationMenu() {

        storeNavigation.shouldBe(visible);

        storeNavigation.$("#store_nav_search_term").shouldBe(visible);

        return this;
    }

    @Step("Inspect blocks on the left side")
    public TestData inspectGutterBlock() {

        homePageGutter.shouldBe(visible).$$(".home_page_gutter_block").shouldHave(size(4));

        return this;
    }

    @Step("Inspect main content block")
    public TestData inspectMainContentBlock() {

        List<ElementsCollection> firstElement = new ArrayList<>();
        List<ElementsCollection> secondElement = new ArrayList<>();

        firstElement.add($$(".store_main_capsule").first(1));

        popular.lastChild().click();

        secondElement.add($$(".store_main_capsule").first(2));

        assertThat(firstElement).isNotEqualTo(secondElement);

        secondElement.clear();

        popular.$(".arrow", 0).click();

        secondElement.add($$(".store_main_capsule").first(1));

        assertThat(firstElement).isEqualTo(secondElement);

        return this;
    }

    @Step("Inspect special offers")
    public TestData inspectSpecialOffers() {

        List<ElementsCollection> firstElement = new ArrayList<>();
        List<ElementsCollection> secondElement = new ArrayList<>();

        spotlightCarousel.scrollTo();

        firstElement.add(spotlightCarousel.$$(".home_special_offers_group"));

        spotlightCarousel.lastChild().scrollTo().click();

        secondElement.add(spotlightCarousel.$$(".home_special_offers_group"));

        assertThat(firstElement).isNotEqualTo(secondElement.get(0));

        spotlightCarousel.$(".arrow", 0).click();

        secondElement.clear();

        secondElement.add(spotlightCarousel.$$(".home_special_offers_group"));

        assertThat(firstElement).isEqualTo(secondElement);

        return this;
    }

    @Step("Inspect community recommendation")
    public TestData inspectCommunityRecommendation() {

        List<ElementsCollection> firstElement = new ArrayList<>();
        List<ElementsCollection> secondElement = new ArrayList<>();

        communityRecomendation.scrollTo().shouldBe(visible);

        firstElement.add(communityRecomendation.$$(".focus").first(1));

        communityRecomendation.$(".home_page_content").$(".arrow", 1).click();

        secondElement.add(communityRecomendation.$$(".focus").first(2));

        assertThat(secondElement.get(0)).isNotEqualTo(firstElement);

        communityRecomendation.$(".home_page_content").$(".arrow", 0).click();

        secondElement.clear();

        secondElement.add(communityRecomendation.$$(".focus").first(1));

        assertThat(secondElement).isEqualTo(firstElement);

        return this;
    }

    @Step("Inspect popular content block")
    public TestData inspectPopularContent() {

        popularContent.$(".big_button", 0).shouldHave(href(baseUrl + "explore/new/?snr=1_4_4__146"));
        popularContent.$(".big_button", 1).shouldHave(href(baseUrl + "search/?specials=1&snr=1_4_4__146"));
        popularContent.$(".big_button", 2).shouldHave(href(baseUrl + "genre/Free to Play/?snr=1_4_4__146"));
        popularContent.$(".big_button", 3).shouldHave(href(baseUrl + "tag/browse/?snr=1_4_4__146"));

        return this;
    }

    @Step("Inspect popular VR games")
    public TestData inspectPopularVrGames() {

        popularVrGames.$(".carousel_items").scrollTo().shouldBe(visible);

        if (popularVrGames.$(".arrow", 1).exists()) {
            popularVrGames.$(".arrow", 1).click();
            popularVrGames.$(".carousel_items").shouldBe(visible);
            popularVrGames.$(".arrow", 0).click();
            popularVrGames.$(".carousel_items").shouldBe(visible);
        }

        return this;
    }

    @Step("Inspect home tab content block")
    public TestData inspectHomeTab() {

        $("#delayedimage_home_tabs_autoload_1").scrollTo();

        for (int i = 1; i < 11; i++) {
            $("#delayedimage_home_tabs_autoload_" + i).shouldBe(visible).hover();
            preview.shouldBe(visible);
        }

        $("#tab_topsellers_content_trigger").click();

        for (int i = 0; i < 10; i++) {
            $("#delayedimage_home_tabs_" + i).shouldBe(visible).hover();
            preview.shouldBe(visible);
        }

        $("#tab_upcoming_content_trigger").click();

        for (int i = 0; i < 10; i++) {
            $("#delayedimage_home_tabs_3" + i).shouldBe(visible).hover();
            preview.shouldBe(visible);
        }

        return this;
    }

    @Step("Inspect live stream block")
    public TestData inspectLiveStreamTab() {

        liveStream.$(".carousel_items").$(".focus").scrollTo().shouldBe(visible);

        return this;
    }

    @Step("Inspect cheap games tab")
    public TestData inspectCheapGamesTab() {

        upTo300rub.scrollTo().shouldBe(visible);

        List<ElementsCollection> firstElement = new ArrayList<>();

        List<ElementsCollection> secondElement = new ArrayList<>();

        firstElement.add(upTo300rub.$$(".focus").first(1));

        $(upTo300rub).$(".specials_under10").lastChild().click();

        secondElement.add(upTo300rub.$$(".focus").first(2));

        assertThat(firstElement).isNotEqualTo(secondElement);

        secondElement.clear();

        $(upTo300rub).$(".specials_under10").$(".arrow", 0).click();

        secondElement.add(upTo300rub.$$(".focus").first(1));

        assertThat(firstElement).isEqualTo(secondElement);

        return this;
    }

    @Step("Inspect Lower Login")
    public TestData inspectLowerLogin() {

        lowerLogin.scrollTo().shouldBe(visible)
                .$(".btn_green_white_innerfade").shouldHave(href(baseUrl + "login/?snr=1_4_4__more-content-login"));

        return this;
    }

    @Step("Inspect footer links")
    public TestData inspectFooter() {

        footer.scrollTo().shouldBe(visible);
        footer.find("div", 1).find("a", 0).shouldHave(href(baseUrl + "privacy_agreement/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 1).shouldHave(href(baseUrl + "legal/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 2).shouldHave(href(baseUrl + "subscriber_agreement/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 3).shouldHave(href(baseUrl + "steam_refunds/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 4).shouldHave(href(baseUrl + "account/cookiepreferences/?snr=1_44_44_"));

        return this;
    }
}
