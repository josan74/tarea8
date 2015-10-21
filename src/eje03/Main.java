package eje03;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sFecha = "00000000";
		try {
			System.out.println(Ibex.getCloseValue(sdf.parse(sFecha)));
		} catch (ParseException e) { }

	}

}
