package hu.unideb.worktime.core.security

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
    
    def "test password check "(){

        setup: "create new Encryption object"
            def encrytor = new WTEncryption()
        and: "create a digest password"
            def storedPassword = encrytor.encryptPassword(inputPassword)
        expect: "not generate the same result"
            encrytor.checkPassword(testPassword, storedPassword) == expected

        where:
            inputPassword | testPassword || expected
            "lost"        | "lost"       || true
            "save"        | "save"       || true
            "save"        | "lost"       || false
            "lost"        | "save"       || false
    }

    def "test base64 functionality"(){

        setup: "create new Encryption object"
            def encrytor = new WTEncryption()
        expect: "encode and decode string input and compare with the original input"
            input == encrytor.base64Decode(encrytor.base64Encode(input))

        where:
            input << ['123', 'test', 'TEST', ':@#$%^&*!_+(){}~*/\\[];,.', '', ' ', '\n']
    }
}

