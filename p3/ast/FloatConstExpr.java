package ast;

import java.io.*;
import java.util.*;
import java.io.PrintStream;

public class FloatConstExpr extends Expr {
    public final Double fval; 
    public FloatConstExpr(Double f, Location loc) {
	super(loc);
	fval = f;
    }
    public void print(PrintStream ps) {
	ps.print(fval);
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        type = Type.FLOAT;
    }
    
    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        value = fval;
    }
}
