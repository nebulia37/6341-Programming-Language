package ast;

import java.io.*;
import java.util.*;
import java.io.PrintStream;

public class ReadFloatExpr extends Expr {
    public ReadFloatExpr(Location loc) {
	super(loc);
    }
    public void print(PrintStream ps) {
	ps.print("readfloat");
    }
    
    public void check(ArrayList<Map<String, Type>> tables) {
        type = Type.FLOAT;
    }
    
    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        System.out.print("Read Float: ");
        try {
            value = s.nextDouble();
        } catch (Exception e) {
            Interpreter.fatalError("Type mismatch from input, " + type + " required", Interpreter.EXIT_FAILED_STDIN_READ);
        }
    }
}
