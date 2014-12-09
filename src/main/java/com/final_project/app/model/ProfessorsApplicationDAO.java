package com.final_project.app.model;

import org.springframework.stereotype.Repository;

import com.i2rd.labs.profile.ProfileDAO;

import net.proteusframework.core.spring.ApplicationContextUtils;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/7/14 11:59 AM
 */
@Repository(ProfessorsApplicationDAO.RESOURCE_NAME)
public class ProfessorsApplicationDAO extends ProfileDAO<ProfessorsApplicationProfile, ProfessorsApplicationMember,
    ProfessorsMemberType, ProfessorsApplicationMemberType>
{
    public static final String RESOURCE_NAME = "ProfessorsApplicationDAO";

    public static ProfessorsApplicationDAO getInstance() {
        return (ProfessorsApplicationDAO) ApplicationContextUtils.getInstance().getContext().getBean(RESOURCE_NAME);
    }

    public ProfileDAO<?, ?, ProfessorsMemberType, ?> getParentManager() { return null; }

    public ProfessorsApplicationProfile getProfile() {
        ProfessorsApplicationProfile result = (ProfessorsApplicationProfile) getSession().createQuery("from " +
            ProfessorsApplicationProfile.class.getName()).uniqueResult();
        if(result == null)
            throw new IllegalStateException("The " + ProfessorsApplicationProfile.class.getSimpleName() + " table has no records."
                + "It should have exactly one. Either insert it manuallly or visit the management UI to create it.");

        return result;
    }
}
