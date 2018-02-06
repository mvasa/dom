/**A mutable object that maintains state. Three components of state include textual, numerical, and logical data.
 * @version CS4250 Fall 2017 v2.4.3
 * @author Dr. Jody Paul and Manoj Vasa
 * */
public class Stateful{
	
	private String currentText;
	private Number currentNumber;
	private Boolean currentTruth;
	
	/**
	 * Constructs a stateful object using arbitrary values.
	 */
	Stateful()
	{
		currentText = " ";
		currentNumber = 0;
		currentTruth = true;
	}
	
	/**
	 * Constructs a stateful object using specified values.
	 * @param text the textual data
	 * @param number the numerical data
	 * @param truth the logical data
	 */
	Stateful(String text, Number number, Boolean truth)
	{
		currentText = text;
		currentNumber = number;
		currentTruth = truth;
	}
	
	/**
	 * Modifies the state by inverting the logical value. If the state value was true, it is changes to
	 * false. If the state value was false, it is changed to true.
	 */
	public void flip()
	{
		if(currentTruth == false)
		{
			currentTruth = true;
		}
		else
		{
			currentTruth = false;
		}
	}
	
	/**
	 * Accesses the numeric component of state.
	 * @return the numerical status
	 */
	public Number number()
	{
		
		return currentNumber;
	}
	
	/**
	 * Accesses the text component of state.
	 * @return the textual status
	 */
	public String text()
	{
		return currentText;
	}
	
	/**
	 * Renders object in the format: Stateful[truth, number, "text"]
	 */
	public String toString()
	{
		return "Stateful["+currentTruth+", " +currentNumber+", "+"\""+currentText+"\""+"]";
	}
	
	/**
	 * Accesses the logic component of state.
	 * @return the truth status
	 */
	public Boolean truth()
	{
		return currentTruth;
	}
}
