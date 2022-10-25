create table Supplier(
	name varchar(255),
 	supplierID int,
 	phone int,
 	houseNo int,
 	street int,
 	city varchar(255),
 	pin int,
 	moneySpent int,
 	ordersFulfilled int,
	email varchar(255),
    PRIMARY KEY (supplierID)
    
);

create table ProductSuppliers(
	supplierID int,
	ProductID int,
	FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id) ON DELETE CASCADE,
	FOREIGN KEY (Product_id) REFERENCES Product(Product_id) ON DELETE CASCADE,
	PRIMARY KEY(supplier_id,Product_id)
);

create table Supply_Order_Item(
	Line_No int,
	Supply_Order_id int,
	Quantity int,
	Total int,
	Product_id int,
	Additional_Info LONGTEXT,
	PRIMARY KEY(Line_No,Supply_Order_id),
	FOREIGN KEY (Supply_Order_id) REFERENCES Supply_Order(Order_id) ON DELETE CASCADE,
	FOREIGN KEY (Product_id) REFERENCES Product(Product_id) ON DELETE CASCADE

);

create table Supply_Order(
	Order_id int,
	Order_Date date,
	Delivery_Date date,
	Delivery_Status varchar(255),
	total_amount int,
	supplier_id int,
	placed_by int,
	PRIMARY KEY(Order_id),
	FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id) ON DELETE CASCADE,
	FOREIGN KEY (placed_by) REFERENCES Employee(emp_id) ON DELETE CASCADE
);

create table Employee(
	emp_id int,
	name varchar(255),
	date_of_birth date,
	email varchar(255),
	phone int,
	salary  int,
	join_date date,
	role varchar(255),
	house_no int,
	street int,
	city int,
	pin int,
	PRIMARY KEY(emp_id)


);

create table Product(
 	Product_id int,
 	Name varchar(255),
 	Description LONGTEXT,
 	Category varchar(255),
 	Warranty_Lenght int,
 	Warranty_Coverage LONGTEXT,
 	MRP int,
 	-- Current_Price int,
 	Variant int,
 	amt_int_stock int,
 	PRIMARY KEY (Product_id)
    
);

create table Customer_Order(
	Order_id int,
	Order_Date date,
	transaction_id int,
	mode_of_payment varchar(255),
	Total int,
	Customer_Id int,
	served_by int,
	PRIMARY KEY(Order_id),
	FOREIGN KEY (Customer_Id) REFERENCES Customer(Customer_Id) ON DELETE CASCADE,
	FOREIGN KEY (served_by) REFERENCES Employee(emp_id) ON DELETE CASCADE
);
	
create table Customer(
	Customer_Id int,
	Name varchar(255),
	Phone int,
	Email varchar(255),
	date_of_birth date,
	House_Number int,
	Street int,
	City varchar(255),
	Pin int,
	PRIMARY KEY(Customer_Id)
);

create table Inventory_item(
	item_id int,
	Product_id int,
	Supply_Order_id int,
	Line_No int,
	Order_id int,
	PRIMARY KEY(item_id,Product_id),
	FOREIGN KEY (Product_id) REFERENCES Product(Product_id),
	FOREIGN KEY (Supply_Order_id) REFERENCES Supply_Order(Order_id) ON DELETE CASCADE,
	FOREIGN KEY (Line_No) REFERENCES Customer_Order_Item(Line_No) ON DELETE CASCADE,
	FOREIGN KEY (Order_id) REFERENCES Customer_Order_Item(Order_id) ON DELETE CASCADE
);

create table Customer_Order_Item(
	Line_No int,
	Order_id int,
	Quantity int,
	price_per_item int,
	Product_id int,
	Additional_Info LONGTEXT,
	PRIMARY KEY(Line_No,Order_id),
	FOREIGN KEY (Order_id) REFERENCES Customer_Order(Order_id) ON DELETE CASCADE,
	FOREIGN KEY (Product_id) REFERENCES Product(Product_id) ON DELETE CASCADE

);
