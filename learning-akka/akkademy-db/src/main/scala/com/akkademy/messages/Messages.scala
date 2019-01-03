package com.akkademy.messages

case class SetRequest(key : String, value : Object)
case class SetIfNotExists(key : String, value : Object)
case class GetRequest(Key : String)
case class Delete(Key : String)
case class KeyNotFoundException(key : String) extends Exception
