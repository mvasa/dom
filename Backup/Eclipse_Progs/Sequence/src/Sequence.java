/**
 * <p>Integer sequence generators. A sequence is defined by its initial values, and a depth. The initial values represent the first values returned by the sequence. The depth is how many previous items are added<br> 
 * together to calculate the next item in the sequence, after all initial values have been exhausted.</p>
 * 
 * Examples:<br>
 * 
 * <p>Sequence({4, 9, 12}, 0) produces [4, 9, 12, 0, 0, 0, 0, ...]<br>
 * Sequence({1}, 1) produces [1, 1, 1, 1, 1, 1, ...]<br>
 * Sequence({3, 8}, 1) produces [3, 8, 8, 8, 8, 8, 8, ...]<br>
 * Sequence({2, 4}, 2) produces [2, 4, 6, 10, 16, 26, 42, 68, ...]<br>
 * Sequence({2, 4, 6, 8}, 3) produces [2, 4, 6, 8, 18, 32, 58, 108, ...]</p>
 * 
 * <p>The behavior of sequences for which the depth is greater than number of the initial values is unspecified.</p>
 * 
 * @version 2.0.1.1
 * 
 * @author CS4250 (Fall 2017): Manoj Vasa
 *
 */
public class Sequence {
	
	private Integer []initialNums;
	private Integer []resultingNums;
	private int depthOfCalculation;
	private Integer []arbitrarySequence = new Integer[1];
	private int arbitraryDepth = 1;
	
	/**
	 * Constructs an arbitrary sequence.	
	 */
	Sequence()
	{
		arbitrarySequence[0] = 1;
		this.initialNums = arbitrarySequence;
		this.depthOfCalculation = arbitraryDepth;
		this.resultingNums = this.initialNums;
	}
	
	/**
	 *Constructs a specified sequence. 
	 * @param init the initial values of the sequence
	 * @param depth the number of historical values used in calculation
	 */
	Sequence(Integer []init, int depth)
	{
		this.initialNums = init;
		this.depthOfCalculation = depth;
		this.resultingNums = this.initialNums;
	}
	
	private int depthTracker = 1;
	
	/**
	 * Accesses the next number in the sequence.
	 * @return the next number in the sequence
	 */
	public Integer next()
	{
		int nextNum = 0;
		
		
		if (this.depthOfCalculation > 1 && this.depthOfCalculation <= this.initialNums.length)
		{
			if(depthTracker > this.initialNums.length)
			{	
				int i;
				for(i = 0; i < this.depthOfCalculation; i++)
				{
					nextNum += this.resultingNums[this.resultingNums.length - (i+1)];
				}
				
				//Building new array with the new next num
				Integer [] resultingNumsWithNextNum = new Integer [this.resultingNums.length + 1];
				int k;
				for(k = 0; k < this.resultingNums.length;k++)
				{
					resultingNumsWithNextNum[k] = this.resultingNums[k];
				}
				resultingNumsWithNextNum[resultingNumsWithNextNum.length - 1] = nextNum; 
				this.resultingNums = resultingNumsWithNextNum;
			}	
			else if(depthTracker <= this.initialNums.length)
			{
				nextNum = this.initialNums[depthTracker - 1];
				depthTracker++;
			}
		}
		
		else if(this.depthOfCalculation == 1)
		{
			if(depthTracker <= this.initialNums.length)
			{
				nextNum = this.initialNums[depthTracker - 1];
				depthTracker++;
			}
			else if(depthTracker > this.initialNums.length)
			{
				nextNum = this.resultingNums[this.initialNums.length - 1];
				
				//Building new array with the new next num
				Integer [] resultingNumsWithNextNum = new Integer [this.resultingNums.length + 1];
				int k;
				for(k = 0; k < this.resultingNums.length;k++)
				{
					resultingNumsWithNextNum[k] = this.resultingNums[k];
				}
				resultingNumsWithNextNum[resultingNumsWithNextNum.length - 1] = nextNum;
				this.resultingNums = resultingNumsWithNextNum;
			}
		}
		
		else if(this.depthOfCalculation == 0)
		{
			if(depthTracker <= this.initialNums.length)
			{
				nextNum = this.initialNums[depthTracker - 1];
				depthTracker++;
			}
			else if(depthTracker > this.initialNums.length)
			{
				nextNum = 0;
				
				//Building new array with the new next num
				Integer [] resultingNumsWithNextNum = new Integer [this.resultingNums.length + 1];
				int k;
				for(k = 0; k < this.resultingNums.length;k++)
				{
					resultingNumsWithNextNum[k] = this.resultingNums[k];
				}
				resultingNumsWithNextNum[resultingNumsWithNextNum.length - 1] = nextNum;
				this.resultingNums = resultingNumsWithNextNum;
			}
		}
		
		else if(this.depthOfCalculation > this.initialNums.length)
		{
			System.out.println("depth invalid");
		}
		return nextNum;
	}
}