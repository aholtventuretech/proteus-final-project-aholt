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
 * @since 12/7/14 12:09 PM
 */
@Entity
public class ProfessorsGroupMember extends AbstractMember
{
    private static final long serialVersionUID = 7887496068647251971L;

    private ProfessorsGroupProfile _profile;

    private ProfessorsGroupMemberType _memberType;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "professorsgroupmember_id_seq")
    @SequenceGenerator(name = "professorsgroupmember_id_seq", sequenceName = "professorsgroupmember_id_seq")
    @Override
    public Long getId() { return super.getId(); }

    @ManyToOne
    @NotNull
    public ProfessorsGroupProfile getProfile() { return _profile; }

    public void setProfile(ProfessorsGroupProfile profile) { _profile = profile; }

    @Enumerated(EnumType.STRING)
    @NotNull
    public ProfessorsGroupMemberType getMemberType() { return _memberType; }

    public void setMemberType(ProfessorsGroupMemberType memberType) { _memberType = memberType; }
}
