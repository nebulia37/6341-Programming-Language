package ast;
import java.io.PrintStream;
import java.util.*;

public class PrintStmt extends Stmt {
    public final Expr expr;
    public PrintStmt(Expr e, Location loc) {
	super(loc);
	expr = e;
    }
    public void print(PrintStream ps, String indent) { 
	ps.print(indent + "print ");
	expr.print(ps);
	ps.print(";");
    }
    public void print(PrintStream ps) { 
	print(ps,"");
    }
    
    public void typeCheck(LinkedMap lm) throws Exception {
    	expr.typeCheck(lm);
    }
    
    public void exec(Map<String, AS> st) {
    	System.out.println("PRINT: " +  expr.eval(st));
    }
}
