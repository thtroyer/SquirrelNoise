package thtroyer.squirrelnoise;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static thtroyer.squirrelnoise.SquirrelNoise.*;


/**
 * Various tests to validate this version against the original C++ implementation
 */
public class VerifyOriginalResultsTest {
    /**
     * These are verified outputs from the original C++ version
     */
    @Test
    public void testSquirrelNoise5() {
        assertThat(squirrelNoise5(0,0))
                .isEqualTo(377036288);
        assertThat(squirrelNoise5(1,0))
                .isEqualTo(3365260061L );
        assertThat(squirrelNoise5(2,0))
                .isEqualTo(3009420505L);
        assertThat(squirrelNoise5(182382948,0))
                .isEqualTo(159582615L);
        assertThat(squirrelNoise5(0,1))
                .isEqualTo(603375697L);
        assertThat(squirrelNoise5(1,1))
                .isEqualTo(2562153792L);
        assertThat(squirrelNoise5(238198231,129389182))
                .isEqualTo(2277364147L);
        assertThat(squirrelNoise5(-5,0))
                .isEqualTo(3761360600L);
        assertThat(squirrelNoise5(-19238,28382))
                .isEqualTo(2417081196L);
    }

    @Test
    public void testGet1dNoise() {
        assertThat(get1dNoise(0,0))
                .isEqualTo(377036288);
        assertThat(get1dNoise(1,0))
                .isEqualTo(3365260061L );
        assertThat(get1dNoise(2,0))
                .isEqualTo(3009420505L);
        assertThat(get1dNoise(182382948,0))
                .isEqualTo(159582615L);
        assertThat(get1dNoise(0,1))
                .isEqualTo(603375697L);
        assertThat(get1dNoise(1,1))
                .isEqualTo(2562153792L);
        assertThat(get1dNoise(238198231,129389182))
                .isEqualTo(2277364147L);
        assertThat(get1dNoise(-5,0))
                .isEqualTo(3761360600L);
        assertThat(get1dNoise(-19238,28382))
                .isEqualTo(2417081196L);
    }

    @Test
    public void test1dZeroTo1() {
        assertThat(get1dNoiseZeroToOne(0,0))
                .isEqualTo(0.0877856f);
    }

    @Test
    public void test1dNeg1To1() {
        assertThat(get1dNoiseNegOneToOne(0,0))
                .isCloseTo(0.175571f, Percentage.withPercentage(0.001));
    }

    /**
     * Based on hash of large outputs from original SquirrelNoise5
     */
    @Test
    public void test1MBytes_0Seed_Hash() throws NoSuchAlgorithmException {
        SquirrelRandom squirrelRandom = new SquirrelRandom(0);

        List<Byte> byteList = new ArrayList<>();
        for (int i = 0; i < (1024 * 1024); i += 4) {
            var b = squirrelRandom.getNextBytes();
            byteList.add(b[0]);
            byteList.add(b[1]);
            byteList.add(b[2]);
            byteList.add(b[3]);
        }

        byte[] bytes = new byte[1048576];
        for (int i = 0; i < 1048576; i++) {
            bytes[i] = byteList.get(i);
        }

        MessageDigest md = MessageDigest.getInstance("MD5");
        var digest = md.digest(bytes);
        BigInteger bigInt = new BigInteger(1,digest);
        String hash = bigInt.toString(16);

        assertThat(hash).isEqualTo("2768c9b3029d459e6525f6e5ab074515");
    }

    /**
     * Based on hash of large outputs from original SquirrelNoise5
     */
    @Test
    public void test1MBytes_4852Seed_Hash() throws NoSuchAlgorithmException {
        SquirrelRandom squirrelRandom = new SquirrelRandom(4852);

        List<Byte> byteList = new ArrayList<>();
        for (int i = 0; i < (1024 * 1024); i += 4) {
            var b = squirrelRandom.getNextBytes();
            byteList.add(b[0]);
            byteList.add(b[1]);
            byteList.add(b[2]);
            byteList.add(b[3]);
        }

        byte[] bytes = new byte[1048576];
        for (int i = 0; i < 1048576; i++) {
            bytes[i] = byteList.get(i);
        }

        MessageDigest md = MessageDigest.getInstance("MD5");
        var digest = md.digest(bytes);
        BigInteger bigInt = new BigInteger(1,digest);
        String hash = bigInt.toString(16);

        assertThat(hash).isEqualTo("138eb42b996e3f2d2c49008f62b394ed");
    }
}