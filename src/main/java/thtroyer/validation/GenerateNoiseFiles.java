package thtroyer.validation;

import thtroyer.squirrelnoise.SquirrelRandom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * This file generates a bunch of output as files, for analysis using sp800_22_tests.
 * Meant for testing/analysis/validation only.
 */
public class GenerateNoiseFiles {
    public static void main(String[] args) {
        generate_file_1024b();
        generate_file_1M();
        generate_file_1M_maxint_seed();
        generate_file_1M_maxuint_c_seed();
        generate_file_1M_0_seed();
        generate_file_1M_maxlong_seed();

        generate_files_50k_random_seeds();
        generate_files_50k_random_seeds_and_pos();
    }

    private static void generate_files_50k_random_seeds_and_pos() {
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            SquirrelRandom squirrelRandom = new SquirrelRandom(random.nextInt(), random.nextInt());

            File file = new File("data/random_50k_rand" + i + ".dat");
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                for (int j = 0; j < (1024 * 50); j += 4) {
                    outputStream.write(squirrelRandom.getNextBytes());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private static void generate_files_50k_random_seeds() {
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            SquirrelRandom squirrelRandom = new SquirrelRandom(random.nextInt());

            File file = new File("data/random_50k_rand" + i + ".dat");
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                for (int j = 0; j < (1024 * 50); j += 4) {
                    outputStream.write(squirrelRandom.getNextBytes());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private static void generate_file_1M() {
        SquirrelRandom squirrelRandom = new SquirrelRandom(0);

        File file = new File("data/random_1M.dat");
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            for (int i = 0; i < (1024 * 1024); i += 4) {
                outputStream.write(squirrelRandom.getNextBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void generate_file_1M_maxint_seed() {
        SquirrelRandom squirrelRandom = new SquirrelRandom(Integer.MAX_VALUE);

        File file = new File("data/random_1M_maxint.dat");
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            for (int i = 0; i < (1024 * 1024); i += 4) {
                outputStream.write(squirrelRandom.getNextBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void generate_file_1M_maxuint_c_seed() {
        SquirrelRandom squirrelRandom = new SquirrelRandom(Long.parseLong("ffffffff", 16));

        File file = new File("data/random_1M_maxuint_c.dat");
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            for (int i = 0; i < (1024 * 1024); i += 4) {
                outputStream.write(squirrelRandom.getNextBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void generate_file_1M_0_seed() {
        SquirrelRandom squirrelRandom = new SquirrelRandom(0);

        File file = new File("data/random_1M_0.dat");
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            for (int i = 0; i < (1024 * 1024); i += 4) {
                var bytes = squirrelRandom.getNextBytes();
                outputStream.write(bytes);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void generate_file_1M_maxlong_seed() {
        SquirrelRandom squirrelRandom = new SquirrelRandom(Long.MAX_VALUE);

        File file = new File("data/random_1M_longseed.dat");
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            for (int i = 0; i < (1024 * 1024); i += 4) {
                outputStream.write(squirrelRandom.getNextBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static SquirrelRandom generate_file_1024b() {
        File file = new File("data/random_1024b.dat");
        SquirrelRandom squirrelRandom = new SquirrelRandom(128383);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            for (int i = 0; i < 1024; i += 4) {
                outputStream.write(squirrelRandom.getNextBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return squirrelRandom;
    }
}
