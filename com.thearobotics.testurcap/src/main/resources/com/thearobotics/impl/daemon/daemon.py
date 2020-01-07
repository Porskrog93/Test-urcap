#!/usr/bin/env python

import xmlrpclib
from SimpleXMLRPCServer import SimpleXMLRPCServer

def hello_world():
	return "Hello World"

server = SimpleXMLRPCServer(("127.0.0.1", 40405))
server.register_function(hello_world, "hello_world")
server.serve_forever()