Svar på förberedelseuppgifter för labb 2:

# F1:
	läs bakgrund

# F2:
	a) null
	b) QueueNode<E> n = last.next;
	c) E e = last.next.element;

# F3:
	a) assertFalse
	b) assertEquals
	
# F4:
	Lös D1, D2, D3, D4

# F5:
	Läs alla uppgifter

---

#D1
	anropa bara metod med samma namn fast på listan.
	
#D2
	allt funkade
	
#D3
	offer() tog lång tid att få rätt. Försökta iterera igenom listan och allt tills jag sist insåg hur jag kunde utnyttja datastrukturen.

#D4
	Försökte först genom att ha en counter och jämföra den med size.
	Det kändes dock fuskigt/onödigt att använda en index/counter i en iterator,
	så till sist kom jag på att man kunde använda en flagga för att kolla
	om man har varit på den första noden innan, och om man redan har det,
	då är man klar och kan returna false i hasNext().

#D5
	Inga större problem här. Behövde se till att q blev tom med q.size = 0 och q.last = null.
	hade kanske kunnat använda q.empty() ?
	
#D6
	Inga konstigheter. Baserade på de färdiga testerna.
	
#D7
	a) Man hade nog kunnat göra det, men det hade krävts mer logik än att bara delegera.
		Man hade exempelvis behövt se till att offer lägger in i början av listan
		och att poll då tar från den första positionen och shiftar över allt.
		
	b) D1 är ju trevlig eftersom att den delegerar allt som lätt kan bli fel till en färdig klass, men D3
		ökar ju förståelsen för datastrukturen vilket gör att man kanske kan komma fram till mer effektiv
		kod då man förstår den underliggande teorin.
		
	c) Det kan vara olämpligt att använda färdiga när effektivitet och exekveringstid är viktig.
		I sådanna situationer är det bäst att ha full kontroll över allt som sker, dock är det
		nog i nästan alla fall så att den färdigimplementerade redan är väldigt optimerad.
		
	d) Inte direkt. Mina test-cases kan ju vara felaktiga, och jag kan ju ha missat att skriva
		tester i edge-cases eller annat som då skulle komma undan.

		
		