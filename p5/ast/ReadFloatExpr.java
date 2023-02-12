package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class ReadFloatExpr extends Expr {
    public ReadFloatExpr(Location loc) {
        super(loc);
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        type = Type.FLOAT;
    }

    @Override
    public void evaluate(Map<String, AbstractVal> valueTable, Scanner s) {
        value = AbstractVal.AnyFloat;
    }

    public void print(PrintStream ps) {
        ps.print("readfloat");
    }
}
