package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class ReadIntExpr extends Expr {
    public ReadIntExpr(Location loc) {
        super(loc);
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        type = Type.INT;
    }

    @Override
    public void evaluate(Map<String, AbstractVal> valueTable, Scanner s) {
        value = AbstractVal.AnyInt;
    }

    public void print(PrintStream ps) {
        ps.print("readint");
    }
}
