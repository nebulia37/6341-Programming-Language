package ast;
import java.io.PrintStream;
import java.util.Map;
import interpreter.Interpreter;

public class PlusExpr extends Expr {
    public final Expr expr1, expr2;
    public PlusExpr(Expr e1, Expr e2, Location loc) {
	super(loc);
	expr1 = e1; 
	expr2 = e2;
    }
    public void print(PrintStream ps) {
	ps.print("(");
	expr1.print(ps);
	ps.print("+");
	expr2.print(ps);
	ps.print(")");
    }

    public String check(Map<String, String> globalTable){
        String type_expr1 = expr1.check(globalTable);
        String type_expr2 = expr2.check(globalTable);
        
        if(!type_expr1.equals(type_expr2)){
            Interpreter.fatalError(" Operands of + are not of the same type: ", Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }
         
        return type_expr1;
    }
}
