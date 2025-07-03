package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests {
    @BeforeAll
    static void basicBrowserSettings() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

    }


    @Test
    void successfulFillFormTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        //Блок Name

        $("#firstName").setValue("Kostya");
        $("#lastName").setValue("Ivanov");

        //Блок e-mail

        $("#userEmail").setValue("kostya@ivanov.com");

        //Блок Gender

        $(byText("Male")).click();


        //Блок Mobile

        $("#userNumber").setValue("1234567893");

        //Блок Date of Birth

        $("#dateOfBirthInput").click();

        $(".react-datepicker__month-select").selectOption("March");

        $(".react-datepicker__year-select").selectOption("2005");

        $(".react-datepicker__day--025").click();

        //Блок Subjects

        $("#subjectsInput").setValue("Eng");

        $$(".subjects-auto-complete__option").findBy(text("English")).click();


        //Блок Hobbies

        $("#hobbiesWrapper").$(byText("Music")).click();

        // Блок загрузки фото
        $("#uploadPicture").uploadFromClasspath("Photo.jpeg");

        // Блок Address
        $("#currentAddress").setValue("123 Main St.");

        // Блок State and City

        // Штат и Город
        $("#state").click();

        $("#stateCity-wrapper").$(byText("Haryana")).click();

        $("#city").click();

        $("#stateCity-wrapper").$(byText("Karnal")).click();




        $("#submit").click();


        // Проверка текста в форме после заполнения


        $(".modal-dialog").should(appear);

        $(".table-responsive").shouldHave(text("Kostya Ivanov"));
        $(".table-responsive").shouldHave(text("kostya@ivanov.com"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("1234567893"));
        $(".table-responsive").shouldHave(text("25 March,2005"));
        $(".table-responsive").shouldHave(text("English"));
        $(".table-responsive").shouldHave(text("Music"));
        $(".table-responsive").shouldHave(text("Photo.jpeg"));
        $(".table-responsive").shouldHave(text("123 Main St."));
        $(".table-responsive").shouldHave(text("Haryana Karnal"));

        // Дополнительно проверяем общий успех отправки формы
        $(".modal-content").shouldHave(text("Thanks for submitting the form"));
    }
}
