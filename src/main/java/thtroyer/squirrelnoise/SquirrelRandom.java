package thtroyer.squirrelnoise;

public class SquirrelRandom {
    private final long seed;
    private int position;

    public SquirrelRandom(long seed) {
        position = 0;
        this.seed = seed;
    }

    public SquirrelRandom(long seed, int position) {
        this.position = position;
        this.seed = seed;
    }

    /**
     * Returns next signed int
     */
    public int getNextInt() {
        return (int)SquirrelNoise.squirrelNoise5(position++, seed);
    }

    /**
     * Returns next unsigned int (as a long)
     */
    public long getNextLong() {
        return SquirrelNoise.squirrelNoise5(position++, seed);
    }

    /**
     * Returns the next 4 bytes
     * @return byte[4]
     */
    public byte[] getNextBytes() {
        int value = getNextInt();
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }

}
