# Importamos los módulos necesarios para la ejecución del script
import sys
import tkinter as tk
from tkinter import filedialog
import re

def leer_archivo_en_lista(ruta):
    """
    Esta función abre un archivo de texto y devuelve sus líneas como una lista.
    Se eliminan los espacios en blanco al inicio y al final de cada una.

    @param: ruta (La ruta del archivo a leer)
    @return: Lista de String que es el txt en formato de lista. Cada elem corresponde a una línea del archivo.
    """
    try:
        with open(ruta, 'r') as archivo:
            lineas = [linea.strip() for linea in archivo.readlines()]
        return lineas
    except Exception as e:
        print(f"Error al leer el archivo: {e}")
        return []

def seleccionar_archivo():
    """
    Abre una ventana de diálogo para que el usuario seleccione un archivo. 
    Se utiliza la interfaz gráfica de tkinter.
    
    @returns: La ruta del archivo seleccionado por el usuario.
    """
    root = tk.Tk()
    root.withdraw()  # Oculta la ventana principal de tkinter
    ruta_archivo = filedialog.askopenfilename()  # Muestra el diálogo para seleccionar archivo
    root.destroy()  # Cierra la ventana de tkinter
    return ruta_archivo

def buscar_tipos(variables):
    """
    Utiliza expresiones regulares para encontrar declaraciones de variables y recopilar estadísticas.
    Analiza las líneas de texto proporcionadas buscando declaraciones de variables, identifica su tipo, si están inicializadas,
    si son arreglos y si son declaraciones constantes.
    
    @params: lista variables: Lista de líneas de texto a analizar.
    
    @returns:
    - Un diccionario con los tipos de variables como claves y las variables correspondientes como valores.
    - El total de nombres de variables únicos encontrados.
    - El número total de variables inicializadas.
    - El número total de variables de tipo arreglo.
    - El número total de declaraciones constantes.
    """
    # Patrones de expresiones regulares para identificar tipos de datos y si una variable está inicializada o es constante
    patrones_ext = {
        'int': re.compile(r'\b(?:final\s+)?int\s+(\w+)(\s*=\s*.+)?'),
        'double': re.compile(r'\b(?:final\s+)?double\s+(\w+)(\s*=\s*.+)?'),
        'byte': re.compile(r'\b(?:final\s+)?byte\s+(\w+)(\s*=\s*.+)?'),
        'short': re.compile(r'\b(?:final\s+)?short\s+(\w+)(\s*=\s*.+)?'),
        'long': re.compile(r'\b(?:final\s+)?long\s+(\w+)(\s*=\s*.+)?'),
        'float': re.compile(r'\b(?:final\s+)?float\s+(\w+)(\s*=\s*.+)?'),
        'char': re.compile(r'\b(?:final\s+)?char\s+(\w+)(\s*=\s*.+)?'),
        'boolean': re.compile(r'\b(?:final\s+)?boolean\s+(\w+)(\s*=\s*.+)?'),
        
        'int[]': re.compile(r'\b(?:final\s+)?int\[\]\s+(\w+)(\s*=\s*.+)?'),
        'double[]': re.compile(r'\b(?:final\s+)?double\[\]\s+(\w+)(\s*=\s*.+)?'),
        'byte[]': re.compile(r'\b(?:final\s+)?byte\[\]\s+(\w+)(\s*=\s*.+)?'),
        'short[]': re.compile(r'\b(?:final\s+)?short\[\]\s+(\w+)(\s*=\s*.+)?'),
        'long[]': re.compile(r'\b(?:final\s+)?long\[\]\s+(\w+)(\s*=\s*.+)?'),
        'float[]': re.compile(r'\b(?:final\s+)?float\[\]\s+(\w+)(\s*=\s*.+)?'),
        'char[]': re.compile(r'\b(?:final\s+)?char\[\]\s+(\w+)(\s*=\s*.+)?'),
        'boolean[]': re.compile(r'\b(?:final\s+)?boolean\[\]\s+(\w+)(\s*=\s*.+)?'),

    }

    resultados = {tipo: [] for tipo in patrones_ext}
    nombres_variables = set()
    total_inicializadas = 0
    total_arreglos = 0
    total_constantes = 0

    for linea in variables:
        for tipo, patron in patrones_ext.items():
            match = patron.search(linea)
            if match:
                variable = match.group(1)
                nombres_variables.add(variable)
                resultados[tipo].append(variable)
                if match.group(2):  # Si la variable está inicializada
                    total_inicializadas += 1
                if "[]" in tipo:  # Si es un tipo de arreglo
                    total_arreglos += 1
                if "final" in linea:  # Si la declaración es constante
                    total_constantes += 1
                break  # Termina la iteración actual del bucle for para evitar contabilizar una variable más de una vez

    return resultados, len(nombres_variables), total_inicializadas, total_arreglos, total_constantes

def imprimir_estadisticas(resultados, total_variables, total_inicializadas, total_arreglos, total_constantes):
    """
    Imprime estadísticas sobre las variables encontradas en el análisis, incluyendo el total de variables, el total por tipo,
    cuántas están inicializadas, cuántas son arreglos y cuántas son constantes.
    
    @params:
    - resultados: Diccionario con los tipos de variables y las variables correspondientes.
    - total_variables: Número total de variables encontradas.
    - total_inicializadas: Número total de variables inicializadas.
    - total_arreglos: Número total de variables de tipo arreglo.
    - total_constantes: Número total de declaraciones constantes.
    """
    print(f"\nNumero total de variables declaradas: {total_variables}")
    print(f"Numero total de tipos utilizados en las declaraciones encontradas: {len(resultados)} \n")
    for tipo, variables in resultados.items():
        print(f"Numero total de variables declaradas de tipo {tipo}: {len(variables)}")
        # Aquí clasificamos e imprimimos los nombres de las variables por tipo declarado
        print(f"Variables de tipo {tipo}: {', '.join(variables)}\n")
    print(f"Numero total de variables inicializadas: {total_inicializadas}\n")
    print(f"Numero total de variables de tipo arreglo: {total_arreglos}\n")
    print(f"Número total de declaraciones constantes: {total_constantes}\n")

def main():
    """
    Función principal que llama al resto de las funciones:
    1. Inicia la selección de archivo
    2. Lee el contenido del archivo 
    3. Analiza las variables declaradas
    4. Imprime las estadísticas relevantes sobre las variables
    """
    variables = []
    if len(sys.argv) > 1:
        ruta_archivo = sys.argv[1]  # Intenta obtener la ruta del archivo desde los argumentos del comando
        variables = leer_archivo_en_lista(ruta_archivo)
    else:
        ruta_archivo = seleccionar_archivo()  # Pide al usuario que seleccione un archivo
        if ruta_archivo:
            variables = leer_archivo_en_lista(ruta_archivo)
        else:
            print("No se seleccionó ningún archivo.")
            sys.exit()

    resultados, total_variables, total_inicializadas, total_arreglos, total_constantes = buscar_tipos(variables)
    imprimir_estadisticas(resultados, total_variables, total_inicializadas, total_arreglos, total_constantes)

if __name__ == "__main__":
    main()
