package com.final_project.app.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.i2rd.labs.profile.AbstractMember;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/8/14 8:22 AM
 */
@Entity
public class ProfessorProjMember extends AbstractMember
{
    private static final long serialVersionUID = 5353649915306593496L;

    private ProfessorProjProfile _profile;

    private ProfessorProjMemberType _memberType;

    public ProfessorProjMember() { super(); }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "professorprojmember_id_seq")
    @SequenceGenerator(name = "professorprojmember_id_seq", sequenceName = "professorprojmember_id_seq")
    @Override
    public Long getId() { return super.getId(); }

    @ManyToOne
    @NotNull
    public ProfessorProjProfile getProfile() { return _profile; }

    public void setProfile(ProfessorProjProfile profile) { _profile = profile; }

    @Enumerated(EnumType.STRING)
    @NotNull
    public ProfessorProjMemberType getMemberType() { return _memberType; }

    public void setMemberType(ProfessorProjMemberType memberType) { _memberType = memberType; }
}
