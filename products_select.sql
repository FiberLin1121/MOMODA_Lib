-- 查全部產品
SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products GROUP BY products.id;

-- 用部分名稱查詢產品 
SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products WHERE name LIKE ? GROUP BY products.id;

-- 用產品編號查詢產品
SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products WHERE id =?;

-- 用產品編號,顏色,照片編號查詢產品照片
SELECT main_photoUrl,detail_photoUrl_1,detail_photoUrl_2,png_photoUrl,collocation_photoUrl FROM products 
INNER JOIN color ON products.id=color.fkey_2_products WHERE id=? and color_name=?;

-- 用分類查詢產品
SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products 
WHERE type=? GROUP BY products.id;

-- 用分類查詢產品
SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products 
WHERE outfit=? GROUP BY products.id;

--修改欄位值為NULL
update colors set collocation_id = NULL
where cid =?;

--ORDER BY排序:
--小提醒:order by中列，應該出現在group by子句中
--查全部產品(價錢排序:低到高)
SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products GROUP BY products.id,unitPrice ORDER BY unitPrice*(100-discount)/100;
--查全部產品(價錢排序:高到低)
SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products GROUP BY products.id,unitPrice ORDER BY unitPrice*(100-discount)/100 DESC;
--查全部產品(價錢排序:高到低)
SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products GROUP BY products.id,launch_date ORDER BY launch_date DESC;


--用部分名稱查詢產品 (價錢排序:低到高)
SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products WHERE name LIKE ? GROUP BY products.id,unitPrice ORDER BY unitPrice*(100-discount)/100;
--用部分名稱查詢產品 (價錢排序:高到低)
SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products WHERE name LIKE ? GROUP BY products.id,unitPrice ORDER BY unitPrice*(100-discount)/100 DESC;

--用部分名稱查詢產品 (時間排序:新到舊)
SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products WHERE name LIKE ? GROUP BY products.id,launch_date ORDER BY launch_date;

--用分類查詢產品 (價錢排序:低到高)
SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products WHERE type = ? GROUP BY products.id,unitPrice ORDER BY unitPrice*(100-discount)/100;
--用分類查詢產品 (價錢排序:高到低)
SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products WHERE type = ? GROUP BY products.id,unitPrice ORDER BY unitPrice*(100-discount)/100 DESC;
--用部分名稱查詢產品 (時間排序:新到舊)
SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products WHERE type = ? GROUP BY products.id,launch_date ORDER BY launch_date;

--抓最近一週上架的產品 (時間排序:新到舊)
SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products WHERE launch_date >= ? GROUP BY products.id,launch_date ORDER BY launch_date DESC;