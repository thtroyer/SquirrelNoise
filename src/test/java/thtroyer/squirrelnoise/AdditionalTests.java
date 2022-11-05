package thtroyer.squirrelnoise;

import org.assertj.core.data.Offset;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static thtroyer.squirrelnoise.SquirrelNoise.*;

public class AdditionalTests {
    @Test
    public void testSquirrelNoise5() {
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            assertThat(squirrelNoise5(random.nextInt(500000), random.nextInt(500000)))
                    .isBetween(0L, 4294967295L);
        }
    }

    @Test
    public void testZeroToOneWithinRange() {
        Random random = new Random();

        for (int i = 0; i < 1000000; i++) {
            int rand1 = random.nextInt(500000);
            int rand2 = random.nextInt(500000);
            int rand3 = random.nextInt(500000);
            int rand4 = random.nextInt(500000);
            long randSeed = random.nextInt(500000);

            assertThat(get1dNoiseZeroToOne(
                    rand1,
                    randSeed
            )).isBetween(0f, 1.0f);

            assertThat(get2dNoiseZeroToOne(
                    rand1,
                    rand2,
                    randSeed
            )).isBetween(0f, 1.0f);

            assertThat(get3dNoiseZeroToOne(
                    rand1,
                    rand2,
                    rand3,
                    randSeed
            )).isBetween(0f, 1.0f);

            assertThat(get4dNoiseZeroToOne(
                    rand1,
                    rand2,
                    rand3,
                    rand4,
                    randSeed
            )).isBetween(0f, 1.0f);
        }
    }

    @Test
    public void testZeroToOneAvg() {
        Random random = new Random();
        int numIterations = 10000;
        double sum1d = 0;
        double sum2d = 0;
        double sum3d = 0;
        double sum4d = 0;

        for (int i = 0; i < numIterations; i++) {
            int rand1 = random.nextInt(500000);
            int rand2 = random.nextInt(500000);
            int rand3 = random.nextInt(500000);
            int rand4 = random.nextInt(500000);
            long randSeed = random.nextInt(500000);

            sum1d += get1dNoiseZeroToOne( rand1, randSeed);

            sum2d += get2dNoiseZeroToOne( rand1, rand2, randSeed);

            sum3d += get3dNoiseZeroToOne( rand1, rand2, rand3, randSeed);

            sum4d += get4dNoiseZeroToOne( rand1, rand2, rand3, rand4, randSeed);
        }

        assertThat(sum1d/numIterations).isCloseTo(0.5f, Percentage.withPercentage(2));
        assertThat(sum2d/numIterations).isCloseTo(0.5f, Percentage.withPercentage(2));
        assertThat(sum3d/numIterations).isCloseTo(0.5f, Percentage.withPercentage(2));
        assertThat(sum4d/numIterations).isCloseTo(0.5f, Percentage.withPercentage(2));
    }

    @Test
    public void testNegOneToOneAvg() {
        Random random = new Random();
        int numIterations = 10000;
        double sum1d = 0;
        double sum2d = 0;
        double sum3d = 0;
        double sum4d = 0;

        for (int i = 0; i < numIterations; i++) {
            int rand1 = random.nextInt(500000);
            int rand2 = random.nextInt(500000);
            int rand3 = random.nextInt(500000);
            int rand4 = random.nextInt(500000);
            long randSeed = random.nextInt(500000);

            sum1d += get1dNoiseNegOneToOne( rand1, randSeed);

            sum2d += get2dNoiseNegOneToOne( rand1, rand2, randSeed);

            sum3d += get3dNoiseNegOneToOne( rand1, rand2, rand3, randSeed);

            sum4d += get4dNoiseNegOneToOne( rand1, rand2, rand3, rand4, randSeed);
        }

        assertThat(sum1d/numIterations).isCloseTo(0.0f, Offset.offset(0.1));
        assertThat(sum2d/numIterations).isCloseTo(0.0f, Offset.offset(0.1));
        assertThat(sum3d/numIterations).isCloseTo(0.0f, Offset.offset(0.1));
        assertThat(sum4d/numIterations).isCloseTo(0.0f, Offset.offset(0.1));
    }

    @Test
    public void testNegOneToOneWithinRange() {
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            int rand1 = random.nextInt(500000);
            int rand2 = random.nextInt(500000);
            int rand3 = random.nextInt(500000);
            int rand4 = random.nextInt(500000);
            long randSeed = random.nextInt(500000);

            assertThat(get1dNoiseNegOneToOne(
                    rand1,
                    randSeed
            )).isBetween(-1.0f, 1.0f);

            assertThat(get2dNoiseNegOneToOne(
                    rand1,
                    rand2,
                    randSeed
            )).isBetween(-1.0f, 1.0f);

            assertThat(get3dNoiseNegOneToOne(
                    rand1,
                    rand2,
                    rand3,
                    randSeed
            )).isBetween(-1.0f, 1.0f);

            assertThat(get4dNoiseNegOneToOne(
                    rand1,
                    rand2,
                    rand3,
                    rand4,
                    randSeed
            )).isBetween(-1.0f, 1.0f);
        }

    }

}