package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

public class ReadFloatExpr extends Expr {
    public ReadFloatExpr(Location loc) {
        super(loc);
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        type = Type.FLOAT;
    }

    public void print(PrintStream ps) {
        ps.print("readfloat");
    }
}
