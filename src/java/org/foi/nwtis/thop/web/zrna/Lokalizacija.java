/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.thop.web.zrna;

import java.util.HashMap;
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
public class Lokalizacija {

    private static final Map<String,Object> jezici;
    private String odabraniJezik;
    private Locale vazecaLokalizacija;
    
    static{
        jezici = new HashMap<>();
        jezici.put("Hrvatski", new Locale("hr"));
        jezici.put("English", Locale.ENGLISH);
        jezici.put("Deutsch", Locale.GERMAN);
    }
            
    /**
     * Creates a new instance of Lokalizacija
     */
    public Lokalizacija() {
        vazecaLokalizacija = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        odabraniJezik = vazecaLokalizacija.getLanguage();
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
    
    
}
