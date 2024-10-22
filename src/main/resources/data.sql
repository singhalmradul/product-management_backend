BEGIN TRANSACTION;

INSERT INTO category (id, name, unit_preference, description, images)
VALUES
    ('1', 'Fruits', 'KG', 'Fruits are good for health', ARRAY['https://i.ibb.co/Ksfwpms/bear-high-five.png']),
    ('2', 'Vegetables', 'KG', 'Vegetables are good for health', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg']),
    ('3', 'Dairy', 'BOXES', 'Dairy products are good for health', ARRAY['https://i.ibb.co/Z8YdR2N/lizard-high-five.png']),
    ('4', 'Bakery', 'PCS', 'Bakery products are good for health', ARRAY['https://i.ibb.co/WFLHKHm/eagle-high-five.png']),
    ('5', 'Meat', 'KG', 'Meat products are good for health', ARRAY['https://i.ibb.co/VTMfYzP/crab-high-five.png']),
    ('6', 'Seafood', 'KG', 'Seafood products are good for health', ARRAY['https://i.ibb.co/y6k9K57/fish-high-five.png']),
    ('7', 'Poultry', 'KG', 'Poultry products are good for health', ARRAY['https://i.ibb.co/gP4LDPn/elephant-high-five.png','https://i.ibb.co/1Rzxj4B/mechanical-high-five.jpg']),
    ('8', 'Beverages', 'BOXES', 'Beverages are good for health', ARRAY['https://i.ibb.co/v38Zyks/robot-high-five.jpg','https://i.ibb.co/4Yr7z0J/cosmic-high-five.jpg'])
;

INSERT INTO product (id, code, name, weight, length, width, height, images, description, unit_preference, variant_group_id)
VALUES
    ('1', 'P001', 'Apple', '1200', '12', '11', '13', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Apple is good for health', 'KG', NULL),
    ('2', 'P002', 'Banana', '1100', '11', '10', '12', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Banana is good for health', 'KG', NULL),
    ('3', 'P003', 'Orange', '1300', '13', '14', '12', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Orange is good for health', 'KG', NULL),
    ('4', 'P004', 'Mango', '1500', '15', '16', '14', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Mango is good for health', 'KG', NULL),
    ('5', 'P005', 'Pineapple', '1800', '18', '19', '17', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Pineapple is good for health', 'KG', NULL),
    ('6', 'P006', 'Grapes', '500', '5', '4', '6', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Grapes is good for health', 'KG', NULL),
    ('7', 'P007', 'Strawberry', '300', '3', '2', '4', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Strawberry is good for health', 'KG', NULL),
    ('8', 'P008', 'Watermelon', '2000', '20', '19', '21', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Watermelon is good for health', 'KG', NULL),
    ('9', 'P009', 'Cucumber', '600', '6', '7', '5', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Cucumber is good for health', 'KG', NULL),
    ('10', 'P010', 'Tomato', '400', '4', '2', '3', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Tomato is good for health', 'KG', NULL),
    ('11', 'P011', 'Potato', '700', '7', '7', '8', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Potato is good for health', 'KG', NULL),
    ('12', 'P012', 'Onion', '500', '5', '10', '9', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Onion is good for health', 'KG', NULL),
    ('13', 'P013', 'Garlic', '200', '2', '1', '1', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Garlic is good for health', 'KG', NULL),
    ('14', 'P014', 'Bread', '800', '8', '13', '1', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Bread is good for health', 'PCS', NULL),
    ('15', 'P015', 'Cake', '1000', '10', '15', '20', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Cake is good for health', 'PCS', NULL),
    ('16', 'P016', 'Milk', '1200', '12', '14', '1', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Milk is good for health', 'BOXES', NULL),
    ('17', 'P017', 'Cheese', '900', '9', '6', '4', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Cheese is good for health', 'BOXES', NULL),
    ('18', 'P018', 'Butter', '700', '7', '5', '4', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Butter is good for health', 'BOXES', NULL),
    ('19', 'P019', 'Biscuit', '300', '3', '2', '2', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Biscuit is good for health', 'PCS', NULL),
    ('20', 'P020', 'Chocolate', '400', '4', '1', '11', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Chocolate is good for health', 'PCS', NULL),
    ('21', 'P021', 'Chicken', '1500', '15', '10', '12', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Chicken is good for health', 'KG', NULL),
    ('22', 'P022', 'Fish', '1300', '13', '12', '10', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Fish is good for health', 'KG', '1'),
    ('23', 'P023', 'Prawn', '1100', '11', '13', '20', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Prawn is good for health', 'KG', '1'),
    ('24', 'P024', 'Crab', '1400', '14', '12', '11', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Crab is good for health', 'KG', '1'),
    ('25', 'P025', 'Lobster', '1600', '16', '15', '17', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Lobster is good for health', 'KG', '1'),
    ('26', 'P026', 'Beef', '1800', '18', '15', '17', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Beef is good for health', 'KG', '2'),
    ('27', 'P027', 'Pork', '1700', '17', '18', '15', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Pork is good for health', 'KG', '2'),
    ('28', 'P028', 'Turkey', '1900', '19', '2', '12', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Turkey is good for health', 'KG', NULL),
    ('29', 'P029', 'Duck', '1400', '14', '18', '17', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Duck is good for health', 'KG', NULL),
    ('30', 'P030', 'Goose', '1600', '16', '19', '13', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Goose is good for health', 'KG', NULL),
    ('31', 'P031', 'Beer', '1200', '12', '10', '10', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Beer is good for health', 'BOXES', NULL),
    ('32', 'P032', 'Wine', '1300', '13', '16', '13', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Wine is good for health', 'BOXES', NULL),
    ('33', 'P033', 'Whiskey', '1400', '14', '6', '3', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Whiskey is good for health', 'BOXES', NULL),
    ('34', 'P034', 'Vodka', '1500', '15', '6', '5', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Vodka is good for health', 'BOXES', NULL),
    ('35', 'P035', 'Rum', '1600', '16', '6', '7', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Rum is good for health', 'BOXES', NULL),
    ('36', 'P036', 'Tequila', '1700', '17', '5', '7', ARRAY['https://i.ibb.co/pz2dWyC/cat-high-five.jpg'], 'Tequila is good for health', 'BOXES', NULL)
;

INSERT INTO product_category (product_id, category_id)
VALUES
    ('1', '1'),
    ('2', '1'),
    ('3', '1'),
    ('4', '1'),
    ('5', '1'),
    ('6', '1'),
    ('7', '1'),
    ('8', '1'),
    ('9', '2'),
    ('10', '2'),
    ('11', '2'),
    ('12', '2'),
    ('13', '2'),
    ('14', '4'),
    ('15', '4'),
    ('16', '3'),
    ('17', '3'),
    ('18', '3'),
    ('19', '4'),
    ('20', '4'),
    ('21', '7'),
    ('22', '6'),
    ('23', '6'),
    ('24', '6'),
    ('25', '6'),
    ('26', '5'),
    ('27', '5'),
    ('28', '5'),
    ('29', '5'),
    ('30', '5'),
    ('31', '8'),
    ('32', '8'),
    ('33', '8'),
    ('34', '8'),
    ('35', '8'),
    ('36', '8')
;

INSERT INTO customer (id, name, phone_number, email, gstin, description)
VALUES
    ('1', 'John Doe', '1234567891', 'doejohn@email.io', 'GSTIN123456', 'John Doe is a good customer'),
    ('2', 'Jane Doe', '1234567892', 'doejane@email.io', 'GSTIN123457', 'Jane Doe is a good customer'),
    ('3', 'John Smith', '1234567893', 'smithjohn@email.io', 'GSTIN123458', 'John Smith is a good customer'),
    ('4', 'Jane Smith', '1234567890', 'smithjane@email.io', 'GSTIN123459', 'Jane Smith is a good customer')
;

INSERT INTO order_request (id, customer_id, date, pdf)
VALUES
    ('1', '1', '2021-01-01', 'https://res.cloudinary.com/singhalmradul/image/upload/resume-singhalmradul_grjr8r.pdf'),
    ('2', '2', '2021-01-02', 'https://res.cloudinary.com/singhalmradul/image/upload/resume-singhalmradul_grjr8r.pdf'),
    ('3', '3', '2021-01-03', 'https://res.cloudinary.com/singhalmradul/image/upload/resume-singhalmradul_grjr8r.pdf'),
    ('4', '4', '2021-01-04', 'https://res.cloudinary.com/singhalmradul/image/upload/resume-singhalmradul_grjr8r.pdf')
;

INSERT INTO order_product (order_id, product_id, amount, unit)
VALUES
    ('1', '1', 1, 'KG'),
    ('1', '2', 2, 'KG'),
    ('1', '3', 3, 'KG'),
    ('2', '4', 4, 'PCS'),
    ('2', '5', 5, 'PCS'),
    ('2', '6', 6, 'PCS'),
    ('3', '7', 7, 'KG'),
    ('3', '8', 8, 'KG'),
    ('3', '9', 9, 'KG'),
    ('4', '10', 10, 'PCS'),
    ('4', '11', 11, 'PCS'),
    ('4', '12', 12, 'PCS')
;

COMMIT;