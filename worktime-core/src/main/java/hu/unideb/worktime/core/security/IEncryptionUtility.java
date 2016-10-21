package hu.unideb.worktime.core.security;

public interface IEncryptionUtility {

    String encryptPassword(String inputPassword);
    boolean checkPassword(String inputPassword, String storedPassword);
    String base64Decode(String text);
    String base64Encode(String text);
    String generateRandomPassword();

}
