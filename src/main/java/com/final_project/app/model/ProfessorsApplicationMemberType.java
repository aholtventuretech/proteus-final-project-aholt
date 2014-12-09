package com.final_project.app.model;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/7/14 11:50 AM
 */
public enum ProfessorsApplicationMemberType implements ProfessorsMemberType
{
    admin;

    @Override
    public boolean manageProfessorMembers() {
        return true;
    }
}
