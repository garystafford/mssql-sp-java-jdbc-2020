USE AdventureWorks;

-- uspGetAverageProductWeight
EXECUTE Production.uspGetAverageProductWeight;

-- uspGetAverageProductWeightOUT
DECLARE @average DECIMAL(8, 2);

EXECUTE Production.uspGetAverageProductWeightOUT
    @averageWeight = @average OUTPUT;

SELECT @average AS 'Average weight of products';

-- uspGetEmployeesByLastName
EXECUTE Person.uspGetEmployeesByLastName
    @lastNameStartsWith = 'C';

-- uspGetProductsByColorAndSize
EXECUTE Production.uspGetProductsByColorAndSize
    @productColor = 'Red',
    @productSize = 44;