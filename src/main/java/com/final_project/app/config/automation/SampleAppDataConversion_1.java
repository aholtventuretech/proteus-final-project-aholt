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
 * @since 12/6/14 11:38 PM
 */
@Configuration
@Lazy
public class SampleAppDataConversion_1
{
    private static final String IDENTIFIER = "Sample-app";

    /**
     * TODO: Write description of schema update
     * @return Bean.
     */
    @TaskQualifier(TaskQualifier.Type.data_conversion)
    @Bean
    public DataConversion SampleAppDataConversion()
    {
        List<SQLStatement> ddl = new ArrayList<>();
        ddl.add(new SQLStatement("create table GroupMember (id int8 not null, createtime timestamp, lastmodtime timestamp, enddate timestamp, startdate timestamp, membertype varchar(255), createuser_id int8, lastmoduser_id int8, principal_id int8, profile_id int8, primary key (id))",null));
        ddl.add(new SQLStatement("create table GroupProfile (id int8 not null, name varchar(255), slug varchar(255), parent_id int8, primary key (id))",null));
        ddl.add(new SQLStatement("create table MembershipProcessorStatus (id int8 not null, lastprocessedtime timestamp, processorname varchar(255), principal_id int8, primary key (id))",null));
        ddl.add(new SQLStatement("create table ProjectMember (id int8 not null, createtime timestamp, lastmodtime timestamp, enddate timestamp, startdate timestamp, membertype varchar(255), createuser_id int8, lastmoduser_id int8, principal_id int8, profile_id int8, primary key (id))",null));
        ddl.add(new SQLStatement("create table ProjectProfile (id int8 not null, name varchar(255), slug varchar(255), parent_id int8, primary key (id))",null));
        ddl.add(new SQLStatement("create table SampleApplicationMember (id int8 not null, createtime timestamp, lastmodtime timestamp, enddate timestamp, startdate timestamp, membertype varchar(255), createuser_id int8, lastmoduser_id int8, principal_id int8, profile_id int8, primary key (id))",null));
        ddl.add(new SQLStatement("create table SampleApplicationProfile (id int8 not null, name varchar(255), slug varchar(255), primary key (id))",null));
        ddl.add(new SQLStatement("create table SamplePrincipalInfo (principal_id int8 not null, favoritecolor varchar(255), primary key (principal_id))",null));
        ddl.add(new SQLStatement("create table TestEntity (id int8 not null, property1 varchar(255), property3 int8, property2_id int8, primary key (id))",null));
        ddl.add(new SQLStatement("alter table GroupProfile add constraint UK_3p2aps5bp1gfc8jo7rv9cs6l6  unique (slug)",null));
        ddl.add(new SQLStatement("alter table MembershipProcessorStatus add constraint UK_70xj9uofnggndgcosrw2d39oj  unique (principal_id, processorname)",null));
        ddl.add(new SQLStatement("alter table ProjectProfile add constraint UK_6eovekx61xcmshpmjceqaf74o  unique (slug)",null));
        ddl.add(new SQLStatement("alter table GroupMember add constraint FK_c9e4b7mg5n8jph0dii4bhed00 foreign key (createuser_id) references Role",null));
        ddl.add(new SQLStatement("alter table GroupMember add constraint FK_nvlc1b21d7qon7lx5u50o6act foreign key (lastmoduser_id) references Role",null));
        ddl.add(new SQLStatement("alter table GroupMember add constraint FK_c19tsebugxc88y0v2htha1xso foreign key (principal_id) references Role",null));
        ddl.add(new SQLStatement("alter table GroupMember add constraint FK_f7qsd4nrdx3b1yu4pbg5ek9as foreign key (profile_id) references GroupProfile",null));
        ddl.add(new SQLStatement("alter table GroupProfile add constraint FK_5a9cek9yjo8mkjopbclvg1fyp foreign key (parent_id) references SampleApplicationProfile",null));
        ddl.add(new SQLStatement("alter table MembershipProcessorStatus add constraint FK_ijpip1jc6a4qb7yk0ajuj8ccg foreign key (principal_id) references Role on delete cascade",null));
        ddl.add(new SQLStatement("alter table ProjectMember add constraint FK_prfu1571o36ytg4xnbaj9kp8c foreign key (createuser_id) references Role",null));
        ddl.add(new SQLStatement("alter table ProjectMember add constraint FK_ivedbdqhyl568avatgc92tjj7 foreign key (lastmoduser_id) references Role",null));
        ddl.add(new SQLStatement("alter table ProjectMember add constraint FK_n5te81gsmdy3mjuy3f3er5s9i foreign key (principal_id) references Role",null));
        ddl.add(new SQLStatement("alter table ProjectMember add constraint FK_4ek440h8yy3706h14s5q9w1c1 foreign key (profile_id) references ProjectProfile",null));
        ddl.add(new SQLStatement("alter table ProjectProfile add constraint FK_hcc76obceyep67dcd75xwe67n foreign key (parent_id) references GroupProfile",null));
        ddl.add(new SQLStatement("alter table SampleApplicationMember add constraint FK_q99y6vksjb7yd4suqxf5fadrb foreign key (createuser_id) references Role",null));
        ddl.add(new SQLStatement("alter table SampleApplicationMember add constraint FK_bk1p0gt8rtg0es4vpuyteqhlg foreign key (lastmoduser_id) references Role",null));
        ddl.add(new SQLStatement("alter table SampleApplicationMember add constraint FK_2jpjc3r4ccr1406aiwleb251t foreign key (principal_id) references Role",null));
        ddl.add(new SQLStatement("alter table SampleApplicationMember add constraint FK_1wgnl1c4ch79nr4huvfaqb0xd foreign key (profile_id) references SampleApplicationProfile",null));
        ddl.add(new SQLStatement("alter table TestEntity add constraint FK_qu40u5oh1x5xait4mykbej41l foreign key (property2_id) references Role",null));
        ddl.add(new SQLStatement("create sequence groupmember_id_seq",null));
        ddl.add(new SQLStatement("create sequence groupprofile_id_seq",null));
        ddl.add(new SQLStatement("create sequence membershipprocessorstatus_id_seq",null));
        ddl.add(new SQLStatement("create sequence projectmember_id_seq",null));
        ddl.add(new SQLStatement("create sequence projectprofile_id_seq",null));
        ddl.add(new SQLStatement("create sequence sampleapplicationmember_id_seq",null));
        ddl.add(new SQLStatement("create sequence sampleapplicationprofile_id_seq",null));
        ddl.add(new SQLStatement("create sequence test_entity_id_seq",null));
        ddl.add(new SQLStatement("ALTER TABLE \"reportrequest2\" ADD CONSTRAINT fk_7ehpmrp3md1wttufwlpk9vbc9 FOREIGN KEY" +
            "(createuser_id) REFERENCES \"role\" ON DELETE SET NULL",null));
        ddl.add(new SQLStatement("ALTER TABLE \"reportrequest2\" ADD CONSTRAINT fk_2cae90b3sgpnup4y41wgsbrn4 FOREIGN KEY "
            + "(lastmoduser_id) REFERENCES \"role\" ON DELETE SET NULL",null));
//        ddl.add(new SQLStatement("ALTER TABLE \"reportrequest2_recipients\" ADD CONSTRAINT fk_oyed1cu67sb2b0bjtu2n6gn7a FOREIGN "
//            + "KEY (reportrequest2_hibernateid) REFERENCES \"reportrequest2\" ON DELETE CASCADE",null));
        ddl.add(new SQLStatement("ALTER TABLE \"reportrequest2\" ADD CONSTRAINT fk_nue2bq3nd9y2i6ts1w9x9064g FOREIGN KEY "
            + "(reportdirectory_id) REFERENCES \"filesystementity\" ON DELETE CASCADE",null));
        ddl.add(new SQLStatement("ALTER TABLE \"reportrequest2\" ADD CONSTRAINT fk_g6grr6bicbu7vrx3mk0fb889r FOREIGN KEY "
            + "(reportfileid) REFERENCES \"filesystementity\" ON DELETE CASCADE",null));
        ddl.add(new SQLStatement("ALTER TABLE \"emailtemplate\" ADD CONSTRAINT fk_15c3p8bub3on7xr5qy7vgoti FOREIGN KEY "
            + "(emailconfig_id) REFERENCES \"emailconfig\" ON DELETE SET NULL",null));
//        ddl.add(new SQLStatement("ALTER TABLE \"credentialpolicycollection\" ADD CONSTRAINT fk_v5lycuiyy7vdvcg6057oyts1 FOREIGN "
//            + "KEY (domain_id) REFERENCES \"authenticationdomain\" ON DELETE CASCADE",null));
//        ddl.add(new SQLStatement("ALTER TABLE \"extravalue_choiceextrachoice\" ADD CONSTRAINT fk_s97xoqg4hwitwt3ybhu6ynor7 "
//            + "FOREIGN KEY (extravalue_id) REFERENCES \"extravalue\" ON DELETE CASCADE",null));
//        ddl.add(new SQLStatement("ALTER TABLE \"extravalue_choicetextvalue\" ADD CONSTRAINT fk_aexguwq2ylp4sqgqardpl4wla FOREIGN "
//            + "KEY (extravalue_id) REFERENCES \"extravalue\" ON DELETE CASCADE",null));
//        ddl.add(new SQLStatement("ALTER TABLE \"formprocesseditsession\" ADD CONSTRAINT fk_gk2owixptbegpov88eye8rrwl FOREIGN KEY "
//            + "(formprocess_id) REFERENCES \"formprocess\" ON DELETE CASCADE",null));
        ddl.add(new SQLStatement("ALTER TABLE \"role_role\" ADD CONSTRAINT fk_scjhdc8dkdytasm7holic56s8 FOREIGN KEY (children_id) "
            + "REFERENCES \"role\" ON DELETE CASCADE",null));
        ddl.add(new SQLStatement("ALTER TABLE \"role_role\" ADD CONSTRAINT fk_kfj4p9y477irove9d3lqh9kcg FOREIGN KEY (role_id) "
            + "REFERENCES \"role\" ON DELETE CASCADE",null));
        return SQLDataConversion.createSchemaUpdate(IDENTIFIER, "sample application data setup", 1, false, ddl);
    }
}
