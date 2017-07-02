
create table user (
	id int auto_increment,
	password varchar(50),
	email varchar(50),
	creation datetime,
	active bit ,
	wronglogin int ,
	
	constraint PK_user
		primary key (id)
);

create table place (
	id int auto_increment,

	creation datetime,

	code varchar(250),
	estateagency varchar(250),

	description varchar(250),

	address varchar(250),
	value int,

	acceptpets bit,
	acceptkids bit,
	hasbackyard bit,

	backyardapart bit,
	lightapart bit,
	waterapart bit,
	
	discarded bit not null,
	
	user_id int not null,
	
	constraint PK_place
		primary key (id),
	
	constraint FK_place_user
		foreign key (user_id)
		references user (id);
);

