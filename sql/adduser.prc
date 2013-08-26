create or replace procedure adduser
(
   n_id user_info.id%type,
   n_name user_info.name%type,
   n_pwd user_info.pwd%type,
   n_address user_info.address%type
 )
 as
 begin
   insert into user_info(id,name,pwd,address) values(n_id,n_name,n_pwd,n_address);
   end adduser;
/
