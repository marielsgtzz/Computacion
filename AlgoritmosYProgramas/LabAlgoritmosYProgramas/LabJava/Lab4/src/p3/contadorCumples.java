package p3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class contadorCumples {

	public static void main(String[] args) {
		RegistroCumples c = new RegistroCumples();
		System.out.println(c.toString());
		System.out.println("------");
		System.out.println(c.cumpleMes(3));
		System.out.println("------");
		System.out.println(c.mOf(5)); 
		System.out.println(c.mOf(11));
		System.out.println(c.mOf(1));
		System.out.println("------");
		System.out.println(c.nadie());
	}

	
}
