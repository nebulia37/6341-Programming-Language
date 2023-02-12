package ast;

import interpreter.Interpreter;
import java.io.*;
import java.util.*;
import java.io.PrintStream;

public class Decl extends Unit {
    public final VarDecl varDecl;
    public final Expr expr;
    public Decl(VarDecl d, Location loc) {
	super(loc);
	varDecl = d;
	expr = null;
    }
    public Decl(VarDecl d, Expr e, Location loc) {
	super(loc);
	varDecl = d;
	expr = e;
    }
    public void print(PrintStream ps, String indent) { 
	ps.print(indent);
	varDecl.print(ps); 
	if (expr != null) {
	    ps.print(" = ");
	    expr.print(ps);
	}
	ps.print(";");
    }
    
    
    public void check(ArrayList<Map<String, Type>> tables) {
        varDecl.check(tables);

        if (expr != null) {
            expr.check(tables);

            if (varDecl.type != expr.type) {
                Interpreter.fatalError("Type error in declaration: " + varDecl.type + " = " + expr.type, Interpreter.EXIT_STATIC_CHECKING_ERROR);
            }
        }

        tables.get(tables.size() - 1).put(varDecl.ident, varDecl.type);
    }

    public void print(PrintStream ps) {
	print(ps,"");
    }
    
    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        varDecl.evaluate(valueTable, s);

        if (expr != null) {
            expr.evaluate(valueTable, s);
            valueTable.put(varDecl.ident, expr.value);
        } else {
            valueTable.put(varDecl.ident, null);
        }
    }
}
