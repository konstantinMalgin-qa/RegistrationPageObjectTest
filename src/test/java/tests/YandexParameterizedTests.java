package tests;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Тесты на отели Яндекс")
@Tag("SMOKE")
public class YandexParameterizedTests {
    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";

        open("https://travel.yandex.ru/hotels");
    }

    @ValueSource(strings = {
            "Москва", "Казань"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдаваться не пустой список карточек")
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    @Tag("BLOCKER")
    void successfulSearchHotelsAtCityTest(String searchQuery) {
        $(".lstI9").click();
        $(".input_center").setValue(searchQuery);

        $(".vap86").click();
        SelenideElement dateElement = $("[data-qa='calendar-day-2025-08-11']");
        dateElement.scrollTo();
        dateElement.click();
        SelenideElement dateSecondElement = $("[data-qa='calendar-day-2025-08-12']");
        dateSecondElement.scrollTo();
        dateSecondElement.click();
        $(".EWeI5").click();
        $(".ReS7e")
                .shouldHave(text("Отели и гостиницы"));
        $$(".L8lvE")
                .shouldBe(sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "Москва, .ymaps-2-1-79-inner-panes",
            "Казань, .ymaps-2-1-79-inner-panes"
    })
    @CsvFileSource(resources = "/test_data/successfulSearchHotelsAtCityTest.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} должна отображаться карта {1}")
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    @Tag("BLOCKER")
    void searchMapTest(String searchQuery, String visibleMap) {
        $(".lstI9").click();
        $(".input_center").setValue(searchQuery);

        $(".vap86").click();
        SelenideElement dateElement = $("[data-qa='calendar-day-2025-08-11']");
        dateElement.scrollTo();
        dateElement.click();
        SelenideElement dateSecondElement = $("[data-qa='calendar-day-2025-08-12']");
        dateSecondElement.scrollTo();
        dateSecondElement.click();
        $(".EWeI5").click();
        $(".ReS7e")
                .shouldHave(text("Отели и гостиницы"));
        $$(".L8lvE")
                .shouldBe(sizeGreaterThan(0));
        $(visibleMap).shouldBe(visible);

    }

}

