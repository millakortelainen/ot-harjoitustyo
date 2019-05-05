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

Sovelluksen toiminnallisuudessa vastaa pääasiassa RaffleServicen ainoa olio. RaffleService kuitenkin käyttää apunaan UserService, ProjectService ja ProjectCategoryService olioita. Nämä oliot ovat kaikki uniikkeja ja määritellään RaffleServicen konstruktiossa. RaffleService luokka tarjoaa kaikille käyttöliittymän toiminnolle oman metodin. Näitä ovat esimerkiksi
*adminLoginSuccessfull
*createNewProject
*initDatabase

RaffleService-luokka käyttää ohjelman ja tietokannan väliseen kommunikointiin projectService, projectCatgoryService ja userService- luokkia. Nämä luokat käyttävät omia Dao-rajapinnan periviä luokkiaan: UserDao, projectCategoryDao ja projectDao tietokannan ja ohjelman välisen kommunikoinnin toteuttamiseen.

Sovelluslogiikkaa voi kuvata seuraavalla luokkakaaviolla
![](https://raw.githubusercontent.com/millakortelainen/ot-harjoitustyo/master/harjoitustyo/dokumentaatio/pics/luokkakaavio.png)

## Tietojen pysyväistallennus
Pakkauksen Raffle.dao luokat UserDao, ProjectDao ja ProjectServiceDao huolehtivat tietojen tallettamiesta ja lukemisesta tietokannasta.

Luokat noudattavat Data Access object-suunnittelumallia. Luokat toteuttavat Dao-rajapinnan. Luokat on eristetty omien Service-luokkien taakse, joten ohjelman kokonaisuutta hallinnoiva luokka, ei käytä niitä suoraan.

Sovelluksen testauksessa daoja hyödynnetään kuitenkin suoraan. Testauksessa on myös käytetty omaa tietokantaa.  

### Tietokanta
Sovellus tallettaa käyttäjiä, projekteja ja projektien kategorioita tietokantaan.

Kun sovellus käynnistetään, uusi tietokanta luodaan, jos sellaista ei ole vielä olemassa paikallisesti. Tietokannan tauluihin alustetaan myös tarpeellista tietoa taulujen alustuksen yhteydessä. 

## Päätoiminnallisuudet
Kuvataan seuraavaksi sovelluksen toiminta logiikkaa muutaman päätoiminnallisuuden osalta sekvenssikaavioina

### käyttäjän kirjautuminen
Kun krijautumiskenttään on annettu käyttäjätunnus "Milla" ja "Kirjaudu"-painiketta painetaan, etenee sovelluslogiikka seuraavasti:

![](https://raw.githubusercontent.com/millakortelainen/ot-harjoitustyo/master/harjoitustyo/dokumentaatio/pics/sekvenssikaavioKirjautumisesta.png)

Tapahtumankäsittelijä reagoi käyttäjän painaessa painiketta. Tällöin käyttöliittymä lukee tekstikenttään syötetyn merkkijonon ja antaa sen parametrina raffleService:n metodille .userLogInSuccesful(). RaffleService välittää parametrinaan saamansa merkkijonon edelleen userService:lle. UserService:n metodi .usernameExists() tarkistaa, onko kysyttä käyttäjänimeä olemassa. Metodi kutsuu userDao:n metodia .list(), joka listaa kaikki tietokannasta löytyvät käyttäjät. .userNameExists kutsuu tämän jälkeen userService:n .usernames()-metodia, joka muuttaa User-olioita sisältävän ArrayList:n merkkijono muotoiseksi listaksi. Tästä listasta tutkitaan .contains()-metodilla .usernameExists()-metodin parametrina saama merkkijono, joka oli haettu käyttäjänimi. Jos käyttäjänimi on olemassa, palauttaa metodi totuusarvon true, jonka metodi .userLogInSuccesful() palauttaa lopuksi käyttöliittymälle, joka vaihtaa kirjautumisen oonistuessa scenen käyttäjän sceneen.

### projektin arpominen
Käyttäjä valitsee CheckBox:in "Korttipeli" ja painaa "Arvo projektiaihe":

![](https://raw.githubusercontent.com/millakortelainen/ot-harjoitustyo/master/harjoitustyo/dokumentaatio/pics/sekvenssikaavioProjektinArpomisesta.png)

Painikkeen tapahtumankäsittelijä reagoi jälleen käyttäjän klikkaukseen. Tällöin käyttöliittymä lukee käyttäjän valitsemat CheckBox valinnat ja antaa ne listana raffleservicen metodille .getRandomProject(). Ensimmäisenä metodi .getRandomProject() kääntää parametrinaan saamaansa merkkijonomuotoisen listan projectCategory-olioita sisältäväksi listaksi käyttäen apunaan ProjectCategoryService:n metodia .stringListToProjectCategoryList(). Metodi käy listan jokaisen alkoin läpi ja muuntaa ne yksi kerrallaan merkkijonosta Projectcategory-olioiksi käyttäen omaa metodiaan .getProjectCategoryFromString(). .getProjectCategoryFromString()-metodi käyttää projectCategoryDao-olion .list()-metodia hakiessaan olemassa olevia projekti kategorioita. Lopuksi projectCategory-olioita sisältävä ArrayList palautetaan raffleService-luokan metodille .getRandomProject(). 

Tämän jälkeen kutsutaan projectService-olion metodia .getRandomProjectFromCategory() projectCategory-olioita sisältävällä ArrayList:lla. projectService hakee kategorioiden kaikki projektit listalle hyödyntäen projectCategoryService:ä ja projectDao:a. Lopuksi se sekoittaa listan, johon projektit talletetaan ja valitsee siitä ensimmäisen alkion. Tämän se palauttaa satunnaisena projektina raffleService:lle.

### Muut toiminnallisuudet
Kaikki toiminnallisuudet noudattavat tätä samaa tyyliä toiminnassaan. Ensin käyttöliittymän avulla kutsutaan raffleService-oliota, joka kutsuu tarpeen vaatiessa userService, projectService ja projectCategoryService olioita. Nämä Service-oliot voivat myös kutsua toinen toisiaan. Service-olioiden vastuulla on myös käyttää Dao-rajapinnan kautta tietokannan kanssa kommunikointi.

## Ohjelman rakenteeseen jääneet heikkoudet



