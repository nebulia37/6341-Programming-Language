package ast;
import java.io.PrintStream;
import java.util.Map;

public class PrintStmt extends Stmt {
    public final Expr expr;
    public PrintStmt(Expr e, Location loc) {
	super(loc);
	expr = e;
    }
    public void print(PrintStream ps) { 
	ps.print("print ");
	expr.print(ps);
	ps.print(";");
    }
    
    public String check(Map<String, String> globalTable){
        String type = expr.check(globalTable);
        return type;
    }
}
