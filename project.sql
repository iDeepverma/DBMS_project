create table Supplier(
	name varchar(255),
 	supplierID int,
 	phone int,
 	address varchar(255),
 	-- houseNo int,
 	-- street int,
 	-- city varchar(255),
 	-- pin int,
 	moneySpent int,
 	ordersFulfilled int,
	email varchar(255),
    PRIMARY KEY (supplierID)
    
);

create table ProductSuppliers(
	supplierID int,
	productID int,
	FOREIGN KEY (supplierID) REFERENCES Supplier(supplierID) ON DELETE CASCADE,
	FOREIGN KEY (productID) REFERENCES Product(productID) ON DELETE CASCADE,
	PRIMARY KEY(supplierID,productID)
);

create table SupplyOrderItem(
	-- lineNo int,
	supplyOrderID int,
	quantity int,
	total int,
	productID int,
	additionalInfo LONGTEXT,
	PRIMARY KEY(productID,supplyOrderID),
	FOREIGN KEY (supplyOrderID) REFERENCES SupplyOrder(orderID) ON DELETE CASCADE,
	FOREIGN KEY (productID) REFERENCES Product(productID) ON DELETE CASCADE

);

create table SupplyOrder(
	orderID int,
	orderDate date,
	deliveryDate date,
	deliveryStatus varchar(255),
	totalAmount int,
	supplierID int,
	placedBy int,
	PRIMARY KEY(orderID),
	FOREIGN KEY (supplierID) REFERENCES Supplier(supplierID) ON DELETE CASCADE,
	FOREIGN KEY (placedBy) REFERENCES Employee(empID) ON DELETE CASCADE
);

create table Employee(
	empID int,
	name varchar(255),
	DOB date,
	email varchar(255),
	phone int,
	salary  int,
	joinDate date,
	role varchar(255),
	address varchar(255),
	-- houseNo int,
	-- street int,
	-- city int,
	-- pin int,
	PRIMARY KEY(empID)


);

create table Product(
 	productID int,
 	description LONGTEXT,
 	-- category varchar(255),
 	warrantyLenght int,
 	warrantyCoverage LONGTEXT,
 	MRP int,
 	costPrice int,
 	variant int,
 	amountInStock int,
 	name varchar(255),                 
 	PRIMARY KEY (productID),
 	FOREIGN KEY (name) REFERENCES ProductCategory(name) ON DELETE CASCADE
    
);

create table ProductCategory(
	name varchar(255),
	category varchar(255),
	PRIMARY KEY (name)
);

create table CustomerOrder(
	orderID int,
	orderDate date,
	transactionID int,
	modeOfPayment varchar(255),
	total int,
	customerID int,
	servedBy int,
	PRIMARY KEY(orderID),
	FOREIGN KEY (customerID) REFERENCES Customer(customerID) ON DELETE CASCADE,
	FOREIGN KEY (servedBy) REFERENCES Employee(empID) ON DELETE CASCADE
);
	
create table Customer(
	customerID int,
	name varchar(255),
	phone int,
	email varchar(255),
	dateOfBirth date,
	address varchar(255),
	-- houseNo int,
	-- street int,
	-- city varchar(255),
	-- pin int,
	PRIMARY KEY(customerID)
);

create table InventoryItem(
	itemID int,
	productID int,
	supplyOrderID int,
	-- lineNo int,
	orderID int,
	PRIMARY KEY(itemID,productID),
	FOREIGN KEY (productID) REFERENCES Product(productID),
	FOREIGN KEY (supplyOrderID) REFERENCES SupplyOrder(orderID) ON DELETE CASCADE,
	-- FOREIGN KEY (lineNo) REFERENCES customerOrderItem(lineNo) ON DELETE CASCADE,
	FOREIGN KEY (orderID) REFERENCES CustomerOrderItem(orderID) ON DELETE CASCADE
);

create table CustomerOrderItem(
	-- lineNo int,
	orderID int,
	quantity int,
	sellingPrice int,
	productID int,
	additionalInfo LONGTEXT,
	PRIMARY KEY(productID,orderID),
	FOREIGN KEY (orderID) REFERENCES CustomerOrder(orderID) ON DELETE CASCADE,
	FOREIGN KEY (productID) REFERENCES Product(productID) ON DELETE CASCADE

);
