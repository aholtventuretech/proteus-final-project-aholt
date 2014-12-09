package com.final_project.app.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.i2rd.labs.profile.AbstractPrincipalInfo;

import net.proteusframework.users.model.Principal;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/7/14 11:35 AM
 */
@Entity
public class ProfessorsPrincipalInfo extends AbstractPrincipalInfo
{
    private static final long serialVersionUID = 7881332314715490581L;

    public ProfessorsPrincipalInfo() {}

    public ProfessorsPrincipalInfo(Principal principal) { setPrincipal(principal); }

    @Id
    @GenericGenerator(name = "professorsprincipalinfo_id_gen", strategy = "foreign",
        parameters = @Parameter(name = "property", value = "principal"))
    @GeneratedValue(generator = "professorsprincipalinfo_id_gen")
    @Column(name = "principal_id")
    public Long getId() {
        return super.getId();
    }
}
