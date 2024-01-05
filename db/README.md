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
CREATE TABLE teaching_staff (
    id SERIAL PRIMARY KEY,
    staff_name TEXT,
    surname TEXT,
    email TEXT,
    department_id INT,
    role TEXT,
    isDean BOOLEAN,
    isViceDean BOOLEAN,
    isDepartmentHead BOOLEAN,
    isViceDepartmentHead BOOLEAN,
    password TEXT,
    CONSTRAINT fk_bolumid
        FOREIGN KEY (department_id)
            REFERENCES department(department_id)
);
```

## Course Table

```sql
CREATE TABLE course (
    course_id SERIAL PRIMARY KEY,
    course_name TEXT,
    course_code TEXT
);
```

## Branch Table

```sql
CREATE TABLE branch (
    branch_id SERIAL PRIMARY KEY,
    branch_number INT,
    course_id INT,
    instructor_id INT,
    assistant_id INT,
    quota INT
);
```

## Branch Records Table

```sql
CREATE TABLE branch_records (
    record_id SERIAL PRIMARY KEY,
    branch_id INT,
    student_id INT,
    current_status TEXT,
    CONSTRAINT fk_branch_id
        FOREIGN KEY(branch_id)
            REFERENCES branch(branch_id),
    CONSTRAINT fk_student_id
        FOREIGN KEY(student_id)
            REFERENCES student(student_id)
);
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
CREATE TABLE request (
    request_id SERIAL PRIMARY KEY,
    student_adviser_id INT,
    student_id INT,
    request_type_id INT,
    information TEXT,
    addition TEXT,
    current_index INT,
    CONSTRAINT fk_type
        FOREIGN KEY (request_type_id)
            REFERENCES request_types(id),
    CONSTRAINT fk_studentid
        FOREIGN KEY (student_id)
            REFERENCES student(student_id),
    CONSTRAINT fk_adviser
        FOREIGN KEY (student_adviser_id)
            REFERENCES teaching_staff(id)
);
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
CREATE TABLE request_actors (
    id SERIAL PRIMARY KEY,
    request_type_id INT,
    staff_id INT,
    index INT
);
```

## Request Requirements Table

```sql
CREATE TABLE request_requirements (
    requirement_id SERIAL PRIMARY KEY,
    name TEXT,
    type TEXT,
    request_type_id INT,
    index INT
);
```

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
CREATE VIEW requestRequirements AS
    SELECT rt.id AS requestid, rt.request_name, rr.name, rr.type, rr.index
    FROM request_types rt
    JOIN request_requirements rr ON rt.id = rr.request_type_id;
```

## Student Request Path View

```sql
-- View to show student request path
CREATE VIEW studentRequestPath AS
    SELECT s.name AS student_name, s.student_id AS student_id, rt.request_name AS request_name,
        ts.staff_name AS adviser_name, ts.id AS adviser_id, ts2.id AS actor_id,
        ts2.staff_name AS actor_name, ra.index AS actor_index
    FROM request r
    JOIN student s ON r.student_id = s.student_id
    JOIN teaching_staff ts ON s.adviser_id = ts.id
    JOIN request_types rt ON rt.id = r.request_type_id
    JOIN request_actors ra ON ra.request_type_id = rt.id
    JOIN teaching_staff ts2 ON ra.staff_id = ts2.id
    ORDER BY s.student_id, ra.index;
```

## Course, Branch, and Student List View

```sql
-- View to show course, branch, and student list
CREATE VIEW courseBranchStudentList AS
    SELECT c.course_code, b.branch_number, s.student_id, s.surname, s.name
    FROM branch b
    JOIN course c ON b.course_id = c.course_id
    JOIN branch_records br ON br.branch_id = b.branch_id
    JOIN student s ON s.student_id = br.student_id
    ORDER BY b.branch_id;
```

## Users View

```sql
-- View to show users
CREATE VIEW Users AS
    SELECT ts.staff_name, ts.id, ts.role AS staff_role, s.name, s.student_id
    FROM student s
    FULL OUTER JOIN teaching_staff ts ON s.email = ts.email;
```