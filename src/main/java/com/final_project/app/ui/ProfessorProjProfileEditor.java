package com.final_project.app.ui;

import com.final_project.app.model.ProfessorProjDAO;
import com.final_project.app.model.ProfessorProjMemberType;
import com.final_project.app.model.ProfessorProjProfile;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;

import com.i2rd.labs.profile.ui.EntityValueEditor;

import net.proteusframework.core.html.HTMLElement;
import net.proteusframework.core.locale.TextSources;
import net.proteusframework.core.notification.Notifiable;
import net.proteusframework.ui.miwt.component.Calendar;
import net.proteusframework.ui.miwt.component.Checkbox;
import net.proteusframework.ui.miwt.component.ComboBox;
import net.proteusframework.ui.miwt.component.Container;
import net.proteusframework.ui.miwt.component.Field;
import net.proteusframework.ui.miwt.component.composite.Message;
import net.proteusframework.ui.miwt.data.SimpleListModel;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/8/14 9:37 AM
 */
public class ProfessorProjProfileEditor extends EntityValueEditor<ProfessorProjProfile>
{
    protected Field _nameField;
    protected Field _slugField;
    protected Calendar _dateJoinedCalendar;
    protected ComboBox _typeCombo;
    protected Field _areaOfResearchField;
    protected Checkbox _onSabbaticalChekbox;

    @Override
    protected ProfessorProjProfile newEntity() { return new ProfessorProjProfile(); }

    @Override
    protected void setupUI() {
        addClassName("professor_proj_profile_editor");
        _nameField = new Field();
        _slugField = new Field();
        _areaOfResearchField = new Field();

        _typeCombo = new ComboBox(new SimpleListModel<>(ProfessorProjMemberType.values()));

        _dateJoinedCalendar = new Calendar();

        _onSabbaticalChekbox = new Checkbox();

        add(Container.of(HTMLElement.div, "prop name", TextSources.create("Name"), _nameField));
        add(Container.of(HTMLElement.div, "prop slug", TextSources.create("Slug"), _slugField));
        add(Container.of(HTMLElement.div, "prop date", TextSources.create("Date Joined"), _dateJoinedCalendar));
        add(Container.of(HTMLElement.div, "prop profType", TextSources.create("Professor Type"), _typeCombo));
        add(Container.of(HTMLElement.div, "prop areaOfResearch", TextSources.create("Area of Research"), _areaOfResearchField));
        add(Container.of(HTMLElement.div, "prop onSabbatical", TextSources.create("On Sabbatical"), _onSabbaticalChekbox));
    }

    @Override
    protected void updateUI(ProfessorProjProfile value, boolean editable) {
        _nameField.setText(value.getName());
        _slugField.setText(value.getSlug());
        _dateJoinedCalendar.setDate(value.getDateJoined());
        _typeCombo.setSelectedObject(value.getProfessorType());
        _areaOfResearchField.setText(value.getAreaOfResearch());
        _onSabbaticalChekbox.setSelected(value.getOnSabbatical());

        _nameField.setEnabled(editable);
        _slugField.setEnabled(editable);
        _dateJoinedCalendar.setEnabled(editable);
        _typeCombo.setEnabled(editable);
        _areaOfResearchField.setEnabled(editable);
        _onSabbaticalChekbox.setEnabled(editable);
    }

    @Override
    protected ProfessorProjProfile updateEntity(ProfessorProjProfile value, Level logErrorLevel) {
        value.setName(_nameField.getText());
        value.setSlug(_slugField.getText());
        value.setDateJoined(_dateJoinedCalendar.getDate());
        value.setProfessorType(((ProfessorProjMemberType) _typeCombo.getSelectedObject()).toString());
        value.setAreaOfResearch(_areaOfResearchField.getText());
        value.setOnSabbatical(_onSabbaticalChekbox.isSelected());
        return value;
    }

    @Override
    public boolean validateUIValue(Notifiable notifiable) {
        if( StringUtils.stripToNull(_slugField.getText()) == null) {
            notifiable.sendNotification(Message.error(TextSources.create("Slug is required.")));
            return false;
        } else if(ProfessorProjDAO.getInstance().validateUniqueSlug(_slugField.getText(), getValue())) {
            return true;
        } else {
            notifiable.sendNotification(Message.error(TextSources.create("Slug must be unique.")));
            return false;
        }
    }
}
