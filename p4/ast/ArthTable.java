package ast;
import java.util.*;

public class ArthTable {
	/* Int Arithmetic Expression Tables */
	public static AS[][] PlusTableInt = 
	{
		{AS.Neg,    AS.Neg,    AS.AnyInt, AS.AnyInt},
		{AS.Neg,    AS.Zero,   AS.Pos,    AS.AnyInt},
		{AS.AnyInt, AS.Pos,    AS.Pos,    AS.AnyInt},
		{AS.AnyInt, AS.AnyInt, AS.AnyInt, AS.AnyInt}
	};

	public static AS[][] MinusTableInt = 
	{
		{AS.AnyInt, AS.Neg,    AS.Neg,    AS.AnyInt},
		{AS.Pos,    AS.Zero,   AS.Neg,    AS.AnyInt},
		{AS.Pos,    AS.Pos,    AS.AnyInt, AS.AnyInt},
		{AS.AnyInt, AS.AnyInt, AS.AnyInt, AS.AnyInt}
	};

	public static AS[][] TimesTableInt = 
	{
		{AS.Pos,    AS.Zero,   AS.Neg,    AS.AnyInt},
		{AS.Zero,   AS.Zero,   AS.Zero,   AS.Zero  },
		{AS.Neg   , AS.Zero,   AS.Pos,    AS.AnyInt},
		{AS.AnyInt, AS.Zero,   AS.AnyInt, AS.AnyInt}
	};

	public static AS[][] DivTableInt = 
	{
		{AS.AnyInt, AS.Error,  AS.AnyInt, AS.Error },
		{AS.Zero,   AS.Error,  AS.Zero,   AS.Error },
		{AS.AnyInt, AS.Error,  AS.AnyInt, AS.Error },
		{AS.AnyInt, AS.Error,  AS.AnyInt, AS.Error }
	};

	/* Float Arithmetic Expression Tables*/
	public static AS[][] PlusTableFloat = 
	{
		{AS.Neg,      AS.Neg,      AS.AnyFloat, AS.AnyFloat},
		{AS.Neg,      AS.Zero,     AS.Pos,      AS.AnyFloat},
		{AS.AnyFloat, AS.Pos,      AS.Pos,      AS.AnyFloat},
		{AS.AnyFloat, AS.AnyFloat, AS.AnyFloat, AS.AnyFloat}
	};

	public static AS[][] MinusTableFloat = 
	{
		{AS.AnyFloat, AS.Neg,      AS.Neg,      AS.AnyFloat},
		{AS.Pos,      AS.Zero,     AS.Neg,      AS.AnyFloat},
		{AS.Pos,      AS.Pos,      AS.AnyFloat, AS.AnyFloat},
		{AS.AnyFloat, AS.AnyFloat, AS.AnyFloat, AS.AnyFloat}
	};

	public static AS[][] TimesTableFloat = 
	{
		{AS.Pos,      AS.Zero,     AS.Neg,      AS.AnyFloat},
		{AS.Zero,     AS.Zero,     AS.Zero,     AS.Zero    },
		{AS.Neg   ,   AS.Zero,     AS.Pos,      AS.AnyFloat},
		{AS.AnyFloat, AS.Zero,     AS.AnyFloat, AS.AnyFloat}
	};

	public static AS[][] DivTableFloat = 
	{
		{AS.Pos,      AS.Error,    AS.Neg,      AS.Error   },
		{AS.Zero,     AS.Error,    AS.Zero,     AS.Error   },
		{AS.Neg,      AS.Error,    AS.Pos,      AS.Error   },
		{AS.AnyFloat, AS.Error,    AS.AnyFloat, AS.Error   }
	};
	
	public static AS[][] MergeTableInt = 
	{
		{AS.Neg,    AS.AnyInt, AS.AnyInt, AS.AnyInt},
		{AS.AnyInt, AS.Zero,   AS.AnyInt, AS.AnyInt},
		{AS.AnyInt, AS.AnyInt, AS.Pos,    AS.AnyInt},
		{AS.AnyInt, AS.AnyInt, AS.AnyInt, AS.AnyInt}
	};
	
	public static AS[][] MergeTableFloat = 
	{
		{AS.Neg,      AS.AnyFloat, AS.AnyFloat, AS.AnyFloat},
		{AS.AnyFloat, AS.Zero,     AS.AnyFloat, AS.AnyFloat},
		{AS.AnyFloat, AS.AnyFloat, AS.Pos,      AS.AnyFloat},
		{AS.AnyFloat, AS.AnyFloat, AS.AnyFloat, AS.AnyFloat}
	};
}
