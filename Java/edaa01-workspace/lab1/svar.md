Svar på förberedelseuppgifter för labb 1:

# F1:
	a) NullPointerException uppstod i Scheduler.java på rad 19. Orsaken är att machines[] inte initieras i konstruktorn utan att det istället skapas en ny lokal variabel.
	b) ArrayIndexOutOfBoundsException i Scheduler.java på rad 49. Orsaken är att for-loopen testar för om i <= lengthen men denskall egentligen enbart nå till i < length.
	Där stod "Index 3 out of bounds for length 3". Det betyder att om en array har längden 3 så inkluderas ju inte indexen 3 iom att den börjar på 0.
	
# F2:
  4. Svar: DescTimeComp returnerade differansen mellan jobb1 och jobb2, men det skall vara tvärtom för att stortera i avtagande ordning.