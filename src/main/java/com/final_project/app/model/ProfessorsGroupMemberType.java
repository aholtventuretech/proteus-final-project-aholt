package com.final_project.app.model;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/7/14 12:08 PM
 */
public enum ProfessorsGroupMemberType implements ProfessorsMemberType
{
    groupManager;

    @Override
    public boolean manageProfessorMembers() { return true; }
}
