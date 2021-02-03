package xyz.leonardoarias.plugins.motdRandomizer.utilities;

import org.bukkit.ChatColor;

import xyz.leonardoarias.plugins.motdRandomizer.enums.DefaultFontInfo;

/**
 * Replaces the text &+code with the correct chat color.
 * 
 * @author LeoAB2907
 * @version 1.0
 */
public class TextFormatter {

	/**
	 * Replaces all color codes with the respective color.
	 * Must NOT be used before Center(String st);
	 * 
	 * @param str the string to be reconstructed
	 * @return str
	 */
	public static String Color(String str) {
		return ChatColor.translateAlternateColorCodes('&', str);
	}

	private static String CenteredText(String str, int max_px) {
		int center_px;
		if(max_px%2 != 0)
			center_px = (max_px+1) / 2;
		else
			center_px = max_px/2;
		
		int strPxSize = 0;
		boolean isBold = false;
		boolean previousCode = false;
		
		
		for(char c : str.toCharArray()) {
			if(c=='&' || c=='§') {
				previousCode = true;
				continue;
			}else if(previousCode == true) {
				previousCode = false;
				if(c== 'l' || c=='L') {
					isBold = true;
					continue;
				}else isBold = false;
			}else {
				DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
				strPxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
				strPxSize++;
			}
		}
		
		
		int halvedMessageSize = strPxSize / 2;
		int toCompensate = center_px - halvedMessageSize;
		int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
		int compensated = 0;
		StringBuilder sb = new StringBuilder();
		while(compensated<toCompensate) {
			sb.append(" ");
			compensated += spaceLength;
		}
		return sb.toString()+str;
		
	}
	
	/**
	 * Centers a text.
	 * 
	 * @param str The string to be replaced
	 * @param maxLength The maximum number of characters per line
	 * @return The centered string
	 * @throws IllegalStateException when the length of one line is greater than maxLength
	 */
	public static String Center(String str, int maxLength){
		String[] lines = str.split("\n");
		StringBuilder text;
		
		for (int i = 0; i< lines.length; i++) {
			lines[i] = CenteredText(lines[i], maxLength*5);
		}
		
		/* String builder */
		text = new StringBuilder("");
		for(String s : lines) {
			text.append(s);
			text.append("\n"); // this will add an extra \n at the end
		}
		text.replace(text.length()-1, text.length(), ""); // this removes the extra \n
		
		
		return text.toString();
	}
}
