
=============================================

7. # Rekryteringen
   1. ### Vad skall implementeras i Applicant.java?
    Det verkar som att jag kommer att behöva implementera getAvgGrade() samt toString(). Eventuellt kanske också ändra hur parseGrades() algoritmen funkar föär den verkar lite icke-tillförlitlig.

   2. ## FileReader och FindBestCandidate
   De båda klasserna har en ganska tydlig ansvarsfördelning som man lätt kan finna bara på namnen.

   3. ## --

   4. ## Hur översätts bokstavsbetygen tilkl siffror?
   På rad 16-31 i Applicant.java med metoden parseGrades(). Programmet kollar om betyget på plats i i vektorn av betyg är lika med "U" och sätter det isf till 0 (rad 26). Vektorn med heltalsvärden heter grades[].

   5. ## Implementera getAvgGrade()

   6. ## Implementera readFromFile()

   7. ## Anropa readFromFile() från main()

   8. ## Skriv ut all applicants som readFromFile ger
   De följer formatet "rekrytering.Applicant@XXXXXXXX". Förmodligen för att vi inte har implementerat toString() som automatiskt anropas på all aobject när de skall skrivas ut.

   9. ## Ändra toString() till formatet "Namn Efternamn[<grade1>, <grade2>, ...](avg: <avg>)"

   10. ## --

   11. ## Implementera findBestCandidates(). Varför är det bra att använda konstanter?
   Istället för att då behöva ändra de på flera ställen och hålla reda på alla ställen man deklarerar dem så är det mycket enklare och mer maintainable kod att deklarera konstanter som enkelt kan ändras från ett enda ställe och sedan läsas vartifrån som helst i resten av koden. (om den är statisk ofc...)

   12. ## Testa findBestCandidates()
   Den verkar fungera som den skall. Det känns som att bra värde på MIN_AVG_GRADE är runt 3 till 4.

   13. ## --

   14. ## Blir det fel med en större mängd? (applicants_x.txt)
   Ja. Det verkar som att några kandidater inte har följt input-formatet och istället angett sina betyg med bokstäver "A, B," samt stora tal som "44" och något negativt tal -1.

   15.  ## Hur hade man kunnat förbättre situationen?
   Det hade varit mycket bättre om en användare aldrig fick ge den faktiska indatan som hanteras av programmet internt. Det hade exempelvis kunnat finnas en "dropdown-selection" där användaren kunde välja mellan ett begränsat set av värden.

   Man hade dock kunnat försöka gissa vad de menar, typ A = 5, B = 4 osv. Men om denna gissning är felaktig så kan de som matat in data på det sättet få en eventuell advantage/disadvantage.

   16. ## Fixa det!
   Jag gjorde så att C->A översätts till 3->4, jag gjorde så att readFromFile() testar ifall en rad är tom och går vidare isf, och jag gjorde så att alla bokstäver som testas först omvandlas till upper-case.

   17. ## Är allt bra?
   Det känns rimligt att bokstavsbetygen C->A skall översättas till siffrorna då det inte nödvändigtvis är elevernas/de ansökandes fel att instruktionerna varit otydliga. Vad gäller de som inte matat in tillräckligt är det synd att de missaat, men det finns inte riktigt något sätt att hantera dem utan att missgynna folk som har följt instruktionerna.

   18. ## Fler förbättringar efter att ha läst kommentarer?
   Det hade ju varit rimligt ifall algoritmen viktar betygen mot de senaste då det är de som är svårare och rimligare reflekterar de eventuella expert-kunskaperna man söker när man skall anställa en programemrare.

   Man hade även kunnat tolka dubbel-inmatningar som "44" som bara "4" osv. Vad gäller de som inte har fyllt i alla fem betyg så hade man kunnat ha en separat ansökningsgrupp för de individer som exempelvis kryssat i "Hoppade av efter x antal kurser" och göra en individuel bedömning där.

   19. ## Implementera förbättring!

   20. ## --


=============================================


8. # Memory!
   1. ## Förstår du implementationen av MemoryCardImage?
   Ja, den känns implementerad på ett rimligt sätt efter specifikationen. Har t.o.m. lite felhantering i readImage().

   2. ## Implementera Skelett-klassen MemoryBoard.java

   3. ## Läs MemoryWindow.java klassen

   4. ## Implementera main() metoden