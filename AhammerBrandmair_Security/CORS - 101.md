## CORS - 101

[Access-Control-Allow-Origin](https://portswigger.net/web-security/cors/access-control-allow-origin): https://example.com

[Access-Control-Allow-Origin](https://portswigger.net/web-security/cors/access-control-allow-origin) - Allows a single, multiple or all Webservers to access our Webserver via REST and set off calls

https://example.com - the Domain of the allowed Server, can use Wildcards though only * (Subdomains)

*Additional Headers*

```http
Access-Control-Allow-Headers: X-PINGOTHER, Content-Type
Access-Control-Allow-Methods: POST, GET
Access-Control-Max-Age: 86400
```

**Credentials**

Access-Control-Allow-Credentials: true

This header means, Cookies and other Credentials are accessable by the site specified in the 

Access-Allow-Origin Header.

**Multiple Trusted Servers**

If we look back at the header we can see only one resource specified which is beeing allowed.

[Access-Control-Allow-Origin](https://portswigger.net/web-security/cors/access-control-allow-origin): https://example.com

But what if we need more?
In the original w3 specification it says, they only need to be seperated by a space like:

[Access-Control-Allow-Origin](https://portswigger.net/web-security/cors/access-control-allow-origin): https://example.com https://example2.com 

The Problem

*No Browser actually implemented this* so we went on to using wildcards.

And the only one available is "*\**", you might be familiar with the following Header

[Access-Control-Allow-Origin](https://portswigger.net/web-security/cors/access-control-allow-origin): *

This actually just says allow everything, which is a reasonable expectations for an API Server serving other Servers.

And its not really dangerous, as you **can't** use Credentials when using any "*" inside the allowed Origin.

```markdown
~~Access-Control-Allow-Credentials: true
```

As a result of these limitations, **many servers programmatically generate the Access-Control-Allow-Origin header based on the user-supplied Origin value**. This is the single most common CORS vulnerability. If you see a HTTP response with any Access-Control-* headers but no origins declared, this is a strong indication that the server will generate the header based on your input. Other servers will only send CORS headers if they receive a request containing the Origin header, making associated vulnerabilities extremely easy to miss.

Here comes the catch, there is another which allowes all sources aswell **and** the Credentails can be used aswell.

```http
Access-Control-Allow-Origin: null
or
Access-Control-Allow-Origin: list-or-null
```



**What is null?**

****

**Null** is specified as allowing all Origins, aswell as local files. Which opens up another whole lot of Vulnerabilites by itself.

For instance:

- We have a site called https://www.mysite.com

  - mysite.com uses `Access-Allow-Credentails: null` to allow all other Sites accessing my resources.
  - it also uses Cookies for sessions and credential storage

- Setup of Malicious Site

  - www.badsite.com uses I-Frames in sandbox to show www.mysite.com

  - ```html
    <iframe sandbox="allow-scripts allow-top-navigation allow-forms" src='data:text/html,<script>*site stuff here*</script>’>
    </iframe>
    ```

- The Attack

  - By allowing all resources **and** on top all local files (which include I-Frames in sandbox mode), we can get the cookies and send them to the Server, allowing access.

  - And the server probably has an endpoint showing user credentials, and sending us a handy dandy JSON Response with the data we want

  - ``````js
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "https:/www.mysite.com/user", true);
    xhttp.withCredentials = true;
    xhttp.send();
    ``````