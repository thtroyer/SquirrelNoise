package thtroyer.squirrelnoise;

public class SquirrelNoise {
    /**
     * Based on SquirrelNoise5 by Squirrel Eiserloh, implemented in C++.
     * Original version available here: * http://eiserloh.net/noise/SquirrelNoise5.hpp
     *
     * Since Java has no unsigned int used in the original algorithm, a long is being used, which is
     * regularly masked back to the original 32-bit int size.
     *
     * @param positionX signed int value for position
     * @param seed unsigned int value between 0 and 4294967295
     * @return random value between 0 and 4294967295
     */
    public static long squirrelNoise5(int positionX, long seed )
    {
        if (positionX < 0 || positionX > 4294967295L) {
            throw new RuntimeException("positionX out of range.");
        }

        if (seed < 0 || seed > 4294967295L) {
            throw new RuntimeException("seed out of range.");
        }

        long bitMask = Long.parseLong("ffffffff", 16);

        long SQ5_BIT_NOISE1 =Long.parseLong("d2a80a3f",16);
        long SQ5_BIT_NOISE2 =Long.parseLong("a884f197",16);
        long SQ5_BIT_NOISE3 =Long.parseLong("6C736F4B",16);
        long SQ5_BIT_NOISE4 =Long.parseLong("B79F3ABB",16);
        long SQ5_BIT_NOISE5 =Long.parseLong("1b56c4f5",16);

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
}
