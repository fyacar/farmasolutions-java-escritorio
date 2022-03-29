-- >>>>>>>>>>>>>>>>>>>>>>> Segunda Versión <<<<<<<<<<<<<<<<
-- borra la bd si existe
DROP DATABASE IF EXISTS farmaSolutions;
-- creamos la bd
CREATE DATABASE farmaSolutions;
-- activamos la bd
USE farmaSolutions;

create table tb_tipo(
id_tipo	 int not null primary key,
des_tipo varchar(20)
);

CREATE TABLE tb_Empleados(
cod_emp  int auto_increment,
nombre varchar(15) not null,
apellido varchar(25) not null,
usuario  char(6) NOT NULL,
clave    char(6) not null,
fnacim  date  not null,
id_tipo  int DEFAULT 2,
primary key (cod_emp),
foreign key (id_tipo) references tb_tipo(id_tipo)
);

create table tb_Clientes(
cod_cli int auto_increment,
nombre varchar(25) not null,
apellido varchar(25) not null,
dni varchar(8) not null,
clave char(5) not null,
puntos int,
primary key (cod_cli)
);

create table tb_proveedores(
cod_prov int auto_increment,
nombre_pro varchar(30) not null,
telefono varchar (20) not null,
direccion varchar(30) not null,
correo varchar(30) not null,
descripcion varchar (100) not null,
primary key (cod_prov)
);

create table tb_categorias(
idtipo		int not null primary key,
descripcion varchar(45)
);


create table tb_productos(
idprod      char(6) not null,
descripcion varchar(45) not null,
stock		int not null,
precio		decimal(8,2) not null,
idtipo		int not null,
cod_prov int not null,
fcha_registro date  not null,
fcha_vencimiento date  not null,
primary key (idprod), 
foreign key (idtipo) references tb_categorias(idtipo),
foreign key (cod_prov) references tb_proveedores(cod_prov)
);

create table tb_cab_boleta(
num_bol      char(5) not null,
fch_bol    date not null,
cod_cliente  int not null,
cod_vendedor int not null,
total_bol decimal(8,2) not null,
primary key (num_bol),
foreign key (cod_cliente) references tb_Clientes(cod_cli),
foreign key (cod_vendedor) references tb_Empleados(cod_emp)
);

create table tb_det_boleta(
num_bol     char(5) not null,
idprod      char(6) not null,
cantidad    int not null,
preciovta   decimal(8,2) not null,
importe		decimal(8,2) not null,
primary key (num_bol,idprod),
foreign key (num_bol) references tb_cab_boleta(num_bol),
foreign key (idprod) references tb_productos(idprod)
);

-- >>>>>>>>>>>>>>>>>>>>>>> TABLA TIPOS <<<<<<<<<<<<<<<<
insert into tb_tipo values (1, 'Administrador');
insert into tb_tipo values (2, 'Empleado');


-- >>>>>>>>>>>>>>>>>>>>>>> TABLA EMPLEADOS <<<<<<<<<<<<<<<<
insert into tb_Empleados values(1001, 'Admin ', 'Admin', 'ADM001','farma1','1996-01-30',1);
insert into tb_Empleados values(null, 'Javier', 'Vasconcelos Guillen', 'EMP001','farma1','1996-01-30',2);
insert into tb_Empleados values(null, 'Jean', 'Yaya Carbajal', 'EMP002','farma2','1993-02-09',2);
insert into tb_Empleados values(null, 'Catherine', 'Varas', 'EMP003','farma3','1992-03-09',2);
insert into tb_Empleados values(null, 'Nayelli', 'Illescas Huacho', 'EMP004','farma4','1999-04-09',2);
insert into tb_Empleados values(null, 'Jerson', 'Concha', 'EMP006','farma5','1995-05-09',2);
insert into tb_Empleados values (null,'Pepe', 'Ruiz','pepe12', 'pepe23', '1998-08-12',2);
insert into tb_Empleados values (null,'Luisa', 'Perez','luisa1', 'luisa2', '2000-11-15',2);
insert into tb_Empleados values (null,'José', 'Lopez','jose12', '123456', '2001-12-18',2);


-- >>>>>>>>>>>>>>>>>>>>>>> TABLA CLIENTES <<<<<<<<<<<<<<<<
insert into tb_Clientes values (80001,'Carlos', 'Quispe','78359215', '12345', 100);
insert into tb_Clientes values (null,'Juan', 'Rodriguez','75945344', '54321', 100);
insert into tb_Clientes values (null,'Luz', 'Peña','75382875', 'asdfg', 100);
insert into tb_Clientes values (null,'Carmen', 'Guerrero','76353484', '0123', 100);



-- >>>>>>>>>>>>>>>>>>>>>>> TABLA CATEGORIAS <<<<<<<<<<<<<<<<
insert into tb_categorias values (1, 'Pastillas');
insert into tb_categorias values (2, 'Jarabe');
insert into tb_categorias values (3, 'Cremas');
insert into tb_categorias values (4, 'Tocador');
insert into tb_categorias values (5, 'Cuidado');
insert into tb_categorias values (6, 'Otros');


-- >>>>>>>>>>>>>>>>>>>>>>> TABLA PROVEEDORES <<<<<<<<<<<<<<<<
insert into tb_proveedores values (50001, 'Artesa', '945124878','Av. Los Aires 485','atesa@artesa.com','Proveedora de medicamentos');
insert into tb_proveedores values (50002, 'Perfection', '4716784','Av. Palermo 485','perfection@perfection.com','Proveedora de Productos de belleza');
insert into tb_proveedores values (50003, 'Cafini', '944812781','Av. Los Girasoles 485','cafini@cafini.com','Proveedora de Productos variados');


-- >>>>>>>>>>>>>>>>>>>>>>> TABLA PRODUCTOS <<<<<<<<<<<<<<<<
insert into tb_productos values ('P00001','Panadol',20,1.85,1,50001,'2020-01-15','2021-01-15');
insert into tb_productos values ('P00002','Curitas unidad',100,1.0,3,50001,'2020-01-15','2021-01-15');
insert into tb_productos values ('P00003','Kitatos',80,15.0,2,50001, '2020-01-15','2022-01-15');
insert into tb_productos values ('P00004','flu',120,1.0,1,50001,'2020-01-15','2022-01-15');
insert into tb_productos values ('P00005','Jaboncillo cj',120,1.0,3,50002,'2020-01-15','2021-01-15');
insert into tb_productos values ('P00006','Termometro',80,2.8,5,50001,'2020-01-15','2022-01-15');
insert into tb_productos values ('P00007','Panadol jarabe',40,10.5,2,50001,'2020-01-15','2023-01-15');
insert into tb_productos values ('P00008','Antalgina',55,1.8,1,50001,'2020-01-15','2023-01-15');
insert into tb_productos values ('P00009','Ibuprofeno',60,15.0,4,50001,'2020-01-15','2023-01-15');
insert into tb_productos values ('P00010','Mejoralito Niños',10,1.5,1,50001,'2020-01-15','2024-01-15');
insert into tb_productos values ('P00011','Panadol jarabe',10,1.5,2,50001,'2020-01-15','2024-01-15');
insert into tb_productos values ('P00012','Jabon Neko',40,8.5,4,50002,'2020-01-15','2024-01-15');
insert into tb_productos values ('P00013','Pañales x 24u',10,1.5,5,50003,'2020-01-15','2025-01-15');
insert into tb_productos values ('P00014','Mejoralito Forte',10,1.5,1,50001,'2020-01-15','2025-01-15');
insert into tb_productos values ('P00015','Champu Amigo',50,0.99,5,50002,'2020-01-15','2025-01-15');
insert into tb_productos values ('P00016','Mejoralito',10,1.5,4,50001,'2020-01-15','2023-01-15');
insert into tb_productos values ('P00017','SinDolor',23,1.5,6,50001,'2020-01-15','2022-01-15');
insert into tb_productos values ('P00018','Mejoralito UForte',10,0.99,5,50001,'2020-01-15','2021-01-15');



-- >>>>>>>>>>>>>>>>>>>>>PROCEDIMIENTOS ALMACENADOS<<<<<<<<<<<<<<<<<


-- >>>> LISTADOXTIPO <<<<
DELIMITER $$
create procedure usp_listadoxtipo(xtipo int)
begin
	select cod_emp, concat (nombre , ' ' ,apellido) as 'Nombre Completo',t.des_tipo as 'Descripcion'
 from tb_Empleados  as u
 inner join tb_tipo as t
 on u.id_tipo=t.id_tipo
 where t.id_tipo=xtipo;
end $$
delimiter ;


DELIMITER $$
create procedure usp_validaempleado(usr char(6), pas char(6))
begin
	select * from tb_Empleados
    where usuario =usr  and clave=pas;
end $$
DELIMITER ;


delimiter $$
create  procedure usp_consultanombreproveedor (nombre varchar(30) )
begin
select * from tb_Proveedores p
where p.nombre_pro = nombre;
end $$


--
use farmaSolutions;

insert into tb_productos values ('P00019','Panadol',20,1.85,8,50001,'2020-01-15','2021-01-15');