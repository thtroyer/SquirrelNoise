package thtroyer.squirrelnoise;

public class SquirrelNoise {
    private static double ONE_OVER_MAX_UINT = (1.0 / (double) Long.parseLong("ffffffff", 16));

    /**
     * Based on SquirrelNoise5 by Squirrel Eiserloh, implemented in C++.
     * Original version available here: * http://eiserloh.net/noise/SquirrelNoise5.hpp
     * <p>
     * Since Java has no unsigned int used in the original algorithm, a long is being used, which is
     * regularly masked back to the original 32-bit int size.
     *
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

    //todo: test

    public static long get1dNoiseUint(int positionX, long seed) {
        return squirrelNoise5(positionX, seed);
    }

    //    //todo: implement
//
//    //-----------------------------------------------------------------------------------------------
//    constexpr unsigned int Get3dNoiseUint( int indexX, int indexY, int indexZ, unsigned int seed )
//    {
//        constexpr int PRIME1 = 198491317; // Large prime number with non-boring bits
//        constexpr int PRIME2 = 6542989; // Large prime number with distinct and non-boring bits
//        return SquirrelNoise5( indexX + (PRIME1 * indexY) + (PRIME2 * indexZ), seed );
//    }
//
//    //-----------------------------------------------------------------------------------------------
//    constexpr unsigned int Get4dNoiseUint( int indexX, int indexY, int indexZ, int indexT, unsigned int seed )
//    {
//        constexpr int PRIME1 = 198491317; // Large prime number with non-boring bits
//        constexpr int PRIME2 = 6542989; // Large prime number with distinct and non-boring bits
//        constexpr int PRIME3 = 357239; // Large prime number with distinct and non-boring bits
//        return SquirrelNoise5( indexX + (PRIME1 * indexY) + (PRIME2 * indexZ) + (PRIME3 * indexT), seed );
//    }
//
////-----------------------------------------------------------------------------------------------
    public static float get1dNoiseZeroToOne(int index, long seed) {
        return (float) (ONE_OVER_MAX_UINT * (double) squirrelNoise5(index, seed));
    }
//
////-----------------------------------------------------------------------------------------------
//    constexpr float Get2dNoiseZeroToOne( int indexX, int indexY, unsigned int seed )
//    {
//        constexpr double ONE_OVER_MAX_UINT = (1.0 / (double) 0xFFFFFFFF);
//        return (float)( ONE_OVER_MAX_UINT * (double) Get2dNoiseUint( indexX, indexY, seed ) );
//    }
//
////-----------------------------------------------------------------------------------------------
//    constexpr float Get3dNoiseZeroToOne( int indexX, int indexY, int indexZ, unsigned int seed )
//    {
//        constexpr double ONE_OVER_MAX_UINT = (1.0 / (double) 0xFFFFFFFF);
//        return (float)( ONE_OVER_MAX_UINT * (double) Get3dNoiseUint( indexX, indexY, indexZ, seed ) );
//    }
//
////-----------------------------------------------------------------------------------------------
//    constexpr float Get4dNoiseZeroToOne( int indexX, int indexY, int indexZ, int indexT, unsigned int seed )
//    {
//        constexpr double ONE_OVER_MAX_UINT = (1.0 / (double) 0xFFFFFFFF);
//        return (float)( ONE_OVER_MAX_UINT * (double) Get4dNoiseUint( indexX, indexY, indexZ, indexT, seed ) );
//    }
//
//
////-----------------------------------------------------------------------------------------------
//    constexpr float Get1dNoiseNegOneToOne( int index, unsigned int seed )
//    {
//        constexpr double ONE_OVER_MAX_INT = (1.0 / (double) 0x7FFFFFFF);
//        return (float)( ONE_OVER_MAX_INT * (double) (int) SquirrelNoise5( index, seed ) );
//    }
//
//
////-----------------------------------------------------------------------------------------------
//    constexpr float Get2dNoiseNegOneToOne( int indexX, int indexY, unsigned int seed )
//    {
//        constexpr double ONE_OVER_MAX_INT = (1.0 / (double) 0x7FFFFFFF);
//        return (float)( ONE_OVER_MAX_INT * (double) (int) Get2dNoiseUint( indexX, indexY, seed ) );
//    }
//
//
////-----------------------------------------------------------------------------------------------
//    constexpr float Get3dNoiseNegOneToOne( int indexX, int indexY, int indexZ, unsigned int seed )
//    {
//        constexpr double ONE_OVER_MAX_INT = (1.0 / (double) 0x7FFFFFFF);
//        return (float)( ONE_OVER_MAX_INT * (double) (int) Get3dNoiseUint( indexX, indexY, indexZ, seed ) );
//    }
//
//
////-----------------------------------------------------------------------------------------------
//    constexpr float Get4dNoiseNegOneToOne( int indexX, int indexY, int indexZ, int indexT, unsigned int seed )
//    {
//        constexpr double ONE_OVER_MAX_INT = (1.0 / (double) 0x7FFFFFFF);
//        return (float)( ONE_OVER_MAX_INT * (double) (int) Get4dNoiseUint( indexX, indexY, indexZ, indexT, seed ) );
//    }


}
