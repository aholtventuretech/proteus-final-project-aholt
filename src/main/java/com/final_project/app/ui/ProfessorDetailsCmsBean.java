package com.final_project.app.ui;

import freemarker.template.Configuration;

import com.final_project.app.model.ProfessorProjDAO;
import com.final_project.app.model.ProfessorProjProfile;
import com.final_project.app.model.SlugNotPresentException;
import org.apache.log4j.Logger;

import javax.annotation.Nullable;

import net.proteusframework.cms.PageElement;
import net.proteusframework.cms.PageElementModelConfiguration;
import net.proteusframework.cms.category.Categorized;
import net.proteusframework.cms.category.CmsCategory;
import net.proteusframework.cms.component.AbstractContentElement;
import net.proteusframework.cms.component.generator.Generator;
import net.proteusframework.cms.component.generator.GeneratorImpl;
import net.proteusframework.cms.controller.CmsRequest;
import net.proteusframework.cms.controller.CmsResponse;
import net.proteusframework.cms.controller.RenderChain;
import net.proteusframework.core.locale.annotation.I18N;
import net.proteusframework.core.locale.annotation.I18NFile;
import net.proteusframework.core.locale.annotation.L10N;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/8/14 2:23 PM
 */
@I18NFile(
    symbolPrefix = "app.ui.ProfessorDetailsCmsBean",
    i18n = {
        @I18N(symbol = "Component Name", l10n = @L10N("ProfessorDetailsCmsBean"))
    }
)
@Categorized(category = CmsCategory.WebContent)
@PageElementModelConfiguration(name = ProfessorDetailsCmsBeanLOK.COMPONENT_NAME_SYMBOL)
public class ProfessorDetailsCmsBean extends AbstractContentElement
{
    private Logger _logger = Logger.getLogger(ProfessorDetailsCmsBean.class);

    @Override
    public Generator<? extends PageElement> getGenerator(CmsRequest<? extends PageElement> request)
    {
        return new ProfessorDetailsGenerator();
    }

    class ProfessorDetailsGenerator extends GeneratorImpl<ProfessorDetailsCmsBean> {

        private String _slug;

        public void render(CmsRequest<ProfessorDetailsCmsBean> request, CmsResponse response, RenderChain chain) {
            _slug = request.getPathInfo().replace("/","");

            try{
                ProfessorProjProfile profile = ProfessorProjDAO.getInstance().getProfileForSlug(_slug);
                response.getContentWriter().append(getProfileView(profile));
            }catch(SlugNotPresentException ex) {
                _logger.debug(ex.getMessage(), ex);
                response.getContentWriter().append("<div>" + ex.getMessage() + "</div>");
                response.getContentWriter().append(getProfileView_FacultyLink());
            }
        }

        public String getProfileView(ProfessorProjProfile profile) {
            StringBuilder sb = new StringBuilder();
            sb.append("<div class='profile_view container'>");
                sb.append(getProfileView_Name(profile));
                sb.append(getProfileView_Slug(profile));
                sb.append(getProfileView_DateJoined(profile));
                sb.append(getProfileView_ProfessorType(profile));
                sb.append(getProfileView_AreaOfResearch(profile));
                sb.append(getProfileView_OnSabbatical(profile));
                sb.append(getProfileView_FacultyLink());
            sb.append("</div>");
            return sb.toString();
        }

        public String getProfileView_Name(ProfessorProjProfile profile) {
            StringBuilder sb = new StringBuilder();
            sb.append("<div class='profile_view segment name'>");
                sb.append("<label for='profile_view_name'>Name</label>");
                sb.append("<span id='profile_view_name'>" + profile.getName() + "</span>");
            sb.append("</div>");
            return sb.toString();
        }

        public String getProfileView_Slug(ProfessorProjProfile profile) {
            StringBuilder sb = new StringBuilder();
            sb.append("<div class='profile_view segment slug'>");
                sb.append("<label for='profile_view_slug'>Slug</label>");
                sb.append("<span id='profile_view_slug'>" + profile.getSlug() + "</span>");
            sb.append("</div>");
            return sb.toString();
        }

        public String getProfileView_DateJoined(ProfessorProjProfile profile) {
            StringBuilder sb = new StringBuilder();
            sb.append("<div class='profile_view segment dateJoined'>");
                sb.append("<label for='profile_view_dateJoined'>Date Joined</label>");
                sb.append("<span id='profile_view_dateJoined'>" + profile.getDateJoined().toString() + "</span>");
            sb.append("</div>");
            return sb.toString();
        }

        public String getProfileView_ProfessorType(ProfessorProjProfile profile) {
            StringBuilder sb = new StringBuilder();
            sb.append("<div class='profile_view segment professorType'>");
                sb.append("<label for='profile_view_professorType'>Professor Type</label>");
                sb.append("<span id='profile_view_professorType'>" + profile.getProfessorType() + "</span>");
            sb.append("</div>");
            return sb.toString();
        }

        public String getProfileView_AreaOfResearch(ProfessorProjProfile profile) {
            StringBuilder sb = new StringBuilder();
            sb.append("<div class='profile_view segment areaOfResearch'>");
                sb.append("<label for='profile_view_areaOfResearch'>Area of Research</label>");
                sb.append("<span id='profile_view_areaOfResearch'>" + profile.getAreaOfResearch() + "</span>");
            sb.append("</div>");
            return sb.toString();
        }

        public String getProfileView_OnSabbatical(ProfessorProjProfile profile) {
            StringBuilder sb = new StringBuilder();
            sb.append("<div class='profile_view segment onSabbatical'>");
                sb.append("<label for='profile_view_onSabbatical'>On Sabbatical</label>");
                sb.append("<span id='profile_view_onSabbatical'>" + profile.getOnSabbatical() + "</span>");
            sb.append("</div>");
            return sb.toString();
        }

        public String getProfileView_FacultyLink() {
            StringBuilder sb = new StringBuilder();
            sb.append("<div class='profile_view segment facultyLink'>");
                sb.append("<a id='profile_view_facultyLink' href='/faculty'>Faculty</span>");
            sb.append("</div>");
            return sb.toString();
        }
    }
}