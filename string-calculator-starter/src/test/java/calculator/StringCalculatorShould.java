package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;

class StringCalculatorShould {
	
	 
    @Test
    void empty_string_should_return_0() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(0, stringCalculator.add(""));
    }

	
    @Test
    void string_with_single_number_should_return_number_as_int() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(89, stringCalculator.add("89"));
    }
    
	
    @Test
    void string_with_two_numbers_seperated_by_comma_should_return_sum() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(90, stringCalculator.add("19,71"));
    }
    

    @Test
    void string_with_unknown_amount_of_numbers_seperated_by_comma_should_return_sum() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(852, stringCalculator.add("19,71,0,4,751,7"));
    }
    

    @Test
    void string_with_whitespace_between_numbers_seperated_by_comma_should_return_sum() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(91, stringCalculator.add("19\n,71,1"));
    }
  
    @Test
    void string_having_multiple_negative_numbers_throw_exception_displaying_them() {
        StringCalculator stringCalculator = new StringCalculator();
        Exception x=assertThrows(IllegalArgumentException.class, () -> stringCalculator.add("-4,6,-9,44,-66"));
        assertEquals("Negative Number(s) not allowed. Unaccepted inputs: -4 -9 -66 ", x.getMessage());
    }
    

    @Test
    void test_GetCalledCount() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(6, stringCalculator.GetCalledCount());
    }
    
    @Test
    void numbers_greater_than_thousand_ignored_while_summing() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(22, stringCalculator.add("19,7145,1,1001,1997,2"));
    }
    
    @Test
    void string_of_numbers_seperated_by_custom_delimiter_should_return_sum() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(91, stringCalculator.add("//#\n19#71#1"));
    } 
    
    @Test
    void string_with_variable_number_of_single_delimiter_should_return_sum() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(80, stringCalculator.add("//**\n71**1,8"));
    }
    
    @Test
    void string_with_variable_number_of_multiple_delimiter1_should_return_sum() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(131, stringCalculator.add("//***%%%\n31%%%19***81"));
    } 
    
    @Test
    void string_with_variable_number_of_multiple_delimiter2_should_return_sum() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(176, stringCalculator.add("//$$&&\n17&&141$$18"));
    } 
}
