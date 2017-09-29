create table Game_Character (
	uuid_ VARCHAR(75) null,
	characterId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	url VARCHAR(75) null,
	status VARCHAR(75) null
);