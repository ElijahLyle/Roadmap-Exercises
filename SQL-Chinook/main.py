import sqlite3
import pandas as pd
import matplotlib.pyplot as plt
from tkinter import ON

conn = sqlite3.connect('Chinook_Sqlite.sqlite')

cursor = conn.cursor()

# Query to get the top 10 tracks with the highest sales
query1 = 'WITH TrackSales AS (SELECT il.TrackId, SUM(il.UnitPrice * il.Quantity) AS TotalSales FROM InvoiceLine AS il GROUP BY il.TrackId) SELECT t.Name, ts.TotalSales FROM TrackSales AS ts INNER JOIN Track AS t ON ts.TrackId = t.TrackId ORDER BY ts.TotalSales DESC LIMIT 10;'

# Query to get the country with the highest total revenue
query2 = 'WITH CustomerRevenue AS (SELECT i.CustomerId, SUM(i.Total) AS TotalRevenue FROM Invoice AS i GROUP BY i.CustomerId) SELECT c.Country FROM CustomerRevenue AS cr INNER JOIN Customer AS c ON cr.CustomerId = c.CustomerId GROUP BY c.Country ORDER BY SUM(cr.TotalRevenue) DESC LIMIT 1;'

# Query to get the employee with the highest total sales
query3 = 'WITH CustomerRevenue AS (SELECT i.CustomerId, SUM(i.Total) AS TotalRevenue FROM Invoice AS i GROUP BY i.CustomerId), EmployeeCustomerRevenue AS (SELECT c.SupportRepId, SUM(cr.TotalRevenue) AS TotalSales FROM CustomerRevenue AS cr INNER JOIN Customer AS c ON cr.CustomerId = c.CustomerId GROUP BY c.SupportRepId) SELECT e.FirstName, e.LastName FROM EmployeeCustomerRevenue AS ecr INNER JOIN Employee AS e ON ecr.SupportRepId = e.EmployeeId ORDER BY ecr.TotalSales DESC LIMIT 1;'

dataframe1 = pd.read_sql_query(query1, conn)
dataframe2 = pd.read_sql_query(query2, conn)
dataframe3 = pd.read_sql_query(query3, conn)

print(dataframe1)
print(dataframe2)
print(dataframe3)

plt.bar(dataframe1['Name'], dataframe1['TotalSales'])
plt.xlabel('Track Name')
plt.ylabel('Total Sales')
plt.title('Top 10 Tracks with Highest Sales')
plt.xticks(rotation=45, ha='right')
plt.tight_layout()
plt.show()

conn.close()