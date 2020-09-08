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
ALTER PROCEDURE [Person].[uspGetEmployeesByLastName]
    @lastNameStartsWith VARCHAR(20) = 'A'
AS
BEGIN
    SET NOCOUNT ON;

    SELECT [Title], [FirstName], [MiddleName], [LastName], [Suffix]
    FROM [Person].[Person]
    WHERE ([PersonType] = 'EM')
      AND ([LastName] LIKE @lastNameStartsWith + '%')
    ORDER BY [LastName], [FirstName], [MiddleName], [Suffix], [Title]
END

GO


