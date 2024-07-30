package com.capellax.spring_fundamentals.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

/**
 * Token that has been sent to the user's email for verification.
 */
@Entity
@Table(name = "verification_token")
public class VerificationToken {


    /** The unique ID for the record. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    /** The token that was sent to user. */
    @Lob
    @Column(name = "token", nullable = false, unique = true)
    private String token;


    /** The timestamp of when the token was created. */
    @Column(name = "created_timestamp", nullable = false)
    private Timestamp createdTimestamp;


    /** The user this verification token is for. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser user;



    // Getters & Setter of the verification_token model

    /**
     * Gets the user.
     * @return The user.
     */
    public LocalUser getUser() {
        return user;
    }



    /**
     * The timestamp when the token was created.
     * @return The timestamp.
     */
    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    /**
     * Sets the timestamp of when the token was created.
     * @param createdTimestamp The timestamp.
     */
    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    /**
     * Gets the token.
     * @return The token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token.
     * @param token The token.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets the id.
     * @return The id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * @param id The id.
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * Sets the user.
     * @param user The user.
     */
    public void setUser(LocalUser user) {
        this.user = user;
    }


}


