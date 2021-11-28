# StudentManagementSystem
Creating a student management system which allows you to create a virtual school with teachers, subjects and students.


**NEW: SampleData added for your convienience**

**URL For Insomnia** 
http://localhost:8080/student-management-system/api/v1/students


# Endpoints for Part 1 of the Project


1. **Create:**
- Create a student: `POST & JSON Body, No URL change`
```
{
	"firstName": "Peter",
	"lastName": "Jorgensen",
	"email": "peterj@iths.se",
	"phoneNumber" : "073123123"
}

{
	"firstName": "Josefin",
	"lastName": "Hovart",
	"email": "jossehov@outlook.com",
	"phoneNumber" : "09827156"
}
```

2. **Read:**
- Get all students: `GET, No URL change`
- Get one student: `GET, /{id}`
- Get Student with lastname: `GET & Query, /lastname`
  - ``lastname     Jorgensen``

3. **Update:**
- Update students firstname: `PATCH & Query, /{id}`
  - ``firstname    Amanda`` 
-  Update all of student: `PUT &  JSON Body, /{id}`
```
{
	"firstName": "Henrik",
	"lastName": "Eklund",
	"email": "Henriklund@gmail.se",
	"phoneNumber" : "88241431225"
}
```

4. **Delete:**
- Delete student: `DELETE, /{id}` 

---------------------------------------------------------------

http://localhost:8080/student-management-system/api/v1/teachers

http://localhost:8080/student-management-system/api/v1/subjects

# Endpoints for Part 2 of the Project

**NEW: SampleData added for your convienience**


**1. Create:**
- Create a Teacher: `POST & JSON Body, No URL Change`
```
{
	"firstName": "Håkan",
	"lastName": "Hellström",
	"email": "håkan@iths.se",
	"phoneNumber" : "195269299"
}
```
- Create a Subject: `POST & JSON Body, No URL Change`
```
{
	"subject" : "Matte"
}
```

**2. Read:**
- Get all teachers: `GET, No URL Change`
- Get all Subjects: `GET, No URL Change`

- Get Teacher(s) by First Name: `GET & Query, /getAllByFirstName `
	- ``firstName		Pontus`` 
- Get Subject by Name: `GET & Query, /getAllByName`
	- ``subjectName	Geography`` 


- Get Teacher by Id: `GET, /{id}`
- Get Subject by Id: `GET, /{id}`

**3. Update:**
- Add Teacher to a Subject: `POST, {subjectId}/addteacher/{teacherId}`

- Add Student to a Subject: `POST, {subjectId}/addstudent/{studentId}`


**4. Delete**
- Delete teacher: `DELETE, /{id}`
- Delete Subject: `DELETE, /{id}`

- Delete Teacher from a Subject: `POST, {subjectId}/deleteteacher/{teacherId}`
- Delete Student from a Subject: `POST, {subjectId}/deletestudent/{studentId}`
