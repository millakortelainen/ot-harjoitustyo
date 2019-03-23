package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    Maksukortti pieniKortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
        pieniKortti = new Maksukortti(500);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }
    
    @Test
    public void saldoOnAlussaOikein() {
        assertEquals(1000, kortti.saldo());
    }

    @Test
    public void rahanLisaysKasvattaaSaldoa() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 20.0", kortti.toString());
    }

    @Test
    public void rahaVaheneeKunMaksetaan() {
        kortti.otaRahaa(100);
        assertEquals("saldo: 9.0", kortti.toString());
    }

    @Test
    public void saldoEiMuutuJosEiOleRahaa() {
        pieniKortti.otaRahaa(600);
        assertEquals("saldo: 5.0", pieniKortti.toString());
    }
    
    @Test
    public void kortiltaVoiOttaaRahaa(){
        assertTrue(kortti.otaRahaa(100));
    }
    
    @Test
    public void kortiltaEiVoiOttaaRahaa(){
        assertFalse(pieniKortti.otaRahaa(600));
    }
}
