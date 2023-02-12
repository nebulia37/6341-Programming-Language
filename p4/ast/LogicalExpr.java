package ast;
import java.io.PrintStream;
import java.util.*;

public class LogicalExpr extends CondExpr {
    public static final int AND = 1;
    public static final int OR = 2;
    public static final int NOT = 3;
    public final CondExpr expr1, expr2;
    public final int op;
    public LogicalExpr(CondExpr e1, int oper, CondExpr e2, Location loc) {
	super(loc);
	expr1 = e1; 
	expr2 = e2;
	op = oper;
    }
    public LogicalExpr(CondExpr e1, int oper, Location loc) {
	this(e1,oper,null,loc); // used for NOT
    }
    public void print(PrintStream ps) {
	if (op == NOT) {
	    ps.print("!(");
	    expr1.print(ps);
	    ps.print(")");
	    return;
	}
	ps.print("(");
	expr1.print(ps);
	switch (op) { 
	case AND: ps.print("&&"); break;
	case OR: ps.print("||"); break;
	}
	expr2.print(ps);
	ps.print(")");
    }
    
    public void typeCheck(LinkedMap lm) {}
    
    public boolean eval(Map<String, AS> st) {
    	boolean val = true;
    	/*
    	boolean v1 = expr1.eval(st);
    	switch (op) {
    		case AND:
    			if (v1 == false) {
    				val = false;
    				break;
    			} else {
    				val = v1 && expr2.eval(st);
    				break;
    			}
    		case OR:
    			if (v1 == true) {
    				val = true;
    				break;
    			} else {
    				val = v1 || expr2.eval(st);
    				break;
    			}
    		case NOT:
    			val = !v1;
    		default:
    			break;
    	}
    	*/
    	return val;
    }
}
