# StudentManagementSystem
Creating a student management system which allows you to create students and store them in a db thanks to JAX-RS.

**URL For Insomnia** 
http://localhost:8080/student-management-system/api/v1/students


# Endpoints


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
