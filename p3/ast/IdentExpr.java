package ast;

import java.io.*;
import java.util.*;
import java.io.PrintStream;
import interpreter.Interpreter;

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

    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        value = valueTable.get(ident);

        if (value == null) {
            Interpreter.fatalError("Use of uninitialized variable: " + ident, Interpreter.EXIT_UNINITIALIZED_VAR_ERROR);
        }
    }
}
