public class PostfixTree_tester {

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
		
		
		// Test basic operations
		PostfixTree testTree_add = new PostfixTree();
		testTree_add.pushNumber(1);
		testTree_add.pushNumber(2);
		testTree_add.pushAdd();
		TEST(testTree_add.evaluate()==3.0);

		PostfixTree testTree_multiply = new PostfixTree();
		testTree_multiply.pushNumber(2);
		testTree_multiply.pushNumber(2);
		testTree_multiply.pushMultiply();
		TEST(testTree_multiply.evaluate()==4.0);

		PostfixTree testTree_subtract = new PostfixTree();
		testTree_subtract.pushNumber(3);
		testTree_subtract.pushNumber(2);
		testTree_subtract.pushSubtract();
		TEST(testTree_subtract.evaluate()==1.0);

		PostfixTree testTree_divide = new PostfixTree();
		testTree_divide.pushNumber(3);
		testTree_divide.pushNumber(2);
		testTree_divide.pushDivide();
		TEST(testTree_divide.evaluate()==3.0/2.0);

		// Test multiple operations: "1 2 + 3 4 - *" should be -3
		PostfixTree testTree_moar = new PostfixTree();
		testTree_moar.pushNumber(1);
		testTree_moar.pushNumber(2);
		testTree_moar.pushAdd();
		testTree_moar.pushNumber(3);
		testTree_moar.pushNumber(4);
		testTree_moar.pushSubtract();
		testTree_moar.pushMultiply();
		TEST(testTree_moar.evaluate()==-3);

		// Test height: "1 2 + 3 4 - *" should be 2
		PostfixTree testTree_deep = new PostfixTree();
		testTree_deep.pushNumber(1);
		testTree_deep.pushNumber(2);
		testTree_deep.pushAdd();
		testTree_deep.pushNumber(3);
		testTree_deep.pushNumber(4);
		testTree_deep.pushSubtract();
		testTree_deep.pushMultiply();
		TEST(testTree_deep.height()==2);

		// Test height again: "1 2 +" should be 1
		PostfixTree testTree_2deep4me = new PostfixTree();
		testTree_2deep4me.pushNumber(1);
		testTree_2deep4me.pushNumber(2);
		testTree_2deep4me.pushAdd();
		TEST(testTree_2deep4me.height()==1);

		// Test variable: "1 2 VARIABLE * +"
		PostfixTree testTree_var = new PostfixTree();
		testTree_var.pushNumber(1);
		testTree_var.pushNumber(2);
		testTree_var.pushVariable();
		testTree_var.pushMultiply();
		testTree_var.pushAdd();
		TEST(testTree_var.evaluate(3)==7);
		
		// Test inorder traversal 1
		PostfixTree testTree_inorder = new PostfixTree();
		testTree_inorder.pushNumber(1);
		testTree_inorder.pushNumber(2);
		testTree_inorder.pushAdd();
		testTree_inorder.pushNumber(3);
		testTree_inorder.pushNumber(4);
		testTree_inorder.pushSubtract();
		testTree_inorder.pushMultiply();
		System.out.println(testTree_inorder.inorder());

		// Test inorder traversal 2 "1 2 VARIABLE * +"
		PostfixTree testTree_var2 = new PostfixTree();
		testTree_var2.pushNumber(1);
		testTree_var2.pushNumber(2);
		testTree_var2.pushVariable();
		testTree_var2.pushMultiply();
		testTree_var2.pushAdd();
		System.out.println(testTree_var2.inorder());
		
	}
}