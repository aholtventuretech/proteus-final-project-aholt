package com.final_project.app.model;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import com.i2rd.labs.profile.ProfileDAO;

import net.proteusframework.core.spring.ApplicationContextUtils;
import net.proteusframework.ui.search.PropertyConstraint;
import net.proteusframework.users.model.Principal;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/8/14 8:30 AM
 */
@Repository(ProfessorProjDAO.RESOURCE_NAME)
public class ProfessorProjDAO extends ProfileDAO<ProfessorProjProfile, ProfessorProjMember, ProfessorsMemberType, ProfessorProjMemberType>
{
    public static final String RESOURCE_NAME = "ProfessorProjDAO";

    public static ProfessorProjDAO getInstance() {
        return (ProfessorProjDAO) ApplicationContextUtils.getInstance().getContext().getBean(RESOURCE_NAME);
    }

    @Autowired
    private ProfessorsGroupDAO _parentManager;

    @Autowired
    private ProfessorsMembershipProcessor _membershipProcessor;

    @Override
    public ProfessorsGroupDAO getParentManager() { return _parentManager; }

    @Override
    public void validateProfile(ProfessorProjProfile profile) {
        super.validateProfile(profile);
        if(profile.getSlug() == null) throw new IllegalArgumentException("Slug is required on profile");
    }

    @Override
    public void deleteProfile(ProfessorProjProfile profile) {
        boolean success = false;
        beginTransaction();
        try{
            List<Principal> principals = new ArrayList<Principal>();
            List<ProfessorProjMember> members = getMembers(getMemberQLBuilder().appendCriteria(getQLPropertyMemberProfile(),
                PropertyConstraint.Operator.eq, profile));
            for(ProfessorProjMember member : members) {
                principals.add(member.getPrincipal());
            }
            deleteMembers(members);
            getSession().delete(profile);
            for(Principal principal : principals) {
                _membershipProcessor.process(principal);
            }
            success = true;
        }finally {
            if(success)
            {
                commitTransaction();
            }else {
                recoverableRollbackTransaction();
            }
        }
    }

    public ProfessorProjProfile getProfileForSlug(String slug) {
        List<ProfessorProjProfile> profiles = getProfiles(getProfileQLBuilder());
        for(ProfessorProjProfile profile : profiles) {
            if(profile.getSlug().equals(slug)) {
                return profile;
            }
        }
        throw new SlugNotPresentException(slug);
    }
}
