/**
 * Resource-locking device with circular bit storage.
 * @version 2.1.4
 * @author Jody Paul
 *
 */
public class Device {
	public static void main(String [] args)
	{
		Device dev = new Device();
		CharSequence pattern = "";
		dev.poke(pattern);
		dev.spin();
		dev.peek(pattern);
		String test = dev.toString();
		System.out.println(test + "\n\n\nsuccess");
	}
	/**
	 * 	Default number of bits to reveal per peek.
	 * @see<a href = "http://jodypaul.com/cs/sweprin/deviceProj/api/constant-values.html#Device.DEFAULT_PEEKS"></a>
	 */
	public static final int DEFAULT_PEEKS = 2;

	/**
	 * Default number of bits stored.
	 * @see<a href = "http://jodypaul.com/cs/sweprin/deviceProj/api/constant-values.html#Device.DEFAULT_SIZE"></a>
	 */
	public static final int DEFAUT_SIZE = 4;
	
	/**
	 * Character indicator of false.
	 * @see<a href="http://jodypaul.com/cs/sweprin/deviceProj/api/constant-values.html#Device.VALUE_FALSE"></a>
	 */
	public static final  char VALUE_FALSE = 70;

	/**
	 * Character indicator of true.
	 * @see<a href = "http://jodypaul.com/cs/sweprin/deviceProj/api/constant-values.html#Device.VALUE_TRUE"></a>
	 */
	public static final char VALUE_TRUE = 84;
	
	/**
	 * Constructs device using defaults.
	 */
	Device()
	{
		
	}
	
	/**
	 * Construct device with specified bits for testing.
	 * @param intialBits the number of bits stored in this device
	 * @param bitsPerPeek the number of bits to disclose via peek or set via poke
	 */
	Device(boolean[] intialBits, int bitsPerPeek)
	{
		
	}
	
	/**
	 * Construct device with specified size and number of peek/poke bits.
	 * @param size the bit values for this test device
	 * @param bitsPerPeek the number of bits to disclose via peek or set via poke
	 */
	Device(int size, int bitsPerPeek)
	{
		
	}
	/**
	 * Peek at bits of device.
	 * @param pattern indicating which bits to show as '?'
	 * @return a pattern that discloses the values of the indicated bits
	 */
	public CharSequence peek(CharSequence pattern)
	{
		return pattern;
	}
	
	/**
	 * Poke bits into device.
	 * @param pattern indicator of values of bits to poke
	 */
	public void poke(CharSequence pattern)
	{
		
	}
	
	/**
	 * Initiate device rotation.
	 * @return true if all bits have identical value; false otherwise
	 */
	public boolean spin()
	{
		return true;
	}
	
	/**
	 * Render device information as a string.
	 * @return rendering that reveals partial state
	 */
	public String toString(){
		return "nothing";
	}

}
