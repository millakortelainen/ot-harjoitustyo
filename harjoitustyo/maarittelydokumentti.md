# Määritelydokumentti

## Sovelluksen tarkoitus

Sovelluksen tarkoitus on arpoa käyttäjälle projektityöaiheita ohjelmistotekniikan kurssia varten. Sovellukselle voidaan syöttää tietoa, jolla sovellus optimoi arpomaansa tulosta käyttäjälle. Lisäksi sovellus näyttää hieman tietoa projektityöstä.

## Käyttäjät

Sovelluksella on kahden tyyppisiä käyttäjiä. Moderaattori käyttäjä voi lisätä ja poistaa sovellusaiheita sovelluksesta ja tavallinen käyttäjä voi arpoa ja valita projektityöaiheita itselleen.

## Suunnitellut toiminnallisuudet

Ennen kirjautumista
   * Käyttäjä voi kirjautua sisään
       - Sisäänkirjautuminen tapahtuu käyttäjätunnuksella
   * Käyttäjä voi luoda uuden tunnuksen
       * Tunnuksen tulee olla uniikki ja vähintään 3 merkkiä pitkä
       * Moderaattori tunnukset annetaan erikseen, niitä ei voi luoda

Kirjautumisen jälkeen
   * Käyttäjä:
       * Käyttäjä näkee listan mahdollisista aiheista
       * Käyttäjä voi valita yhden tai usean aihealueen
       * Käyttäjä voi arpoa aiheen projektityölle
       * Käyttäjä voi kirjautua ulos

   * Moderaattori:
       * Moderaattori voi lisätä uuden projektiaiheen
       * Moderaattori voi poistaa aiheen.
       * Moderaattori voi kirjautua ulos. 
