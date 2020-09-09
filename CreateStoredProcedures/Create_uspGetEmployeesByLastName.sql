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
-- Description:	Returns a list of all employee names
--				whose last name is starts with value.
-- =====================================================
CREATE OR
ALTER PROCEDURE [HumanResources].[uspGetEmployeesByLastName]
  @lastNameStartsWith VARCHAR(20) = 'A'
AS
BEGIN
  SET NOCOUNT ON;

  SELECT p.[FirstName], p.[MiddleName], p.[LastName], p.[Suffix], e.[JobTitle], m.[EmailAddress]
  FROM [HumanResources].[Employee] AS e
    LEFT JOIN [Person].[Person] p ON e.[BusinessEntityID] = p.[BusinessEntityID]
    LEFT JOIN [Person].[EmailAddress] m ON e.[BusinessEntityID] = m.[BusinessEntityID]
  WHERE e.[CurrentFlag] = 1
    AND p.[PersonType] = 'EM'
    AND p.[LastName] LIKE @lastNameStartsWith + '%'
  ORDER BY p.[LastName], p.[FirstName], p.[MiddleName]
END

GO


