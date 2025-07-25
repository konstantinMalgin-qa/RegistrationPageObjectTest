package tests;

import java.util.Date;

public class SuitTestData {
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

}
