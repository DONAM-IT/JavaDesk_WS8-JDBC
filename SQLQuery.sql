create database SaleManager
use SaleManager

create table Products(
ProductID int,
ProductName nvarchar(50),
UnitPrice float,
Quantity int)

select * from Products
insert into Products values(1,'nam',12.0,5)