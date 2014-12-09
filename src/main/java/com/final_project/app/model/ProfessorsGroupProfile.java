package com.final_project.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.util.ArrayList;
import java.util.List;

import com.i2rd.labs.profile.AbstractProfile;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/7/14 12:05 PM
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "slug")})
public class ProfessorsGroupProfile extends AbstractProfile
{
    private static final long serialVersionUID = -3351189111002671569L;

    private ProfessorsApplicationProfile _parent;

    private List<ProfessorProjProfile> _children = new ArrayList<ProfessorProjProfile>(0);

    private List<ProfessorsGroupMember> _members = new ArrayList<ProfessorsGroupMember>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "professorsgroupprofile_id_seq")
    @SequenceGenerator(name = "professorsgroupprofile_id_seq", sequenceName = "professorsgroupprofile_id_seq")
    @Override
    public Long getId() { return super.getId(); }

    @ManyToOne
    public ProfessorsApplicationProfile getParent() { return _parent; }

    public void setParent(ProfessorsApplicationProfile parent) { _parent = parent; }

    @OneToMany(mappedBy = "parent")
    public List<ProfessorProjProfile> getChildren() { return _children; }

    public void setChildren(List<ProfessorProjProfile> children) { _children = children; }

    @OneToMany(mappedBy = "profile")
    public List<ProfessorsGroupMember> getMembers() { return _members; }

    public void setMembers(List<ProfessorsGroupMember> members) { _members = members; }
}
