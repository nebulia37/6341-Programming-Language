package ast;

import interpreter.Interpreter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

public class IdentExpr extends Expr {
    public final String ident;

    public IdentExpr(String i, Location loc) {
        super(loc);
        ident = i;
    }

    public void print(PrintStream ps) {
        ps.print(ident);
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        for (int i = tables.size(); i > 0; i--) {
            Map<String, Type> table = tables.get(i - 1);
            type = table.get(ident);
            if (type != null) {
                break;
            }
        }

        if (type == null) {
            Interpreter.fatalError("Identifier was not declared: " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }
    }
}
