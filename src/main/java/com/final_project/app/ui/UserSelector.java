package com.final_project.app.ui;

import com.final_project.app.model.ProfessorProjMember;
import com.google.common.base.Supplier;

import net.proteusframework.cms.dao.CmsFrontendDAO;
import net.proteusframework.core.locale.LocalizedText;
import net.proteusframework.ui.column.AbstractDataColumn;
import net.proteusframework.ui.column.FixedValueColumn;
import net.proteusframework.ui.column.PropertyColumn;
import net.proteusframework.ui.miwt.History;
import net.proteusframework.ui.miwt.component.Container;
import net.proteusframework.ui.miwt.component.PushButton;
import net.proteusframework.ui.miwt.event.ActionEvent;
import net.proteusframework.ui.miwt.event.ActionListener;
import net.proteusframework.ui.miwt.util.CommonActions;
import net.proteusframework.ui.search.FixedSearchConstraint;
import net.proteusframework.ui.search.PropertyConstraint;
import net.proteusframework.ui.search.QLBuilder;
import net.proteusframework.ui.search.QLBuilderImpl;
import net.proteusframework.ui.search.SearchModelImpl;
import net.proteusframework.ui.search.SearchResultColumnImpl;
import net.proteusframework.ui.search.SearchSupplierImpl;
import net.proteusframework.ui.search.SearchUIImpl;
import net.proteusframework.ui.search.SimpleConstraint;
import net.proteusframework.users.model.Principal;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/8/14 10:24 AM
 */
public class UserSelector extends Container
{
    private History _history;

    private SearchUIImpl _searchUI;

    public UserSelector(History history) { _history = history; }

    @Override
    public void init() {
        super.init();

        Supplier<QLBuilder> builderSupplier = new Supplier<QLBuilder>()
        {
            @Override
            public QLBuilder get()
            {
                return new QLBuilderImpl(Principal.class, "p");
            }
        };

        SearchSupplierImpl supplier = new SearchSupplierImpl();
        supplier.setBuilderSupplier(builderSupplier);

        SearchModelImpl searchModel = new SearchModelImpl();
        searchModel.getResultColumns().add(
            new SearchResultColumnImpl().withTableColumn(new PropertyColumn(ProfessorProjMember.class, "contact.preferredEmailAddress")
                .withColumnName(new LocalizedText("Email"))));
        searchModel.getResultColumns().add(
            new SearchResultColumnImpl().withTableColumn(new PropertyColumn(ProfessorProjMember.class, "contact.name.first")
                .withColumnName(new LocalizedText("First Name"))));
        searchModel.getResultColumns().add(
            new SearchResultColumnImpl().withTableColumn(new PropertyColumn(ProfessorProjMember.class, "contact.name.last")
                .withColumnName(new LocalizedText("Last Name"))));
        searchModel.getConstraints().add(
            new SimpleConstraint().withLabel(new LocalizedText("Email")).withProperty("contact.emailAddresses.email").withOperator(
                PropertyConstraint.Operator.like));
        searchModel.getConstraints().add(
            new SimpleConstraint().withLabel(new LocalizedText("First Name")).withProperty("contact.name.first").withOperator(
                PropertyConstraint.Operator.like));
        searchModel.getConstraints().add(
            new SimpleConstraint().withLabel(new LocalizedText("Last Name")).withProperty("contact.name.last").withOperator(
                PropertyConstraint.Operator.like));

        AbstractDataColumn actionsColumn = new FixedValueColumn().withColumnName(new LocalizedText("Actions"));
        PushButton selectButton = CommonActions.SELECT.push();
        selectButton.addActionListener((ev) -> {
                UserSelector.this.fire(new ActionEvent(_searchUI.getLeadSelection(), UserSelector.this, null));
        });
        searchModel.getResultColumns().add(
            new SearchResultColumnImpl().withTableColumn(actionsColumn).withTableCellRenderer(
                Container.of("actions row_actions", selectButton)));
        supplier.setSearchModel(searchModel);
        SearchUIImpl.Options options = new SearchUIImpl.Options(getClass().getName());
        options.getFixedConstraints().add(new FixedSearchConstraint("authenticationDomains", PropertyConstraint.Operator.in,
            CmsFrontendDAO.getInstance().getOperationalSite().getDomain()));
        options.addSearchSupplier(supplier);
        options.setHistory(_history);
        _searchUI = new SearchUIImpl(options);
        add(_searchUI);
    }
}
