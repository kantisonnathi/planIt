create table user
(
	id int auto_increment primary key,
	username varchar(255) null,
	password varchar(255) null,
	email varchar(255) null,
	email_verified bit null,
	is_user_complete bit not null,
	phoneNumber varchar(255) null,
	phone_verified bit null,
	profession varchar(255) null
);

create table category
(
	id int auto_increment
		primary key,
	name varchar(255) null,
	user_id int null,
	constraint FKpfk8djhv5natgshmxiav6xkpu
		foreign key (user_id) references user (id)
);

create table item
(
	id int auto_increment
		primary key,
	name varchar(255) null,
	quantity int null,
	to_do bit not null,
	category_id int null,
	constraint FK2n9w8d0dp4bsfra9dcg0046l4
		foreign key (category_id) references category (id)
);

