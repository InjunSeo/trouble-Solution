show databases;
use gominno;
insert into categories values(1, '연애', 1);
insert into categories values (2, '운동', 2);
insert into categories values (3, '취업', 3);
insert into categories values (4, '생활', 4);

insert into member values('seomoon', null, now(), 'TWENTYToTHIRTY', 'injun Seo', 1234);
insert into member values('seostar', null, now(), 'TWENTYToTHIRTY', 'Frege', 1234);
insert into member values('skyblue', null, now(), 'ELDER', 'Russell', 1234);
insert into member values('eveningstar', null, now(), 'TEEN', 'Grice', 1234);
insert into member values('morningStar', null, now(), 'ELDER', 'Grice', 1234);

insert into worry values(1, 'not easy', now(), 'TEEN', null, default, 'Job problem', 'seomoon');
insert into worry values(2, '썸 타는 사람이랑', now(), 'TWENTYToTHIRTY', null, default, '연애 고민', 'seostar');
insert into worry values(3, '중량 올리기', now(), 'TWENTYToTHIRTY', null, default, '중량 올리는 방법', 'skyblue');
insert into worry values(4, 'run faster', now(), 'TWENTYToTHIRTY', null, default, 'How can I run faster?', 'eveningstar');
insert into worry values(5, 'Inference to the best explaantion', now(), 'TWENTYToTHIRTY', null, default, 'IBE', 'seomoon');


insert into category_worry values(1, 3, 1);
insert into category_worry values(2, 1, 2);
insert into category_worry values(3, 2, 3);
insert into category_worry values(4, 2, 4);


insert into solution values(1, default, '매일 차근차근 준비하세요', now(), 'skyblue', 1);
insert into Solution value (2, default, '직진하세요.', now(), 'seostar', 1);
insert into Solution value (3, default, '썸에서 연애로 관계를..', now(), 'seomoon', 2);

select * from Member;
select * from Worry;
select * from Solution;
select * from categories;
select * from category_worry;
desc categories;
desc category_worry;

select m.member_id, m.create_date, w.worry_id, w.worry_title from member m
left outer join worry w
    on m.member_id = w.member_id;

select m.member_id, m.create_date, w.worry_id, w.worry_title from member m
cross join worry w;


/* solution 데이터들은 worry에서 요청한 generation과 일치하는가? */


/* worry.createDate 오류 검사: */
select m.member_id, m.create_date, w.create_date, w.worry_title
from worry w
right OUTER JOIN member m on w.member_id = m.member_id
where m.create_date > w.create_date;

use gominno;
select member_Id, Generation, user_name
from Member
where member_Id like '%star';

/*SubQuery*/
select member_Id, Generation, user_name
from member
where create_date > (select create_date from member where member_Id = 'eveningstar');
# 서브쿼리 아래 반환된 값이 두 개 이상일때
select member_Id, user_name, create_date
from member
where create_date >= any(select s.create_date from solution s where accepted_status = 'READY');
/* Sorting "*/
select * from member order by member_id desc, create_date asc;

/*Join*/
select m.member_id, m.create_date, s.solution_id, s.accepted_status
from member m
inner join solution s on s.member_id = m.member_id;

select c.category_name, w.worry_id, w.worry_title
from categories c
inner join category_worry cw
    on c.category_id = cw.category_id
inner join worry w
    on cw.worry_id  = w.worry_id;
/* 3중 Join */
select w.worry_id, w.worry_title, w.member_id, c.category_name
from worry w
    inner join category_worry cw
        on w.worry_id = cw.worry_id
    inner join categories c
        on cw.category_id = c.category_id;
/* outer join */
select m.member_id, w.worry_id, w.worry_title
from member m
    left outer join worry w
        on m.member_id = w.member_id;

select m.member_id, w.worry_id, w.worry_title
from worry w
    right join member m
        on m.member_id = w.member_id;

select m.member_id, m.create_date, w.worry_title, w.create_date
from member m
    left outer join worry w
        on m.member_id = w.member_id;

select c.category_name, c.category_id, w.worry_title
from worry w
    left outer join category_worry cw
        on cw.worry_id = w.worry_id
    right outer join categories c
        on cw.category_id = c.category_id;

// member_id,  worry_title, categoryName

select w.worry_id, w.worry_title, w.create_date, c.category_name
from worry w
    left outer join category_worry cw
        on w.worry_id = cw.worry_id
    left outer join categories c
        on cw.category_id = c.category_id
union
select w.worry_id, w.worry_title, w.create_date, c.category_name
from worry w
    left outer join category_worry cw
        on cw.worry_id = w.worry_id
    right outer join categories c
        on cw.category_id = c.category_id;

select * from solution;
select mm.member_Id, mm.user_name
from (select m.member_Id, m.user_name
      from Member m) as mm;