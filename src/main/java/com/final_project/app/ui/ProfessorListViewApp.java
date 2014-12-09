package com.final_project.app.ui;

import com.final_project.app.model.ProfessorProjDAO;
import com.final_project.app.model.ProfessorProjProfile;
import com.google.common.base.Supplier;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.i2rd.cms.bean.MIWTBeanConfig;
import com.i2rd.cms.bean.MIWTStateEvent;
import com.i2rd.cms.bean.redirect.RedirectAction;
import com.i2rd.cms.miwt.SiteAwareMIWTApplication;
import com.i2rd.miwt.beans.component.composite.ViewChangeEvent;

import net.proteusframework.cms.dao.CmsFrontendDAO;
import net.proteusframework.core.locale.TextSources;
import net.proteusframework.internet.http.Link;
import net.proteusframework.ui.column.AbstractDataColumn;
import net.proteusframework.ui.column.FixedValueColumn;
import net.proteusframework.ui.column.PropertyColumn;
import net.proteusframework.ui.miwt.AbstractAction;
import net.proteusframework.ui.miwt.History;
import net.proteusframework.ui.miwt.component.Container;
import net.proteusframework.ui.miwt.component.Field;
import net.proteusframework.ui.miwt.component.PushButton;
import net.proteusframework.ui.miwt.component.Table;
import net.proteusframework.ui.miwt.component.URILink;
import net.proteusframework.ui.miwt.component.composite.HistoryContainer;
import net.proteusframework.ui.miwt.data.TableModel;
import net.proteusframework.ui.miwt.event.ActionEvent;
import net.proteusframework.ui.miwt.event.ApplicationEvent;
import net.proteusframework.ui.miwt.util.CommonActions;
import net.proteusframework.ui.search.FixedSearchConstraint;
import net.proteusframework.ui.search.PropertyConstraint;
import net.proteusframework.ui.search.QLBuilder;
import net.proteusframework.ui.search.SearchModelImpl;
import net.proteusframework.ui.search.SearchResultColumnImpl;
import net.proteusframework.ui.search.SearchSupplierImpl;
import net.proteusframework.ui.search.SearchUIImpl;
import net.proteusframework.ui.search.SimpleConstraint;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/8/14 4:57 PM
 */
@MIWTBeanConfig(value = ProfessorListViewApp.RESOURCE_NAME, displayName = "Professors App: Faculty List View", applicationClass =
    SiteAwareMIWTApplication.class, stateEvents = {
    @MIWTStateEvent(eventName = SiteAwareMIWTApplication.SAMA_ADD_COMPONENT, eventValue = ProfessorListViewApp.RESOURCE_NAME),
    @MIWTStateEvent(eventName = SiteAwareMIWTApplication.SAMA_RECREATE_ON_SITE_CHANGE, eventValue = "true")
})
@Scope("prototype")
public class ProfessorListViewApp extends Container
{
    private final static Logger _logger = Logger.getLogger(ProfessorListViewApp.class);

    public final static String RESOURCE_NAME = "ProfessorListViewApp";

    @Autowired
    private ProfessorProjDAO _projectManager;

    private ProfessorProjProfile _profile;

    private SearchUIImpl _searchUI;

    public ProfessorListViewApp() {}

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

        SearchResultColumnImpl idColumnImpl = new SearchResultColumnImpl();
        PropertyColumn idPropColumn = new PropertyColumn(ProfessorProjProfile.class, "id");

        SearchResultColumnImpl nameColumnImpl = new SearchResultColumnImpl();
        PropertyColumn namePropColumn = new PropertyColumn(ProfessorProjProfile.class , "name");

        SearchResultColumnImpl slugColumnImpl = new SearchResultColumnImpl();
        PropertyColumn slugPropColumn = new PropertyColumn(ProfessorProjProfile.class, "slug");

        SearchResultColumnImpl dateJoinedColumnImpl = new SearchResultColumnImpl();
        PropertyColumn dateJoinedPropColumn = new PropertyColumn(ProfessorProjProfile.class, "dateJoined");

        SearchResultColumnImpl professorTypeColumnImpl = new SearchResultColumnImpl();
        PropertyColumn professorTypePropColumn = new PropertyColumn(ProfessorProjProfile.class, "professorType");

        SearchResultColumnImpl onSabbaticalColumnImpl = new SearchResultColumnImpl();
        PropertyColumn onSabbaticalPropColumn  = new PropertyColumn(ProfessorProjProfile.class, "onSabbatical");

        URILink slugLink = new URILink();
        slugLink.addClassName("slug_link");
        Field slugField = new Field();
        slugField.setEnabled(false);
        slugField.addClassName("slug_field");

        searchModel.getResultColumns().add(idColumnImpl.withTableColumn(idPropColumn.withColumnName(TextSources.create("ID"))));
        searchModel.getResultColumns().add(nameColumnImpl.withTableColumn(namePropColumn.withColumnName(TextSources.create
            ("Name"))).withTableCellRenderer(slugLink));
        searchModel.getResultColumns().add(slugColumnImpl.withTableColumn(slugPropColumn.withColumnName(TextSources.create
            ("Slug"))).withTableCellRenderer(slugField));
        searchModel.getResultColumns().add(dateJoinedColumnImpl.withTableColumn(dateJoinedPropColumn.withColumnName(TextSources.create("Date Joined"))));
        searchModel.getResultColumns().add(professorTypeColumnImpl.withTableColumn(professorTypePropColumn.withColumnName(TextSources.create("Prof. Type"))));
        searchModel.getResultColumns().add(onSabbaticalColumnImpl.withTableColumn(onSabbaticalPropColumn.withColumnName(TextSources.create("On Sabbatical"))));

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

        supplier.setSearchModel(searchModel);

        SearchUIImpl.Options options = new SearchUIImpl.Options(getClass().getName());
        options.addSearchSupplier(supplier);

        _searchUI = new SearchUIImpl(options);

        HistoryContainer historyContainer = new HistoryContainer();
        historyContainer.setDefaultComponent(_searchUI);

        add(historyContainer);
    }
}
