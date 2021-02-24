Svar på förberedelseuppgifter för labb 3:

# F1:
	Läs bakgrund
	
# F2:
	Map.entrySet() ger tillbaka ett set av alla entries
	
# F3:
	(e1, e2) -> e2.getValue() - e1.getValue()
	
# F4:
	a) addActionListener()
	b) getText() från superklassen JTextComponent
	c) add() från superklassens superklass Container.
	d) setSelectedIndex() eller setSelectedIndicies() eller setSelectedValue() beroende på hur man vill sätta en rad/rader till selected.
	
# F5:
	lös D1, D2, D3 & D4.
	
# F6:
	Övriga uppgifter
	
---
	
# D1:
	```
	public List<Map.Entry<String, Integer>> getWordList() {
		return new ArrayList<>(wordCountMap.entrySet());
	}
	```


# D9:
	Jag valde att lägga in en popup, göra så att sökterman hanterar versaler och mellanslag samt att *enter* klickar på find knappen.

