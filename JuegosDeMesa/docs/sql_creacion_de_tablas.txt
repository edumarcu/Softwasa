create database mydb;

CREATE  TABLE IF NOT EXISTS `mydb`.`tb_categorias` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nombre_categoria` VARCHAR(100) NULL ,
  `descripcion_categoria` TEXT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB

CREATE  TABLE IF NOT EXISTS `mydb`.`tb_usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login_usuario`  VARCHAR(50) NOT NULL,
  `password_usuario`  VARCHAR(20) NOT NULL,
  `nombre_usuario`  VARCHAR(50) NOT NULL,
  `apellido1_usuario`  VARCHAR(50) NOT NULL,
  `apellido2_usuario`  VARCHAR(50) NOT NULL,
  `direccion_usuario`  VARCHAR(250) NOT NULL,
  `telefono_usuario`  VARCHAR(15) NOT NULL,
  `email_usuario` VARCHAR(100) NOT NULL,
  `forma_pago` ENUM('Tarjeta', 'Contra Reembolso', 'Pay-Pal'),
  PRIMARY KEY (`id`) )
ENGINE = InnoDB



CREATE  TABLE IF NOT EXISTS `mydb`.`tb_productos` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre_producto` VARCHAR(100) NULL ,
  `descripcion_producto` TEXT NULL ,
  `enlace_producto` VARCHAR(100) NULL ,
  `id_categoria` INT UNSIGNED NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB





//**

CREATE  TABLE IF NOT EXISTS `mydb`.`tb_facturas` (
  `id_factura` INT NOT NULL AUTO_INCREMENT ,
  `id_usuario` VARCHAR(100) NULL ,
  `direccion_factura` VARCHAR(250),
  `fecha_factura` DATETIME,
   PRIMARY KEY (`id_factura`) )
ENGINE = InnoDB


CREATE  TABLE IF NOT EXISTS `mydb`.`tb_facturas_productos` (
  `id_producto` INT NOT NULL AUTO_INCREMENT ,
  `id_factura` VARCHAR(100) INT NOT NULL AUTO_INCREMENT )
  
  ENGINE = InnoDB


