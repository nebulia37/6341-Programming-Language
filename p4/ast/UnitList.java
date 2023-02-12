package ast;
import java.io.PrintStream;
import java.util.*;

public class UnitList extends ASTNode {
    public final Unit unit;
    public final UnitList unitList; 
    public UnitList(Unit u, UnitList ul, Location loc) {
        super(loc);
	unit = u;
	unitList = ul;
    }
    public UnitList(Unit u, Location loc) { 
        super(loc);
	unit = u;
        unitList = null;
    }
    public void print(PrintStream ps, String indent) {
	unit.print(ps,indent);
	ps.println();
	if (unitList != null)
	    unitList.print(ps,indent);
    }
    public void print(PrintStream ps) { 
	print(ps,""); 
    }
    
    public void typeCheck(LinkedMap lm) throws Exception{
    	unit.typeCheck(lm);
    	lm.tbl.putAll(unit.decl);
    	if (unitList != null)
    		unitList.typeCheck(lm);    		
    }
    
    public void exec(Map<String, AS> st) {
    	unit.exec(st);
    	if (unitList != null)
    		unitList.exec(st);
    }
}
