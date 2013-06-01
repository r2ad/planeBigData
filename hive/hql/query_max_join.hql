select year, max(arrdelay) max_delay,  c.description
from flight_data fd 
left outer join carriers c on (fd.uniquecarrier = c.code) 
group by year,  c.description 
order by year, max_delay desc
limit 200;

