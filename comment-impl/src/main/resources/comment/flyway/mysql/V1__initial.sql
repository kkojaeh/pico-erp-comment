create table cmt_comment (
	id binary(16) not null,
	comment varchar(200),
	created_by_id varchar(50),
	created_by_name varchar(50),
	created_date datetime,
	subject_id varchar(50),
	subject_type_id varchar(50),
	primary key (id)
) engine=InnoDB;

create index CMT_COMMENT_SUBJECT_ID_IDX
	on cmt_comment (subject_id);
