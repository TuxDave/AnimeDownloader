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

## Build (Maven richiesto)
    cd animeworld-downloader-master/
    mvn install:install-file -Dfile=lib/JTuxObjects-4.1.5-jar-with-dependencies.jar -DgroupId=com.tuxdave -DartifactId=JTuxObjects -Dversion=4.1.5 -Dpackaging=jar
    mvn packege
## Run (Java8+ richiesto)
Per comodità, si può scaricare una versione precompilata del programma [qui](https://github.com/TuxDave/AnimeDownloader/releases) selezionando l'ultima versione e scaricando il file con estensione JAR
Se si compila personalmente il programma, invece, è necessario avviare il file "AnimeDownloader(versione)-jar-with-dependencies.jar"
### Windows:
Se java è installato e configurato correttamente per quanto riguarda le variabili di ambiente, **doppio click sul file AnimeWorld(versione).jar**, se non accade nulla:

    Start > cmd > Enter
    Start > java > Trascinare java nel cmd > aggiungere " -jar " > trascinare il file Jar nel cmd > Enter
### Mac/Linux

    java -jar AnimeDownloader.jar
## Utilizzo
Il programma ha 2 funzioni principali e 2 metodi per utilizzarle:
### Ricerca Anime
- **Ricerca Anime tramite link (sconsigliata):**
Per utilizzare questo metodo è necessario recarsi sul sito animeworld.tv e ricercare manualmente l'anime desiderato tra il catalogo. Una volta sulla pagina principale della serie sarà sufficiente copiare negli appunti l'URL ed incollarlo nella seconda casella di testo, quella con accanto una lente di ingrandimento.
- **Ricerca Anime per parola chiave (consigliata):**
Questa funzionllità permette di cercare un qualsiasi anime nella libreria disponibile e selezionarlo per eseguire altre azioni.
Per utilizzarla è suffuciente premere il bottone con l'icone della lente di ingrandimento accanto alla seconda casella di testo, successivamente verrà aperta una finestra di dialogo dove sarà possibile cercare una serie animata tramite una parola chiave che richiama il titolo e selezionare il risultato desiterato tra una lista di di Anime corrispondenti alla ricerca.
Per facilitare una selezione corretta, sotto al campo per indicare quale serie scegliere sono disponibili delle informazioni come l'immagine di copertina, il numero di episodi e la durata di essi.
Una volta scelto premere "OK" e si potrà passare alla fase successiva.

### Trova e Scarica gli episodi

- **Trova gli episodi:**
  Tramite il bottone "Trova Episodi", dopo aver selezionato l'anime desiderato, permette di trovare i links ai quali
  sono direttamente collegati tutti gli episodi, riportandoli in un box di testo. Dopo di che questi links possono
  essere copiati negli appunti per essere incollati oppure salvati in un file di testo semplice.
- **Scaricare gli Episodi:**
  Una volta che tutti gli episodi sono stati trovati, il bottone "Download Episodi" viene abilitato e diventa possibile
  scaricare gli episodi. Seguendo le richieste del programma, verrà chiesto dove salvare gli episodi e quali scaricare (
  tramite un selettore si range che vi permette di sceglierne solo alcuni).

Tutti i processi che non risultano istantanei nel loro completamento hanno a disposizione una progressBar per indicare
la percentuale di completamento,

## TO FIX

Nel tempo il sito AnimeWorld.tv è cambiato nella sua struttura HTML, di conseguenza lo scraper potrebbe di punto in
bianco non funzionare più... Al momento non funziona la ricerca, ma incollando manualmente il link dell'anime nell'
apposito spazio funziona no problemo. Lo fixerò in caso comincerò ad utilizzarlo più spesso, per adesso lo lascio così,
no problemo