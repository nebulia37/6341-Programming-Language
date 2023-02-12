package ast;
import java.io.PrintStream;
import java.util.*;

public class AssignStmt extends Stmt {
    public final String ident;
    public final Expr expr;
    public AssignStmt(String i, Expr e, Location loc) {
	super(loc);
	ident = i;
	expr = e;
    }
    public void print(PrintStream ps, String indent) { 
	ps.print(indent + ident + " = ");
	expr.print(ps);
	ps.print(";");
    }
    public void print(PrintStream ps) {     
	print(ps,"");
    }
    
    public void typeCheck(LinkedMap lm) throws Exception{
    	String identType = "";
    	boolean found = false;
    	LinkedMap curr = lm;
    	
    	while (curr != null) {
    		if (!curr.tbl.containsKey(ident)) {
    			curr = curr.prev;
    		} else {
    			found = true;
    			identType = curr.tbl.get(ident);
    			break;
    		}
    	}
    	
    	if(!found)
    		throw new Exception("\nident." + ident + " has not been declared");
    		
    	expr.typeCheck(lm);   
    		
    	if(!expr.type.equals(identType))
    		throw new Exception("\nident." + ident + " type is not equal the expr.type");
    }
    
    public void exec(Map<String, AS> st) {
    	AS val = expr.eval(st);
    	st.put(ident, val);
    }
}
