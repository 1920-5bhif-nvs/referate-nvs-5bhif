# HTTP Smuggling

HTTP Smuggling is a method utilized by hackers to trick secured Proxies in forwarding malicious payloads by attacking certain vulnerabilites within them.

Lets imagine a scenario like this one:

```http
POST /search HTTP/1.1
Host: example.com
Content-Type: application/x-www-form-urlencoded
Content-Length: 10
q=smuggling

```

We have:

- A Post Request
- A specified Host, which is the Server we are speaking to
- a ContentType
- a ContentLength
- a Payload/RequestBody

After looking at this, there seems no way to fool or even spoof this simple methology.
But if it gets more complicated errors start accuring.

We can see this in the following example:

```http
POST /search HTTP/1.1
Host: example.com
Content-Type: application/x-www-form-urlencoded
Content-Length: 51
Transfer-Encoding: zchunked
11
=x&q=smuggling&x=
0
GET /404 HTTP/1.1
Foo: bPOST /search HTTP/1.1
Host: example.com

```

Here we see: 

- A Post request again
- And Transfer-Encoding
  - Transfer-Encoding is another way of ContentLength but more like EOF-Systems, where the end of the conversation is marked.
    In this example as a 0
- And then its start to get weird
  - We have a Get Request after the initial request
    - And inside that an even 