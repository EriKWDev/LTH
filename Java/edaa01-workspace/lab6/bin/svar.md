 
 
 # F1:
 	a)	String
 	b) Integer
 	c) 	"null 0"
 			"43 1"
 			"42 1"
 
 # D12:
 	a) Ifall hashkoden blir samma för olika nycklar, eller om tabellen är för liten (max tillåtna loadingFactor för stor) så kommer kollisioner att uppstå. När olika nycklar pekar på samma index så måste man spara flera värden på samma  plats i vektorn.
 	
 	b) hashCode användst för att generera vilket index i tabellen som objektet skall placeras (abs( hashCode % table.length )), och equals används för att jämföra nycklar ifall en entry med den nyckeln redan existerar. 
 
 # D13:
 	En trädstruktur borde också fungera, men kommer att leda till högre medeltidskomplexitet för de flesta metoderna (get, put, remove) (O(log n)) istället för O(1), men den kommer använda mindre minne eftersom att den inte har en "onödigt lång" array att hålla reda på. Inga null keys.
 