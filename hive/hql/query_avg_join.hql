select year, avg(arrdelay) avg_delay,  c.description
from flight_data fd 
left outer join carriers c on (fd.uniquecarrier = c.code) 
group by year,  c.description 
order by year, avg_delay desc
limit 200;

