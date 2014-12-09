package com.final_project.app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import com.i2rd.labs.profile.ProfileDAO;

import net.proteusframework.core.spring.ApplicationContextUtils;
import net.proteusframework.ui.search.PropertyConstraint.Operator;
import net.proteusframework.users.model.Principal;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/8/14 8:30 AM
 */
@Repository(ProfessorsGroupDAO.RESOURCE_NAME)
public class ProfessorsGroupDAO extends ProfileDAO<ProfessorsGroupProfile, ProfessorsGroupMember, ProfessorsMemberType, ProfessorsGroupMemberType>
{
    public static final String RESOURCE_NAME = "ProfessorsGroupDAO";

    public static ProfessorsGroupDAO getInstance() {
        return (ProfessorsGroupDAO) ApplicationContextUtils.getInstance().getContext().getBean(RESOURCE_NAME);
    }

    @Autowired
    private ProfessorsApplicationDAO _parentManager;

    @Autowired
    private ProfessorsMembershipProcessor _membershipProcessor;

    @Override
    public ProfessorsApplicationDAO getParentManager() { return _parentManager; }

    public void deleteProfile(ProfessorsGroupProfile profile) {
        boolean success = false;
        ProfessorProjDAO projectManager = ProfessorProjDAO.getInstance();
        beginTransaction();
        try {
            List<ProfessorProjProfile> children = new ArrayList<ProfessorProjProfile>(profile.getChildren());
            profile.getChildren().clear();
            projectManager.deleteProfiles(children);
            List<Principal> principals = new ArrayList<>();
            List<ProfessorsGroupMember> members = getMembers(getMemberQLBuilder().appendCriteria(getQLPropertyMemberProfile(),
                Operator.eq, profile));
            for(ProfessorsGroupMember member : members)
            {
                principals.add(member.getPrincipal());
            }
            deleteMembers(members);
            getSession().delete(profile);
            for(Principal principal : principals) {
                _membershipProcessor.process(principal);
            }
            success = true;
        } finally {
            if(success) {
                commitTransaction();
            } else {
                recoverableRollbackTransaction();
            }
        }
    }
}
