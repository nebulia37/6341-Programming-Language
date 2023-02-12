package ast;
import java.io.PrintStream;
import java.util.*;

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
    
    public void typeCheck(LinkedMap lm) throws Exception {
    	expr.typeCheck(lm);
    	super.type = expr.type;
    }
    
    public AS eval(Map<String, AS> st) {
    	AS val = expr.eval(st);
		switch (val) {
			case Pos:
				val = AS.Neg;
				break;
			case Neg:
				val = AS.Pos;
				break;
			case Zero:
			case AnyInt:
			case AnyFloat:
			default:
				break;
		}
    	return val;
	}
}
