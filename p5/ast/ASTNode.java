package ast;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

abstract class ASTNode {
    public final Location loc;

    ASTNode(Location loc) {
        this.loc = loc;
    }

    public abstract void print(PrintStream ps);

    public String toString() {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        print(new PrintStream(b));
        return b.toString(java.nio.charset.StandardCharsets.UTF_8);
    }

    public abstract void check(ArrayList<Map<String, Type>> tables);

    public abstract void evaluate(Map<String, AbstractVal> valueTable, Scanner s);
}
