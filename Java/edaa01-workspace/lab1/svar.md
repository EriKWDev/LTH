Svar på förberedelseuppgifter för labb 1:

# F1:
	a) NullPointerException uppstod i Scheduler.java på rad 19. Orsaken är att machines[] inte initieras i konstruktorn utan att det istället skapas en ny lokal variabel.
	b) ArrayIndexOutOfBoundsException i Scheduler.java på rad 49. Orsaken är att for-loopen testar för om i <= lengthen men denskall egentligen enbart nå till i < length.
	Där stod "Index 3 out of bounds for length 3". Det betyder att om en array har längden 3 så inkluderas ju inte indexen 3 iom att den börjar på 0.
	
# F2:
 	4. Svar: DescTimeComp returnerade differansen mellan jobb1 och jobb2, men det skall vara tvärtom för att stortera i avtagande ordning.
 	5. Svar: Machine.assignJob overrideade tiden varje gång där den egentligen skulle addera det nya jobbets tid. = byttes till += 
  
 # F3:
 	Svar: Prioritetskö borde fungera för då kan man hämta det objektet med den lägsta av ett visst attribut. Exempelvis SortedSet.
 
 # F4:
	a. 1) 20 st. Listor kan innehålla dubletter.
	a. 2) Ja
	
	b. 1) endast 10. Sets innehåller inga dubletter.
	b. 2) Nej, sets har ingen ordning.
	
	c. 1)  Map<String, Integer> m = new HashMap<String, Integer>();
	c. 2) 7. Nyckeln albatross är länkad med integern 7.
	
	d) containsKey();, returneraren boolean beroende på om nyckeln finns eller ej.

# F5:
	Läs igenom "Bakgrund"
	
# F6:
	D1) ---
	D2) w == word kommer alltid ge false. Måste använda .equals eller åtminstone .compare med strängar.
	D3) ---
	D4) Gjorde om det till en ArrayList<TextProcessor>() och använde for(TextProcessor processor : processors) för att iterera över alla.
	
	D10) Kompileringsfelet beror på att WordCountComparator inte implementerat Comparator.
	
	D13) HashMap: Tiderna det tar att exikvera programmet: 
			1: 555ms
			2: 585ms
			3: 618ms
			Median: 585ms
	
	D14) TreeMap: Tiderna det tar att exikvera programmet: 
			1: 627ms
			2: 609ms
			3:  538ms
			Median: 609ms
			
			Programmet fungerar fortfarande.
			Ordningen ändras för MultiWordCounter (landskapen). De blev i bokstavsordning.
			Map är ett interface medans HashMap är en konkret implementation.
			TreeMap har en ordning och är sorterad efter nycklarna. HashMap har inte det.
			Mängden är bra för att vi inte bryr oss om återkommande ord. 
			HashMap är bra eftersom att den har en look-up tid på O(1). Dvs, den är snabb.
