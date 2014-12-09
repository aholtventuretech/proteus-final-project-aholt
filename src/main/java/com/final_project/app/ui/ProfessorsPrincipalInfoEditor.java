package com.final_project.app.ui;

import com.final_project.app.model.ProfessorsPrincipalInfo;

import org.apache.log4j.Level;

import com.i2rd.labs.profile.ui.EntityValueEditor;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/8/14 10:34 AM
 */
public class ProfessorsPrincipalInfoEditor extends EntityValueEditor<ProfessorsPrincipalInfo>
{
    @Override
    protected ProfessorsPrincipalInfo newEntity() { return new ProfessorsPrincipalInfo(); }

    @Override
    protected void setupUI()
    {
        addClassName("professors_principal_info_editor");
    }

    @Override
    protected void updateUI(ProfessorsPrincipalInfo value, boolean editable) { }

    @Override
    protected ProfessorsPrincipalInfo updateEntity(ProfessorsPrincipalInfo value, Level logErrorLevel) { return value; }
}
