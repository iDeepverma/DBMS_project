INSERT INTO Employee VALUES (1,'Ram','2006-11-11','',0,0,'2006-11-11','Owner', '');
INSERT INTO Employee VALUES (2,'SHRIRam','2006-11-11','',0,0,'2006-11-11','Employee', '');
INSERT INTO Employee VALUES (3,'JANKIRam','2006-11-11','',0,0,'2006-11-11','Employee', '');
INSERT INTO Employee VALUES (4,'RamSITA','2006-11-11','',0,0,'2006-11-11','Employee', '');
INSERT INTO Employee VALUES (5,'SITARam','2006-11-11','',0,0,'2006-11-11','Employee', '');

INSERT INTO Product VALUES (1,'',0,'', 1400,1100, 1, 10, 'M1 pro 6g');
INSERT INTO Product VALUES (2,'',0,'', 1400,900, 1, 21, 'Samsung charger');
INSERT INTO Product VALUES (3,'',0,'', 1400,1200, 1, 11, 'Asus ROG');
INSERT INTO Product VALUES (4,'',0,'', 2500,1300, 1, 30, 'SONY LED');
INSERT INTO Product VALUES (5,'',0,'', 3000,2500, 1, 40, 'LG smart');

INSERT INTO ProductCategory VALUES ('M1 pro 6g' , 'Phone');
INSERT INTO ProductCategory VALUES ('Samsung charger' , 'Charger');
INSERT INTO ProductCategory VALUES ('Asus ROG' , 'Laptop');
INSERT INTO ProductCategory VALUES ('SONY LED' , 'TV');
INSERT INTO ProductCategory VALUES ('LG smart' , 'Refrigirator');

INSERT INTO Customer VALUES (1, 'Shyam' , 91829, 'shyam@gmail.com', '2001-10-9', 'vatican');
INSERT INTO Customer VALUES (2, 'Mohan' , 914249, 'mohan@gmail.com', '2002-11-9', 'mumbai');
INSERT INTO Customer VALUES (3, 'Thomas' , 98432429, 'THomas@gmail.com', '2003-12-1', 'pune');
INSERT INTO Customer VALUES (4, 'Geeta' , 9142829, 'Geeta@gmail.com', '1999-1-1', 'latur');
INSERT INTO Customer VALUES (5, 'Aahna' , 9421829, 'Aahna@gmail.com', '1998-10-9', 'nashik');

INSERT INTO CustomerOrder VALUES (1 ,'2011-1-2',23478, 'Cash', 2300, 2, 1);
INSERT INTO CustomerOrder VALUES (2 ,'2012-2-1',23238, 'UPI', 2780, 1, 2);
INSERT INTO CustomerOrder VALUES (3 ,'2011-9-11',32478, 'Cash', 4060, 3, 2);
INSERT INTO CustomerOrder VALUES (4 ,'2012-7-1',43478, 'Net-Banking', 3920, 2, 3);
INSERT INTO CustomerOrder VALUES (5 ,'2013-11-6',23218, 'DebitCard', 1300, 1, 2);

INSERT INTO CustomerOrderItem VALUES(1 , 1 , 1300, 1 , '');
INSERT INTO CustomerOrderItem VALUES(1 , 1 , 1000, 2 , '');
INSERT INTO CustomerOrderItem VALUES(2 , 1 , 1400, 3 , '');
INSERT INTO CustomerOrderItem VALUES(2 , 1 , 1380, 4 , '');
INSERT INTO CustomerOrderItem VALUES(3 , 2 , 1380, 4 , '');
INSERT INTO CustomerOrderItem VALUES(3 , 1 , 1300, 3 , '');
INSERT INTO CustomerOrderItem VALUES(4 , 1 , 1320, 4 , '');
INSERT INTO CustomerOrderItem VALUES(4 , 1 , 2600, 5 , '');
INSERT INTO CustomerOrderItem VALUES(5 , 1 , 1300 ,1 , '');

INSERT INTO Supplier VALUES('MukeshBhai' , 1 , 872321, '', 12000 , 3 , '');
INSERT INTO Supplier VALUES('Kamlesh' , 2 , 2313213, '', 16000 , 5 , '');
INSERT INTO Supplier VALUES('Bhadresh' , 3 , 2132323, '', 20000 , 8 , '');

INSERT INTO ProductSuppliers VALUES(1 , 2);
INSERT INTO ProductSuppliers VALUES(1 , 3);
INSERT INTO ProductSuppliers VALUES(1 , 1);
INSERT INTO ProductSuppliers VALUES(2 , 1);
INSERT INTO ProductSuppliers VALUES(2 , 5);
INSERT INTO ProductSuppliers VALUES(3 , 4);
INSERT INTO ProductSuppliers VALUES(3 , 5);
INSERT INTO ProductSuppliers VALUES(3 , 3);

INSERT INTO SupplyOrder VALUES(1, '2010-3-2' , '2011-1-1', 'Delivered', 6000, 1, 2);
INSERT INTO SupplyOrder VALUES(2,'2009-2-2' , '2011-9-7', 'Delivered', 6000, 2, 3);
INSERT INTO SupplyOrder VALUES(3, '2007-3-12', NULL, 'In transit', 6000, 2, 4);
INSERT INTO SupplyOrder VALUES(4, '2009-3-2' , NULL, 'In transit', 6000, 2, 3);
INSERT INTO SupplyOrder VALUES(5, '2009-3-12', '2012-4-9', 'Delivered', 6000, 3, 5);

INSERT INTO SupplyOrderItem VALUES(1, 3, 722, 2, '');
INSERT INTO SupplyOrderItem VALUES(1, 2, 4734, 1, '');
INSERT INTO SupplyOrderItem VALUES(1, 1, 218, 3, '');
INSERT INTO SupplyOrderItem VALUES(2, 1, 173, 1, '');
INSERT INTO SupplyOrderItem VALUES(2, 1, 422, 4, '');
INSERT INTO SupplyOrderItem VALUES(2, 1, 321, 5, '');
INSERT INTO SupplyOrderItem VALUES(5, 2, 422, 4, '');
INSERT INTO SupplyOrderItem VALUES(5, 1, 189, 5, '');

INSERT INTO InventoryItem VALUES(1,2,1,1);
INSERT INTO InventoryItem VALUES(2,1,2,2);
INSERT INTO InventoryItem VALUES(3,4,5,2);
INSERT INTO InventoryItem VALUES(4,5,5,3);