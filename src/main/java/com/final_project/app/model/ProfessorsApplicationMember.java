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
 * @since 12/7/14 11:52 AM
 */
@Entity
public class ProfessorsApplicationMember extends AbstractMember
{
    private static final long serialVersionUID = 8668926350073040995L;

    private ProfessorsApplicationProfile _profile;
    private ProfessorsApplicationMemberType _memberType;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "professorsapplicationmember_id_seq")
    @SequenceGenerator(name = "professorsapplicationmember_id_seq", sequenceName = "professorsapplicationmember_id_seq")
    @Override
    public Long getId() { return super.getId(); }

    @ManyToOne
    @NotNull
    public ProfessorsApplicationProfile getProfile() { return _profile; }

    public void setProfile(ProfessorsApplicationProfile profile) { _profile = profile; }

    @Enumerated(EnumType.STRING)
    @NotNull
    public ProfessorsApplicationMemberType getMemberType() { return _memberType; }

    public void setMemberType(ProfessorsApplicationMemberType memberType) { _memberType = memberType; }
}
