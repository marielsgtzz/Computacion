# FundamentosMatematicosDeLaComputacion

Este proyecto consiste en construir un sencillo reconocedor de declaraciones de variables de un lenguaje de programación.

El programa lee datos de entrada, una serie de declaraciones de variables y arroja como resultado una serie de estadísticas relacionadas con las variables declaradas y sus tipos.

## Especificaciones

✅ El programa lee los datos de entrada de un archivo, el cual se le pasa por medio de la línea de comandos o se carga por medio de una ventana de dialogo de abrir archivo.

✅ El programa utiliza expresiones regulares para realizar el análisis de la entrada.

✅ El programa reconoce todos los tipos de declaraciones de variables del lenguaje Java, al menos en su versión 1.8 (SE 8). Los tipos de declaraciones se confirman en la sección `Tipos de variables en Java` de este mismo documento.

La salida del programa contiene los siguientes datos:

- ✅ Numero total de variables declaradas.
- ✅ Numero total de tipos utilizados en las declaraciones encontradas.
- ✅ Numero total de variables declaradas de cada tipo.
- ✅ Numero total de variables inicializadas.
- ✅ Numero total de variables de tipo arreglo.
- ✅ Numero total de declaraciones constantes (es decir, el valor no se puede cambiar después de la inicialización).
- ✅ Clasificación de todos los nombres de variables por tipo declarado.
- El formato de la salida es

Numero total de variables declaradas:

Numero total de tipos utilizados en las declaraciones encontradas:

Numero total de variables declaradas de tipo:

Variables de tipo:

Numero total de variables inicializadas:

Numero total de variables de tipo arreglo:

Número total de declaraciones constantes:

![DemostracionProyecto](imgs/Prueba.gif)

## Uso del proyecto

1. Correr el programa [codigo.py](codigo.py)
2. Abrir la carpeta `FundamentosMatematicosDeLaComputacion`
3. Seleccionar el archivo [Variables.txt](Variables.txt)

Esta es una opción básica para probar el programa, si se quiere probar con otro archivo de texto solo es cuestión de seleccionar ese.

### Ejemplo al correr el proyecto con el archivo Variables.txt
Numero total de variables declaradas: 49
Numero total de tipos utilizados en las declaraciones encontradas: 16 

Numero total de variables declaradas de tipo int: 5
Variables de tipo int: edad, temperatura, distancia, altura, puntos

Numero total de variables declaradas de tipo double: 5
Variables de tipo double: temperaturaMedia, alturaMontaña, profundidadOceano, distanciaEstrellas, salario

Numero total de variables declaradas de tipo byte: 5
Variables de tipo byte: nivel, edadPerro, temperaturaSala, diasMes, capasOzono

Numero total de variables declaradas de tipo short: 5
Variables de tipo short: poblacionCiudad, capacidadEstadio, nivelMar, añoNacimiento, peso

Numero total de variables declaradas de tipo long: 5
Variables de tipo long: distanciaLuna, poblacionMundial, presupuestoGobierno, numeroEstrellas, galaxiasUniverso

Numero total de variables declaradas de tipo float: 5
Variables de tipo float: pi, temperaturaCuerpo, velocidadLuz, masaElectron, cargaElectron

Numero total de variables declaradas de tipo char: 5
Variables de tipo char: letraFinal, inicial, simboloEuro, signoPregunta, nuevaLinea

Numero total de variables declaradas de tipo boolean: 5
Variables de tipo boolean: contieneGluten, esBisiesto, tieneCarnet, esMayorDeEdad, semaforoRojo

Numero total de variables declaradas de tipo int[]: 1
Variables de tipo int[]: años

Numero total de variables declaradas de tipo double[]: 1
Variables de tipo double[]: medidas

Numero total de variables declaradas de tipo byte[]: 1
Variables de tipo byte[]: nivelesAcceso

Numero total de variables declaradas de tipo short[]: 1
Variables de tipo short[]: codigosError

Numero total de variables declaradas de tipo long[]: 1
Variables de tipo long[]: poblacionCiudades

Numero total de variables declaradas de tipo float[]: 1
Variables de tipo float[]: notas

Numero total de variables declaradas de tipo char[]: 1
Variables de tipo char[]: vocales

Numero total de variables declaradas de tipo boolean[]: 2
Variables de tipo boolean[]: diasLaborales, respuestasExamen

Numero total de variables inicializadas: 49

Numero total de variables de tipo arreglo: 9

Número total de declaraciones constantes: 0

### Tipos de variables en Java

#### Tipos Numéricos

##### Enteros

- byte: 8 bits, rango de -128 a 127.
  - ejemplo: byte diasMes = 31;
- short: 16 bits, rango de -32,768 a 32,767.
  - ejemplo: short diasLustro = (12 _ 31) _ 5;
- int: 32 bits, rango de -2^31 a 2^31-1.
  - ejemplo: int edad = 30;
- long: 64 bits, rango de -2^63 a 2^63-1.
  -ejemplo:long añoLuz = velocidadLuz \* 365;

##### Punto Flotante

- float: 32 bits, rango de aproximadamente ±1.4E-45 a ±3.4028235E38.
  - ejemplo: float pi = 3.1415926535f;
- double: 64 bits, rango de aproximadamente ±4.9E-324 a ±1.7976931348623157E308.
  - ejemplo: double e = 2.718281828459045235360

#### Tipos No Numéricos

##### Carácter

- char: 16 bits, utilizado para almacenar caracteres Unicode, rango de '\u0000' (0) a '\uffff' (65,535).
  - ejemplos:
    -char letraA = 'a';
    -char letraANumerico = 61;

##### Booleano

- boolean: representa dos valores: true y false.
  - ejemplo: boolean verdadero = true;

##### Declaraciones de Arrays

- ejemplos:
  - int[] numeros = {1, 2, 3};
  - String[] nombres = {"Ana", "Luis", "Carlos"};
  - boolean[] valores = {true, false, true};
  - byte[] byteArray = {10, 20, 30, 40, 50};
  - short[] shortArray = {1000, 2000, 3000, 4000, 5000};
  - long[] longArray = {10000000000L, 20000000000L, 30000000000L, 40000000000L, 50000000000L};
  - float[] floatArray = {1.2f, 3.4f, 5.6f, 7.8f, 9.0f};
  - double[] doubleArray = {1.23, 4.56, 7.89, 0.12, 3.45};
  - char[] charArray = {'H', 'o', 'l', 'a', '!'};
  - boolean[] booleanArray = {true, false, true, false, true};

## Problemas Conocidos

### Análisis Léxico
El primer desafío es dividir la entrada de texto en unidades, lo cual implica identificar palabras clave, identificadores (nombres de variables), tipos de datos, operadores y otros elementos sintácticos. Los lenguajes de programación suelen tener reglas específicas para estas unidades, como nombres de variables que no pueden comenzar con números o el uso de ciertos caracteres especiales.

### Análisis Sintáctico
Una vez identificadas las unidades, el siguiente paso es analizar la estructura de las declaraciones de variables para asegurarse de que siguen la gramática del lenguaje. Esto implica reconocer patrones y manejar correctamente declaraciones más complejas que pueden incluir inicializaciones, arreglos o estructuras.

### Ambigüedades en la Gramática
Algunas gramáticas pueden ser ambiguas, lo que significa que una secuencia de unidades puede interpretarse de más de una manera. Resolver estas ambigüedades para asegurar que el análisis sintáctico es correcto puede ser complicado.

### Manejo de Errores
Identificar y reportar errores de manera informativa es crucial. Esto incluye errores léxicos (por ejemplo, caracteres no válidos), errores sintácticos (declaraciones mal formadas) y posiblemente errores semánticos (por ejemplo, uso de un tipo de dato no declarado).

### Variabilidad entre Lenguajes de Programación
Los lenguajes de programación pueden variar significativamente en cuanto a sintaxis y semántica. Un reconocedor que funciona para un lenguaje puede necesitar ajustes significativos para trabajar con otro, especialmente si los lenguajes difieren en cómo se declaran las variables, se especifican los tipos o se organizan las declaraciones.

### Análisis Semántico
Además del análisis léxico y sintáctico, puede ser necesario un análisis semántico para recopilar estadísticas útiles. Esto podría incluir determinar si hay declaraciones duplicadas o si los tipos de las variables son consistentes a través del código.

### Eficiencia y Escalabilidad
El reconocedor debe ser lo suficientemente eficiente como para manejar grandes volúmenes de datos de entrada sin consumir recursos excesivos ni tardar demasiado tiempo.

### Interfaz de Usuario y Presentación de Resultados
Presentar los resultados de manera clara y útil para el usuario final, especialmente si se pretende ofrecer estadísticas complejas o análisis detallados, puede ser un desafío en sí mismo.
