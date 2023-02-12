package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

public class WhileStmt extends Stmt {
    public final CondExpr expr;
    public final Stmt body;

    public WhileStmt(CondExpr e, Stmt s, Location loc) {
        super(loc);
        expr = e;
        body = s;
    }

    public void print(PrintStream ps, String indent) {
        ps.print(indent + "while (");
        expr.print(ps);
        ps.print(")\n");
        body.print(ps, indent + "  ");
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        expr.check(tables);

        body.check(tables);
    }

    public void print(PrintStream ps) {
        print(ps, "");
    }
}
