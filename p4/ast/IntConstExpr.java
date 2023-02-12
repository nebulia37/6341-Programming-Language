package ast;
import java.io.PrintStream;
import java.util.*;

public class IntConstExpr extends Expr {
    public final Long ival; 
    public IntConstExpr(Long i, Location loc) {
	super(loc);
	ival = i;
	super.type = "int";
    }
    public void print(PrintStream ps) {
	ps.print(ival);
    }
    
    public void typeCheck(LinkedMap lm) {}
    
    public AS eval(Map<String, AS> st){
    	AS val;
    	Long isZero = ival - 0; 
    	if (isZero > 0)
    		val = AS.Pos;
    	else if (isZero < 0)
    		val = AS.Neg;
    	else
    		val = AS.Zero;
    	return val;
    }
    
}
