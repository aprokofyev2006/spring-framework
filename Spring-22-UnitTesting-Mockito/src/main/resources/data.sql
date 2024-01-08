-- INSERT INTO roles(description) VALUES ('Admin');
-- INSERT INTO roles(description) VALUES ('Manager');
-- INSERT INTO roles(description) VALUES ('Employee');

select * from roles;

-- INSERT INTO roles(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, description)
-- VALUES ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Admin'),
--        ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Manager'),
--        ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Employee');
--
--
-- INSERT INTO users(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, enabled,
--                   first_name, last_name, user_name, password, gender, phone, role_id)
-- VALUES ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, true, 'admin', 'admin', 'admin@admin.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK', 'MALE', '0000000000', 1);

--'$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK' --> Abc1