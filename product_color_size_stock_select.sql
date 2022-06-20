SELECT id,color_name,product_color_size_stock.size,product_color_size_stock.stock as the_stock FROM products LEFT JOIN product_color_size_stock ON products.id=product_color_size_stock.product_id
WHERE products.id='AB7745' AND color_name='深灰藍' AND product_color_size_stock.size='L';
