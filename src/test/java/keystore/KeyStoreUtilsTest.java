package keystore;

import org.junit.jupiter.api.Test;
import symmetric.SymmetricEncryptionUtils;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import java.security.KeyStore;

import static org.junit.jupiter.api.Assertions.*;

class KeyStoreUtilsTest {

    @Test
    void createPrivateKeyJavaKeyStore() throws Exception{
        SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
        String secretKeyHex = DatatypeConverter.printHexBinary(secretKey.getEncoded());
        KeyStore keyStore = KeyStoreUtils.createPrivateKeyJavaKeyStore("password", "foo", secretKey, "keyPassword");
        assertNotNull(keyStore);

        keyStore.load(null, "password".toCharArray());
        KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection("keyPassword".toCharArray());
        KeyStore.SecretKeyEntry resultEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry("foo", entryPassword);
        SecretKey result = resultEntry.getSecretKey();
        String resultKeyHex = DatatypeConverter.printHexBinary(result.getEncoded());
        assertEquals(secretKeyHex, resultKeyHex);
    }
}