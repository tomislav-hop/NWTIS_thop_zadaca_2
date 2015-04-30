/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.thop.web.zrna;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author NWTiS_3
 */
@ManagedBean
@SessionScoped
public class Lokalizacija implements Serializable {

    private static Map<String, Object> jezici;

    public Map<String, Object> getJezici() {
        return jezici;
    }
    private String odabraniJezik;
    private Locale vazecaLokalizacija;

    static {
        jezici = new HashMap<>();
        jezici.put("Hrvatski", new Locale("hr"));
        jezici.put("English", Locale.ENGLISH);
        jezici.put("Deutsch", Locale.GERMAN);
    }

    /**
     * Creates a new instance of Lokalizacija
     */
    public Lokalizacija() {
//        vazecaLokalizacija = FacesContext.getCurrentInstance().getViewRoot().getLocale();
//        odabraniJezik = vazecaLokalizacija.getLanguage();
    }

    public String getOdabraniJezik() {
        return odabraniJezik;
    }

    public void setOdabraniJezik(String odabraniJezik) {
        this.odabraniJezik = odabraniJezik;
    }

    public Locale getVazecaLokalizacija() {
        return vazecaLokalizacija;
    }

    public void setVazecaLokalizacija(Locale vazecaLokalizacija) {
        this.vazecaLokalizacija = vazecaLokalizacija;
    }
    
    public Object odaberiJezik() {
        Iterator i = jezici.keySet().iterator();
        while(i.hasNext()) {
            String kljuc = (String) i.next();
            Locale l = (Locale) jezici.get(kljuc);
            if(odabraniJezik.equals(l.getLanguage())) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale(l);
                vazecaLokalizacija = l;
                return "OK";
            }
        }
        return "ERROR";
    }
}
