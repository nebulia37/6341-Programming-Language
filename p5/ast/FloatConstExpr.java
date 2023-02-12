package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

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

    @Override
    public void evaluate(Map<String, AbstractVal> valueTable, Scanner s) {
        if (Double.compare(fval, 0.0) > 0) {
            value = AbstractVal.Pos;
        } else if (Double.compare(fval, 0.0) == 0) {
            value = AbstractVal.Zero;
        } else {
            value = AbstractVal.Neg;
        }
    }
}
