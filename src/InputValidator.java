import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
public class InputValidator {
    public static boolean isValidPhoneNumber (String phoneNumber) {
        String numbers = "0123456789";
        if(phoneNumber.length() < 11 || phoneNumber.length() > 12) {
            return false;
        }

        if(phoneNumber.charAt(0) == '+') {
            phoneNumber = phoneNumber.substring(1);
        }

        for(int i = 0; i < phoneNumber.length(); i++) {
            if(numbers.indexOf(phoneNumber.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        if (password.length() < 8 || password.length() > 20) {
            return false;
        }

        int uppercaseCounter = 0, lowercaseCounter = 0, specialCounter = 0, digitCounter = 0;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if(Character.isUpperCase(c))
                uppercaseCounter++;

            else if(Character.isLowerCase(c))
                lowercaseCounter++;

            else if(Character.isDigit(c))
                digitCounter++;

            if(c>=33&&c<=46||c==64){
                specialCounter++;
            }
        }

        return (uppercaseCounter > 1 && lowercaseCounter > 1 && specialCounter > 1 && digitCounter > 1);
    }
}
