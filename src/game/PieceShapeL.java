package game;

public class PieceShapeL extends PieceShape {
	private static final char[][][] shapes  = { 
		       { 
		    	   { '0', '2', '0', '0' }, 
		    	   { '0', '2', '0', '0' }, 
		    	   { '0', '2', '2', '0' },
		    	   { '0', '0', '0', '0' } 
			   }, 
		       {    
		    	   { '0', '0', '2', '0' },
		    	   { '2', '2', '2', '0' },
		    	   { '0', '0', '0', '0' },
		    	   { '0', '0', '0', '0' }
			   }, 
		       {    
		    	   { '2', '2', '0', '0' },
		    	   { '0', '2', '0', '0' },
		    	   { '0', '2', '0', '0' },
		    	   { '0', '0', '0', '0' }
			   }, 
		       {   
		    	   { '2', '2', '2', '0' },
		    	   { '2', '0', '0', '0' },
		    	   { '0', '0', '0', '0' },
		    	   { '0', '0', '0', '0' }
			   } };
			
			public PieceShapeL () {
				super();
			}	

			@Override
			protected char[][][] getShapes() {
				return shapes;
			}
}
