# Introduction to RESTful services
Welcome, we will be talking about RESTful web services and HTTP protocol.

```bash
git clone https://github.com/siucacm/WorkshopFour
chmod u+x getjoke.sh
```

Run it

```bash
./getjoke.sh
```

This was possible due to many factors one of which is Representational state transfer.

<hr>

## REST
REST stands for Representational State Transfer. It is an architectural style to be used for creating web services. With a RESTful web service, you can exchange information with a computer system on the internet.

REST was designed by Roy Fielding in his PhD dissertation. He designed it with the following architectural style constraints:
* Client-Server architectural style
* Stateless
* Cacheable
* Layered system
* Uniform interface
* Code on demand (optional)

<hr>

## HTTP
HTTP stands for Hypertext Transfer Protocol, it is the base of all data communication for internet. We will be using it to help communicate with RESTful services.

HTTP has many request methods to define the action that will be performed. Four of them are the most important.
Important methods:
* GET : Request a resource
* HEAD : Request a resource but no body
* PUT/POST : Adding a resource
* DELETE : Delete a resource

There are some arguments between PUT and POST about which one is used for what :)

Each HTTP request comes with a status code, we are going to focus on 2 of them: 
1. 200 = Okay
2. 404 = Not found

Anything in the 400s is client side error
Anything in the 500s is server side error

<hr>

```bash
GET http://stackoverflow.com/

```

Now check a summary

```bash
HEAD http://stackoverflow.com/
```

Try any random website and see what you get.


These are websites but there has to be some sort of an Application program interfaces to used

## API
API : Set of protocols,tools for building software. Java API (Sounds familiar?)

```java
//The below uses javas api
System.out.println("Hello World");
```

<hr>
##  RESTful API's

Fetch resources using HTTP

Type in browser:
http://quotes.rest/qod.json?category=inspire

Keep on fully refreshing the page.
After a while, you will get what is called a rate limit.
This prevents people for misusing restful services.

Lets have a look at the API docs here [theysaidso.com](https://theysaidso.com/api/#qod)


```bash
#Run script below
./getip.sh
```

You just made GET requests to ipify's API requesting a resource.
You may have noticed all of this objects we get are wrapped in braces etc


#### ITS JSON :)

<hr>

### JSON
JavaScript Object Notation is a data-interchange format.
Its like a dictionary for those familiar with python.
Learn more at [JSON.org](http://www.json.org/)

<hr>

### Chuck Norris Joke API

To get an understanding on how REST works, we will be looking into the Chuck Norris Joke RESTful API. We can take a look at the API here: https://api.chucknorris.io/.

Let's do the first command listed on the site:

```bash
GET https://api.chucknorris.io/jokes/random
```

We see that a JSON object is outputted.

#### Testing in front end web development

The below html script gets a joke from the chuck norris api:

```html
<!DOCTYPE html>
<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	</head>
	<body>
		<button type="submit" onclick="getchucknorrisjoke()" >Chuck Norris Joke</button>
		<p id="joke"></p>
		<script>
			function getchucknorrisjoke(){
				var getJoke = $.ajax({
					type: 'GET',
					url: 'https://api.chucknorris.io/jokes/random?category=food',
					dataType: 'json',
					timeout: '3000'
				});
				
				getJoke.done(function(response){
					$('#joke').html(response.value)
				});
			}
		</script>
	</body>
</html>
```


### Authentication
```
GET http://api.wordnik.com/v4/words.json/wordOfTheDay
```

Sometimes, you need an <b> API KEY </b> to make requests

<b> Never leave your API KEY hanging about in a publically accessible domain. Its dangerous :) </b>

Instead,export it as an environment variable or throw it in a config file.

```bash
export KEY="secretkey"

echo $KEY
```

<hr>

Sometimes, it can be hard to read a gigantic response like here:

```bash
GET http://api.wordnik.com/v4/words.json
```

We have some tools for that

```bash
#Python ships with a tool to pretty print json object
#The line is a pipe which pipes output of command 1 to input of command 2
GET http://api.wordnik.com/v4/words.json | python -m json.tool
```

Notice how its still a large object.
Good guy UNIX ships with some tools that can be handy:

```bash
#Go through file slowly
GET http://api.wordnik.com/v4/words.json | python -m json.tool | less 

#head and tail are your friends
```

<hr>

### Lets get even more hands on

Lets POST some data!
Firebase (Backend as a service) allows us to have a database which can be modified through HTTP.

```bash
GET https://rest-1663a.firebaseio.com/.json | python -m json.tool

###POST https://rest-1663a.firebaseio.com/.json
### Now enter your object as {"NAME":"LANGUAGE PROFICIENT IN}
### Press CTRL D and give it a couple of seconds.
### Something should pop up in your screen

GET https://rest-1663a.firebaseio.com/.json

#You should see your data

```
<b>You can modify authentication to change options on who can get and post data. </b>

A small practical use
```bash

java -jar APIGetReq.jar
#Type in http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en

```

Want to learn more? Have a look at the source code and tweak it to display any json object.

<hr>
