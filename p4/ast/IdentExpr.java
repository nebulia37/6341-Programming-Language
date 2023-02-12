package ast;
import java.io.PrintStream;
import java.util.*;
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
    
    public void typeCheck(LinkedMap lm) throws Exception {
		// Create a copy for traversing
		boolean found = false;
		LinkedMap curr = lm;
		
		while (curr != null)  {
			if (!curr.tbl.containsKey(ident)){
    			curr = curr.prev;
    		} else {
				found = true;
				super.type = curr.tbl.get(ident);				
				break;
			}
		}
		
		if (!found) 
			throw new Exception("\nident." + ident + " has not been declared");  	
		
    }
    
    public AS eval(Map<String, AS> st) {
    	AS val = AS.Error;
    	if (st.containsKey(ident)) {
    		val = st.get(ident);
    	} else
    		Interpreter.fatalError("Uninitialized var error: (" + ident + ") is uninitialized", Interpreter.EXIT_UNINITIALIZED_VAR_ERROR);
    	return val;
    }
}
