USE AdventureWorks
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =====================================================
-- Author:		Gary A. Stafford
-- Create date: 08/21/2012
-- Modified:    09/08/2020
-- Description:	Returns average weight of all products.
-- =====================================================
CREATE OR
ALTER PROCEDURE [Production].[uspGetAverageProductWeight]
AS
BEGIN
    SET NOCOUNT ON;

    SELECT ROUND(AVG([Weight]), 2) AS AverageWeight
    FROM [Production].[Product]
    WHERE [Weight] > 0
      AND [WeightUnitMeasureCode] = 'LB'
END

GO