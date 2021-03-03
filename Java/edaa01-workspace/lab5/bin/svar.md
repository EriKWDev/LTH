
# F1:
	a) svar: 10 20 30 47
	b) svar: 3
	c) svar: 4

# F2:
	a) om trädet är tom är höjden __0__
	   annars är höjden 1 + __max(höjden av vänster, höjden av höger)__
	b) Math.max(a, b);

# F3:
	svar: int mid = first + (last - first)/2;
	
---


# D8: Diskussionsfrågor
	1) Anledningen är (förutom att användaren hade behövt komma ihåg att anropa funktionen med eventuella root-nodes, start-index mm), så är BinaryNode classen en intern class som bara borde användas av denna BST implementationen.
	
	Genom att skapa en publik funktion utan konstiga parametrar så blir det enklare för användarena att använda metoderna utan att behöva bry sig om metodens implementation.
	
	
	2) Ja. Det hade lika gärna kunnat vara en LinkedList. Dock blir det inte lika effektivt eftersom att man behöver hämta specifika index vilket är en operation som har linjnär tidskomplexitet för LinkedList medans enbart konstant för ArrayList.
	