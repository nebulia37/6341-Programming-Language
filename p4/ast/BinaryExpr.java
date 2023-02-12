package ast;
import java.io.PrintStream;
import java.util.*;
import interpreter.Interpreter;

public class BinaryExpr extends Expr {
    public static final int PLUS = 1;
    public static final int MINUS = 2;
    public static final int TIMES = 3;
    public static final int DIV = 4;
    public final Expr expr1, expr2;
    public final int op;
    public BinaryExpr(Expr e1, int oper, Expr e2, Location loc) {
	super(loc);
	expr1 = e1; 
	expr2 = e2;
	op = oper;
    }
    public void print(PrintStream ps) {
	ps.print("(");
	expr1.print(ps);
	switch (op) {
	case PLUS: ps.print("+"); break;
	case MINUS: ps.print("-"); break;
	case TIMES: ps.print("*"); break;
	case DIV: ps.print("/"); break;
	}
	expr2.print(ps);
	ps.print(")");
    }
    
    public void typeCheck(LinkedMap lm) throws Exception {
    	expr1.typeCheck(lm);
    	expr2.typeCheck(lm);
    	if (!expr1.type.equals(expr2.type))
    		throw new Exception("\n<expr1> <binary_oper> <expr2>: expr1.type is not equal to expr2.type");  	
    	super.type = expr1.type;
    }
    
    public AS eval(Map<String, AS> st) {
 		AS as1 = expr1.eval(st);
 		AS as2 = expr2.eval(st);
 		AS result = AS.Error;
 		/* Check whether as1 and as2 are int or float */
 		if (expr1.type.equals("int")) {
			switch (op) 
	 		{
	 			case PLUS:
	 				result = ArthTable.PlusTableInt[as1.v()][as2.v()];
	 				break;
	 			case MINUS:
	 				result = ArthTable.MinusTableInt[as1.v()][as2.v()];
	 				break;
	 			case TIMES:
	 				result = ArthTable.TimesTableInt[as1.v()][as2.v()];
	 				break;
	 			case DIV:
	 				result = ArthTable.DivTableInt[as1.v()][as2.v()];
	 				if(result == AS.Error)
	 					Interpreter.fatalError("Divide by zero error", Interpreter.EXIT_DIV_BY_ZERO_ERROR);
	 			default:
	 				break;
	 		}
 		} else {
 			int as1val = as1.v();
 			int as2val = as2.v();
 			if (as1 == AS.AnyFloat) as1val--;
 			if (as2 == AS.AnyFloat) as2val--;	
			switch (op) 
	 		{
	 			case PLUS:
	 				result = ArthTable.PlusTableFloat[as1val][as2val];
	 				break;
	 			case MINUS:
	 				result = ArthTable.MinusTableFloat[as1val][as2val];
	 				break;
	 			case TIMES:
	 				result = ArthTable.TimesTableFloat[as1val][as2val];
	 				break;
	 			case DIV:
	 				result = ArthTable.DivTableFloat[as1val][as2val];
	 				if(result == AS.Error)
	 					Interpreter.fatalError("Divide by zero error", Interpreter.EXIT_DIV_BY_ZERO_ERROR);
	 			default:
	 				break;
 			}
		}
		return result;
	}
}
