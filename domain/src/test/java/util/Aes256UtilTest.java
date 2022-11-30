package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.junit.jupiter.api.Assertions.*;

class Aes256UtilTest {


    @Test
    void encrypt() {
        String encrypt = Aes256Util.encrypt("hello world");
        Assertions.assertEquals(Aes256Util.decrypt(encrypt), "hello world");

    }

}