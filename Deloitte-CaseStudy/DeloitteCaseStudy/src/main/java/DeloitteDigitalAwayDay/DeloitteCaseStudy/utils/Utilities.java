package DeloitteDigitalAwayDay.DeloitteCaseStudy.utils;


/**
 * Class to hold Utility static methods
 * 
 */
public class Utilities{
	
	/**
	 *  This method checks if if the input string is null or empty and returns bool value
	 * 
	 * @param str Input string to check
	 * @return boolean flag
	 */
	public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
}