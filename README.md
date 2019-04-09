# Raffle

Sovelluksella käyttäjä voi arpoa itselleen ohjelmistotekniikka-kurssin projektiaiheen.

## Dokumentaatio

[Määrittelydokumentti](https://github.com/millakortelainen/ot-harjoitustyo/blob/master/harjoitustyo/maarittelydokumentti.md)

[Työaikakirjanpito](https://github.com/millakortelainen/ot-harjoitustyo/blob/master/harjoitustyo/ty%C3%B6aikakirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/millakortelainen/ot-harjoitustyo/blob/master/harjoitustyo/dokumentaatio/arkkitehtuuri.md)

## Releaset
coming

## Komentoriviotiminnot

### Testaus
Testit suoritetaan komennolla

mvn test

Testikattavuusraportti luodaan komennolla

mvn jacoco:report

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

### Suoritettavan jarin generointi
coming
### JavaDoc
coming
### Checkstyle
Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

 mvn jxr:jxr checkstyle:checkstyle

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html
