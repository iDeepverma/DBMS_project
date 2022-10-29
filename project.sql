create table Supplier(
	name varchar(255),
 	supplierID int AUTO_INCREMENT,
 	phone int,
 	address varchar(255),
 	moneySpent int,
 	ordersFulfilled int,
	email varchar(255),
    PRIMARY KEY (supplierID)
);
create table Customer(
	customerID int AUTO_INCREMENT,
	name varchar(255),
	phone int,
	email varchar(255),
	DOB date,
	address varchar(255),
	PRIMARY KEY(customerID)
);


create table Employee(
	empID int AUTO_INCREMENT,
	name varchar(255),
	DOB date,
	email varchar(255),
	phone int,
	salary  int,
	joinDate date,
	role varchar(255),
	address varchar(255),
	PRIMARY KEY(empID)
);

create table ProductCategory(
	name varchar(255),
	category varchar(255),
	PRIMARY KEY (name)
);

create table Product(
 	productID int AUTO_INCREMENT,
 	description LONGTEXT,
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

create table ProductSuppliers(
	supplierID int,
	productID int,
	FOREIGN KEY (supplierID) REFERENCES Supplier(supplierID) ON DELETE CASCADE,
	FOREIGN KEY (productID) REFERENCES Product(productID) ON DELETE CASCADE,
	PRIMARY KEY(supplierID,productID)
);
create table SupplyOrder(
	orderID int AUTO_INCREMENT,
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

create table SupplyOrderItem(
	supplyOrderID int,
	quantity int,
	total int,
	productID int,
	additionalInfo LONGTEXT,
	PRIMARY KEY(productID,supplyOrderID),
	FOREIGN KEY (supplyOrderID) REFERENCES SupplyOrder(orderID) ON DELETE CASCADE,
	FOREIGN KEY (productID) REFERENCES Product(productID) ON DELETE CASCADE

);

create table CustomerOrder(
	orderID int AUTO_INCREMENT,
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


create table CustomerOrderItem(
	orderID int,
	quantity int,
	sellingPrice int,
	productID int,
	additionalInfo LONGTEXT,
	PRIMARY KEY(productID,orderID),
	FOREIGN KEY (orderID) REFERENCES CustomerOrder(orderID) ON DELETE CASCADE,
	FOREIGN KEY (productID) REFERENCES Product(productID) ON DELETE CASCADE

);

create table InventoryItem(
	itemID int AUTO_INCREMENT,
	productID int,
	supplyOrderID int,
	orderID int,
	PRIMARY KEY(itemID,productID),
	FOREIGN KEY (productID) REFERENCES Product(productID),
	FOREIGN KEY (supplyOrderID) REFERENCES SupplyOrder(orderID) ON DELETE CASCADE,
	FOREIGN KEY (orderID) REFERENCES CustomerOrderItem(orderID) ON DELETE CASCADE
);