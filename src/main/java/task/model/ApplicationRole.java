/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author celem
 */
@Entity
@Table(name = "APPLICATION_ROLE")

public class ApplicationRole implements Serializable {

    @JoinTable(name = "USER_ROLE", joinColumns = {
        @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")},
            inverseJoinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ApplicationUser> applicationUserList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NAME")
    private String name;

    public ApplicationRole() {
    }

    public ApplicationRole(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationRole)) {
            return false;
        }
        ApplicationRole other = (ApplicationRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ApplicationRole{" +
                //"applicationUserList=" + applicationUserList +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @XmlTransient
    public List<ApplicationUser> getApplicationUserList() {
        return applicationUserList;
    }

    public void setApplicationUserList(List<ApplicationUser> applicationUserList) {
        this.applicationUserList = applicationUserList;
    }
    
}
