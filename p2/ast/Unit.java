package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

public abstract class Unit extends ASTNode {
    public Unit(Location loc) {
        super(loc);
    }

    public abstract void print(PrintStream ps, String ident);

    public abstract void check(ArrayList<Map<String, Type>> tables);
}
