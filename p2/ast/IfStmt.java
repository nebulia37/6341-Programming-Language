package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

public class IfStmt extends Stmt {
    public final CondExpr expr;
    public final Stmt thenstmt, elsestmt;

    public IfStmt(CondExpr e, Stmt s, Location loc) {
        super(loc);
        expr = e;
        thenstmt = s;
        elsestmt = null;
    }

    public IfStmt(CondExpr e, Stmt s1, Stmt s2, Location loc) {
        super(loc);
        expr = e;
        thenstmt = s1;
        elsestmt = s2;
    }

    public void print(PrintStream ps, String indent) {
        ps.print(indent + "if (");
        expr.print(ps);
        ps.print(")\n");
        thenstmt.print(ps, indent + "  ");
        if (elsestmt != null) {
            ps.print("\n" + indent + "else\n");
            elsestmt.print(ps, indent + "  ");
        }
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        expr.check(tables);

        thenstmt.check(tables);

        if (elsestmt != null) {
            elsestmt.check(tables);
        }
    }

    public void print(PrintStream ps) {
        print(ps, "");
    }
}
