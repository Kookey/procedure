create or replace procedure userlist(ul_cursor out list.list_cursor)
is
begin
open ul_cursor for select * from scott.user_info;
end userlist;
/
