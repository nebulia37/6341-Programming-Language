package ast;

import java.io.*;
import java.util.*;
import java.io.PrintStream;

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
    public void print(PrintStream ps) { 
	print(ps,"");
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        expr.check(tables);
    }

    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        expr.evaluate(valueTable, s);
        System.out.println(expr.value);
    }
}
