# Arkkitehtuurikuvaus

## Rakenne
Ohjelman rakenne noudattaa nelitasoista kerrosarkitehtuuria ja koodin pakkausrakenne on seuraava:

![](https://raw.githubusercontent.com/millakortelainen/ot-harjoitustyo/master/harjoitustyo/dokumentaatio/pics/pakkauskaavio.png)

Pakkaus Raffle.ui sisältää JavaFX:llä toteutetun käyttöliittymän, Raffle.service sisältää sovelluslogiikkaa, Raffle.dao tietojan pysyvyistallennuksesta vastaavan koodin ja Raffle.domain sisältää sovelluksen käsitteiden ilmentymät.

## Käyttöliittymä
Käyttöliittymä sisältää erilaisia näkymiä. Ensimmäinen näkymä, jonka mikä tahansa käyttäjä näkee on
*kirjautuminen
Tavallinen käyttäjä näkee seuraavat näkymät:
*uuden käyttäjän luominen
*projektiaiheen arpominen
Admin käyttäjä näkee seuraavat nakymät:
*valintanäkymä, jossa voi valita haluaako poistaa tai luoda uuden projektin
*uuden projektin kategorian valinta
*uuden kategoria luominen
*uuden projektin luominen
*projektin poistaminen

jokainen näistä on teutettu omana Scene-oliona. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen stageen. Käyttöliittymä on rakennettu ohjelmallisesti luokassa Raffle.ui.RaffleUi.

Käyttöliittymä on pyritty eristämään sovelluslogiikasta, se ainoastaan kutsuu sopivin parametrein sovelluslogiikan toteuttavan olion raffleServicen metodeita.

Kun käyttäjä on kirjautunut sisään, sovellus listaa tietokannasta löytyvät projektiaiheiden kategoriat. Käyttöliittymä hakee tiedot projekteista ja kategorioista raffle servicen metodeilla.

## Sovelluslogiikka
Sovelluksen loogisen datamallin muodostavat User, Project ja ProjectCategory. Käyttäjällä ja projektilla ei ole varsinaista suhdett. Projekti kategorioilla on projekteja. 

![](https://raw.githubusercontent.com/millakortelainen/ot-harjoitustyo/master/harjoitustyo/dokumentaatio/pics/Untitled%20Diagram.png)

Sovelluksen toiminnallisuudessa vastaa pääasiassa RaffleServicen ainoa olio. RaffleService kuitenkin käyttää apunaan UserService, ProjectService ja ProjectCategoryService olioita. Nämä oliot ovat kaikki uniikkeja ja määritellään RaffleService määrittelee. RaffleService luokka tarjoaa kaikille käyttöliittymän toiminnolle oman metodin. Näitä ovat esimerkiksi
*okei
*nää on
*on viel aika hajalla

RaffleService luokka käyttäjiin ja projekteihin UserService, ProjectService ja ProjectCategoryService, jotka hoitavat kommunikoinnin tietokannan ja ohjelman välillä.

Sovelluslogiikkaa voi kuvata seuraavalla luokkakaaviolla
![](https://raw.githubusercontent.com/millakortelainen/ot-harjoitustyo/master/harjoitustyo/dokumentaatio/pics/luokkakaavio.png)

## Tietojen pysyväistallennus
Pakkauksen Raffle.dao luokat UserDao, ProjectDao ja ProjectServiceDao huolehtivat tietojen tallettamiesta ja lukemisesta tietokannasta.

Luokat noudattavat Data Access object-suunnittelumallia. Luokat toteuttavat Dao-rajapinnan.

### Tietokanta
Sovellus tallettaa käyttäjiä, projekteja ja projektien kategorioita tietokantaan.

Kun sovellus käynnistetään, uusi tietokanta luodaan, jos sellaista ei ole vielä olemassa.

## Päätoiminnallisuudet
Kuvataan seuraavaksi sovelluksen toiminta logiikkaa muutaman päätoiminnallisuuden osalta sekvenssikaavioina

### projektin arpominen
![](https://raw.githubusercontent.com/millakortelainen/ot-harjoitustyo/master/harjoitustyo/dokumentaatio/pics/sekvenssikaavio.png)

## Ohjelman rakenteeseen jääneet heikkoudet
tulossa..

