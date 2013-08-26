create or replace procedure getuser(in_id in varchar2 ,out_username out varchar2)
as
begin
  select s.name into out_username from user_info s where s.id = in_id;
  end getuser;
/
