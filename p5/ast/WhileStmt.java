package ast;

import interpreter.Interpreter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WhileStmt extends Stmt {
    public final CondExpr expr;
    public final Stmt body;
    public ArrayList<Map<String, Type>> Types;

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

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        expr.check(tables);
        ArrayList<Map<String, Type>> typeTables = new ArrayList<>(tables);
        body.check(tables);
        Types = typeTables;
    }

    @Override
    public void evaluate(Map<String, AbstractVal> valueTable, Scanner s) {
        Map<String, AbstractVal> valueTableCopy_1 = new HashMap<>(valueTable);
        Map<String, AbstractVal> valueTableCopy_2 = new HashMap<>(valueTable);

        expr.evaluate(valueTable, s);
        if (expr.value == AbstractVal.True || expr.value == AbstractVal.AnyBool) {
            body.evaluate(valueTableCopy_1, s);
            AbstractStateMerge.mergeState(valueTable, valueTableCopy_1, Types);

            while (!valueTable.equals(valueTableCopy_2)) {
                valueTableCopy_2 = new HashMap<>(valueTable);
                body.evaluate(valueTableCopy_1, s);
                AbstractStateMerge.mergeState(valueTable, valueTableCopy_1, Types);
            }
        } else if (expr.value == AbstractVal.False && body != null) {
            Interpreter.fatalError("Dead code detected", Interpreter.EXIT_DEAD_CODE);
        }
    }

    public void print(PrintStream ps) {
        print(ps, "");
    }
}
