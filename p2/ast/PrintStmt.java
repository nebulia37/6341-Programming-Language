package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

public class PrintStmt extends Stmt {
    public final Expr expr;

    public PrintStmt(Expr e, Location loc) {
        super(loc);
        expr = e;
    }

    public void print(PrintStream ps, String indent) {
        ps.print(indent + "print ");
        expr.print(ps);
        ps.print(";");
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        expr.check(tables);
    }

    public void print(PrintStream ps) {
        print(ps, "");
    }
}
