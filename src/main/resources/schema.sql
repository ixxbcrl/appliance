drop table if exists APPLIANCE;

create table APPLIANCE(
    ID long not null AUTO_INCREMENT,
    APPLIANCE_ID varchar(30) not null,
    FACTORY_NO varchar(20) not null
);

create index `appliance_id_idx` on `appliance`(APPLIANCE_ID);