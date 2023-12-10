--TAREA 1 usando la base de datos de Northwind que es una BD ficticia de una empresa importadora y exportadora de alimentos. 

--Qué contactos de proveedores tienen la posición de sales representative?
select s.contact_name from suppliers s where s.contact_title = 'Sales Representative';

--Qué contactos de proveedores no son marketing managers?
select s.contact_name from suppliers s where not s.contact_title = 'Marketing Manager';

--Cuales órdenes no vienen de clientes en Estados Unidos?
select o.order_id from orders o where not o.ship_country = 'USA';

--Qué productos de los que transportamos son quesos?
--Solo los que se están transportando
--select p.product_id, p.product_name from products p where p.category_id = 4;
select distinct p.product_id, p.product_name from categories c join products p using (category_id) join order_details od using (product_id) join orders o using 
(order_id) where shipped_date is not null and c.description = 'Cheeses';

--Qué ordenes van a Bélgica o Francia?
select o.order_id from orders o where o.ship_country = 'Belgium' or o.ship_country = 'France';

--Qué órdenes van a LATAM?
select o.order_id from orders o where o.ship_country = 'Brazil' or o.ship_country = 'Mexico' or o.ship_country = 'Venezuela' or o.ship_country = 'Argentina';

--Qué órdenes no van a LATAM?
select o.order_id from orders o where not (o.ship_country = 'Brazil' or o.ship_country = 'Mexico' or o.ship_country = 'Venezuela' or o.ship_country = 'Argentina');

--Necesitamos los nombres completos de los empleados, nombres y apellidos unidos en un mismo registro
select (e.first_name, e.last_name) as Employee_data from employees e;

--Cuánta lana tenemos en inventario?
select SUM(p.units_in_stock * p.unit_price) as total_money_inventory from products p  ;

--Cuantos clientes tenemos de cada país?
select c.country, count(c.contact_name) amount_clients from customers c group by country ;

--Obtener un reporte de edades de los empleados para checar su elegibilidad para seguro de gastos médicos menores.
select e.employee_id , age (current_date, e.birth_date) edades from employees e;

--Cuál es la orden más reciente por cliente?
select max(o.order_id), c.contact_name from orders o join customers c on (o.customer_id = c.customer_id) group by c.contact_name;

--De nuestros clientes, qué función desempeñan y cuántos son?
select c.contact_title, count(c.contact_title) from customers c group by c.contact_title ;

--Cuántos productos tenemos de cada categoría?
--select p.category_id, count(p.category_id) from products p group by p.category_id;
select c.category_name, count(p.category_id) from products p join categories c on (p.category_id = c.category_id) group by p.category_id,c.category_name;

--Cómo podemos generar el reporte de reorder?
select reorder_level,  product_name producto, product_id id_producto, units_in_stock from products p order by reorder_level asc ;

--A donde va nuestro envío más voluminoso?
select o.ship_country, od.quantity  from orders o join order_details od on (o.order_id  = od.order_id) order by od.quantity desc limit 1;

--Cómo creamos una columna en customers que nos diga si un cliente es bueno, regular, o malo?
select o.customer_id, sum(od.quantity), 
case 
	when sum(od.quantity) > 650000 then 'bueno'
	when sum(od.quantity) < 300000 then 'malo'
	else 'regular'
end as tipo_cliente from orders o, order_details od group by o.customer_id ;

--Qué colaboradores chambearon durante las fiestas de navidad?
select e.employee_id, e.first_name, e.last_name from orders o join employees e on (o.employee_id = e.employee_id) where (extract(month from o.shipped_date) = 12 
and extract(day from o.shipped_date) = 25) or (extract(month from o.order_date) = 12 and extract(day from o.order_date) = 25) group by e.employee_id ;

--Qué productos mandamos en navidad?
select p.product_id ,p.product_name from products p join order_details od on (p.product_id = od.product_id) join orders o on (od.order_id = o.order_id)
where extract(month from o.shipped_date) = 12 and extract(day from o.shipped_date) = 25;

--Qué país recibe el mayor volumen de producto?
select o.ship_country pais, sum(od.quantity) from orders o join order_details od on o.order_id = od.order_id group by o.ship_country order by sum(od.quantity) 
desc limit 1;
