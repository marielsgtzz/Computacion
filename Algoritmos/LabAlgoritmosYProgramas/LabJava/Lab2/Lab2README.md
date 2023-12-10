# Lab2 Instanciación de objetos

## Recomendaciones

**1.** Identificar en la declaración del problema (sirve marcarlos con diferentes colores):

- el objeto principal, este será la clase

- las características del objeto, que son los atributos

- funcionalidad que se pide, que serían los métodos y funciones. Puede ser que alguno de los requisitos se satisfagan con un getter o setter.

**2.** Construir el diagrama UML. Recordar el formato

| Nombre clase                                                 |
| ------------------------------------------------------------ |
| Atributos                                                    |
| setters y getters, métodos y funciones & funciones genéricas |

- **Recordar que los métodos y funciones siguen el formato de**:
  identificador nombre (tipo de dato del parámetro (si es que hay)) : tipo de dato de retorno o void
  _ej. + getNombre( ): String_

- **Los atributos siguen el formato de**:
  identificador nombre : tipo de dato del atributo
  _ej. - nombre: String_

**Identificadores**:
**-** : private
**+** : public
**#** : protected

**3.** En Eclipse programar primero los atributos

**4.** Escribir las funciones y métodos que cumplen el funcionamiento que el problema pide

**5.** Hacer getters y setters

**6.** Escribir funciones genéricas (toString, compareTo, equals)

**7.** Hacer pruebas. Super importante que en su clase de pruebas (su ejecutable) tengan el método main.
```
public static void main(String[] args) {
		//Código de pruebas
	} 
```

En esta carpeta se responden dos problemas del laboratorio 2.
Problema 1. Se necesita un programa de Java para llevar el control de un juego de basketball. El contenido que la liga nos está pidiendo es que el programa tenga los nombres de ambos equipos, los puntajes de cada equipo y el estatus de la partida (terminada o en curso). Aparte se necesita que se pueda registrar cuando un equipo anota 1, 2 y 3 puntos, que se pueda cambiar el status del juego, que pueda saber los puntos de un equipo en específico y ver quién está ganando actualmente.

Problema 2. Se quiere tener un programa que nos ayude a manejar la información de la población de las especies en peligro de extinción. Por cada especie se le va a pedir al usuario ingresar el nombre, población y velocidad de crecimiento de la misma. El programa necesita poder predecir la población de la especie dentro de x años, este cálculo se puede lograr realizando la siguiente operación x veces (poblacion = poblacion + (velocidadCrecimiento / 100) \* población), el resultado de este cálculo debe de ser un número entero. También se necesita un método para comparar dos especies y ver cuál tiene una población mayor.

En los archivos [ [Juego.java](https://github.com/marielsgtzz/LabAlgoritmosYProgramas/blob/main/LabJava/src/Lab2/Problema1/Juego.java "Juego.java"), [PruebaJuego.java](https://github.com/marielsgtzz/LabAlgoritmosYProgramas/blob/main/LabJava/src/Lab2/Problema1/PruebaJuego.java "PruebaJuego.java") ] y [ [JuegoBasketball.java](https://github.com/marielsgtzz/LabAlgoritmosYProgramas/blob/main/LabJava/src/Lab2/Problema1/JuegoBasketball.java "JuegoBasketball.java"), [PruebaJuegoBasketball.java](https://github.com/marielsgtzz/LabAlgoritmosYProgramas/blob/main/LabJava/src/Lab2/Problema1/PruebaJuegoBasketball.java "PruebaJuegoBasketball.java") ] se responde el primer inciso de dos formas diferentes. Cabe recalcar que en JuegoBasketball el toString( ) se hizo diferente pero ustedes necesitan usar el StringBuilder.

La respuesta del segundo inciso está en [Especie.java](https://github.com/marielsgtzz/LabAlgoritmosYProgramas/blob/main/LabJava/src/Lab2/Problema2/Especie.java "Especie.java") y [PruebaEspecie.java](https://github.com/marielsgtzz/LabAlgoritmosYProgramas/blob/main/LabJava/src/Lab2/Problema2/PruebaEspecie.java "PruebaEspecie.java").
