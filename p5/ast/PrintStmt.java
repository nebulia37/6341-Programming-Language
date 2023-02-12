package ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class PrintStmt extends Stmt {
    public final Expr expr;

    public PrintStmt(Expr e, Location loc) {
        super(loc);
        expr = e;
    }

    public void print(PrintStream ps, String indent) {
        ps.print(indent + "print ");
        expr.print(ps);
        ps.print(";");
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        expr.check(tables);
    }

    @Override
    public void evaluate(Map<String, AbstractVal> valueTable, Scanner s) {
        expr.evaluate(valueTable, s);
        System.out.println(expr.value);
    }

    public void print(PrintStream ps) {
        print(ps, "");
    }
}
