package ru.yandex.mkryuchkov.tests;

import org.junit.jupiter.api.Test;

public class TestData extends TestBase {


    SteamTest steamTest = new SteamTest();
    @Test
    void test() {
        steamTest
                .openMainPage()
                .inspectMainPage()
                .inspectNavigationMenu()
                .inspectGutterBlock()
                .inspectMainContentBlock()
                .inspectSpecialOffers()
                .inspectCommunityRecommendation();

    }
}
