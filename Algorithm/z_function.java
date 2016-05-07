	private List<Integer> z_function(String s) {
		
	    final int size = s.length();
	    final List<Integer> result = new ArrayList<>(size);
	    for ( int i = 0; i < size; ++i ){
	    	result.add( i, 0 );
	    }
	    for (int i = 1, l = 0, r = 0; i < size; ++i) {
	        if (i <= r)
	        	result.set(i, Math.min (r - i + 1, result.get(i - l)));
	        while (i + result.get(i) < size && s.charAt(result.get(i)) == s.charAt( i + result.get(i) ) ){
	        	int value = result.get(i);
	            result.set(i, ++value );
	        }
	        if (i + result.get(i) - 1 > r){
	        	l = i;
	        	r = i + result.get(i) - 1;
	        }
	            
	    }
	    
	    for ( int i : result ){
	    	System.out.print(i);
	    	System.out.print(", ");
	    }
	    System.out.println();
	    
	    return result;
	}
