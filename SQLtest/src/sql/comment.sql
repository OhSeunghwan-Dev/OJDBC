create table b_comment (
	cid number(6) primary key,
	bid number(6) not null,		/*댓글이 달린 게시판 번호*/
	ccontent varchar2(300) not null,
	cauthor varchar2(30) not null,	/*작성자*/
	cdate date not null
)

create sequence commentid_seq increment by 1 start with 1 nocycle nocache

alter table b_comment add constraint comment_fk_memberid foreign key(cauthor) references member (id) on delete cascade
alter table b_comment add constraint comment_fk_boardid foreign key(bid) references board (bid) on delete cascade

SELECT u1.table_name, column_name, constraint_type, u1.constraint_name, search_condition
        FROM user_constraints u1
        JOIN user_cons_columns u2 ON u1.constraint_name = u2.constraint_name
        WHERE u1.table_name = UPPER('b_comment');

select * from b_comment