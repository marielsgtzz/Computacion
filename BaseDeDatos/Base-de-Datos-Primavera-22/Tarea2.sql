--Tarea 2--
create table avengers_email(
	id_avenger numeric(4,0) constraint pk_avengers_email primary key,
	nombre varchar(50) not null,
	email varchar(100) not null
);

create sequence avengers_email_id_avenger_seq start 1 increment 1;
alter table avengers_email alter column id_avenger set default nextval('avengers_email_id_avenger_seq') 


insert into avengers_email (nombre, email) values 
('Wanda Maximoff','wanda.maximoff@avengers.org'),
('Pietro Maximoff','pietro@mail.sokovia.ru'),
('Erik Lensherr','fuck_you_charles@brotherhood.of.evil.mutants.space'),
('Charles Xavier','i.am.secretely.filled.with.hubris@xavier-school-4-gifted-youngste.'),
('Anthony Edward Stark','iamironman@avengers.gov'),
('Steve Rogers','americas_ass@anti_avengers'),
('The Vision','vis@westview.sword.gov'),
('Clint Barton','bul@lse.ye'),
('Natasja Romanov','blackwidow@kgb.ru'),
('Thor','god_of_thunder-^_^@royalty.asgard.gov'),
('Logan','wolverine@cyclops_is_a_jerk.com'),
('Ororo Monroe','ororo@weather.co'),
('Scott Summers','o@x'),
('Nathan Summers','cable@xfact.or'),
('Groot','iamgroot@asgardiansofthegalaxyledbythor.quillsux'),
('Nebula','idonthaveelektras@complex.thanos'),
('Gamora','thefiercestwomaninthegalaxy@thanos.'),
('Rocket','shhhhhhhh@darknet.ru');

--Construyan un query que regrese emails inválidos.
--		Tomo como email válido que haya contenido antes y después de la @, y que después haya un punto y algo más. ej. (h@bla.co)
	--select a.email from avengers_email a where a.email not like '%@%'; --no cumple con la condición de contenido antes y después de la @
	--select a.email from avengers_email a where a.email like '%.'; --muestra los que no tienen algo después del punto 
	--select a.email from avengers_email a where a.email not like '%@%.%'; --no tiene un punto después de la @
	--Caracteres inválidos
		--select a.email from avengers_email a where a.email  like '%^@%';
		--select a.email from avengers_email a where a.email  like '%!@%';
select a.email as Email_invalido from avengers_email a where (a.email like '@%' or a.email like '%.' or a.email not like '%@%' or a.email not like '%@%.%' or 
(a.email like '%^@%' or a.email  like '%!@%' or a.email  like '%>@%' or a.email  like '%<@%' or a.email  like '%?@%' or a.email  like '%"@%' 
or a.email  like '%:@%' or a.email  like '%;@%' or a.email  like '%,@%' or a.email  like '%$@%'));


--PARTE EXTRA--
create table superheroes_anios_servicio(
	id_avenger numeric(4,0) constraint pk_superheroes_anios_servicio primary key,
	nombre_super varchar(50) not null,
	equipo varchar(50),
	anios_servicio numeric(4,0) not null
);

create sequence superheroes_anios_servicio_id_avenger_seq start 1 increment 1;
alter table superheroes_anios_servicio alter column id_avenger set default nextval('superheroes_anios_servicio_id_avenger_seq') 

insert into superheroes_anios_servicio (nombre_super, equipo, anios_servicio) values 
('Tony Stark','Avengers',10),
('Wanda Maximoff','Avengers',5),
('Wanda Maximoff','X Men',3),
('Erik Lensherr','Acolytes',10),
('Erik Lensherr','Brotherhood of Evil Mutants',12),
('Natasja Romanov','KGB', 8),
('Natasja Romanov','Avengers', 10);

--Regresar nombre del superhéroe, lista separada por comas de los grupos o equipos en donde sirvió, total de años de servicio en todos los grupos
	--select sas.nombre_super, string_agg(sas.equipo, ',') from superheroes_anios_servicio sas group by sas.nombre_super; --En que equipos estuvieron
	--select sas.nombre_super, sum(sas.anios_servicio) from superheroes_anios_servicio sas group by sas.nombre_super; --Cuantos años sirvieron en total
select sas.nombre_super, string_agg(sas.equipo, ',') equipos, sum(sas.anios_servicio) total_anios_servidos from superheroes_anios_servicio sas group by sas.nombre_super;



