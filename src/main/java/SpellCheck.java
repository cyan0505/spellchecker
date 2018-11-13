import java.io.IOException;
import java.io.PrintStream;

public class SpellCheck {
    public SpellCheck() {
    }

    public static void main(String[] var0) {
        if (var0.length == 0) {
            showUsageMessage();
        } else {
            String var1 = var0[var0.length - 1];
            String var2 = "wordlist.txt";
            Object var3 = new LousyStringHasher();
            PrintStream var4 = System.out;
            boolean var5 = false;

            for(int var6 = 0; var6 < var0.length - 1; ++var6) {
                if (var0[var6].equals("-degenerate")) {
                    var3 = new DegenerateStringHasher();
                } else if (var0[var6].equals("-lousy")) {
                    var3 = new LousyStringHasher();
                } else if (var0[var6].equals("-better")) {
                    var3 = new BetterStringHasher();
                } else if (var0[var6].equals("-quiet")) {
                    var4 = new PrintStream(new NullOutputStream());
                    var5 = true;
                } else if (var0[var6].equals("-wordlist")) {
                    ++var6;
                    if (var6 >= var0.length - 1) {
                        showUsageMessage();
                        return;
                    }

                    var2 = var0[var6];
                }
            }

            if (var0[var0.length - 1].charAt(0) == '-') {
                showUsageMessage();
            } else {
                try {
                    long var7 = System.currentTimeMillis();
                    (new Checker()).check(var1, var2, (StringHasher)var3, var4);
                    long var9 = System.currentTimeMillis();
                    if (var5) {
                        System.out.println("Checker ran in " + (var9 - var7) + "ms");
                    }
                } catch (IOException var11) {
                    var11.printStackTrace();
                }
            }
        }
    }

    private static void showUsageMessage() {
        System.out.println("Usage: java SpellCheck [options] inputFilename");
        System.out.println();
        System.out.println("    options");
        System.out.println("    -------");
        System.out.println("    -degenerate");
        System.out.println("        runs the spell checker with the DegenerateStringHasher algorithm");
        System.out.println();
        System.out.println("    -lousy");
        System.out.println("        runs the spell checker with a LousyStringHasher");
        System.out.println();
        System.out.println("    -better");
        System.out.println("        runs the spell checker with a better word hashing algorithm");
        System.out.println();
        System.out.println("    -quiet");
        System.out.println("        runs the spell checker without any output, reporting the total time");
        System.out.println("        taken to load the dictionary and perform the spell check");
        System.out.println();
        System.out.println("    -wordlist wordlistFilename");
        System.out.println("        runs the spell checker using the wordlist specified, rather than");
        System.out.println("        the default (wordlist.txt)");
        System.out.println();
        System.out.println("    example");
        System.out.println("    -------");
        System.out.println("    java SpellCheck -wordlist wordlist.txt -better -quiet big-test.txt");
    }
}