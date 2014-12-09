package com.final_project.app.model;

import org.springframework.stereotype.Repository;

import com.i2rd.labs.profile.PrincipalInfoDAO;

import net.proteusframework.core.spring.ApplicationContextUtils;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/7/14 11:40 AM
 */
@Repository(ProfessorsPrincipalInfoDAO.RESOURCE_NAME)
public class ProfessorsPrincipalInfoDAO extends PrincipalInfoDAO<ProfessorsPrincipalInfo>
{
    public static final String RESOURCE_NAME = "ProfessorsPrincipalInfoDAO";

    public static ProfessorsPrincipalInfoDAO getInstance() {
        return (ProfessorsPrincipalInfoDAO) ApplicationContextUtils.getInstance().getContext().getBean(RESOURCE_NAME);
    }
}
