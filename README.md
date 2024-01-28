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
       sr.when,
       CASE
           WHEN sr.current_index > MAX(rr.index) THEN 'accepted'
           WHEN sr.current_index BETWEEN 0 AND MAX(rr.index) THEN 'waiting'
           WHEN sr.current_index = -1 THEN 'rejected'
           ELSE 'unknown'
       END AS status
FROM student_requests sr
JOIN request_requirements rr ON sr.request_type_id = rr.request_type_id
GROUP BY sr.student_id, sr.current_index, sr.information, sr.when;

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

