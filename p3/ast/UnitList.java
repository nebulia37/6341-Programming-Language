package ast;
import java.io.*;
import java.util.*;
import java.io.PrintStream;

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
    
    public void check(ArrayList<Map<String, Type>> tables) {
        unit.check(tables);

        if (unitList != null)
            unitList.check(tables);
    }
    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        unit.evaluate(valueTable, s);

        if (unitList != null) {
            unitList.evaluate(valueTable, s);
        }
    }    
}
