/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.thop.web.slusaci;

import java.io.File;
import java.util.ArrayList;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.foi.nwtis.thop.konfiguracije.bp.BP_Konfiguracija;

/**
 * Web application lifecycle listener.
 *
 * @author NWTiS_3
 */
@WebListener
public class SlusacAplikacije implements ServletContextListener {

    BP_Konfiguracija BP_Konfig = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String direktorij = sce.getServletContext().getRealPath("/WEB-INF") + File.separator;
        String datoteka = direktorij + sce.getServletContext().getInitParameter("konfiguracija");

        BP_Konfiguracija bpk = new BP_Konfiguracija(datoteka);

        sce.getServletContext().setAttribute("BP_Konfig", bpk);

        ArrayList ak = new ArrayList();
        sce.getServletContext().setAttribute("AktivniKorisnici", ak);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("BP_Konfig");
        sce.getServletContext().removeAttribute("AktivniKorisnici");
    }
}
