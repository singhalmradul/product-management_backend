BEGIN TRANSACTION;

INSERT INTO category (id, name, unit_preference, description, images)
VALUES
    ('00000000-0000-0000-0000-000000000001', 'Fruits', 'KG', 'Fruits are good for health', ARRAY['https://i.ibb.co/Ksfwpms/bear-high-five.png']),
    ('00000000-0000-0000-0000-000000000002', 'Vegetables', 'KG', 'Vegetables are good for health', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg']),
    ('00000000-0000-0000-0000-000000000003', 'Dairy', 'BOXES', 'Dairy products are good for health', ARRAY['https://i.ibb.co/Z8YdR2N/lizard-high-five.png']),
    ('00000000-0000-0000-0000-000000000004', 'Bakery', 'PCS', 'Bakery products are good for health', ARRAY['https://i.ibb.co/WFLHKHm/eagle-high-five.png']),
    ('00000000-0000-0000-0000-000000000005', 'Meat', 'KG', 'Meat products are good for health', ARRAY['https://i.ibb.co/VTMfYzP/crab-high-five.png']),
    ('00000000-0000-0000-0000-000000000006', 'Seafood', 'KG', 'Seafood products are good for health', ARRAY['https://i.ibb.co/y6k9K57/fish-high-five.png']),
    ('00000000-0000-0000-0000-000000000007', 'Poultry', 'KG', 'Poultry products are good for health', ARRAY['https://i.ibb.co/gP4LDPn/elephant-high-five.png','https://i.ibb.co/1Rzxj4B/mechanical-high-five.jpg']),
    ('00000000-0000-0000-0000-000000000008', 'Beverages', 'BOXES', 'Beverages are good for health', ARRAY['https://i.ibb.co/v38Zyks/robot-high-five.jpg','https://i.ibb.co/4Yr7z0J/cosmic-high-five.jpg'])
;

INSERT INTO product (id, code, name, weight, length, width, height, images, description, unit_preference, variant_group_id)
VALUES
    ('00000000-0000-0000-0000-000000000001', 'P001', 'Apple', '1200', '12', '11', '13', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Apple is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-000000000002', 'P002', 'Banana', '1100', '11', '10', '12', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Banana is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-000000000003', 'P003', 'Orange', '1300', '13', '14', '12', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Orange is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-000000000004', 'P004', 'Mango', '1500', '15', '16', '14', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Mango is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-000000000005', 'P005', 'Pineapple', '1800', '18', '19', '17', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Pineapple is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-000000000006', 'P006', 'Grapes', '500', '5', '4', '6', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Grapes is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-000000000007', 'P007', 'Strawberry', '300', '3', '2', '4', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Strawberry is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-000000000008', 'P008', 'Watermelon', '2000', '20', '19', '21', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Watermelon is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-000000000009', 'P009', 'Cucumber', '600', '6', '7', '5', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Cucumber is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-00000000000a', 'P010', 'Tomato', '400', '4', '2', '3', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Tomato is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-00000000000b', 'P011', 'Potato', '700', '7', '7', '8', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Potato is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-00000000000c', 'P012', 'Onion', '500', '5', '10', '9', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Onion is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-00000000000d', 'P013', 'Garlic', '200', '2', '1', '1', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Garlic is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-00000000000e', 'P014', 'Bread', '800', '8', '13', '1', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Bread is good for health', 'PCS', NULL),
    ('00000000-0000-0000-0000-00000000000f', 'P015', 'Cake', '1000', '10', '15', '20', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Cake is good for health', 'PCS', NULL),
    ('00000000-0000-0000-0000-000000000010', 'P016', 'Milk', '1200', '12', '14', '1', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Milk is good for health', 'BOXES', NULL),
    ('00000000-0000-0000-0000-000000000011', 'P017', 'Cheese', '900', '9', '6', '4', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Cheese is good for health', 'BOXES', NULL),
    ('00000000-0000-0000-0000-000000000012', 'P018', 'Butter', '700', '7', '5', '4', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Butter is good for health', 'BOXES', NULL),
    ('00000000-0000-0000-0000-000000000013', 'P019', 'Biscuit', '300', '3', '2', '2', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Biscuit is good for health', 'PCS', NULL),
    ('00000000-0000-0000-0000-000000000014', 'P020', 'Chocolate', '400', '4', '1', '11', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Chocolate is good for health', 'PCS', NULL),
    ('00000000-0000-0000-0000-000000000015', 'P021', 'Chicken', '1500', '15', '10', '12', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Chicken is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-000000000016', 'P022', 'Fish', '1300', '13', '12', '10', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Fish is good for health', 'KG', '00000000-0000-0000-0000-000000000001'),
    ('00000000-0000-0000-0000-000000000017', 'P023', 'Prawn', '1100', '11', '13', '20', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Prawn is good for health', 'KG', '00000000-0000-0000-0000-000000000001'),
    ('00000000-0000-0000-0000-000000000018', 'P024', 'Crab', '1400', '14', '12', '11', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Crab is good for health', 'KG', '00000000-0000-0000-0000-000000000001'),
    ('00000000-0000-0000-0000-000000000019', 'P025', 'Lobster', '1600', '16', '15', '17', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Lobster is good for health', 'KG', '00000000-0000-0000-0000-000000000001'),
    ('00000000-0000-0000-0000-00000000001a', 'P026', 'Beef', '1800', '18', '15', '17', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Beef is good for health', 'KG', '00000000-0000-0000-0000-000000000002'),
    ('00000000-0000-0000-0000-00000000001b', 'P027', 'Pork', '1700', '17', '18', '15', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Pork is good for health', 'KG', '00000000-0000-0000-0000-000000000002'),
    ('00000000-0000-0000-0000-00000000001c', 'P028', 'Turkey', '1900', '19', '2', '12', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Turkey is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-00000000001d', 'P029', 'Duck', '1400', '14', '18', '17', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Duck is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-00000000001e', 'P030', 'Goose', '1600', '16', '19', '13', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Goose is good for health', 'KG', NULL),
    ('00000000-0000-0000-0000-00000000001f', 'P031', 'Beer', '1200', '12', '10', '10', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Beer is good for health', 'BOXES', NULL),
    ('00000000-0000-0000-0000-000000000020', 'P032', 'Wine', '1300', '13', '16', '13', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Wine is good for health', 'BOXES', NULL),
    ('00000000-0000-0000-0000-000000000021', 'P033', 'Whiskey', '1400', '14', '6', '3', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Whiskey is good for health', 'BOXES', NULL),
    ('00000000-0000-0000-0000-000000000022', 'P034', 'Vodka', '1500', '15', '6', '5', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Vodka is good for health', 'BOXES', NULL),
    ('00000000-0000-0000-0000-000000000023', 'P035', 'Rum', '1600', '16', '6', '7', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Rum is good for health', 'BOXES', NULL),
    ('00000000-0000-0000-0000-000000000024', 'P036', 'Tequila', '1700', '17', '5', '7', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Tequila is good for health', 'BOXES', NULL)
;

INSERT INTO product_category (product_id, category_id)
VALUES
    ('00000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001'),
    ('00000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000001'),
    ('00000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000001'),
    ('00000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000001'),
    ('00000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000001'),
    ('00000000-0000-0000-0000-000000000006', '00000000-0000-0000-0000-000000000001'),
    ('00000000-0000-0000-0000-000000000007', '00000000-0000-0000-0000-000000000001'),
    ('00000000-0000-0000-0000-000000000008', '00000000-0000-0000-0000-000000000001'),
    ('00000000-0000-0000-0000-000000000009', '00000000-0000-0000-0000-000000000002'),
    ('00000000-0000-0000-0000-00000000000a', '00000000-0000-0000-0000-000000000002'),
    ('00000000-0000-0000-0000-00000000000b', '00000000-0000-0000-0000-000000000002'),
    ('00000000-0000-0000-0000-00000000000c', '00000000-0000-0000-0000-000000000002'),
    ('00000000-0000-0000-0000-00000000000d', '00000000-0000-0000-0000-000000000002'),
    ('00000000-0000-0000-0000-00000000000e', '00000000-0000-0000-0000-000000000004'),
    ('00000000-0000-0000-0000-00000000000f', '00000000-0000-0000-0000-000000000004'),
    ('00000000-0000-0000-0000-000000000010', '00000000-0000-0000-0000-000000000003'),
    ('00000000-0000-0000-0000-000000000011', '00000000-0000-0000-0000-000000000003'),
    ('00000000-0000-0000-0000-000000000012', '00000000-0000-0000-0000-000000000003'),
    ('00000000-0000-0000-0000-000000000013', '00000000-0000-0000-0000-000000000004'),
    ('00000000-0000-0000-0000-000000000014', '00000000-0000-0000-0000-000000000004'),
    ('00000000-0000-0000-0000-000000000015', '00000000-0000-0000-0000-000000000007'),
    ('00000000-0000-0000-0000-000000000016', '00000000-0000-0000-0000-000000000006'),
    ('00000000-0000-0000-0000-000000000017', '00000000-0000-0000-0000-000000000006'),
    ('00000000-0000-0000-0000-000000000018', '00000000-0000-0000-0000-000000000006'),
    ('00000000-0000-0000-0000-000000000019', '00000000-0000-0000-0000-000000000006'),
    ('00000000-0000-0000-0000-00000000001a', '00000000-0000-0000-0000-000000000005'),
    ('00000000-0000-0000-0000-00000000001b', '00000000-0000-0000-0000-000000000005'),
    ('00000000-0000-0000-0000-00000000001c', '00000000-0000-0000-0000-000000000005'),
    ('00000000-0000-0000-0000-00000000001d', '00000000-0000-0000-0000-000000000005'),
    ('00000000-0000-0000-0000-00000000001e', '00000000-0000-0000-0000-000000000005'),
    ('00000000-0000-0000-0000-00000000001f', '00000000-0000-0000-0000-000000000008'),
    ('00000000-0000-0000-0000-000000000020', '00000000-0000-0000-0000-000000000008'),
    ('00000000-0000-0000-0000-000000000021', '00000000-0000-0000-0000-000000000008'),
    ('00000000-0000-0000-0000-000000000022', '00000000-0000-0000-0000-000000000008'),
    ('00000000-0000-0000-0000-000000000023', '00000000-0000-0000-0000-000000000008'),
    ('00000000-0000-0000-0000-000000000024', '00000000-0000-0000-0000-000000000008')
;

INSERT INTO customer (id, name, phone_number, email, gstin, description)
VALUES
    ('00000000-0000-0000-0000-000000000001', 'John Doe', '1234567891', 'doejohn@email.io', 'GSTIN123456', 'John Doe is a good customer'),
    ('00000000-0000-0000-0000-000000000002', 'Jane Doe', '1234567892', 'doejane@email.io', 'GSTIN123457', 'Jane Doe is a good customer'),
    ('00000000-0000-0000-0000-000000000003', 'John Smith', '1234567893', 'smithjohn@email.io', 'GSTIN123458', 'John Smith is a good customer'),
    ('00000000-0000-0000-0000-000000000004', 'Jane Smith', '1234567890', 'smithjane@email.io', 'GSTIN123459', 'Jane Smith is a good customer')
;

INSERT INTO order_request (id, customer_id, date)
VALUES
    ('00000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001', '2021-01-01'),
    ('00000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000002', '2021-01-02'),
    ('00000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000003', '2021-01-03'),
    ('00000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000004', '2021-01-04')
;

INSERT INTO order_product (order_id, product_id, amount, unit)
VALUES
    ('00000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001', 1, 'KG'),
    ('00000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000002', 2, 'KG'),
    ('00000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000003', 3, 'KG'),
    ('00000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000004', 4, 'PCS'),
    ('00000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000005', 5, 'PCS'),
    ('00000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000006', 6, 'PCS'),
    ('00000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000007', 7, 'KG'),
    ('00000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000008', 8, 'KG'),
    ('00000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000009', 9, 'KG'),
    ('00000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-00000000000a', 10, 'PCS'),
    ('00000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-00000000000b', 11, 'PCS'),
    ('00000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-00000000000c', 12, 'PCS')
;

COMMIT;