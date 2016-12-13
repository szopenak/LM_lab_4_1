import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpAnalyzer {
	
	static String ip = "(([0-1]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([0-1]?\\d\\d?|2[0-4]\\d|25[0-5])";
	static String mac = "(\\p{XDigit}{2}\\:){5}\\p{XDigit}{2}";
	static String mail = "\\w(\\w|\\.)*\\@(\\w+\\.)+\\w+";
	static String substraction = "\\-?([1-9]\\d*|0)(\\-{1,2}([1-9]\\d*|0))+";
	static String complex_add = "([\\-]?(([1-9]\\d*)|0)\\+)*([\\-]?(([1-9]\\d*)|0)?j)(\\+([\\-]?(([1-9]\\d*)|0)?j?))*";
	static String multiplication = "(\\-?(([1-9]\\d*)|0)(\\,\\d+)?)(\\*\\-?(([1-9]\\d*)|0)(\\,\\d+)?)+";
	static Pattern p;
	static Matcher m;
	
	static String check(String s) {
		p = Pattern.compile(ip);
		m = p.matcher(s);
		if(m.matches()) {return "IP adress or Subnet mask";}
		p = Pattern.compile(mail);
		m = p.matcher(s);
		if(m.matches()) {return "Email adress";}
		p = Pattern.compile(mac);
		m = p.matcher(s);
		if(m.matches()) {return "Mac adress";}
		p = Pattern.compile(substraction);
		m = p.matcher(s);
		if(m.matches()) {return "Substraction";}
		p = Pattern.compile(complex_add);
		m = p.matcher(s);
		if(m.matches()) {return "Complex addition";}
		p = Pattern.compile(multiplication);
		m = p.matcher(s);
		if(m.matches()) {return "Multiplication";}
		return "No matches found!";
	}
	
}