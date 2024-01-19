package utils;

import org.passay.CharacterRule;  
import org.passay.EnglishCharacterData;  
import org.passay.PasswordGenerator;
import org.passay.CharacterData;
import org.passay.CharacterCharacteristicsRule;

public class GeracaoSenha {
	
	CharacterData specialChars = new CharacterData() {
        public String getErrorCode() {
            return CharacterCharacteristicsRule.ERROR_CODE;
        }

        public String getCharacters() {
            return "!@#$%&*+=?<>:;{}[]";
        }
    };
	
	public String GeradorSenha() {
		// create character rule for lower case  
        CharacterRule LCR = new CharacterRule(EnglishCharacterData.LowerCase);  
        // set number of lower case characters  
        LCR.setNumberOfCharacters(20);  
  
        // create character rule for upper case  
        CharacterRule UCR = new CharacterRule(EnglishCharacterData.UpperCase);  
        // set number of upper case characters  
        UCR.setNumberOfCharacters(20);  
  
        // create character rule for digit  
        CharacterRule DR = new CharacterRule(EnglishCharacterData.Digit);  
        // set number of digits  
        DR.setNumberOfCharacters(20);  
  
        // create character rule for special characters  
        CharacterRule SR = new CharacterRule(specialChars);  
        // set number of special characters  
        SR.setNumberOfCharacters(0);
        
        // create instance of the PasswordGenerator class   
        PasswordGenerator passGen = new PasswordGenerator();  
          
        // call generatePassword() method of PasswordGenerator class to get Passay generated password  
        String password = passGen.generatePassword(60, SR, LCR, UCR, DR);  
        
        return password;
	}

}
