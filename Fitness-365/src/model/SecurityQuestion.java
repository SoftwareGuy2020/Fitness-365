package model;

public enum SecurityQuestion {
	DOG("What was your first dog's name?"), MOTHER("What is your mother's maiden name?"), JOB("In what town or city was your first full time job?")
	, LICENSE("What are the last five digits of your driver's licence number?"), OLDEST_CHILD("What is the middle name of your oldest child?")
	, ADDRESS("What was the house number and street name you lived in as a child?"), SCHOOL("What primary school did you attend?")
	, PARTNERS_MOTHER(" What is your spouse or partner's mother's maiden name?"), PARENTS_MET("In what town or city did your mother and father meet?")
	, PHONE("What were the last four digits of your childhood telephone number?");
	
	private String mQuestion;
	private static int numOfQuestions = 10;
	
	private SecurityQuestion(String s) {
		this.mQuestion = s;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.mQuestion;
	}
	
	public static String[] getAllQuestions() {
		String[] results = new String[numOfQuestions];
		int i = 0;
		for (SecurityQuestion q : SecurityQuestion.values()) 
			results[i++] = q.mQuestion;		
		return results;
	}
	
	public static SecurityQuestion parseInt(int selection) {
		
		switch (selection) {		
		case 1:
			return SecurityQuestion.DOG;
		case 2:
			return SecurityQuestion.MOTHER;
		case 3:
			return SecurityQuestion.JOB;
		case 4:
			return SecurityQuestion.LICENSE;
		case 5:
			return SecurityQuestion.OLDEST_CHILD;
		case 6:
			return SecurityQuestion.ADDRESS;
		case 7:
			return SecurityQuestion.SCHOOL;
		case 8:
			return SecurityQuestion.PARTNERS_MOTHER;
		case 9:
			return SecurityQuestion.PARENTS_MET;
		default:
			return SecurityQuestion.PHONE;
		}		
	}
}
