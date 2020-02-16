/*	Derek Snyder
*	3/8/2018
*	Algorithms HW 4
*
*	I did the bonus problem, but evaluate() won't throw an exception
*	for a variable expression :c
*
*/

public class PostfixTree_test2 {

	public static void TEST(boolean expr) {
		if (!expr) {
			StackTraceElement[] failed = Thread.currentThread().getStackTrace();
			System.out.println("TEST FAILED:");
			/*
			// Print whole call stack
			for (int i=0; i<failed.length; i++) {
				System.out.println(failed[i].toString());
			}
			*/
			// Print bottom of call stack
			System.out.println(failed[failed.length -1].toString());
			//System.exit(-1); //uncomment if you like crashing
		}
	}

	public static void main(String[] args) {
		
		// "1 2 +"
		System.out.println("Testing \"1 2 +\" ...");
		PostfixTree expr1=new PostfixTree();
		expr1.pushNumber(1);
		expr1.pushNumber(2);
		expr1.pushAdd();
		//inorder
		System.out.println("--> inorder:\t" +expr1.inorder());
		//evaluate
		System.out.println("--> evaluate:\t" +expr1.evaluate());
		TEST(expr1.evaluate()==3);
		//height
		System.out.println("--> height:\t" +expr1.height());
		TEST(expr1.height()==1);

		// "1 2 + 3 4 - *"
		System.out.println("\nTesting \"1 2 + 3 4 - *\" ...");
		PostfixTree expr2=new PostfixTree();
		expr2.pushNumber(1);
		expr2.pushNumber(2);
		expr2.pushAdd();
		expr2.pushNumber(3);
		expr2.pushNumber(4);
		expr2.pushSubtract();
		expr2.pushMultiply();
		//inorder
		System.out.println("--> inorder:\t" +expr2.inorder());
		//evaluate
		System.out.println("--> evaluate:\t" +expr2.evaluate());
		TEST(expr2.evaluate()==-3);
		//height
		System.out.println("--> height:\t" +expr2.height());
		TEST(expr2.height()==2);

		// "4 5 + 8.6 33 * -"
		System.out.println("\nTesting \"4 5 + 8.6 33 * -\" ...");
		PostfixTree expr3=new PostfixTree();
		expr3.pushNumber(4);
		expr3.pushNumber(5);
		expr3.pushAdd();
		expr3.pushNumber(8.6);
		expr3.pushNumber(33);
		expr3.pushMultiply();
		expr3.pushSubtract();
		//inorder
		System.out.println("--> inorder:\t" +expr3.inorder());
		//evaluate
		System.out.println("--> evaluate:\t" +expr3.evaluate());
		TEST(expr3.evaluate()==-274.8);
		//height
		System.out.println("--> height:\t" +expr3.height());
		TEST(expr3.height()==2);

		// "1 2 VARIABLE * +"
		System.out.println("\nTesting \"1 2 VARIABLE * +\" ...");
		PostfixTree expr4=new PostfixTree();
		expr4.pushNumber(1);
		expr4.pushNumber(2);
		expr4.pushVariable();
		expr4.pushMultiply();
		expr4.pushAdd();
		//inorder
		System.out.println("--> inorder:\t" +expr4.inorder());
		//evaluate
		System.out.println("--> eval(3):\t" +expr4.evaluate(3));
		TEST(expr4.evaluate(3)==7);
		//height
		System.out.println("--> height:\t" +expr4.height());
		TEST(expr4.height()==2);
	}
}