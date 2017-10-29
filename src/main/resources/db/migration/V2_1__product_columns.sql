alter table product add column is_food_department boolean
update product set is_food_department = false where product_id in (SELECT product_id FROM PRODUCT p inner join department d on p.department_id = d.department_id where department = 'other' or department = 'pets' or department = 'personal care' or department = 'household' or department = 'babies' or department = 'missing')
update product set is_food_department = true where is_food_department is null
