    static long stringSimilarity(String str) {
       	    final int size = str.length();
	    final List<Integer> result = new ArrayList<>(size);
	    for ( int i = 0; i < size; ++i ){
	    	result.add( i, 0 );
	    }
	    for (int i = 1, l = 0, r = 0; i < size; ++i) {
		if (i <= r){
			int value = Math.min (r - i + 1, result.get(i - l));
			result.set(i, value );
		}
		while (i + result.get(i) < size && str.charAt(result.get(i)) == str.charAt( i + result.get(i) ) ){
			int value = result.get(i);
		    result.set(i, ++value );
		}
		if (i + result.get(i) - 1 > r){
			l = i;
			r = i + result.get(i) - 1;
		}     
	    }
	    
	    long answer = str.length();
	    for ( int i : result ){
	    	answer += i;
	    }
	    
	    return answer;
    }
