# Database Schema

## Faculty Table

```sql
CREATE TABLE faculty (
    faculty_id SERIAL PRIMARY KEY,
    name TEXT
);
```

## Department Table

```sql
CREATE TABLE department (
    department_id SERIAL PRIMARY KEY,
    name TEXT,
    faculty_id INT,
    CONSTRAINT fk_facultyid
        FOREIGN KEY(faculty_id)
            REFERENCES faculty(faculty_id)
);
```

## Teaching Staff Table

```sql
CREATE TABLE public.teaching_staff (
	id serial4 NOT NULL,
	"name" text NULL,
	surname text NULL,
	email text NULL,
	department_id int4 NULL,
	"password" text NULL,
	"role" varchar NOT NULL,
	web varchar(255) NULL,
	phone_number varchar(20) NULL,
	CONSTRAINT teaching_staff_pkey PRIMARY KEY (id)
);


-- public.teaching_staff foreign keys

ALTER TABLE public.teaching_staff ADD CONSTRAINT fk_bolumid FOREIGN KEY (department_id) REFERENCES public.department(id);
```

## Course Table

```sql
CREATE TABLE public.course (
	"name" text NULL,
	code text NOT NULL,
	department_id int4 NULL,
	"type" varchar NOT NULL,
	CONSTRAINT course_pk PRIMARY KEY (code)
);


-- public.course foreign keys

ALTER TABLE public.course ADD CONSTRAINT course_fk FOREIGN KEY (department_id) REFERENCES public.department(id);
```

## Section Table

```sql
CREATE TABLE public."section" (
	section_number int4 NOT NULL,
	instructor_id int4 NULL,
	assistant_id int4 NULL,
	course_code varchar NOT NULL,
	CONSTRAINT section_pk PRIMARY KEY (section_number, course_code)
);


-- public."section" foreign keys

ALTER TABLE public."section" ADD CONSTRAINT fk_assistant_id FOREIGN KEY (assistant_id) REFERENCES public.teaching_staff(id);
ALTER TABLE public."section" ADD CONSTRAINT fk_instructor_id FOREIGN KEY (instructor_id) REFERENCES public.teaching_staff(id);
ALTER TABLE public."section" ADD CONSTRAINT section_fk FOREIGN KEY (course_code) REFERENCES public.course(code);
```

## Section Records Table

```sql
CREATE TABLE public.section_records (
	student_id int4 NOT NULL,
	course_code varchar NOT NULL,
	section_number int4 NOT NULL,
	term varchar NOT NULL,
	CONSTRAINT section_records_pk PRIMARY KEY (student_id, course_code, section_number, term)
);


-- public.section_records foreign keys

ALTER TABLE public.section_records ADD CONSTRAINT fk_student_id FOREIGN KEY (student_id) REFERENCES public.student(id);
ALTER TABLE public.section_records ADD CONSTRAINT section_records_fk FOREIGN KEY (section_number,course_code) REFERENCES public."section"(section_number,course_code);
```

## Student Table

```sql
CREATE TABLE student (
    student_id INT PRIMARY KEY,
    name TEXT,
    surname TEXT,
    email TEXT,
    department_id INT,
    adviser_id INT,
    password TEXT,
    CONSTRAINT fk_adviser_id
        FOREIGN KEY(adviser_id)
            REFERENCES teaching_staff(id)
);
```

## Request Table

```sql
CREATE TABLE public.student_requests (
	student_id int4 NOT NULL,
	request_type_id int4 NOT NULL,
	information text NULL,
	addition text NULL,
	current_index int4 NULL,
	when_created timestamptz NOT NULL,
	student_comment text NULL,
	CONSTRAINT student_requests_pk PRIMARY KEY (when_created, student_id, request_type_id)
);


-- public.student_requests foreign keys

ALTER TABLE public.student_requests ADD CONSTRAINT fk_studentid FOREIGN KEY (student_id) REFERENCES public.student(id);
ALTER TABLE public.student_requests ADD CONSTRAINT fk_type FOREIGN KEY (request_type_id) REFERENCES public.request_types(id);
```

## Request Types Table

```sql
CREATE TABLE request_types (
    id SERIAL PRIMARY KEY,
    info TEXT,
    request_name TEXT
);
```

## Request Actors Table

```sql
CREATE TABLE public.request_actors (
	request_type_id int4 NOT NULL,
	staff_id int4 NOT NULL,
	"index" int4 NOT NULL,
	CONSTRAINT request_actors_pk PRIMARY KEY (request_type_id, staff_id)
);


-- public.request_actors foreign keys

ALTER TABLE public.request_actors ADD CONSTRAINT fk_staff FOREIGN KEY (staff_id) REFERENCES public.teaching_staff(id);
ALTER TABLE public.request_actors ADD CONSTRAINT fk_type FOREIGN KEY (request_type_id) REFERENCES public.request_types(id);
```

## Request Requirements Table

```sql
CREATE TABLE public.request_requirements (
	id int4 NOT NULL DEFAULT nextval('request_requirements_requierment_id_seq'::regclass),
	"name" text NULL,
	"type" text NULL,
	request_type_id int4 NULL,
	"index" int4 NULL,
	CONSTRAINT request_requirements_pkey PRIMARY KEY (id)
);


-- public.request_requirements foreign keys

ALTER TABLE public.request_requirements ADD CONSTRAINT fk_type FOREIGN KEY (request_type_id) REFERENCES public.request_types(id);
```
## Request Requirements Table

```sql
CREATE TABLE public.staff_comments (
	requester_id int4 NOT NULL,
	request_date timestamptz NOT NULL,
	request_type_id int4 NOT NULL,
	staff_id int4 NOT NULL,
	"comment" text NULL,
	CONSTRAINT staff_comments_pk PRIMARY KEY (requester_id, request_date, request_type_id, staff_id)
);


-- public.staff_comments foreign keys

ALTER TABLE public.staff_comments ADD CONSTRAINT fk_requeste FOREIGN KEY (requester_id,request_date,request_type_id) REFERENCES <?>();
ALTER TABLE public.staff_comments ADD CONSTRAINT fk_staff_id FOREIGN KEY (staff_id) REFERENCES public.teaching_staff(id);


# Database Views

## Requests Actors View

```sql
-- View to show request actors
CREATE VIEW requestsActors AS
    SELECT rt.id AS requestid, rt.request_name, ts.id, ts.staff_name
    FROM request_types rt
    JOIN request_actors ra ON ra.request_type_id = rt.id
    JOIN teaching_staff ts ON ra.staff_id = ts.id;
```

## Request Requirements View

```sql
-- View to show request requirements
CREATE OR REPLACE VIEW public.requestrequirements
AS SELECT rt.id AS requestid,
    rt.request_name,
    rr.name,
    rr.type,
    rr.index
   FROM request_types rt
     JOIN request_requirements rr ON rt.id = rr.request_type_id;
```

## Student Request Path View

```sql
-- View to show student request path
CREATE OR REPLACE VIEW public.studentrequestpath
AS SELECT s.name AS student_name,
    s.id AS student_id,
    rt.request_name,
    ts.name AS adviser_name,
    ts.id AS adviser_id,
    ts2.id AS actor_id,
    ts2.name AS actor_name,
    ra.index AS actor_index
   FROM student_requests r
     JOIN student s ON r.student_id = s.id
     JOIN teaching_staff ts ON s.adviser_id = ts.id
     JOIN request_types rt ON rt.id = r.request_type_id
     JOIN request_actors ra ON ra.request_type_id = rt.id
     JOIN teaching_staff ts2 ON ra.staff_id = ts.id
  ORDER BY s.id, ra.index;
```

## Course, Branch, and Student List View
```sql

```

## Adviser_info View
```
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
		
```
