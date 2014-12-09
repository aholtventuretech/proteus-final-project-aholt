package com.final_project.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.i2rd.labs.profile.AbstractProfile;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/7/14 11:48 AM
 */
@Entity
public class ProfessorsApplicationProfile extends AbstractProfile
{
    private static final long serialVersionUID = 8497847580600918976L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "professorsapplicationprofile_id_seq")
    @SequenceGenerator(name = "professorsapplicationprofile_id_seq", sequenceName = "professorsapplicationprofile_id_seq")
    @Override
    public Long getId() {
        return super.getId();
    }
}
