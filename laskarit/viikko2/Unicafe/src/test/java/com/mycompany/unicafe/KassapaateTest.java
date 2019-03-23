package com.mycompany.unicafe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kortemil
 */
public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;
    Maksukortti koyhaKortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
        koyhaKortti = new Maksukortti(100);
    }

    @Test
    public void luotuKassaOnOlemassa() {
        assertTrue(kassa != null);
    }

    @Test
    public void alussaOikeaMaaraRahaaKassassa() {
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void alussaOikeaMaaraMyytyjaEdullisisaLounaita() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void alussaOikeaMaaraMyytyjaMaukkaitaLounaita() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void edullisenLounaanOstoPalauttaaOikeanMaaranRahaa() {
        assertEquals(260, kassa.syoEdullisesti(500));
    }

    @Test
    public void edullisenLounaanOstoKasvattaaMyytyjenEdullistenMaaraa() {
        kassa.syoEdullisesti(500);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullisenOstoKasvattaaRahaaKassassa() {
        kassa.syoEdullisesti(500);
        assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void rahaEiRiitaEdulliseen() {
        kassa.syoEdullisesti(100);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(100, kassa.syoEdullisesti(100));
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaanLounaanOstaminen() {
        kassa.syoMaukkaasti(800);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void oikeaMaaraVaihtorahaa() {
        assertEquals(400, kassa.syoMaukkaasti(800));
    }

    @Test
    public void maukastaEiVoiOstaaJosEiOleTarpeeksiRahaa() {
        kassa.syoMaukkaasti(100);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void rahaPalautuuJosEiRahaaMaukkaaseen() {
        assertEquals(100, kassa.syoMaukkaasti(100));
    }

    @Test
    public void edullisenVoiOstaaKortilla() {
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullisenMyyntiKortillaEiKasvattaKassanRahaa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void maukkaanVoiOstaaKortilla() {
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void maukkaanOstoKortillaEiKavattaKassanRahaa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void edulliseenPitaaOllaRahaaKortilla() {
        assertFalse(kassa.syoEdullisesti(koyhaKortti));
    }

    @Test
    public void eiMyydaEdullistaJosEiOleRahaa() {
        kassa.syoEdullisesti(koyhaKortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void maukkaaseenPitaaOllaRahaaKortilla() {
        assertFalse(kassa.syoMaukkaasti(koyhaKortti));
    }

    @Test
    public void eiMyydaMaukastaJosEiOleRahaa() {
        kassa.syoMaukkaasti(koyhaKortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void ladataanKorttiaKassanKautta() {
        kassa.lataaRahaaKortille(koyhaKortti, 1000);
        assertEquals(1100, koyhaKortti.saldo());
    }

    @Test
    public void eiVoiLadataNegatiivistaRahaMaaraaKortilleKassanKautta() {
        kassa.lataaRahaaKortille(koyhaKortti, -1);
        assertEquals(100, koyhaKortti.saldo());
    }

}
