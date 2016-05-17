
package hu.unideb.worktime.service.security;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.stereotype.Component;

@Component
public class WTEncryption {

   private final StandardStringDigester passwordEncryptor;
   
   public WTEncryption(){
       this.passwordEncryptor = new StandardStringDigester();
       this.passwordEncryptor.setAlgorithm("SHA-1");
       this.passwordEncryptor.setIterations(1000);
       this.passwordEncryptor.setSaltGenerator(new RandomSaltGenerator());
       this.passwordEncryptor.setSaltSizeBytes(8);
       this.passwordEncryptor.setStringOutputType("base64");
       this.passwordEncryptor.initialize();
   }

   public String encryptPassword(String inputPassword){
       return this.passwordEncryptor.digest(inputPassword);
   }
   
   public boolean checkPassword(String inputPassword, String storedPassword){
       return this.passwordEncryptor.matches(inputPassword, storedPassword);
   }
}
