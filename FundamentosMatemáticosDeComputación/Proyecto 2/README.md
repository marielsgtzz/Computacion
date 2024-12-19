# Reconocimiento de enunciados `while`

## Descripción
Este programa está diseñado para validar la correcta estructura y sintaxis de ciclos `while` dentro de archivos de código en Python. Analiza archivos de texto para asegurarse de que cada ciclo `while` tenga paréntesis y llaves equilibradas, y verifica la validez de las condiciones y la estructura interna de estos bloques.

## Problema que Resuelve
Muchos errores en la programación provienen de estructuras de control mal formadas, especialmente en los ciclos `while`. Este programa ayuda a identificar errores comunes como paréntesis desequilibrados, condiciones mal formadas y la falta de coherencia en las declaraciones dentro de ellos. 

## Cómo Ejecutar
Para ejecutar este programa, primero es necesario tener Python instalado en el sistema. Además, se requiere la biblioteca tkinter, que generalmente viene preinstalada con Python. Si no se tiene, se puede instalar con este comando:
   ```
   sudo apt-get install python3-tk
   ```
Posteriormente seguir estos pasos:

1. Guarda el archivo `Proyecto 2.py` en tu directorio local.
2. Abre una terminal o línea de comandos.
3. Navega hasta el directorio donde se encuentra el archivo.
4. Ejecuta el programa con el siguiente comando:
   ```
   python Proyecto 2.py
   ```
5. El programa abrirá una ventana de diálogo para que selecciones el archivo de Python que deseas validar (en el caso de este proyecto puede ser Valido.txt o Invalido.txt como prueba)

## Ejemplos de Ejecución


### Ejemplo 1: Archivo Válido
**Entrada:**
Archivo con ciclos `while` correctamente estructurados (Valido.txt).

**Salida:**
```
Bloques válidos: True
Variables distintas: 7
Operadores de comparación: 7
Bloques while: 7
```

### Ejemplo 2: Archivo Inválido
**Entrada:**
Archivo con ciclos `while` mal estructurados (Inválido.txt).

**Salida:**
```
Bloques válidos: False
Variables distintas: 0
Operadores de comparación: 0
Bloques while: 0
```

## Problemas Conocidos
- El programa actualmente no soporta la validación de ciclos `while` anidados con más de dos niveles de profundidad.
- Los errores específicos dentro de cada ciclo `while` no se detallan completamente, proporcionando solo un mensaje general de error estructural.