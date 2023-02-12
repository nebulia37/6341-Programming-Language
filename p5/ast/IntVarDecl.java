package ast;

import interpreter.Interpreter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class IntVarDecl extends VarDecl {
    public IntVarDecl(String i, Location loc) {
        super(i, loc);
    }

    public void print(PrintStream ps) {
        ps.print("int " + ident);
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        Map<String, Type> declTable = tables.get(tables.size() - 1);
        type = Type.INT;

        if (declTable.containsKey(ident)) {
            Interpreter.fatalError("Double decl: " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }
    }

    @Override
    public void evaluate(Map<String, AbstractVal> valueTable, Scanner s) {
        assert true;
    }
}
