package ast;
import java.io.PrintStream;
import java.util.Map;
import interpreter.Interpreter;

public class IntVarDecl extends VarDecl {
    public IntVarDecl(String i, Location loc) {
	super(i,loc);
    }
    public void print(PrintStream ps) {
	ps.print("int " + ident);
    }

    public String check(Map<String, String> globalTable){
        String type = globalTable.get(ident);
        if(type!=null){
            Interpreter.fatalError("Double Decl: " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }

        globalTable.put(ident, "INT");
        return "INT";
    }
}
