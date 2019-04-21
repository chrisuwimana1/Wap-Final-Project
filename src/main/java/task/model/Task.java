/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author celem
 */
@Entity
@Table(name = "TASK")

public class Task implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;

    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category categoryId;
    @JoinColumn(name = "TASK_OWNER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ApplicationUser taskOwnerId;
    @JoinColumn(name = "PROJECT_MANAGER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ApplicationUser projectManagerId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRIORITY")
    private int priority;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DUE_DATE")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Size(max = 80)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "COMPLETION_DATE")
    @Temporal(TemporalType.DATE)
    private Date completionDate;

    public Task() {
    }

    public Task(Integer id) {
        this.id = id;
    }

    public Task(Integer id, int priority, String name, Date dueDate) {
        this.id = id;
        this.priority = priority;
        this.name = name;
        this.dueDate = dueDate;
    }

    public Task(int priority, String name, Date dueDate) {
        this.priority = priority;
        this.name = name;
        this.dueDate = dueDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
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
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "taskmngt.model.Task[ id=" + id + " ]";
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public ApplicationUser getTaskOwnerId() {
        return taskOwnerId;
    }

    public void setTaskOwnerId(ApplicationUser taskOwnerId) {
        this.taskOwnerId = taskOwnerId;
    }

    public ApplicationUser getProjectManagerId() {
        return projectManagerId;
    }

    public void setProjectManagerId(ApplicationUser projectManagerId) {
        this.projectManagerId = projectManagerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}