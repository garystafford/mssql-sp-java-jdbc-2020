USE AdventureWorks
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =====================================================
-- Author:		Gary A. Stafford
-- Create date: 08/20/2012
-- Modified:    09/08/2020
-- Description:	Returns a list of products
--				based on color and size.
-- =====================================================
CREATE OR
ALTER PROCEDURE [Production].[uspGetProductsByColorAndSize]
    @productColor VARCHAR(20),
    @productSize INTEGER
AS
BEGIN
    SET NOCOUNT ON;

    SELECT p.[ProductNumber], m.[Name] AS [Model], p.[Name] AS [Product], p.[Color], p.[Size]
    FROM [Production].[ProductModel] AS m
             INNER JOIN
         [Production].[Product] AS p ON m.[ProductModelID] = p.[ProductModelID]
    WHERE (p.[Color] = @productColor)
      AND (p.[Size] = @productSize)
    ORDER BY [ProductNumber], [Model], [Product]
END

GO


