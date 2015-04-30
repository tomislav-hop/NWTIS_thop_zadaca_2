/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.thop.web.zrna;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author NWTiS_3
 */
@ManagedBean
@RequestScoped
public class EmailPovezivanje implements Serializable{

    private String server = "127.0.0.1";
    private String korisnik = "servis@nwtis.nastava.foi.hr";
    private String lozinka = "";

    /**
     * Creates a new instance of EmailPovezivanje
     */
    public EmailPovezivanje() {
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String saljiPoruku() {
        return "OK";
    }

    public String citajPoruku() {
        return "OK";
    }
}
