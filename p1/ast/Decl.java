package ast;
import java.io.PrintStream;
import java.util.*;
import interpreter.Interpreter;

public class Decl extends Unit {
    public final VarDecl varDecl;
    public final Expr expr;
    public Decl(VarDecl d, Location loc) {
	super(loc);
	varDecl = d;
	expr = null;
    }
    public Decl(VarDecl d, Expr e, Location loc) {
	super(loc);
	varDecl = d;
	expr = e;
    }
    public void print(PrintStream ps) { 
	varDecl.print(ps); 
	if (expr != null) {
	    ps.print(" = ");
	    expr.print(ps);
	}
	ps.print(";");
    }

    public String check(Map<String, String> globalTable){
        String expr_type = "";
        if(expr!=null){
            expr_type = expr.check(globalTable);
        }
        String varDecl_type = varDecl.check(globalTable);
        if(expr_type != ""){
            if(!varDecl_type.equals(expr_type)){
           Interpreter.fatalError("type error in decl, var decl type is not equal to expr type: ", Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }
        }
        return varDecl_type;
    }
}
