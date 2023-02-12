package ast;
import java.io.PrintStream;
import java.util.Map;

import java.util.List;
abstract class ASTNode {
    public final Location loc;
    ASTNode(Location loc) { this.loc = loc; }
    public abstract void print(PrintStream ps);
    public abstract String check(Map<String, String> table);
}
