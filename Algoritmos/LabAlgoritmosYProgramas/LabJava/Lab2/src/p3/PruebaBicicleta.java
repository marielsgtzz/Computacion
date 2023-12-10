package p3;

public class PruebaBicicleta {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bicicleta a = new Bicicleta('u', 0, "aluminio");
		System.out.println(a.toString());
		
		Bicicleta b = new Bicicleta('m', 3, "Fibra de Carbono");
		System.out.println(b.toString());
		
		Bicicleta c = new Bicicleta('u', 0, "aluminio");
		System.out.println(c.toString());
		
		//Costo Bici
		System.out.println("-----COSTO-----");
		System.out.println("El costo de la bicicleta es $" + b.costoBici()+"\n");
		
		//Tiempo armado
		System.out.println("-----TIEMPO-----");
		System.out.println("La bicicleta toma "+b.tiempoArmado()+" dias para armar.\n");
		
		//Comparacion
		System.out.println("-----COMPARACION-----");
		int res = b.compareTo(c);
		if(res<0) {
			System.out.println("La bicicleta b se hizo antes que la c"+"\n");
		} else if(res>0) {
			System.out.println("La bicicleta c se hizo antes que la b"+"\n");
		} else {
			System.out.println("Se está comparando la misma bicicleta"+"\n");
		}
		
		res = a.compareTo(c);
		if(res<0) {
			System.out.println("La bicicleta a se hizo antes que la c"+"\n");
		} else if(res>0) {
			System.out.println("La bicicleta c se hizo antes que la a"+"\n");
		} else {
			System.out.println("Se está comparando la misma bicicleta"+"\n");
		}
		
		//Igualdad
		System.out.println("-----IGUALDAD-----");
		if (a.equals(b)) {
			System.out.println("Las bicicletas son iguales"+"\n");
		} else 
			System.out.println("Las bicicletas son diferentes"+"\n");
		
		if (a.equals(c)) {
			System.out.println("Las bicicletas son iguales"+"\n");
		} else 
			System.out.println("Las bicicletas son diferentes"+"\n");
	}

}

