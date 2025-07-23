package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static io.qameta.allure.Allure.step;


public class RegistrationWithPageObjectTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @Tag("DemoQa")
    void successfulFillFormTest() {

        step("Открываем форму", () -> {
            registrationPage.openPage();
        });
        step("Заполняем форму полностью и отправляем", () -> {

                    registrationPage
                            .removeAd()
                            .setFirstName("Kostya")
                            .setLastName("Ivanov")
                            .setEmail("kostya@ivanov.com")
                            .setGender("Male")
                            .setNumber("1234567893")
                            .setBirthDate("March", "2005", "25")
                            .setSubjects("Eng")
                            .setHobbies("Music")
                            .uploadPicture("Photo.jpeg")
                            .setAddress("123 Main St.")
                            .selectStateAndCity("Haryana", "Karnal")
                            .submitForm();
                });
        step("Проверяем окно с результатами", () -> {

            registrationPage.verifyResultsModalAppears()
                    .verifyResult("Student Name", "Kostya Ivanov")
                    .verifyResult("Student Email", "kostya@ivanov.com")
                    .verifyResult("Gender", "Male")
                    .verifyResult("Mobile", "1234567893")
                    .verifyResult("Date of Birth", "25 March,2005")
                    .verifyResult("Subjects", "English")
                    .verifyResult("Hobbies", "Music")
                    .verifyResult("Picture", "Photo.jpeg")
                    .verifyResult("Address", "123 Main St.")
                    .verifyResult("State and City", "Haryana Karnal");
        });
    }
    @Test
    void successfulMinFormTest() {

            step("Открываем форму", () -> {
                        registrationPage.openPage();
                    });
        step("Заполняем форму полностью и отправляем", () -> {
                    registrationPage
                            .removeAd()
                            .setFirstName("Kostya")
                            .setLastName("Ivanov")
                            .setGender("Male")
                            .setNumber("1234567893")
                            .submitForm();
                });
        step("Проверяем окно с результатами", () -> {
            registrationPage.verifyResultsModalAppears()
                    .verifyResult("Student Name", "Kostya Ivanov")
                    .verifyResult("Gender", "Male")
                    .verifyResult("Mobile", "1234567893");
        });
    }
    @Test
    void negativeMinFormTest() {
        step("Открываем форму", () -> {
            registrationPage.openPage();
        });
        step("Заполняем форму полностью и отправляем", () -> {
                    registrationPage
                            .removeAd()
                            .setFirstName("Kostya")
                            .setLastName("Ivanov")
                            .setGender("Male")
                            .setNumber("")
                            .submitForm();
                });
        step("Проверяем окно с результатами", () -> {

            registrationPage.verifyResultsModalAppearsNeg();
        });
    }
}