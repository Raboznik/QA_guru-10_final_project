package ru.yandex.mkryuchkov.tests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.yandex.mkryuchkov.pages.components.FooterMenuComponent;
import ru.yandex.mkryuchkov.pages.components.NavigationMenuComponent;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.href;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class MainPage extends TestBase {

    public NavigationMenuComponent navigationMenuComponent = new NavigationMenuComponent();
    public FooterMenuComponent footerMenuComponent = new FooterMenuComponent();

    private SelenideElement
            storeNavigation = $("#store_nav_area"),
            homePageGutter = $(".home_page_gutter"),
            popularContent = $(".big_buttons .button_container"),
            popularVrGames = $(".best_selling_vr_ctn .home_page_content .paging_capsules"),
            preview = $("#tab_preview_container"),
            liveStream = $("#live_streams_carousel"),
            lowerLogin = $("#content_login");


    @Step("Open steam main page")
    public MainPage openPage() {
        open("");
        return this;
    }

    @Step("Search game bar should be visible")
    public MainPage searchFieldShouldBeVisible() {
        storeNavigation.shouldBe(visible);
        storeNavigation.$("#store_nav_search_term").shouldBe(visible);

        return this;
    }

    @Step("Check gutter block on the left side")
    public MainPage checkGutterBlock() {
        homePageGutter.shouldBe(visible).$$(".home_page_gutter_block").shouldHave(size(4));

        return this;
    }

    @Step("Check popular content block links")
    public MainPage checkPopularContentTabHrefs() {
        popularContent.$(".big_button", 0).shouldHave(href(baseUrl + "explore/new/?snr=1_4_4__146"));
        popularContent.$(".big_button", 1).shouldHave(href(baseUrl + "search/?specials=1&snr=1_4_4__146"));
        popularContent.$(".big_button", 2).shouldHave(href(baseUrl + "genre/Free to Play/?snr=1_4_4__146"));
        popularContent.$(".big_button", 3).shouldHave(href(baseUrl + "tag/browse/?snr=1_4_4__146"));

        return this;
    }

    @Step("Popular VR games should be visible")
    public MainPage popularVrGamesTabShouldBeVisible() {
        popularVrGames.$(".carousel_items").scrollTo().shouldBe(visible);

        return this;
    }

    @Step("Home tab content block (newreleases_content) all elements should be visible")
    public MainPage contentInTabNewReleasesShouldBeVisible() {
        List<SelenideElement> listForScrollElements = new ArrayList<>();

        $("#tab_newreleases_content").scrollTo();

        for (int i = 0; i < 10; i++) {
            listForScrollElements.add($("#tab_newreleases_content").find(".tab_item", i).shouldBe(visible));
        }
        for (SelenideElement e : listForScrollElements) {
            e.shouldBe(visible).hover();
            preview.shouldBe(visible);
        }
        return this;
    }

    @Step("Inspect home tab content block (topsellers_content) all elements should be visible")
    public MainPage contentInTabTopSellersShouldBeVisible() {
        List<SelenideElement> listForScrollElements = new ArrayList<>();

        $("#tab_topsellers_content_trigger").click();

        for (int i = 0; i < 10; i++) {
            listForScrollElements.add($("#tab_topsellers_content").find(".tab_item", i).shouldBe(visible));
        }
        for (SelenideElement e : listForScrollElements) {
            e.shouldBe(visible).hover();
            preview.shouldBe(visible);
        }
        return this;
    }

    @Step("Inspect home tab content block (upcoming_content) all elements should be visible")
    public MainPage contentInTabUpcomingContentShouldBeVisible() {
        List<SelenideElement> listForScrollElements = new ArrayList<>();

        $("#tab_upcoming_content_trigger").click();

        for (int i = 0; i < 10; i++) {
            listForScrollElements.add($("#tab_upcoming_content").find(".tab_item", i).shouldBe(visible));
        }
        for (SelenideElement e : listForScrollElements) {
            e.shouldBe(visible).hover();
            preview.shouldBe(visible);
        }
        return this;
    }

    @Step("Inspect home tab content block (specials_content) all elements should be visible")
    public MainPage contentInTabSpecialsContentShouldBeVisible() {
        List<SelenideElement> listForScrollElements = new ArrayList<>();

        $("#tab_specials_content_trigger").click();

        for (int i = 0; i < 10; i++) {
            listForScrollElements.add($("#tab_specials_content").find(".tab_item", i).shouldBe(visible));
        }
        for (SelenideElement e : listForScrollElements) {
            e.shouldBe(visible).hover();
            preview.shouldBe(visible);
        }
        return this;
    }

    @Step("Live stream block should be visible")
    public MainPage liveStreamTabShouldBeVisible() {
        liveStream.$(".carousel_items .focus").scrollTo().shouldBe(visible);

        return this;
    }

    @Step("Check lower Login link")
    public MainPage checkBigButtonLoginHref() {
        lowerLogin.scrollTo().shouldBe(visible)
                .$(".btn_green_white_innerfade").shouldHave(href(baseUrl + "login/?snr=1_4_4__more-content-login"));

        return this;
    }
}
