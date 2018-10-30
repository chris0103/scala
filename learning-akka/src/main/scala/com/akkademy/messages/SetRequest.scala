package com.akkademy.messages

case class SetRequest(key : String, value : String)
case class GetRequest(Key : String)
case class KeyNotFoundException(key : String) extends Exception
