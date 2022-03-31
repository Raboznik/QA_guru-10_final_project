package ru.yandex.mkryuchkov.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

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

//    public SteamTest inspectMainContentBlock() {
//
//        List<ElementsCollection> firstElement = new ArrayList<>();
//        List<ElementsCollection> secondElement = new ArrayList<>();
//        List<ElementsCollection> thirdElement = new ArrayList<>();
//
//        firstElement.add($$(".store_main_capsule").filterBy(visible));
//        sleep(50);
//
//        $x("//*[@id=\"home_maincap_v7\"]/div[4]").click();
//        sleep(100);
//
//        secondElement.add($$(".store_main_capsule").filterBy(visible));
//
//        assertThat(firstElement).isNotEqualTo(secondElement);
//
//        $x("//*[@id=\"home_maincap_v7\"]/div[3]").click();
//        sleep(100);
//
//        thirdElement.add($$(".store_main_capsule").filterBy(visible));
//
//        assertThat(firstElement).isEqualTo(thirdElement);
//
//        return this;
//    }

    public SteamTest inspectSpecialOffers() {

        List<ElementsCollection> firstElement = new ArrayList<>();
        List<ElementsCollection> firstElement4 = new ArrayList<>();
        List<SelenideElement> firstElement2 = new ArrayList<>();
        List<SelenideElement> firstElement3 = new ArrayList<>();
        sleep(300);
        spotlightCarousel.scrollTo();
        sleep(500);
//        firstElement2.add($(".home_special_offers_group").find("home_area_spotlight"));
        firstElement.add(spotlightCarousel.$$(".home_special_offers_group").filterBy(visible));
//        firstElement2.add(spotlightCarousel.$(".responsive_scroll_snap_start app_impression_tracked").lastChild());

//        firstElement2.add(spotlightCarousel);
//        firstElement2.add($x("//*[@id=\"spotlight_carousel\"]/div[1]/div[1]/div[1]"));
//        System.out.println(firstElement2.size());
//        for (SelenideElement e:firstElement2) {
//            System.out.println(e);
//
//        }
        sleep(500);
        for (ElementsCollection e:firstElement) {
            System.out.println(e);

        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~");

        $x("//*[@id=\"spotlight_carousel\"]/div[4]").scrollTo().click();
        sleep(500);
//        firstElement3.add($(".home_special_offers_group").find("home_area_spotlight"));
//            firstElement3.add(spotlightCarousel.$(".responsive_scroll_snap_start app_impression_tracked").lastChild());
        firstElement4.add(spotlightCarousel.$$(".home_special_offers_group").filterBy(visible));
//        firstElement3.add(spotlightCarousel);
//        firstElement3.add($x("//*[@id=\"spotlight_carousel\"]/div[1]/div[2]/div[1]"));
        for (ElementsCollection e:firstElement4) {
            System.out.println(e);

        }
        assertThat(firstElement3).isNotEqualTo(firstElement2);

        $x("//*[@id=\"spotlight_carousel\"]/div[3]").click();

        sleep(100);
        firstElement4.clear();
        firstElement4.add(spotlightCarousel.$$(".carousel_items responsive_scroll_snap_ctn").filterBy(visible).filterBy(cssClass("focus")));

        for (ElementsCollection e:firstElement4) {
            System.out.println(e);

        }

        assertThat(firstElement).isEqualTo(firstElement4);

        return this;
    }



}
