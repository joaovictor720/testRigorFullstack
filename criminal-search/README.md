We have a map with the most wanted criminals.
The key of the map is his actual name.
The value contains possible aliases the criminal has used.
The police is interviewing people that have been in contact with one of them.
They don't know if the criminal used their name or an alias.
Write a function that returns the most probable criminal having the name (provided by interviewed people)
as an input. The function should return a string in the following shape: "First name: name. Aliases: alias0,
alias1, aliasN". If there is no match, the response should be "No match".
Of course, matching the actual name of the criminal is more meaningful than matching an alias and having
an exact match is more meaningful than a partial match.

For instance:

```
Map<String, String> criminals = new HashMap<>();
criminals.put("Paul White", "Roger Night, Peter Llong Jr.");
criminals.put("Roger Fedexer", "Rob Ford, Pete Lord, Roger McWire");
criminals.put("Paul White Jr.", null);
criminals.put("Red Fortress", "Roger Rabbit, Ross Winter");
criminals.put("Redford Fort", "Red Strong, Red Fort");
```

Input -> "paul White" should of course return the first entry.

Input -> "Roger" should return the second entry. There are 2 more guys using "Roger" as an alias, but since
the actual name of the second one is Roger, it has more weight.

Input -> "Ross" should return the 4th entry.

Input -> "white jr." should return the third entry.
