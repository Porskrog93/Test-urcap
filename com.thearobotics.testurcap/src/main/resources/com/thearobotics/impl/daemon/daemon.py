#!/usr/bin/env python

import time
import sys

import xmlrpclib
from SimpleXMLRPCServer import SimpleXMLRPCServer

title = ""

def hello_world():

	return "Hello World!"


sys.stdout.write("MyDaemon daemon started")
sys.stderr.write("MyDaemon daemon started")

server = SimpleXMLRPCServer(("127.0.0.1", 40405))
server.register_function(hello_world, "hello_world")
server.serve_forever()