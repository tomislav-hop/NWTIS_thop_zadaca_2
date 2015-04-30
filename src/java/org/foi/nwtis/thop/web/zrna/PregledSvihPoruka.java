/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.thop.web.zrna;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import org.foi.nwtis.thop.web.kontrole.Poruka;
import org.foi.nwtis.thop.web.slusaci.ObradaPoruka;

/**
 *
 * @author NWTiS_3
 */
@ManagedBean
@RequestScoped
public class PregledSvihPoruka {

    private String server;
    private String korisnik;
    private String lozinka;
    private List<Poruka> poruke;
    private Map<String, String> mape;
    private String odabranaMapa = "INBOX";

    /**
     * Creates a new instance of PregledSvihPoruka
     */
    public PregledSvihPoruka() {
        EmailPovezivanje ep = (EmailPovezivanje) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("emailPovezivanje");
        server = ep.getServer();
        korisnik = ep.getKorisnik();
        lozinka = ep.getLozinka();

        try {
            // Start the session
            java.util.Properties properties = System.getProperties();
            properties.put("mail.smtp.host", server);
            Session session = Session.getInstance(properties, null);

            // Connect to the store
            Store store = session.getStore("imap");
            store.connect(server, korisnik, lozinka);

            Folder osnovnaMapa = store.getDefaultFolder();
            Folder[] folderi = osnovnaMapa.list();

            mape = new HashMap<>();

            for (Folder f : folderi) {
                mape.put(f.getName(), f.getName());
            }
            store.close();

        } catch (NoSuchProviderException ex) {
            Logger.getLogger(ObradaPoruka.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ObradaPoruka.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Poruka> getPoruka() {
        // todo optimizirati broj čitanja poruka
        System.out.println("getPoruka");
        try {
            // Start the session
            java.util.Properties properties = System.getProperties();
            properties.put("mail.smtp.host", server);
            Session session = Session.getInstance(properties, null);

            // Connect to the store
            Store store = session.getStore("imap");
            store.connect(server, korisnik, lozinka);

            Folder folder = store.getFolder(odabranaMapa);
            folder.open(Folder.READ_ONLY);

            Message[] messages = folder.getMessages();

            poruke = new ArrayList<>();
            // Print each message
            for (int i = 0; i < messages.length; ++i) {
                MimeMessage m = (MimeMessage) messages[i];
                String tip = m.getContentType().toLowerCase();
                Poruka p = new Poruka(m.getHeader("Message-ID")[0],
                        m.getSentDate(), m.getFrom()[0].toString(),
                        m.getSubject(), m.getContentType(), m.getSize(), 0, m.getFlags(), null, false, true);
                poruke.add(p);
            }
            store.close();
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(ObradaPoruka.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ObradaPoruka.class.getName()).log(Level.SEVERE, null, ex);
        }

        return poruke;
    }

    public void setPoruka(List<Poruka> poruke) {
        this.poruke = poruke;
    }

    public Map<String, String> getMape() {
        return mape;
    }

    public void setMape(Map<String, String> mape) {
        this.mape = mape;
    }

    public String getOdabranaMapa() {
        return odabranaMapa;
    }

    public void setOdabranaMapa(String odabranaMapa) {
        this.odabranaMapa = odabranaMapa;
    }

    public String odaberiMapu() {
        return "";
    }
}
