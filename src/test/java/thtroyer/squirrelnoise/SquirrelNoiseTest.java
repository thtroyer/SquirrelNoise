package thtroyer.squirrelnoise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static thtroyer.squirrelnoise.SquirrelNoise.squirrelNoise5;

public class SquirrelNoiseTest {
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
    public void testSquirrelNoise5Performance() {
        for (int i = 0; i < 10000000; i++) {
            squirrelNoise5(i, i+5);
        }
    }

}