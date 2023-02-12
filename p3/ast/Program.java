package ast;
import java.io.*;
import java.util.*;
import java.io.PrintStream;

public class Program extends ASTNode {
    public final UnitList unitList;
    public Program(UnitList ul, Location loc) {
        super(loc);
        unitList = ul;
    }
    public void print(PrintStream ps) {
	unitList.print(ps,"");
    }
    
    public void check(ArrayList<Map<String, Type>> tables){
        Map<String, Type> tmp = new HashMap<>();
        tables.add(tmp);
        unitList.check(tables);
    }

    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        unitList.evaluate(valueTable, s);
    }
}
