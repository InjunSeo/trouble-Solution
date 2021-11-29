
show databases;
create database GominNO;
use GominNo;

drop table if exists member;
drop table if exists Worry;
drop table if exists solution;
drop table if exists Favouring;

create table Member(
	member_Id varchar(30) not null,
	password varchar(30) not null,
	user_name varchar(30) not null,
	Generation char(4) not null, 
	create_date Date,
	constraint primary key(member_Id)
)default charset=utf8mb4;

create table Worry(
	worryId int auto_increment not null,
	writer varchar(30),
	worryTitle varchar(45),
	content text not null,
	categories varchar(30),
	answerGeneration char(4),
	create_date datetime,
	solved datetime,
	constraint primary key(worryId)
)default charset=utf8mb4;

create table Solution(
	solId int auto_increment not null,
	worryId int,
	solverId varchar(30),
	content text,
	create_date datetime,
	accepted datetime,
	favoured int,
	constraint primary key(solId)
)default charset=utf8mb4;

create table Favouring(
	solId int,
	whoFavour varchar(30),
	favourDate datetime,
	constraint primary key(solId, whofavour)
);
 

alter table member
	alter column create_date set default now();
	
alter table worry
	add constraint FK_member_worry
		foreign key(writer)
		references member(member_Id)
		on update cascade
		on delete set null, 
	alter column answerGeneration set default '1090';

alter table Solution
	add constraint FK_worry_solution
		foreign key(worryId)
		references worry(worryId)
		on delete set null,
	add constraint FK_member_solution
		foreign key(solverId)
		references member(member_Id)
		on delete set null;

alter table Favouring
	add constraint FK_Sol_Favour
		foreign key(solId)
		references solution(solId)
		on delete cascade,
	add constraint FK_Member_Favour
		foreign key(whoFavour)
		references member(member_Id)
		on update cascade
		on delete cascade;

insert into categories values(1, '연애', 1);
insert into categories values (2, '운동', 2);
insert into categories values (3, '취업', 3);

insert into member values('seomoon', null, now(), 'TWENTYToTHIRTY', 'injun Seo', 1234);
insert into member values('seostar', null, now(), 'TWENTYToTHIRTY', 'Frege', 1234);
insert into member values('skyblue', null, now(), 'ELDER', 'Russell', 1234);
insert into member values('eveningstar', null, now(), 'TEEN', 'Grice', 1234);
insert into member values('morningStar', null, now(), 'ELDER', 'Lewis', 1234);

insert into worry values(1, 'not easy', now(), 'TEEN', null, default, 'Job problem', 'seomoon');
insert into worry values(2, '썸 타는 사람이랑', now(), 'TWENTYToTHIRTY', null, default, '연애 고민', 'seostar');

insert into solution values(1, default, '매일 차근차근 준비하세요', now(), 'skyblue', 1);
insert into Solution value (2, default, '직진하세요.', now(), 'seostar', 1);

insert into category_worry values (1, 3, 1);
insert into category_worry values (2, 1, 2);

desc member;
desc worry;
desc Solution;
desc categories;
desc category_worry;
select * from Member;
select * from Worry;
select * from Solution;
select * from category_worry;

select * from Worry where generation like 'TEEN';





