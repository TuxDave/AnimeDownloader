# AnimeDownloader

AnimeDownloader è un client non ufficiale che ha l'obbiettivo di semplificare il download degli episodi dai server di AnimeWorld.tv
Sul sito riportato sopra si possono trovare pressochè qualunque Anime SUB-ITA e alcuni anche tradotti in italiano, anche se la maggior parte dei tradotti sono stati acquistati da altre compagnie e quindi non sono disponibili al download.

## Features
- Dispone di una interfaccia grafica intuitiva che facilita la navigazione tra il catalogo;
- Non è necessario andare a cercare la serie sul sito di AnimeWorld, perchè nel programma è incorporato uno script che lo fa per voi;
- È possibile estrarre i link per il download degli episodi senza però scaricarli, in modo da poterli utilizzare per poi scaricarli secondariamente tramite magari un dispositivo dedicato (es RPI con la funzione di downloadBox);
- Permette di decidere quali episodi scaricare tramite un selettore di range, per dare la possibilità per es. di scaricare solo dal primo al settimo episodio della serie;
- Presto verrà portato su Android, dove sarà molto più comodo e pratico.

## Problemi
- Se non si utilizza correttamente l'interfaccia grafica si rischia di mandare in crash il programma e sarà necessario un riavvio di esso (irrobustirerò i controlli per evitare questo);
- La grafica, se pur comoda ed intuitiva, risulta orrenda alla vista, una review grafica arriverà presto.
- Se si tenta di cercare un anime che nel nome ha un carattere speciale (es: Re:Zero Kara...) la ricerca fallisce.

## Build (Maven richiesto)
    cd animeworld-downloader-master/
    mvn packege
## Run (Java7+ richiesto)
Per comodità, si può scaricare una versione precompilata del programma [qui](https://github.com/TuxDave/AnimeDownloader/releases) selezionando l'ultima versione e scaricando il file con estensione JAR
Se si compila personalmente il programma, invece, è necessario avviare il file "AnimeDownloader(versione)-jar-with-dependencies.jar"
### Windows:
Se java è installato e configurato correttamente per quanto riguarda le variabili di ambiente, **doppio click sul file AnimeWorld(versione).jar**, se non accade nulla:

    Start > cmd > Enter | Start > java > Trascinare java nel cmd > aggiungere " -jar " > trascinare il file Jar nel cmd > Enter
### Mac/Linux

    java -jar AnimeDownloader.jar
