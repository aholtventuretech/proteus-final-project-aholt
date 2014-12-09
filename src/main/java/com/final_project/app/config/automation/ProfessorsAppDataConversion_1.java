package com.final_project.app.config.automation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

import net.proteusframework.core.automation.DataConversion;
import net.proteusframework.core.automation.SQLDataConversion;
import net.proteusframework.core.automation.SQLStatement;
import net.proteusframework.core.automation.TaskQualifier;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/8/14 9:24 AM
 */
@Configuration
@Lazy
public class ProfessorsAppDataConversion_1
{
    private static final String IDENTIFIER = "Professors-App";

    private static final String DESC = "professors application data setup";

    /**
     * TODO: Write description of schema update
     * @return Bean.
     */
    @TaskQualifier(TaskQualifier.Type.data_conversion)
    @Bean
    public DataConversion ProfessorsAppDC3()
    {
        List<SQLStatement> ddl = new ArrayList<>();
        ddl.add(new SQLStatement("alter table ProfessorProjProfile add column areaofresearch varchar(255)",null));
        ddl.add(new SQLStatement("alter table ProfessorProjProfile add column onsabbatical boolean not null default false",null));
//        ddl.add(new SQLStatement("ALTER TABLE "reportrequest2_recipients" ADD CONSTRAINT fk_oyed1cu67sb2b0bjtu2n6gn7a FOREIGN KEY (reportrequest2_hibernateid) REFERENCES "reportrequest2" ON DELETE CASCADE",null));
//        ddl.add(new SQLStatement("ALTER TABLE "credentialpolicycollection" ADD CONSTRAINT fk_v5lycuiyy7vdvcg6057oyts1 FOREIGN KEY (domain_id) REFERENCES "authenticationdomain" ON DELETE CASCADE",null));
//        ddl.add(new SQLStatement("ALTER TABLE "extravalue_choiceextrachoice" ADD CONSTRAINT fk_s97xoqg4hwitwt3ybhu6ynor7 FOREIGN KEY (extravalue_id) REFERENCES "extravalue" ON DELETE CASCADE",null));
//        ddl.add(new SQLStatement("ALTER TABLE "extravalue_choicetextvalue" ADD CONSTRAINT fk_aexguwq2ylp4sqgqardpl4wla FOREIGN KEY (extravalue_id) REFERENCES "extravalue" ON DELETE CASCADE",null));
//        ddl.add(new SQLStatement("ALTER TABLE "formprocesseditsession" ADD CONSTRAINT fk_gk2owixptbegpov88eye8rrwl FOREIGN KEY (formprocess_id) REFERENCES "formprocess" ON DELETE CASCADE",null));
        return SQLDataConversion.createSchemaUpdate(IDENTIFIER, DESC, 3, false, ddl);
    }

    /**
     * TODO: Write description of schema update
     * @return Bean.
     */
    @TaskQualifier(TaskQualifier.Type.data_conversion)
    @Bean
    public DataConversion ProfessorsAppDC2()
    {
        List<SQLStatement> ddl = new ArrayList<>();
        ddl.add(new SQLStatement("alter table ProfessorProjProfile add column datejoined timestamp",null));
        ddl.add(new SQLStatement("alter table ProfessorProjProfile add column professortype varchar(255)",null));
//        ddl.add(new SQLStatement("ALTER TABLE "reportrequest2_recipients" ADD CONSTRAINT fk_oyed1cu67sb2b0bjtu2n6gn7a FOREIGN KEY (reportrequest2_hibernateid) REFERENCES "reportrequest2" ON DELETE CASCADE",null));
//        ddl.add(new SQLStatement("ALTER TABLE "credentialpolicycollection" ADD CONSTRAINT fk_v5lycuiyy7vdvcg6057oyts1 FOREIGN KEY (domain_id) REFERENCES "authenticationdomain" ON DELETE CASCADE",null));
//        ddl.add(new SQLStatement("ALTER TABLE "extravalue_choiceextrachoice" ADD CONSTRAINT fk_s97xoqg4hwitwt3ybhu6ynor7 FOREIGN KEY (extravalue_id) REFERENCES "extravalue" ON DELETE CASCADE",null));
//        ddl.add(new SQLStatement("ALTER TABLE "extravalue_choicetextvalue" ADD CONSTRAINT fk_aexguwq2ylp4sqgqardpl4wla FOREIGN KEY (extravalue_id) REFERENCES "extravalue" ON DELETE CASCADE",null));
//        ddl.add(new SQLStatement("ALTER TABLE "formprocesseditsession" ADD CONSTRAINT fk_gk2owixptbegpov88eye8rrwl FOREIGN KEY (formprocess_id) REFERENCES "formprocess" ON DELETE CASCADE",null));
        return SQLDataConversion.createSchemaUpdate(IDENTIFIER, DESC, 2, false, ddl);
    }

    /**
     * TODO: Write description of schema update
     * @return Bean.
     */
    @TaskQualifier(TaskQualifier.Type.data_conversion)
    @Bean
    public DataConversion ProfessorsAppDC1()
    {
        List<SQLStatement> ddl = new ArrayList<>();
        ddl.add(new SQLStatement("create table ProfessorProjMember (id int8 not null, createtime timestamp, lastmodtime timestamp, enddate timestamp, startdate timestamp, membertype varchar(255), createuser_id int8, lastmoduser_id int8, principal_id int8, profile_id int8, primary key (id))",null));
        ddl.add(new SQLStatement("create table ProfessorProjProfile (id int8 not null, name varchar(255), slug varchar(255), parent_id int8, primary key (id))",null));
        ddl.add(new SQLStatement("create table ProfessorsApplicationMember (id int8 not null, createtime timestamp, lastmodtime timestamp, enddate timestamp, startdate timestamp, membertype varchar(255), createuser_id int8, lastmoduser_id int8, principal_id int8, profile_id int8, primary key (id))",null));
        ddl.add(new SQLStatement("create table ProfessorsApplicationProfile (id int8 not null, name varchar(255), slug varchar(255), primary key (id))",null));
        ddl.add(new SQLStatement("create table ProfessorsGroupMember (id int8 not null, createtime timestamp, lastmodtime timestamp, enddate timestamp, startdate timestamp, membertype varchar(255), createuser_id int8, lastmoduser_id int8, principal_id int8, profile_id int8, primary key (id))",null));
        ddl.add(new SQLStatement("create table ProfessorsGroupProfile (id int8 not null, name varchar(255), slug varchar(255), parent_id int8, primary key (id))",null));
        ddl.add(new SQLStatement("create table ProfessorsPrincipalInfo (principal_id int8 not null, primary key (principal_id))",null));
        ddl.add(new SQLStatement("alter table ProfessorProjProfile add constraint UK_njyh2vojjg69majlpr6k7o52e  unique (slug)",null));
        ddl.add(new SQLStatement("alter table ProfessorsGroupProfile add constraint UK_och7orhox55q1le1xoni8y0tc  unique (slug)",null));
        ddl.add(new SQLStatement("alter table ProfessorProjMember add constraint FK_m7pnk354oplk1xlwwrbk38ia6 foreign key (createuser_id) references Role",null));
        ddl.add(new SQLStatement("alter table ProfessorProjMember add constraint FK_is53ksi3vnpwufm0poj6npd2f foreign key (lastmoduser_id) references Role",null));
        ddl.add(new SQLStatement("alter table ProfessorProjMember add constraint FK_a5jft3atb1k25eo0vxn1lm8ya foreign key (principal_id) references Role",null));
        ddl.add(new SQLStatement("alter table ProfessorProjMember add constraint FK_2lrdxmye3hxe4jm95f6mdf2hk foreign key (profile_id) references ProfessorProjProfile",null));
        ddl.add(new SQLStatement("alter table ProfessorProjProfile add constraint FK_olalc2m1kssm2r8k1kmx4llp2 foreign key (parent_id) references ProfessorsGroupProfile",null));
        ddl.add(new SQLStatement("alter table ProfessorsApplicationMember add constraint FK_9enobgkuh5rkrowhl87l2da4a foreign key (createuser_id) references Role",null));
        ddl.add(new SQLStatement("alter table ProfessorsApplicationMember add constraint FK_b2ke8boats0haj6t1wcqeomi2 foreign key (lastmoduser_id) references Role",null));
        ddl.add(new SQLStatement("alter table ProfessorsApplicationMember add constraint FK_tfdwwnuah7il4up4b338l7wrw foreign key (principal_id) references Role",null));
        ddl.add(new SQLStatement("alter table ProfessorsApplicationMember add constraint FK_c91xt8ktaery5hxqly0xlry5 foreign key (profile_id) references ProfessorsApplicationProfile",null));
        ddl.add(new SQLStatement("alter table ProfessorsGroupMember add constraint FK_rdnwjsyu9qvveckxw0e3841fm foreign key (createuser_id) references Role",null));
        ddl.add(new SQLStatement("alter table ProfessorsGroupMember add constraint FK_4vyy1owc9q9duea3jqd8p9n5 foreign key (lastmoduser_id) references Role",null));
        ddl.add(new SQLStatement("alter table ProfessorsGroupMember add constraint FK_f2bq5xyri30p2cecseadq3sdv foreign key (principal_id) references Role",null));
        ddl.add(new SQLStatement("alter table ProfessorsGroupMember add constraint FK_44rnp4sc32gndx8gxdmrrpibe foreign key (profile_id) references ProfessorsGroupProfile",null));
        ddl.add(new SQLStatement("alter table ProfessorsGroupProfile add constraint FK_ir0oaenwo23jcn4km3qcdhdjh foreign key (parent_id) references ProfessorsApplicationProfile",null));
        ddl.add(new SQLStatement("create sequence professorprojmember_id_seq",null));
        ddl.add(new SQLStatement("create sequence professorprojprofile_id_seq",null));
        ddl.add(new SQLStatement("create sequence professorsapplicationmember_id_seq",null));
        ddl.add(new SQLStatement("create sequence professorsapplicationprofile_id_seq",null));
        ddl.add(new SQLStatement("create sequence professorsgroupmember_id_seq",null));
        ddl.add(new SQLStatement("create sequence professorsgroupprofile_id_seq",null));
//        ddl.add(new SQLStatement("ALTER TABLE \"reportrequest2_recipients\" ADD CONSTRAINT fk_oyed1cu67sb2b0bjtu2n6gn7a FOREIGN "
//            + "KEY (reportrequest2_hibernateid) REFERENCES \"reportrequest2\" ON DELETE CASCADE",null));
//        ddl.add(new SQLStatement("ALTER TABLE \"credentialpolicycollection\" ADD CONSTRAINT fk_v5lycuiyy7vdvcg6057oyts1 FOREIGN "
//            + "KEY (domain_id) REFERENCES \"authenticationdomain\" ON DELETE CASCADE",null));
//        ddl.add(new SQLStatement("ALTER TABLE \"extravalue_choiceextrachoice\" ADD CONSTRAINT fk_s97xoqg4hwitwt3ybhu6ynor7 "
//            + "FOREIGN KEY (extravalue_id) REFERENCES \"extravalue\" ON DELETE CASCADE",null));
//        ddl.add(new SQLStatement("ALTER TABLE \"extravalue_choicetextvalue\" ADD CONSTRAINT fk_aexguwq2ylp4sqgqardpl4wla FOREIGN "
//            + "KEY (extravalue_id) REFERENCES \"extravalue\" ON DELETE CASCADE",null));
//        ddl.add(new SQLStatement("ALTER TABLE \"formprocesseditsession\" ADD CONSTRAINT fk_gk2owixptbegpov88eye8rrwl FOREIGN KEY "
//            + "(formprocess_id) REFERENCES \"formprocess\" ON DELETE CASCADE",null));
        return SQLDataConversion.createSchemaUpdate(IDENTIFIER, DESC, 1, false, ddl);
    }
}
