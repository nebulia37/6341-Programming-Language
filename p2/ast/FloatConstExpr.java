package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

public class FloatConstExpr extends Expr {
    public final Double fval;

    public FloatConstExpr(Double f, Location loc) {
        super(loc);
        fval = f;
    }

    public void print(PrintStream ps) {
        ps.print(fval);
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        type = Type.FLOAT;
    }
}
