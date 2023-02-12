package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

public class IntConstExpr extends Expr {
    public final Long ival;

    public IntConstExpr(Long i, Location loc) {
        super(loc);
        ival = i;
    }

    public void print(PrintStream ps) {
        ps.print(ival);
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        type = Type.INT;
    }
}
