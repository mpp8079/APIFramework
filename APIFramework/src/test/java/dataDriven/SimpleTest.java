package dataDriven;

import java.io.IOException;
import java.util.ArrayList;

public class SimpleTest {
	
	
	
	
	public static void main(String[] args) throws IOException {
		GetData d = new GetData();
		ArrayList a =d.getData("Login");
		System.out.println(a.get(0));
		System.out.println(a.get(1));
		System.out.println(a.get(2));
	}

}
