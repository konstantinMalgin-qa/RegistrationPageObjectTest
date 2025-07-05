package tests;


import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationWithFakerTests extends TestBase{



    @Test
    void successfulFillFormTest() {

        Faker faker = new Faker(new Locale("en-GB"));


        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail = faker.internet().emailAddress();
        String streetAddress = faker.address().streetAddress();

        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        //Блок Name


        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);

        //Блок e-mail

        $("#userEmail").setValue(userEmail);

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
        $("#currentAddress").setValue(streetAddress);

        // Блок State and City

        // Штат и Город
        $("#state").click();

        $("#stateCity-wrapper").$(byText("Haryana")).click();

        $("#city").click();

        $("#stateCity-wrapper").$(byText("Karnal")).click();




        $("#submit").click();


        // Проверка текста в форме после заполнения


        $(".modal-dialog").should(appear);

        $(".table-responsive").shouldHave(text(firstName), text(lastName));
        $(".table-responsive").shouldHave(text(userEmail));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("1234567893"));
        $(".table-responsive").shouldHave(text("25 March,2005"));
        $(".table-responsive").shouldHave(text("English"));
        $(".table-responsive").shouldHave(text("Music"));
        $(".table-responsive").shouldHave(text("Photo.jpeg"));
        $(".table-responsive").shouldHave(text(streetAddress));
        $(".table-responsive").shouldHave(text("Haryana Karnal"));

        // Дополнительно проверяем общий успех отправки формы
        $(".modal-content").shouldHave(text("Thanks for submitting the form"));
    }
}
