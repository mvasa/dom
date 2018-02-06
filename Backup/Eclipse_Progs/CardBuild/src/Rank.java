import java.util.HashMap;
import java.util.Map;


public abstract class Rank
{
	private int value;
	private String symbol;

	public static Map<Integer, String> possibleRanks;

	public static void addRank(int value, String symbol)
	{

	}

	public static void setRank(int value, String symbol)
	{

	}

	public static void removeRank(int value)
	{

	}

	public void setValue(int value)
	{

	}

	public void setSymbol(String symbol)
	{

	}

	public static Map<Integer, String > getPossibleRanks()
	{
		return new HashMap<>();
	}

	public int getValue()
	{
		return 0;
	}

	public String getSymbol()
	{
		return "";
	}

	public boolean equals(Rank otherRank)
	{
		return value == otherRank.getValue();
	}

	public String toString()
	{
		return symbol;
	}
}