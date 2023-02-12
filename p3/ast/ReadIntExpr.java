package ast;

import java.io.*;
import java.util.*;
import java.io.PrintStream;

public class ReadIntExpr extends Expr {
    public ReadIntExpr(Location loc) {
	super(loc);
    }
    public void print(PrintStream ps) {
	ps.print("readint");
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        type = Type.INT;
    }

    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        System.out.print("Read Int: ");
        try {
            value = s.nextLong();
        } catch (Exception e) {
            Interpreter.fatalError("Type mismatch from input, " + type + " required", Interpreter.EXIT_FAILED_STDIN_READ);
        }
    }
}
