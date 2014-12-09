package com.final_project.app.ui;

import net.proteusframework.core.locale.TextSource;
import net.proteusframework.core.locale.TextSources;
import net.proteusframework.ui.miwt.component.Checkbox;
import net.proteusframework.ui.miwt.component.Component;
import net.proteusframework.ui.search.AbstractPropertyConstraint;
import net.proteusframework.ui.search.QLBuilder;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/8/14 10:18 AM
 */
public class ShowExpiredConstraint extends AbstractPropertyConstraint
{
    public void restoreValueForPersistence(Component constraintComponent, byte[] encodedValue){}

    @Override
    public void reset(Component constraintComponent) { ((Checkbox) constraintComponent).setSelected(false); }

    @Override
    public TextSource getLabel() { return TextSources.create("Show expired members"); }

    @Override
    public Component getConstraintComponent() { return new Checkbox(); }

    @Override
    public byte[] encodeValueForPersistence(Component constraintComponent) { return null; }

    @Override
    public void addCriteria(QLBuilder builder, Component constraintComponent) {
        if(!((Checkbox) constraintComponent).isSelected()) {
            builder.appendCriteria("endDate IS NULL");
        }
    }
}
