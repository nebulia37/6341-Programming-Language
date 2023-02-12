package ast;
import java.io.PrintStream;
import java.util.Map;

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
    public void print(PrintStream ps) {
	unit.print(ps);

	ps.println();
	if (unitList != null)
	    unitList.print(ps);
    }

    public String check(Map<String, String> globalTable){
        unit.check(globalTable);

        if (unitList!=null){
            unitList.check(globalTable);
        }

        return null;
    }
}
