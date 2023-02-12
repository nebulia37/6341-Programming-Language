package ast;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

abstract class ASTNode {
    public final Location loc;

    ASTNode(Location loc) {
        this.loc = loc;
    }

    public abstract void print(PrintStream ps);

    public String toString() {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        print(new PrintStream(b));
        return new String(b.toByteArray(), java.nio.charset.StandardCharsets.UTF_8);
    }

    public abstract void check(ArrayList<Map<String, Type>> tables);
}
