package com.final_project.app.ui;

import com.final_project.app.model.ProfessorProjDAO;
import com.final_project.app.model.ProfessorProjProfile;
import com.google.common.base.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.List;

import com.i2rd.cms.bean.MIWTBeanConfig;
import com.i2rd.cms.bean.MIWTStateEvent;
import com.i2rd.cms.miwt.SiteAwareMIWTApplication;

import net.proteusframework.core.hibernate.dao.EntityRetriever;
import net.proteusframework.core.locale.TextSources;
import net.proteusframework.ui.column.AbstractDataColumn;
import net.proteusframework.ui.column.FixedValueColumn;
import net.proteusframework.ui.column.PropertyColumn;
import net.proteusframework.ui.miwt.HistoryElement;
import net.proteusframework.ui.miwt.component.Checkbox;
import net.proteusframework.ui.miwt.component.Component;
import net.proteusframework.ui.miwt.component.Container;
import net.proteusframework.ui.miwt.component.PushButton;
import net.proteusframework.ui.miwt.component.Window;
import net.proteusframework.ui.miwt.component.composite.HistoryContainer;
import net.proteusframework.ui.miwt.component.composite.OptionDialog;
import net.proteusframework.ui.miwt.event.ActionListener;
import net.proteusframework.ui.miwt.util.CommonActions;
import net.proteusframework.ui.search.AbstractBulkAction;
import net.proteusframework.ui.search.PropertyConstraint;
import net.proteusframework.ui.search.QLBuilder;
import net.proteusframework.ui.search.SearchModelImpl;
import net.proteusframework.ui.search.SearchResultColumnImpl;
import net.proteusframework.ui.search.SearchSupplierImpl;
import net.proteusframework.ui.search.SearchUIAction;
import net.proteusframework.ui.search.SearchUIContext;
import net.proteusframework.ui.search.SearchUIImpl;
import net.proteusframework.ui.search.SimpleConstraint;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/8/14 9:36 AM
 */
@MIWTBeanConfig(value = ProfessorProjManagementApp.RESOURCE_NAME, displayName = "Professors App: Project Management",
    applicationClass = SiteAwareMIWTApplication.class, stateEvents = {
    @MIWTStateEvent(eventName = SiteAwareMIWTApplication.SAMA_ADD_COMPONENT, eventValue = ProfessorProjManagementApp.RESOURCE_NAME),
    @MIWTStateEvent(eventName = SiteAwareMIWTApplication.SAMA_RECREATE_ON_SITE_CHANGE, eventValue = "true")
})
@Scope("prototype")
public class ProfessorProjManagementApp extends Container
{
    /**Resource Name*/
    public final static String RESOURCE_NAME = "ProfessorProjManagementApp";

    /**DAO*/
    @Autowired
    private ProfessorProjDAO _projectManager;

    /**Search UI*/
    private SearchUIImpl _searchUI;

    public ProfessorProjManagementApp() {}

    /** {@inheritDoc} */
    @Override
    public void init() {
        super.init();

        addClassName("management professor");

        Supplier<QLBuilder> builderSupplier = () -> {
            return _projectManager.getProfileQLBuilder();
        };

        SearchSupplierImpl supplier = new SearchSupplierImpl();
        supplier.setBuilderSupplier(builderSupplier);

        /** Search Model Columns */
        SearchModelImpl searchModel = new SearchModelImpl();
        searchModel.getResultColumns().add(new SearchResultColumnImpl().withTableColumn(new PropertyColumn(ProfessorProjProfile
            .class, "id").withColumnName(TextSources.create("ID"))));
        searchModel.getResultColumns().add(new SearchResultColumnImpl().withTableColumn(new PropertyColumn(ProfessorProjProfile
            .class, "name").withColumnName(TextSources.create("Name"))));
        searchModel.getResultColumns().add(new SearchResultColumnImpl().withTableColumn(new PropertyColumn(ProfessorProjProfile
            .class, "slug").withColumnName(TextSources.create("Slug"))));
        searchModel.getResultColumns().add(new SearchResultColumnImpl().withTableColumn(new PropertyColumn(ProfessorProjProfile
            .class, "dateJoined").withColumnName(TextSources.create("Date Joined"))));
        searchModel.getResultColumns().add(new SearchResultColumnImpl().withTableColumn(new PropertyColumn(ProfessorProjProfile
            .class, "professorType").withColumnName(TextSources.create("Prof. Type"))));
        AbstractDataColumn onSabbaticalColumn = new PropertyColumn(ProfessorProjProfile.class, "onSabbatical").withColumnName
            (TextSources.create("On Sabbatical"));
        searchModel.getResultColumns().add(new SearchResultColumnImpl().withTableColumn(onSabbaticalColumn));

        /** Search Model Constraints */
        searchModel.getConstraints().add(new SimpleConstraint().withLabel(TextSources.create("ID")).withProperty("id")
            .withOperator(PropertyConstraint.Operator.eq));
        searchModel.getConstraints().add(new SimpleConstraint().withLabel(TextSources.create("Name")).withProperty("name")
            .withOperator(PropertyConstraint.Operator.like));
        searchModel.getConstraints().add(new SimpleConstraint().withLabel(TextSources.create("Slug")).withProperty("slug")
            .withOperator(PropertyConstraint.Operator.like));
        searchModel.getConstraints().add(new SimpleConstraint().withLabel(TextSources.create("Date Joined")).withProperty
            ("dateJoined").withOperator(PropertyConstraint.Operator.like));
        searchModel.getConstraints().add(new SimpleConstraint().withLabel(TextSources.create("Prof. Type")).withProperty
            ("professorType").withOperator(PropertyConstraint.Operator.like));
        searchModel.getConstraints().add(new SimpleConstraint().withLabel(TextSources.create("On Sabbatical")).withProperty
            ("onSabbatical").withOperator(PropertyConstraint.Operator.eq));

        AbstractDataColumn actionsColumn = new FixedValueColumn().withColumnName(TextSources.create("Actions"));

        PushButton openButton = CommonActions.OPEN.push();
        openButton.addActionListener((ev) -> { _edit(_searchUI.getLeadSelection()); });
        searchModel.getResultColumns().add(new SearchResultColumnImpl().withTableColumn(actionsColumn).withTableCellRenderer
            (Container.of("actions row_actions", openButton)));

        AbstractBulkAction deleteAction = new AbstractBulkAction()
        {
            @Override
            public void doAction(final SearchUIContext context)
            {
                final List<ProfessorProjProfile> profiles = (List<ProfessorProjProfile>) context.getSearchUI().getSelection();
                ActionListener okAction = (ev) -> {
                    EntityRetriever er = EntityRetriever.getInstance();
                    for(int i = 0; i < profiles.size(); i++) {
                        profiles.set(i, er.reattachIfNecessary(profiles.get(i)));
                    }
                    _projectManager.deleteProfiles(profiles);
                    context.getSearchUI().doAction(SearchUIAction.search);
                };
                final OptionDialog dlg = new OptionDialog(context.getSearchUI().getComponent(), TextSources.create("Delete "
                    + "selected professors? Cannot be undone."), new Window.Closer(okAction), new Window.Closer());
                dlg.setTitle(TextSources.create("Delete"));
                dlg.show();
            }
        };
        deleteAction.setName(TextSources.create("Delete professor"));
        searchModel.getBulkActions().add(deleteAction);

        supplier.setSearchModel(searchModel);

        SearchUIImpl.Options options = new SearchUIImpl.Options(getClass().getName());
        options.addSearchSupplier(supplier);
        options.addEntityAction(CommonActions.ADD.defaultAction().setActionListener((ev) -> {
            _edit(new ProfessorProjProfile());
        }));

        _searchUI = new SearchUIImpl(options);
        HistoryContainer historyContainer = new HistoryContainer();
        historyContainer.setDefaultComponent(_searchUI);
        add(historyContainer);
    }

    private void _edit(ProfessorProjProfile profile) {
        Component details = new ProfessorProjDetails(profile, _searchUI.getHistory());
        PushButton backButton = CommonActions.BACK.push();
        Container ui = Container.of("details_container", backButton, details);
        backButton.addActionListener(ui.new Closer());
        _searchUI.getHistory().add(new HistoryElement(ui));
    }
}
