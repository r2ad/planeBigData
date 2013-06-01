
CREATE EXTERNAL TABLE IF NOT EXISTS PLANE_DATA
	(tailnum STRING,
	type STRING,
	manufacturer STRING,
	issue_date STRING,
	model STRING,
	status STRING,
	aircraft_type STRING,
	engine_type STRING,
	year INT)
ROW FORMAT DELIMITED
        FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION '/user/cbove/planedata'




