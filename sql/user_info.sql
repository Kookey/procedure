prompt PL/SQL Developer import file
prompt Created on 2013Äê8ÔÂ26ÈÕ by admin
set feedback off
set define off
prompt Creating USER_INFO...
create table USER_INFO
(
  id      VARCHAR2(4),
  name    VARCHAR2(15),
  pwd     VARCHAR2(15),
  address VARCHAR2(30)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for USER_INFO...
alter table USER_INFO disable all triggers;
prompt Deleting USER_INFO...
delete from USER_INFO;
commit;
prompt Loading USER_INFO...
insert into USER_INFO (id, name, pwd, address)
values ('123', 'wang', '345', 'beijing');
insert into USER_INFO (id, name, pwd, address)
values ('w123', 'houddm', 'wxl654', 'shanghai');
commit;
prompt 2 records loaded
prompt Enabling triggers for USER_INFO...
alter table USER_INFO enable all triggers;
set feedback on
set define on
prompt Done.
