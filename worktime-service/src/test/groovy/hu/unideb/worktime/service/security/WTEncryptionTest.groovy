package hu.unideb.worktime.service.security

import spock.lang.Specification

class WTEncryptionTest extends Specification {
    
    def "test password creation"(){

        setup: "create new Encryption object"
            def encrytor = new WTEncryption()

        expect: "not generate the same result"
            encrytor.encryptPassword(inputPassword) != encrytor.encryptPassword(inputPassword)

        where:
            inputPassword << ["root", "hard"]
    }
    
    def "test password check"(){
        
        setup: "create new Encryption object"
            def encrytor = new WTEncryption()
        
        and: "create password"
            def storedPassword = encrytor.encryptPassword(inputPassword)
        
        expect: "not generate the same result"
            encrytor.checkPassword(inputPassword, storedPassword) == true
        
        where:
            inputPassword << ["lost", "save"]
    }
}

