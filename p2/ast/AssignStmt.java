package ast;

import interpreter.Interpreter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

public class AssignStmt extends Stmt {
    public final String ident;
    public final Expr expr;

    public AssignStmt(String i, Expr e, Location loc) {
        super(loc);
        ident = i;
        expr = e;
    }

    public void print(PrintStream ps, String indent) {
        ps.print(indent + ident + " = ");
        expr.print(ps);
        ps.print(";");
    }

    public void print(PrintStream ps) {
        print(ps, "");
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        Type id_type = null;
        for (int i = tables.size(); i > 0; i--) {
            Map<String, Type> table = tables.get(i - 1);
            id_type = table.get(ident);
            if (id_type != null) {
                break;
            }
        }

        expr.check(tables);

        if (id_type == null) {
            Interpreter.fatalError("No declaration in assign: " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        } else if (id_type != expr.type) {
            Interpreter.fatalError("Type error in assign: " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }
    }
}
