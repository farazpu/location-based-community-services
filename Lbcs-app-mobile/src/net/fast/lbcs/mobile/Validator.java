package net.fast.lbcs.mobile;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import net.fast.lbcs.data.entities.admin.item.Validation;

public class Validator {
	
	public static boolean isNumeric(String str){
		try{
			Double.parseDouble(str);
		}catch (Exception e){
			return false;
		}
		return true;
	}
	
	public static boolean isValidRegex(String regex){
	    try {
	        Pattern.compile(regex);
	    } catch (PatternSyntaxException e) {
	        return false;
	    }
	    return true;
	}

	public static String validate(String validationId, String value, String attr){
		List<Validation> validations = CurrentServiceInfo.getValidations();
		if(validationId.equals("None"))
			return "true";
		Validation v = new Validation();
		for(Validation validation : validations){
			if(validation.getId().equals(validationId))
				v=validation;
		}
		
		if(v.getType().equals("Numeric Equal")){
			if(isNumeric(v.getParams().get(0)) && isNumeric(value) ){
				if(Double.parseDouble(v.getParams().get(0)) == Double.parseDouble(value)){
					return "true";
				} else{
					return attr + " must be equal to " + v.getParams().get(0);
				}
			}else{
				return "values must be numeric";
			}
		}
		
		if(v.getType().equals("Numeric Greater")){
			if(isNumeric(v.getParams().get(0)) && isNumeric(value) ){
				if(Double.parseDouble(v.getParams().get(0)) < Double.parseDouble(value)){
					return "true";
				} else{
					return attr + " must be greater than " + v.getParams().get(0);
				}
			}else{
				return "values must be numeric";
			}
		}
		
		if(v.getType().equals("Numeric Smaller")){
			if(isNumeric(v.getParams().get(0)) && isNumeric(value) ){
				if(Double.parseDouble(v.getParams().get(0)) > Double.parseDouble(value)){
					return "true";
				} else{
					return attr + " must be smaller than " + v.getParams().get(0);
				}
			}else{
				return "values must be numeric";
			}
		}
		
		if(v.getType().equals("Numeric Between")){
			if(isNumeric(v.getParams().get(0)) && isNumeric(v.getParams().get(1)) && isNumeric(value) ){
				if((Double.parseDouble(v.getParams().get(0)) > Double.parseDouble(value) && 
						Double.parseDouble(v.getParams().get(1)) < Double.parseDouble(value)) ||
						(Double.parseDouble(v.getParams().get(0)) < Double.parseDouble(value) && 
						Double.parseDouble(v.getParams().get(1)) > Double.parseDouble(value))){
					return "true";
				} else{
					return attr + " must be between " + v.getParams().get(0) + " and " + v.getParams().get(1);
				}
			}else{
				return "values must be numeric";
			}
		}
		
		if(v.getType().equals("Numeric GreaterEqual")){
			if(isNumeric(v.getParams().get(0)) && isNumeric(value) ){
				if(Double.parseDouble(v.getParams().get(0)) <= Double.parseDouble(value)){
					return "true";
				} else{
					return attr + " must be greater or equal to " + v.getParams().get(0);
				}
			}else{
				return "values must be numeric";
			}
		}
		
		if(v.getType().equals("Numeric LesserEqual")){
			if(isNumeric(v.getParams().get(0)) && isNumeric(value) ){
				if(Double.parseDouble(v.getParams().get(0)) >= Double.parseDouble(value)){
					return "true";
				} else{
					return attr + " must be smaller or equal to " + v.getParams().get(0);
				}
			}else{
				return "values must be numeric";
			}
		}
		
		if(v.getType().equals("Numeric NotEqual")){
			if(isNumeric(v.getParams().get(0)) && isNumeric(value) ){
				if(Double.parseDouble(v.getParams().get(0)) != Double.parseDouble(value)){
					return "true";
				} else{
					return attr + " must not be equal to " + v.getParams().get(0);
				}
			}else{
				return "values must be numeric";
			}
		}
		
		if(v.getType().equals("Numeric BetweenInclusive")){
			if(isNumeric(v.getParams().get(0)) && isNumeric(v.getParams().get(1)) && isNumeric(value) ){
				if((Double.parseDouble(v.getParams().get(0)) >= Double.parseDouble(value) && 
						Double.parseDouble(v.getParams().get(1)) <= Double.parseDouble(value)) ||
						(Double.parseDouble(v.getParams().get(0)) <= Double.parseDouble(value) && 
						Double.parseDouble(v.getParams().get(1)) >= Double.parseDouble(value))){
					return "true";
				} else{
					return attr + " must be between " + v.getParams().get(0) + " and " + v.getParams().get(1) + " inclusive";
				}
			}else{
				return "values must be numeric";
			}
		}
		
		if(v.getType().equals("Numeric Positive")){
			if(isNumeric(value) ){
				if(Double.parseDouble(value) > 0){
					return "true";
				} else{
					return attr + " must be positive";
				}
			}else{
				return "values must be numeric";
			}
		}
		
		if(v.getType().equals("Numeric Negative")){
			if(isNumeric(value) ){
				if(Double.parseDouble(value)<0){
					return "true";
				} else{
					return attr + " must be negative";
				}
			}else{
				return "values must be numeric";
			}
		}
		
		if(v.getType().equals("Numeric NonNegative")){
			if(isNumeric(value) ){
				if(Double.parseDouble(value)>=0){
					return "true";
				} else{
					return attr + " must be non-negative";
				}
			}else{
				return "values must be numeric";
			}
		}
		
		if(v.getType().equals("String StartsWith")){
			if(value != null ){
				if(value.startsWith(v.getParams().get(0))){
					return "true";
				} else{
					return attr + " must start with " + v.getParams().get(0);
				}
			}else{
				return "null value detected";
			}
		}
		
		if(v.getType().equals("String EndsWith")){
			if(value != null ){
				if(value.endsWith(v.getParams().get(0))){
					return "true";
				} else{
					return attr + " must end with " + v.getParams().get(0);
				}
			}else{
				return "null value detected";
			}
		}
		
		if(v.getType().equals("String Equals")){
			if(value != null ){
				if(value.equals(v.getParams().get(0))){
					return "true";
				} else{
					return attr + " must be equal to " + v.getParams().get(0);
				}
			}else{
				return "null value detected";
			}
		}
		
		if(v.getType().equals("String ContainsWith")){
			if(value != null ){
				if(value.contains(v.getParams().get(0))){
					return "true";
				} else{
					return attr + " must contain " + v.getParams().get(0);
				}
			}else{
				return "null value detected";
			}
		}

		if(v.getType().equals("String LengthEquals")){
			if(value != null && isNumeric(v.getParams().get(0))){
				if(value.length() == Double.parseDouble(v.getParams().get(0))){
					return "true";
				} else{
					return attr + " length must be equal to " + v.getParams().get(0);
				}
			}else{
				return "null value detected";
			}
		}
		
		if(v.getType().equals("String LengthGreater")){
			if(value != null && isNumeric(v.getParams().get(0))){
				if(value.length() > Double.parseDouble(v.getParams().get(0))){
					return "true";
				} else{
					return attr + " length must be greater than " + v.getParams().get(0);
				}
			}else{
				return "null value detected";
			}
		}
		
		if(v.getType().equals("String LengthLess")){
			if(value != null && isNumeric(v.getParams().get(0))){
				if(value.length() < Double.parseDouble(v.getParams().get(0))){
					return "true";
				} else{
					return attr + " length must be less than " + v.getParams().get(0);
				}
			}else{
				return "null value detected";
			}
		}
		
		if(v.getType().equals("String LengthBetween")){
			if(value != null && isNumeric(v.getParams().get(0)) && isNumeric(v.getParams().get(1))){
				if((value.length() > Double.parseDouble(v.getParams().get(0)) && value.length() < Double.parseDouble(v.getParams().get(1)))
						|| (value.length() < Double.parseDouble(v.getParams().get(0)) && value.length() > Double.parseDouble(v.getParams().get(1))))
				{
					return "true";
				} else{
					return attr + " length must be between " + v.getParams().get(0) + " and " + v.getParams().get(1);
				}
			}else{
				return "null value detected";
			}
		}
		
		
		if(v.getType().equals("Logical And")){
			if(value != null){
				if(validate(v.getParams().get(0), value, attr).equals("true") && 
						validate(v.getParams().get(1), value, attr).equals("true") ){
					return "true";
				} else{
					return attr + " validation Failed ";
				}
			}else{
				return "null value detected";
			}
		}
		
		if(v.getType().equals("Logical Or")){
			if(value != null){
				if(validate(v.getParams().get(0), value, attr).equals("true") || 
						validate(v.getParams().get(1), value, attr).equals("true") ){
					return "true";
				} else{
					return attr + " validation Failed ";
				}
			}else{
				return "null value detected";
			}
		}

		if(v.getType().equals("Logical Not")){
			if(value != null){
				if(!(validate(v.getParams().get(0), value, attr).equals("true"))){
					return "true";
				} else{
					return attr + " validation Failed ";
				}
			}else{
				return "null value detected";
			}
		}
		
		
		if(v.getType().equals("String LengthGreater")){
			if(value != null && isValidRegex(v.getParams().get(0))){
				if(value.matches(v.getParams().get(0))){
					return "true";
				} else{
					return attr + " length must match " + v.getParams().get(0);
				}
			}else{
				return "null value detected";
			}
		}
		
		
		return "";
	}

}
