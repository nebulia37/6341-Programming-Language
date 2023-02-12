package ast;
import java.io.PrintStream;
import java.util.*;
import interpreter.Interpreter;

public class ReadIntExpr extends Expr {
    public ReadIntExpr(Location loc) {
	super(loc);
	super.type = "int";
    }
    public void print(PrintStream ps) {
	ps.print("readint");
    }
    
    public void typeCheck(LinkedMap lm) {}
    
    public AS eval(Map<String, AS> st) {
    	return AS.AnyInt;
    }
}
