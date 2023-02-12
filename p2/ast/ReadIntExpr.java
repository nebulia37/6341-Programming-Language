package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

public class ReadIntExpr extends Expr {
    public ReadIntExpr(Location loc) {
        super(loc);
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        type = Type.INT;
    }

    public void print(PrintStream ps) {
        ps.print("readint");
    }
}
