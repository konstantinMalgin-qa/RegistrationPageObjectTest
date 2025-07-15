package tests;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Тесты на отели Aviasales")
@Tag("SMOKE")
public class AviasalesParameterizedTests {
    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";

        open("https://www.aviasales.ru/hotels");
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
        $("[data-test-id='hotel-autocomplete-input']").setValue(searchQuery);
        $(byText(searchQuery)).shouldBe(visible);
       // sleep(2000);

        $("[data-test-id='start-date-field']").click();
        SelenideElement dateElement = $("[data-test-id='date-17.07.2025']");
        dateElement.scrollTo();
        dateElement.click();
        SelenideElement dateSecondElement = $("[data-test-id='date-18.07.2025']");
        dateSecondElement.scrollTo();
        dateSecondElement.click();
        $("[data-test-id='form-submit']").click();
        sleep(7000);
        $("[data-selene-widget='hotels_search_page']")
                .shouldHave(text(" на Авиасейлс"));
        sleep(5000);
        $$("[data-test-id='hotel-preview']")
                .shouldBe(sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "Москва, .mapboxgl-canvas",
            "Казань, .mapboxgl-canvas"
    })
    @CsvFileSource(resources = "/test_data/successfulSearchHotelsAtCityTest.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} должна отображаться карта {1}")
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    @Tag("BLOCKER")

    void searchMapTest(String searchQuery, String visibleMap) {
        $("[data-test-id='hotel-autocomplete-input']").setValue(searchQuery);
        $(byText(searchQuery)).shouldBe(visible);
        // sleep(2000);

        $("[data-test-id='start-date-field']").click();
        SelenideElement dateElement = $("[data-test-id='date-17.07.2025']");
        dateElement.scrollTo();
        dateElement.click();
        SelenideElement dateSecondElement = $("[data-test-id='date-18.07.2025']");
        dateSecondElement.scrollTo();
        dateSecondElement.click();
        $("[data-test-id='form-submit']").click();
        sleep(7000);
        $("[data-selene-widget='hotels_search_page']")
                .shouldHave(text(" на Авиасейлс"));
        sleep(5000);
        $$("[data-test-id='hotel-preview']")
                .shouldBe(sizeGreaterThan(0));
        $(visibleMap).shouldBe(visible);

    }
}

