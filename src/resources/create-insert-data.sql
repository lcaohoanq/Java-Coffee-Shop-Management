CREATE DATABASE dataCoffeeShop;
USE dataCoffeeShop;
use master;
DROP DATABASE dataCoffeeShop;
CREATE TABLE [Ingredient] (
  [id] VARCHAR(4) PRIMARY KEY,
  [name] NVARCHAR(255),
  [quantity] FLOAT,
  [unit] VARCHAR(3),
  [price] MONEY
)
GO

CREATE TABLE [Menu] (
  [id] VARCHAR(4) PRIMARY KEY,
  [name] NVARCHAR(255)
)
GO

CREATE TABLE [Order] (
  [id] INT PRIMARY KEY IDENTITY(1, 1),
  [m_id] VARCHAR(4),
  [order_time] datetime,
  [quantity] INT
)
GO

CREATE TABLE [IngredientMenu] (
  [i_id] VARCHAR(4),
  [m_id] VARCHAR(4),
  [quantity] FLOAT,
  FOREIGN KEY ([i_id]) REFERENCES [Ingredient] ([id]),
  FOREIGN KEY ([m_id]) REFERENCES [Menu] ([id]),
  PRIMARY KEY ([i_id], [m_id])
)
GO

CREATE TABLE [MenuOrder] (
  [m_id] VARCHAR(4),
  [o_id] INT,
  FOREIGN KEY ([m_id]) REFERENCES [Menu] ([id]),
  FOREIGN KEY ([o_id]) REFERENCES [Order] ([id]),
  PRIMARY KEY ([m_id], [o_id])
)
GO

-- --------------------INSERT DATA---------------------------

-- Insert data into Ingredient table
INSERT INTO Ingredient (id, name, quantity, unit, price)
VALUES 
('I001', N'Sugar', 2.5, 'kg', 15.99),
('I002', N'Coffee Beans', 1.5, 'kg', 25.49),
('I003', N'Milk', 3, 'l', 10.75),
('I004', N'Chocolate Syrup', 0.5, 'l', 8.99),
('I005', N'Espresso Shot', 0.03, 'l', 5.99),
('I006', N'Whipped Cream', 0.2, 'l', 7.49),
('I007', N'Vanilla Syrup', 0.5, 'l', 6.99),
('I008', N'Caramel Sauce', 0.5, 'l', 9.99),
('I009', N'Almond Milk', 1, 'l', 12.99),
('I010', N'Ice Cream', 0.5, 'l', 8.49);

-- Insert data into Menu table
INSERT INTO Menu (id, name)
VALUES 
('M001', N'Coffee'),
('M002', N'Cappuccino'),
('M003', N'Latte'),
('M004', N'Mocha');

-- Insert data into Order table
INSERT INTO [Order] (m_id, order_time, quantity)
VALUES 
('M001', '2024-03-23 08:00:00', 2),
('M002', '2024-03-23 09:15:00', 1),
('M003', '2024-03-23 10:30:00', 3),
('M004', '2024-03-23 11:45:00', 1);

-- Insert data into IngredientMenu table
INSERT INTO IngredientMenu (i_id, m_id, quantity)
VALUES 
('I001', 'M001', 0.1),
('I002', 'M001', 0.2),
('I003', 'M001', 0.05),
('I001', 'M002', 0.1),
('I002', 'M002', 0.2),
('I003', 'M002', 0.05),
('I001', 'M003', 0.1),
('I002', 'M003', 0.2),
('I003', 'M003', 0.05),
('I001', 'M004', 0.1),
('I002', 'M004', 0.2),
('I003', 'M004', 0.05),
('I004', 'M004', 0.05);

-- Insert data into MenuOrder table
INSERT INTO MenuOrder (m_id, o_id)
VALUES 
('M001', 1),
('M002', 2),
('M003', 3),
('M004', 4);

-- ==========================QUERY ==========================
SELECT * FROM Ingredient