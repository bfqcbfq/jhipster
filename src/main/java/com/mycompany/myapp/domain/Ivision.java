package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

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

    @NotNull
    @Column(name = "cust_name", nullable = false)
    private String cust_name;

    @Column(name = "cust_source")
    private String cust_source;

    @Column(name = "cust_industry")
    private String cust_industry;

    @Column(name = "cust_level")
    private String cust_level;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public Ivision cust_name(String cust_name) {
        this.cust_name = cust_name;
        return this;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_source() {
        return cust_source;
    }

    public Ivision cust_source(String cust_source) {
        this.cust_source = cust_source;
        return this;
    }

    public void setCust_source(String cust_source) {
        this.cust_source = cust_source;
    }

    public String getCust_industry() {
        return cust_industry;
    }

    public Ivision cust_industry(String cust_industry) {
        this.cust_industry = cust_industry;
        return this;
    }

    public void setCust_industry(String cust_industry) {
        this.cust_industry = cust_industry;
    }

    public String getCust_level() {
        return cust_level;
    }

    public Ivision cust_level(String cust_level) {
        this.cust_level = cust_level;
        return this;
    }

    public void setCust_level(String cust_level) {
        this.cust_level = cust_level;
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
            ", cust_name='" + getCust_name() + "'" +
            ", cust_source='" + getCust_source() + "'" +
            ", cust_industry='" + getCust_industry() + "'" +
            ", cust_level='" + getCust_level() + "'" +
            "}";
    }
}
