package com.ivision.app.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A Ivision.
 */
@Entity
@Table(name = "ivision")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ivision implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_photo", precision = 21, scale = 2)
    private BigDecimal profile_photo;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "telphone")
    private String telphone;

    @Column(name = "mailbox")
    private String mailbox;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getProfile_photo() {
        return profile_photo;
    }

    public Ivision profile_photo(BigDecimal profile_photo) {
        this.profile_photo = profile_photo;
        return this;
    }

    public void setProfile_photo(BigDecimal profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getNickname() {
        return nickname;
    }

    public Ivision nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public Ivision gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Ivision birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getTelphone() {
        return telphone;
    }

    public Ivision telphone(String telphone) {
        this.telphone = telphone;
        return this;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getMailbox() {
        return mailbox;
    }

    public Ivision mailbox(String mailbox) {
        this.mailbox = mailbox;
        return this;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ivision)) {
            return false;
        }
        return id != null && id.equals(((Ivision) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Ivision{" +
            "id=" + getId() +
            ", profile_photo=" + getProfile_photo() +
            ", nickname='" + getNickname() + "'" +
            ", gender='" + getGender() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", telphone='" + getTelphone() + "'" +
            ", mailbox='" + getMailbox() + "'" +
            "}";
    }
}
