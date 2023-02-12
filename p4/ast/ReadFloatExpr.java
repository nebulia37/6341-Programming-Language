package ast;
import java.io.PrintStream;
import java.util.*;
import interpreter.Interpreter;

public class ReadFloatExpr extends Expr {
    public ReadFloatExpr(Location loc) {
	super(loc);
	super.type = "float";
    }
    public void print(PrintStream ps) {
	ps.print("readfloat");
    }
    
    public void typeCheck(LinkedMap lm) {}
    
    public AS eval(Map<String, AS> st) {
    	return AS.AnyFloat;
    }
}
