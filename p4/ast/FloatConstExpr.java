package ast;
import java.io.PrintStream;
import java.util.*;

public class FloatConstExpr extends Expr {
    public final Double fval; 
    public FloatConstExpr(Double f, Location loc) {
	super(loc);
	fval = f;
	super.type = "float";
    }
    public void print(PrintStream ps) {
	ps.print(fval);
    }
    
    public void typeCheck(LinkedMap lm) {}
    
    public AS eval(Map<String, AS> st) {
    	AS val;
    	int isZero = Double.compare(fval, 0.0); 
    	if (isZero > 0)
    		val = AS.Pos;
    	else if (isZero < 0)
    		val = AS.Neg;
    	else
    		val = AS.Zero;
    	return val;
    }
}
