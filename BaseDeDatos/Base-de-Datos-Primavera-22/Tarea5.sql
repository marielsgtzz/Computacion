/*
 * Como parte de la modernización de nuestras video rental stores, vamos a automatizar la recepción y entrega de discos con robots.
 * Parte de la infraestructura es diseñar contenedores cilíndricos giratorios para facilitar la colocación y extracción de discos por brazos automatizados. 
 * 
 * Medidas:
 		-Cada cajita de Blu-Ray mide 20cm x 13.5cm x 1.5cm, y para que el brazo pueda manipular adecuadamente cada cajita, debe estar contenida dentro de un arnés 
 		que cambia las medidas a 30cm x 21cm x 8cm para un espacio total de 5040 centímetros cúbicos (0.00504m3) y un peso de 500 gr por película.
 * 
 * Necesidad:
		-Formular la medida de dichos cilindros de manera tal que quepan todas las copias de los Blu-Rays de cada uno de nuestros stores. 
 * 
 * Limitantes:
 		-Las medidas deben ser estándar y en cada store pueden haber más de 1 de estos cilindros. 
		-Cada cilindro aguanta un peso máximo de 50kg como máximo.
 * 
 * Supuestos:
 		-Entre cada nivel del cilindro habrá una separación de 1 centímetro.
 		-Por cilindro se guardarán 25 películas por nivel
 		-La caja de la película mide 8cm de alto, 30 de profundidad y 21 de largo. 
 		-El valor de Pi se redondeará a 3.14
 */


--Cantidad de películas por tienda
select i.store_id as Tienda, count(i.inventory_id) as Numero_Ejemplares
	from inventory i
	group by i.store_id;
	
--Gramos totales de películas por tienda 
select i.store_id as Tienda, 500*count(i.inventory_id) as Gramos_Ejemplares
	from inventory i
	group by i.store_id;
	
--Cantidad de cilindros necesarios por tienda 
select i.store_id as Tienda, ceiling ((500*count(i.inventory_id))/50000) as Cant_Cilindros
	from inventory i
	group by i.store_id;

--Cantidad de ejemplares por cilindro (100)
select 50000/500 as ejemplares_por_cilindro;

--Para cumplir la necesidad del problema, los discos solo pueden estar en la orilla (no discos en el centro del cilindro)
	--Circunferencia del cilindro interno (hueco del cilindro principal)
	select 21*25 as circunferencia_interna_cm;
	
	--Radio interno 
	select 21*25/(3.14*2) as radio_interno;
	
	--Radio externo
	select (21*25/(3.14*2))+30 as radio_externo; --!!!!   +30 por la profundida de las cajas
	
	--Circunferencia exterior 
	select ((21*25/(3.14*2))+30)*3.14*2 as circunferencia_exterior;

	--Van a ser cuatro niveles, porque a lo mucho pueden ser 100 peliculas por cilindro y uno de los supuestos es que se tendrán por nivel 25
	select (8*4)+4 as altura_cilindro;--+4 porque se dijo que por nivel va a haber un espacio de 1cm --!!!!

--Información de los cilindros (Correr esta query)
	select i.store_id as Tienda, ceiling((500*count(i.inventory_id))/50000) as Cant_Cilindros, (21*25/(3.14*2))+30 as radio_cilindro, (8*4)+4 as altura_cilindro
	from inventory i
	group by i.store_id;
