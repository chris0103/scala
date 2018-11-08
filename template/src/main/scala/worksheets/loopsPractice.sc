package worksheets

object loopsPractice {
	var x = 10                                //> x  : Int = 10
	
	while (x >= 0) {
		println(x)
		x -= 1
	}                                         //> 10
                                                  //| 9
                                                  //| 8
                                                  //| 7
                                                  //| 6
                                                  //| 5
                                                  //| 4
                                                  //| 3
                                                  //| 2
                                                  //| 1
                                                  //| 0
	println("BlastOff!")                      //> BlastOff!
	x = 10
	do {
		println(x)
		x -= 1
	} while (x >= 0)                          //> 10
                                                  //| 9
                                                  //| 8
                                                  //| 7
                                                  //| 6
                                                  //| 5
                                                  //| 4
                                                  //| 3
                                                  //| 2
                                                  //| 1
                                                  //| 0
  println("BlastOff!")                            //> BlastOff!
	for (x <- 10 to 0 by -1) {
		println(x)                        //> 10
                                                  //| 9
                                                  //| 8
                                                  //| 7
                                                  //| 6
                                                  //| 5
                                                  //| 4
                                                  //| 3
                                                  //| 2
                                                  //| 1
                                                  //| 0
	}
	println("BlastOff!")                      //> BlastOff!
	
	var y = for (num <- 1 to 10) yield num + 1//> y  : scala.collection.immutable.IndexedSeq[Int] = Vector(2, 3, 4, 5, 6, 7, 8
                                                  //| , 9, 10, 11)
  var word = "Monday"                             //> word  : String = Monday
  for (x <- 0 until word.length) {
  	println(word(x))                          //> M
                                                  //| o
                                                  //| n
                                                  //| d
                                                  //| a
                                                  //| y
  }
}