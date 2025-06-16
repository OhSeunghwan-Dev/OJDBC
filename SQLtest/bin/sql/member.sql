create table member (
	id varchar2(30) primary key,
	pw varchar2(30) not null,
	gender varchar2(10) not null,
	address varchar2(50),
	phoneNumber varchar2(15),
	email varchar2(30),
	regdate date not null
)

select * from member
drop table member

insert into member(id, pw, gender, address, phoneNumber, email, regdate) values('abcd', '1234', 'male', '경기도 성남시 분당구', '010-1111-1111', 'abc@naver.com', sysdate)
insert into member(id, pw, gender, address, phoneNumber, email, regdate) values('qwer', '8520', 'female', '서울특별시 강남구', '010-2345-1123', 'QWER@naver.com', sysdate)
insert into member(id, pw, gender, address, phoneNumber, email, regdate) values('admin', '1111', 'male', '경시도 수원시', '010-9874-3216', 'admin@gmail.com', sysdate)