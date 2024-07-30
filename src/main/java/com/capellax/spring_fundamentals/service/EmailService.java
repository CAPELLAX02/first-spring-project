package com.capellax.spring_fundamentals.service;

import com.capellax.spring_fundamentals.exception.EmailFailureException;
import com.capellax.spring_fundamentals.models.LocalUser;
import com.capellax.spring_fundamentals.models.VerificationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service for handling emails being sent.
 */
@Service
public class EmailService {

    /** The form address to use on emails. */
    @Value("${email.form}")
    private String fromAddress;

    /** The URL of the front-end for links. */
    @Value("${app.frontend.url}")
    private String url;

    /** The JavaMailSender instance. */
    private JavaMailSender javaMailSender;


    /**
     * Constructor for spring injection.
     * @param javaMailSender
     */
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    /**
     * Makes a SimpleMailMessage for sending.
     * @return The SimpleMailMessage created.
     */
    public SimpleMailMessage makeMailMessage()
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromAddress);
        return simpleMailMessage;
    }


    /**
     * Sends a verification email to the user.
     * @param verificationToken The verification token to be sent.
     * @throws EmailFailureException Thrown if are unable to send the email.
     */
    public void sendVerificationEmail(VerificationToken verificationToken) throws EmailFailureException {
        SimpleMailMessage message = makeMailMessage();

        message.setTo(verificationToken.getUser().getEmail());
        message.setSubject("Verify your email to active your account.");
        message.setText("Please follow the link below to verify your email to active your account.\n" +
                url + "/auth/verify?token=" + verificationToken.getToken());

        try {
            javaMailSender.send(message);

        } catch (MailException exception) {
            throw new EmailFailureException();
        }
    }


    /**
     * Sends a password reset request email to the user.
     * @param user The user to send to.
     * @param token The token to send the user for reset password.
     * @throws EmailFailureException
     */
    public void sendPasswordResetEmail(LocalUser user, String token) throws EmailFailureException {
        SimpleMailMessage message = makeMailMessage();

        message.setTo(user.getEmail());
        message.setSubject("Your password reset request link.");
        message.setText("You requested a password reset on our website. Please " +
                "find the link below to be able to reset your password.\n" + url +
                "/auth/reset?token=" + token);

        try {
            javaMailSender.send(message);

        } catch (MailException exception) {
            throw new EmailFailureException();
        }
    }

}
