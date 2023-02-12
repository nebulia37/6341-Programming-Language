package ast;
import java.io.PrintStream;
import java.util.*;

public class CompExpr extends CondExpr {
    public static final int GE = 1;
    public static final int GT = 2;
    public static final int LE = 3;
    public static final int LT = 4;
    public static final int EQ = 5;
    public static final int NE = 6;
    public final Expr expr1, expr2;
    public final int op;
    public CompExpr(Expr e1, int oper, Expr e2, Location loc) {
	super(loc);
	expr1 = e1; 
	expr2 = e2;
	op = oper;
    }
    public void print(PrintStream ps) {
	ps.print("(");
	expr1.print(ps);
	switch (op) { 
	case GE: ps.print(">="); break;
	case GT: ps.print(">"); break;
	case LE: ps.print("<="); break;
	case LT: ps.print("<"); break;
	case EQ: ps.print("=="); break;
	case NE: ps.print("!="); break;
	}
	expr2.print(ps);
	ps.print(")");
    }
    
    public void typeCheck(LinkedMap lm) throws Exception {
    	expr1.typeCheck(lm);
    	expr2.typeCheck(lm);
    	if (!expr1.type.equals(expr2.type))
    		throw new Exception("\n<expr1> <comp_oper> <expr2>: expr1.type is not equal to expr2.type");  	
    }
    
    public boolean eval(Map<String, AS> st) {
    	boolean val = true;
    	/*
    	Number v1 = expr1.eval(st);
    	Number v2 = expr2.eval(st);
    	if (v1 instanceof Long) {
    		long val1 = v1.longValue();
    		long val2 = v2.longValue();
			switch (op) {
				case GE:
					val = val1 >= val2;
					break;
				case GT:
					val = val1 > val2;
					break;
				case LE:
					val = val1 <= val2;
					break;
				case LT:
					val = val1 < val2;
					break;
				case EQ:
					val = val1 == val2;
					break;
				case NE:
					val = val1 != val2;
					break;
				default:
					break;
			}
    	} else {
    		double val1 = v1.doubleValue();
    		double val2 = v2.doubleValue();
			switch (op) {
				case GE:
					val = val1 >= val2;
					break;
				case GT:
					val = val1 > val2;
					break;
				case LE:
					val = val1 <= val2;
					break;
				case LT:
					val = val1 < val2;
					break;
				case EQ:
					val = Double.compare(val1, val2) == 0;
					break;
				case NE:
					val = Double.compare(val1, val2) != 0;
					break;
				default:
					break;
			}
    	}*/
    	return val;
    	
    }
}
