# 10.03.2024
# BABE WAKE UP NEW DDLs JUST DROPPED 
```
CREATE TYPE public.request_status AS ENUM (
	'accepted',
	'need affirmation',
	'waiting',
	'rejected');


 -- ENUM[”nrw” (new request waiting), “arwf” (accepted request waiting feedback)]
 CREATE TYPE public.staff_noti_type AS ENUM (
	'nrw',
	'arwf');
 -- ENUM[”sra” (student request accepted), “srd” (student request declined)]
 CREATE TYPE public.student_noti_type AS ENUM (
	'sra',
	'srd');

```

```
-- comments
CREATE TABLE public.student_comments (
	request_when_created timestamptz NOT NULL,
	request_student_id int4 NOT NULL,
	request_type_id int4 NOT NULL,
	user_id int4 NOT NULL,
	user_comment text NULL,
	time_posted timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT student_comments_pk PRIMARY KEY (request_when_created, request_student_id, request_type_id, user_id, time_posted),
	CONSTRAINT fk_request FOREIGN KEY (request_when_created,request_student_id,request_type_id) REFERENCES public.student_requests(when_created,student_id,request_type_id),
	CONSTRAINT fk_student_id FOREIGN KEY (request_student_id) REFERENCES public.student(id)
);
DROP TABLE public.staff_comments;
CREATE TABLE public.staff_comments (
	request_when_created timestamptz NOT NULL,
	request_student_id int4 NOT NULL,
	request_type_id int4 NOT NULL,
	user_id int4 NOT NULL,
	user_comment text NULL,
	time_posted timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT staff_comments_pk PRIMARY KEY (request_when_created, request_student_id, request_type_id, user_id, time_posted),
	CONSTRAINT fk_request FOREIGN KEY (request_when_created,request_student_id,request_type_id) REFERENCES public.student_requests(when_created,student_id,request_type_id),
	CONSTRAINT fk_staff_id FOREIGN KEY (user_id) REFERENCES public.teaching_staff(id)
);
```

```
-- notifications
CREATE TABLE public.staff_notification (
	request_when_created timestamptz NOT NULL,
	request_student_id int4 NOT NULL,
	request_type_id int4 NOT NULL,
	user_id int4 NOT NULL,
	haveseen bool DEFAULT false NULL,
	notification_type public.staff_noti_type NOT NULL,
	time_posted timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT staff_notification_pk PRIMARY KEY (request_when_created, request_student_id, request_type_id, user_id, time_posted),
	CONSTRAINT fk_request FOREIGN KEY (request_when_created,request_student_id,request_type_id) REFERENCES public.student_requests(when_created,student_id,request_type_id),
	CONSTRAINT fk_staff_id FOREIGN KEY (user_id) REFERENCES public.teaching_staff(id)
);
CREATE TABLE public.student_notification (
	request_when_created timestamptz NOT NULL,
	request_student_id int4 NOT NULL,
	request_type_id int4 NOT NULL,
	user_id int4 NOT NULL,
	haveseen bool DEFAULT false NULL,
	notification_type public.student_noti_type NOT NULL,
	time_posted timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT student_notification_pk PRIMARY KEY (request_when_created, request_student_id, request_type_id, user_id, time_posted),
	CONSTRAINT fk_request FOREIGN KEY (request_when_created,request_student_id,request_type_id) REFERENCES public.student_requests(when_created,student_id,request_type_id),
	CONSTRAINT fk_student_id FOREIGN KEY (request_student_id) REFERENCES public.student(id)
);
```

```
DROP VIEW IF EXISTS student_requests_listing_view;
CREATE VIEW student_requests_listing_view AS
SELECT sr.student_id,
       sr.current_index,
       sr.information,
       sr.when_created,
       sr.status
FROM student_requests sr
JOIN request_actors ra ON sr.request_type_id = ra.request_type_id
GROUP BY sr.student_id, sr.current_index, sr.information, sr.when_created, sr.status;


DROP VIEW IF EXISTS staff_waiting_requests_view;
CREATE OR REPLACE VIEW staff_waiting_requests_view AS
SELECT sr.student_id,
	   sr.request_type_id,
	   rt.request_name,
       sr.current_index,
       sr.information,
       sr.when_created,
       ra.staff_id as current_actor_id
FROM student_requests sr
JOIN request_actors ra ON sr.request_type_id = ra.request_type_id
JOIN request_types rt ON rt.id = sr.request_type_id
WHERE ra.index = sr.current_index
ORDER BY sr.when_created;


DROP VIEW IF EXISTS advisor_waiting_requests_view;
CREATE OR REPLACE VIEW advisor_waiting_requests_view AS
SELECT sr.student_id,
	   sr.request_type_id,
	   rt.request_name,
       sr.current_index,
       sr.information,
       sr.when_created,
       ts.id as current_actor_id
FROM student_requests sr
JOIN student s ON s.id = sr.student_id
JOIN teaching_staff ts ON s.adviser_id= ts.id 
JOIN request_types rt ON rt.id = sr.request_type_id
WHERE sr.current_index=0
ORDER BY sr.when_created;

DROP VIEW IF EXISTS waiting_requests_unioned_view;
SELECT * FROM staff_waiting_requests_view
UNION 
SELECT * FROM advisor_waiting_requests_view;


```

## 

new views
  - advisor_info
  - student_requests_listing_view

request_requirements tables primary key is now named "id".

added columns "web" and "phone_number" to teaching_staff table.

monke
```
ALTER TABLE request_requirements
RENAME COLUMN requirement_id TO id;

ALTER TABLE teaching_staff
ADD COLUMN web VARCHAR(255),
ADD COLUMN phone_number VARCHAR(20);

DROP VIEW IF EXISTS advisor_info;
CREATE OR REPLACE VIEW advisor_info AS
SELECT s.id AS student_id,
       ts.id AS advisor_id,
       ts.name AS advisor_firstname,
       ts.surname AS advisor_lastname,
       d.name AS department_name,
       ts.web AS advisor_web,
       ts.phone_number AS advisor_phone_number
FROM student s
JOIN teaching_staff ts ON s.adviser_id = ts.id
JOIN department d ON ts.department_id = d.id;

update teaching_staff
set web = 'https://www.linkedin.com/in/ey%C3%BCp-can-y%C3%B6nter-4b3342219/',
	phone_number = '505 734 2315'
where id = 1;

DROP VIEW IF EXISTS student_requests_listing_view;
CREATE VIEW student_requests_listing_view AS
SELECT sr.student_id,
       sr.current_index,
       sr.information,
       sr.when_created,
       CASE
           WHEN sr.current_index > MAX(ra.index) THEN 'accepted'
           WHEN sr.current_index BETWEEN 0 AND MAX(ra.index) THEN 'waiting'
           WHEN sr.current_index = -1 THEN 'rejected'
           ELSE 'unknown'
       END AS status
FROM student_requests sr
JOIN request_actors ra ON sr.request_type_id = ra.request_type_id
GROUP BY sr.student_id, sr.current_index, sr.information, sr.when_created;

```


changed when attribute to when_created because when is a keyword obv
student_comment added to the student_request table
```
ALTER TABLE public.student_requests ADD student_comment text NULL;
ALTER TABLE public.student_requests RENAME COLUMN "when" TO when_created;

```


New table staff_comments added for make teaching staff able to comment on the student_requests
```
CREATE TABLE staff_comments(
	requester_id int ,
	request_date timestamptz ,
	request_type_id int,
	staff_id int ,
	comment text,
	CONSTRAINT staff_comments_pk PRIMARY KEY (requester_id, request_date, request_type_id, staff_id),
	CONSTRAINT fk_requeste
		foreign key (requester_id, request_date, request_type_id)
			references student_requests(student_id, when_created, request_type_id),
	CONSTRAINT fk_staff_id
		foreign key (staff_id)
			references teaching_staff(id)
	);	

```

Added department references to request_types +++ added pretext column to the request_requirements 
```
ALTER TABLE public.request_types ADD department_id int NOT NULL;
ALTER TABLE public.request_types ADD CONSTRAINT request_types_fk FOREIGN KEY (department_id) REFERENCES public.department(id);
ALTER TABLE public.request_types ALTER COLUMN department_id SET NOT NULL;

ALTER TABLE public.request_requirements ADD prtext text NULL;
ALTER TABLE public.request_requirements ALTER COLUMN pretext SET NOT NULL;


```

```sql
DROP VIEW IF EXISTS staff_waiting_requests_view;
CREATE OR REPLACE VIEW staff_waiting_requests_view AS
SELECT sr.student_id,
	   sr.request_type_id,
	   rt.request_name,
       sr.current_index,
       sr.information,
       sr.when_created,
       sr.student_comment,
       ra.staff_id as current_actor_id
FROM student_requests sr
JOIN request_actors ra ON sr.request_type_id = ra.request_type_id
JOIN request_types rt ON rt.id = sr.request_type_id
WHERE ra.index = sr.current_index
ORDER BY sr.when_created;
```

```sql

DROP VIEW IF EXISTS advisor_waiting_requests_view;
CREATE OR REPLACE VIEW advisor_waiting_requests_view AS
SELECT sr.student_id,
	   sr.request_type_id,
	   rt.request_name,
       sr.current_index,
       sr.information,
       sr.when_created,
       sr.student_comment,
       ts.id as current_actor_id
FROM student_requests sr
JOIN student s ON s.id = sr.student_id
JOIN teaching_staff ts ON s.adviser_id= ts.id 
JOIN request_types rt ON rt.id = sr.request_type_id
WHERE sr.current_index=0
ORDER BY sr.when_created;


```

```sql

CREATE OR REPLACE VIEW waiting_requests_unioned_view AS
SELECT * FROM staff_waiting_requests_view
UNION 
SELECT * FROM advisor_waiting_requests_view;

```

