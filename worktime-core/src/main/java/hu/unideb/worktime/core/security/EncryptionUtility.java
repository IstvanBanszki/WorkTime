package hu.unideb.worktime.core.security;

import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.jasypt.digest.StandardStringDigester;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.stereotype.Component;
import java.security.SecureRandom;

@Component
public class EncryptionUtility implements IEncryptionUtility {

    private final StandardStringDigester passwordEncryptor;
    private final Base64 base64Converter;

    static final String ABC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public EncryptionUtility() {
        this.passwordEncryptor = new StandardStringDigester();
        this.passwordEncryptor.setAlgorithm("SHA-1");
        this.passwordEncryptor.setIterations(1000);
        this.passwordEncryptor.setSaltGenerator(new RandomSaltGenerator());
        this.passwordEncryptor.setSaltSizeBytes(8);
        this.passwordEncryptor.setStringOutputType("base64");
        this.passwordEncryptor.initialize();
        this.base64Converter = new Base64();
    }

    @Override
    public String encryptPassword(String inputPassword) {
        return this.passwordEncryptor.digest(inputPassword);
    }

    @Override
    public boolean checkPassword(String inputPassword, String storedPassword) {
        return this.passwordEncryptor.matches(inputPassword, storedPassword);
    }

    @Override
    public String base64Decode(String text) {
        return new String(this.base64Converter.decode(text.getBytes()));
    }

    @Override
    public String base64Encode(String text) {
        return new String(this.base64Converter.encode(text.getBytes()));
    }

    @Override
    public String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(ABC.charAt(random.nextInt(ABC.length())));
        }
        return sb.toString();
    }

}
