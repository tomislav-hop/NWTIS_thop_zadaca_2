/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.thop.web.zrna;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author NWTiS_3
 */
@ManagedBean
@RequestScoped
public class SlanjePoruke implements Serializable {

    private String salje;
    private String prima;
    private String predmet;
    private String poruka;

    /**
     * Creates a new instance of SlanjePoruke
     */
    public SlanjePoruke() {
    }

    public String getSalje() {
        return salje;
    }

    public void setSalje(String salje) {
        this.salje = salje;
    }

    public String getPrima() {
        return prima;
    }

    public void setPrima(String prima) {
        this.prima = prima;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Object saljiPoruku() {
        EmailPovezivanje ep = (EmailPovezivanje) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("emailPovezivanje");
        String smtpHost = ep.getServer();

        try {
            // Create the JavaMail session
            java.util.Properties properties = System.getProperties();
            properties.put("mail.smtp.host", smtpHost);

            Session session
                    = Session.getInstance(properties, null);

            // Construct the message
            MimeMessage message = new MimeMessage(session);

            // Set the from address
            Address fromAddress = new InternetAddress(salje);
            message.setFrom(fromAddress);

            // Parse and set the recipient addresses
            Address[] toAddresses = InternetAddress.parse(prima);
            message.setRecipients(Message.RecipientType.TO, toAddresses);

            // Set the subject and text
            message.setSubject(predmet);
            message.setText(poruka);

            Transport.send(message);

            return "OK";

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (SendFailedException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }
}