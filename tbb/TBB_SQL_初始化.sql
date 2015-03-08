-- 清除sys_config
truncate table ORGAN;

INSERT INTO ORGAN(ORGAN_ID,ORGAN_NAME,ORGAN_LEVEL,PARENT_ID,OFFICER_NUM,SOLDIER_NUM,SORT_ORDER,ADDRESS,TELE_NUMS,HOMEPAGE_URL,NOTE) VALUES('21000000','辽宁总队',1,'0','0','0','0','','','','');


-- 清除sys_permit
truncate table sys_permit;	

insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.action.PAction@login','登录',null);

insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.action.IndexAction@view','首页',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.action.IndexAction@query','综合查询',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.action.IndexAction@left','左边菜单权限',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.IndexAction@view','系统管理首页',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysGrantAction@edit_role_permit','编辑角色权限',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysGrantAction@edit_role_permit_save','编辑保存角色权限',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysGrantAction@edit_user_role','编辑用户角色',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysGrantAction@edit_user_role_save','编辑保存用户角色',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysPermitAction@query','查询权限',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysPermitAction@view','查看权限',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysRoleAction@add','新增角色',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysRoleAction@add_save','新增保存角色',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysRoleAction@delete','删除角色',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysRoleAction@edit','编辑角色',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysRoleAction@edit_save','编辑保存角色',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysRoleAction@query','查询角色',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysRoleAction@view','查看角色',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysUserAction@add','新增用户',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysUserAction@add_save','新增保存用户',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysUserAction@delete','删除用户',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysUserAction@edit','编辑用户',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysUserAction@edit_my_pwd','编辑我的密码',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysUserAction@edit_my_pwd_save','编辑保存我的密码',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysUserAction@edit_pwd','编辑密码',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysUserAction@edit_pwd_save','编辑保存密码',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysUserAction@edit_save','编辑保存用户',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysUserAction@query','查询用户',null);	
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysUserAction@view','查看用户',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.sys.action.SysUserAction@registe','注册用户',null);

insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.OrganAction@query','查询机构',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.OrganAction@add','添加机构',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.OrganAction@add_save','保存机构',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.OrganAction@edit','编辑机构',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.OrganAction@edit_save','编辑保存机构',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.OrganAction@delete','删除机构',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.OrganAction@showOrganTree','机构树型',null);

insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.DictionaryAction@query','查询字典',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.DictionaryAction@add','添加字典',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.DictionaryAction@add_save','保存字典',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.DictionaryAction@edit','编辑字典',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.DictionaryAction@edit_save','编辑保存字典',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.DictionaryAction@delete','删除字典',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.DictionaryAction@view','查看字典',null);


insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.TBB_BASEDATAAction@query','查询TBB_BASEDATA',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.TBB_BASEDATAAction@add','添加TBB_BASEDATA',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.TBB_BASEDATAAction@add_save','保存TBB_BASEDATA',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.TBB_BASEDATAAction@edit','编辑TBB_BASEDATA',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.TBB_BASEDATAAction@edit_save','编辑保存TBB_BASEDATA',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.TBB_BASEDATAAction@delete','删除TBB_BASEDATA',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.TBB_BASEDATAAction@view','查看TBB_BASEDATA',null);

insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.TBB_PROJ_LISTAction@query','查询TBB_PROJ_LIST',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.TBB_PROJ_LISTAction@add','添加TBB_PROJ_LIST',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.TBB_PROJ_LISTAction@add_save','保存TBB_PROJ_LIST',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.TBB_PROJ_LISTAction@edit','编辑TBB_PROJ_LIST',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.TBB_PROJ_LISTAction@edit_save','编辑保存TBB_PROJ_LIST',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.TBB_PROJ_LISTAction@delete','删除TBB_PROJ_LIST',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.TBB_PROJ_LISTAction@view','查看TBB_PROJ_LIST',null);

insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.T1Action@query','查询T1',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.T1Action@add','添加T1',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.T1Action@add_save','保存T1',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.T1Action@edit','编辑T1',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.T1Action@edit_save','编辑保存T1',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.T1Action@delete','删除T1',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.T1Action@view','查看T1',null);

insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.RestrauntAction@query','查询Restraunt',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.RestrauntAction@add','添加Restraunt',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.RestrauntAction@add_save','保存Restraunt',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.RestrauntAction@edit','编辑Restraunt',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.RestrauntAction@edit_save','编辑保存Restraunt',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.RestrauntAction@delete','删除Restraunt',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.RestrauntAction@view','查看Restraunt',null);

--  sys_permit注册 -- 
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.vpn.action.VpnuserAction@query','查询Vpnuser',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.vpn.action.VpnuserAction@add','添加Vpnuser',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.vpn.action.VpnuserAction@add_save','保存Vpnuser',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.vpn.action.VpnuserAction@edit','编辑Vpnuser',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.vpn.action.VpnuserAction@edit_save','编辑保存Vpnuser',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.vpn.action.VpnuserAction@delete','删除Vpnuser',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.vpn.action.VpnuserAction@view','查看Vpnuser',null);


-- 清除sys_role
truncate table sys_role;	
insert into SYS_ROLE(ROLE_ID,ROLE_NAME,NOTE) values('r001','系统管理员','');	
insert into SYS_ROLE(ROLE_ID,ROLE_NAME,NOTE) values('r002','匿名用户','');

-- 清除sys_role_permit
truncate table sys_role_permit;			
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.action.IndexAction@view','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.action.IndexAction@query','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.action.IndexAction@left','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.IndexAction@view','r001');	

insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysGrantAction@edit_role_permit','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysGrantAction@edit_role_permit_save','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysGrantAction@edit_user_role','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysGrantAction@edit_user_role_save','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysPermitAction@query','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysPermitAction@view','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysRoleAction@add','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysRoleAction@add_save','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysRoleAction@delete','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysRoleAction@edit','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysRoleAction@edit_save','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysRoleAction@query','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysRoleAction@view','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysUserAction@add','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysUserAction@add_save','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysUserAction@delete','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysUserAction@edit','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysUserAction@edit_my_pwd','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysUserAction@edit_my_pwd_save','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysUserAction@edit_pwd','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysUserAction@edit_pwd_save','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysUserAction@edit_save','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysUserAction@query','r001');	
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.sys.action.SysUserAction@view','r001');

insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.OrganAction@query','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.OrganAction@add','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.OrganAction@add_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.OrganAction@edit','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.OrganAction@edit_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.OrganAction@delete','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.OrganAction@showOrganTree','r001');

insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.DictionaryAction@query','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.DictionaryAction@add','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.DictionaryAction@add_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.DictionaryAction@edit','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.DictionaryAction@edit_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.DictionaryAction@delete','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.DictionaryAction@view','r001');

insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.TBB_BASEDATAAction@query','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.TBB_BASEDATAAction@add','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.TBB_BASEDATAAction@add_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.TBB_BASEDATAAction@edit','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.TBB_BASEDATAAction@edit_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.TBB_BASEDATAAction@delete','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.TBB_BASEDATAAction@view','r001');

insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.TBB_PROJ_LISTAction@query','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.TBB_PROJ_LISTAction@add','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.TBB_PROJ_LISTAction@add_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.TBB_PROJ_LISTAction@edit','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.TBB_PROJ_LISTAction@edit_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.TBB_PROJ_LISTAction@delete','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.TBB_PROJ_LISTAction@view','r001');

insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.T1Action@query','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.T1Action@add','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.T1Action@add_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.T1Action@edit','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.T1Action@edit_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.T1Action@delete','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.T1Action@view','r001');

insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.RestrauntAction@query','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.RestrauntAction@add','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.RestrauntAction@add_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.RestrauntAction@edit','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.RestrauntAction@edit_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.RestrauntAction@delete','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.RestrauntAction@view','r001');

insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.PAction@login','r001');

insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.vpn.action.VpnuserAction@query','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.vpn.action.VpnuserAction@add','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.vpn.action.VpnuserAction@add_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.vpn.action.VpnuserAction@edit','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.vpn.action.VpnuserAction@edit_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.vpn.action.VpnuserAction@delete','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.vpn.action.VpnuserAction@view','r001');
-- 清除sys_user
truncate table sys_user;	
-- 密码888888
insert into SYS_USER(USER_ID,ORGAN_ID,USER_NAME,PASSWORD,STATE,IS_SYS,NOTE) values('admin','21000000','系统管理员','DUMCWIKCWVQU','0','1','');	
insert into SYS_USER(USER_ID,ORGAN_ID,USER_NAME,PASSWORD,STATE,IS_SYS,NOTE) values('0808','21000000','匿名用户','DUMCWIKCWVQU','0','1','');

-- 清理user_role
truncate table user_role;			
insert into USER_ROLE(USER_ID,ROLE_ID) values('admin','r001');	
insert into USER_ROLE(USER_ID,ROLE_ID) values('0808','r002');


--  以下以模块划分为主，便于sql代码调试 -- 
--  sys_permit注册 -- 
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.RecipeAction@query','查询Recipe',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.RecipeAction@add','添加Recipe',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.RecipeAction@add_save','保存Recipe',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.RecipeAction@edit','编辑Recipe',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.RecipeAction@edit_save','编辑保存Recipe',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.RecipeAction@delete','删除Recipe',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.basedata.action.RecipeAction@view','查看Recipe',null);

--  sys_permit授权 -- 
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.RecipeAction@query','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.RecipeAction@add','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.RecipeAction@add_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.RecipeAction@edit','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.RecipeAction@edit_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.RecipeAction@delete','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.basedata.action.RecipeAction@view','r001');

----  vpn相关---- 
--  vpnline注册 -- 
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.vpn.action.VpnlineAction@query','查询Vpnline',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.vpn.action.VpnlineAction@add','添加Vpnline',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.vpn.action.VpnlineAction@add_save','保存Vpnline',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.vpn.action.VpnlineAction@edit','编辑Vpnline',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.vpn.action.VpnlineAction@edit_save','编辑保存Vpnline',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.vpn.action.VpnlineAction@delete','删除Vpnline',null);
insert into SYS_PERMIT(PERMIT_ID,PERMIT_NAME,NOTE) values('com.tbb.vpn.action.VpnlineAction@view','查看Vpnline',null);

--  vpnline授权 -- 
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.vpn.action.VpnlineAction@query','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.vpn.action.VpnlineAction@add','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.vpn.action.VpnlineAction@add_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.vpn.action.VpnlineAction@edit','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.vpn.action.VpnlineAction@edit_save','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.vpn.action.VpnlineAction@delete','r001');
insert into SYS_ROLE_PERMIT(PERMIT_ID,ROLE_ID) values('com.tbb.vpn.action.VpnlineAction@view','r001');


















