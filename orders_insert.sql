-- INSERT INTO orders
-- (id,customer_id,order_date,order_time,payment_type,payment_fee,
-- shipping_type,shipping_fee,recipient_name,recipient_email,recipient_phone,shipping_address)
-- VALUES(?,?,?,?,?,?,?,?,?,?,?,?);
-- 
-- INSERT INTO order_items
-- (order_id,product_id,color,size,price,quantity)
-- VALUES(?,?,?,?,?,?);
-- 
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '01:06:58', 'ATM', 0.0, '', 'SHOP', 0.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市新生南路100號', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '01:07:11', 'ATM', 0.0, '', 'SHOP', 0.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市新生南路100號', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '14:57:27', 'ATM', 0.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '15:21:55', 'ATM', 0.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '15:23:02', 'ATM', 0.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '15:26:39', 'ATM', 0.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '15:46:28', 'ATM', 0.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '19:10:08', 'ATM', 0.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '19:13:40', 'ATM', 0.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '19:17:14', 'HOME', 50.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '19:21:10', 'ATM', 0.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '21:00:23', 'HOME', 50.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '21:23:12', 'HOME', 50.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '21:24:44', 'ATM', 0.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '21:32:20', 'ATM', 0.0, '', 'STORE', 65.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '21:34:01', 'SHOP', 0.0, '', 'SHOP', 0.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '21:36:37', 'ATM', 0.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '21:39:18', 'HOME', 50.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '21:41:46', 'HOME', 50.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-06', '21:46:45', 'ATM', 0.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-07', '02:15:33', 'HOME', 50.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-07', '02:31:05', 'SHOP', 0.0, '', 'SHOP', 0.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-07', '04:29:28', 'SHOP', 0.0, '', 'SHOP', 0.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-07', '04:30:59', 'SHOP', 0.0, '', 'SHOP', 0.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-07', '15:41:33', 'STORE', 0.0, '', 'STORE', 65.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange03@gmail.com', '2019-04-07', '23:59:25', 'SHOP', 0.0, '', 'SHOP', 0.0, '', '林小葵', 'orange03@gmail.com', '0987654321', '博愛街1號6F', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange06@gmail.com', '2019-04-08', '15:01:41', 'HOME', 50.0, '', 'HOME', 100.0, '', '林米雪', 'orange06@gmail.com', '0987654321', '承德路一段39巷26號', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-09', '14:20:29', 'ATM', 0.0, '', 'SHOP', 0.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '台北市新生南路100號', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange06@gmail.com', '2019-04-09', '14:21:58', 'ATM', 0.0, '', 'HOME', 100.0, '', '林米雪', 'orange06@gmail.com', '0987654321', '承德路一段39巷26號', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange06@gmail.com', '2019-04-09', '14:23:05', 'ATM', 0.0, '', 'HOME', 100.0, '', '林米雪', 'orange06@gmail.com', '0987654321', '承德路一段39巷26號', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-10', '13:36:32', 'STORE', 0.0, '', 'STORE', 65.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '1918,全家永和保平店,新北市永和區永貞路354號，356號', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange06@gmail.com', '2019-04-16', '11:42:03', 'SHOP', 0.0, '', 'SHOP', 0.0, '', '林米雪', 'orange06@gmail.com', '0987654321', '敦南門市-台北市大安區敦化南路161巷4號', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-16', '14:17:45', 'STORE', 0.0, '', 'STORE', 65.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '14482,全家通昌店,台北市大安區文昌街142號壹樓全部', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange06@gmail.com', '2019-04-16', '22:36:27', 'ATM', 0.0, '', 'HOME', 100.0, '', '林米雪', 'orange06@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange06@gmail.com', '2019-04-16', '22:38:46', 'ATM', 0.0, '', 'HOME', 100.0, '', '林米雪', 'orange06@gmail.com', '0987654321', '承德路一段39巷26號', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange06@gmail.com', '2019-04-16', '22:42:05', 'ATM', 0.0, '', 'HOME', 100.0, '', '林米雪', 'orange06@gmail.com', '0987654321', '承德路一段39巷26號', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange06@gmail.com', '2019-04-16', '22:44:30', 'ATM', 0.0, '', 'HOME', 100.0, '', '林米雪', 'orange06@gmail.com', '0987654321', '台北市復興北路二段', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange01@gmail.com', '2019-04-16', '22:52:02', 'ATM', 0.0, '', 'HOME', 100.0, '', '林橘比', 'orange01@gmail.com', '0987654321', '承德路一段39巷26號', 0);
INSERT INTO mmd.orders (customer_email, order_date, order_time, payment_type, payment_fee, payment_note, shipping_type, shipping_fee, shipping_note, recipient_name, recipient_email, recipient_phone, shipping_address, status) 
	VALUES ('orange06@gmail.com', '2019-04-16', '22:56:39', 'ATM', 0.0, '', 'HOME', 100.0, '', '林米雪', 'orange06@gmail.com', '0987654321', '承德路一段39巷26號', 0);
