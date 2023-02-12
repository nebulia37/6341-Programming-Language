package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program extends ASTNode {
    public final UnitList unitList;

    public Program(UnitList ul, Location loc) {
        super(loc);
        unitList = ul;
    }

    public void print(PrintStream ps) {
        unitList.print(ps, "");
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        Map<String, Type> temp = new HashMap<>();
        tables.add(temp);
        unitList.check(tables);
    }

    public void evaluate(Map<String, AbstractVal> valueTable, Scanner s) {
        unitList.evaluate(valueTable, s);
    }
}
