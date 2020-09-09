USE AdventureWorks;

-- Flush the ad hoc and prepared plan cache for the entire instance
DBCC FREESYSTEMCACHE ('SQL Plans');
GO

CHECKPOINT;
GO

-- Impossible to run with Amazon RDS for Microsoft SQL Server on AWS
-- DBCC DROPCLEANBUFFERS;
-- GO