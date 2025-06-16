create user testaccount identified by testaccount;

grant resource, connect to testaccount;
alter user testaccount default tablespace users;
alter user testaccount temporary tablespace temp;