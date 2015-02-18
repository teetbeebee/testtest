-- 删除表
drop table SYS_PERMIT;
drop table SYS_ROLE;
drop table SYS_ROLE_PERMIT;
drop table SYS_USER;
drop table USER_ROLE;
drop table ORGAN;
drop table DICTIONARY;


drop table VPNUSER;
drop table VPNSERVER;

-- 创建表
create table SYS_PERMIT (
   PERMIT_ID            varchar(100)         not null,
   PERMIT_NAME          varchar(100)         null,
   NOTE                 varchar(500)         null,
   constraint PK_SYS_PERMIT primary key  (PERMIT_ID)
);
create table SYS_ROLE (
   ROLE_ID              varchar(20)          not null,
   ROLE_NAME            varchar(100)         not null,
   NOTE                 varchar(500)         null,
   constraint PK_SYS_ROLE primary key  (ROLE_ID)
);
create table SYS_ROLE_PERMIT (
   PERMIT_ID            varchar(100)         not null,
   ROLE_ID              varchar(20)          not null,
   constraint PK_SYS_ROLE_PERMIT primary key  (PERMIT_ID, ROLE_ID)
);
create table SYS_USER (
   USER_ID              varchar(20)          not null,
   ORGAN_ID             varchar(10)          null,
   USER_NAME            varchar(100)         null,
   USER_ORGAN_ID        varchar(10)          null,
   PASSWORD             varchar(32)          null,
   STATE                int                  null,
   IS_SYS               int                  null,
   NOTE                 varchar(500)         null,
   SESSION_ID			varchar(100)         null,
   OPERATE_TIME			TIMESTAMP	         null,
   QQ					varchar(20)	         null,
   EMAIL				varchar(64)			null,
   constraint PK_SYS_USER primary key  (USER_ID)
);
create table USER_ROLE (
   USER_ID              varchar(20)          not null,
   ROLE_ID              varchar(20)          not null,
   NOTE                 varchar(500)         null,
   constraint PK_USER_ROLE primary key  (USER_ID, ROLE_ID)
);
create table ORGAN (
   ORGAN_ID             varchar(10)          not null,
   POI_ID               varchar(128)         null,
   ORGAN_NAME           varchar(100)         null,
   PARENT_ID            varchar(20)          null,
   ORGAN_LEVEL          varchar(20)          null,
   OFFICER_NUM          int                  null,
   SOLDIER_NUM          int                  null,
   SORT_ORDER           int                  null,
   ADDRESS              varchar(100)         null,
   TELE_NUMS            varchar(100)         null,
   HOMEPAGE_URL         varchar(100)         null,
   NOTE                 varchar(500)         null,
   constraint PK_ORGAN primary key  (ORGAN_ID)
);
CREATE TABLE DICTIONARY(
	DIC_ID INTEGER NOT NULL,
	TABLE_NAME VARCHAR(100) null,
	COL_NAME VARCHAR(50) null,
	COL_VALUE VARCHAR(500) null,
	INFO VARCHAR(500) null,
	PRIMARY KEY(DIC_ID)
);


CREATE TABLE VPNUSER (                     
           USER_ID varchar(50) NOT NULL,            
           USER_NAME varchar(50) NOT NULL,          
           PASSWORD varchar(50) default NULL,       
           EMAIL varchar(50) default NULL,          
           QQ varchar(20) default NULL,             
           REGTIME timestamp,     
           EXPIRETIME timestamp,  
           SESSION_ID varchar(50) default NULL,     
           USER_TYPE int default NULL,          
           STATE int default NULL,  -- 0未激活，1已激活，-1被禁用
           VPN_SERVER_ID varchar(50) default NULL,  
           ACCOUNT double,  -- ACCOUNT NUMBER(16,2),
           PRIMARY KEY  (USER_ID),		
           UNIQUE KEY USER_NAME (USER_NAME)       
         )

CREATE TABLE VPNSERVER (                                                               
           VPNSERVER_ID VARCHAR(50) NOT NULL,
           IP VARCHAR(50)  NULL,
           VPN_TYPE VARCHAR(50)  NULL,
           EXPIRETIME TIMESTAMP,
           PRIMARY KEY  (VPNSERVER_ID)
         );

-- 基础模块相关结束


-- cms开始
CREATE TABLE CATEGORY(
	ID VARCHAR(64) NOT NULL,
	TITLE VARCHAR(64),
	CONTENT VARCHAR(4000),
	CREATETIME TIMESTAMP(3),
	AUTHOR VARCHAR(32),
	
)

