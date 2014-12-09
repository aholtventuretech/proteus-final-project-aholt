package com.final_project.app.model;

import com.i2rd.labs.profile.MemberType;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/7/14 11:45 AM
 */
public interface ProfessorsMemberType extends MemberType
{
    /**
     * Check if the MemberType can manage Professor Members.
     * @return true for MemberTypes that can manage ProfessorMember
     */
    boolean manageProfessorMembers();
}
