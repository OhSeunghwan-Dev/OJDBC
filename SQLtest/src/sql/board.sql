create table board (
	bid number(6) primary key,
	btitle varchar2(100) not null,
	bcontent varchar2(1000) not null,
	bauthor varchar2(30) not null,
	bdate date not null
)

alter table board add constraint fk_memberid foreign key(bauthor) references member (id) on delete cascade
create sequence boardid_seq increment by 1 start with 1 nocycle nocache

select * from board
drop table board

/* 제약조건 확인 */
SELECT u1.table_name, column_name, constraint_type, u1.constraint_name, search_condition
        FROM user_constraints u1
        JOIN user_cons_columns u2 ON u1.constraint_name = u2.constraint_name
        WHERE u1.table_name = UPPER('board');
        
        
insert into board (bid, btitle, bcontent, bauthor, bdate) values (boardid_seq.nextval, '안녕하세요', '첫 게시글입니다. 잘 부탁드립니다.', 'abcd', sysdate)
insert into board (bid, btitle, bcontent, bauthor, bdate) values (boardid_seq.nextval, '여러분', '다른 분들은 안 계신가요? 글 써주세요.', 'abcd', sysdate)
insert into board (bid, btitle, bcontent, bauthor, bdate) values (boardid_seq.nextval, '안녕하세요!', '밴드 QWER 공식 계정입니다! 잘 부탁드립니다.', 'qwer', sysdate)
insert into board (bid, btitle, bcontent, bauthor, bdate) values (boardid_seq.nextval, '관리자입니다.', '아직 아무런 권한도 못 받은 관리자입니다.', 'admin', sysdate)
insert into board (bid, btitle, bcontent, bauthor, bdate) values (boardid_seq.nextval, '게발자님께', '저 관리자 권한 좀 만들어주세요...제가 일반 사용자랑 다를게 뭡니까', 'admin', sysdate)