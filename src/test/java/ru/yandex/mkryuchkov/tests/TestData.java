package ru.yandex.mkryuchkov.tests;


import org.junit.jupiter.api.Test;

public class TestData extends TestBase {

    SteamTest steamTest = new SteamTest();

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
                .inspectHomeTab();

    }
}
