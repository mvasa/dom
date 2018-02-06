(defn mybuildbst [list1]
		
	(cond	(< (second list1) (first list1))
			(if (> (second(rest list1)) (first list1))
				(cons (first list1) (cons(conj (conj [(second list1)] nil) nil)
				(cons(conj (conj [(second(rest list1))] nil) nil)(rest (rest(rest list1))))))	
			)	
			
			
		(> (second list1) (first list1))
			(if (< (second(rest list1)) (first list1))
				(cons (first list1) (cons(conj (conj [(second(rest list1))] nil) nil)
				(cons(conj (conj [(second list1)] nil) nil)(rest (rest(rest list1))))))	
			
			)
			

	
	)
	
															
)


(cons (cons (first list1) (cons (cons (second list1) (conj(conj[(second (rest list1))] nil)nil)))) nil)
	