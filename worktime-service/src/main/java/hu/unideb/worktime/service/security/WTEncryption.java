package hu.unideb.worktime.service.security;

import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.jasypt.digest.StandardStringDigester;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.stereotype.Component;

@Component
public class WTEncryption {

    private final StandardStringDigester passwordEncryptor;
    private final Base64 base64Converter;

    public WTEncryption() {
        this.passwordEncryptor = new StandardStringDigester();
        this.passwordEncryptor.setAlgorithm("SHA-1");
        this.passwordEncryptor.setIterations(1000);
        this.passwordEncryptor.setSaltGenerator(new RandomSaltGenerator());
        this.passwordEncryptor.setSaltSizeBytes(8);
        this.passwordEncryptor.setStringOutputType("base64");
        this.passwordEncryptor.initialize();
        this.base64Converter = new Base64();
    }

    public String encryptPassword(String inputPassword) {
        return this.passwordEncryptor.digest(inputPassword);
    }

    public boolean checkPassword(String inputPassword, String storedPassword) {
        return this.passwordEncryptor.matches(inputPassword, storedPassword);
    }

    public String base64Decode(String text) {
        return new String(this.base64Converter.decode(text.getBytes()));
    }
    
    public String base64Encode(String text){
        return new String(this.base64Converter.encode(text.getBytes()));
    }
}
