package model;

import io.swagger.model.User;
import org.junit.Assert;
import org.junit.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

public class UserTest {
    @Test
    public void createUserShouldNotBeNull(){
        User user = new User("Kayleigh","Rem", "kayleighrem@gmail.com", true, "Welkom01", 33);
        assertNotNull(user);
    }

    @Test
    public void invalidTypeShouldThrowIllegalArgumentException(){
        User user = new User();
        user.setEmail("CUSTOMER");
        Assert.assertTrue(user.getEmail().matches("^[A-Za-z0-9]{5,20}$"));
    }

    @Test
    public void invalidPasswordShouldThrowIllegalArgumentException(){
        User user = new User();
        user.setPassword("Welkom01");
        boolean isValid = true;
        if (user.getPassword().length() < 8) isValid = false;

        int charCount = 0;
        int numCount = 0;
        for (int i = 0; i < user.getPassword().length(); i++) {
            char ch = user.getPassword().charAt(i);
            if (isNumeric(ch))
                numCount++;
            else if (isLetter(ch))
                charCount++;
            else
                isValid = false;
        }
        isValid = (charCount >= 2 && numCount >= 2);

        if (!isValid)
            throw new IllegalArgumentException("Password is not valid!");
    }

    public static boolean isLetter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }

    public static boolean isNumeric(char ch) {
        return (ch >= '0' && ch <= '9');
    }

    @Test
    public void invalidFirstNameShouldThrowIllegalArgumentException(){
        User user = new User();
        user.setName("Kayleigh");
        Assert.assertTrue(user.getName().matches("^[A-Za-z]{1,20}$"));
    }

    @Test
    public void invalidLastNameShouldThrowIllegalArgumentException(){
        User user = new User();
        user.setLastname("Rem");
        Assert.assertTrue(user.getLastname().matches("^[A-Za-z]{1,20}$"));
    }

    @Test
    public void invalidEmailShouldThrowIllegalArgumentException(){
        User user = new User();
        user.setEmail("kayleighrem@gmail.com");
        Assert.assertTrue(user.getEmail().matches("^[A-Za-z]{1,20}[@][A-Za-z]{5,20}[.][a-z]{2,3}$"));
    }
}
