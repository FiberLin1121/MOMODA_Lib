--亂數產生上衣去背圖和產品編號
SELECT fkey_2_products,png_photoUrl FROM colors WHERE png_photoUrl IS NOT NULL AND fkey_2_products LIKE 'AB%' ORDER BY  RAND() LIMIT 15;

--亂數產生下著去背圖和產品編號
SELECT fkey_2_products,png_photoUrl FROM colors WHERE png_photoUrl IS NOT NULL AND (fkey_2_products LIKE 'CA%' OR fkey_2_products LIKE 'BA%') ORDER BY  RAND() LIMIT 15;
