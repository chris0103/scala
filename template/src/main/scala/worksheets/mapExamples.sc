package worksheets

object mapExamples {

	var groceries = Map(1 -> "milk", 2 -> "bread", 3 -> "juice", 4 -> "eggs")
                                                  //> groceries  : scala.collection.immutable.Map[Int,String] = Map(1 -> milk, 2 -
                                                  //| > bread, 3 -> juice, 4 -> eggs)
 	groceries = groceries + (5 -> "hashbrown")
 	groceries.get(3)                          //> res0: Option[String] = Some(juice)
	groceries(3)                              //> res1: String = juice
	groceries.getOrElse(6, "No match")        //> res2: String = No match
	
	for (v <- groceries.values)
		println(v)                        //> hashbrown
                                                  //| milk
                                                  //| bread
                                                  //| juice
                                                  //| eggs
  var z = for ((k, v) <- groceries) yield (v, k)  //> z  : scala.collection.immutable.Map[String,Int] = Map(eggs -> 4, milk -> 1, 
                                                  //| juice -> 3, bread -> 2, hashbrown -> 5)
 	groceries.foreach(println)                //> (5,hashbrown)
                                                  //| (1,milk)
                                                  //| (2,bread)
                                                  //| (3,juice)
                                                  //| (4,eggs)
	
	
	
}