DROP TABLE IF EXISTS product CASCADE

CREATE TABLE product (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `price` INT NULL,
  PRIMARY KEY (`id`))

INSERT INTO product (name, price) VALUES
('milk', 10),
('rice', 5);