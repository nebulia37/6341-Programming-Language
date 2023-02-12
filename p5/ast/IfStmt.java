package ast;

import interpreter.Interpreter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IfStmt extends Stmt {
    public final CondExpr expr;
    public final Stmt thenstmt, elsestmt;
    public ArrayList<Map<String, Type>> thenAbstractTypes = null;

    public IfStmt(CondExpr e, Stmt s, Location loc) {
        super(loc);
        expr = e;
        thenstmt = s;
        elsestmt = null;
    }

    public IfStmt(CondExpr e, Stmt s1, Stmt s2, Location loc) {
        super(loc);
        expr = e;
        thenstmt = s1;
        elsestmt = s2;
    }

    public void print(PrintStream ps, String indent) {
        ps.print(indent + "if (");
        expr.print(ps);
        ps.print(")\n");
        thenstmt.print(ps, indent + "  ");
        if (elsestmt != null) {
            ps.print("\n" + indent + "else\n");
            elsestmt.print(ps, indent + "  ");
        }
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        expr.check(tables);
        expr.type = Type.BOOL;

        ArrayList<Map<String, Type>> thenstmtTypes = new ArrayList<>(tables);

        thenstmt.check(tables);

        thenAbstractTypes = thenstmtTypes;
        if (elsestmt != null) {
            ArrayList<Map<String, Type>> elsestmtTypes = new ArrayList<>(tables);
            elsestmt.check(elsestmtTypes);
        }
    }

    @Override
    public void evaluate(Map<String, AbstractVal> valueTable, Scanner s) {
        Map<String, AbstractVal> valueTableCopy = new HashMap<>(valueTable);
        expr.evaluate(valueTable, s);

        if ((expr.value == AbstractVal.True && elsestmt != null) ||
                (expr.value == AbstractVal.False && thenstmt != null)) {
            Interpreter.fatalError("Dead code detected", Interpreter.EXIT_DEAD_CODE);
        }

        if (expr.value == AbstractVal.True) {
            thenstmt.evaluate(valueTableCopy, s);
        } else if (elsestmt != null) {
            elsestmt.evaluate(valueTable, s);
        }

        AbstractStateMerge.mergeState(valueTable, valueTableCopy, thenAbstractTypes);
    }

    public void print(PrintStream ps) {
        print(ps, "");
    }
}
