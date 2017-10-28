create table aisle(aisle_id VARCHAR(255), aisle VARCHAR(255));
create table orders(order_id VARCHAR(255), user_id VARCHAR(255), eval_set VARCHAR(255), order_number BIGINT, order_day_of_the_week BIGINT, order_hour_of_day BIGINT, days_since_prior_order BIGINT);
create table product(product_id VARCHAR(255), name VARCHAR(255), aisle_id VARCHAR(255), department_id VARCHAR(255));
create table orders_products_train(order_id VARCHAR(255), product_id VARCHAR(255), add_to_cart_order BIGINT, reordered BOOLEAN);
create table orders_products_prior(order_id VARCHAR(255), product_id VARCHAR(255), add_to_cart_order BIGINT, reordered BOOLEAN);
create table department(department_id VARCHAR(255), department VARCHAR(255));
