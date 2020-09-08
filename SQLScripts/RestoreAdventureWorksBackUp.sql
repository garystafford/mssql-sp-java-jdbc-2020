-- https://docs.microsoft.com/en-us/sql/samples/adventureworks-install-configure?view=sql-server-ver15&tabs=ssms

DROP DATABASE IF EXISTS AdventureWorks;
GO

EXECUTE msdb.dbo.rds_restore_database
     @restore_db_name='AdventureWorks',
     @s3_arn_to_restore_from='arn:aws:s3:::garystaf-mssql-backups/AdventureWorks2017.bak',
     @type='FULL',
     @with_norecovery=0;

-- get task_id from output (e.g. 9)

EXECUTE msdb.dbo.rds_task_status
     @db_name='AdventureWorks',
     @task_id=13;