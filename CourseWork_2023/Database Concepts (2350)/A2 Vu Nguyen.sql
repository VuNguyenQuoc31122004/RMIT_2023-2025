--Assignment 2
--Student Name: Vu Nguyen
--Student Id: s4010423

--Q1
SELECT COUNT(*) AS TotalOrders
FROM ORDERS
WHERE TO_CHAR(orderDate, 'MM/YYYY') = '09/2021';


--Q2
SELECT SUM(itemPrice * itemQuantityAvailable) AS TotalValue
FROM ITEMS;


--Q3
SELECT
  custFName || ' ' || custLName AS "Customer Name",
  custAddress AS "Address",
  custTown AS "Town",
  custPostcode AS "Postcode"
FROM CUSTOMERS;


--Q4


--Q5
SELECT
  I.itemDescription AS "Item Description",
  I.itemPrice AS "Item Price"
FROM ITEMS I
JOIN ORDER_ITEMS OI ON I.itemID = OI.itemID
ORDER BY I.itemPrice ASC;


--Q6
SELECT
  C.custFName AS "Customer First Name",
  C.custEmail AS "Email Address",
  I.itemDescription AS "Item Description"
FROM CUSTOMERS C
JOIN ORDERS O ON C.custID = O.custID
JOIN ORDER_ITEMS OI ON O.orderNo = OI.orderNo
JOIN ITEMS I ON OI.itemID = I.itemID;

--Q7
SELECT DISTINCT
  I.itemID AS "Item ID",
  I.itemDescription AS "Item Description"
FROM ITEMS I
JOIN ORDER_ITEMS OI ON I.itemID = OI.itemID
JOIN ORDERS O ON OI.orderNo = O.orderNo
WHERE O.dispatchDate <= TO_DATE('21/AUG/21', 'DD/MON/YY');


--Q8
SELECT C.custFName, C.custPhone
FROM CUSTOMERS C
JOIN ORDERS O ON C.custID = O.custID
JOIN ORDER_ITEMS OI ON O.orderNo = OI.orderNo
JOIN ITEMS I ON OI.itemID = I.itemID
WHERE I.itemDescription = 'Multisport Watch';



--Q9
SELECT
  orderNo AS "Order Number",
  TO_NUMBER(dispatchDate - orderDate) AS "Dispatch Time (Days)"
FROM ORDERS
ORDER BY TO_NUMBER(dispatchDate - orderDate) DESC
FETCH FIRST 1 ROW ONLY;

--Q10
SELECT
  I.itemID AS "Item ID",
  I.itemDescription AS "Item Description"
FROM ITEMS I
WHERE I.itemID IN (
  SELECT itemID
  FROM SHOP_ITEMS
  GROUP BY itemID
  HAVING COUNT(DISTINCT shopID) > 1
);

--Q11
UPDATE ITEMS
SET itemPrice = itemPrice * 1.075
WHERE itemColour = 'Blue';

--Q12
UPDATE ORDERS
SET dispatchDate = TO_DATE('15/AUG/21', 'DD/MON/YY')
WHERE orderNo = '1';

--Q13

CREATE TABLE CUSTOMERS_COPY AS SELECT * FROM CUSTOMERS;
ALTER TABLE CUSTOMERS_COPY DROP COLUMN custEmail;
ALTER TABLE CUSTOMERS_COPY MODIFY (custPhone NUMBER(10));

--Q14
UPDATE ORDERS
SET dispatchDate = NULL
WHERE dispatchDate IS NOT NULL
  AND TO_DATE('15/SEP/21', 'DD/MON/YY') < dispatchDate
  AND custID IN (SELECT custID FROM CUSTOMERS WHERE shopID = 'MEL3000');

--Q15
CREATE VIEW view_sydney AS
SELECT *
FROM SUPPLIERS
WHERE suppTown = 'Sydney';

--Q16

UPDATE view_sydney
SET suppPhone = 'NEW_PHONE_NUMBER'
WHERE suppName = 'SupplierNameToUpdate';

UPDATE SUPPLIERS
SET suppAddress = 'NEW_ADDRESS'
WHERE suppTown = 'Sydney' AND suppName = 'SupplierNameToUpdate';



--Q17

ALTER TABLE CUSTOMERS ADD creditCardNumber VARCHAR2(16);


UPDATE CUSTOMERS
SET creditCardNumber = '1234567890123456'
WHERE custID = 'CUST123';

