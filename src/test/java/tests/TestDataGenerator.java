package tests;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static tests.TestData.*;

public class TestDataGenerator  {

    public static Faker faker = new Faker(new Locale("en-GB"));

//    public String getFullUserName() {
//        return faker.name().fullName();
//    }

    public String setFirstName() {
        return faker.name().firstName();
    }

    public String setLastName() {
        return faker.name().lastName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getAddress() {
        return faker.address().streetAddress();
    }

    public String getPhoneNumber() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public String getGender() {
        return faker.demographic().sex();
    }

    public String getSubject() {
        return faker.options().option(TestData.SUBJECT_OPTIONS);
    }

    public String getHobby() {
        return faker.options().option(TestData.HOBBY_OPTIONS);
    }

    public String getState() {
        return faker.options().option(TestData.mapStateWithCity.keySet().toArray()).toString();
    }

    public String getCity(String state) {
        return faker.options().option(TestData.mapStateWithCity.get(state));
    }

    public Date getBirthday() {
        return faker.date().birthday();
    }

    public String getDayOfBirth(Date birthday) {
        return new SimpleDateFormat("dd", Locale.ENGLISH).format(birthday);
    }

    public String getMonthOfBirth(Date birthday) {
        return new SimpleDateFormat("MMMM", Locale.ENGLISH).format(birthday);
    }

    public String getYearOfBirth(Date birthday) {
        return new SimpleDateFormat("y", Locale.ENGLISH).format(birthday);
    }

    public String getFile() {
        return uploadPicture;
    }
}