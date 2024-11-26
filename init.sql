GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS almacen;
USE almacen;

CREATE TABLE almacen.Productos (
	id INT auto_increment NOT NULL,
	nombre varchar(100) NOT NULL,
	descripcion varchar(255) NOT NULL,
	cantidad INT NOT NULL,
	precio DOUBLE NOT NULL,
	CONSTRAINT Productos_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE almacen.Empleados (
	id INT auto_increment NOT NULL,
	nombre varchar(100) NOT NULL,
	apellido varchar(100) NOT NULL,
	correo varchar(100) NOT NULL,
	CONSTRAINT Empleados_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE almacen.Pedidos (
	id INT auto_increment NOT NULL,
	id_producto INT NOT NULL,
	descripcion varchar(255) NULL,
	precio_total DOUBLE NULL,
	CONSTRAINT Pedidos_pk PRIMARY KEY (id),
	CONSTRAINT Pedidos_Productos_FK FOREIGN KEY (id_producto) REFERENCES almacen.Productos(id) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE almacen.Productos_Fav (
	id INT auto_increment NOT NULL,
	id_producto INT NOT NULL,
	CONSTRAINT Productos_Fav_PK PRIMARY KEY (id),
	CONSTRAINT Productos_Fav_Productos_FK FOREIGN KEY (id_producto) REFERENCES almacen.Productos(id) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;