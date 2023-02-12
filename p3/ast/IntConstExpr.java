package ast;

import java.io.*;
import java.util.*;
import java.io.PrintStream;

public class IntConstExpr extends Expr {
    public final Long ival; 
    public IntConstExpr(Long i, Location loc) {
	super(loc);
	ival = i;
    }
    public void print(PrintStream ps) {
	ps.print(ival);
    }
   
    public void check(ArrayList<Map<String, Type>> tables) {
        type = Type.INT;
    }

    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        value = ival;
    }
}
