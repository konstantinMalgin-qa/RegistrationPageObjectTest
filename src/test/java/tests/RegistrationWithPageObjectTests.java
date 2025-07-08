package tests;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static tests.TestData.uploadPicture;


public class RegistrationWithPageObjectTests extends TestBase {

    TestDataGenerator data = new TestDataGenerator();
    String userName = data.setFirstName();
    String lastName = data.setLastName();
    String userEmail = data.getEmail();
    String gender = data.getGender();
    String phoneNumber = data.getPhoneNumber();
    String address = data.getAddress();
    String subjects = data.getSubject();
    String hobbies = data.getHobby();

    Date birthday = data.getBirthday();
    String dayOfBirth = data.getDayOfBirth(birthday);
    String monthOfBirth = data.getMonthOfBirth(birthday);
    String yearOfBirth = data.getYearOfBirth(birthday);

    String state = data.getState();
    String city = data.getCity(state);


    @Test
    void successfulFillFormTest() {

        registrationPage.openPage()
                .removeAd()
                .setFirstName(userName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setNumber(phoneNumber)
                .setBirthDate(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .uploadPicture(uploadPicture)
                .setAddress(address)
                .selectStateAndCity(state, city)
                .submitForm();

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", userName + " " + lastName)
                .verifyResult("Student Email", userEmail)
                .verifyResult("Gender", gender)
                .verifyResult("Mobile", phoneNumber)
                .verifyResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .verifyResult("Subjects", subjects)
                .verifyResult("Hobbies", hobbies)
                .verifyResult("Picture", uploadPicture)
                .verifyResult("Address", address)
                .verifyResult("State and City", state + " " + city);
    }
    @Test
    void successfulMinFormTest() {

        registrationPage.openPage()
                .removeAd()
                .setFirstName(userName)
                .setLastName(lastName)
                .setGender(gender)
                .setNumber(phoneNumber)
                .submitForm();

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", userName + " " + lastName)
                .verifyResult("Gender", gender)
                .verifyResult("Mobile", phoneNumber);
    }
    @Test
    void negativeMinFormTest() {

        registrationPage.openPage()
                .removeAd()
                .setFirstName(userName)
                .setLastName(lastName)
                .setGender(gender)
                .setNumber("")
                .submitForm();

        registrationPage.verifyResultsModalAppearsNeg();
    }
}