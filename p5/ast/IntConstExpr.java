package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

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

    @Override
    public void evaluate(Map<String, AbstractVal> valueTable, Scanner s) {
        if (ival.compareTo(0L) > 0) {
            value = AbstractVal.Pos;
        } else if (ival.compareTo(0L) == 0) {
            value = AbstractVal.Zero;
        } else {
            value = AbstractVal.Neg;
        }
    }
}
