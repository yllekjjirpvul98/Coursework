public class Calculator {
        Double x;
        
        /*
        * Chops up input on ' ' then decides whether to add or multiply.
        * If the string does not contain a valid format returns null.
        */
        
        public Double x(String x){
        	/*String[] tokens = x.split(" ");
    		this.x = Double.valueOf(tokens[0]);
    		if (tokens[1].equals("+")) {
    			//the input must be of type Double
    			 return this.x(Double.valueOf(tokens[2]));
    		}else if (tokens[1].equals("x")) {
    			//the input need to be of type double
    			return this.x(Double.parseDouble(tokens[2]));
    		}else {
    			return null;
    		}*/
        	return new Double(0);
        }

        /*
        * Adds the parameter x to the instance variable x and returns the answer as a Double.
        */
        public Double x(Double x){
                System.out.println("== Adding ==");
                //add parameter to x
                //x += this.x;
                return x;
        }

        /*
        * Multiplies the parameter x by instance variable x and return the value as a Double.
        */
        public Double x(double x){
                System.out.println("== Multiplying ==");
              //  x = x * this.x;
                return x;
        }

}