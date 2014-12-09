package com.final_project.app.model;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/8/14 8:21 AM
 */
public enum ProfessorProjMemberType implements ProfessorsMemberType
{
    Lecturer,
    AdjunctProfessor,
    AssistantProfessor,
    AssociateProfessor,
    Professor;

    @Override
    public boolean manageProfessorMembers() { return true; }
}
