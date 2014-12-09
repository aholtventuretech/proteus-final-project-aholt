package com.final_project.app.ui;

import com.final_project.app.model.ProfessorProjDAO;
import com.final_project.app.model.ProfessorProjProfile;
import com.google.common.base.Preconditions;

import com.i2rd.contentmodel.miwt.PropertyEditor;
import com.i2rd.hibernate.util.HibernateUtil;

import net.proteusframework.core.hibernate.dao.EntityRetriever;
import net.proteusframework.core.locale.TextSources;
import net.proteusframework.ui.miwt.History;
import net.proteusframework.ui.miwt.ReflectiveAction;
import net.proteusframework.ui.miwt.component.Container;
import net.proteusframework.ui.miwt.component.TabItemDisplay;
import net.proteusframework.ui.miwt.component.composite.TabbedContainerImpl;
import net.proteusframework.ui.miwt.component.composite.editor.ValueEditor;
import net.proteusframework.ui.miwt.event.ActionEvent;
import net.proteusframework.ui.miwt.util.CommonActions;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/8/14 10:58 AM
 */
public class ProfessorProjDetails extends Container
{
    private ProfessorProjDAO _projectManager = ProfessorProjDAO.getInstance();

    private ProfessorProjProfile _profile;

    private History _history;

    public ProfessorProjDetails(ProfessorProjProfile profile, History history) {
        _profile = profile;
        _history = history;
    }

    @Override
    public void init() {
        super.init();
        addClassName("details professor");

        final PropertyEditor<ProfessorProjProfile> propertyEditor = new PropertyEditor<>();
        final ValueEditor<ProfessorProjProfile> valueEditor = new ProfessorProjProfileEditor();
        valueEditor.setValue(getProfile());
        valueEditor.setEditable(false);
        propertyEditor.setValueEditor(valueEditor);

        final ReflectiveAction edit = CommonActions.EDIT.defaultAction();
        final ReflectiveAction save = CommonActions.SAVE.defaultAction();
        final ReflectiveAction cancel = CommonActions.CANCEL.defaultAction();
        propertyEditor.setPersistenceActions(edit, save, cancel);
        edit.setActionListener((ev) -> {
            valueEditor.setEditable(true);
            edit.setEnabled(false);
            save.setEnabled(true);
            cancel.setEnabled(true);
        });
        save.setActionListener((ev) -> {
            propertyEditor.getNotifiable().clearNotifications();
            if(valueEditor.validateUIValue(propertyEditor.getNotifiable())) {
                _profile = valueEditor.commitValue();
                _projectManager.saveProfile(_profile);
                valueEditor.setEditable(false);
                edit.setEnabled(true);
                save.setEnabled(false);
                cancel.setEnabled(false);
            }
        });
        cancel.setActionListener((ev) -> {
            valueEditor.setEditable(false);
            valueEditor.setValue(getProfile());
            edit.setEnabled(true);
            save.setEnabled(false);
            cancel.setEnabled(false);
        });

        add(propertyEditor);

        save.setEnabled(false);
        cancel.setEnabled(false);

        if(HibernateUtil.getInstance().isTransient(_profile)) {
            edit.actionPerformed(new ActionEvent(this, this, null));
        } else {
        }
    }

    protected ProfessorProjProfile getProfile() { return EntityRetriever.getInstance().reattachIfNecessary(_profile); }
}
