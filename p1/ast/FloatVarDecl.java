package ast;
import java.io.PrintStream;
import java.util.Map;
import interpreter.Interpreter;


public class FloatVarDecl extends VarDecl {
    public FloatVarDecl(String i, Location loc) {
	super(i,loc);
    }
    public void print(PrintStream ps) {
	ps.print("float " + ident);
    }
    
    public String check(Map<String, String> globalTable){
        String type = globalTable.get(ident);
        if(type!=null){
            Interpreter.fatalError("Double Decl: " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }

        globalTable.put(ident, "FLOAT");
        return "FLOAT";
    }
}
