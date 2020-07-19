package assymetric;

import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.security.KeyPair;

import static org.junit.jupiter.api.Assertions.*;

class AssymetricEncriptionUtilsTest {

    @Test
    void generateRSAKeyPair() throws Exception{
        KeyPair keyPair = AssymetricEncriptionUtils.generateRSAKeyPair();
        assertNotNull(keyPair);
        System.out.println("Private Key: " + DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));
        System.out.println("Public Key:  " + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
    }

    @Test
    void testRSACryptoRoutine() throws Exception{
        KeyPair keypair = AssymetricEncriptionUtils.generateRSAKeyPair();
        String plainText = "this is the text we are going to hide in plain sight";
        byte[] cipherText = AssymetricEncriptionUtils.performRSAEncryption(plainText, keypair.getPrivate());
        assertNotNull(cipherText);
        System.out.println(DatatypeConverter.printHexBinary(cipherText));
        String decryptedText = AssymetricEncriptionUtils.performRSADecryption(cipherText, keypair.getPublic());
        assertEquals(plainText, decryptedText);

    }
}