DROP TABLE FLIGHT_DATA;

CREATE EXTERNAL TABLE IF NOT EXISTS FLIGHT_DATA
	(Year STRING,
	Month INT,
	DayofMonth STRING,
	DayofWeek STRING,
	DepTime INT,
	CRSDepTime INT,
	ArrTime INT,
	CRSArrTime INT,
	UniqueCarrier STRING,
	FlightNum STRING,
	TailNum STRING,
	ActualElapsedTime INT,
	CRSElapsedTime INT,
	AirTime	INT,
	ArrDelay INT,
	DepDelay INT,
	Origin STRING,
	Dest STRING,
	Distance INT,
	TaxiIn INT,
	TaxiOut INT,
	Canceled STRING,
	CancellationCode STRING,
	Diverted STRING,
	CarrierDelay STRING,
	WeatherDelay STRING,
	NASDelay STRING,
	SecurityDelay STRING,
	LateAircraftDelay STRING)
ROW FORMAT DELIMITED
	FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION '/user/cbove/flightdata'


