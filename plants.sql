BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "plants" (
	"id"	INTEGER NOT NULL UNIQUE,
	"name"	TEXT NOT NULL,
	"age"	INTEGER NOT NULL,
	"is_trimmed"	INTEGER NOT NULL,
	"is_sick"	INTEGER NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (0,'oak',70,1,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (1,'oak',20,1,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (2,'oak',100,1,1);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (3,'birch',15,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (4,'ash tree',17,1,1);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (5,'chestnut tree',24,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (6,'linden tree',1,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (7,'alder',0,0,1);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (8,'apple tree',2,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (9,'rosehip',12,1,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (10,'rosehip',12,1,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (11,'rosehip',7,1,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (12,'honeysuckle',16,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (13,'mountain ash',5,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (14,'juniper',0,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (15,'hawthorn',3,1,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (16,'raspberry',4,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (17,'raspberry',0,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (18,'bird cherry',6,0,1);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (19,'lingonberry',2,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (20,'lingonberry',10,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (21,'celandine',2,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (22,'celandine',1,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (23,'celandine',1,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (24,'nettle',0,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (25,'nettle',0,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (26,'burdock',1,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (27,'lungwort',1,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (28,'lungwort',2,1,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (29,'violet',3,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (30,'violet',2,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (31,'bellflower',1,0,0);
INSERT INTO "plants" ("id","name","age","is_trimmed","is_sick") VALUES (32,'bellflower',1,0,0);
COMMIT;
