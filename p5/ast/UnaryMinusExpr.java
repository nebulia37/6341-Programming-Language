package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

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

    @Override
    public void evaluate(Map<String, AbstractVal> valueTable, Scanner s) {
        expr.evaluate(valueTable, s);

        if (expr.value == AbstractVal.Pos) {
            value = AbstractVal.Neg;
        } else if (expr.value == AbstractVal.Neg) {
            value = AbstractVal.Pos;
        } else if (expr.value == AbstractVal.Zero) {
            value = AbstractVal.Zero;
        } else {
            value = AbstractVal.AnyInt;
        }
    }
}
