package com.final_project.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import java.util.Date;

import com.i2rd.labs.profile.AbstractProfile;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/7/14 12:05 PM
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "slug")})
public class ProfessorProjProfile extends AbstractProfile
{
    private static final long serialVersionUID = -6838457477652841480L;

    private ProfessorsGroupProfile _parent;

    private String _professorType;

    private String _areaOfResearch;

    private boolean _onSabbatical;

    private Date _dateJoined;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "professorprojprofile_id_seq")
    @SequenceGenerator(name = "professorprojprofile_id_seq", sequenceName = "professorprojprofile_id_seq")
    @Override
    public Long getId() { return super.getId(); }

    @ManyToOne
    public ProfessorsGroupProfile getParent() { return _parent; }

    public void setParent(ProfessorsGroupProfile parent) { _parent = parent; }

    @NotNull
    public String getProfessorType() { return _professorType; }
    public void setProfessorType(String professorType) { _professorType = professorType; }

    @NotNull
    public Date getDateJoined() { return _dateJoined; }
    public void setDateJoined(Date dateJoined) { _dateJoined = dateJoined; }

    public String getAreaOfResearch() { return _areaOfResearch; }
    public void setAreaOfResearch(String areaOfResearch) { _areaOfResearch = areaOfResearch; }

    public boolean getOnSabbatical() { return _onSabbatical; }
    public void setOnSabbatical(boolean onSabbatical) { _onSabbatical = onSabbatical; }
}
