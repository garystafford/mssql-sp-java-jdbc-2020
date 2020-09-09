USE AdventureWorks;

-- uspGetAverageProductWeight
EXECUTE [Production].[uspGetAverageProductWeight];

-- uspGetAverageProductWeightOUT
DECLARE @average DECIMAL(8, 2);

EXECUTE [Production].[uspGetAverageProductWeightOUT]
    @averageWeight = @average OUTPUT;

SELECT @average AS 'Average product weight';

-- uspGetEmployeesByLastName
EXECUTE [HumanResources].[uspGetEmployeesByLastName]
    @lastNameStartsWith = 'Sa';

-- uspGetProductsByColorAndSize
EXECUTE [Production].[uspGetProductsByColorAndSize]
    @productColor = 'Red',
    @productSize = 44;