# WorkshopFour
Welcome, we will be talking about RESTful web services and API's in general

```bash
git clone https://github.com/siucacm/WorkshopFour
chmod u+x getjoke.sh
```

Run it

```bash
./getjoke.sh
```

This was possible due to many factors one of which is Representational state transfer.

##REST 
REST is an architectural style and is heavily used by web services to communicate.
Designed by Roy Fielding in his PhD dissertation.
It has the following constraints:
1. Client-Server architectural style
2. Stateless
3. Cacheable
4. Layered system
5. Uniform interface
6. Code on demand (optional)

##HTTP
HTTP is a protocol.
Famous methods:
* GET : Request a resource
* HEAD : Request a resource but no body
* PUT/POST : Adding a resource
* DELETE : Delete a resource

There are some arguments between PUT and POST about which one is used for what :)

```bash
GET http://stackoverflow.com/

```

Now check a summary

```bash
HEAD http://stackoverflow.com/
```

These are websites but there has to be some sort of an Application program interfaces to used

API : Set of protocols,tools and protocols for building software. Java API (Sounds familiar?)


##RESTful API's
Fetch resources ussing HTTP
