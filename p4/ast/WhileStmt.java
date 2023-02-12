package ast;
import java.io.PrintStream;
import java.util.*;
import interpreter.Interpreter;

public class WhileStmt extends Stmt {
    public final CondExpr expr;
    public final Stmt body;
    public WhileStmt(CondExpr e, Stmt s, Location loc) {
	super(loc);
	expr = e;
	body = s;
    }
    public void print(PrintStream ps, String indent) { 
	ps.print(indent + "while (");
	expr.print(ps);
	ps.print(")\n");
	body.print(ps, indent + "  ");
    }
    public void print(PrintStream ps) {     
	print(ps,"");
    }
    
    public void typeCheck(LinkedMap lm) throws Exception {
    	expr.typeCheck(lm);
    	body.typeCheck(new LinkedMap(lm));	
    }
    
    public void exec(Map<String, AS> st) {
    	expr.eval(st);
    	// copy st
		int isMerged = 0;
		Map<String, AS> st_new = new HashMap<>();
    	Map<String, AS> result = new HashMap<>();
		result.putAll(st);
		st_new.putAll(st);
    	while (isMerged == 0) {
    		body.exec(result);
    		// st_new = result merge w/ st_new
    		for (String var: result.keySet()) {
				AS as2 = result.get(var);
				int v2 = as2.v();
				if (st_new.containsKey(var)) {
					AS as1 = st_new.get(var);
					int v1 = as1.v();
					if (Interpreter.lm.get(var).equals("int")) {
						st_new.put(var, ArthTable.MergeTableInt[v1][v2]);
					} else {
						if (as1 == AS.AnyFloat) v1--;
						if (as2 == AS.AnyFloat) v2--;
						st_new.put(var, ArthTable.MergeTableFloat[v1][v2]);
					}
				} else {
					st_new.put(var, as2);
				}
			}
			if (st_new.equals(st)) isMerged = 1;
			else st.putAll(st_new);
    	}
    }
}
