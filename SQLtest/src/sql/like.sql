create table b_like (
	bid number(6) not null,
	likeuser varchar2(30)
)

alter table b_like add constraint like_fk_memberid foreign key(likeuser) references member (id) on delete set null
alter table b_like add constraint like_fk_boardid foreign key(bid) references board (bid) on delete cascade

SELECT u1.table_name, column_name, constraint_type, u1.constraint_name, search_condition
        FROM user_constraints u1
        JOIN user_cons_columns u2 ON u1.constraint_name = u2.constraint_name
        WHERE u1.table_name = UPPER('b_like');

select * from b_like

select m.* from member m join b_like b on m.id = b.likeuser where b.bid = 5