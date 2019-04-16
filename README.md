# Raffle

Sovelluksella käyttäjä voi arpoa itselleen ohjelmistotekniikka-kurssin projektiaiheen.

## Dokumentaatio

[Määrittelydokumentti](https://github.com/millakortelainen/ot-harjoitustyo/blob/master/harjoitustyo/maarittelydokumentti.md)

[Työaikakirjanpito](https://github.com/millakortelainen/ot-harjoitustyo/blob/master/harjoitustyo/ty%C3%B6aikakirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/millakortelainen/ot-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/arkkitehtuuri.md)

## Releaset
[Viikko 5](https://github.com/millakortelainen/ot-harjoitustyo/releases/tag/viikko5)

## Komentoriviotiminnot

### Testaus
Testit suoritetaan komennolla

mvn test

Testikattavuusraportti luodaan komennolla

mvn jacoco:report

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

### Suoritettavan jarin generointi
Komento

mvn package

generoi hakemistoon target suoritettavan jar-tiedoston Raffle-1.0-SNAPSHOT.jar

### JavaDoc
coming
### Checkstyle
Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

 mvn jxr:jxr checkstyle:checkstyle

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html
