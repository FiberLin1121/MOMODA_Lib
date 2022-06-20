DROP DATABASE IF EXISTS mmd;

CREATE DATABASE mmd DEFAULT CHARACTER SET utf8mb4;

USE mmd;


CREATE TABLE `customers` (
  `name` varchar(30) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(45) NOT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE `products` (
  `id` char(6) NOT NULL,
  `name` varchar(30) NOT NULL,
  `type` varchar(2) NOT NULL,
  `size` varchar(10) NOT NULL,
  `unitPrice` int(11) NOT NULL,
  `stock` int(11) NOT NULL DEFAULT '10',
  `description` varchar(100) NOT NULL,
  `outfit` varchar(20) DEFAULT NULL,
  `activity` varchar(20) DEFAULT NULL,
  `discount` int(11) NOT NULL DEFAULT '0',
  `class_name` varchar(20) NOT NULL DEFAULT 'Product',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- 
-- CREATE TABLE `size` (
--   `sid` char(4) NOT NULL,
--   `size` char(1) NOT NULL,
--   `fkey_2_products` char(6) NOT NULL,
--   PRIMARY KEY (`id`),
--   KEY `FK_size_2_product_id` (`fkey_2_products`),
--   CONSTRAINT `FK_size_2_product_id` FOREIGN KEY (`fkey_2_products`) REFERENCES `products` (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `color` (
  `cid` char(4) NOT NULL,
  `color_name` varchar(4) NOT NULL,
  `main_photoUrl` varchar(40) NOT NULL,
  `detail_photoUrl_1` varchar(40) NOT NULL,
  `detail_photoUrl_2` varchar(40) NOT NULL,
  `png_photoUrl` varchar(40) DEFAULT NULL,
  `collocation_photoUrl` varchar(40) DEFAULT NULL,
  `collocation_url` varchar(40) DEFAULT NULL,
  `color_block` varchar(40) DEFAULT NULL,
  `fkey_2_products` char(6) DEFAULT NULL,
  PRIMARY KEY (`cid`,`color_name`),
  KEY `FK_color_2_product_id_idx` (`fkey_2_products`),
  CONSTRAINT `FK_color_2_product_id` FOREIGN KEY (`fkey_2_products`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `orders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_email` varchar(60) NOT NULL ,
  `order_date` date NOT NULL,
  `order_time` time NOT NULL,
  `payment_type` varchar(10) NOT NULL,
  `payment_fee` double NOT NULL DEFAULT '0',
  `payment_note` varchar(100) NOT NULL DEFAULT '',
  `shipping_type` varchar(10) NOT NULL,
  `shipping_fee` double NOT NULL DEFAULT '0',
  `shipping_note` varchar(100) NOT NULL DEFAULT '',
  `recipient_name` varchar(30) NOT NULL,
  `recipient_email` varchar(60) NOT NULL,
  `recipient_phone` varchar(20) NOT NULL,
  `shipping_address` varchar(100) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fkey_orders_to_customers_idx` (`customer_email`),
  CONSTRAINT `fkey_orders_to_customers` FOREIGN KEY (`customer_email`) REFERENCES `customers` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE `order_items` (
  `order_id` int(10) unsigned NOT NULL,
  `product_id` char(6) NOT NULL,
  `color` varchar(20) NOT NULL,
  `size` varchar(10) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`,`color`,`size`),
  KEY `fkey_order_items_to_products_idx` (`product_id`),
  CONSTRAINT `fkey_order_items_to_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkey_order_items_to_products` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
DROP DATABASE IF EXISTS mmd;
CREATE DATABASE mmd DEFAULT CHARACTER SET utf8mb4;
USE mmd;
CREATE TABLE customers (`name` VARCHAR(30) NOT NULL, email VARCHAR(60) NOT NULL, password VARCHAR(45) NOT NULL, birthday DATE, PRIMARY KEY (email));
CREATE TABLE products (id CHAR(6) NOT NULL, `name` VARCHAR(30) NOT NULL, `type` VARCHAR(2) NOT NULL, `size` VARCHAR(10) NOT NULL, `unitPrice` INT NOT NULL, stock INT DEFAULT 10  NOT NULL, launch_date DATE, description VARCHAR(100) NOT NULL, outfit VARCHAR(20), activity VARCHAR(20), discount INT DEFAULT 0  NOT NULL, class_name VARCHAR(20) DEFAULT Product  NOT NULL, PRIMARY KEY (id));
CREATE TABLE colors (cid CHAR(4) NOT NULL, color_name VARCHAR(4) NOT NULL, `main_photoUrl` VARCHAR(40) NOT NULL, `detail_photoUrl_1` VARCHAR(40) NOT NULL, `detail_photoUrl_2` VARCHAR(40) NOT NULL, `png_photoUrl` VARCHAR(40), `collocation_photoUrl` VARCHAR(40), collocation_id CHAR(6), color_block VARCHAR(40), fkey_2_products CHAR(6), PRIMARY KEY (cid, color_name));
CREATE TABLE orders (id INT UNSIGNED NOT NULL AUTO_INCREMENT, customer_email VARCHAR(60) NOT NULL, order_date DATE NOT NULL, order_time TIME(10) NOT NULL, payment_type VARCHAR(10) NOT NULL, payment_fee DOUBLE DEFAULT 0  NOT NULL, payment_note VARCHAR(100) NOT NULL, shipping_type VARCHAR(10) NOT NULL, shipping_fee DOUBLE DEFAULT 0  NOT NULL, shipping_note VARCHAR(100) NOT NULL, recipient_name VARCHAR(30) NOT NULL, recipient_email VARCHAR(60) NOT NULL, recipient_phone VARCHAR(20) NOT NULL, shipping_address VARCHAR(100) NOT NULL, status INT DEFAULT 0  NOT NULL, PRIMARY KEY (id));
CREATE TABLE order_items (order_id INT UNSIGNED NOT NULL, product_id CHAR(6) NOT NULL, color VARCHAR(20) NOT NULL, `size` VARCHAR(10) NOT NULL, price DOUBLE NOT NULL, quantity INT UNSIGNED NOT NULL, PRIMARY KEY (order_id, product_id, color, `size`));
CREATE TABLE product_color_size_stock (product_id CHAR(6) NOT NULL, color_name VARCHAR(4) NOT NULL, `size` VARCHAR(5) NOT NULL, stock INT DEFAULT 0  NOT NULL, PRIMARY KEY (product_id, color_name, `size`, stock));

