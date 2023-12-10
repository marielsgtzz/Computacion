/*
 Una de las métricas para saber si un cliente es bueno, aparte de la suma y el promedio de sus pagos, es si tenemos una 
 progresión consistentemente creciente en los montos.

Debemos calcular para cada cliente su promedio mensual de deltas en los pagos de sus órdenes en la tabla order_details en la
 BD de Northwind, es decir, la diferencia entre el monto total de una orden en tiempo t y el anterior en t-1, para tener la foto completa 
 sobre el customer lifetime value de cada miembro de nuestra cartera.
*/


--Ordenes de lxs clientes (suma el precio de las ordenes que ocurren el mismo día)
select c.customer_id as customer, sum((od.unit_price*od.quantity)-od.discount) as total_order_price, o.order_date as order_date from order_details od
join orders o on o.order_id = od.order_id 
join customers c on c.customer_id = o.customer_id 
group by customer, order_date
order by customer;

--Delta pagos (diferencia entre el monto total de una orden en tiempo t y el anterior en t-1)
with deltas_pagos as (
	select c.customer_id as customer, 
	lead(sum((od.unit_price*od.quantity)-od.discount)) over (order by o.order_date) - sum((od.unit_price*od.quantity)-od.discount) as total_order_price_dif 
	from order_details od
	join orders o on o.order_id = od.order_id 
	join customers c on c.customer_id = o.customer_id 
	group by customer, order_date
	order by customer
)

--customer lifetime value
select customer, avg(total_order_price_dif) as lifetime_value from deltas_pagos group by customer;
