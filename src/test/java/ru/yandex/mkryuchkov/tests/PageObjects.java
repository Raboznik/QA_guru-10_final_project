package ru.yandex.mkryuchkov.tests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.href;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class PageObjects extends TestBase {

    private SelenideElement
            header = $("#global_header"),
            storeNavigation = $("#store_nav_area"),
            homePageGutter = $(".home_page_gutter"),
            popularContent = $(".big_buttons .button_container"),
            popularVrGames = $(".best_selling_vr_ctn .home_page_content .paging_capsules"),
            preview = $("#tab_preview_container"),
            liveStream = $("#live_streams_carousel"),
            lowerLogin = $("#content_login"),
            footer = $("#footer_text");

    @Step("Open main page")
    public PageObjects openMainPage() {
        open(baseUrl);
        return this;
    }

    @Step("Inspect Headers hrefs")
    public PageObjects searchHrefsOfHeaderMenu() {
        header.shouldBe(visible);

        header.$(".supernav_container").find(".menuitem", 0).shouldHave(href(baseUrl + "?snr=1_4_4__global-header"));
        header.$(".supernav_container").find(".menuitem", 1).shouldHave(href("https://steamcommunity.com/"));
        header.$(".supernav_container").find(".menuitem", 2).shouldHave(href(baseUrl + "about/?snr=1_4_4__global-header"));
        header.$(".supernav_container").find(".menuitem", 3).shouldHave(href("https://help.steampowered.com/en/"));

        return this;
    }

    @Step("Inspect search bar")
    public PageObjects searchFieldShouldBeVisible() {
        storeNavigation.shouldBe(visible);
        storeNavigation.$("#store_nav_search_term").shouldBe(visible);

        return this;
    }

    @Step("Inspect blocks on the left side")
    public PageObjects checkGutterBlockOnTheLeftSide() {
        homePageGutter.shouldBe(visible).$$(".home_page_gutter_block").shouldHave(size(4));

        return this;
    }

    @Step("Inspect popular content block")
    public PageObjects checkPopularContentTabHrefs() {
        popularContent.$(".big_button", 0).shouldHave(href(baseUrl + "explore/new/?snr=1_4_4__146"));
        popularContent.$(".big_button", 1).shouldHave(href(baseUrl + "search/?specials=1&snr=1_4_4__146"));
        popularContent.$(".big_button", 2).shouldHave(href(baseUrl + "genre/Free to Play/?snr=1_4_4__146"));
        popularContent.$(".big_button", 3).shouldHave(href(baseUrl + "tag/browse/?snr=1_4_4__146"));

        return this;
    }

    @Step("Inspect popular VR games")
    public PageObjects popularVrGamesTabShouldBeVisible() {
        popularVrGames.$(".carousel_items").scrollTo().shouldBe(visible);

        return this;
    }

    @Step("Inspect home tab content block (newreleases_content)")
    public PageObjects contentInTabNewReleasesShouldBeVisible() {
        List<SelenideElement> list = new ArrayList<>();

        $("#tab_newreleases_content").scrollTo();

        for (int i = 0; i < 10; i++) {
            list.add($("#tab_newreleases_content").find(".tab_item", i).shouldBe(visible));
        }

        for (SelenideElement e : list) {

            e.shouldBe(visible).hover();

            preview.shouldBe(visible);
        }
        return this;
    }

    @Step("Inspect home tab content block (topsellers_content)")
    public PageObjects contentInTabTopSellersShouldBeVisible() {
        List<SelenideElement> list = new ArrayList<>();

        $("#tab_topsellers_content_trigger").click();

        for (int i = 0; i < 10; i++) {
            list.add($("#tab_topsellers_content").find(".tab_item", i).shouldBe(visible));
        }

        for (SelenideElement e : list) {

            e.shouldBe(visible).hover();

            preview.shouldBe(visible);
        }
        return this;
    }

    @Step("Inspect home tab content block (upcoming_content)")
    public PageObjects contentInTabUpcomingContentShouldBeVisible() {
        List<SelenideElement> list = new ArrayList<>();

        $("#tab_upcoming_content_trigger").click();

        for (int i = 0; i < 10; i++) {
            list.add($("#tab_upcoming_content").find(".tab_item", i).shouldBe(visible));
        }

        for (SelenideElement e : list) {

            e.shouldBe(visible).hover();

            preview.shouldBe(visible);
        }

        return this;
    }

    @Step("Inspect home tab content block (specials_content)")
    public PageObjects contentInTabSpecialsContentShouldBeVisible() {
        List<SelenideElement> list = new ArrayList<>();

        $("#tab_specials_content_trigger").click();

        for (int i = 0; i < 10; i++) {
            list.add($("#tab_specials_content").find(".tab_item", i).shouldBe(visible));
        }

        for (SelenideElement e : list) {

            e.shouldBe(visible).hover();

            preview.shouldBe(visible);
        }
        return this;
    }

    @Step("Inspect live stream block")
    public PageObjects liveStreamTabShouldBeVisible() {
        liveStream.$(".carousel_items .focus").scrollTo().shouldBe(visible);

        return this;
    }

    @Step("Inspect Lower Login")
    public PageObjects checkBigButtonLoginHref() {
        lowerLogin.scrollTo().shouldBe(visible)
                .$(".btn_green_white_innerfade").shouldHave(href(baseUrl + "login/?snr=1_4_4__more-content-login"));

        return this;
    }

    @Step("Inspect footer links")
    public PageObjects checkFootersHrefs() {
        footer.scrollTo().shouldBe(visible);
        footer.find("div", 1).find("a", 0).shouldHave(href(baseUrl + "privacy_agreement/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 1).shouldHave(href(baseUrl + "legal/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 2).shouldHave(href(baseUrl + "subscriber_agreement/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 3).shouldHave(href(baseUrl + "steam_refunds/?snr=1_44_44_"));
        footer.find("div", 1).find("a", 4).shouldHave(href(baseUrl + "account/cookiepreferences/?snr=1_44_44_"));

        return this;
    }
}
