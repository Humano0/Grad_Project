

CREATE TABLE IF NOT EXISTS entry_logs (
    id SERIAL PRIMARY KEY,
    users_ip VARCHAR(15),
    users_id INT,
    entry_time TIMESTAMPTZ
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

/* DROP TABLE IF EXISTS student_requests CASCADE; */

CREATE TABLE IF NOT EXISTS public.student_requests (
	student_id int4 NOT NULL,
	request_type_id int4 NOT NULL,
	information text NULL,
	addition text NULL,
	current_index int4 NULL,
	when_created timestamptz NOT NULL,
	status varchar,
    unique_request_id UUID DEFAULT uuid_generate_v4() UNIQUE,
	CONSTRAINT student_requests_pk PRIMARY KEY (when_created, student_id, request_type_id),
    CONSTRAINT fk_studentid FOREIGN KEY (student_id) REFERENCES public.student(id),
    CONSTRAINT fk_type FOREIGN KEY (request_type_id) REFERENCES public.request_types(id)
);

DROP TABLE IF EXISTS cancel_reject_reasons CASCADE;

CREATE TABLE IF NOT EXISTS cancel_reject_reasons (
    request_uuid UUID PRIMARY KEY,
    reason TEXT,
    status VARCHAR,
    CONSTRAINT fk_request_uuid FOREIGN KEY (request_uuid) REFERENCES student_requests(unique_request_id)
);

DROP VIEW IF EXISTS all_requests_with_actors_view CASCADE;

CREATE OR REPLACE VIEW all_requests_with_actors_view AS
    SELECT sr.student_id,
        s.name || ' ' || s.surname as student_name,
        s.email,
        ts.name || ' ' || ts.surname as advisor,
        d.name as department_name,
        sr.request_type_id,
        rt.request_name,
        sr.current_index,
        sr.information,
        sr.when_created,
        ra.staff_id as actor_id,
        sr.status,
        sr.addition
    FROM student_requests sr
    JOIN student s ON s.id = sr.student_id
    JOIN teaching_staff ts ON s.adviser_id= ts.id
    JOIN department d on s.department_id = d.id
    JOIN request_actors ra ON sr.request_type_id = ra.request_type_id
    JOIN request_types rt ON rt.id = sr.request_type_id
    WHERE sr.status <> 'REJECTED' AND sr.status <> 'ACCEPTED' AND sr.status <> 'CANCELLED'
UNION
    SELECT sr.student_id,
        s.name || ' ' || s.surname as student_name,
        s.email,
        ts.name || ' ' || ts.surname as advisor,
        d.name as department_name,
        sr.request_type_id,
        rt.request_name,
        sr.current_index,
        sr.information,
        sr.when_created,
        ts.id as actor_id,
        sr.status,
        sr.addition
    FROM student_requests sr
    JOIN student s ON s.id = sr.student_id
    JOIN department d on s.department_id = d.id
    JOIN teaching_staff ts ON s.adviser_id= ts.id
    JOIN request_types rt ON rt.id = sr.request_type_id
    WHERE sr.status <> 'REJECTED' AND sr.status <> 'ACCEPTED' AND sr.status <> 'CANCELLED'
ORDER BY when_created;


DROP VIEW IF EXISTS concluded_requests_with_actors_view CASCADE;

CREATE OR REPLACE VIEW concluded_requests_with_actors_view AS
    SELECT sr.student_id,
        s.name || ' ' || s.surname as student_name,
        s.email,
        ts.name || ' ' || ts.surname as advisor,
        d.name as department_name,
        sr.request_type_id,
        rt.request_name,
        sr.current_index,
        sr.information,
        sr.when_created,
        ra.staff_id as actor_id,
        sr.status,
        sr.addition
    FROM student_requests sr
    JOIN student s ON s.id = sr.student_id
    JOIN teaching_staff ts ON s.adviser_id= ts.id
    JOIN department d on s.department_id = d.id
    JOIN request_actors ra ON sr.request_type_id = ra.request_type_id
    JOIN request_types rt ON rt.id = sr.request_type_id
    WHERE sr.status = 'REJECTED' or sr.status = 'ACCEPTED' or sr.status = 'CANCELLED'
UNION
    SELECT sr.student_id,
        s.name || ' ' || s.surname as student_name,
        s.email,
        ts.name || ' ' || ts.surname as advisor,
        d.name as department_name,
        sr.request_type_id,
        rt.request_name,
        sr.current_index,
        sr.information,
        sr.when_created,
        ts.id as actor_id,
        sr.status,
        sr.addition
    FROM student_requests sr
    JOIN student s ON s.id = sr.student_id
    JOIN department d on s.department_id = d.id
    JOIN teaching_staff ts ON s.adviser_id= ts.id
    JOIN request_types rt ON rt.id = sr.request_type_id
    where sr.status = 'REJECTED' or sr.status = 'ACCEPTED' or sr.status = 'CANCELLED'
ORDER BY when_created;


DROP VIEW IF EXISTS waiting_requests_unioned_view;
DROP VIEW IF EXISTS staff_waiting_requests_view;
DROP VIEW IF EXISTS advisor_waiting_requests_view;




DROP VIEW IF EXISTS staff_waiting_requests_view;
CREATE OR REPLACE VIEW staff_waiting_requests_view AS
SELECT sr.student_id,
	   sr.request_type_id,
	   rt.request_name,
       sr.current_index,
       sr.information,
       sr.when_created,
       ra.staff_id as current_actor_id,
       sr.addition,
	sr.status
FROM student_requests sr
JOIN request_actors ra ON sr.request_type_id = ra.request_type_id
JOIN request_types rt ON rt.id = sr.request_type_id
WHERE ra.index = sr.current_index AND sr.status = 'WAITING'
ORDER BY sr.when_created;


CREATE OR REPLACE VIEW advisor_waiting_requests_view AS
SELECT sr.student_id,
	   sr.request_type_id,
	   rt.request_name,
       sr.current_index,
       sr.information,
       sr.when_created,
       ts.id as current_actor_id,
       sr.addition,
	sr.status
FROM student_requests sr
JOIN student s ON s.id = sr.student_id
JOIN teaching_staff ts ON s.adviser_id= ts.id
JOIN request_types rt ON rt.id = sr.request_type_id
WHERE sr.current_index=0 AND sr.status = 'WAITING'
ORDER BY sr.when_created;


CREATE OR REPLACE VIEW waiting_requests_unioned_view as
SELECT * FROM staff_waiting_requests_view
UNION
SELECT * FROM advisor_waiting_requests_view;



