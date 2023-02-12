package ast;
import java.io.PrintStream;
import java.util.Map;
import interpreter.Interpreter;

public class IdentExpr extends Expr {
    public final String ident; 
    public IdentExpr(String i, Location loc) {
	super(loc);
	ident = i;
    }
    public void print(PrintStream ps) {
	ps.print(ident);
    }

    public String check(Map<String, String> globalTable){
        String id_type = globalTable.get(ident);

        if(id_type==null){
            Interpreter.fatalError("Ident not declared: " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }
        
        return id_type;
    }
}
