package ast;

import java.io.*;
import java.util.*;
import java.io.PrintStream;

public class UnaryMinusExpr extends Expr {
    public final Expr expr;
    public UnaryMinusExpr(Expr e, Location loc) {
	super(loc);
	expr = e; 
    }
    public void print(PrintStream ps) {
	ps.print("-(");
	expr.print(ps);
	ps.print(")");
    }
  
    public void check(ArrayList<Map<String, Type>> tables) {
        expr.check(tables);
        type = expr.type;
    }   
    
    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        expr.evaluate(valueTable, s);

        if (expr.value instanceof Long) {
            value = -((Long) expr.value);
        } else if (expr.value instanceof Double) {
            value = -((Double) expr.value);
        }
    }
}
