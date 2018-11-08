package worksheets

object arrayExample {

	var nums = new Array[Int](10)             //> nums  : Array[Int] = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
	var furniture = Array("chair", "sofa", "table", "bed")
                                                  //> furniture  : Array[String] = Array(chair, sofa, table, bed)
 	for (f <- furniture)
 		println(f)                        //> chair
                                                  //| sofa
                                                  //| table
                                                  //| bed
	println(furniture(0))                     //> chair

	var a = Array(1, 2, 3, 4, 5)              //> a  : Array[Int] = Array(1, 2, 3, 4, 5)
	var result = for (n <- a) yield n * 2     //> result  : Array[Int] = Array(2, 4, 6, 8, 10)
	var even = for (n <- a if n % 2 == 0) yield n
                                                  //> even  : Array[Int] = Array(2, 4)
}