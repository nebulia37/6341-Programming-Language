package ast;
import java.io.PrintStream;
import java.util.Map;
import interpreter.Interpreter;

public class AssignStmt extends Stmt {
    public final String ident;
    public final Expr expr;
    public AssignStmt(String i, Expr e, Location loc) {
	super(loc);
	ident = i;
	expr = e;
    }
    public void print(PrintStream ps) { 
	ps.print(ident + " = ");
	expr.print(ps);
	ps.print(";");
    }
    public String check(Map<String, String> globalTable){
        String id_type = globalTable.get(ident);
        String expr_type = expr.check(globalTable);
   
        if(id_type==null){
             Interpreter.fatalError("No declaration of ident in assign: " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }else if(!id_type.equals(expr_type)){
             Interpreter.fatalError("Type of the variable on the left-hand side is not equal to that of the righ-hand side in assign: " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }

        return id_type;
    }
}
