package com.final_project.app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.i2rd.labs.profile.MembershipProcessor;
import com.i2rd.labs.profile.ProfileDAO;

import net.proteusframework.core.spring.ApplicationContextUtils;
import net.proteusframework.users.model.PrincipalGroup;
import net.proteusframework.users.model.Role;
import net.proteusframework.users.model.dao.PrincipalDAO;
import net.proteusframework.users.model.dao.RoleDAO;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/8/14 8:33 AM
 */
@Service(ProfessorsMembershipProcessor.RESOURCE_NAME)
public class ProfessorsMembershipProcessor extends MembershipProcessor<ProfessorsMemberType>
{
    public static final String RESOURCE_NAME = "ProfessorsMembershipProcessor";

    public static ProfessorsMembershipProcessor getInstance() {
        return (ProfessorsMembershipProcessor) ApplicationContextUtils.getInstance().getContext().getBean(RESOURCE_NAME);
    }

    @Autowired
    private ProfessorProjDAO _projectManager;

    @Autowired
    private ProfessorsGroupDAO _groupManager;

    @Autowired
    private ProfessorsApplicationDAO _applicationManager;

    @Autowired
    private RoleDAO _roleProcessor;

    @Autowired
    private PrincipalDAO _principalProcessor;

    protected List<ProfileDAO<?, ?, ProfessorsMemberType, ? extends ProfessorsMemberType>> getProfileManagers() {
        List<ProfileDAO<?, ?, ProfessorsMemberType, ? extends ProfessorsMemberType>> list = new ArrayList<ProfileDAO<?, ?, ProfessorsMemberType, ? extends ProfessorsMemberType>>();
        list.add(_projectManager);
        list.add(_groupManager);
        list.add(_applicationManager);
        return list;
    }

    private static final String professorsRoleName = "ProfessorsMembershipProcessors_role";

    private static final String professorsPrincipalGroupName = "ProfessorsMembershipProcessor_principalGroup";

    public Role getOrCreateRole() {
        Role role = _roleProcessor.getRoleByProgrammaticName(professorsRoleName);
        if(role == null) {
            role = new Role();
            role.setProgrammaticName(professorsRoleName);
            _roleProcessor.save(role);
        }
        return role;
    }

    public PrincipalGroup getOrCreatePrincipalGroup() {
        PrincipalGroup principalGroup = _principalProcessor.getGroupByProgrammaticIdentifier(professorsPrincipalGroupName);
        if(principalGroup == null) {
            principalGroup = new PrincipalGroup();
            principalGroup.setProgrammaticIdentifier(professorsPrincipalGroupName);
            _principalProcessor.saveGroup(principalGroup);
        }
        return principalGroup;
    }

    @Override
    protected Role getRole(ProfessorsMemberType memberType) {
        return memberType == ProfessorProjMemberType.Professor ? getOrCreateRole() : null;
    }

    @Override
    protected PrincipalGroup getPrincipalGroup(ProfessorsMemberType memberType) {
        return memberType == ProfessorProjMemberType.Professor ? getOrCreatePrincipalGroup() : null;
    }

    @Override
    protected Set<Role> getMemberTypeRoles() { return Collections.singleton(getOrCreateRole()); }

    @Override
    protected Set<PrincipalGroup> getMemberTypePrincipalGroups() {
        return Collections.singleton(getOrCreatePrincipalGroup());
    }
}