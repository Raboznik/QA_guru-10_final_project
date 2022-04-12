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

public class SteamTest extends TestBase {

    private SelenideElement
            header = $("#global_header"),
            storeNavigation = $("#store_nav_area"),
            homePageGutter = $(".home_page_gutter"),
            spotlightCarousel = $("#spotlight_carousel"),
            communityRecomendation = $("#module_community_recommendations"),
            liveStream = $("#live_streams_carousel"),
            upTo300rub = $(".specials_under10_content"),
            lowerLogin = $("#content_login"),
            footer = $("#footer_text");

    @Step("Open main page")
    public SteamTest openMainPage() {
        open(baseUrl);
        return this;
    }

    @Step("Inspect Header")
    public SteamTest inspectMainPage() {

        header.shouldBe(visible);

        header.$(".supernav_container").find(".menuitem", 0).shouldHave(href(baseUrl + "?snr=1_4_4__global-header"));
        header.$(".supernav_container").find(".menuitem", 1).shouldHave(href("https://steamcommunity.com/"));
        header.$(".supernav_container").find(".menuitem", 2).shouldHave(href(baseUrl + "about/?snr=1_4_4__global-header"));
        header.$(".supernav_container").find(".menuitem", 3).shouldHave(href("https://help.steampowered.com/en/"));

        return this;
    }

    @Step("Inspect search bar")
    public SteamTest inspectNavigationMenu() {

        storeNavigation.shouldBe(visible);

        storeNavigation.$("#store_nav_search_term").shouldBe(visible);

        return this;
    }

    public SteamTest inspectGutterBlock() {

        homePageGutter.shouldBe(visible).$$(".home_page_gutter_block").shouldHave(size(4));

        return this;
    }

    public SteamTest inspectMainContentBlock() {

        List<ElementsCollection> firstElement = new ArrayList<>();
        List<ElementsCollection> secondElement = new ArrayList<>();

        firstElement.add($$(".store_main_capsule").first(1));

        $x("//*[@id=\"home_maincap_v7\"]/div[4]").click();

        secondElement.add($$(".store_main_capsule").first(2));

        assertThat(firstElement).isNotEqualTo(secondElement);

        secondElement.clear();

        $x("//*[@id=\"home_maincap_v7\"]/div[3]").click();

        secondElement.add($$(".store_main_capsule").first(1));

        assertThat(firstElement).isEqualTo(secondElement);

        return this;
    }

    public SteamTest inspectSpecialOffers() {

        List<ElementsCollection> firstElement = new ArrayList<>();
        List<ElementsCollection> secondElement = new ArrayList<>();

        spotlightCarousel.scrollTo();

        firstElement.add(spotlightCarousel.$$(".home_special_offers_group"));

        $x("//*[@id=\"spotlight_carousel\"]/div[4]").scrollTo().click();

        secondElement.add(spotlightCarousel.$$(".home_special_offers_group"));

        assertThat(firstElement).isNotEqualTo(secondElement.get(0));       // get0?

        $x("//*[@id=\"spotlight_carousel\"]/div[3]").click();

        secondElement.clear();

        secondElement.add(spotlightCarousel.$$(".home_special_offers_group"));

        assertThat(firstElement).isEqualTo(secondElement);

        return this;
    }

    public SteamTest inspectCommunityRecommendation() {

        List<ElementsCollection> firstElement = new ArrayList<>();
        List<ElementsCollection> secondElement = new ArrayList<>();

        communityRecomendation.scrollTo().shouldBe(visible);

        firstElement.add(communityRecomendation.$$(".focus").first(1));

        $x("//*[@id=\"module_community_recommendations\"]/div/div/div[5]").click();

        secondElement.add(communityRecomendation.$$(".focus").first(2));

        assertThat(secondElement.get(0)).isNotEqualTo(firstElement);

        $x("//*[@id=\"module_community_recommendations\"]/div/div/div[4]").click();

        secondElement.clear();

        secondElement.add(communityRecomendation.$$(".focus").first(1));

        assertThat(secondElement).isEqualTo(firstElement);

        return this;
    }

    public SteamTest inspectPopularContent() {

        $x("//*[@id=\"responsive_page_template_content\"]/div[1]/div[2]/div[11]/div/div/a[1]")
                .scrollTo().shouldHave(text("Новинки")).shouldBe(visible);

        return this;
    }

    public SteamTest inspectPopularVrGames() {

        $x("//*[@id=\"responsive_page_template_content\"]/div[1]/div[2]/div[18]/div/div").shouldBe(visible);

        return this;
    }

    public SteamTest inspectHomeTab() {

        $("#delayedimage_home_tabs_autoload_0").scrollTo();

        for (int i = 0; i < 10; i++) {
            $("#delayedimage_home_tabs_autoload_" + i).shouldBe(visible).hover();
            $("#tab_preview_container").shouldBe(visible);
        }

        $("#tab_topsellers_content_trigger").click();

        for (int i = 0; i < 10; i++) {
            $("#delayedimage_home_tabs_" + i).shouldBe(visible).hover();
            $("#tab_preview_container").shouldBe(visible);
        }

        $("#tab_upcoming_content_trigger").click();

        for (int i = 0; i < 10; i++) {
            $("#delayedimage_home_tabs_3" + i).shouldBe(visible).hover();
            $("#tab_preview_container").shouldBe(visible);
        }

        return this;
    }

    public SteamTest inspectLiveStreamTab() {

        liveStream.scrollTo().shouldBe(visible);

        return this;
    }

    public SteamTest inspectUpTo300RubTab() {

        upTo300rub.scrollTo().shouldBe(visible);

        List<ElementsCollection> firstElement = new ArrayList<>();

        List<ElementsCollection> secondElement = new ArrayList<>();

        firstElement.add(upTo300rub.$$(".focus").first(1));

        $x("//*[@id=\"responsive_page_template_content\"]/div[1]/div[2]/div[20]/div/div/div[4]").click();

        secondElement.add(upTo300rub.$$(".focus").first(2));

        assertThat(firstElement).isNotEqualTo(secondElement);

        secondElement.clear();

        $x("//*[@id=\"responsive_page_template_content\"]/div[1]/div[2]/div[20]/div/div/div[3]").click();

        secondElement.add(upTo300rub.$$(".focus").first(1));

        assertThat(firstElement).isEqualTo(secondElement);

        return this;
    }


    public SteamTest inspectLowerLogin() {

        lowerLogin.scrollTo().shouldBe(visible)
                .$(".btn_green_white_innerfade").shouldHave(href(baseUrl + "login/?snr=1_4_4__more-content-login"));

        return this;
    }

    public SteamTest inspectFooter() {

        footer.scrollTo().shouldBe(visible);
        footer.find("div", 1).find("a", 0).shouldHave(href(baseUrl + "privacy_agreement/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 1).shouldHave(href(baseUrl + "legal/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 2).shouldHave(href(baseUrl + "subscriber_agreement/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 3).shouldHave(href(baseUrl + "steam_refunds/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 4).shouldHave(href(baseUrl + "account/cookiepreferences/?snr=1_44_44_"));

        return this;
    }


}
