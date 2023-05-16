package com.alpergayretoglu.online_student_election.constants;

public class ApplicationMessages {
    private ApplicationMessages() {
    }

    // MAIN MESSAGES
    public static final String LOGIN_SUCCESS = "Login Success";
    public static final String LOGIN_FAIL_INCORRECT_FIELDS = "You have entered some of the fields incorrectly";

    public static final String LOGOUT_BEFORE = "You are about to logout, if you are sure you want to do this, press OK";

    public static final String ELECTION_CREATE_SUCCESS = "Election has been created";
    public static final String ELECTION_CREATE_FAIL_MISSING_PARAMETERS = "Please enter all the expected parameters";
    public static final String ELECTION_CREATE_FAIL_START_DATE_AFTER_END_DATE = "Start date cannot come after the end date of an election";
    public static final String ELECTION_CREATE_FAIL_START_DATE_BEFORE_CURRENT_DATE = "Start date cannot come before the current date";
    public static final String ELECTION_CREATE_FAIL_NAME_ALREADY_EXISTS = "An election with this name already exists";

    public static final String ANNOUNCEMENT_EMAIL_SEND_SUCCESS = "The emails have been sent successfully";

    public static final String SELECT_COUNCIL_PRESIDENT_SUCCESS = "President will now be visible in the election results page";

    public static final String RE_ELECTION_CREATE_SUCCESS = "New election has been confirmed";
    public static final String RE_ELECTION_CREATE_FAIL_MISSING_PARAMETERS = "Please enter all the expected parameters";
    public static final String RE_ELECTION_CREATE_FAIL_START_DATE_AFTER_END_DATE = "Start date cannot come after the end date of an election";
    public static final String RE_ELECTION_CREATE_FAIL_START_DATE_BEFORE_CURRENT_DATE = "Start date cannot come before the current date";

    public static final String ELECTION_END_SUCCESS = "The election has ended, and the results have been announced";

    public static final String CANDIDATE_APPLICATION_SUBMIT_SUCCESS = "Submission was successful";
    public static final String CANDIDATE_APPLICATION_STATUS_ACCEPTED = "Your application has been accepted; you are a candidate now";
    public static final String CANDIDATE_APPLICATION_SUBMIT_FAIL_MISSING_PARAMETERS = "Please enter all the expected parameters";
    public static final String CANDIDATE_APPLICATION_STATUS_REJECTED = "Your application has been rejected; you are not eligible to become a candidate";

    public static final String VOTE_SUBMIT_SUCCESS = "Your vote has been saved";
    public static final String VOTE_SUBMIT_FAIL_NO_CANDIDATE_SELECTED = "Please choose a candidate to vote";

    public static final String CANDIDATE_WITHDRAW_BEFORE = "Are you sure you want to withdraw from candidacy? This action cannot be undone.";
    public static final String CANDIDATE_WITHDRAW_FAIL_CANCELLED = "Withdrawal has been canceled";
    public static final String CANDIDATE_WITHDRAW_SUCCESS = "You have withdrawn from candidacy";


    // EXTRA MESSAGES
    public static final String ELECTION_CREATE_FAIL_ELECTION_FINISHED = "Election is finished, cannot update";

    public static final String ELECTION_END_FAIL_ELECTION_ALREADY_FINISHED = "This election has already finished";

    public static final String CANDIDATE_APPLICATION_SUBMIT_FAIL_NO_ELECTION = "There is no election for your department yet.";
    public static final String CANDIDATE_APPLICATION_SUBMIT_FAIL_ALREADY_CANDIDATE = "You are already a candidate.";
    public static final String CANDIDATE_APPLICATION_SUBMIT_FAIL_NOT_ELIGIBLE = "You are not eligible for candidacy.";

    public static final String VOTE_SUBMIT_FAIL_ALREADY_VOTED = "You have already voted";
    public static final String VOTE_SUBMIT_FAIL_INVALID_CANDIDATE = "The candidate you have chosen is not valid";

    public static final String CANDIDATE_WITHDRAW_FAIL_NOT_CANDIDATE = "You are not a candidate";

}
