--修改products的庫存數量
UPDATE products SET stock = stock-? WHERE ? AND stock>=?;

--修改product_color_size_stock庫存數量
UPDATE product_color_size_stock SET stock = stock-? WHERE product_id=? AND stock>=? AND color_name=? AND size=? ;