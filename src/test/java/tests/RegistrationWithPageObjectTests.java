package tests;

import org.junit.jupiter.api.Test;


import static tests.TestData.UPLOAD_PICTURE;


public class RegistrationWithPageObjectTests extends TestBase {


    @Test
    void successfulFillFormTest() {

        SuitTestData data = new SuitTestData();

        registrationPage.openPage()
                .removeAd()
                .setFirstName(data.userName)
                .setLastName(data.lastName)
                .setEmail(data.userEmail)
                .setGender(data.gender)
                .setNumber(data.phoneNumber)
                .setBirthDate(data.dayOfBirth, data.monthOfBirth, data.yearOfBirth)
                .setSubjects(data.subjects)
                .setHobbies(data.hobbies)
                .uploadPicture(UPLOAD_PICTURE)
                .setAddress(data.address)
                .selectStateAndCity(data.state, data.city)
                .submitForm();

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", data.userName + " " + data.lastName)
                .verifyResult("Student Email", data.userEmail)
                .verifyResult("Gender", data.gender)
                .verifyResult("Mobile", data.phoneNumber)
                .verifyResult("Date of Birth", data.dayOfBirth + " " + data.monthOfBirth + "," + data.yearOfBirth)
                .verifyResult("Subjects", data.subjects)
                .verifyResult("Hobbies", data.hobbies)
                .verifyResult("Picture", UPLOAD_PICTURE)
                .verifyResult("Address", data.address)
                .verifyResult("State and City", data.state + " " + data.city);
    }
    @Test
    void successfulMinFormTest() {

        SuitTestData data = new SuitTestData();

        registrationPage.openPage()
                .removeAd()
                .setFirstName(data.userName)
                .setLastName(data.lastName)
                .setGender(data.gender)
                .setNumber(data.phoneNumber)
                .submitForm();

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", data.userName + " " + data.lastName)
                .verifyResult("Gender", data.gender)
                .verifyResult("Mobile", data.phoneNumber);
    }
    @Test
    void negativeMinFormTest() {

        SuitTestData data = new SuitTestData();

        registrationPage.openPage()
                .removeAd()
                .setFirstName(data.userName)
                .setLastName(data.lastName)
                .setGender(data.gender)
                .setNumber("")
                .submitForm();

        registrationPage.verifyResultsModalAppearsNeg();
    }
}