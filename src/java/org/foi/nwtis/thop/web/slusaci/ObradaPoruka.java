/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.thop.web.slusaci;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author NWTiS_3
 */
public class ObradaPoruka extends Thread {

    // todo preuzeti podatke iz postavki
    private String server = "127.0.0.1";
    private String korisnik = "servis@nwtis.nastava.foi.hr";
    private String lozinka = "123456";

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        while (true) {

            try {
                // Start the session
                java.util.Properties properties = System.getProperties();
                properties.put("mail.smtp.host", server);
                Session session = Session.getInstance(properties, null);

                // Connect to the store
                Store store = session.getStore("imap");
                store.connect(server, korisnik, lozinka);

                Folder osnovnaMapa = store.getDefaultFolder();

                // todo preuzmi iz postavi
                String mapaNWTisPoruke = "NWTIS_poruke";
                if (!osnovnaMapa.getFolder(mapaNWTisPoruke).exists()) {
                    osnovnaMapa.getFolder(mapaNWTisPoruke).create(Folder.HOLDS_MESSAGES);
                }

                // todo preuzmi iz postavi
                String mapaNeNWTisPoruke = "Ostale_poruke";
                if (!osnovnaMapa.getFolder(mapaNeNWTisPoruke).exists()) {
                    osnovnaMapa.getFolder(mapaNeNWTisPoruke).create(Folder.HOLDS_MESSAGES);
                }

                Folder folder = store.getFolder("INBOX");
                folder.open(Folder.READ_WRITE);

                Message[] messages = folder.getMessages();

                String NWTiS_predmet = "NWTiS poruka";

                // Print each message
                for (int i = 0; i < messages.length; ++i) {
                    MimeMessage m = (MimeMessage) messages[i];
                    String tip = m.getContentType().toLowerCase();
                    if (tip.equals("text/plain")) {
                        if (m.getSubject().startsWith(NWTiS_predmet)) {
                            String sadrzaj = (String) m.getContent();
                            // todo dovršiti dalje
                        }
                    }
                }
            } catch (NoSuchProviderException ex) {
                Logger.getLogger(ObradaPoruka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(ObradaPoruka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ObradaPoruka.class.getName()).log(Level.SEVERE, null, ex);
            }

            // todo preuzeti iz postavki
            int brojSekundiZaSpavanje = 30;
            try {
                sleep(brojSekundiZaSpavanje * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ObradaPoruka.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }

    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }
}
