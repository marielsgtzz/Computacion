package p2;

public class PruebaEspecie {

	public static void main(String[] args) {
		Especie perry = new Especie("Ornitorrinco", 3000, 0.25);
		Especie perry2 = new Especie("Ornitorrinco", 3000, 0.25);
		Especie shamu = new Especie("Orca", 5000, 0.25);

		//Calcular poblacion futura
		System.out.println("\nPOBLACION FUTURA");
		System.out.println("En 3 anios van a haber " + perry.calculaPob(3));
		
		//Igualdad Especies
		System.out.println("\nSON IGUALES LAS ESPECIES");
		System.out.println("Las especies son iguales: "+perry.equals(perry2));
		System.out.println("Las especies son iguales: "+perry.equals(shamu));

		//Comparar Especies
		System.out.println("\nMAYOR POBLACION");
		int pob = perry.compareTo(shamu);
		if (pob == -1) {
			System.out.println("La segunda especie es mas grande");
		} else if (pob == 1)
			System.out.println("La primera especie es mas grande");
		else
			System.out.println("Los dos especies son igual de grandes");
		
		pob = perry.compareTo(perry2);
		if (pob == -1) {
			System.out.println("La segunda especie es mas grande");
		} else if (pob == 1)
			System.out.println("La primera especie es mas grande");
		else
			System.out.println("Los dos especies son igual de grandes");
		
		
		//Informacion Especie
		System.out.println("\nINFO ESPECIE");
		System.out.println(perry2.toString());
		

	}

}
