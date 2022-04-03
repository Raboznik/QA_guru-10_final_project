package ru.yandex.mkryuchkov.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class SteamTest extends TestBase {

    private SelenideElement
            header = $("#global_header"),
            storeNavigation = $("#store_nav_area"),
            homePageGutter = $(".home_page_gutter"),                        // ?
            homePageContent = $("#home_maincap_v7"),
            spotlightCarousel = $("#spotlight_carousel"),
            communityRecomendation = $("#module_community_recommendations"),
            popularSections = $(".big_buttons home_page_content"),                        // ?
            popularVrGames = $(".home_ctn best_selling_vr_ctn"),
            homeTabContent = $(".home_leftcol home_tab_col"),
            liveStream = $("#live_streams_carousel"),
            upTo300rub = $(".specials_under10_content"),
            updatesAndDiscount = $(".marketingmessage_container"),
            lowerLogin = $("#content_login"),
            footer = $("#footer");


    public SteamTest openMainPage() {
        open(baseUrl);
        return this;
    }

    public SteamTest inspectMainPage() {

        header.shouldBe(visible);

        $x("//*[@id=\"global_header\"]/div/div[2]/a[1]").shouldHave(href(baseUrl + "?snr=1_4_4__global-header"));
        $x("//*[@id=\"global_header\"]/div/div[2]/a[2]").shouldHave(href("https://steamcommunity.com/"));
        $x("//*[@id=\"global_header\"]/div/div[2]/a[3]").shouldHave(href(baseUrl + "about/?snr=1_4_4__global-header"));
        $x("//*[@id=\"global_header\"]/div/div[2]/a[4]").shouldHave(href("https://help.steampowered.com/ru/"));

        return this;
    }

    public SteamTest inspectNavigationMenu() {

        storeNavigation.shouldBe(visible);
        storeNavigation.$("#store_nav_search_term").shouldBe(visible); //проверяю строку поиска

        return this;
    }

    public SteamTest inspectGutterBlock() {
        homePageGutter.shouldBe(visible).$$(".home_page_gutter_block").shouldHave(size(4)); //проверяю, что блоков слева 4

        return this;
    }

    public SteamTest inspectMainContentBlock() {

        List<ElementsCollection> firstElement = new ArrayList<>();
        List<ElementsCollection> secondElement = new ArrayList<>();

//        firstElement.add($$(".store_main_capsule").filterBy(visible));
//
//        $x("//*[@id=\"home_maincap_v7\"]/div[4]").click();
//
//        secondElement.add($$(".store_main_capsule").filterBy(visible));
//
//        assertThat(firstElement).isNotEqualTo(secondElement);
//        secondElement.clear();
//
//        $x("//*[@id=\"home_maincap_v7\"]/div[3]").click();
//        sleep(100);
//
//        secondElement.add($$(".store_main_capsule").filterBy(visible));
//
//        assertThat(firstElement).isEqualTo(secondElement);

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
//        popularSections.scrollTo().shouldBe(visible);
        $x("//*[@id=\"responsive_page_template_content\"]/div[1]/div[2]/div[14]/div/div/a[1]").scrollTo().shouldHave(text("Новинки")).shouldBe(visible);
//        popularSections.$(".big_button").scrollTo().shouldHave(text("Новинки")).shouldBe(visible);
        return this;
    }

    public SteamTest inspectPopularVrGames() {
        List<SelenideElement> firstElement = new ArrayList<>();
        List<SelenideElement> secondElement = new ArrayList<>();

        firstElement.add($x("//*[@id=\"responsive_page_template_content\"]" +
                "/div[1]/div[2]/div[18]/div/div/div[1]/div[1]/a[1]/div[1]"));

        $x("//*[@id=\"responsive_page_template_content\"]" +
                "/div[1]/div[2]/div[18]/div/div/div[4]").scrollTo().click();

        secondElement.add($x("//*[@id=\"responsive_page_template_content\"]" +
                "/div[1]/div[2]/div[18]/div/div/div[1]/div[2]/a[1]/div[1]"));

        assertThat(secondElement.get(0)).isNotEqualTo(firstElement);

        $x("//*[@id=\"responsive_page_template_content\"]" +
                "/div[1]/div[2]/div[18]/div/div/div[3]").click();

        secondElement.clear();

        secondElement.add($x("//*[@id=\"responsive_page_template_content\"]" +
                "/div[1]/div[2]/div[18]/div/div/div[1]/div[1]/a[1]/div[1]"));

        assertThat(secondElement).isEqualTo(firstElement);

        return this;
    }

    public SteamTest inspectHomeTab() {

        List<ElementsCollection> firstElement = new ArrayList<>();

        firstElement.add($$(".tab_item_cap").filterBy(visible));

        for (int i = 0; i < firstElement.size(); i++) {
            firstElement.remove(firstElement.get(i));

        }

//        for (ElementsCollection e: firstElement) {
//            e.fhover();
//
//        }

        for (ElementsCollection e: firstElement) {
            System.out.println(e);

        }





        return this;
    }

}
