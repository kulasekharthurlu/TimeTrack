package com.app.timetrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class TimeTrackBackendApplication {  
	public static void main(String[] args) {
		SpringApplication.run(TimeTrackBackendApplication.class, args);	
		 
		        int[] nums = {2, 3, 4, 5};
		        int[] result = getProductArray(nums);

		        // Printing the product array
		        for (int i = 0; i < result.length; i++) {
		            System.out.print(result[i] + " ");
		        }  
		    

		
		

	}	
	
    public static int[] getProductArray(int[] nums) {
        int[] result = new int[nums.length];

        // Calculate the product of numbers before the current element
        int product = 1;
        for (int i = 0; i < nums.length; i++) {
            result[i] = product;
            product *= nums[i];
        }

        // Calculate the product of numbers after the current element and multiply with the previous product
        product = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= product;
            product *= nums[i];
        }

        return result;
    }
}	

