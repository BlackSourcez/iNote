CREATE TABLE `notes` (
	`_id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`topic`	TEXT NOT NULL,
	`content`	TEXT NOT NULL,
	`priority`	INTEGER,
	`date`	TEXT,
	`time`	TEXT
);

  INSERT INTO notes (_id,topic,content,priority,date,time)
VALUES (null,"one " , "two three" , 3, "THIS IS A DATE" , "THIS IS A TIME");


DELETE FROM notes