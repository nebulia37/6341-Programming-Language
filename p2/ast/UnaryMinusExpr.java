package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

public class UnaryMinusExpr extends Expr {
    public final Expr expr;

    public UnaryMinusExpr(Expr e, Location loc) {
        super(loc);
        expr = e;
    }

    public void print(PrintStream ps) {
        ps.print("-(");
        expr.print(ps);
        ps.print(")");
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        expr.check(tables);
        type = expr.type;
    }
}
