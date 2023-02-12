package ast;
import java.io.PrintStream;
import java.util.Map;

public abstract class Expr extends ASTNode {
    public Expr(Location loc) {
	super(loc);
    }
    public abstract void print(PrintStream ps);
}
