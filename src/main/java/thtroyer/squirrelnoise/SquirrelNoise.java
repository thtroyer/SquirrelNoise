package thtroyer.squirrelnoise;

/**
 * Based on squirrelNoise5 by Squirrel Eiserloh, implemented in C++.
 * Original version available here: http://eiserloh.net/noise/squirrelNoise5.hpp
 *
 * Since Java has no unsigned int used in the original algorithm, a long is being used, which is
 * regularly masked back to the original 32-bit int size.
 */
public class SquirrelNoise {
    private static final double ONE_OVER_MAX_UINT = (1.0 / (double) Long.parseLong("ffffffff", 16));
    private static final double ONE_OVER_MAX_INT = (1.0 / (double) Long.parseLong("7fffffff", 16));

    /**
     * @param positionX signed int value for position
     * @param seed      unsigned int value between 0 and 4294967295
     * @return random value between 0 and 4294967295
     */
    public static long squirrelNoise5(int positionX, long seed) {
        if (seed < 0 || seed > 4294967295L) {
            // for compatibility with C version.  May remove later.
            throw new RuntimeException("seed out of range.");
        }

        long bitMask = Long.parseLong("ffffffff", 16);

        long SQ5_BIT_NOISE1 = Long.parseLong("d2a80a3f", 16);
        long SQ5_BIT_NOISE2 = Long.parseLong("a884f197", 16);
        long SQ5_BIT_NOISE3 = Long.parseLong("6C736F4B", 16);
        long SQ5_BIT_NOISE4 = Long.parseLong("B79F3ABB", 16);
        long SQ5_BIT_NOISE5 = Long.parseLong("1b56c4f5", 16);

        long mangledBits = positionX;
        mangledBits *= SQ5_BIT_NOISE1;

        mangledBits += seed;

        mangledBits &= bitMask;
        mangledBits ^= (mangledBits >> 9);

        mangledBits += SQ5_BIT_NOISE2;

        mangledBits &= bitMask;
        mangledBits ^= (mangledBits >> 11);

        mangledBits *= SQ5_BIT_NOISE3;

        mangledBits &= bitMask;
        mangledBits ^= (mangledBits >> 13);

        mangledBits += SQ5_BIT_NOISE4;

        mangledBits &= bitMask;
        mangledBits ^= (mangledBits >> 15);

        mangledBits *= SQ5_BIT_NOISE5;

        mangledBits &= bitMask;
        mangledBits ^= (mangledBits >> 17);

        mangledBits &= bitMask;

        return mangledBits;
    }

    /**
     * @return long, but returns 32bit unsigned in range, 0-4294967295
     */
    public static long get1dNoise(int indexX, long seed) {
        return squirrelNoise5(indexX, seed);
    }

    public static long get2dNoise(int indexX, int indexY, long seed) {
        int PRIME_NUMBER = 198491317;
        return squirrelNoise5(indexX + (PRIME_NUMBER * indexY), seed);
    }

    public static long get3dNoise(int indexX, int indexY, int indexZ, long seed) {
        int PRIME1 = 198491317;
        int PRIME2 = 6542989;

        return squirrelNoise5(indexX + (PRIME1 * indexY) + (PRIME2 * indexZ), seed);
    }

    public static long get4dNoise(int indexX, int indexY, int indexZ, int indexT, long seed) {
        int PRIME1 = 198491317;
        int PRIME2 = 6542989;
        int PRIME3 = 357239;
        return squirrelNoise5(indexX + (PRIME1 * indexY) + (PRIME2 * indexZ) + (PRIME3 * indexT), seed);
    }

    public static float get1dNoiseZeroToOne(int index, long seed) {
        return (float) (ONE_OVER_MAX_UINT * (double) squirrelNoise5(index, seed));
    }

    public static float get2dNoiseZeroToOne(int indexX, int indexY, long seed) {
        return (float) (ONE_OVER_MAX_UINT * (double) get2dNoise(indexX, indexY, seed));
    }

    public static float get3dNoiseZeroToOne(int indexX, int indexY, int indexZ, long seed) {
        return (float) (ONE_OVER_MAX_UINT * (double) get3dNoise(indexX, indexY, indexZ, seed));
    }

    public static float get4dNoiseZeroToOne(int indexX, int indexY, int indexZ, int indexT, long seed) {
        return (float) (ONE_OVER_MAX_UINT * (double) get4dNoise(indexX, indexY, indexZ, indexT, seed));
    }


    public static float get1dNoiseNegOneToOne(int index, long seed) {
        return (float) (ONE_OVER_MAX_INT * (double) (int) squirrelNoise5(index, seed));
    }


    public static float get2dNoiseNegOneToOne(int indexX, int indexY, long seed) {
        return (float) (ONE_OVER_MAX_INT * (double) (int) get2dNoise(indexX, indexY, seed));
    }


    public static float get3dNoiseNegOneToOne(int indexX, int indexY, int indexZ, long seed) {
        return (float) (ONE_OVER_MAX_INT * (double) (int) get3dNoise(indexX, indexY, indexZ, seed));
    }


    public static float get4dNoiseNegOneToOne(int indexX, int indexY, int indexZ, int indexT, long seed) {
        return (float) (ONE_OVER_MAX_INT * (double) (int) get4dNoise(indexX, indexY, indexZ, indexT, seed));
    }

}
