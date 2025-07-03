package tests;

import org.junit.jupiter.api.Test;


public class RegistrationWithPageObjectTests extends TestBase {


    @Test
    void successfulFillFormTest() {

        registrationPage.openPage()
                .removeAd()
                .setFirstName("Kostya")
                .setLastName("Ivanov")
                .setEmail("kostya@ivanov.com")
                .setGender("Male")
                .setNumber("1234567893")
                .setBirthDate("25", "March", "2005")
                .setSubjects("Eng")
                .setHobbies("Music")
                .uploadPicture("Photo.jpeg")
                .setAddress("123 Main St.")
                .selectStateAndCity("Haryana", "Karnal")
                .submitForm();

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
    }
    @Test
    void successfulMinFormTest() {

        registrationPage.openPage()
                .removeAd()
                .setFirstName("Kostya")
                .setLastName("Ivanov")
                .setGender("Male")
                .setNumber("1234567893")
                .submitForm();

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", "Kostya Ivanov")
                .verifyResult("Gender", "Male")
                .verifyResult("Mobile", "1234567893");
    }
    @Test
    void negativeMinFormTest() {

        registrationPage.openPage()
                .removeAd()
                .setFirstName("Kostya")
                .setLastName("Ivanov")
                .setGender("Male")
                .setNumber("")
                .submitForm();

        registrationPage.verifyResultsModalAppearsNeg();
    }
}