# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatio testaus

### sovelluslogiikka
Sovelluslogiikkaa testataan luokka kohtaisesti. Tärkeimpiä testejä on Raffle.service -pakkauksen testit, sillä se on suurimmaksi osaksi vastuussa sovelluksen toimintalogiikasta.

### testikattavuus
Käyttöliittymää lukuunottamatta sovelluksen testauksen rivikattavuus on 94% ja haarautumakattavuus 86%
![](https://raw.githubusercontent.com/millakortelainen/ot-harjoitustyo/master/harjoitustyo/dokumentaatio/pics/testikattavuus.png)

Testaamatta jäit tilanteen, joita työntekijä ei kerennyt toteuttamaan.

## Järjestelmätestaus
Sovelluksen järjestelmä testaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi
Sovellus on testattu Linux-ympäristössä ja todettu toimivaksi käyttöohjeistakin löytyvällä tavalla.

### Toiminnallisuudet
Virheisiin ei ole varauduttu kovinkaan hyvin. Testit ovat lähinnä testanneet toiminnallisuutta oletetulla tavalla.

## Sovellukseen jääneet laatuongelmat
* testeihin jäi bugina se, että ne eivät todennäköisesti mene läpi, ellei ohjelma ole kerran itsenäisesti pyörinyt ja luonut siten tietokantoja itselleen
