package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BlockStmt extends Stmt {
    public final UnitList block;

    public BlockStmt(UnitList b, Location loc) {
        super(loc);
        block = b;
    }

    public void print(PrintStream ps, String indent) {
        ps.print(indent + "{\n");
        block.print(ps, indent + "  ");
        ps.print(indent + "}");
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        Map<String, Type> temp = new HashMap<>();
        tables.add(temp);
        block.check(tables);
        tables.remove(tables.size() - 1);
    }

    @Override
    public void evaluate(Map<String, AbstractVal> valueTable, Scanner s) {
        block.evaluate(valueTable, s);
    }

    public void print(PrintStream ps) {
        print(ps, "");
    }
}
